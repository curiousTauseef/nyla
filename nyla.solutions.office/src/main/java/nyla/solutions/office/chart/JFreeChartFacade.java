package nyla.solutions.office.chart;
import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import nyla.solutions.global.exception.RequiredException;
import nyla.solutions.global.exception.SystemException;
import nyla.solutions.global.util.Config;
import nyla.solutions.global.util.Debugger;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;



/**
 * [Taken from JFreeChart Documentation]
 * To create a chart using JFreeChart, you must create a Dataset, which you then use to create a JFreeChart. A Dataset contains the data that displays in the chart. JFreeChart features many different Dataset objects, which you can use to create assorted types of charts. Once you create a Dataset, you next create the actual chart. JFreeChart uses an object appropriately named JFreeChart to represent charts. You create JFreeChart objects from Dataset objects with the ChartFactory class. In the following examples, we will create pie, XY, and bar charts along with their corresponding Dataset objects.

		Pie chart 
		A pie chart is created from a PieDataset. The following example creates a PieDataset using the DefaultPieDataset class, adds two values via the setValue() method, and then creates a pie chart with the ChartFactory's createPieChart() method. This example will create a pie chart with the title "Sample Pie Chart," a legend, and two slices: JavaWorld with 75 percent of the pie, and Other with the other 25 percent:
		
		
		DefaultPieDataset pieDataset = new DefaultPieDataset();
		pieDataset.setValue("JavaWorld", new Integer(75));
		pieDataset.setValue("Other", new Integer(25));
		
		JFreeChart chart = ChartFactory.createPieChart
		                     ("Sample Pie Chart",   // Title
		                      pieDataset,           // Dataset
		                      true                  // Show legend  
		                     );
		
		XY chart 
		An XYDataset can create area, line, and step XY charts. The following example creates an XYDataset from a series of data containing three XY points. Next, ChartFactory's createAreaXYChart() method creates an area XY chart. In addition to parameters for title, dataset, and legend, createAreaXYChart() takes in the labels for the X and Y axes:
		
		
		XYSeries series = new XYSeries("Average Size");
		series.add(20.0, 10.0);
		series.add(40.0, 20.0);
		series.add(70.0, 50.0);
		XYDataset xyDataset = new XYSeriesCollection(series);
		
		JFreeChart chart = ChartFactory.createAreaXYChart
		                     ("Sample XY Chart",  // Title
		                      "Height",           // X-Axis label
		                      "Weight",           // Y-Axis label
		                      xyDataset,          // Dataset
		                      true                // Show legend
		                     );
		
		Bar chart 
		A CategoryDataset can create numerous different charts, including horizontal and vertical bar charts. The following example creates a CatagoryDataset with two series of data and two categories, and then creates a 3D vertical bar chart from this dataset. This example creates a chart that compares the sales growth in two quarters over two years:
		
		
		String[] seriesNames = new String[] {"2001", "2002"};
		String[] categoryNames = new String[] {"First Quater",
		                                       "Second Quater"};
		Number[][] categoryData = new Integer[][] {{new Integer(20),
		                                            new Integer(35)},
		                                           {new Integer(40),
		                                            new Integer(60)}
		                                           };
		CategoryDataset categoryDataset = new DefaultCategoryDataset
		                                        (seriesNames,
		                                         categoryNames,
		                                         categoryData);
		
		JFreeChart chart = ChartFactory.createVerticalBarChart3D
		                     ("Sample Category Chart", // Title
		                      "Quarters",              // X-Axis label
		                      "Sales",                 // Y-Axis label
		                      categoryDataset,         // Dataset
		                      true                     // Show legend
		                     );
		
		Integrate JFreeChart 
		Integrating JFreeChart into a Swing application is relatively easy. Just create a BufferedImage from the chart and use the image as an icon for a JLabel:
		
		
		BufferedImage image = chart.createBufferedImage(500,300);
		
		JLabel lblChart = new JLabel();
		lblChart.setIcon(new ImageIcon(image));
		
		JFreeChart also includes a class named ChartUtilities that provides several methods for saving charts to files or writing them out to streams in JPEG or PNG format. For example, the following piece of code can export a chart to a JPEG:
		
		
		ChartUtilities.saveChartAsJPEG(new File("chart.jpg"), chart, 500, 300);
		
		The methods in the ChartUtilities class can be used to create JPEGs for use in a static Webpage, or used in a jsp (JavaServer Pages)/servlet-based application to dynamically stream charts to Webpages.
		
 Usage Bar Char
	Chart chart = new JFreeChartFacade();
	
      chart.setCategoryLabel(categoryLabel);
	chart.setHeight(height);
	chart.setName(name);
	chart.plotValue(100.0, "Usage", "CIB");
	chart.plotValue(50.0, "Usage", "AIB");
	chart.setTypeName(Chart.PNG_TYPE_NAME);
	//default bar
	IO.writeFile(new File(filePath), chart.getBytes());
	
Usage Pie Char
	chart.setCategoryLabel(categoryLabel);
	chart.setHeight(height);
	chart.setName(name);
	chart.plotValue(100.0, "Group A (66%)", "Usage");
	chart.plotValue(50.0, "Group B  (34%)", "Usage");
	chart.setTypeName(Chart.PNG_TYPE_NAME);
	
	chart.setGraphType(Chart.PIE_GRAPH_TYPE);
	
	
	IO.writeFile(new File(filePath), chart.getBytes());
	
	
	
	Debugger.println("Write to "+filePath);
 * @author Gregory Green
 *
 */
