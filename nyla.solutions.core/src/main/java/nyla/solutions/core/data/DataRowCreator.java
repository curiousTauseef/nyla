package nyla.solutions.core.data;

import java.util.HashMap;

import nyla.solutions.core.patterns.creational.RowObjectCreator;
import nyla.solutions.core.patterns.reflection.JavaBeanVisitor;
import nyla.solutions.core.util.JavaBean;


/**
 * Create a Data ROW that data from a object
 * @author Gregory Green
 *
 */
public class DataRowCreator implements JavaBeanVisitor, RowObjectCreator<DataRow, Object>
{
	public DataRowCreator()
	{
	}

	/**
	 * @see nyla.solutions.core.patterns.reflection.JavaBeanVisitor#visitClass(java.lang.Class, java.lang.Object)
	 */
	@Override
	public void visitClass(Class<?> aClass, Object object)
	{
	}

	/**
	 * @see nyla.solutions.core.patterns.reflection.JavaBeanVisitor#visitProperty(java.lang.String, java.lang.Object, java.lang.Object)
	 */
	@Override
	public void visitProperty(String name, Object value, Object object)
	{
		map.put(name, value);
	}// --------------------------------------------------------
	/**
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public DataRow getDataRow()
	{
		return new DataRow((HashMap<String,Object>)map.clone());
	}// --------------------------------------------------------
	
	@Override
	public DataRow create(Object rs, int index)
	{
		JavaBean.acceptVisitor(rs, this);
		
		DataRow dataRow = this.getDataRow();
		
		dataRow.setRowNum(index);
		
		this.clear();
		
		return dataRow;
	}// --------------------------------------------------------
	
	/**
	 * Clear the current built properties
	 */
	public void clear()
	{
		map.clear();
	}
	
	private HashMap<String, Object> map = new HashMap<String, Object>();



}
