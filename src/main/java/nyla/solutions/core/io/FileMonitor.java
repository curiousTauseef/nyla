package nyla.solutions.core.io;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import nyla.solutions.core.operations.ClassPath;
import nyla.solutions.core.patterns.observer.SubjectObserver;
import nyla.solutions.core.patterns.observer.Topic;
import nyla.solutions.core.util.Config;

/**
 * Observable for the file watching
 */
public class FileMonitor extends Topic<FileEvent>
{

   public FileMonitor()
   {
      this(1000);
   }//------------------------------------------------------------------
   /**
    * Create a file monitor instance with specified polling interval.
    * 
    * @param pollingInterval
    *           Polling interval in milliseconds.
    */
   public FileMonitor(long pollingInterval)
   {

      this.pollingInterval = pollingInterval;

      timer = new Timer(true);

   }//------------------------------------------------------------------
   /**
    * Stop the file monitor polling.
    */
   public synchronized void stop()
   {
      timer.cancel();
   }//------------------------------------------------------------------
   /**
    * Used to wait for transferred file's content length to stop changes for
    * 5 seconds
    * 
    * @param aFile the file
    */
   public static void waitFor(File aFile)
   
   {
      if(aFile == null)
         return;
      
      String path =aFile.getAbsolutePath();
   
      long previousSize = IO.getFileSize(path);
      long currentSize = previousSize;
      
      long sleepTime = Config.getPropertyLong("file.monitor.file.wait.time",100).longValue();
      
      while(true)
      {
         try
         {
           Thread.sleep(sleepTime);
         }
         catch(InterruptedException e){}
          
          currentSize = IO.getFileSize(path);
       
          if(currentSize == previousSize )
             return;
          
          previousSize = currentSize;
      }
   }//------------------------------------------------------------------
   /**
    * Add file to observer for. File may be any java.io.File (including a
    * directory) and may well be a non-existing file in the case where the
    * creating of the file is to be trepped.
    * <p>
    * More than one file can be listened for. When the specified file is
    * created, modified or deleted, listeners are notified.
    * 
    * @param directory the directory to listen for.
    * @param fileNameFilter the file name
    * @param processCurrentFiles whether to process current names
    */
   public synchronized void monitor(String directory, String fileNameFilter, boolean processCurrentFiles)
   {

      timer.schedule(
            new FileMonitorNotifier(this, directory, fileNameFilter, processCurrentFiles)
	    , 0,
            pollingInterval);
   }//-------------------------------------------

   /**
    * Notify observers that the file has changed
    * 
    * @param file
    *           the file that changes
    */
   protected synchronized void notifyChange(File file)
   {
      
      
      System.out.println("Notify change file="+file.getAbsolutePath());
      
   
      this.notify(FileEvent.createChangedEvent(file));
   }//---------------------------------------------------
   /**
    * 
    * @param file file that was added
    */
   protected synchronized void notifyAdd(File file)
   {   
      System.out.println("Notify added file="+file.getAbsolutePath());

      this.notify(FileEvent.createAddedEvent(file));
   }//---------------------------------------------------

/**
 * This is the timer thread which is executed every n milliseconds according * to the setting of the file monitor. It investigates the file in question * and notify listeners if changed.
 */

   private static class FileMonitorNotifier extends TimerTask
   {
      /**
       * Constructor
       * 
       * @param aFileMonitor
       *           the file monitor
       * @param aDirectory
       *           the directory to monitor
       * @param aFileFilter
       *           the file filter example *.exe"
       * @param processCurrentFiles flag to process previous files
       */
      FileMonitorNotifier(FileMonitor aFileMonitor, String aDirectory,
            String aFileFilter, boolean processCurrentFiles)
      {
         if (aDirectory == null || aDirectory.length() == 0)
            throw new IllegalArgumentException(
                  "Directory to minotor has not provided.");

         this.directory = new File(aDirectory);

         if (!this.directory.isDirectory())
            throw new IllegalArgumentException("Invalid directory \""
                  + aDirectory + "\" provided!");

         if (aFileMonitor == null)
            throw new IllegalArgumentException("FileMonitor not provided");

         this.fileMonitor = aFileMonitor;
         
         //----------------------------------------------
         this.filter = new WildCardFilter(aFileFilter);
         
         if(!processCurrentFiles) 
            init();

      }//--------------------------------------
      
      private void init()
      {
         File[] files = this.directory.listFiles(this.filter);
         
         if(files == null)
        	 return;
         
         File file = null;
         for(int i = 0; i<files.length;i++)
         {
            file = files[i];
            this.fileLastModifyTimeMap.put(file,  Long.valueOf(file.lastModified()));
         }
      }//-----------------------------------------

