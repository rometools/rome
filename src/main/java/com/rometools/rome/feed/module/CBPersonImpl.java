/*
 * Copyright 2015 MetricStream, Inc.
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
package com.rometools.rome.feed.module;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.rometools.rome.feed.CopyFrom;
import com.rometools.rome.feed.impl.CopyFromHelper;
import com.rometools.rome.feed.impl.ObjectBean;

/**
 * Person of the Central Bank ModuleImpl, default implementation.
 * <p>
 *
 * @see <a href="http://www.cbwiki.net/wiki/index.php/RSS-CBMain">RSS CB module</a>.
 * @author Norbert Kiesel <nkiesel@metricstream.com>
 * @author Manish SV Kumar <manish.svk@metricstream.com>
 *
 */

public class CBPersonImpl implements Cloneable, Serializable, CBPerson {

    private static final long serialVersionUID = 1L;
    private static final CopyFromHelper COPY_FROM_HELPER;

    private final ObjectBean objBean;
    private String givenName;
    private String surname;
    private String personalTitle;
    private String nameAsWritten;
    private CBRole role;
    
    static {
        final Map<String, Class<?>> basePropInterfaceMap = new HashMap<String, Class<?>>();
        basePropInterfaceMap.put("givenName", String.class);
        basePropInterfaceMap.put("surname", String.class);
        basePropInterfaceMap.put("personalTitle", String.class);
        basePropInterfaceMap.put("nameAsWritten", String.class);

        final Map<Class<? extends CopyFrom>, Class<?>> basePropClassImplMap = new HashMap<Class<? extends CopyFrom>, Class<?>>();
        basePropClassImplMap.put(CBRole.class, CBRoleImpl.class);

        COPY_FROM_HELPER = new CopyFromHelper(CBPerson.class, basePropInterfaceMap, basePropClassImplMap);
    }

    /**
     * Default constructor. All properties are set to <b>null</b>.
     * <p>
     *
     */
    public CBPersonImpl() {
        objBean = new ObjectBean(this.getClass(), this);
    }

    /**
     * Creates a deep 'bean' clone of the object.
     * <p>
     *
     * @return a clone of the object.
     * @throws CloneNotSupportedException thrown if an element of the object cannot be cloned.
     *
     */
    @Override
    public Object clone() throws CloneNotSupportedException {
        return objBean.clone();
    }

    /**
     * Indicates whether some other object is "equal to" this one as defined by the Object equals()
     * method.
     * <p>
     *
     * @param other he reference object with which to compare.
     * @return <b>true</b> if 'this' object is equal to the 'other' object.
     *
     */
    @Override
    public boolean equals(final Object other) {
        if (!(other instanceof CBPersonImpl)) {
            return false;
        }
        return objBean.equals(other);
    }

    /**
     * Returns a hashcode value for the object.
     * <p>
     * It follows the contract defined by the Object hashCode() method.
     * <p>
     *
     * @return the hashcode of the bean object.
     *
     */
    @Override
    public int hashCode() {
        return objBean.hashCode();
    }

    /**
     * Returns the String representation for the object.
     * <p>
     *
     * @return String representation for the object.
     *
     */
    @Override
    public String toString() {
        return objBean.toString();
    }

    @Override
    public Class<CBPerson> getInterface() {
        return CBPerson.class;
    }

    @Override
    public void copyFrom(final CopyFrom obj) {
        COPY_FROM_HELPER.copy(this, obj);
    }

    /**
     * Returns the CB person givenName.
     * <p>
     *
     * @return the CB person givenName, <b>null</b> if none.
     *
     */
    @Override
    public String getGivenName(){
    	return givenName;
    }

    /**
     * Sets the CB person givenName.
     * <p>
     *
     * @param givenName the CB person givenName to set, <b>null</b> if none.
     *
     */
    @Override
    public void setGivenName(String givenName){
    	this.givenName = givenName;
    }
    
    /**
     * Returns the CB person surname.
     * <p>
     *
     * @return the CB person surname, <b>null</b> if none.
     *
     */
    @Override
    public String getSurname(){
    	return surname;
    }

    /**
     * Sets the CB person surname.
     * <p>
     *
     * @param surname the CB person surname to set, <b>null</b> if none.
     *
     */
    @Override
    public void setSurname(String surname){
    	this.surname = surname;
    }
    
    /**
     * Returns the CB person personalTitle.
     * <p>
     *
     * @return the CB person personalTitle, <b>null</b> if none.
     *
     */
    @Override
    public String getPersonalTitle(){
    	return personalTitle;
    }

    /**
     * Sets the CB person personalTitle.
     * <p>
     *
     * @param personalTitle the CB person personalTitle to set, <b>null</b> if none.
     *
     */
    @Override
    public void setPersonalTitle(String personalTitle){
    	this.personalTitle = personalTitle;
    }

    /**
     * Returns the CB person nameAsWritten.
     * <p>
     *
     * @return the CB person nameAsWritten, <b>null</b> if none.
     *
     */
    @Override
    public String getNameAsWritten(){
    	return nameAsWritten;
    }

    /**
     * Sets the CB person nameAsWritten.
     * <p>
     *
     * @param nameAsWritten the CB person nameAsWritten to set, <b>null</b> if none.
     *
     */
    @Override
    public void setNameAsWritten(String nameAsWritten){
    	this.nameAsWritten = nameAsWritten;
    }
    
    /**
     * Returns the CB role.
     * <p>
     *
     * @return the CB role, <b>null</b> if none.
     *
     */
    @Override
    public CBRole getRole(){
    	return role;
    }

    /**
     * Sets the CB role.
     * <p>
     *
     * @param role the CB person role to set, <b>null</b> if none.
     *
     */
    @Override
    public void setRole(CBRole role){
    	this.role = role;
    }

}
