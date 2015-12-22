package nyla.solutions.global.patterns.workthread;

public interface WorkState
{
   /**
    * 
    * @return the state name
    */
   public String getName();
 
   
   /**
    * Adverse a worker
    * @param aWorker the worker to advise
    */
   public void advise(SupervisedWorker aWorker);
}
