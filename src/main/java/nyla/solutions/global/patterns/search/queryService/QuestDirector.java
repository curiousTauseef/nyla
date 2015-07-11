package nyla.solutions.global.patterns.search.queryService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import nyla.solutions.global.data.DataRow;
import nyla.solutions.global.data.DataRowCreator;
import nyla.solutions.global.patterns.creational.RowObjectCreator;
import nyla.solutions.global.patterns.iteration.PageCriteria;
import nyla.solutions.global.patterns.iteration.Pagination;
import nyla.solutions.global.patterns.iteration.PagingCollection;
import nyla.solutions.global.util.Constants;
import nyla.solutions.global.util.JavaBean;

public class QuestDirector
{
	/**
	 * Process results with support for paging
	 * @param results the results to be converted to data rows 
	 * @param questCriteria the input query search criteria
	 * @param visitor the visitor to convert results to the data rows
	 * @return the collection of data rows
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static final <T> Collection<DataRow> constructDataRows(Iterator<T> iterator, QuestCriteria questCriteria, DataRowCreator visitor)
	{
		if(iterator == null )
			return null;
		
		
		ArrayList<DataRow> dataRows = new ArrayList<DataRow>(Constants.BATCH_SIZE);
		
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
