package nyla.solutions.global.data;

import java.io.Serializable;

import nyla.solutions.global.data.Copier;
import nyla.solutions.global.data.Data;
import nyla.solutions.global.data.Numbered;
import nyla.solutions.global.data.NumberedProperty;
import nyla.solutions.global.data.Property;

/**
 * <pre>
 * NumberedProperty represents a number name/value object
 * </pre> 
 * @author Gregory Green
 * @version 1.0
 */
public class NumberedProperty extends Property
implements Comparable<Object>, Copier, Numbered
{
   /**
    * 
    * @see java.lang.Comparable#compareTo(java.lang.Object)
    */
   public int compareTo(Object aOther)
   {
      NumberedProperty other = (NumberedProperty)aOther;
      
      //compare names
      int comparedName = other.getName().compareTo(this.getName());
      if(comparedName != 0)
         return comparedName;
      
      //compare numbers
      Integer othernumber = Integer.valueOf(other.number);
      
      return Integer.compare(this.number, othernumber.intValue());
      //----------------------------------------
   }
	
	   /**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + number;
		return result;
	}
	
	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		NumberedProperty other = (NumberedProperty) obj;
		if (number != other.number)
			return false;
		return true;
	}

/**
    * 
    * @see nyla.solutions.global.data.Copier#copy(nyla.solutions.global.data.Copier)
    */
   public void copy(Copier aFrom)
   {
      if (aFrom == null)
         throw new IllegalArgumentException(
         "aFrom required in NumberedProperty.copy");
      
      if (!(aFrom instanceof NumberedProperty))
         throw new IllegalArgumentException(
         "aFrom instanceof NumberedProperty required in NumberedProperty.copy");
      
      NumberedProperty numberedProperty = (NumberedProperty)aFrom;
      this.setValue((Serializable)numberedProperty.getValue());
      this.setName(numberedProperty.getName());
      this.setNumber(numberedProperty.getNumber());
   }//--------------------------------------------
   /**
    * Constructor for NumberedProperty initializes internal 
    * data settings.
    * 
    */
   public NumberedProperty()
   {
      super();
   }
   
   /**
    * @return Returns the number.
    */
   public int getNumber()
   {
      return number;
   }
   /**
    * @param number The number to set.
    */
   public void setNumber(int number)
   {
      this.number = number;
   }//--------------------------------------------
   private int number = Data.NULL;
   static final long serialVersionUID = NumberedProperty.class.getName()
   .hashCode();
}
