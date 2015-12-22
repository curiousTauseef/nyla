package nyla.solutions.global.patterns.creational.builder.mapped;
import java.util.Map;

import nyla.solutions.global.exception.NoDataFoundException;
import nyla.solutions.global.exception.SystemException;
import nyla.solutions.global.patterns.servicefactory.ServiceFactory;
import nyla.solutions.global.util.Config;
import nyla.solutions.global.util.Debugger;

public class MappedKeyDirector<K,V>
{
	/**
	 * 
	 * @param url the url to look for map
	 * @return the map of text object
	 * @throws NoDataFoundException
	 */
	protected Map<K,V> constructMapToText(String path)
	throws NoDataFoundException
	{		
		MapFactoryById<K,V> factory = ServiceFactory.getInstance().create(this.mapFactoryByIdServiceName);
		factory.setId(path);
		
		return factory.createMap();
	}// --------------------------------------------
	/**
	 * Director method to construct a document
	 * @param path the 
	 * @param engineer
	 */
	public void constructDocument(String id, MappedKeyEngineer<K,V> engineer)
	{
		try
		{
			//construct map
			Map<K,V> textableMap = this.constructMapToText(id);
					
			engineer.construct(id,textableMap);
		}
		catch (NoDataFoundException e)
		{
			throw new SystemException("No textable found for id="+id+" ERROR:"+Debugger.stackTrace(e));
		}		
	}// --------------------------------------------
	
	/**
	 * @return the mapFactoryByIdServiceName
	 */
	public String getMapFactoryByIdServiceName()
	{
		return mapFactoryByIdServiceName;
	}
	/**
	 * @param mapFactoryByIdServiceName the mapFactoryByIdServiceName to set
	 */
	public void setMapFactoryByIdServiceName(String mapFactoryByIdServiceName)
	{
		this.mapFactoryByIdServiceName = mapFactoryByIdServiceName;
	}

	private String mapFactoryByIdServiceName = Config.getProperty(this.getClass(),
			"mapFactoryByIdServiceName",MapFactoryById.class.getName());
	

}
