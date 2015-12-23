package nyla.solutions.global.web.commas.patterns.queryService;

import nyla.solutions.global.data.DataRow;
import nyla.solutions.global.patterns.command.commas.annotations.CMD;
import nyla.solutions.global.patterns.command.commas.annotations.COMMAS;
import nyla.solutions.global.patterns.command.commas.json.JsonAdvice;
import nyla.solutions.global.patterns.iteration.PageCriteria;
import nyla.solutions.global.patterns.iteration.Pagination;
import nyla.solutions.global.patterns.iteration.Paging;
import nyla.solutions.global.patterns.search.queryService.QuestCriteria;
import nyla.solutions.global.patterns.search.queryService.QuestFactory;

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
