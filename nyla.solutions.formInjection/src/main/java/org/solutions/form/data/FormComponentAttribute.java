package org.solutions.form.data;



import java.io.Serializable;

import nyla.solutions.global.data.Attribute;
import nyla.solutions.global.data.Data;
import nyla.solutions.global.data.Mappable;


/**
 * <pre>
 * FormComponentAttribute represents a attribute to a form component
 * </pre> 
 * @author Gregory Green
 * @version 1.0
 */
public abstract class FormComponentAttribute extends FormComponent
implements Mappable, Attribute
{
    private String value;
    private String key;
    
    public FormComponentAttribute(String key, String value) {
        setKey(key);
        setValue(value);
    }
    
    public String getText() {
        return key + "=" + value;
    }
    
   /**
    * 
    * @see com.bms.informatics.gcsm.common.data.Property#setValue(java.io.Serializable)
    */
   public void setValue(Object value)
   {
       setValue((Serializable) value);
   }//--------------------------------------------
   public void setValue(Serializable value) {
       this.value = String.valueOf(value);
   }

    public Object getValue() {
        return value;
    }
    public String getStringValue() {
        return value;
    }
    public Integer getValueInteger() {
        try { return new Integer(getStringValue()); }
        catch (Exception e) { return null; }
    }
    public void setKey(Object key) {
        if (key == null)
            throw new IllegalArgumentException("key cannot be null");
        this.key = String.valueOf(key);
    }
    public void setName(String name) {
        setKey(name);
    }
    public Object getKey() {
        return key;
    }
    public String getName() {
        return key;
    }
   /**
    * Constructor for FormComponentAttribute initalizes internal 
    * data settings.
    * 
    */
   public FormComponentAttribute()
   {
      super(); 
   }//--------------------------------------------
   /**
    * @return Returns the facts.
    */
   public final AttributeFacts getFacts()
   {
      return facts;
   }//--------------------------------------------
   /**
    * @param facts The facts to set.
    */
   public final void setFacts(AttributeFacts facts)
   {
      this.facts = facts;
   }//--------------------------------------------

   
   /**
    * @return Returns the primaryKey.
    */
   public final int getPrimaryKey()
   {
      return primaryKey;
   }//--------------------------------------------
   /**
    * @param primaryKey The primaryKey to set.
    */
   public final void setPrimaryKey(int primaryKey)
   {
      this.primaryKey = primaryKey;
   }//--------------------------------------------

   /**
    * 
    * @see java.lang.Object#clone()
    */
   public Object clone() throws CloneNotSupportedException
   {
      return super.clone();    
   }//----------------------------------------
   /**
    * 
    * @see com.bms.informatics.gcsm.common.data.Copier#copy(com.bms.informatics.gcsm.common.data.Copier)
    */
   /*
   public void copy(Copier aFrom)
   {
      if (!(aFrom instanceof FormComponentAttribute))
         throw new IllegalArgumentException(
         "aFrom instanceof FormComponentAttribute required in FormComponentAttribute.copy");
      
      
            
      FormComponentAttribute from = (FormComponentAttribute)aFrom;
      
      Object thisvalue = this.getValue();
      //set dirty values not equals
      if(thisvalue  == null || !thisvalue.equals(from.getValue()))
      {
         this.setDirty(true);
      }      
      
      Object thiskey = this.getKey();
      //set dirty keys not equal
      if(thiskey  == null || !thiskey.equals(from.getKey()))
      {
         this.setDirty(true);
      }    
         
      super.copy(aFrom);
      
      if(from.primaryKey > 0)  
         this.primaryKey = from.primaryKey;
      
      if(from.getCreateDate() != null)
         setCreateDate(from.getCreateDate());
      
      if(from.getUpdateDate() != null)
         setUpdateDate(from.getUpdateDate());
      
      if(from.getUpdateUserID() != null)
         setUpdateUserID(from.getUpdateUserID());
      
      if(from.getCreateUserID() != null)
         setCreateUserID(from.getCreateUserID());    
      
      this.setDeleted(from.isDeleted() ? true : false);
   }//--------------------------------------------
   */
   /**
    * 
    * @see com.bms.informatics.gcsm.common.data.Property#toString()
    */
   public String toString()
   {
      StringBuffer txt = new StringBuffer(super.toString())
      .append(" primaryKey=").append(primaryKey)
      .append(" createUserID=").append(getCreateUserID())
      .append(" updateUserID=").append(getUpdateUserID())
      .append(" updateDate=").append(getUpdateDate())
      .append(" createDate=").append(getCreateDate())
      .append(" deletedCode=").append(getDeletedCode());
      
      return txt.toString();
      
   }//----------------------------------------   
   /**
    * @return Returns the dirty.
    */
   public boolean isDirty()
   {
      if(isNew())
         setDirty(true);
         
      return dirty;
   }//--------------------------------------------
   /**
    * @param dirty The dirty to set.
    */
   public void setDirty(boolean dirty)
   {
      this.dirty = dirty;
   }//--------------------------------------------
   
   /**
    * @return this.primaryKey < 1
    * @see com.bms.informatics.gcsm.common.data.DirtyMarker#isNew()
    */
   public boolean isNew()
   {
      return this.primaryKey < 1;
   }//----------------------------------------
   /**
    * 
    * @see com.bms.informatics.gcsm.common.data.DirtyMarker#setDeleted(boolean)
    */
   public void setDeleted(boolean aDeleted)
   {
     if(aDeleted)
        this.setDeletedCode(Data.YES);
     else
        this.setDeletedCode(Data.NO);
   }//--------------------------------------------
   /**
    * 
    * @see com.bms.informatics.gcsm.common.data.DirtyMarker#setNew(boolean)
    */
   public void setNew(boolean aNew)
   {
   }//--------------------------------------------
   /**
    * 
    * @see com.bms.informatics.gcsm.common.data.Auditable#delete()
    */
   public void delete()
   {
      setDeletedCode("Y");
   }//--------------------------------------------
   public boolean equalsValueIgnoreCase(Object aValue) {
       return String.valueOf(value).equalsIgnoreCase(String.valueOf(aValue));
   }
   
   private boolean dirty = false;
   private AttributeFacts facts = null;
   private int primaryKey = Data.NULL;
}
