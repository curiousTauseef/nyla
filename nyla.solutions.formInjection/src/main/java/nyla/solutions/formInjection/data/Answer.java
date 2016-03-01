package nyla.solutions.formInjection.data;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import nyla.solutions.global.data.Data;
import nyla.solutions.global.exception.RequiredException;

/**
 * 
 * <pre>
 *  Answer is a value object representation of the Answer table
 *  and associated entities.
 * </pre>
 * 
 * @author Gregory Green
 * @version 1.0
 */

public class Answer implements FormComponent, Comparable<Object>
{
	private static final String KEY_SEPARATOR = "-";

	private String key;

	private Integer questionId;

	private Integer responseTableId;

	private String formTypeCode;

	private String answerId;


	private Integer row;

	private Integer col;

	private String value;

	private Map answerProps = new HashMap();

	public Answer()
	{
	}

	public Answer(Question question)
	{
		if (question == null)
			throw new RequiredException("question in Answer.Answer");

		setQuestionId(question.getQuestionId());

		if (question.getResponseTable() != null)
		{
			this.setResponseTableId(question.getResponseTable()
					.getResponseTableId());

		}
	}// --------------------------------------------

	public Answer(int questionId, int row, int col)
	{
		setQuestionId(new Integer(questionId));
		setRow(row >= 0 ? new Integer(row) : null);
		setCol(col >= 0 ? new Integer(col) : null);
	}

	public boolean isBlank()
	{
		return getValue() == null || getValue().equals("");
	}

	public Integer getCol()
	{
		return col;
	}

	public void setCol(Integer col)
	{
		this.col = col;
	}// --------------------------------------------

	public String getAnswerId()
	{
		return answerId;
	}// --------------------------------------------

	public void setAnswerId(String answerId)
	{
		this.answerId = answerId;
		for (Iterator i = this.getAnswerProps().values().iterator(); i
				.hasNext();)
		{
			AnswerProperty property = (AnswerProperty) i.next();
			property.setFormAnswerId(answerId);
		}
	}

	public String getAnswerId(boolean create)
	{
		if (!create)
			return getAnswerId();
		if (answerId == null)
			return generateKey(true);
		return answerId;
	}

	public Object getKey()
	{
		return key == null ? getAnswerId() : key;
	}

	public Integer getFormId()
	{
		return formId;
	}

	public void setFormId(Integer formId)
	{
		this.formId = formId;
	}

	public String getFormTypeCode()
	{
		return formTypeCode;
	}

	public void setFormTypeCode(String formTypeCode)
	{
		this.formTypeCode = formTypeCode;
	}// --------------------------------------------

	public Integer getQuestionId()
	{
		return questionId;
	}

	public void setQuestionId(Integer questionId)
	{
		this.questionId = questionId;
	}

	public Integer getResponseTableId()
	{
		return responseTableId;
	}

	public void setResponseTableId(Integer responseTableId)
	{
		this.responseTableId = responseTableId;
	}

	public Integer getRow()
	{
		return row;
	}

	public void setRow(Integer row)
	{
		this.row = row;
	}

	public Map getAnswerProps()
	{
		return answerProps;
	}

	protected void setAnswerProps(Map answerProps)
	{
		this.answerProps = answerProps != null ? answerProps : new HashMap();
	}

	public AnswerProperty getProperty(String key)
	{
		return (AnswerProperty) getAnswerProps().get(key);
	}

	public AnswerProperty retrieveProperty(String key)
	{
		return getProperty(key);
	}

	@Override
	public int compareTo(Object o)
	{
		return getText().compareTo(((Answer)o).getText());
	}// --------------------------------------------------------
	
	public void addProperty(AnswerProperty property)
	{
		if (property == null)
			return;
		property.setFormAnswerId(getAnswerId());
		getAnswerProps().put(property.getName(), property);
	}// --------------------------------------------

	public void addProperty(AnswerProperty property, Object userId)
	{
		if (property == null)
			return;
		property.setFormAnswerId(getAnswerId());
		getAnswerProps().put(property.getName(), property);
	}

	public void addProperty(String key, Object value, Object userId)
	{
		AnswerProperty property = getProperty(key);
		if (property == null)
		{
			property = new AnswerProperty();
			property.setName(key);
		}
		addProperty(property, userId);
		property.setValue((Serializable) value);
	}

	public String generateKey()
	{
		return generateKey(true);
	}

	/**
	 * 
	 * @param setPK
	 * @return formID + questionId + tableID * col + row
	 */
	protected String generateKey(boolean setPK)
	{
		key = toKey(getFormId(), getQuestionId(), getResponseTableId(),
				getCol(), getRow());
		if (setPK)
			setAnswerId(key);
		return key;
	}// --------------------------------------------

	public void setKey(String aKey)
	{
		key = aKey;
	}// --------------------------------------------

	public static String toKey(Object formId, Object aQuestionPK,
			Object aTablePK, Object aColumnNumber, Object aRowNumber)
	{
		StringBuffer newKey = new StringBuffer();
		newKey.append(formId).append(KEY_SEPARATOR).append(aQuestionPK)
				.append(KEY_SEPARATOR).append(aTablePK).append(KEY_SEPARATOR)
				.append(aColumnNumber).append(KEY_SEPARATOR).append(aRowNumber);
		return newKey.toString();
	}// --------------------------------------------

	public static String toKey(int formPK, int aQuestionPK, int aTablePK,
			int aColumnNumber, int aRowNumber)
	{
		Integer formId = new Integer(formPK);
		Integer questionId = new Integer(aQuestionPK);
		Integer tableId = aTablePK > 0 ? new Integer(aTablePK) : null;
		Integer col = aColumnNumber >= 0 ? new Integer(aColumnNumber) : null;
		Integer row = aRowNumber >= 0 ? new Integer(aRowNumber) : null;
		return toKey(formId, questionId, tableId, col, row);
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

	public String getValue()
	{
		return this.value;
	}// --------------------------------------------

	public void setValue(Object value)
	{
		this.value = (String) value;
	}// --------------------------------------------

	public int hashCode()
	{
		return questionId.hashCode();
	}

	public void setValue(String value)
	{
		this.value = value;
	}

	public String getText()
	{
		return getValue().toString();
	}

	static final long serialVersionUID = Answer.class.getName().hashCode();

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
