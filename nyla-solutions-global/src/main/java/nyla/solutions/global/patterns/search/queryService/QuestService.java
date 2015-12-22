package nyla.solutions.global.patterns.search.queryService;

import nyla.solutions.global.data.DataRow;
import nyla.solutions.global.patterns.iteration.PageCriteria;
import nyla.solutions.global.patterns.iteration.Paging;

/**
 * Query User Engine Search Tool service interface
 * @author Gregory Green
 *
 */
public interface QuestService
{
	/**
	 * Execute a quest service like search
	 * @param questCriteria the search criteria
	 * @return Collection of data rows
	 */
	Paging<DataRow> search(QuestCriteria questCriteria);
	
	
	/**
	 * 
	 * @param pageCriteria the page criteria
	 * @return the total count of the records in a saved page criteria
	 */
	long count(PageCriteria pageCriteria);
	
	
	/**
	 * 
	 * @param pageCriteria the page criteria 
	 * @return data rows for a given page criteria
	 */
	Paging<DataRow> getPaging(PageCriteria pageCriteria);
	
	
	
}
