package nyla.solutions.global.patterns.search.queryService;

import org.junit.Ignore;

import nyla.solutions.global.data.DataRow;
import nyla.solutions.global.patterns.iteration.Paging;
import nyla.solutions.global.patterns.search.queryService.QuestCriteria;
import nyla.solutions.global.patterns.search.queryService.QuestMgr;
import nyla.solutions.global.patterns.search.queryService.QuestService;
import junit.framework.TestCase;


/**
 * Test case for quest manager
 * @author Gregory Green
 *
 */
@Ignore
public class QuestMgrTest extends TestCase
{
	/**
	 * Test search for quest
	 */
	
	public void testSearch()
	{
		QuestCriteria questCriteria = new QuestCriteria();
		
		String[] dataSources = {"quest.dataSource.sqlFire.inventory"};
		
		questCriteria.setDataSources(dataSources);
		
		questCriteria.setQuestName("selectInventoryByContainingLabel");
		
		Paging<DataRow> paging = quest.search(questCriteria);
		
		assertTrue(paging != null && !paging.isEmpty());
		
	}// --------------------------------------------------------
	private QuestService quest = new QuestMgr();
}
