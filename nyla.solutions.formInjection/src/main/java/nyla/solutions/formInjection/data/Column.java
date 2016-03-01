package nyla.solutions.formInjection.data;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import nyla.solutions.global.data.Data;
import nyla.solutions.global.util.Organizer;

/**
 * 
 * <pre>
 * Column is a value object representation of the Column table
 * and associated entities.
 * </pre>
 * 
 * @author Gregory Green
 * @version 1.0
 */
public class Column implements FormComponent
{

	/**
	 * 
	 * @return the map of choices for this component
	 */
	public Map getChoiceMap()
	{
		if (choiceMap == null)
			choiceMap = new HashMap();

		return choiceMap;
	}// --------------------------------------------

	public Collection getQuestionChoices()
	{
		return choiceMap.values();
	}

	protected void setChoiceMap(Map choiceMap)
	{
		this.choiceMap = choiceMap != null ? choiceMap : new LinkedHashMap();
	}

	public boolean hasChoices()
	{
		Collection choices = this.getQuestionChoices();
		return !Organizer.isEmpty(choices);
	}// --------------------------------------------

	public Object getKey()
	{
		return colNumber;
	}

	public Integer getColNumber()
	{
		return colNumber;
	}

	public Integer getNumber()
	{
		return colNumber;
	}

	protected void setColNumber(Integer colNumber)
	{
		this.colNumber = colNumber;
	}

	public int getColSize()
	{
		return colSize;
	}

	protected void setColSize(int colSize)
	{
		this.colSize = colSize;
	}

	public String getName()
	{
		return name;
	}

	protected void setName(String name)
	{
		this.name = name;
	}

	public Integer getResponseTableId()
	{
		return responseTableId;
	}

	protected void setResponseTableId(Integer responseTableId)
	{
		this.responseTableId = responseTableId;
	}

	public Integer getResponseTypeId()
	{
		return responseTypeId;
	}

	protected void setResponseTypeId(Integer responseTypeId)
	{
		this.responseTypeId = responseTypeId;
	}

	public ResponseType getResponseType()
	{
		return responseType;
	}// --------------------------------------------

	/**
	 * 
	 * @param name the attribute name
	 * @return the question attribute with a given name
	 */
	public QuestionAttribute getAttribute(String name)
	{
		if (attributes == null)
			return null;

		return (QuestionAttribute) attributes.get(name);
	}// --------------------------------------------

	public Map getAttributes()
	{
		if (attributes == null)
			return new HashMap(); // empty attributes

		return attributes;
	}// --------------------------------------------

	public String getText()
	{
		return this.name;
	}

	// -------------------------------------------------------
	// for retrofit
	// -------------------------------------------------------
	public QuestionAttribute findAttributeByName(String name)
	{
		return getAttribute(name);
	}// --------------------------------------------

	/**
	 * 
	 *
	 * @see nyla.solutions.formInjection.data.FormComponent#toString()
	 */
	public String toString()
	{
		ResponseType type = responseType;
		responseType = null;
		String s = super.toString();
		responseType = type;
		return s;
	}// --------------------------------------------

	/**
	 * 
	 * Compare the column numbers
	 * 
	 * @see nyla.solutions.formInjection.data.FormComponent#compareTo(java.lang.Object)
	 */
	public int compareTo(Object o)
	{
		if (this.colNumber == null || o == null)
			return -1;

		Column other = (Column) o;
		// if(other.colNumber == null)
		// return -1;

		return this.colNumber.compareTo(other.getColNumber());
	}// --------------------------------------------

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

	/**
	 * @param responseType the responseType to set
	 */
	public void setResponseType(ResponseType responseType)
	{
		this.responseType = responseType;
	}

	/**
	 * @param attributes the attributes to set
	 */
	public void setAttributes(Map attributes)
	{
		this.attributes = attributes;
	}

	static final long serialVersionUID = Column.class.getName().hashCode();

	private Integer responseTableId;

	private Integer responseTypeId;

	private ResponseType responseType;

	private String name;

	private int colSize;

	private Integer colNumber;

	private Map choiceMap = new LinkedHashMap();

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
