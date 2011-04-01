/*
 * Copyright 2004 Sun Microsystems, Inc.
 *
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
 *
 */
package com.sun.syndication.feed.impl;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;

/**
 * Obtains all property descriptors from a bean (interface or implementation).
 * <p>
 * The java.beans.Introspector does not process the interfaces hierarchy chain, this one does.
 * <p>
 * @author Alejandro Abdelnur
 *
 */
public class BeanIntrospector {

    private static final Map _introspected = new HashMap();

    public static synchronized PropertyDescriptor[] getPropertyDescriptors(Class klass) throws IntrospectionException {
        PropertyDescriptor[] descriptors = (PropertyDescriptor[]) _introspected.get(klass);
        if (descriptors==null) {
            descriptors = getPDs(klass);
            _introspected.put(klass,descriptors);
        }
        return descriptors;
    }

    private static PropertyDescriptor[] getPDs(Class klass) throws IntrospectionException {
        Method[] methods = klass.getMethods();
        Map getters = getPDs(methods,false);
        Map setters = getPDs(methods,true);
        List pds     = merge(getters,setters);
        PropertyDescriptor[] array = new PropertyDescriptor[pds.size()];
        pds.toArray(array);
        return array;
    }

    private static final String SETTER = "set";
    private static final String GETTER = "get";
    private static final String BOOLEAN_GETTER = "is";

    private static Map getPDs(Method[] methods,boolean setters) throws IntrospectionException {
        Map pds = new HashMap();
        for (int i=0;i<methods.length;i++) {
            String pName = null;
            PropertyDescriptor pDescriptor = null;
            if ((methods[i].getModifiers()&Modifier.PUBLIC)!=0) {
                if (setters) {
                    if (methods[i].getName().startsWith(SETTER) &&
                        methods[i].getReturnType()==void.class && methods[i].getParameterTypes().length==1) {
                        pName = Introspector.decapitalize(methods[i].getName().substring(3));
                        pDescriptor = new PropertyDescriptor(pName,null,methods[i]);
                    }
                }
                else {
                    if (methods[i].getName().startsWith(GETTER) &&
                        methods[i].getReturnType()!=void.class && methods[i].getParameterTypes().length==0) {
                        pName = Introspector.decapitalize(methods[i].getName().substring(3));
                        pDescriptor = new PropertyDescriptor(pName,methods[i],null);
                    }
                    else
                    if (methods[i].getName().startsWith(BOOLEAN_GETTER) &&
                        methods[i].getReturnType()==boolean.class && methods[i].getParameterTypes().length==0) {
                        pName = Introspector.decapitalize(methods[i].getName().substring(2));
                        pDescriptor = new PropertyDescriptor(pName,methods[i],null);
                    }
                }
            }
            if (pName!=null) {
                pds.put(pName,pDescriptor);
            }
        }
        return pds;
    }

    private static List merge(Map getters,Map setters) throws IntrospectionException {
        List props = new ArrayList();
        Set processedProps = new HashSet();
        Iterator gs = getters.keySet().iterator();
        while (gs.hasNext()) {
            String name = (String) gs.next();
            PropertyDescriptor getter = (PropertyDescriptor) getters.get(name);
            PropertyDescriptor setter = (PropertyDescriptor) setters.get(name);
            if (setter!=null) {
                processedProps.add(name);
                PropertyDescriptor prop = new PropertyDescriptor(name,getter.getReadMethod(),setter.getWriteMethod());
                props.add(prop);
            }
            else {
                props.add(getter);
            }
        }
        Set writeOnlyProps = new HashSet(setters.keySet());
        writeOnlyProps.removeAll(processedProps);
        Iterator ss = writeOnlyProps.iterator();
        while (ss.hasNext()) {
            String name = (String) ss.next();
            PropertyDescriptor setter = (PropertyDescriptor) setters.get(name);
            props.add(setter);
        }
        return props;
    }

}
