package nyla.solutions.global.patterns.memento;


import nyla.solutions.global.data.MethodCallFact;
import nyla.solutions.global.exception.RequiredException;
import nyla.solutions.global.exception.SummaryException;
import nyla.solutions.global.exception.SystemException;
import nyla.solutions.global.patterns.creational.proxy.ObjectProxy;
import nyla.solutions.global.util.Debugger;

/**
 * Handles replay method calls from a Memento
 * 
 * MethodCallObjectPreparer is used to modify an object prior to execute
 * @see Memento design pattern
 * @author Gregory Green
 *
 */
public class MethodCallSavePointPlayer
{
   /**
    * 
    * @param target the target object
    */
   public MethodCallSavePointPlayer(Object target)
   {
	if (target == null)
	   throw new RequiredException("target");
	
	this.setTarget(target);
   }// ----------------------------------------------
   /**
    * Redo the method calls of a target object
    * @param memento the memento to restore MethodCallFact
    * @param savePoints the list of the MethodCallFact save points
    * @throws SummaryException
    */
   public synchronized void playMethodCalls(Memento memento, String [] savePoints)
   {
	String savePoint = null;
	
	MethodCallFact methodCallFact = null;
	SummaryException exceptions = new SummaryException();
	
	//loop thru savepoints
	for (int i = 0; i < savePoints.length; i++)
	{
	   savePoint = savePoints[i];
	   
	   if(savePoint == null || savePoint.length() == 0 || savePoint.trim().length() == 0)
		continue;
	   
	   Debugger.println(this,"processing savepoint="+savePoint);
	   
	   //get method call fact
	   methodCallFact = (MethodCallFact)memento.restore(savePoint, MethodCallFact.class);
	   try
	   {
		ObjectProxy.executeMethod(prepareObject(methodCallFact,savePoint), methodCallFact);
	   }
	   catch(Exception e)
	   {
		exceptions.addException(new SystemException("savePoint="+savePoint+" methodCallFact="+methodCallFact+" exception="+Debugger.stackTrace(e)));
		throw new SystemException(e); // TODO: 
	   }
	}
	
	if(!exceptions.isEmpty())
	   throw exceptions;
   }// ----------------------------------------------
   /**
    * 
    * @param fact the method call fact
    * @return the prepared object by the the methodCallObjectPreparer
    */
   private Object prepareObject(MethodCallFact fact, String savePoint)
   {
	if(this.methodCallObjectPreparer != null)
	   return methodCallObjectPreparer.prepare(this.target, fact,savePoint);
	
	//else return target
	return this.target;
   }// ----------------------------------------------
   
   /**
    * @return the target
    */
   public Object getTarget()
   {
      return target;
   }// ----------------------------------------------

   /**
    * @param target the target to set
    */
   public void setTarget(Object target)
   {
      this.target = target;
   }// ----------------------------------------------

   
   /**
    * @return the methodCallObjectPreparer
    */
   public MethodCallObjectPreparer getMethodCallObjectPreparer()
   {
      return methodCallObjectPreparer;
   }// ----------------------------------------------
   /**
    * Set to modify an objet prior to execution
    * @param methodCallObjectPreparer the methodCallObjectPreparer to set
    */
   public void setMethodCallObjectPreparer(
   	MethodCallObjectPreparer methodCallObjectPreparer)
   {
      this.methodCallObjectPreparer = methodCallObjectPreparer;
   }// ----------------------------------------------


   private MethodCallObjectPreparer methodCallObjectPreparer = null;
   private Object target = null; 
}