public class JFreeChartFacade implements Chart
{
   
	/**
	 * serial Version UID
	 */
	private static final long serialVersionUID = -6910401210511262949L;
	/**
	 * 
	 * @return JPEG version of chart
	 */
	public byte[] getBytes()
	{
		try
		{
			JFreeChart chart = createChart();
			
			
			ByteArrayOutputStream chartOut =  new ByteArrayOutputStream(this.byteArrayBufferSize);
			
			ChartUtilities.writeChartAsJPEG(chartOut, chart, width, height);
			
			return chartOut.toByteArray();
		} 
		catch (IOException e)
		{
			throw new SystemException(Debugger.stackTrace(e));
		}
		
	}//---------------------------------------------


	/**
	   * Creates and returns a bar chart showing the breakdown
	   * of visits by folder. The data is arbitrary.
	   */
	  private  JFreeChart createChart()
	  {	   
	   
	    JFreeChart jfreeChart = null;
	    
	    if(LINE_GRAPH_TYPE.equalsIgnoreCase(graphType))
	     {
	    	jfreeChart = ChartFactory.createLineChart( title, categoryLabel, valueLabel, dataset, orientation, legend, tooltips, urls );	    	
	    	
	    	
	     }
	    else if(LINE3D_GRAPH_TYPE.equalsIgnoreCase(graphType))
	    {
		 jfreeChart = ChartFactory.createLineChart3D(title, categoryLabel, valueLabel, dataset, orientation, detectLegend, tooltips, urls);
	    }
	    else if(PIE_GRAPH_TYPE.equalsIgnoreCase(graphType))
	    {
		 //String title, PieDataset dataset, boolean legend, boolean tooltips, boolean urls)
		jfreeChart = ChartFactory.createPieChart(title, toPieDataset(), legend,tooltips,urls);
	    }
	    else if(AREA_GRAPH_TYPE.equalsIgnoreCase(graphType))
	    {
		 //String title, PieDataset dataset, boolean legend, boolean tooltips, boolean urls)
		jfreeChart = ChartFactory.createAreaChart(title, categoryLabel, valueLabel, dataset, orientation, detectLegend, tooltips, urls);
	    }
	    else if(PIE_3D_GRAPH_TYPE.equalsIgnoreCase(graphType))
	    {
		 jfreeChart = ChartFactory.createPieChart3D(title, toPieDataset(), legend,tooltips,urls);
	    }
	    else
	    {
		 //default
	    	jfreeChart = ChartFactory.createBarChart( title, categoryLabel, valueLabel, dataset, orientation, legend, tooltips, urls );
	    }
	    
	    paintPlotSeries(jfreeChart);
	    
	    jfreeChart.setBackgroundPaint(backgroundColor );
	    
	    //BarRenderer renderer = (BarRenderer)plot.getRenderer();
	    //renderer.setDrawBarOutline(false);
	    
	    //renderer.setSeriesPaint(0, new GradientPaint( 0.0f, 0.0f, Color.green, 0.0f, 0.0f, Color.lightGray ));
	    return jfreeChart;
	  }//---------------------------------------------
	  /**
	   * Render the colors for various series data.
	   * This method using the colors provided in the seriesColor arrayList
	   * @param chart the chart to paint
	   */
	  protected void paintPlotSeries(JFreeChart chart)
	  {
		  if(this.seriesColors == null || this.seriesColors.isEmpty())
			  return; //nothing to paint (use default values)
		  
		  
		  CategoryPlot plot = chart.getCategoryPlot();
		  CategoryItemRenderer  renderer = plot.getRenderer();
		  
		  Color color = null;
		  
		  for (int i = 0; i < seriesColors.size(); i++) 
		  {
			color = (Color)seriesColors.get(i);
			renderer.setSeriesPaint(i, color);
		  }
	  }//---------------------------------------------
	  /**
	   * Plot a value on the chart
	   * @param label the column name
	   * @param rowKey the row key data
	   */
	  public void plotValue(double value, Comparable<?> rowKey, Comparable<?> label)
	  {
		  dataset.addValue(value, rowKey, label);
	  }//---------------------------------------------
	  /**
	   * Plot a value on the chart
	   */
	  public void plotValue(Double value, Comparable<?> rowKey, Comparable<?> label)
	  {
		  if(value == null)
			  return; //ignore plot (no data)
		  
		  if(detectLegend && rowKey != null && rowKey.toString().length() > 0 &&
				  label != null && label.toString().length() > 0)
		  {
			this.legend = true;		  
		  }
			  
		  dataset.addValue(value.doubleValue(), rowKey, label);
	  }//---------------------------------------------
	  
