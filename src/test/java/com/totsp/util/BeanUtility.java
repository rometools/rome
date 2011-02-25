/*
 * StringUtil.java
 *
 * Created on May 7, 2004, 7:43 PM
 *
 * Copyright (C) 2004  Robert Cooper, Temple of the Screaming Penguin
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */
package com.totsp.util;
import java.beans.*;
import java.lang.reflect.*;

/** This class contains some generic methods for working with String.
 * @version $Rev: 87 $
 * @author  <a href="mailto:cooper@screaming-penguin.com">Robert Cooper</a>
 */
public class BeanUtility {
    
    /** Creates a new instance of StringUtil */
    private BeanUtility() { }
    
    /** This method takes a JavaBean and generates a standard toString() type result for it.
     * @param o JavaBean object to stringinate
     * @return STRINGIATION! Stringingating the countryside. Stringinating all the peasants.
     */
    public static String beanToString( Object o ){
        StringBuffer result = new StringBuffer();
        if(o == null)
            return "--- null";
        result.append( "--- begin");result.append( o.getClass().getName()); result.append(" hash: ");
        result.append( o.hashCode() ); result.append( "\r\n");
        try{
            PropertyDescriptor[] pds = Introspector.getBeanInfo( o.getClass() ).getPropertyDescriptors();
            for( int pdi = 0; pdi < pds.length; pdi ++ ){
                try{
                    result.append( "Property: "+ pds[pdi].getName() + " Value: " + pds[pdi].getReadMethod().invoke( o, null ) );
                } catch( IllegalAccessException iae ){
                    result.append( "Property: "+ pds[pdi].getName() + " (Illegal Access to Value) ");
                }
                catch( InvocationTargetException iae ){
                    result.append( "Property: "+ pds[pdi].getName() + " (InvocationTargetException) " + iae.toString() );
                }
                catch( Exception e ){
                     result.append( "Property: "+ pds[pdi].getName() +" (Other Exception )"+e.toString());
                }
                result.append( "\r\n");
            }
            
            
        } catch( IntrospectionException ie){
            result.append( "Introspection Exception: " + ie.toString() ); result.append( "\r\n");
        }
        result.append( "--- end " ); result.append( o.getClass().getName()); result.append(" hash: ");
        result.append( o.hashCode() ); result.append( "\n");
        return result.toString();
    }
    
    /** This method takes 2 JavaBeans of the same type and copies the properties of one bean to the other.
     * Any attempts that have an IllegalAccessException will be ignored. This will also NOT recurse into nested bean
     * results. References to existing beanage will be includes. Try using .clone() for that stuff.
     * @param from Source Bean
     * @param to Desitnation Bean
     */
    public static void copyBeanToBean( Object from, Object to ) throws InvocationTargetException, IntrospectionException{
        PropertyDescriptor[] pds = Introspector.getBeanInfo( from.getClass() ).getPropertyDescriptors();
        for( int i=0; i < pds.length; i++){
            try{
                if(pds[i].getName().equals("class")) continue;
                Object[] value = {pds[i].getReadMethod().invoke(from, null) };
                pds[i].getWriteMethod().invoke( to, value ) ;
            } catch( IllegalAccessException iae ){
                //Im just going to ignore any properties I don't have access too.
            }
        }
        
    }
    
    public static String[] getPropertyNames(Object o) throws IntrospectionException {
        PropertyDescriptor[] pds = Introspector.getBeanInfo( o.getClass() ).getPropertyDescriptors();
        String[] propertyNames = new String[ pds.length];
        for( int i=0; i< pds.length; i++){
            propertyNames[i] = pds[i].getName();
        }
        return propertyNames;
    }
    
    public static Object getProperty( Object o, String propertyName ) throws Exception {
        PropertyDescriptor[] pds = Introspector.getBeanInfo( o.getClass() ).getPropertyDescriptors();
        for( int i=0; i< pds.length; i++){
            if( pds[i].getName().equals(propertyName)){
                return pds[i].getReadMethod().invoke( o, null ) ;
            }
        }
        throw new Exception("Property not found.");
    }
    public static void setProperty( Object o, String propertyName, Object value ) throws Exception{
        PropertyDescriptor[] pds = Introspector.getBeanInfo( o.getClass() ).getPropertyDescriptors();
        for( int i=0; i< pds.length; i++){
            if( pds[i].getName().equals(propertyName)){
                pds[i].getWriteMethod().invoke( o, new Object[]{value} );
                return;
            }
        }
        throw new Exception("Property not found.");
    }
    
    public static Class getPropertyType( Object o, String propertyName ) throws Exception{
        PropertyDescriptor[] pds = Introspector.getBeanInfo( o.getClass() ).getPropertyDescriptors();
        for( int i=0; i< pds.length; i++){
            if( pds[i].getName().equals(propertyName)){
                return pds[i].getPropertyType();
            }
        }
        throw new Exception("Property not found.");
    }
}
