/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/*
 * This class was copied from OpenBeans (Apache Harmony) and modified to
 * avoid using java.beans package, because it is not available on some
 * platforms (Android in particular).
 */

package com.rometools.rome.feed.impl;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class PropertyDescriptor {

    private final String name;
    private final Method getter;
    private final Method setter;

    public PropertyDescriptor(String name, Method getter, Method setter) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Bad property name");
        }

        this.name = name;
        this.getter = checkGetter(getter);
        this.setter = checkSetter(setter);
    }

    public String getName() {
        return name;
    }

    public Method getReadMethod() {
        return getter;
    }

    public Method getWriteMethod() {
        return setter;
    }

    public Class<?> getPropertyType() {
        if (getter != null) {
            return getter.getReturnType();
        } else if (setter != null) {
            Class<?>[] parameterTypes = setter.getParameterTypes();
            return parameterTypes[0];
        } else {
            return null;
        }
    }

    private Method checkGetter(Method method) {
        if (method != null) {
            int modifiers = method.getModifiers();
            if (!Modifier.isPublic(modifiers)) {
                throw new IllegalArgumentException("Modifier for getter method should be public");
            }
            Class<?>[] parameterTypes = method.getParameterTypes();
            if (parameterTypes.length != 0) {
                throw new IllegalArgumentException("Number of parameters in getter method is not equal to 0");
            }
            Class<?> returnType = method.getReturnType();
            if (returnType.equals(Void.TYPE)) {
                throw new IllegalArgumentException("Getter has return type void");
            }
            Class<?> propertyType = getPropertyType();
            if (propertyType != null && !returnType.equals(propertyType)) {
                throw new IllegalArgumentException("Parameter type in getter does not correspond to setter");
            }
        }
        return method;
    }

    private Method checkSetter(Method method) {
        if (method != null) {
            int modifiers = method.getModifiers();
            if (!Modifier.isPublic(modifiers)) {
                throw new IllegalArgumentException("Modifier for setter method should be public");
            }
            Class<?>[] parameterTypes = method.getParameterTypes();
            if (parameterTypes.length != 1) {
                throw new IllegalArgumentException("Number of parameters in setter method is not equal to 1");
            }
            Class<?> parameterType = parameterTypes[0];
            Class<?> propertyType = getPropertyType();
            if (propertyType != null && !propertyType.equals(parameterType)) {
                throw new IllegalArgumentException("Parameter type in setter does not correspond to getter");
            }
        }
        return method;
    }
}