	  /**
	 * @return the orientation
	 */
	public PlotOrientation getOrientation()
	{
		return orientation;
	}

	/**
	 * @param orientation the orientation to set
	 */
	public void setOrientation(PlotOrientation orientation)
	{
		this.orientation = orientation;
	}//---------------------------------------------
	/**
	 * Note: Default orientation is vertical 
	 * @param orientation the display orientation (HORIZONTAL or VERTICAL).
	 * 
	 *
	 */
	public void setOrientation(String orientation)
	{
		if("HORIZONTAL".equalsIgnoreCase(orientation))
		{
			this.setOrientation(PlotOrientation.HORIZONTAL);
		}
		else
		{
			this.setOrientation(PlotOrientation.VERTICAL);
		}		
	}//---------------------------------------------

	/**
	 * @return the dataset
	 */
	public DefaultCategoryDataset getDataset()
	{
		return dataset;
	}//---------------------------------------------
	

	/**
	 * @param dataset the dataset to set
	 */
	public void setDataset(DefaultCategoryDataset dataset)
	{
		this.dataset = dataset;
	}

	/**
	 * @return the valueLabel
	 */
	public String getValueLabel()
	{
		return valueLabel;
	}

	/**
	 * @param valueLabel the valueLabel to set
	 */
	public void setValueLabel(String valueLabel)
	{
		this.valueLabel = valueLabel;
	}

	/**
	 * @return the categoryLabel
	 */
	public String getCategoryLabel()
	{
		return categoryLabel;
	}

	/**
	 * @param categoryLabel the categoryLabel to set
	 */
	public void setCategoryLabel(String categoryLabel)
	{
		this.categoryLabel = categoryLabel;
	}

	/**
	 * @return the title
	 */
	public String getTitle()
	{
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title)
	{
		this.title = title;
	}

	/**
	 * @return the tooltips
	 */
	public boolean isTooltips()
	{
		return tooltips;
	}

	/**
	 * @param tooltips the tooltips to set
	 */
	public void setTooltips(boolean tooltips)
	{
		this.tooltips = tooltips;
	}

	/**
	 * @return the legend
	 */
	public boolean isLegend()
	{
		return legend;
	}

	/**
	 * @param legend the legend to set
	 */
	public void setLegend(boolean legend)
	{
		this.legend = legend;
	}

	/**
	 * @return the urls
	 */
	public boolean isUrls()
	{
		return urls;
	}

	/**
	 * @param urls the urls to set
	 */
	public void setUrls(boolean urls)
	{
		this.urls = urls;
	}

	/**
	 * @return the byteArrayBufferSize
	 */
	public int getByteArrayBufferSize()
	{
		return byteArrayBufferSize;
	}

	/**
	 * @param byteArrayBufferSize the byteArrayBufferSize to set
	 */
	public void setByteArrayBufferSize(int byteArrayBufferSize)
	{
		this.byteArrayBufferSize = byteArrayBufferSize;
	}

	/**
	 * @return the width
	 */
	public int getWidth()
	{
		return width;
	}

	/**
	 * @param width the width to set
	 */
	public void setWidth(int width)
	{
		this.width = width;
	}

	/**
	 * @return the height
	 */
	public int getHeight()
	{
		return height;
	}

	/**
	 * @param height the height to set
	 */
	public void setHeight(int height)
	{
		this.height = height;
	}//---------------------------------------------

	
	/**
	 * @return the name
	 */
	public String getName()
	{
		return name;
	}//---------------------------------------------

