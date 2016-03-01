package nyla.solutions.formInjection.data;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import nyla.solutions.global.data.Data;



public class ManagedForm implements FormComponent 
{

    private String formTypeCode;

    public Integer getFormId() {
        return replaceWithNull(formId);
    }
    protected void setFormId(Integer formId) {
        this.formId = replaceWithNull(formId);
    }
    public Object getKey() {
        return getFormId();
    }
    public Integer getFormStatusId() {
        return replaceWithNull(formStatusId);
    }
    public void setFormStatusId(Integer formStatusId) {
        this.formStatusId = replaceWithNull(formStatusId);
    }
    public String getFormTypeCode() {
        return formTypeCode;
    }
    public void setFormTypeCode(String aFormTypeCode) {
        this.formTypeCode = aFormTypeCode;
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
	public Map getFormProps() {
        return formProps;
    }
    protected void setFormProps(Map formProps) {
    	if (formProps == null)
    		this.formProps.clear();
        for (Iterator i = formProps.values().iterator(); i.hasNext(); ) {
        	FormProperty prop = (FormProperty) i.next();
        	addProperty(prop);
        }
    }// --------------------------------------------

    public void setProperties(Collection formProps) {
    if (formProps == null || formProps.isEmpty())
        this.formProps.clear();
       for (Iterator i = formProps.iterator(); i.hasNext(); ) {
        FormProperty prop = (FormProperty) i.next();
        addProperty(prop);
       }
   }// --------------------------------------------

    public void addProperty(FormProperty property) {
    	if (formProps == null)
    		formProps = new HashMap();
        if (property != null) {
            property.setFormId(getFormId());
            formProps.put(property.getName(), property);
        }
    }
    public FormProperty getProperty(String key) {
        return (FormProperty) formProps.get(key);
    }
    public FormProperty removeProperty(String key) {
        return (FormProperty) formProps.remove(key);
    }
    public String getText() {
        return getFormId() != null ? String.valueOf(getFormId()) : "";
    }
    static final long serialVersionUID = ManagedForm.class.getName().hashCode();
    
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
