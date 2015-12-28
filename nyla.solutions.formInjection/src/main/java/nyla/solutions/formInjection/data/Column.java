package nyla.solutions.formInjection.data;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import nyla.solutions.global.util.Organizer;

/**
 * 
 * <pre>
 * Column is a value object representation of the Column table
 * and associated entities.
 * </pre>
 * 
 * @author Gregory Green
 * @version 1.0
 */
public class Column extends FormComponent
{

   /**
    * 
    * @return the map of choices for this component
    */
   public Map getChoiceMap()
   {
      if (choiceMap == null)
         choiceMap = new HashMap();

      return choiceMap;
   }// --------------------------------------------

   public Collection getQuestionChoices()
   {
      return choiceMap.values();
   }

   protected void setChoiceMap(Map choiceMap)
   {
      this.choiceMap = choiceMap != null ? choiceMap : new LinkedHashMap();
   }

   public boolean hasChoices()
   {
      Collection choices = this.getQuestionChoices();
      return !Organizer.isEmpty(choices);
   }// --------------------------------------------


   public Object getKey()
   {
      return colNumber;
   }

   public Integer getColNumber()
   {
      return colNumber;
   }

   public Integer getNumber()
   {
      return colNumber;
   }

   protected void setColNumber(Integer colNumber)
   {
      this.colNumber = colNumber;
   }

   public int getColSize()
   {
      return colSize;
   }

   protected void setColSize(int colSize)
   {
      this.colSize = colSize;
   }

   public String getName()
   {
      return name;
   }

   protected void setName(String name)
   {
      this.name = name;
   }

   public Integer getResponseTableId()
   {
      return responseTableId;
   }

   protected void setResponseTableId(Integer responseTableId)
   {
      this.responseTableId = responseTableId;
   }

   public Integer getResponseTypeId()
   {
      return responseTypeId;
   }

   protected void setResponseTypeId(Integer responseTypeId)
   {
      this.responseTypeId = responseTypeId;
   }

   public ResponseType getResponseType()
   {
      return responseType;
   }//--------------------------------------------
   /**
    * 
    * @param name the attribute name
    * @return the question attribute with a given name
    */
   public QuestionAttribute getAttribute(String name)
   {
      if(attributes ==null)
         return null;
      
      return (QuestionAttribute) attributes.get(name);
   }//--------------------------------------------

   public Map getAttributes()
   {
      if(attributes == null)
         return new HashMap(); //empty attributes
      
      return attributes;
   }//--------------------------------------------

   public String getText()
   {
      return this.name;
   }

   // -------------------------------------------------------
   // for retrofit
   // -------------------------------------------------------
   public QuestionAttribute findAttributeByName(String name)
   {
      return getAttribute(name);
   }//--------------------------------------------
   /**
    * 
    *
    * @see nyla.solutions.formInjection.data.FormComponent#toString()
    */
   public String toString()
   {
      ResponseType type = responseType;
      responseType = null;
      String s = super.toString();
      responseType = type;
      return s;
   }//--------------------------------------------
   /**
    * 
    * Compare the column numbers
    * @see nyla.solutions.formInjection.data.FormComponent#compareTo(java.lang.Object)
    */
   public int compareTo(Object o)
   {
      if(this.colNumber == null || o == null)
        return -1;
    
      Column other = (Column)o;
      //if(other.colNumber == null)
       //  return -1;
      
      return this.colNumber.compareTo(other.getColNumber());
   }//--------------------------------------------
   

   static final long serialVersionUID = Column.class.getName().hashCode();

   private Integer responseTableId;

   private Integer responseTypeId;

   private ResponseType responseType;

   private String name;

   private int colSize;

   private Integer colNumber;

   private Map choiceMap = new LinkedHashMap();

   private Map attributes = new HashMap();

}
