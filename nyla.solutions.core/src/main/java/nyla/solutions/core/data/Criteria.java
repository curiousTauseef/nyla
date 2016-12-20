package nyla.solutions.core.data;

import java.io.Serializable;

import nyla.solutions.core.data.Copier;
import nyla.solutions.core.data.Criteria;
import nyla.solutions.core.data.Data;
import nyla.solutions.core.data.Identifier;
import nyla.solutions.core.data.PrimaryKey;

/**
 * 
 * <pre>
 * Criteria is a value object representation of data that
 * can be selected from a datastore and has an integer primary key 
 * identifier.
 * </pre> 
 * @author Gregory Green
 * @version 1.0
 */

public class Criteria extends Data 
implements Comparable<Object>, PrimaryKey, Cloneable, Copier, Serializable, Identifier
{

   /**
    * 
    * Constructor for Criteria initializes primaryKey to -1.
    */

   protected Criteria()

   {

      primaryKey = -1;

   }//--------------------------------------------
   /**
    * 
    * this.setPrimaryKey(Integer.valueOf(aKey.toString()));
    * @see solutions.global.data.Key#setKey(java.lang.Object)
    */
 /*  public void setKey(Object aKey)
   {
      if (aKey == null)
         throw new IllegalArgumentException("aKey required in Criteria.setKey");
      
      
      if (!Text.isInteger(aKey.toString()))
         throw new IllegalArgumentException("Integer aKey required in Criteria.setKey key="+aKey);
      else if(aKey instanceof Integer)
      {
         this.setPrimaryKey((Integer)aKey);
      }
      else
      {
         this.setPrimaryKey(Integer.valueOf(aKey.toString()));
      }
      
   }*/
   //--------------------------------------------
   /**
    * 
    * Constructor for Criteria initalizes internal 
    * data settings.
    * @param aPK the primary key
    * @throws IllegalArgumentException
    */

   public Criteria(int aPK) throws IllegalArgumentException

   {

      primaryKey = -1;

      setPrimaryKey(aPK);

   }//--------------------------------------------

   /**

    * 

    * Constructor for Criteria initalizes primaryKey to aPK

    * @param aPK the primary key

    */   

   public Criteria(Criteria aPK) throws IllegalArgumentException

   {

      primaryKey = -1;

      setPrimaryKeyObject(aPK);

   }//--------------------------------------------

   /**

    * 

    * Constructor for Criteria initializes internal 

    * data settings.

    * @param aPK the primary key the set

    * @throws IllegalArgumentException

    */

   public Criteria(String aPK) throws IllegalArgumentException

   {

      primaryKey = -1;

      setPrimaryKeyString(aPK);

   }//--------------------------------------------

   /**

    * 

    * @return the primary key

    * @see PrimaryKey#getPrimaryKey()

    */

   public int getPrimaryKey()

   {

      return primaryKey;

   }//--------------------------------------------
   /**
    * 
    * @see java.lang.Object#clone()
    */
   public Object clone() throws CloneNotSupportedException
   {
      return super.clone();    
   }//--------------------------------------------   

   /**

    * Set primary key

    * @param primaryKey the primary key to set

    * @throws IllegalArgumentException primary key is < 1

    */

   public void setPrimaryKey(int primaryKey) 
   throws IllegalArgumentException
   {

      if (primaryKey < 1)
      {
         this.primaryKey = Data.NULL;
      }
      else
      {

         this.primaryKey = primaryKey;

         //resetNew();

         return;
      }
   }//--------------------------------------------
   public void setPrimaryKeyInteger(Integer aInteger)
   {
      if (aInteger == null)
         throw new IllegalArgumentException(
         "aInteger required in Criteria.setPrimaryKey");
      
      setPrimaryKey(aInteger.intValue());
   }//--------------------------------------------

   /**

    * Set primary key

    * @param primaryKey the primary key to set

    * @throws IllegalArgumentException primary key is < 1

    */

   protected void setPrimaryKeyObject(PrimaryKey aCriteria)

         throws IllegalArgumentException

   {

      if (aCriteria == null)

      {

         return;

      }

      else

      {

         setPrimaryKey(aCriteria.getPrimaryKey());

         return;

      }

   }//--------------------------------------------

   /**
    * @param object the object to compare
    * @see java.lang.Comparable#compareTo(java.lang.Object)
    * @return new Integer(getPrimaryKey())).compareTo(new Integer(vo
    * .getPrimaryKey())
    */

   public int compareTo(Object object)
   {

      if (!(object instanceof Criteria))
      {
         return -1;
      }
      else
      {

         Criteria vo = (Criteria) object;

         return Integer.compare(getPrimaryKey(), vo.getPrimaryKey());
      }

   }//--------------------------------------------

   protected void setPrimaryKeyString(String primaryKey)

         throws IllegalArgumentException

   {

      setPrimaryKey(Integer.parseInt(primaryKey));

   }//--------------------------------------------
   /**
    * Set primaryKey = Data.NULL
    *
    */
   public void resetNew() 
   {
       this.primaryKey = Data.NULL;
       
   }//--------------------------------------------
   /**
    * 
    * 
    * @see nyla.solutions.core.data.Copier#copy(nyla.solutions.core.data.Copier)
    */
   public void copy(Copier aOther)
   {

      Criteria other = (Criteria)aOther;

      if(other == null)

         return;

      

      if(this == other)

         return;      

     if(other.primaryKey > 0) 
       this.primaryKey = other.primaryKey;

   }//--------------------------------------------
   /**
    * 
    * 
    * @see java.lang.Object#equals(java.lang.Object)
    */
   public boolean equals(Object o)

   {

      if (o == null)

         return false;

      if (o instanceof Criteria)

      {

         Criteria otherKey = (Criteria) o;

         return primaryKey == otherKey.primaryKey;

      }

      else

      {

         return false;

      }

   }//--------------------------------------------

   /**

    * 

    * @return  new Integer(primaryKey)).hashCode()

    * @see java.lang.Object#hashCode()

    */

   public int hashCode()

   {

      return (Integer.valueOf(primaryKey)).hashCode();

   }//--------------------------------------------
   /**
    * 
    * @return null if primaryKey < 1 else
    * new Integer(primaryKey)
    */
   public String getId()
   {
     return id;
   }//--------------------------------------------
   /**
    * @return this.getId()
    * @see nyla.solutions.core.data.Mappable#getKey()
    */
   public Object getKey()
   {
      return this.getId();
   }//--------------------------------------------
   /**
    * @return this
    * @see nyla.solutions.core.data.Mappable#getValue()
    */
   //public Object getValue()
   //{
   //   return this;
   //}//--------------------------------------------   
   /*public void setId(String id)
   {
      this.setKey(id);      
   }*/
   //--------------------------------------------
   
   private int primaryKey = Data.NULL;
   /**
 * @param id the id to set
 */
public void setId(String id)
{
	this.id = id;
}
private String id;
   static final long serialVersionUID = Criteria.class.getName().hashCode();

}
