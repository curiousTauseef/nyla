package nyla.solutions.global.patterns.command.file;

import java.io.File;



import nyla.solutions.global.exception.FatalException;
import nyla.solutions.global.exception.RequiredException;
import nyla.solutions.global.exception.SummaryException;
import nyla.solutions.global.patterns.command.Environment;
import nyla.solutions.global.patterns.observer.Subject;
import nyla.solutions.global.patterns.observer.SubjectObserver;
import nyla.solutions.global.patterns.observer.Topic;
import nyla.solutions.global.patterns.workthread.Boss;
import nyla.solutions.global.patterns.workthread.MemorizedQueue;
import nyla.solutions.global.util.Config;
import nyla.solutions.global.util.Debugger;


/**
 * Implementation of the file executable based on the worker thread design pattern
 * @author Gregory Green
 *
 */
public class WorkerThreadFileExecutable extends FileExecutable implements SubjectObserver
{
	public WorkerThreadFileExecutable()
	{
		//
		errorSubject = new Topic();
		errorSubject.add(this);
	}//---------------------------------------------
	/**
	 * 
	 */
	public void execute(Environment env, String[] args)
	{
		super.execute(env, args);
		
		Boss boss = new Boss(workQueue);
		
		boss.startWorkers(workerCount);
		
		if(!summaryException.isEmpty())
			throw summaryException;
	}//---------------------------------------------
	/**
	 * Created a file runner worker thread
	 */
	protected void processDocument(File file)
	{		
		if(this.workQueue == null)
			throw new RequiredException("this.workQueue in ThreadedFileExecutable");
		
		workQueue.add(new FileCommandRunner(super.getFileCommand(),file,this.errorSubject));
	}//---------------------------------------------
	/**
	 * Implements the subject observer interface
	 * Add exceptions to the summary exception object
	 */
	public void update(Subject subject, Object data)
	{
		synchronized (summaryException) 
		{
			if(data instanceof Throwable)
			{
				summaryException.addException(new FatalException(Debugger.stackTrace((Throwable)data)));
			}
		}
		 
	}//---------------------------------------------
	/**
	 * @return the id
	 */
	public String getId()
	{
		return id;
	}//---------------------------------------------
	/**
	 * @param id the id to set
	 */
	public void setId(String id)
	{
		this.id = id;
	}


	private static SummaryException summaryException = new SummaryException();
	private String id = this.getClass().getName();
	private Subject errorSubject = null;
	private MemorizedQueue workQueue = new MemorizedQueue();
	private int workerCount = Config.getPropertyInteger(this.getClass(),"workerCount").intValue();
}
