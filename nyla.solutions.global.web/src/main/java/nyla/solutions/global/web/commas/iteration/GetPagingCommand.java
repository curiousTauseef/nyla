package nyla.solutions.global.web.commas.iteration;

import nyla.solutions.global.exception.NoDataFoundException;
import nyla.solutions.global.patterns.command.commas.annotations.CMD;
import nyla.solutions.global.patterns.command.commas.annotations.COMMAS;
import nyla.solutions.global.patterns.command.commas.json.JsonAdvice;
import nyla.solutions.global.patterns.iteration.PageCriteria;
import nyla.solutions.global.patterns.iteration.Pagination;
import nyla.solutions.global.patterns.iteration.Paging;


/**
 * Get paging the information
 * 
 * Sample JavaScript Usage
 * 
 *    var executeFunctionUrl = this.contextRoot+"controller/commas/nyla.solutions.global.web.commas.iteration.GetPagingCommand.getPaging";
     
     web.openAJAX(mmAJAX,this.renderHandlerNextResults, "POST", executeFunctionUrl, true, renderResultsData);	
 * 
 * 
 * @author Gregory Green
 *
 */
@COMMAS(name="Paging")
public class GetPagingCommand<T>
{
	
	/**
	 * Sample JavaScript Usage
 	 
      var executeFunctionUrl = this.contextRoot+"controller/commas/nyla.solutions.global.web.commas.iteration.GetPagingCommand.getPaging";
     
     web.openAJAX(mmAJAX,this.renderHandlerNextResults, "POST", executeFunctionUrl, true, renderResultsData);	
  
	 * @param pageCriteria
	 * @return the paging results
	 * @throws NoDataFoundException
	 */
	@CMD(advice=JsonAdvice.JSON_ADVICE_NAME)
	public Paging<T> getPaging(PageCriteria pageCriteria)
	throws NoDataFoundException
	{
		Pagination pagination = Pagination.getPagination(pageCriteria);
		
		return pagination.getPaging(pageCriteria);
	}// --------------------------------------------------------
	@CMD(advice=JsonAdvice.JSON_ADVICE_NAME)
	public long count(PageCriteria pageCriteria)
	throws NoDataFoundException
	{
			Pagination pagination = Pagination.getPagination(pageCriteria);
			
			if(pagination == null)
				return -1;
			
			
			//wait for completion
			pagination.waitForCompletion();
			
			return pagination.size();

	}// --------------------------------------------------------

}
