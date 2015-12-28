package nyla.solutions.formInjection.dao;


import java.sql.SQLException;
import java.util.Collection;

import nyla.solutions.formInjection.data.AttributeFacts;
import nyla.solutions.formInjection.data.FormType;
import nyla.solutions.formInjection.data.Question;
import nyla.solutions.formInjection.data.QuestionAttribute;
import nyla.solutions.formInjection.data.Questionaire;
import nyla.solutions.formInjection.data.ResponseTable;
import nyla.solutions.formInjection.data.ResponseType;
import nyla.solutions.global.exception.DuplicateRowException;
import nyla.solutions.global.exception.NoDataFoundException;
import nyla.solutions.global.patterns.Disposable;


public interface QuestionDAO
extends Disposable
{

   /**
    * 
    * @see org.solutions.form.QuestionDAO#saveQuestionAttribute(nyla.solutions.formInjection.data.QuestionAttribute)
    */
   public QuestionAttribute saveQuestionAttribute(QuestionAttribute attribute);

   /**
    * 
    * @param aQuestion the question to save
    * @return the saved question
    */
   public Question saveQuestion(Question aQuestion);
   
   /**
    * 
    * @param aResponseType the response type to save
    * @return the response type to save
    */
   public ResponseType saveResponseType(ResponseType aResponseType);
   
   /**
    * 
    * @param aResponseTable the response table
    * @return the saved response table
    */
   public ResponseTable saveResponseTable(ResponseTable aResponseTable);
   
   /**
    * 
    * @see org.solutions.form.QuestionDAO#selectQuestionAttributeByPK(java.lang.Integer, java.lang.Integer, java.lang.String)
    */
   public QuestionAttribute selectQuestionAttributeByPK(Integer formTypeId,
                                                        Integer questionId,
                                                        String attrName) throws SQLException, NoDataFoundException;

   /**
    * 
    * @see org.solutions.form.QuestionDAO#insert(nyla.solutions.formInjection.data.AttributeFacts)
    */
   public AttributeFacts insert(AttributeFacts aAttributeFacts) throws SQLException, DuplicateRowException;//--------------------------------------------

   /**
    * 
    * @see org.solutions.form.QuestionDAO#selectSectionsByFormTypeCode(String)
    */
   public Collection selectSectionsByFormTypeCode(String aFormTypeCode) throws NoDataFoundException, SQLException;//--------------------------------------------

   /**
    * 
    * @see org.solutions.form.QuestionDAO#selectQuestionsByFormTypeCode(java.lang.String)
    */
   public Collection selectQuestionsByFormTypeCode(String aFormTypeName) throws NoDataFoundException, SQLException;//--------------------------------------------

   /**
    * 
    * @see org.solutions.form.QuestionDAO#selectFormTypeByCode(java.lang.String)
    */
   public FormType selectFormTypeByCode(String aFormTypeName) throws SQLException, NoDataFoundException;//--------------------------------------------

   /**
    * 
    * @see org.solutions.form.QuestionDAO#constructQuestioniareByFormTypeCode(java.lang.String)
    */
   public Questionaire constructQuestioniareByFormTypeCode(String aFormTypeName) throws NoDataFoundException, SQLException;//--------------------------------------------


   /***
    * Commit a transaction
    *
    */
   public void commit();
   
   /***
    * Rollback a transaction
    *
    */
   public void rollback();
}