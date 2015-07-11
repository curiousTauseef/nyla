package nyla.solutions.global.patterns.command.file;

import java.io.File;
import java.util.Map;
import java.util.TreeMap;

import nyla.solutions.global.exception.RequiredException;
import nyla.solutions.global.operations.Log;
import nyla.solutions.global.util.Config;
import nyla.solutions.global.util.Debugger;
import nyla.solutions.global.util.Text;


/**
 * Uses complex regular expressions to the determine nested FileCommand to execute
 * @author Gregory Green
 *
 */
public class ReLookupCommand implements FileCommand
{
   /**
    *
    * Constructor for ReLookupCommand initializes internal
    */
   public ReLookupCommand()
   {
   }// --------------------------------------------
   
   /**
    * Executes a nested file command if the file name matches a regular expression in the lookup table.
    * @param file the file to process
    * 
    */
   public synchronized void execute(File file)
   {

         log.debug("Entering the transform method");


          String re = null;  

             //loop thru values
           String fileName = file.getName();
           FileCommand fileCommand = null;

           if(lookupTable == null)
			throw new RequiredException("lookupTable in ReLookupCommand");
		
           for (Map.Entry<String,FileCommand> entry : lookupTable.entrySet())
           {
                     //process next source value
                    re = entry.getKey();

                    //the key contains a RE, test if it matches the current source value
                    if(Text.matches(fileName,re))//sourceValue.matches(re)
                    {
                       //  if matches use the value
                       fileCommand = entry.getValue();
                       
                       fileCommand.execute(file);
                       
                       //check if needs multiple mapping
                       if(!manyMatches)
                          break;
                       
                    }//end for re iteration
                    
                }//end lookup re     
   }// --------------------------------------------

   /**
    *
    *
    * @see java.lang.Object#toString()
    */
   public synchronized String toString()
   {
       StringBuffer stringVal =  new StringBuffer();
       stringVal.append(" lookupTable=").append(this.lookupTable);

       return stringVal.toString();
   }// --------------------------------------------

   /**
    * @return the lookupTable
    */
   public synchronized Map<String,FileCommand>  getLookupTable()
   {
      return lookupTable;
   }

   /**
    * @param lookupTable the lookupTable to set
    */
   public synchronized void setLookupTable(Map<String,FileCommand>  lookupTable)
   {
      this.lookupTable = new TreeMap<String,FileCommand> (lookupTable);
   }



   //TODO: private final static String DEFAULT_LOOKUP_KEY  = "";   
   private boolean manyMatches = Config.getPropertyBoolean(getClass(),"manyMatches",true).booleanValue();   
   //private Attribute targetAttribute = null;
   //private Attribute sourceAttribute = null;
   private TreeMap<String,FileCommand> lookupTable;
   private Log log = Debugger.getLog(this.getClass());
}
