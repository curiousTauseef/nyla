package nyla.solutions.global.patterns.iteration;

import java.io.Serializable;
import java.util.*;



/**
 * 
 * Generic Value List Iterator Iterator
 */ 
public interface ValueListIterator<T> extends Serializable, Collection<T>
{

  /**
   * 
   * @return size
   */    
  public int getSize();
  
  /**
   * 
   * @return
   */
  public boolean hasPrevious();
  
  /**
   * 
   * @param count count
   * @return the previous N
   * @throws IteratorException
   */  
  public void previous() 
    throws IteratorException;
  
  /**
   * 
   * @param count count
   * @return the next N
   * @throws IteratorException
   */
  public void next() 
    throws IteratorException;
  
  /**
   * Set the beginning
   *
   */
  public void first();

  /**
   Determine if at the end of list
  */
  public boolean getDone();

}

