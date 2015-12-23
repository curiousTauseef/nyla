package nyla.solutions.office.chart;

import java.awt.Color;
import java.util.Iterator;
import java.util.List;



import nyla.solutions.dao.DataResultSet;
import nyla.solutions.dao.Query;
import nyla.solutions.global.data.DataRow;
import nyla.solutions.global.exception.NoDataFoundException;
import nyla.solutions.global.exception.RequiredException;
import nyla.solutions.global.exception.SystemException;
import nyla.solutions.global.util.Config;



/**
 * <pre>
 * Builds chart with results from a SQL query
 *  *  <i>Sample Spring Configuration</i>
 *  
 *  &lt;bean id="jvmMemorySqlChart" class="nyla.solutions.office.chart.SqlChartDecorator"&gt;
	   	   &lt;property name="sql"&gt;
	   			&lt;value&gt;
		   		select max(heap_size_used_kb) max_heap,JVM_LABEL,TO_CHAR(capture_date-1,'D')||') '|| TO_CHAR(capture_date,'DY') capture_hour
				from JVM_MEMORY_USAGES
				
				where  JVM_LABEL = 'CHEMCART'
				and capture_date >= trunc(sysdate, 'DAY')+1
		        group by JVM_LABEL, TO_CHAR(capture_date-1,'D')||') '|| TO_CHAR(capture_date,'DY')
		        order by capture_hour, max_heap desc

					   			&lt;/value&gt;
	   		&lt;/property&gt;
	   		&lt;property name="chart"&gt;
	   			&lt;ref bean="chemCartJvmMemoryJFreeChart"/&gt;
	   		&lt;/property&gt;  		
	&lt;/bean&gt;
 * 
 * </pre>
 * @author Gregory Green
 *
 */
public class SqlChartDecorator extends Query implements Chart
{
	/**
	 * serial Version UIDS
	 */
	private static final long serialVersionUID = -4381961249146145154L;
	
	/**
	 * Plot a value on the chart
	 */
	public void plotValue(double value, Comparable<?> rowKey, Comparable<?> labelColumn)
	{
		this.chart.plotValue(value, rowKey, labelColumn);
	}//---------------------------------------------
	/**
	 * Plot a value on the chart
	 */
	public void plotValue(Double value, Comparable<?> rowKey, Comparable<?> labelColumn)
	{
		this.chart.plotValue(value, rowKey, labelColumn);
	}//---------------------------------------------

	/**
	 * Decorate the chart bytes 
	 */
	public byte[] getBytes()
	{	
		checkChart();
		
		try
		{
			//perform query
			DataResultSet results = this.getResults();
			
			//add data of chart
			DataRow row = null;
			for(Iterator<DataRow> i = results.getRows().iterator(); i.hasNext();)
			{
				row = (DataRow)i.next();
			
				chart.plotValue(row.retrieveDouble(valueColumn), row.retrieveString(rowColumn), row.retrieveString(labelColumn));
			}
			
			
			return chart.getBytes();
		} 
		catch (NoDataFoundException e)
		{
			throw new SystemException(e);		 
		}
	}//---------------------------------------------

	private void checkChart()
	{
		if(this.chart == null)
			throw new RequiredException("Chart not set on "+this.getClass().getName());
	}//---------------------------------------------	
	/**
	 * @return the chart
	 */
	public Chart getChart()
	{
		return chart;
	}//---------------------------------------------

	/**
	 * @param chart the chart to set
	 */
	public void setChart(Chart chart)
	{
		if(chart == null)
			throw new RequiredException("chart in SqlChartDecorator");
		
		this.chart = chart;
	}

	/**
	 * @return the name
	 */
	public String getName()
	{
		checkChart();
		return chart.getName();
	}//---------------------------------------------

	/**
	 * @param name the name to set
	 */
	public void setName(String name)
	{
		checkChart();
		
		this.chart.setName(name);
	}//---------------------------------------------

	/**
	 * @return the typeName
	 */
	public String getTypeName()
	{
		checkChart();
		return chart.getTypeName();
	}//---------------------------------------------

	/**
	 * @param typeName the typeName to set
	 */
	public void setTypeName(String typeName)
	{
		checkChart();
		this.chart.setTypeName(typeName);
	}//---------------------------------------------
	
	
	
