package nyla.solutions.formInjection.data;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import nyla.solutions.global.data.Data;

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
public class ResponseTable implements FormComponent, Comparable<Object>
{
	@Override
	public int compareTo(Object o)
	{
		if(o == null)
			return 1;
		
		return getText().compareTo(((Question)o).getText());
	}// --------------------------------------------------------
	
	/**
	 * 
	 *
	 * @see org.solutions.data.Criteria#getKey()
	 */
	public Object getKey()
	{
		return responseTableId;
	}// --------------------------------------------

	/**
	 * 
	 * @return values in the column map
	 */
	public Collection getColumns()
	{
		if (this.columns == null)
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
	 * @return the primaryKey
	 */
	public int getPrimaryKey()
	{
		return primaryKey;
	}

	/**
	 * @param primaryKey the primaryKey to set
	 */
	public void setPrimaryKey(int primaryKey)
	{
		this.primaryKey = primaryKey;
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
			col = (Column) new ArrayList(getColumnMap().values())
					.get(colNumber);
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

	@Override
	public void delete()
	{
		this.deletedCode = Data.YES;
	}

}
