package nyla.solutions.formInjection.data;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import nyla.solutions.global.data.Data;


public class QuestionChoice implements FormComponent {

   /**
    * 
    * Compare the two codes
    * @see nyla.solutions.formInjection.data.FormComponent#compareTo(java.lang.Object)
    */
   public int compareTo(Object o)
   {
      QuestionChoice other = (QuestionChoice)o;
      
      return this.code.compareTo(other.code);
   }//--------------------------------------------
    
    public Object getKey() {
        //return useTextAsValue ? getText() : (getChoiceId() != null ? getChoiceId().toString() : "");
       return code;
    }//--------------------------------------------
    public Integer getChoiceId() {
        return choiceId;
    }
    public void setPrimaryKey(int id) {
        setChoiceId(new Integer(id));
    }
    public void setChoiceId(Integer choiceId) {
        this.choiceId = choiceId;
    }
    public Integer getColNumber() {
        return colNumber;
    }
    public void setColNumber(Integer colNumber) {
        this.colNumber = colNumber;
    }
    public String getDefaultCode() 
    {
       if(defaultCode == null)
          defaultCode = "N";
       
        return defaultCode;
    }// --------------------------------------------

    public boolean isDefault() {
        return "Y".equals(getDefaultCode());
    }
    public void setDefaultCode(String defaultCode) {
        this.defaultCode = "Y".equals(defaultCode) ? "Y" : "N";
    }
    public Integer getFormTypeId() {
        return formTypeId;
    }
    public void setFormTypeId(Integer formTypeId) {
        this.formTypeId = formTypeId;
    }
    public int getNumber() {
        return number;
    }
    public void setNumber(int number) {
        this.number = number;
    }
    public Integer getQuestionId() {
        return questionId;
    }
    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }
    public Integer getResponseTableId() {
        return responseTableId;
    }
    public void setResponseTableId(Integer responseTableId) {
        this.responseTableId = responseTableId;
    }
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
    public String getName() {
        return getText();
    }
    public void setName(String text) {
        this.text = text;
    }
    public boolean isUseTextAsValue() {
        return useTextAsValue;
    }
    public void setUseTextAsValue(boolean useTextAsValue) {
        this.useTextAsValue = useTextAsValue;
    }
    /**
     * @return the code
     */
    public String getCode()
    {
       return code;
    }//--------------------------------------------
    /**
     * @param code the code to set
     */
    public void setCode(String code)
    {
       this.code = code;
    }//--------------------------------------------
    
    
    
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
	 * @return the primaryKey
	 */
	public int getPrimaryKey()
	{
		return primaryKey;
	}



	private String code = null;
    
    static final long serialVersionUID = QuestionChoice.class.getName().hashCode();
    private Integer choiceId;
    private Integer questionId;
    private Integer responseTableId;
    private int number;
    private String defaultCode = "N";
    private String text;
    private Integer formTypeId;
    private Integer colNumber;
    private boolean useTextAsValue;
    
    
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
