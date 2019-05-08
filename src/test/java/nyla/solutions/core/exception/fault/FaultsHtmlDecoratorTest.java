package nyla.solutions.core.exception.fault;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import org.junit.Test;

public class FaultsHtmlDecoratorTest
{

	@Test
	public void testFaultsHtmlDecorator()
	{
		
		FaultsHtmlTextDecorator faultsHtmlTextDecorator = null;
		
		faultsHtmlTextDecorator = new FaultsHtmlTextDecorator(null);
		
		assertNull(faultsHtmlTextDecorator.getText());
		
		faultsHtmlTextDecorator = new FaultsHtmlTextDecorator(Collections.singleton(null));
		assertNull(faultsHtmlTextDecorator.getText());
		
		
	}

	@Test
	public void testGetText()
	{

		FaultException faultException = new FaultException("test_message");
		faultException.setCode("001");
		
		
		Collection<Fault> faults = new ArrayList<Fault>();
		faults.add(faultException);
		FaultsHtmlTextDecorator decorator = new FaultsHtmlTextDecorator(faults);

		String faultsSummaryHtml = decorator.getText();
		
		System.out.println("text:"+faultsSummaryHtml);
		assertTrue(faultsSummaryHtml,faultsSummaryHtml != null && faultsSummaryHtml.trim().length() > 0 && !"null".equals(faultsSummaryHtml));
		assertTrue(faultsSummaryHtml,faultsSummaryHtml.contains(faultException.getCode()));
		assertTrue(faultsSummaryHtml,faultsSummaryHtml.contains("test_message"));
		
	}

	@Test
	public void testGetTarget()
	{
		
		FaultsHtmlTextDecorator decorator = new FaultsHtmlTextDecorator(Collections.singleton(new FaultException("test")));
		
		assertNotNull(decorator.getTarget());
	}

}