	/**
	 * @param name the name to set
	 */
	public void setName(String name)
	{
		this.name = name;
	}//---------------------------------------------

	/**
	 * @return the typeName
	 */
	public String getTypeName()
	{
		return typeName;
	}

	/**
	 * Examples (png, jpg, gif)
	 * @param typeName the typeName to set
	 */
	public void setTypeName(String typeName)
	{
		this.typeName = typeName;
	}


	
	/**
	 * @return the graphType
	 */
	public String getGraphType()
	{
		return graphType;
	}

	/**
	 * Expect bars types (line|bar|pie)
	 * @param graphType the graphType to set
	 */
	public void setGraphType(String graphType)
	{
	   if(graphType != null && 
	     !LINE_GRAPH_TYPE.equals(graphType) &&
	     !LINE3D_GRAPH_TYPE.equals(graphType) &&
	     !PIE_GRAPH_TYPE.equals(graphType) &&
	     !PIE_3D_GRAPH_TYPE.equals(graphType) &&
	     !AREA_GRAPH_TYPE.equals(graphType) &&
	     !BAR_GRAPH_TYPE.equals(graphType))
	   {
		throw new IllegalArgumentException("Expect bars types (line|bar|pie)");
	   }
	   
	    this.graphType = graphType;
	}// ----------------------------------------------

	/**
	 * @return the detectLegend
	 */
	public boolean isDetectLegend()
	{
		return detectLegend;
	}

	/**
	 * @param detectLegend the detectLegend to set
	 */
	public void setDetectLegend(boolean detectLegend)
	{
		this.detectLegend = detectLegend;
	}//---------------------------------------------	
	/**
	 * @return the seriesColors
	 */
	public List<Color> getSeriesColors()
	{
		return seriesColors;
	}//---------------------------------------------

	/**
	 * @param seriesColors the seriesColors to set
	 */
	public void setSeriesColors(List<Color> seriesColors)
	{
		this.seriesColors = seriesColors;
	}//---------------------------------------------
	/**
	 * @return the backgroundColor
	 */
	public Color getBackgroundColor()
	{
		return backgroundColor;
	}//---------------------------------------------

	/**
	 * @param backgroundColor the backgroundColor to set
	 */
	public void setBackgroundColor(Color backgroundColor)
	{
		this.backgroundColor = backgroundColor;
	}
	private DefaultPieDataset toPieDataset()
	{
	   int columnCount = dataset.getColumnCount();	
	   if(columnCount == 0)
		throw new RequiredException("Plot at least one vlaue with a label/column");
	   
	   
	   int rowCount = dataset.getRowCount();
	   
	   DefaultPieDataset pieDataset = new DefaultPieDataset();
	   Comparable<?> key = null;
	   Number value = null;
	   
	   //use row key and row value for first column
	   for (int row = 0; row < rowCount; row++)
	   {
		key = this.dataset.getRowKey(row);
		
		value = this.dataset.getValue(row, 0); //get value for first column
		pieDataset.setValue(key, value);		
	   }
	
	   return pieDataset;
	}// ----------------------------------------------

	
	private Color backgroundColor = Color.white;
	private List<Color> seriesColors = null;
	private String graphType = null;
	private String name = Config.getProperty(this.getClass(),"name","Chart");
	private String typeName = Config.getProperty(this.getClass(),"typeName",Chart.JPG_TYPE_NAME);	
	private PlotOrientation orientation = PlotOrientation.VERTICAL;
	private DefaultCategoryDataset dataset = new DefaultCategoryDataset();
	private String valueLabel = Config.getProperty(this.getClass().getName()+".valueLabel","Values");
	private String categoryLabel = Config.getProperty(this.getClass().getName()+".categoryLabel","Categories");
	private String title = Config.getProperty(this.getClass().getName()+".title","Summary");
	private boolean tooltips = Config.getPropertyBoolean(this.getClass().getName()+".tooltips",false).booleanValue();
	private boolean legend = Config.getPropertyBoolean(this.getClass().getName()+".legend",false).booleanValue();
	private boolean urls = Config.getPropertyBoolean(this.getClass().getName()+".urls",false).booleanValue();
	private int byteArrayBufferSize =  Config.getPropertyInteger(this.getClass().getName()+".byteArrayBufferSize",1024).intValue();
	private int width =  Config.getPropertyInteger(this.getClass().getName()+".width",550).intValue();
	private int height =  Config.getPropertyInteger(this.getClass().getName()+".height",550).intValue();
	private boolean detectLegend = Config.getPropertyBoolean(this.getClass().getName()+".detectLegend",true).booleanValue();
}
