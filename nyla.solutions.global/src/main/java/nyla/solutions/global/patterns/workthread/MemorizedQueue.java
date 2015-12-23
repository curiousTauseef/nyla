package nyla.solutions.global.patterns.workthread;

import java.util.Stack;

/**
 * <pre>
 * MemorizedQueue provides a set of functions to
 * </pre> 
 * @author Gregory Green
 * @version 1.0
 */
public class MemorizedQueue implements WorkQueue
{
	
	/**
    * 
    * @see nyla.solutions.global.patterns.workthread.WorkQueue#add(java.lang.Runnable)
    */
   public synchronized void add(Runnable tasks)
   {
      
      this.queue.add(tasks);
   }// --------------------------------------------

   /**
    * 
    * @see nyla.solutions.global.patterns.workthread.WorkQueue#nextTasks()
    */
   public synchronized Runnable nextTask()
   {
         return (Runnable)queue.pop();
   }// --------------------------------------------
   
   /**
    * 
    * @see nyla.solutions.global.patterns.workthread.WorkQueue#hasMoreTasks()
    */
   public boolean hasMoreTasks()
   {
    
      return !queue.isEmpty();
   }// --------------------------------------------

   /**
    * @return stack size
    * @see nyla.solutions.global.patterns.workthread.WorkQueue#size()
    */
   public int size()
	{
		return queue.size();
	}
   private Stack<Runnable> queue = new Stack<Runnable>();

}
