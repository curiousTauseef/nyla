package nyla.solutions.formInjection.dao.geode.data;

import javax.jdo.annotations.Key;
import javax.persistence.Id;

import org.springframework.data.gemfire.mapping.Region;

@Region("form")
public class Form extends nyla.solutions.formInjection.data.Form
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7725919563569984840L;

	public Form()
	{
		// TODO Auto-generated constructor stub
	}

	@Id
	@Key
	@Override
	public String getId()
	{
		// TODO Auto-generated method stub
		return super.getId();
	}
	
	@Id
	@Key
	@Override
	public void setId(String id)
	{
		// TODO Auto-generated method stub
		super.setId(id);
	}
}
