package nyla.solutions.core.io;

import static org.junit.Assert.*;

import java.io.File;
import java.nio.file.Paths;
import java.util.Observable;
import java.util.Observer;

import org.junit.Test;

import nyla.solutions.core.util.Text;

public class FileMonitorTest extends FileMonitor
{

	static boolean called = false;
	@Test
	public void testMonitor()
	throws Exception
	{
		//Create the file monitoring
		FileMonitor fileMonitor = new FileMonitor();
		
		//Create an observer to be called for a file appears
		Observer printFileObserver = new Observer()
		{
			@Override
			public void update(Observable o, Object arg)
			{
				//Each argment is an instance of FileEvent
				FileEvent fileEvent = (FileEvent)arg;
				
				//Note that file with assert the file is nto changing (ex: FTP files)
				File file = fileEvent.getFile();
				
				//PRint file
				System.out.println("file:"+file);
				
				//Set flag for test case
				called = true;
			}
		};
		
		//Register the observer with the file monitor
		fileMonitor.addObserver(printFileObserver);
		
		//Set the file, pattern and whether current files should trigger event
		fileMonitor.monitor("runtime/", "*.txt", false);
		
		//Write file to trigger observer
		String filePath = "runtime/"+Text.generateId()+".txt"; 
		IO.writeFile(filePath, "Hello");
		Thread.sleep(200);
		
		//Check if observer was trigger
		assertTrue(called);
		
		//Cleanup file
		IO.delete(Paths.get(filePath).toFile());
	}

}
