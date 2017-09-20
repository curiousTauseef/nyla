package nyla.solutions.formInjection.data;

import java.io.Serializable;

import nyla.solutions.global.data.Copier;
import nyla.solutions.global.data.Property;



/**
 * <pre>
 * AttributeFacts is a value object representation of the 
 * ATTRIBUTE_TBL table and associated entities.
 * </pre> 
 * @author Gregory Green
 * @version 1.0
 */
public class AttributeFacts extends Property
implements Serializable
{

   /**
    * Constructor for AttributeFacts initalizes internal 
    * data settings.
    * 
    */
   public AttributeFacts()
   {
      super();
   }//--------------------------------------------
   /**
    * @return Returns the defaultValue.
    */
   public Serializable getDefaultValue()
   {
      return (Serializable)getValue();
   }//--------------------------------------------
   /**
    * @return Returns the typeName.
    */
   public String getTypeName()
   {
      return typeName;
   }//--------------------------------------------
   /**
    * 
    */
   public void copy(Copier aFrom)
   {
     
      super.copy(aFrom);
     
      if(aFrom == null)
         return;
      
      this.typeName = ((AttributeFacts)aFrom).typeName;
   }//--------------------------------------------
   /**
    * @param typeName The typeName to set.
    */
   public void setTypeName(String typeName)
   {
      if (typeName == null)
         typeName = "";

      this.typeName = typeName;
   }//--------------------------------------------
   private String typeName = null;
   static final long serialVersionUID = AttributeFacts.class.getName()
   .hashCode();
}
