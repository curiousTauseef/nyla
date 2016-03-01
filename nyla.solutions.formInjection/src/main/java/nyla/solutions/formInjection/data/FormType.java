package nyla.solutions.formInjection.data;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import nyla.solutions.global.data.Data;

/**
 * 
 * <pre>
 * FormType provides a set of functions to
 * </pre>
 * 
 * @author Gregory Green
 * @version 1.0
 */
public class FormType implements FormComponent
{

	public Object getKey()
	{
		return getFormTypeId();
	}

	public String getCode()
	{
		return formTypeCode;
	}

	public void setFormTypeCode(String formTypeCode)
	{
		this.formTypeCode = formTypeCode;
	}

	public Integer getFormTypeId()
	{
		return formTypeId;
	}

	public void setFormTypeId(Integer formTypeId)
	{
		this.formTypeId = formTypeId;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String formTypeName)
	{
		this.name = formTypeName;
	}

	public Integer getVersion()
	{
		return version;
	}

	public void setVersion(Integer version)
	{
		this.version = version;
	}

	public String getText()
	{
		return getName();
	}// --------------------------------------------

	/**
	 * @return the formTypeCode
	 */
	public String getFormTypeCode()
	{
		return formTypeCode;
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

	static final long serialVersionUID = FormType.class.getName().hashCode();

	private Integer formTypeId;

	private String name;

	private String formTypeCode;

	private Integer version;

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
