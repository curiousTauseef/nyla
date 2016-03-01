/*
 * Created on Nov 8, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package nyla.solutions.formInjection.data;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import nyla.solutions.global.data.Data;


/**
 * 
 * <pre>
 * FormComment provides a set of functions to
 * </pre> 
 * @author Gregory Green
 * @version 1.0
 */
public class FormComment implements FormComponent {
    private Integer commentId;
    private Integer userId;
    private String commentText;
    
    
    public Integer getCommentId() {
        return commentId;
    }
    protected void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }
    public Object getKey() {
        return getCommentId();
    }
    public String getCommentText() {
        return commentText;
    }
    protected void setCommentText(String commentText) {
        this.commentText = commentText;
    }
    public Integer getFormId() {
        return formId;
    }
    protected void setFormId(Integer formId) {
        this.formId = formId;
    }
    public Integer getUserId() {
        return userId;
    }
    protected void setUserId(Integer userId) {
        this.userId = userId;
    }
    public String getText() {
        return getCommentText();
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
    static final long serialVersionUID = FormComment.class.getName().hashCode();
}
