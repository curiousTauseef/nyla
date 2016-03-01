package nyla.solutions.formInjection.data;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nyla.solutions.formInjection.FormGuide;
import nyla.solutions.formInjection.FormVisitor;
import nyla.solutions.global.data.Attribute;
import nyla.solutions.global.data.Auditable;
import nyla.solutions.global.data.Copier;
import nyla.solutions.global.data.Createable;
import nyla.solutions.global.data.Data;
import nyla.solutions.global.data.Identifier;
import nyla.solutions.global.data.PrimaryKey;
import nyla.solutions.global.data.Textable;
import nyla.solutions.global.exception.NoDataFoundException;
import nyla.solutions.global.exception.RequiredException;
import nyla.solutions.global.exception.SystemException;
import nyla.solutions.global.security.data.SecurityCredential;
import nyla.solutions.global.security.user.data.User;

/**
 * 
 * <pre>
 *  Form is a value object representation of the Form table 
 *  and associated entities.
 * </pre>
 * 
 * @author Gregory Green
 * @version 1.0
 */
public class Form implements PrimaryKey, Createable, Serializable, Copier,
		Cloneable, Textable, Auditable, Identifier, FormComponent
{
	/**
	 * 
	 * Constructor for Form initializes internal
	 */
	public Form()
	{
	}// --------------------------------------------

	/**
	 * 
	 * @return the
	 */
	public Form getForm()
	{
		return this;
	}// --------------------------------------------

	/**
	 * 
	 * @param q
	 */
	public void setQuestionaire(Questionaire q)
	{
		if (questionaire == null
				|| !questionaire.getFormTypeId().equals(q.getFormTypeId()))
		{
			this.questionaire = q;
			Map m = q.getQuestions();
			Map formQuestionMap = new HashMap();
			for (Iterator i = m.values().iterator(); i.hasNext();)
			{
				Question question = (Question) i.next();
				formQuestionMap.put(question.getQuestionId(), new FormQuestion(
						this, question));
			}
			this.formQuestionMap = formQuestionMap;
		}
	}// --------------------------------------------

	public Questionaire getQuestionaire()
	{
		return questionaire;
	}// --------------------------------------------

	public boolean hasQuestionaire()
	{
		return questionaire != null;
	}// --------------------------------------------

	/**
	 * 
	 * @return
	 */
	public FormContext getFormContext()
	{
		if (formContext == null)
			throw new IllegalStateException("No form context has been created");
		return formContext;
	}// --------------------------------------------

	/**
    * 
    */
	public Collection getProperties()
	{
		if(this.formProps == null)
			return Collections.EMPTY_LIST;
		
		return this.formProps.values();
	}// --------------------------------------------

	public void setFormContext(FormContext ctx)
	{
		this.formContext = ctx;
	}

	public void addAnswers(Collection answers)
	{
		if (answers == null || answers.isEmpty())
			return; // nothing to add

		for (Iterator i = answers.iterator(); i.hasNext();)
		{
			Answer a = (Answer) i.next();
			addAnswer(a);
		}
	}

	public void addFormAnswers(Collection formAnswers)
	{
		if (formAnswers == null || formAnswers.isEmpty())
			return; // nothing to add

		for (Iterator i = formAnswers.iterator(); i.hasNext();)
		{
			FormAnswer a = (FormAnswer) i.next();
			addFormAnswer(a);
		}
	}// --------------------------------------------

	public void clearAnswers()
	{
		answersByQuestion.clear();
		formAnswerMap.clear();
	}

	public Map clearProperties()
	{
		Map props = new HashMap(getFormProps());
		this.getFormProps().clear();
		return props;
	}

	public Set getFormAnswers()
	{
		if(formAnswerMap == null)
			return Collections.EMPTY_SET;
		
		return new HashSet(formAnswerMap.values());
	}

	public void setAnswers(Set answers)
	{
		this.clearAnswers();
		this.addAnswers(answers);
	}// --------------------------------------------

	public Map getFormAnswerMap()
	{
		return formAnswerMap;
	}

	public boolean hasAnswers()
	{
		
		return formAnswerMap != null && !formAnswerMap.isEmpty();
	}

	public FormAnswer findAnswerByID(Object key)
	{
		return (FormAnswer) formAnswerMap.get(key);
	}

	protected FormAnswer addFormAnswer(FormAnswer a)
	{
		return addAnswer(a);
	}// --------------------------------------------

	public FormAnswer addAnswer(Answer a)
	{
		if (a == null)
			throw new RequiredException("Answerw in Form.addAnswer");

		if (this.questionaire == null)
			throw new RequiredException("Form's Questionaire");

		if (a.getQuestionId() == null)
			throw new RequiredException("Answer's question ID");

		this.remove(a); // remove any existing references
		Integer qid = a.getQuestionId();
		if (qid == null)
			throw new IllegalArgumentException("answer must have a question id");

		FormAnswer formAnswer = null;
		if (!(a instanceof FormAnswer))
		{
			FormQuestion question = findFormQuestion(qid);
			if (question == null)
				throw new SystemException("Question ID " + qid
						+ " is not found in key set "
						+ formQuestionMap.keySet());

			formAnswer = new FormAnswer(question, a);
		}
		else
		{
			formAnswer = (FormAnswer) a;
		}
		if (formAnswer.isDeleted())
		{
			return formAnswer; // nothing left to do, we're not gonna re-add
		}

		formAnswer.setFormId(getFormId());

		// don't add blank answers that haven't yet been saved to db
		// if (formAnswer.getAnswerId() != null || !formAnswer.isBlank())
		// managedAnswers.put(formAnswer.getKey(),
		// formAnswer.getManagedObject());

		formAnswerMap.put(a.getKey(), formAnswer);

		Map m = (Map) answersByQuestion.get(qid);
		if (m == null)
		{
			m = new HashMap();
			answersByQuestion.put(qid, m);
		}
		m.put(formAnswer.getKey(), formAnswer);
		return formAnswer;
	}

	public SecurityCredential getAccessUser()
	{
		if(this.formContext == null)
			return null;
		
		return formContext.getAccessUser();
	}

	public void setAccessUser(SecurityCredential user)
	{
		if(this.formContext == null)
			throw new IllegalStateException("formContext required");
		
		formContext.setAccessUser(user);
	}

	public User getCreateUser()
	{
		return createUser;
	}

	/**
	 * 
	 * @param id the question Id
	 * @return the form question object
	 */
	public FormQuestion findFormQuestion(Integer id)
	{
		FormQuestion formQuestion = (FormQuestion) formQuestionMap.get(id);
		if (formQuestion == null)
		{
			throw new IllegalArgumentException("id " + id
					+ " not found in keys " + formQuestionMap.keySet()
					+ " questionaire=" + questionaire);
		}

		formQuestion.setForm(this);

		return formQuestion;
	}// --------------------------------------------

	public FormQuestion findQuestionByID(Integer id)
	{
		return findFormQuestion(id);
	}// --------------------------------------------

	public FormQuestion findQuestionByID(int id)
	{
		return findFormQuestion(new Integer(id));
	}

	private Question findQuestion(Integer id)
	{
		if (id == null)
			throw new IllegalArgumentException("question id must be provided");
		Question q = questionaire.getQuestion(id);
		return q;
	}

	public Collection findQuestionsWithAttribute(String key, Object val)
	{
		if (key == null || val == null)
			throw new IllegalArgumentException("key and value are required");
		List l = questionaire.getQuestionsWithAttribute(key);
		List questions = new LinkedList();
		for (Iterator i = l.iterator(); i.hasNext();)
		{
			Question q = (Question) i.next();
			QuestionAttribute a = q.getAttribute(key);
			String s = (a != null && a.getValue() != null) ? a.getValue()
					.toString() : "";
			if (val.toString().equalsIgnoreCase(s))
				questions.add((FormQuestion) formQuestionMap.get(q
						.getQuestionId()));
		}
		return questions;
	}

	public Collection findColumnsWithAttribute(String key, Object val)
	{
		if (key == null || val == null)
			throw new IllegalArgumentException("key and value are required");
		List l = questionaire.getColumnsWithAttribute(key);
		List cols = new LinkedList();
		for (Iterator i = l.iterator(); i.hasNext();)
		{
			Object[] entry = (Object[]) i.next();
			Question q = (Question) entry[0];
			Column c = (Column) entry[1];
			QuestionAttribute a = c.getAttribute(key);
			String s = (a != null && a.getValue() != null) ? a.getValue()
					.toString() : "";
			if (val.toString().equalsIgnoreCase(s))
			{
				FormQuestion formQuestion = (FormQuestion) formQuestionMap
						.get(q.getQuestionId());
				FormColumn col = formQuestion.getFormColumn(c.getColNumber()
						.intValue());
				cols.add(col);
			}
		}
		return cols;
	}

	public Collection findQuestionsWithResponseTypePK(Integer responseTypeId)
	{
		if (responseTypeId == null)
			throw new IllegalArgumentException("responseTypeId is required");

		Collection l = questionaire
				.retrieveQuestionsWithResponseType(responseTypeId);
		Collection questions = toFormQuestionList(l);
		return questions;
	}

	/**
	 * 
	 * @return the form questions that have operations
	 */
	public Collection findQuestionsWithOperation()
	{
		Collection l = questionaire.retrieveQuestionsWithOperation();
		Collection questions = toFormQuestionList(l);
		return questions;
	}// --------------------------------------------

	public Collection findColumnsWithOperation()
	{
		Collection l = questionaire.retrieveColumnsWithOperation();
		Collection cols = toFormColumnList(l);
		return cols;
	}

	private Collection<FormQuestion> toFormQuestionList(Collection<Question> questions)
	{
		Collection<FormQuestion> l = new LinkedList<FormQuestion>();
		for (Iterator<Question> i = questions.iterator(); i.hasNext();)
		{
			Question q = (Question) i.next();
			l.add((FormQuestion) formQuestionMap.get(q.getQuestionId()));
		}
		return l;
	}

	private List toFormColumnList(Collection cols)
	{
		List l = new LinkedList();
		for (Iterator i = cols.iterator(); i.hasNext();)
		{
			Object[] entry = (Object[]) i.next();
			Question q = (Question) entry[0];
			Column col = (Column) entry[1];
			FormQuestion formQuestion = (FormQuestion) formQuestionMap.get(q
					.getQuestionId());
			l.add(formQuestion.getFormColumn(col.getColNumber().intValue()));
		}
		return l;
	}

	public Collection getFormQuestions()
	{
		if(this.formQuestionMap == null)
			return Collections.EMPTY_LIST;
		
		return formQuestionMap.values();
	}

	public Collection findAnswerList(Integer questionId)
	{
		Map m = (Map) answersByQuestion.get(questionId);
		if (m == null)
			return Collections.EMPTY_LIST;
		return m.values();
	}

	public FormAnswer retrieveAnswer(String key)
	{
		if(formAnswerMap == null)
			return null;
		
		return (FormAnswer) this.formAnswerMap.get(key);
	}

	// remove all references to the answer object
	public void remove(Answer answer)
	{
		Object key = answer.getKey();
		// managedAnswers.remove(key);
		formAnswerMap.remove(key);
		Map qa = (Map) answersByQuestion.get(answer.getQuestionId());
		if (qa != null)
			qa.remove(key);
	}

	public FormAnswer findAnswer(Integer questionId)
	{
		if (questionId == null)
			throw new IllegalArgumentException("question id must be provided");
		Collection l = findAnswerList(questionId);
		if (l.size() == 0)
			return null;
		return (FormAnswer) l.iterator().next();
	}

	/**
	 * Get answer for a given row and column position
	 * 
	 * @param questionId the question key
	 * @param row the row #
	 * @param col the column number
	 * @return the answer
	 */
	public FormAnswer findAnswer(Integer questionId, int row, int col)
	{
		if (questionId == null)
			throw new IllegalArgumentException("question id must be provided");
		if (row < 0)
			throw new IllegalArgumentException(
					"row number must be greater that or equal to 0");
		if (col < 0)
			throw new IllegalArgumentException(
					"column number must be greater that or equal to 0");

		Question question = findQuestion(questionId);
		if (question == null)
			throw new IllegalArgumentException("no question found with id "
					+ questionId);

		ResponseTable tbl = question.getResponseTable();
		if (tbl == null)
			throw new IllegalArgumentException("question id " + questionId
					+ " does not have a response table");

		String key = Answer.toKey(getFormId(), questionId,
				tbl.getResponseTableId(), new Integer(col), new Integer(row));
		return (FormAnswer) formAnswerMap.get(key);
	}// --------------------------------------------

	public Collection<FormQuestion> findQuestionsBySection(Integer sectionNumber)
	{
		
		Collection<Question> questions = questionaire.getQuestionsBySection(sectionNumber);
		if(questions == null || questions.isEmpty())
			return null;
	
		return this.toFormQuestionList(questions);
		//Collection<FormQuestion> questions = this.toFormQuestionList(l);
		//return questions;
	}

	public FormSection findSectionByNumber(String sectionNumber)
			throws NoDataFoundException
	{
		Section s = questionaire.getSection(new Integer(sectionNumber));
		if (s == null)
		{
			throw new NoDataFoundException("Section " + sectionNumber + " "
					+ questionaire.getSectionMap().keySet());
		}
		return new FormSection(this, s);
	}// --------------------------------------------

	public List getSections()
	{
		if(questionaire == null)
			return Collections.EMPTY_LIST;
		
		Collection sections = questionaire.retrieveSections();
		List l = new LinkedList();
		for (Iterator i = sections.iterator(); i.hasNext();)
		{
			l.add(new FormSection(this, (Section) i.next()));
		}
		return l;
	}

	public void deleteAnswer(FormAnswer a)
	{
		remove(a);
	}

	public void delete(FormAnswer a)
	{
		deleteAnswer(a);
	}

	public void unDelete(FormAnswer aAnswer)
	{
		if (aAnswer == null)
			throw new IllegalArgumentException(
					"aAnswer required in GCSMForm.unDelete");
		/*
		 * if(aAnswer.isWithinTable()) { unDeleteRow(aAnswer); } else {
		 */
		aAnswer.unDelete();
		/*
		 * //add deleted answer addAnswer(aAnswer); }
		 */
	}

	/*
	 * public void unDeleteRow(FormAnswer answer) { if (answer == null) return;
	 * answer.getFormRow().unDelete();
	 * }//--------------------------------------------
	 */

	public boolean isCompleted()
	{
		return FormGuide.isCompleted(this);
	}

	public boolean isSubmitted()
	{
		return FormGuide.isSubmitted(this);
	}

	public void setEdited(boolean aEdited)
	{
		this.addProperty("edited", new Boolean(aEdited));
	}// --------------------------------------------

	/**
	 * Copy form information to current instance
	 *
	 * @see nyla.solutions.formInjection.data.FormComponent#copy(org.solutions.data.Copier)
	 */
	public void copy(Copier aFrom)
	{
		Form that = (Form) aFrom;
		formContext = that.getFormContext();
		this.questionaire = that.getQuestionaire();

		this.answersByQuestion = that.answersByQuestion;
		this.formAnswerMap = that.formAnswerMap;
		this.formId = that.formId;
		this.formProps = that.formProps;
		this.formQuestionMap = that.formQuestionMap;

		this.formStatusId = that.formStatusId;
		this.formType = that.formType;
		this.createUser = that.createUser;

	}// --------------------------------------------

	/**
	 * Modified to perform deep clone
	 */
	public Object clone() throws CloneNotSupportedException
	{
		Form newForm = new Application();
		newForm.copy(this);
		return newForm;
	}

	public String getName()
	{
		return getFormType().getName();
	}

	public String toString()
	{
		// return form.toString();
		// TODO: someone's dumping this a million times to log
		// those should be removed, and the above line restored
		return String.valueOf(getFormId());
	}

	public void setStatusPK(int formStatusId)
	{
		setFormStatusId(Integer.valueOf(formStatusId));
	}

	public FormStatus getFormStatus()
	{
		return FormHelper.getFormStatus(getFormStatusId());
	}

	public FormStatus getStatus()
	{
		return getFormStatus();
	}

	public String getStatusName()
	{
		FormStatus formStatus = getFormStatus();
		
		if(formStatus == null)
			return formStatus.getName();
		return null;
	}// --------------------------------------------------------

	public void setFormType(FormType formType)
	{
		this.formType = formType;
	}// --------------------------------------------

	public FormType getFormType()
	{
		// return FormHelper.getFormType(getFormTypeCode());
		return formType;
	}

	public String findPropertyValue(String key)
	{
		FormProperty p = findProperty(key);
		if (p != null)
			return p.getStringValue();
		return null;
	}

	// ---------------------------------------
	// for retrofit
	// ---------------------------------------
	public Attribute retrieveAttribute(Comparable aKey)
	{
		if(this.formContext == null)
			return null;
		
		return formContext.getAttribute(aKey);
	}

	public Object retrieveAttributeValue(Comparable key)
	{
		Attribute a = retrieveAttribute(key);
		if (a == null)
			return null;
		return a.getValue();
	}

	public void addAttribute(Comparable key, Serializable val)
	{
		if(this.formContext == null)
			throw new IllegalStateException("this.formContext required");
		
		formContext.addAttribute(key, val);

	}

	/**
	 * Calls this.attributeMap.remove(aAttributeName)
	 * 
	 * @param aAttributeName the attribute to remove
	 */
	public void removeAttribute(String aAttributeName)
	{
		if(this.formContext == null)
			throw new IllegalStateException("this.formContext required");
		
		formContext.removeAttribute(aAttributeName);

	}// --------------------------------------------

	public Collection findAnswersByQuestionAndColumnWithAnswerValue(
			int aQuestionPK, int aTablePK, int aColumnNumber,
			String aAnswerValue)
	{
		if (aAnswerValue == null)
			throw new IllegalArgumentException(
					"aAnswer required in FormGuide.findAnswersByQuestionAndColumnWithAnswer");

		Collection answers = findAnswerList(new Integer(aQuestionPK));
		List l = new LinkedList();
		for (Iterator i = answers.iterator(); i.hasNext();)
		{
			Answer a = (Answer) i.next();
			if (a.getResponseTableId() == null)
				return Collections.EMPTY_LIST;
			if (a.getCol().intValue() == aColumnNumber)
			{
				for (Iterator ii = a.getAnswerProps().values().iterator(); ii
						.hasNext();)
				{
					AnswerProperty property = (AnswerProperty) ii.next();
					String s = property.getValue() != null ? property
							.getValue().toString() : "";
					if (aAnswerValue.equalsIgnoreCase(s))
						l.add(a);
				}
			}
		}
		return answers;
	}

	// ---------------------------------------------
	// for retrofit
	// ---------------------------------------------
	public Form retrieveForm()
	{
		return getForm();
	}

	public int getStatusPK()
	{
		return getFormStatusId() != null ? getFormStatusId().intValue() : -1;
	}

	public String retrievePropertyValue(String name)
	{
		FormProperty property = findProperty(name);
		if (property != null)
			return property.getStringValue();
		return null;
	}

	public void accept(FormVisitor visitor)
	{
		if(visitor == null)
			return;
		
		visitor.visit(this);
		if (visitor.visitQuestions())
		{
			List l = new LinkedList(formQuestionMap.values());
			for (Iterator i = l.iterator(); i.hasNext();)
			{
				FormQuestion q = (FormQuestion) i.next();
				visitor.visit(q);
			}
		}
		if (visitor.visitAnswers())
		{
			List l = new LinkedList(formAnswerMap.values());
			for (Iterator i = l.iterator(); i.hasNext();)
			{
				FormAnswer a = (FormAnswer) i.next();
				visitor.visit(a);
			}
		}
	}

	public FormAnswer findAnswerByColumnAndRow(int questionId, int row, int col)
	{
		return findAnswer(new Integer(questionId), row, col);
	}

	public boolean isEditable()
	{
		return !this.isReadOnly();
	}// --------------------------------------------

	public boolean isReadOnly()
	{
		return false;
	}// --------------------------------------------

	public FormType getType()
	{
		return getFormType();
	}

	public void setStatusPK(Integer statusPK)
	{
		setFormStatusId(statusPK);
	}

	public void setOriginatorPK(Integer pk)
	{
		setCreateUserID(pk);
	}

	public Integer getFormId()
	{
		return formId;
	}

	public void setFormId(Integer formId)
	{
		this.formId = Data.NULL;
		this.setPrimaryKey(formId);
	}

	public Integer getFormStatusId()
	{
		return formStatusId;
	}

	public void setFormStatusId(Integer formStatusId)
	{
		this.formStatusId = formStatusId;
	}

	public String getFormTypeCode()
	{
		if (this.formType == null)
			return null;

		return formType.getFormTypeCode();
	}// --------------------------------------------

	public void setFormTypeCode(String formTypeCode)
	{
		if (this.formType != null)
		{
			this.formType.setFormTypeCode(formTypeCode);
		}
	}// --------------------------------------------

	public Map getFormProps()
	{
		return formProps;
	}// --------------------------------------------

	public void setFormProps(Map formProps)
	{
		if (formProps == null)
			return;
		
		//TODO:  this.formProps.clear();
		for (Iterator i = formProps.values().iterator(); i.hasNext();)
		{
			FormProperty prop = (FormProperty) i.next();
			addProperty(prop);
		}
	}// --------------------------------------------

	public void setProperties(Map formProperties)
	{

	}// --------------------------------------------

	public void setProperties(Collection formProps)
	{
		if(formProps == null)
			return;
		
					
		if (formProps == null || formProps.isEmpty())
			this.formProps.clear();
		for (Iterator i = formProps.iterator(); i.hasNext();)
		{
			FormProperty prop = (FormProperty) i.next();
			addProperty(prop);
		}
	}// --------------------------------------------

	/**
	 * 
	 * @param name the form property name
	 * @param value the form property value
	 */
	public void addProperty(String name, Object value)
	{
		FormProperty formProperty = new FormProperty();
		formProperty.setValue(value);
		formProperty.setName(name);

		formProperty.setFormId(this.getFormId());

		addProperty(formProperty);
	}// --------------------------------------------

	/**
	 * 
	 * @param property the form property to add
	 */
	public void addProperty(FormProperty property)
	{
		if (formProps == null)
			formProps = new HashMap();
		if (property != null)
		{
			property.setFormId(getFormId());
			formProps.put(property.getName(), property);
		}
	}

	public FormProperty findProperty(String key)
	{
		if(this.formProps == null)
			return null;
		
		return (FormProperty) formProps.get(key);
	}

	public FormProperty removeProperty(String key)
	{
		if(this.formProps == null)
			return null;

		
		return (FormProperty) formProps.remove(key);
	}

	public String getText()
	{
		return getFormId() != null ? String.valueOf(getFormId()) : "";
	}

	/**
	 * @return the answersByQuestion
	 */
	public Map getAnswersByQuestion()
	{
		return answersByQuestion;
	}

	/**
	 * @param answersByQuestion the answersByQuestion to set
	 */
	public void setAnswersByQuestion(Map answersByQuestion)
	{
		this.answersByQuestion = answersByQuestion;
	}

	/**
	 * @return the formQuestionMap
	 */
	public Map getFormQuestionMap()
	{
		return formQuestionMap;
	}

	/**
	 * @param formQuestionMap the formQuestionMap to set
	 */
	public void setFormQuestionMap(Map formQuestionMap)
	{
		this.formQuestionMap = formQuestionMap;
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
	 * @param createUser the createUser to set
	 */
	public void setCreateUser(User createUser)
	{
		this.createUser = createUser;
	}

	/**
	 * @param formAnswerMap the formAnswerMap to set
	 */
	public void setFormAnswerMap(Map formAnswerMap)
	{
		this.formAnswerMap = formAnswerMap;
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

	/**
	 * @param primaryKey the primaryKey to set
	 */
	public void setPrimaryKey(int primaryKey)
	{
		this.primaryKey = primaryKey;
	}


    @Override
	public void setUpdateDate(Date date)
	{
		if(date == null)
			this.updateDate = null;
		else
			this.updateDate = new Timestamp(date.getTime());
		
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	static final long serialVersionUID = Form.class.getName().hashCode();
	private User createUser;
	private Questionaire questionaire;
	private Map answersByQuestion = new HashMap();
	private Map formAnswerMap = new HashMap();
	private Map formQuestionMap = new HashMap();
	private FormContext formContext = new FormContext();
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
	private String name;


	
}
