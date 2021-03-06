package nyla.solutions.core.patterns.search.queryService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import nyla.solutions.core.data.DataRow;
import nyla.solutions.core.data.DataRowCreator;
import nyla.solutions.core.patterns.creational.RowObjectCreator;
import nyla.solutions.core.patterns.iteration.PageCriteria;
import nyla.solutions.core.patterns.iteration.Pagination;
import nyla.solutions.core.patterns.iteration.PagingCollection;
import nyla.solutions.core.util.Config;
import nyla.solutions.core.util.JavaBean;

public class QuestDirector
{
	   /**
	    * Default batch size 10
	    */
	   public static final int BATCH_SIZE = Config.getPropertyInteger(QuestDirector.class,"BATCH_SIZE",10);

	   
	/**
	 * Process results with support for paging
	 * @param <T> the type
	 * @param iterator the results to be converted to data rows 
	 * @param questCriteria the input query search criteria
	 * @param visitor the visitor to convert results to the data rows
	 * @return the collection of data rows
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static final <T> Collection<DataRow> constructDataRows(Iterator<T> iterator, QuestCriteria questCriteria, DataRowCreator visitor)
	{
		if(iterator == null )
			return null;
		
		
		ArrayList<DataRow> dataRows = new ArrayList<DataRow>(BATCH_SIZE);
		
		boolean usePaging = false;
		boolean savePagination = false;
		int pageSize = 0; 
		
		
		PageCriteria pageCriteria = questCriteria.getPageCriteria();
		Pagination pagination = null;
		
		if(pageCriteria != null)
		{
			pageSize = pageCriteria.getSize();
			
			if(pageSize > 0)
			{
				usePaging = true;
				savePagination = pageCriteria.isSavePagination();
				
				if(savePagination)
					pagination = Pagination.getPagination(pageCriteria);
			}
			
		}
		
		if(usePaging)
		{
			
			//Process results with paging being used
			//int index = 0;
			T item;
			DataRow dataRow;
			while (iterator.hasNext())
			{
				item = iterator.next();
				
				JavaBean.acceptVisitor(item, visitor);
				
				dataRow = visitor.getDataRow();
				dataRows.add(dataRow);
				
				if(savePagination)
					pagination.store(dataRow, pageCriteria);
				
				visitor.clear();
				//if datarows greater than page size
				if(dataRows.size() >= pageSize)
				{
					//then check if has more results and continue to gather results in break ground
					if(savePagination && iterator.hasNext())
					{
						
						pagination.constructPaging(iterator, pageCriteria, (RowObjectCreator)visitor);
					}
					
					break; //break look since page is filled
				}
				
			}
			
		}
		else
		{
			//no paging used
			Object item;
			while (iterator.hasNext())
			{	
				item = iterator.next();
				JavaBean.acceptVisitor(item, visitor);
				
				dataRows.add(visitor.getDataRow());
				
				visitor.clear();
			}
		}

		dataRows.trimToSize();
		
		//data rows to paging collection
		PagingCollection<DataRow> pagingCollection = new PagingCollection<DataRow>(dataRows,questCriteria.getPageCriteria());
		
		return pagingCollection;
	}

}