      /**
       * Process file change notifications
       */
      public void run()
      {

        try
        {
         
            // Loop over the registered files and see which have changed.
            // Use a copy of the list in case listener wants to alter the
            // list within its fileChanged method.
        	
        	if(this.directory == null)
        		return;

            File[] filesInDir = this.directory.listFiles(this.filter);
            
            if(filesInDir == null)
            	return;
            
            File dirfile = null;
            Long lastModifiedTime = null;
            long newModifiedTime = 0;

            //if in map and not in directory, then it was removed
            ArrayList<Object> processedFiles = new ArrayList<Object>();
            for (int i = 0; i< filesInDir.length; i++)
            {
               dirfile = filesInDir[i];
               
               if(processedFiles.contains(dirfile.getAbsolutePath()))
                  continue;
               
               //System.out.println("File="+dirfile);
               //System.out.println("File name="+dirfile.getName());

               lastModifiedTime = (Long) fileLastModifyTimeMap.get(dirfile);
               
               //System.out.println("mod time="+lastModifiedTime);

               if (lastModifiedTime == null)
               {
                  
                  //file added
                  fileMonitor.notifyAdd(dirfile);
               }
               else
               {
                  newModifiedTime =  dirfile.exists()? dirfile.lastModified(): -1;

                  // Chek if file has changed
                  if(newModifiedTime != lastModifiedTime.longValue())
                  {
                     // Register new modified time
                     fileMonitor.notifyChange(dirfile);
                  }
               }

               fileLastModifyTimeMap.put(dirfile,  Long.valueOf(dirfile.lastModified()));
               processedFiles.add(dirfile.getAbsolutePath());
            }//end for loop
            
            List<File> filesInDirList =Arrays.asList(filesInDir);
            File fileInMap = null;
            
            Map.Entry<File,Object> mapEntry = null;
        
            for(Iterator<Map.Entry<File,Object>> i = this.fileLastModifyTimeMap.entrySet().iterator();
            		i.hasNext();)
            {

               try
               {
            	   mapEntry = i.next(); 
            	   fileInMap = mapEntry.getKey();
            	   
                 if(!filesInDirList.contains(fileInMap))
                 {
                    System.out.println("File was removed "+fileInMap.getName());
                    //fileLastModifyTimeMap.remove(fileInMap);
                    i.remove();
                    
                    System.out.println("DONE");
                    
                    
                 }
               }
               catch(ConcurrentModificationException e)
               {}
               
            }
	 }
	 catch(Exception e)
         {
            e.printStackTrace();
         }
      }//--------------------------------------------------
      private Hashtable<File,Object> fileLastModifyTimeMap = new Hashtable<File,Object>(); // File -> Long

      private FilenameFilter filter = null;
      private File directory = null;

      /**
       * 
       * @uml.property name="fileMonitor"
       * @uml.associationEnd multiplicity="(0 1)"
       */
      private FileMonitor fileMonitor = null;

   }//---------------------------------------------------------------

   /**
    * Test this class.
    * 
    * @param args Not used.
    */
   public static void main(String args[])
   {
      try
      {
      // Create the monitor
      
      //FileMonitor fm = FileMonitor.createFileMonitor(args);
         FileMonitor.createFileMonitor(args);
      }
      catch(Exception e)
      {
         System.err.println("Unable monitor specified input error="+e);
         System.exit(-1);
      }
      // Avoid program exit
      while (!false)
      {
         try
         {
            Thread.sleep(1000);
         }
         catch(Exception e){e.printStackTrace();}
      }

   }//-------------------------------------------
   private static FileMonitor createFileMonitor(String [] args)
   throws Exception
   {
        String usage= " Usage: [-pollingInterval <timems>]? [-observer <class>]* [-monitor <dir>:<filter>:<ignoreCurrentContentBoolean>]*";

      if(args == null || args.length < 2 )
         throw new IllegalArgumentException(usage);
      
      FileMonitor fileMonitor =new FileMonitor();
      
      String [] arrayString = null;
      SubjectObserver<FileEvent> observer = null;
      for(int i=0; i < args.length;i++)
      {
         if("-monitor".equals(args[i]))
         {
            if(i+1 >= args.length)
            {
               throw new IllegalArgumentException("directory:filter specified -monitor argument "+usage);
            }
            
            arrayString = args[i+1].split(":");
            if(arrayString.length != 3)
               throw new IllegalArgumentException(args[i+1]+usage );
            
            fileMonitor.monitor(arrayString[0],arrayString[1],Boolean.valueOf(arrayString[2]).booleanValue());

            i = i+1;
         }
         else if("-pollingInterval".equals(args[i]))
         {
            if(i+1 >= args.length)
            {
               throw new IllegalArgumentException("no time specified -pollingInterval argument "+usage);
            }
            
            fileMonitor.setPollingInterval(Long.parseLong(args[i+1]));
            i = i+1;
         }
         else if("-observer".equals(args[i]))
         {
            if(i+1 >= args.length)
            {
               throw new IllegalArgumentException("no observer specific in -observer argument "+usage);
            }
               
            //fileMonitor.addObserver((Observer)Class.forName(args[i+1]).newInstance());
            observer = ClassPath.newInstance(args[i+1]);
            fileMonitor.add(observer);
            i = i+1;
         }
       }//end for
      return fileMonitor;
   }//---------------------------------------------

   /**
    * @return Returns the pollingInterval.
    *
    */
   public long getPollingInterval() {
      return pollingInterval;
   }

   /**
    * @param pollingInterval The pollingInterval to set.
    */
   public void setPollingInterval(long pollingInterval) {
      this.pollingInterval = pollingInterval;
   }

   private Timer timer;
   private long pollingInterval = 1000;
}

