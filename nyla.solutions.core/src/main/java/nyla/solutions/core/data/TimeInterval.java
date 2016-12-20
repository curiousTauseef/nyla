package nyla.solutions.core.data;

import java.util.Date;

public interface TimeInterval
{
   /**
    * @return the start
    */
   public abstract Date getStartDate();
   // --------------------------------------------

   /**
    * @param start the start to set
    */
   public abstract void setStartDate(Date start);
   //----------------------------------------------
   
   /**
    * @return the end
    */
   public abstract Date getEndDate();
   // --------------------------------------------

   /**
    * @param end the end to set
    */
   public abstract void setEndDate(Date end);
   // --------------------------------------------
}