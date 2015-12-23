package nyla.solutions.global.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * Represent a row of data
 * @author Gregory Green
 *
 */
public class DataRow implements Arrayable<Object>, Serializable, Mapped<String, Object>
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6651575339358295855L;
	
	public  DataRow()
	{
		this.positionEntries = new ArrayList<Object>();
		
	}// --------------------------------------------------------
	/***
	 * 
	 * @param inputs initial row values
	 */
	public  DataRow(Object[] inputs)
	{
		this.positionEntries = new ArrayList<Object>(Arrays.asList(inputs));
	}// --------------------------------------------------------
	/***
	 * 
	 * @param inputs initial row values
	 */
	public  DataRow(Map<String,Object> inputs)
	{
		this();
		
		this.nameEntries = inputs;
	}// --------------------------------------------------------
	   /**
	    * 
	    * Constructor for DataRow initializes internal from the result set 
	    * @param resultSet the database result set
	    * @param rowNum starts from 0
	    * @throws SQLException
	    */
	   public DataRow(int rowNum)
	   {     
		   this();
	      this.rowNum = rowNum;
	   }// --------------------------------------------   
	   public DataRow(int rowNum, int size)
	   {     
	     this.rowNum = rowNum;
	     
	     this.positionEntries = new ArrayList<Object>(size);
	     
	     for (int i = 0; i < size; i++)
		 {
			this.positionEntries.add(null);	
		  }
	   }// --------------------------------------------   
	   
	   /**
	    * 
	    * @param aEntry added data
	    */
	   protected void add(Object aEntry)
	   {
	      //if (aEntry == null)
	      //{
	       //  aEntry = aEntry;
	         
	      //}

	      positionEntries.add(aEntry);
	   }// --------------------------------------------
	   /**
	 * @see nyla.solutions.global.data.DataRow#retrieveString(int)
	 */
	   public String retrieveString(int aPosition)
	   {
		 try
		 {
		   
	      Object  o = positionEntries.get(aPosition -1);
	      
	      
	      return String.valueOf(o);
		 }
		 catch(ArrayIndexOutOfBoundsException e)
		 {
			 if(aPosition < 1)
				 throw new ArrayIndexOutOfBoundsException("Invalid Position "+aPosition+" less than 1 ");
			 
			 throw e;
		 }
	   }// --------------------------------------------
	   /**
	 * @see nyla.solutions.global.data.DataRow#retrieveObject(int)
	 */
	   public Object retrieveObject(int aPosition)
	   {
	      return positionEntries.get(aPosition -1);
	   }// --------------------------------------------------------
	   /**
	 * @see nyla.solutions.global.data.DataRow#assignObject(int, java.lang.Object)
	 */
	   public void assignObject(int aPosition , Object obj)
	   {
		   if(positionEntries.size() < aPosition)
		   {
			   for (int i = positionEntries.size(); i < aPosition; i++)
				 {
					this.positionEntries.add(null);	
				  }
		   }
		   
			 
		   positionEntries.set(aPosition-1, obj);
	   }// --------------------------------------------------------
	   /**
	    * 
	    * @param map the map to add
	    */
	   /*public void assignMap(Map<String,Object> map)
	   {
			if(nameEntries == null)
				nameEntries = new  HashMap<String, Object>(map);
			else
				nameEntries.putAll(map);
	   }*/
	   // --------------------------------------------------------
	   
		/**
		 * @param key
		 * @param value
		 * @return
		 * @see java.util.HashMap#put(java.lang.Object, java.lang.Object)
		 */
		public void assignObject(String key, Object value)
		{
			if(nameEntries == null)
				nameEntries = new  HashMap<String, Object>();
			
			nameEntries.put(key, value);
		}// --------------------------------------------------------
	   
	   /**
	 * @see nyla.solutions.global.data.DataRow#retrieveDouble(int)
	 */
	   public Double retrieveDouble(int aPosition)
	   {
	      Object  o = positionEntries.get(aPosition -1);
	      
	      if(o ==null)
	         return null;
	      
	      String text = String.valueOf(o);
	      
	      if(text == null || text.length() == 0)
	    	  return null;
	      
	      return new Double(text);
	   }// --------------------------------------------
	   /**
	 * @see nyla.solutions.global.data.DataRow#retrieveInteger(int)
	 */
	   public Integer retrieveInteger(int aPosition)
	   {
	      Object  o = positionEntries.get(aPosition -1);
	      
	      if(o ==null)
	         return -1;
	      
	      String text = String.valueOf(o);
	      
	      if(text == null || text.length() == 0)
	    	  return -1;
	      
	      return Integer.valueOf(text);
	   }// --------------------------------------------
	   
	   /**
	 * @see nyla.solutions.global.data.DataRow#retrieveDate(int)
	 */
	   public Date retrieveDate(int aPosition)
	   {
	      Object  o = positionEntries.get(aPosition -1);
	      
	      if(o ==null)
	         return null;
	            
	     return (Date)o;
	  
	   }// --------------------------------------------
	  
	   
    /**
	 * @see nyla.solutions.global.data.DataRow#getStrings()
	 */
	   public String[] getStrings()
	   {
	      try
	      {
	         if(this.positionEntries == null || this.positionEntries.isEmpty())
	            return null;
	         
	         String [] strings = new String[this.size()];
	         
	         for(int i = 0;i < this.size();i++)
	         {
	            strings[i] = this.retrieveString(i+1);
	         }
	         
	         return strings;
	      }
	      catch (RuntimeException e)
	      {        
	         throw new RuntimeException("entries="+positionEntries+" ",e);
	      }
	   }// --------------------------------------------
	  

		/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return new StringBuilder("DataRow [")
				.append(" positionEntries=")
				.append(String.valueOf(positionEntries)).append(", nameEntries=").append(nameEntries).append(", rowNum=").append(rowNum).append("]").toString();
	}// --------------------------------------------------------
		/**
	    * 
	    * @return entries.size()
	    */
	   public int size()
	   {
	      if(positionEntries == null)
	         return 0;
	      
	      return positionEntries.size();
	   }//--------------------------------------------
	   
	   /**
	 * @see nyla.solutions.global.data.DataRow#getRowNum()
	 */
		public int getRowNum()
		{
			return rowNum;
		}// --------------------------------------------------------
		/**
		 * @see nyla.solutions.global.data.DataRow#setRowNum(int)
		 */
		public void setRowNum(int rowNum)
		{
			this.rowNum = rowNum;
		}// --------------------------------------------------------
		/**
		 * @see nyla.solutions.global.data.DataRow#toArray()
		 */
		public Object[] toArray()
		{
			return positionEntries.toArray();
		}
		
		
		
		/**
		 * @param key
		 * @return
		 * @see java.util.HashMap#get(java.lang.Object)
		 */
		public String retrieveString(String key)
		{
			if(nameEntries == null)
				return null;
			
			return (String)nameEntries.get(key);
		}// --------------------------------------------------------
		/**
		 * @param key
		 * @return
		 * @see java.util.HashMap#get(java.lang.Object)
		 */
		public Date retrieveDate(String key)
		{
			if(nameEntries == null)
				return null;
			
			return (Date)nameEntries.get(key);
		}// --------------------------------------------------------
		/**
		 * @param key
		 * @return
		 * @see java.util.HashMap#get(java.lang.Object)
		 */
		public Double retrieveDouble(String key)
		{
			if(nameEntries == null)
				return null;
			
			return (Double)nameEntries.get(key);
		}// --------------------------------------------------------		
		/**
		 * @param key
		 * @return
		 * @see java.util.HashMap#get(java.lang.Object)
		 */
		public Integer retrieveInteger(String key)
		{
			if(nameEntries == null)
				return null;
			
			return (Integer)nameEntries.get(key);
		}// --------------------------------------------------------			
		/**
		 * @param key
		 * @return
		 * @see java.util.HashMap#get(java.lang.Object)
		 */
		public Object retrieveObject(String key)
		{
			if(nameEntries == null)
				return null;
			
			return nameEntries.get(key);
		}// --------------------------------------------------------				

		/**
		 * 
		 * @see nyla.solutions.global.data.Mapped#getMap()

		 */
		@Override
		public Map<String, Object> getMap()
		{
			
			return this.nameEntries;
		}// --------------------------------------------------------
		/**
		 * 
		 * @see nyla.solutions.global.data.Mapped#setMap(java.util.Map)
		 */
		@Override
		public void setMap(Map<String, Object> map)
		{
			this.nameEntries = map;
		}// --------------------------------------------------------

	    private final ArrayList<Object> positionEntries;
	    private Map<String, Object> nameEntries;
	    private int rowNum;

}