package nyla.solutions.global.util;

import nyla.solutions.global.util.IteratorException;

/**
 * 
 * @author Gregory Green
 *
 */
public class IteratorException extends Exception
{
   public IteratorException()
   {
   }//-----------------------------------
   public IteratorException(String msg)
   {
      super(msg);
   }//----------------------------------
   
   static final long serialVersionUID = IteratorException.class.getName()
   .hashCode();
}
