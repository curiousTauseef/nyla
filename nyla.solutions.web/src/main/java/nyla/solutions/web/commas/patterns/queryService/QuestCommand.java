package nyla.solutions.web.commas.patterns.queryService;

import nyla.solutions.commas.annotations.CMD;
import nyla.solutions.commas.annotations.COMMAS;
import nyla.solutions.commas.json.JsonAdvice;
import nyla.solutions.core.data.DataRow;
import nyla.solutions.core.patterns.iteration.PageCriteria;
import nyla.solutions.core.patterns.iteration.Pagination;
import nyla.solutions.core.patterns.iteration.Paging;
import nyla.solutions.core.patterns.search.queryService.QuestCriteria;
import nyla.solutions.core.patterns.search.queryService.QuestFactory;

/**
 * Command to execute a quest search
 * @author Gregory Green
 *
 */
@COMMAS(name="QuestService")
public class QuestCommand
{
	/**
	 * 
	 * @see nyla.solutions.global.patterns.command.Command#execute(java.lang.Object)
	 */
	@CMD(advice=JsonAdvice.JSON_ADVICE_NAME)
	public Paging<DataRow> search(QuestCriteria input)
	{
	
		return QuestFactory.getInstance().createQuestService().search(input);
	}// --------------------------------------------------------
	
	/**
	 * 
	 * @see nyla.solutions.global.patterns.command.Command#execute(java.lang.Object)
	 */
	@CMD(advice=JsonAdvice.JSON_ADVICE_NAME)
	public Paging<DataRow> getPaging(PageCriteria pageCriteria)
	{
	
		Pagination pagination = Pagination.getPagination(pageCriteria);
		
		return pagination.getPaging(pageCriteria);
	}// --------------------------------------------------------
	@CMD(advice=JsonAdvice.JSON_ADVICE_NAME)
	public long count(PageCriteria pageCriteria)
	{
		Pagination pagination = Pagination.getPagination(pageCriteria);
		
		return pagination.count(pageCriteria);
	}// --------------------------------------------------------
	
}
