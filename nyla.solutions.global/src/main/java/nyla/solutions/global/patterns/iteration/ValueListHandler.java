package nyla.solutions.global.patterns.iteration;

import java.util.*;

import nyla.solutions.global.util.Debugger;
/**
 * 
 * @author green_gregory
 * @version 1.0
 *
 * <b>ValueListHandler</b> value list design pattern  
 *
 */

public class ValueListHandler<T> extends ArrayList<T>
implements ValueListIterator<T>
{
   /**
    * 
    * @param aCriteria the range criteria
    */
  public ValueListHandler(RangeCriteria aCriteria) 
  {
     rangeCriteria = aCriteria;

  }//---------------------------------------------------
  /**
   * @return previous N values
   * @throws IteratorExceptionwhen an internal error 
   */    
  public void previous() 
  throws IteratorException 
  {
     rangeCriteria.previous();
     
     rangeCriteria.setFetched( rangeCriteria.getFetched()- 
                    (rangeCriteria.bufferSize() + this.size()));

  }//---------------------------------------------------
  /**
   * Get Next N returns
   * @param count the count
   */
  public void next() 
  throws IteratorException 
  {
     rangeCriteria.next();
 
  }//---------------------------------------------------
  /**
   * reset to first iterator
   */  
  public void first() 
  {
     rangeCriteria.first();
  }//------------------------------------------------
  /**
   * 
   * @return getDone
   */
  public boolean isDone()
  {
     return getDone();
     
  }//--------------------------------------------------
  /**
   * @return true f fetched > = size;
   */
  public boolean getDone()
  {
     Debugger.println(this,"getFetched()="+getFetched());
     Debugger.println(this,"size="+size());
     Debugger.println(this,"total="+rangeCriteria.getTotal());
     return getFetched() >= rangeCriteria.getTotal()|| 
            rangeCriteria.last() >= rangeCriteria.getTotal();
  }//------------------------------------------------
  /**
   * 
   * @return fetched count
   */
  public int getFetched()
  {
     return this.getRangeCriteria().getFetched();
     
  }//-----------------------------------------------
   /**
    * @return size
    */
   public int getSize()
   {
      return rangeCriteria.getTotal();
   }//------------------------------------------
   /**
    * @param i the size
    */
   public void setSize(int i)
   {
      rangeCriteria.setTotal(i);
   }//----------------------------------
   public final boolean hasPrevious()
   {
      return rangeCriteria.hasPrevious();
   }//-------------------------------------

   /**
    * @return
    */
   public RangeCriteria getRangeCriteria()
   {
      return rangeCriteria;
   }
   /**
    * Oerrivde add method to increment fetch count
    */   
   public boolean add(T aObject)
   {
      getRangeCriteria().incrementFetched();
      return super.add(aObject);
   }//------------------------------

   /**
    * @param criteria
    */
   public void setRangeCriteria(RangeCriteria criteria)
   {
      rangeCriteria = criteria;
   }//---------------------------------------
   /*
    * @param aTotal the total
    */
   public void setTotal(int aTotal)
   {
      this.getRangeCriteria().setTotal(aTotal);
      
   }//------------------------------------------

   static final long serialVersionUID = 1;
  private RangeCriteria rangeCriteria = null;


} 


