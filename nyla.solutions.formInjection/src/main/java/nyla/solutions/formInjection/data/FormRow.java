package nyla.solutions.formInjection.data;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import nyla.solutions.formInjection.bre.Operation;
import nyla.solutions.formInjection.exception.BreException;
import nyla.solutions.global.data.Data;
import nyla.solutions.global.operations.logging.Log;
import nyla.solutions.global.util.Debugger;

/**
 * 
 * <pre>
 * FormRow provides a set of functions to
 * </pre>
 * 
 * @author Gregory Green
 * @version 1.0
 */
public class FormRow implements FormComponentWrapper, FormComponent
{

	protected FormRow(FormTable table, int rowNum)
	{
		if (table == null)
			throw new IllegalArgumentException("form table is required");
		this.table = table;
		this.rowNum = rowNum;
	}

	public Object getKey()
	{
		return new Integer(rowNum);
	}

	public String getText()
	{
		return "";
	}

	/*
	 * public void unDelete() { for (Iterator i = getAnswers().iterator();
	 * i.hasNext();) { FormAnswer answer = (FormAnswer) i.next();
	 * answer.unDelete(); } }//--------------------------------------------
	 */

	public Form getForm()
	{
		return table.getForm();
	}

	public Form retrieveForm()
	{
		return getForm();
	}

	public FormTable getFormTable()
	{
		return table;
	}

	public Object getManagedObject()
	{
		return null;
	}

	public int getColumnCount()
	{
		return table.getColumnCount();
	}

	public FormAnswer addAnswer(int col, Object value)
	{
		Column c = null;
		if (table.getColumnMap().containsKey(new Integer(col)))
			c = table.getColumn(col);
		else
		{
			List cols = new ArrayList(getFormColumns().values());
			c = (Column) cols.get(col);
		}
		return table.getFormQuestion().setAnswer(value, rowNum,
				c.getColNumber().intValue());
	}

	public FormAnswer pickChoice(int col, String choiceKey)
	{
		FormAnswer a = addAnswer(col, null);
		a.pickChoice(choiceKey);
		return a;
	}

	public FormAnswer pickChoiceByText(int col, String choiceText)
	{

		FormAnswer a = addAnswer(col, null);

		if (!a.hasChoices())
		{
			// TODO: fix PATCH

			ResponseType responseType = a.getResponseType();

			if (responseType.hasOperation())
			{
				Operation operation = responseType.getOperationBluePrint()
						.getOperation();
				Debugger.println("executing " + operation);

				try
				{
					if (a.isWithinTable())
					{
						FormColumn column = a.getFormColumn();
						Debugger.println("executing on column " + column);
						operation.execute(column);
					}
					else
					{
						Debugger.println("executing on question "
								+ a.getFormQuestion());
						operation.execute(a.getFormQuestion());
					}
				}
				catch (BreException e)
				{

					Debugger.printWarn(e);
				}
			}
		}

		Debugger.getLog(this.getClass()).debug("In form row");
		// //Debugger.dump(a);
		// Debugger.dump(a.getFormColumn());
		a.pickChoiceByText(choiceText);
		return a;
	}

	public Map getFormColumns()
	{
		return getFormTable().getFormColumnMap();
	}

	public Collection getColumns()
	{
		return new TreeSet(getFormColumns().values());
	}// --------------------------------------------

	public List getAnswers()
	{
		FormAnswer[] answers = new FormAnswer[table.getColumnCount()];
		int index = 0;
		for (Iterator i = getFormColumns().keySet().iterator(); i.hasNext(); index++)
		{
			answers[index] = getAnswer(((Integer) i.next()).intValue());
		}
		return Arrays.asList(answers);
	}

	public FormAnswer getAnswer(int colNum)
	{
		FormAnswer answer = getForm().findAnswer(
				table.getFormQuestion().getQuestionId(), rowNum, colNum);
		if (answer == null)
			return table.getFormQuestion().setAnswer(null, rowNum, colNum);
		return answer;
	}

	private FormAnswer getAnswer(int colNum, boolean create)
	{
		if (create)
			return getAnswer(colNum);
		return table
				.getFormQuestion()
				.getForm()
				.findAnswer(table.getFormQuestion().getQuestionId(), rowNum,
						colNum);
	}

	public FormAnswer findAnswerByColNumber(int colNum)
	{
		return getAnswer(colNum);
	}

	/*
	 * public void deleteRow() { List answers = getAnswers(); for (Iterator i =
	 * answers.iterator(); i.hasNext(); ) { FormAnswer a = (FormAnswer)
	 * i.next(); a.delete(); } }
	 */

	public void addProperty(String key, Object value)
	{
		List answers = this.getAnswers();
		for (Iterator i = answers.iterator(); i.hasNext();)
		{
			FormAnswer a = (FormAnswer) i.next();
			a.addProperty(key, value, getForm().getAccessUser().getId());
		}
	}

	public AnswerProperty findProperty(String key)
	{
		Log log = Debugger.getLog(this.getClass());
		log.debug("Looing for property with key: " + key);
		FormAnswer a = getAnswer(1, false);
		if (a == null)
		{
			log.debug("Answer is null");
			for (int i = 0; i < 10; i++)
			{
				FormAnswer a1 = getAnswer(i, false);
				if (a1 == null)
					log.debug("Answer is null for col: " + i);
				else
				{
					log.debug("Answer is not null for col: " + i);
					return a1.getProperty(key);
				}

			}
			return null;
		}
		return a.getProperty(key);
	}// --------------------------------------------

	public int getNumber()
	{
		return rowNum;
	}// --------------------------------------------

	/**
	 * @return the table
	 */
	public FormTable getTable()
	{
		return table;
	}

	/**
	 * @param table the table to set
	 */
	public void setTable(FormTable table)
	{
		this.table = table;
	}

	/**
	 * @return the rowNum
	 */
	public int getRowNum()
	{
		return rowNum;
	}

	/**
	 * @param rowNum the rowNum to set
	 */
	public void setRowNum(int rowNum)
	{
		this.rowNum = rowNum;
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

	static final long serialVersionUID = FormRow.class.getName().hashCode();
	private FormTable table;

	int rowNum;

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
