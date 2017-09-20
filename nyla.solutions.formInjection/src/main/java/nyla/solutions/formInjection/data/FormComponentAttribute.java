package nyla.solutions.formInjection.data;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import nyla.solutions.global.data.Attribute;
import nyla.solutions.global.data.Data;
import nyla.solutions.global.data.Mappable;

/**
 * <pre>
 * FormComponentAttribute represents a attribute to a form component
 * </pre>
 * 
 * @author Gregory Green
 * @version 1.0
 */
public abstract class FormComponentAttribute implements Mappable, Attribute,
		FormComponent
{
	private String value;
	private String key;

	public FormComponentAttribute(String key, String value)
	{
		setKey(key);
		setValue(value);
	}

	public String getText()
	{
		return key + "=" + value;
	}

	public void setValue(Object value)
	{
		setValue((Serializable) value);
	}// --------------------------------------------

	public void setValue(Serializable value)
	{
		this.value = String.valueOf(value);
	}

	public Object getValue()
	{
		return value;
	}

	public String getStringValue()
	{
		return value;
	}

	public Integer getValueInteger()
	{
		try
		{
			return new Integer(getStringValue());
		}
		catch (Exception e)
		{
			return null;
		}
	}

	public void setKey(Object key)
	{
		if (key == null)
			throw new IllegalArgumentException("key cannot be null");
		this.key = String.valueOf(key);
	}

	public void setName(String name)
	{
		setKey(name);
	}

	public Object getKey()
	{
		return key;
	}

	public String getName()
	{
		return key;
	}

	/**
	 * Constructor for FormComponentAttribute initalizes internal data settings.
	 * 
	 */
	public FormComponentAttribute()
	{
		super();
	}// --------------------------------------------

	/**
	 * @return Returns the facts.
	 */
	public final AttributeFacts getFacts()
	{
		return facts;
	}// --------------------------------------------

	/**
	 * @param facts The facts to set.
	 */
	public final void setFacts(AttributeFacts facts)
	{
		this.facts = facts;
	}// --------------------------------------------

	/**
	 * @return Returns the primaryKey.
	 */
	public final int getPrimaryKey()
	{
		return primaryKey;
	}// --------------------------------------------

	/**
	 * @param primaryKey The primaryKey to set.
	 */
	public final void setPrimaryKey(int primaryKey)
	{
		this.primaryKey = primaryKey;
	}// --------------------------------------------

	/**
	 * 
	 * @see java.lang.Object#clone()
	 */
	public Object clone() throws CloneNotSupportedException
	{
		return super.clone();
	}// ----------------------------------------

	/*
	 * public void copy(Copier aFrom) { if (!(aFrom instanceof
	 * FormComponentAttribute)) throw new IllegalArgumentException(
	 * "aFrom instanceof FormComponentAttribute required in FormComponentAttribute.copy"
	 * );
	 * 
	 * 
	 * 
	 * FormComponentAttribute from = (FormComponentAttribute)aFrom;
	 * 
	 * Object thisvalue = this.getValue(); //set dirty values not equals
	 * if(thisvalue == null || !thisvalue.equals(from.getValue())) {
	 * this.setDirty(true); }
	 * 
	 * Object thiskey = this.getKey(); //set dirty keys not equal if(thiskey ==
	 * null || !thiskey.equals(from.getKey())) { this.setDirty(true); }
	 * 
	 * super.copy(aFrom);
	 * 
	 * if(from.primaryKey > 0) this.primaryKey = from.primaryKey;
	 * 
	 * if(from.getCreateDate() != null) setCreateDate(from.getCreateDate());
	 * 
	 * if(from.getUpdateDate() != null) setUpdateDate(from.getUpdateDate());
	 * 
	 * if(from.getUpdateUserID() != null)
	 * setUpdateUserID(from.getUpdateUserID());
	 * 
	 * if(from.getCreateUserID() != null)
	 * setCreateUserID(from.getCreateUserID());
	 * 
	 * this.setDeleted(from.isDeleted() ? true : false);
	 * }//--------------------------------------------
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

	}// ----------------------------------------

	/**
	 * @return Returns the dirty.
	 */
	public boolean isDirty()
	{
		if (isNew())
			setDirty(true);

		return dirty;
	}// --------------------------------------------

	/**
	 * @param dirty The dirty to set.
	 */
	public void setDirty(boolean dirty)
	{
		this.dirty = dirty;
	}// --------------------------------------------

	
	public boolean isNew()
	{
		return this.primaryKey < 1;
	}// ----------------------------------------

	public void setDeleted(boolean aDeleted)
	{
		if (aDeleted)
			this.setDeletedCode(Data.YES);
		else
			this.setDeletedCode(Data.NO);
	}// --------------------------------------------


	public void setNew(boolean aNew)
	{
	}// --------------------------------------------

	
	public void delete()
	{
		setDeletedCode("Y");
	}// --------------------------------------------

	public boolean equalsValueIgnoreCase(Object aValue)
	{
		return String.valueOf(value).equalsIgnoreCase(String.valueOf(aValue));
	}

	/**
	 * @return the id
	 */
	public String getId()
	{
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id)
	{
		this.id = id;
	}

	/**
	 * @return the formType
	 */
	public FormType getFormType()
	{
		return formType;
	}

	/**
	 * @param formType the formType to set
	 */
	public void setFormType(FormType formType)
	{
		this.formType = formType;
	}

	/**
	 * @return the formId
	 */
	public Integer getFormId()
	{
		return formId;
	}

	/**
	 * @param formId the formId to set
	 */
	public void setFormId(Integer formId)
	{
		this.formId = formId;
	}

	/**
	 * @return the formStatusId
	 */
	public Integer getFormStatusId()
	{
		return formStatusId;
	}

	/**
	 * @param formStatusId the formStatusId to set
	 */
	public void setFormStatusId(Integer formStatusId)
	{
		this.formStatusId = formStatusId;
	}

	/**
	 * @return the formProps
	 */
	public Map getFormProps()
	{
		return formProps;
	}

	/**
	 * @param formProps the formProps to set
	 */
	public void setFormProps(Map formProps)
	{
		this.formProps = formProps;
	}

	/**
	 * @return the createUserLogin
	 */
	public Object getCreateUserLogin()
	{
		return createUserLogin;
	}

	/**
	 * @param createUserLogin the createUserLogin to set
	 */
	public void setCreateUserLogin(Object createUserLogin)
	{
		this.createUserLogin = createUserLogin;
	}

	/**
	 * @return the createUserID
	 */
	public Object getCreateUserID()
	{
		return createUserID;
	}

	/**
	 * @param createUserID the createUserID to set
	 */
	public void setCreateUserID(Object createUserID)
	{
		this.createUserID = createUserID;
	}

	/**
	 * @return the createDate
	 */
	public Date getCreateDate()
	{
		return createDate;
	}

	/**
	 * @param createDate the createDate to set
	 */
	public void setCreateDate(Date createDate)
	{
		this.createDate = createDate;
	}

	/**
	 * @return the updateUserID
	 */
	public Object getUpdateUserID()
	{
		return updateUserID;
	}

	/**
	 * @param updateUserID the updateUserID to set
	 */
	public void setUpdateUserID(Object updateUserID)
	{
		this.updateUserID = updateUserID;
	}

	/**
	 * @return the updateDate
	 */
	public Timestamp getUpdateDate()
	{
		return updateDate;
	}

	/**
	 * @param updateDate the updateDate to set
	 */
	public void setUpdateDate(Timestamp updateDate)
	{
		this.updateDate = updateDate;
	}

	/**
	 * @return the deletedCode
	 */
	public String getDeletedCode()
	{
		return deletedCode;
	}

	/**
	 * @param deletedCode the deletedCode to set
	 */
	public void setDeletedCode(String deletedCode)
	{
		this.deletedCode = deletedCode;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(String value)
	{
		this.value = value;
	}

	/**
	 * @param key the key to set
	 */
	public void setKey(String key)
	{
		this.key = key;
	}

	@Override
	public void setUpdateDate(Date date)
	{
		this.updateDate = new Timestamp(date.getTime());

	}

	@Override
	public boolean isDeleted()
	{
		return Data.YES.equals(this.deletedCode);
	}

	private boolean dirty = false;
	private AttributeFacts facts = null;

	private String id;
	private FormType formType = null;
	private Integer formId;
	private Integer formStatusId;
	private Map formProps = new HashMap();

	private Object createUserLogin = null;

	private Object createUserID = null;

	private Date createDate = null;

	private Object updateUserID = null;

	private Timestamp updateDate = null;
	private int primaryKey = Data.NULL;

	private String deletedCode = Data.NO;

}
