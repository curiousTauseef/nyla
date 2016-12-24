package nyla.solutions.global.patterns.search.queryService.finders;

import java.util.Collection;

import nyla.solutions.core.data.DataRow;
import nyla.solutions.core.patterns.search.queryService.QuestFinder;


/**
 * The quest finder reference implement using an ReLookup
 * @author Gregory Green
 *
 */
public abstract class AbstractQuestFinder implements QuestFinder
{	
	/**
	 * 
	 * @param reLookup the reLookup
	 */
	public AbstractQuestFinder()
	{
	}// --------------------------------------------------------
	/**
	 * 
	 * @see java.util.concurrent.Callable#call()
	 */
	@Override
	public Collection<DataRow> call() throws Exception
	{
		return this.getResults();
	}// --------------------------------------------------------


	@Override
	public void dispose()
	{
		
	}// -------------------------------------------------------- 
	


}
