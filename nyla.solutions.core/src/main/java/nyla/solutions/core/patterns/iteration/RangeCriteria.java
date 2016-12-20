package nyla.solutions.core.patterns.iteration;

import nyla.solutions.core.data.Data;

/**
  <b>RangeCriteria</b> search criteria for returning a range of entries
  @author Gregory Green
  @version 1.0
*/

public class RangeCriteria extends Data
{
   /**
    * Default buffer size 20
    */
   public static final int DEFAULT_BUFFER_SIZE = 20;
   
   public RangeCriteria(int aBufferSize)
   {      
      the_bufferSize = aBufferSize;
      reset();
   }//---------------------------------------------
   /**
    * Set with default buffer size
    *
    */
   protected RangeCriteria()
   {      
      the_bufferSize = DEFAULT_BUFFER_SIZE;

   }//---------------------------------------------
   /**
    @return the first index of the current buffer slice
   */
   public final int first()
   {
      return the_first;
   }//---------------------------------------------
   /**
    * point to next buffer buffer slice
    */
   public final void next()
   {
      setFirst(the_first + the_bufferSize);
      
   }//--------------------------------------------
   /**
    * point to next buffer buffer slice
    */
   public final void previous()
   {
      setFirst(the_first -the_bufferSize);       
   }//--------------------------------------------   
   /**
      * point to next buffer buffer slice
      */
     public final boolean hasPrevious()
     {
        return the_first > 1;      
     }//--------------------------------------------   
   /**
    * reset the first and last to point to first buffer slice range
    */
   public final void reset()
   {
      
      setFirst(1);
      fetched = 0;
   }//--------------------------------------------
   /**
    @return the buffer size
    */
   public final int bufferSize() 
   {
      return the_bufferSize;
   }//--------------------------------------------
   public final void setFirst(int val)
   {
      if(val <0 )
      { 
         val = 0; 
      }

      the_first = val; 
      
   }//--------------------------------------------
   /**
    * 
    * @return the first index + (the buffer Size - 1
    */
   public final int last()
   {
      return the_first + (the_bufferSize - 1); 
   }//--------------------------------------------
   /**
    * 
    * @return total
    */
   public int getTotal()
   {
      return the_total;
   }//--------------------------------------------
   /**
    * 
    * @param val total
    */
   public void setTotal(int val)
   {
     if(val < 0 ) return;
      
     the_total  = val;
   }//--------------------------------------------
   /**
    * @return
    */
   public int getFetched()
   {
      if(fetched < 0 )
         fetched = 0;
         
      return fetched;
   }//------------------------------------

   /**
    * increment Fetched count
    */
   public void incrementFetched()
   {
      fetched++;
   }//-------------------------------------


   /**
    * @param i
    */
   public void setFetched(int i)
   {
      fetched = i;
      
      if(fetched < 0 )
         fetched = 0;
   }
   private int fetched = 0;
   private final int the_bufferSize;
   private int the_total = 0;
   private int the_first = 1;
   static final long serialVersionUID = RangeCriteria.class.getName()
   .hashCode();

}
