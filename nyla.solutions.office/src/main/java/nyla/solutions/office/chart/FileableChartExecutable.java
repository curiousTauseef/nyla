
package nyla.solutions.office.chart;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.List;

import nyla.solutions.global.exception.RequiredException;
import nyla.solutions.global.exception.SystemException;
import nyla.solutions.global.io.Fileable;
import nyla.solutions.global.io.IO;
import nyla.solutions.global.patterns.command.Environment;
import nyla.solutions.global.patterns.command.Executable;
import nyla.solutions.global.util.Config;
import nyla.solutions.global.util.Debugger;




/**
 * Executable that saves the chart to a file
 * @author Gregory Green
 *
 */
public class FileableChartExecutable implements Executable, ChartDecorator, Fileable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 50256333546723804L;
	
	/**
	 * Save the chart to a file
	 */
	public Integer execute(Environment environment)
	{
		checkChart();
		
		toFile();
		
		return 1;
	}//---------------------------------------------
	/**
	 * @return the chart
	 */
	public Chart getChart()
	{
		return chart;
	}//---------------------------------------------
	/**
	 * @return
	 * @see com.merck.mrl.eln.global.data.Binary#getBytes()
	 */
	public byte[] getBytes()
	{
		checkChart();
		
		return chart.getBytes();
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
	 * @see com.merck.mrl.eln.global.data.Nameable#getName()
	 */
	public String getName()
	{
		checkChart();
		
		return chart.getName();
	}//---------------------------------------------
	/**
	 * @return
	 * @see com.merck.mrl.eln.global.data.Type#getTypeName()
	 */
	public String getTypeName()
	{
		checkChart();
		
		return chart.getTypeName();
	}//---------------------------------------------
	/**
	 * @param value
	 * @param rowKey
	 * @param label
	 * @see com.merck.mrl.office.chart.Chart#plotValue(double, java.lang.Comparable, java.lang.Comparable)
	 */
	public void plotValue(double value, Comparable<?> rowKey, Comparable<?> label)
	{
		checkChart();
		
		chart.plotValue(value, rowKey, label);
	}
	/**
	 * @param value
	 * @param rowKey
	 * @param label
	 * @see com.merck.mrl.office.chart.Chart#plotValue(java.lang.Double, java.lang.Comparable, java.lang.Comparable)
	 */
	public void plotValue(Double value, Comparable<?> rowKey, Comparable<?> label)
	{
		checkChart();
		
		chart.plotValue(value, rowKey, label);
	}
	/**
	 * @param name
	 * @see com.merck.mrl.eln.global.data.Nameable#setName(java.lang.String)
	 */
	public void setName(String name)
	{
		checkChart();
		
		chart.setName(name);
	}//---------------------------------------------
	/**
	 * @param typeName
	 * @see com.merck.mrl.eln.global.data.Type#setTypeName(java.lang.String)
	 */
	public void setTypeName(String typeName)
	{
		checkChart();
		chart.setTypeName(typeName);
	}//---------------------------------------------
	public File getFile()
	{
		checkChart();
		
		return toFile();
	}//---------------------------------------------
	/**
	 * <chart-name>.<chart-type>
	 * @return this.chart.getName()+"."+this.chart.getTypeName()
	 */
	public String getFileName()
	{
		checkChart();
		
		return this.chart.getName()+"."+this.chart.getTypeName();
	}//---------------------------------------------
	/**
	 * @param chart the chart to set
	 */
	public void setChart(Chart chart)
	{
		if(chart == null)
			throw new RequiredException("chart in FileableChartExecutable");
		
		this.chart = chart;
	}//---------------------------------------------
	/**
	 * Assert that the chart is set
	 */
	private void checkChart()
	{
		if(chart == null)
			throw new RequiredException("Chart not set in "+this.getClass().getName());
	}
	private File toFile()
	{
		
		try
		{
			File file = new File(this.rootPath+"/"+this.getFileName());
			
			IO.writeFile(file, chart.getBytes());
			
			return file;
			
		} catch (IOException e)
		{
			throw new SystemException(this.rootPath+"/"+this.getFileName()+" "+Debugger.stackTrace(e));
		}
	}//---------------------------------------------


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
	 * @see nyla.solutions.office.chart.Chart#getLegend()
	 */
	public boolean isLegend()
	{
	   return chart.isLegend();
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


	private String rootPath = Config.getProperty(getClass(),"rootPath");
	private Chart chart = null;


}
