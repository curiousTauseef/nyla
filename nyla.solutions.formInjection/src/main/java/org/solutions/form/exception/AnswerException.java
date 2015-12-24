package org.solutions.form.exception;

import nyla.solutions.global.exception.SystemException;

/**
 * <pre>
 * AnswerException exception representing an invalid question answer
 * </pre> 
 * @author Gregory Green
 * @version 1.0
 */
public class AnswerException extends SystemException
{

   /**
    * Constructor for AnswerException initializes internal 
    * data settings.
    * 
    */
   public AnswerException()
   {
      super();
   }//--------------------------------------------

   /**
    * Constructor for AnswerException initalizes internal 
    * data settings.
    * @param aMesssage
    */
   public AnswerException(String aMesssage)
   {
      super(aMesssage);
   }//--------------------------------------------
   /**
    * Constructor for AnswerException initalizes internal 
    * data settings.
    * @param aMessage
    * @param aThrowable
    */
   public AnswerException(String aMessage, Throwable aThrowable)
   {
      super(aMessage, aThrowable);
   }//--------------------------------------------
   /**
    * Constructor for AnswerException initalizes internal 
    * data settings.
    * @param aThrowable
    */
   public AnswerException(Throwable aThrowable)
   {
      super(aThrowable);
   }//--------------------------------------------
   /**
    * Constructor for AnswerException initalizes internal 
    * data settings.
    * @param aID
    * @param aMessage
    */
   public AnswerException(int aID, String aMessage)
   {
      super(aID, aMessage);
   }//--------------------------------------------
   /**
    * Constructor for AnswerException initalizes internal 
    * data settings.
    * @param aID
    * @param aMessage
    */
   public AnswerException(String aID, String aMessage)
   {
      super(aID, aMessage);
   }//--------------------------------------------
   static final long serialVersionUID = AnswerException.class.getName()
   .hashCode();
}
