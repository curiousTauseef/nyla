package nyla.solutions.dao.patterns.search.queryService;

import org.junit.Ignore;

import nyla.solutions.global.data.DataRow;
import nyla.solutions.global.patterns.iteration.Paging;
import nyla.solutions.global.patterns.search.queryService.QuestCriteria;
import nyla.solutions.global.patterns.search.queryService.QuestFactory;
import nyla.solutions.global.patterns.search.queryService.QuestKey;
import nyla.solutions.global.patterns.search.queryService.QuestService;
import nyla.solutions.global.util.Debugger;
import junit.framework.TestCase;

@Ignore
public class JdbcQuestFinderTest extends TestCase
{

	public void testCall()
	{
		
		QuestService service = QuestFactory.getInstance().createQuestService();
		
		QuestCriteria questCriteria = new QuestCriteria();
		
		String[] dataSources = {"sqlFire.inventory"};
		
		questCriteria.setDataSources(dataSources);
		
		
		QuestKey[] questKeys = {new QuestKey("containerLabel","B0001","java.lang.String")};
		
		questCriteria.setQuestKeys(questKeys);
		
		questCriteria.setQuestName("selectInventoryByContainingLabel");
		
		Paging<DataRow> paging = service.search(questCriteria);
		
		Debugger.println("RESULTS:"+paging);
		
		assertTrue(paging != null && !paging.isEmpty());
		
		for(int i=0;i<10000;i++)
			service.search(questCriteria);
		
	}

}
