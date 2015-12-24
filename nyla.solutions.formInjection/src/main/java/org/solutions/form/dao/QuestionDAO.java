package org.solutions.form.dao;


import java.sql.SQLException;
import java.util.Collection;

import nyla.solutions.global.exception.DuplicateRowException;
import nyla.solutions.global.exception.NoDataFoundException;
import nyla.solutions.global.patterns.Disposable;

import org.solutions.form.data.AttributeFacts;
import org.solutions.form.data.FormType;
import org.solutions.form.data.Question;
import org.solutions.form.data.QuestionAttribute;
import org.solutions.form.data.Questionaire;
import org.solutions.form.data.ResponseTable;
import org.solutions.form.data.ResponseType;


public interface QuestionDAO
extends Disposable
{

   /**
    * 
    * @see org.solutions.form.QuestionDAO#saveQuestionAttribute(org.solutions.form.data.QuestionAttribute)
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
    * @see org.solutions.form.QuestionDAO#insert(org.solutions.form.data.AttributeFacts)
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