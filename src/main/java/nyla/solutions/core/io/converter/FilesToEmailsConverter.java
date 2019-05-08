package nyla.solutions.core.io.converter;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import nyla.solutions.core.exception.SystemException;
import nyla.solutions.core.patterns.conversion.Converter;
import nyla.solutions.core.patterns.conversion.TextToEmailsConverter;

/**
 * Support convert directories and files
 * @author Gregory Green
 *
 */
public class FilesToEmailsConverter implements Converter<File, Set<String>>
{
	/**
	 * @param file the file or directory
	 * @return the set of the emails in files
	 */
	
	public Set<String> convert(File file)
	{
		
		if(file.isDirectory())
		{
			//loop thru each directory
			Set<String> emails = new HashSet<String>();
					
			try (DirectoryStream<Path> stream = Files.newDirectoryStream(file.toPath())) 
			{
			    for (Path path: stream) 
			    {
			    	Set<String> results = convert(path.toFile());
			    	
			    	if(!results.isEmpty())
			    	{
			    		emails.addAll(results);
			    	}
			    }
			    
			    return emails;
			}
			catch(IOException e)
			{
				throw new SystemException("Unable to convert file:"+file+" error:"+e.getMessage(),e);
			}
		}
		else
		{
			Path path = file.toPath();
			PathMatcher pathMatcher = FileSystems.getDefault().getPathMatcher("glob:**/*.{json,txt,csv,xml,yml}");
			if(!pathMatcher.matches(path))
				return Collections.emptySet(); 
			try
			{
				List<String> list = Files.readAllLines(path);
				
				if(list == null)
					return Collections.emptySet();
				
				Set<String> emails =  list.stream().map(line -> 
				{ 
					Set<String> set = textToEmailsConverter.convert(line);
					if(set == null)
						set = Collections.emptySet();
					return set;
				}).flatMap(Collection::stream).collect(Collectors.toSet());
				
				return emails;
			}
			catch (IOException e)
			{
				throw new SystemException("Unable to convert file:"+file+" error:"+e.getMessage(),e);
			}
		}
		
	}//------------------------------------------------
	private final TextToEmailsConverter textToEmailsConverter = new TextToEmailsConverter();
}
