package nyla.solutions.formInjection.formatter;

import java.io.Serializable;

import nyla.solutions.global.data.Textable;


/**
 * 
 * <pre>
 * 
 *  NumberLabelDecorator provides a set of functions to the auto number questions.
 *  
 *  
 * </pre>
 * 
 * @author Gregory Green
 * @version 1.0
 */
public class NumberLabelDecorator 
implements Serializable, Textable
{
   
   static final long serialVersionUID = NumberLabelDecorator.class.getName()
   .hashCode();
   public NumberLabelDecorator()
   {
   }//--------------------------------------------
   public NumberLabelDecorator(int sectionId)
   {
      this.sectionId = sectionId;
   }//--------------------------------------------
   public int getSectionId()
   {
      return sectionId;
   }//--------------------------------------------
   public int getFormComponentId()
   {
      return formComponentId;
   }//--------------------------------------------
   public void setFormComponentId(int formComponentId)
   {
      this.formComponentId = formComponentId;
   }//--------------------------------------------
   public int increment()
   {
      return ++count;
   }//--------------------------------------------
   public int getNumber()
   {
      return count;
   }//--------------------------------------------
   public void setNumber(int count)
   {
      this.count = count;
   }//--------------------------------------------
   public NumberLabelDecorator getParent()
   {
      return parent;
   }//--------------------------------------------
   public void setParent(NumberLabelDecorator counter)
   {
      this.parent = counter;
   }//--------------------------------------------
   /**
    * 
    * @return getText()
    * @see java.lang.Object#toString()
    */
   public String toString()
   {
      return getText();
   }//--------------------------------------------
   /**
    * @return number label
    * 
    * @see com.bms.informatics.gcsm.common.data.Textable#getText()
    */
   public String getText()
   {
      if (parent == null)
         return sectionId + "-" + count;
      return parent.toString() + "." + count ;
   }//--------------------------------------------
   /**
    * 
    * @see java.lang.Comparable#compareTo(java.lang.Object)
    */
   public int compareTo(Object aOther)
   {
      NumberLabelDecorator other = (NumberLabelDecorator)aOther;
      return new Integer(this.count).compareTo(new Integer(other.count));
      //----------------------------------------
   }
   private int sectionId ;

   private int formComponentId;

   private int count = 0;

   private NumberLabelDecorator parent;

   //private AutoCounter child;
}
