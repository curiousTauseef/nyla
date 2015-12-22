package nyla.solutions.global.data;




import java.io.*;
import java.util.*;

import nyla.solutions.global.data.Criteria;
import nyla.solutions.global.data.Updateable;
import nyla.solutions.global.util.Debugger;
//import solutions.global.data.CriteriaComparator;
//import solutions.global.data.UpdateDateComparator;


/**

 * <b>Data</b> represents a serializable data type.

 * @author Gregory Green

 * @version 1.0 

 */

public abstract class Data 

//extends AbstractDirtyMarker 

implements Serializable

{

   /**
	 * 
	 */
	private static final long serialVersionUID = -2411033885084918560L;



/**

    * Represent a true value

    */

   public static final int NULL = -1;

   

   /**

    * Represent a true value

    */

   public static final String TRUE = "T";

   

   /**

    * Represent a flag value

    */

   public static final String FALSE = "F";

   

   /**

    * Represent a true value

    */

   public static final String YES = "Y";

   

   /**

    * Represent a flase value

    */

   public static final String NO = "N";

   



   /**

    * Determines whether the provided str is equal to null

    * or the length is equal to zero

    */

   public static boolean isNull(String str)

   {

      return str == null || str.trim().length() == 0 ||

            "null".equals(str.trim());

   } //---------------------------------------------

   /**

    * @return the string representation of a object

    */

   public String toString()

   {

      return Debugger.toString(this);

   }

   /**
    * Sort a list 
    */
   public static Collection<Object> sortByCriteria(Collection<Object> aVOs)
   {

      List<Object> list = null;

      if (aVOs instanceof List)

         list = (List<Object>) aVOs;

      else

         list = new ArrayList<Object>(aVOs);

      Collections.sort(list, new CriteriaComparator());

      return list;

   } //-----------------------------------------------   

   /**

       * Sort a list 

       */

   @SuppressWarnings({ "rawtypes", "unchecked" })
public static Collection<Object> sortByUpdateDate(Collection aVOs)

   {

      List<Object> list = null;

      if (aVOs instanceof List)

         list = (List<Object>) aVOs;

      else

         list = new ArrayList<Object>(aVOs);

      Collections.sort(list, new UpdateDateComparator());

      return list;

   } //-----------------------------------------------       

   /**
    * Comparator for the status active flag
    */

   public static class CriteriaComparator implements Comparator<Object>, Serializable
   {

      /**

       *  implementation for the Comparator interface

       *  @param first the first object to be compared

       *  @param second the second object to be compared to

       *  @throws ClassCastException if first or second is not an instance of this class

       */

      public int compare(Object first, Object second)

      {

         if (first == null)

            return 1;

         Criteria vo1 = (Criteria) first;

         Criteria vo2 = (Criteria) second;

         return Integer.valueOf(vo1.getPrimaryKey()).compareTo(

            Integer.valueOf(vo2.getPrimaryKey()));

      }
      
      static final long serialVersionUID = CriteriaComparator.class.getName()
      .hashCode();
   } //-------------------------------------------------------------- 

   /**

    * Comparator for th status active flag

    */

   public static class UpdateDateComparator implements Comparator<Object>, Serializable
   {

      /**

       *  implementation for the Comparator interface

       *  @param first the first object to be compared

       *  @param second the second object to be compared to

       *  @throws ClassCastException if first or second is not an instance of this class

       */

      public int compare(Object first, Object second)

      {

         if (first == null)
            return 1;

         Updateable vo1 = (Updateable) first;

         Updateable vo2 = (Updateable) second;

         if (vo1.getUpdateDate() == null)

            return -1;

         return vo1.getUpdateDate().compareTo(vo2.getUpdateDate());

      }
      
      static final long serialVersionUID = UpdateDateComparator.class.getName()
      .hashCode();
   } //--------------------------------------------------------------





}