	/**
	 * @return
	 * @see nyla.solutions.office.chart.Chart#getSeriesColors()
	 */
	public List<Color> getSeriesColors()
	{
		return chart.getSeriesColors();
	}
	/**
	 * @param seriesColors
	 * @see nyla.solutions.office.chart.Chart#setSeriesColors(java.util.List)
	 */
	public void setSeriesColors(List<Color> seriesColors)
	{
		chart.setSeriesColors(seriesColors);
	}
	/**
	 * @return
	 * @see com.merck.mrl.office.chart.Chart#getCategoryLabel()
	 */
	public String getCategoryLabel()
	{
		return chart.getCategoryLabel();
	}
	/**
	 * @return
	 * @see com.merck.mrl.office.chart.Chart#getHeight()
	 */
	public int getHeight()
	{
		return chart.getHeight();
	}
	/**
	 * @return
	 * @see com.merck.mrl.office.chart.Chart#getTitle()
	 */
	public String getTitle()
	{
		return chart.getTitle();
	}
	/**
	 * @return
	 * @see com.merck.mrl.office.chart.Chart#getValueLabel()
	 */
	public String getValueLabel()
	{
		return chart.getValueLabel();
	}
	/**
	 * @param categoryLabel
	 * @see com.merck.mrl.office.chart.Chart#setCategoryLabel(java.lang.String)
	 */
	public void setCategoryLabel(String categoryLabel)
	{
		chart.setCategoryLabel(categoryLabel);
	}
	/**
	 * @param height
	 * @see com.merck.mrl.office.chart.Chart#setHeight(int)
	 */
	public void setHeight(int height)
	{
		chart.setHeight(height);
	}
	/**
	 * @param title
	 * @see com.merck.mrl.office.chart.Chart#setTitle(java.lang.String)
	 */
	public void setTitle(String title)
	{
		chart.setTitle(title);
	}
	/**
	 * @param valueLabel
	 * @see com.merck.mrl.office.chart.Chart#setValueLabel(java.lang.String)
	 */
	public void setValueLabel(String valueLabel)
	{
		chart.setValueLabel(valueLabel);
	}
	

	/**
	 * @return
	 * @see nyla.solutions.office.chart.Chart#getBackgroundColor()
	 */
	public Color getBackgroundColor()
	{
	   return chart.getBackgroundColor();
	}
	/**
	 * @return
	 * @see nyla.solutions.office.chart.Chart#getByteArrayBufferSize()
	 */
	public int getByteArrayBufferSize()
	{
	   return chart.getByteArrayBufferSize();
	}
	/**
	 * @return
	 * @see nyla.solutions.office.chart.Chart#getGraphType()
	 */
	public String getGraphType()
	{
	   return chart.getGraphType();
	}
	/**
	 * @return
	 * @see nyla.solutions.office.chart.Chart#getWidth()
	 */
	public int getWidth()
	{
	   return chart.getWidth();
	}
	/**
	 * @return
	 * @see nyla.solutions.office.chart.Chart#isLegend()
	 */
	public boolean isLegend()
	{
	   return chart.isLegend();
	}
	/**
	 * @param backgroundColor
	 * @see nyla.solutions.office.chart.Chart#setBackgroundColor(java.awt.Color)
	 */
	public void setBackgroundColor(Color backgroundColor)
	{
	   chart.setBackgroundColor(backgroundColor);
	}
	/**
	 * @param byteArrayBufferSize
	 * @see nyla.solutions.office.chart.Chart#setByteArrayBufferSize(int)
	 */
	public void setByteArrayBufferSize(int byteArrayBufferSize)
	{
	   chart.setByteArrayBufferSize(byteArrayBufferSize);
	}
	/**
	 * @param graphType
	 * @see nyla.solutions.office.chart.Chart#setGraphType(java.lang.String)
	 */
	public void setGraphType(String graphType)
	{
	   chart.setGraphType(graphType);
	}
	/**
	 * @param legend
	 * @see nyla.solutions.office.chart.Chart#setLegend(boolean)
	 */
	public void setLegend(boolean legend)
	{
	   chart.setLegend(legend);
	}
	/**
	 * @param width
	 * @see nyla.solutions.office.chart.Chart#setWidth(int)
	 */
	public void setWidth(int width)
	{
	   chart.setWidth(width);
	}



	

	private int valueColumn = Config.getPropertyInteger(getClass(),"valueColumn",1).intValue();
	private int rowColumn = Config.getPropertyInteger(getClass(),"rowColumn",2).intValue();
	private int labelColumn = Config.getPropertyInteger(getClass(),"labelColumn",3).intValue();
	private Chart chart = null;

	

}
