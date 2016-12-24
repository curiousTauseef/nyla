package nyla.solutions.core.patterns.search.queryService;

import java.util.Collection;
import java.util.concurrent.Callable;

import nyla.solutions.core.data.DataRow;
import nyla.solutions.core.patterns.Disposable;
import nyla.solutions.core.patterns.iteration.Paging;

/**
 * Finder interface for retrieving result set
 * @author Gregory Green
 *
 */
public interface QuestFinder extends Callable<Collection<DataRow>>, Disposable
{
	
	/**
	 * 
	 * @param structureCriteria the criteria to use
	 */
	void assignCriteria(QuestCriteria questCriteria, String source);
	
	/**
	 * 
	 * @return the execution results
	 */
	Paging<DataRow> getResults();
	
}
