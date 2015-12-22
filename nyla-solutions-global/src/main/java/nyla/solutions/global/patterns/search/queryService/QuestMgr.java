package nyla.solutions.global.patterns.search.queryService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.concurrent.Callable;

import nyla.solutions.global.data.DataRow;
import nyla.solutions.global.exception.RequiredException;
import nyla.solutions.global.patterns.expression.BooleanExpression;
import nyla.solutions.global.patterns.iteration.PageCriteria;
import nyla.solutions.global.patterns.iteration.Pagination;
import nyla.solutions.global.patterns.iteration.Paging;
import nyla.solutions.global.patterns.workthread.ExecutorBoss;
import nyla.solutions.global.util.Organizer;

/**
 * Query User Engine Search Tool manager
 * @author Gregory Green
 *
 */
public class QuestMgr implements QuestService
{
	/**
	 * Create the quest and executor boss
	 */
	public QuestMgr()
	{
		this.executorBoss = QuestFactory.createExecutorBoss();
	}// --------------------------------------------------------
	
	/**
	 * 
	 * @see nyla.solutions.global.patterns.search.queryService.QuestService#search(nyla.solutions.global.patterns.search.queryService.QuestCriteria)
	 */
	@Override
	public Paging<DataRow> search(QuestCriteria questCriteria)
	throws RequiredException
	{
		QuestFactory factory = QuestFactory.getInstance();
		
		if (questCriteria == null)
			throw new RequiredException("questCriteria");	
		
		String[] dataSources = questCriteria.getDataSources();
		
		if (dataSources == null || dataSources.length == 0)
			throw new RequiredException("questCriteria.dataSources");
			
		String questName = questCriteria.getQuestName();
		
		if(questName == null || questName.length() == 0)
			throw new RequiredException("questCriteria.questName");
		
		PageCriteria pageCriteria = questCriteria.getPageCriteria();
		
		if(pageCriteria != null)
		{
			//validate page criteria
			if(pageCriteria.getSize() <= 0)
				throw new RequiredException("Page Criteria greater than zero");
			
			//clear previous pagination
			if(pageCriteria.isSavePagination())
				Pagination.getPagination(pageCriteria).clear();
		}
		
		ArrayList<Callable<Collection<DataRow>>>  finders = new ArrayList<Callable<Collection<DataRow>>>(dataSources.length);

		try
		{
			for (String dataSource : dataSources)
			{
				//Create Finders
				finders.add(factory.createFinder(questCriteria, dataSource));
			}
			
			//start finders
			Collection<Paging<DataRow>> dataRowCollection = this.executorBoss.startWorking(finders);
			
			//Comparator
			Comparator<DataRow> comparator = null;
			
			String sorter = questCriteria.getSort();
			if(sorter != null)
				comparator = factory.createComparator(sorter);
			
			//Boolean filter
			BooleanExpression<DataRow> filterExpression = null;
			String filter = questCriteria.getFilter();
			if(filter != null)
				filterExpression = factory.createBooleanExpression(filter);
			
			return Organizer.flattenPaging(dataRowCollection,comparator,filterExpression);
		}
		finally
		{
			if(finders != null)
			{
				finders.trimToSize();
				
				if(!finders.isEmpty())
				{
					for (Callable<Collection<DataRow>> finder : finders)
					{
						if(finder != null)
							try{ ((QuestFinder)finder).dispose(); } catch(Exception e){e.printStackTrace();}
					}
				}
			}
		}
		
	}// --------------------------------------------------------
	/**
	 * 
	 * @see nyla.solutions.global.patterns.search.queryService.QuestService#count(nyla.solutions.global.patterns.iteration.PageCriteria)
	 */
	@Override
	public long count(PageCriteria pageCriteria)
	{
		Pagination pagination = Pagination.getPagination(pageCriteria);
		
		if(pagination ==null)
			return 0;
		
		return pagination.count(pageCriteria);
	}// --------------------------------------------------------
	/**
	 * 
	 * @see nyla.solutions.global.patterns.search.queryService.QuestService#getPaging(nyla.solutions.global.patterns.iteration.PageCriteria)
	 */
	@Override
	public Paging<DataRow> getPaging(PageCriteria pageCriteria)
	{
		Paging<DataRow> dataRows = Pagination.getPagination(pageCriteria).getPaging(pageCriteria);
		
		return dataRows;
	}// --------------------------------------------------------
	
	private final ExecutorBoss executorBoss;
}
