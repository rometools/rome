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

import com.sun.syndication.feed.CopyFrom;
import com.sun.syndication.feed.impl.BeanIntrospector;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.*;

/**
 * @author Alejandro Abdelnur
 */
public class CopyFromHelper {
    private static final Object[] NO_PARAMS = new Object[0];

    private Class _beanInterfaceClass;
    private Map _baseInterfaceMap; //ENTRIES(propertyName,interface.class)
    private Map _baseImplMap;      //ENTRIES(interface.class,implementation.class)

    public CopyFromHelper(Class beanInterfaceClass,Map basePropInterfaceMap,Map basePropClassImplMap) {
        _beanInterfaceClass = beanInterfaceClass;
        _baseInterfaceMap = basePropInterfaceMap;
        _baseImplMap = basePropClassImplMap;
    }

    public void copy(Object target,Object source) {
        try {
            PropertyDescriptor[] pds = BeanIntrospector.getPropertyDescriptors(_beanInterfaceClass);
            if (pds!=null) {
                for (int i=0;i<pds.length;i++) {
                    String propertyName = pds[i].getName();
                    Method pReadMethod = pds[i].getReadMethod();
                    Method pWriteMethod = pds[i].getWriteMethod();
                    if (pReadMethod!=null && pWriteMethod!=null &&       // ensure it has getter and setter methods
                        pReadMethod.getDeclaringClass()!=Object.class && // filter Object.class getter methods
                        pReadMethod.getParameterTypes().length==0 &&     // filter getter methods that take parameters
                        _baseInterfaceMap.containsKey(propertyName)) {   // only copies properties defined as copyFrom-able
                        Object value = pReadMethod.invoke(source,NO_PARAMS);
                        if (value!=null) {
                            Class baseInterface = (Class) _baseInterfaceMap.get(propertyName);
                            value = doCopy(value,baseInterface);
                            pWriteMethod.invoke(target,new Object[]{value});
                        }
                    }
                }
            }
        }
        catch (Exception ex) {
            throw new RuntimeException("Could not do a copyFrom "+ex, ex);
        }
    }

    private CopyFrom createInstance(Class interfaceClass) throws Exception {
        if( _baseImplMap.get(interfaceClass) == null ){
            return null;
        }
        else {
            return (CopyFrom) ((Class)_baseImplMap.get(interfaceClass)).newInstance();
        }
    }

    private Object doCopy(Object value,Class baseInterface) throws Exception {
        if (value!=null) {
            Class vClass = value.getClass();
            if (vClass.isArray()) {
                value = doCopyArray(value,baseInterface);
            }
            else
            if (value instanceof Collection) {
                value = doCopyCollection((Collection)value,baseInterface);
            }
            else
            if (value instanceof Map) {
                value = doCopyMap((Map)value,baseInterface);
            }
            else
            if (isBasicType(vClass)) {
                // value = value; // nothing to do here
                if (value instanceof Date) { // because Date it is not inmutable
                    value = ((Date)value).clone();
                }
            }
            else { // it goes CopyFrom
                if (value instanceof CopyFrom) {
                    CopyFrom source = (CopyFrom) value;
                    CopyFrom target = createInstance(source.getInterface());
                    target = target == null ?  (CopyFrom) value.getClass().newInstance() : target;
                    target.copyFrom(source);
                    value = target;
                }
                else {
                    throw new Exception("unsupported class for 'copyFrom' "+value.getClass());
                }
            }
        }
        return value;
    }

    private Object doCopyArray(Object array,Class baseInterface) throws Exception {
        Class elementClass = array.getClass().getComponentType();
        int length = Array.getLength(array);
        Object newArray = Array.newInstance(elementClass,length);
        for (int i=0;i<length;i++) {
            Object element = doCopy(Array.get(array,i),baseInterface);
            Array.set(newArray,i,element);
        }
        return newArray;
    }

    private Object doCopyCollection(Collection collection,Class baseInterface) throws Exception {
        // expecting SETs or LISTs only, going default implementation of them
        Collection newColl = (collection instanceof Set) ? (Collection)new HashSet() : (Collection)new ArrayList();
        Iterator i = collection.iterator();
        while (i.hasNext()) {
            Object element = doCopy(i.next(),baseInterface);
            newColl.add(element);
        }
        return newColl;
    }

    private Object doCopyMap(Map map,Class baseInterface) throws Exception {
        Map newMap = new HashMap();
        Iterator entries = map.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry entry = (Map.Entry) entries.next();
            Object key = entry.getKey(); // we are assuming string KEYS
            Object element = doCopy(entry.getValue(),baseInterface);
            newMap.put(key,element);
        }
        return newMap;
    }

    private static final Set BASIC_TYPES = new HashSet();

    static {
        BASIC_TYPES.add(Boolean.class);
        BASIC_TYPES.add(Byte.class);
        BASIC_TYPES.add(Character.class);
        BASIC_TYPES.add(Double.class);
        BASIC_TYPES.add(Float.class);
        BASIC_TYPES.add(Integer.class);
        BASIC_TYPES.add(Long.class);
        BASIC_TYPES.add(Short.class);
        BASIC_TYPES.add(String.class);
        BASIC_TYPES.add(Date.class);
    }

    private boolean isBasicType(Class vClass) {
        return BASIC_TYPES.contains(vClass);
    }

}
