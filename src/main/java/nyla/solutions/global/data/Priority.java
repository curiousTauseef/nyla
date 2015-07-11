package nyla.solutions.global.data;


import nyla.solutions.global.data.Data;
import nyla.solutions.global.data.Mappable;
import nyla.solutions.global.data.PrimaryKey;
import nyla.solutions.global.data.Priority;
import nyla.solutions.global.util.Text;



/**

 * <pre>

 * Priority provides a set of functions to

 * </pre> 

 * @author Gregory Green

 * @version 1.0

 */

public class Priority
implements PrimaryKey, Mappable<Object,Object>

{



   /**

    * Constructor for Priority initializes internal 

    * data settings.

    * 

    */

   public Priority()

   {

      super();

   }//--------------------------------------------


   /**
    * 
    * this.setPrimaryKey(Integer.valueOf(aKey.toString()));
    * @see nyla.solutions.global.data.Key#setKey(java.lang.Object)
    */
   public void setKey(Object aKey)
   {
      if (aKey == null)
         throw new IllegalArgumentException("aKey required in Criteria.setKey");
      
      
      if (!Text.isInteger(aKey.toString()))
         throw new IllegalArgumentException("Integer aKey required in Criteria.setKey key="+aKey);
      
      this.setPrimaryKey(Integer.parseInt(aKey.toString()));
   }//--------------------------------------------

   /**

    * 

    * @see nyla.solutions.global.data.PrimaryKey#getPrimaryKey()

    */

   public int getPrimaryKey()

   {

      return primaryKey;

   }//--------------------------------------------

   /**

    * @return Returns the name.

    */

   public String getName()

   {

      return name;

   }//--------------------------------------------

   /**

    * @param name The name to set.

    */

   public void setName(String name)

   {

      if (name == null)

         name = "";



      this.name = name;

   }//--------------------------------------------

   /**

    * @param primaryKey The primaryKey to set.

    */

   public void setPrimaryKey(int primaryKey)

   {

      this.primaryKey = primaryKey;

   }//--------------------------------------------

   public void copy(Priority priority)

   {

      if (priority == null)

         throw new IllegalArgumentException("priority required in Priority");

      

      this.primaryKey = priority.primaryKey;

      this.name = priority.name;

      

   }//--------------------------------------------

   /**
    * @return name
    */

   public Object getKey()

   {

      return getName();      

   }//--------------------------------------------

   /**

    * @return priority name

    * @see nyla.solutions.global.data.Mappable#getValue()

    */

   public Object getValue()

   {

      return String.valueOf(primaryKey);

   }//--------------------------------------------

   public void setValue(Object aValue)

   {

      if(aValue == null)
         return;

      

      primaryKey =Integer.parseInt(aValue.toString());

   }//--------------------------------------------

   private int primaryKey  = Data.NULL;
   private String name = null;
   static final long serialVersionUID = Priority.class.getName().hashCode();

}

