package org.solutions.form.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * <pre>
 * ResponseTable is a value object representation of the ResponseTable table
 * and associated entities.
 * </pre>
 * 
 * @author Gregory Green
 * @version 1.0
 */
public class ResponseTable extends FormComponent
{
   /**
    * 
    *
    * @see org.solutions.data.Criteria#getKey()
    */
   public Object getKey()
   {
      return responseTableId;
   }//--------------------------------------------

   /**
    * 
    * @return values in the column map
    */
   public Collection getColumns()
   {
      if(this.columns  == null)
         return null;
      
      return columns.values();      
   }// --------------------------------------------
   /**
    * 
    * @return
    */
   public int getDefaultRowSize()
   {
      return defaultRowSize;
   }

   protected void setDefaultRowSize(int size)
   {
      this.defaultRowSize = size;
   }

   public Integer getResponseTableId()
   {
      return responseTableId;
   }

   protected void setResponseTableId(Integer responseTableId)
   {
      this.responseTableId = responseTableId;
   }

   public String getTableName()
   {
      return tableName;
   }

   protected void setTableName(String tableName)
   {
      this.tableName = tableName;
   }

   public Map getColumnMap()
   {
      return columns != null ? columns : Collections.EMPTY_MAP;
   }

   protected void setColumns(Map columns)
   {
      this.columns = columns;
   }

   public Column getColumn(int colNumber)
   {
      Column col = (Column) getColumnMap().get(new Integer(colNumber));
      if (col == null)
      {
         col = (Column) new ArrayList(getColumnMap().values()).get(colNumber);
      }
      return col;
   }

   public boolean hasColumns()
   {
      return !getColumnMap().isEmpty();
   }

   public Map getAttributes()
   {
      return attributes;
   }

   protected void setAttributes(Map attributes)
   {
      this.attributes = attributes != null ? attributes : new HashMap();
   }

   public String getText()
   {
      return getTableName();
   }

   static final long serialVersionUID = ResponseTable.class.getName()
   .hashCode();

   private Integer responseTableId;

   private int defaultRowSize = 1;

   private String tableName;

   private Map columns;

   private Map attributes = new HashMap();

}
