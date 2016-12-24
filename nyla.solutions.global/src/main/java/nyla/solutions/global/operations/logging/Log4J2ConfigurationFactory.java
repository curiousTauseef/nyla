package nyla.solutions.global.operations.logging;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.Appender;
import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.appender.ConsoleAppender;
import org.apache.logging.log4j.core.appender.FileAppender;
import org.apache.logging.log4j.core.config.AbstractConfiguration;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.ConfigurationFactory;
import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.config.LoggerConfig;
import org.apache.logging.log4j.core.layout.PatternLayout;
import org.apache.logging.log4j.util.PropertiesUtil;

import nyla.solutions.core.util.Config;

/**
 * Default Log4J2 configuration factory
 * @author Gregory Green
 *
 */
public class Log4J2ConfigurationFactory extends ConfigurationFactory
{

	@Override
	public Configuration getConfiguration(ConfigurationSource configurationSource)
	{
		
		return new Log4J2Configuration(configurationSource) ;
	}

	 public String[] getSupportedTypes()
	    {
	        return SUFFIXES.clone();
	    }

	    static final String SUFFIXES[] = {
	        ".properties", "*"
	    };

	    
	 public static class Log4J2Configuration extends AbstractConfiguration
	 {

		protected Log4J2Configuration(ConfigurationSource configurationSource)
		{
			super(ConfigurationSource.NULL_SOURCE);
			
			  setName("Default");
			   PatternLayout layout = PatternLayout.newBuilder().withPattern(Log4J2.DEFALT_LAYOUT).withConfiguration(this).build();
		        Appender appender = ConsoleAppender.createDefaultAppenderForLayout(layout);
		        appender.start();
		        addAppender(appender);
		        
		        String fileName = Config.getProperty(Log4J2.class,"file","");
		        String append = Boolean.TRUE.toString();
		        String locking = Boolean.TRUE.toString();
		        
		        String immediateFlush = Boolean.TRUE.toString();
		        String ignore = Boolean.TRUE.toString();
		        String bufferedIo = Boolean.TRUE.toString();
		        String bufferSizeStr = "8192";
		        Filter filter = null;
		        String advertise = null;
		        String advertiseUri = null;
		        
		        Appender fileAppender = null;
		        if(fileName.length() >  0)
		        {
		        	String name = Config.getProperty(Log4J2.class,"name","default");
		        	
		        	 fileAppender = FileAppender.createAppender(fileName, append, locking, name, immediateFlush, ignore,
		        			bufferedIo, bufferSizeStr, layout, filter, advertise, advertiseUri, this);
		        	 
			        fileAppender.start();
			        addAppender(fileAppender);
			  
		        }
		        	   
		        LoggerConfig root = getRootLogger();
		        root.addAppender(appender, null, null);
		        if(fileAppender != null)
		        {
		        	root.addAppender(fileAppender, null, filter);	
		        }
		        
		        String levelName = PropertiesUtil.getProperties().getStringProperty("org.apache.logging.log4j.level");
		        Level level = levelName == null || Level.valueOf(levelName) == null ? Level.DEBUG : Level.valueOf(levelName);
		        root.setLevel(level);
		}
		
		@Override
		public void start()
		{
			//super.start();
		}

		/**
		 * 
		 */
		private static final long serialVersionUID = -6331371502926249138L;
		 
	 }
}
