package nyla.solutions.global.patterns.workthread;

import nyla.solutions.global.exception.SystemException;

/**
 * <pre>
 * WorkerThread provides a set of functions to
 * </pre> 
 * @author Gregory Green
 * @version 1.0
 */
public class WorkerThread extends Thread implements SupervisedWorker
{
   public WorkerThread()
   {}

   /**
    * 
    * Constructor for WorkerThread initializes internal 
    * data settings.
    * @param supervisor
    */
   public WorkerThread(Supervisor supervisor)
   {
      this.setSupervisor(supervisor);
   }// --------------------------------------------

   
   /**
    * 
    * @see nyla.solutions.global.patterns.workthread.SupervisedWorker#getSupervisor()
    */
   public Supervisor getSupervisor()
   {
      return supervisor;
   }// --------------------------------------------
   /**
    * 
    * @see java.lang.Thread#run()
    */
   public void run()
   {
      if(this.supervisor == null)
      {
         throw new SystemException("Supervisor not set");
      }
     
      WorkQueue workQueue = supervisor.getWorkQueue();
            
      while(workQueue.hasMoreTasks())
      {
         workQueue.nextTask().run();
      }
   }// --------------------------------------------


   /**
    * 
    * @see nyla.solutions.global.patterns.workthread.SupervisedWorker#getThread()
    */
   public Thread getThread()
   {
      return this;
   }// --------------------------------------------


   /**
    * 
    * @see nyla.solutions.global.patterns.workthread.SupervisedWorker#setWorkState(nyla.solutions.global.patterns.workthread.WorkState)
    */
   public void setWorkState(WorkState workState)
   {
      this.workState = workState;
   }// --------------------------------------------
   
   /**
    * @param supervisor the supervisor to set
    */
   public void setSupervisor(Supervisor supervisor)
   {

      this.supervisor = supervisor;
   }// --------------------------------------------

   /**
    * @return Returns the workState.
    */
   public final WorkState getWorkState()
   {
      return workState;
   }// --------------------------------------------

   private WorkState workState = null;
   private Supervisor supervisor = null;
  
}
