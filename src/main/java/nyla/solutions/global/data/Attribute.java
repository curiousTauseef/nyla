package nyla.solutions.global.data;

import java.io.Serializable;

import nyla.solutions.global.data.Mappable;
import nyla.solutions.global.data.Nameable;

/**
 * <pre>
 * Attribute provides a set of functions to
 * </pre> 
 * @author Gregory Green
 * @version 1.0
 */
public interface Attribute<K,V> extends Serializable, Mappable<K,V>, Nameable
{
   /**
    * 
    * @return attribute name
    */
   public String getName();
   
   /**
    * 
    * @param aName the name
    */
   public void setName(String aName);
   
   /**
    * 
    * @param aValue the value
    */
   public void setValue(Serializable aValue);
   
   /**
    * 
    * @param aValue the property value
    * @return true if string version of the property value
    * equals (ignore case) aValue
    */
   public boolean equalsValueIgnoreCase(Object aValue);
   
}
