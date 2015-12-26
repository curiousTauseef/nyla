package org.solutions.form.dao.hibernate;

import java.sql.SQLException;
import java.util.Collection;

import nyla.solutions.global.exception.DuplicateRowException;
import nyla.solutions.global.exception.NoDataFoundException;

import org.solutions.dao.hibernate.HibernateDAO;
import org.solutions.form.dao.QuestionDAO;
import org.solutions.form.data.AttributeFacts;
import org.solutions.form.data.FormType;
import org.solutions.form.data.Question;
import org.solutions.form.data.QuestionAttribute;
import org.solutions.form.data.Questionaire;
import org.solutions.form.data.ResponseTable;
import org.solutions.form.data.ResponseType;

public class QuestionHibernateDAO 
extends HibernateDAO implements QuestionDAO
{

   public QuestionHibernateDAO()
   {
      super();
   }// --------------------------------------------
   /**
    * 
    * @param aQuestion the question to save
    * @return the saved question
    */
   public Question saveQuestion(Question aQuestion)
   {
      super.save(aQuestion);
      
      return aQuestion;
   }// --------------------------------------------

   public QuestionAttribute saveQuestionAttribute(QuestionAttribute attribute)
   {
      return null;
   }// --------------------------------------------

   public QuestionAttribute selectQuestionAttributeByPK(Integer formTypeId,
                                                        Integer questionId,
                                                        String attrName) throws SQLException, NoDataFoundException
   {
      return null;
   }//----------------------------------------
   /**
    * 
    * 
    * @see org.solutions.form.dao.QuestionDAO#insert(org.solutions.form.data.AttributeFacts)
    */
   public AttributeFacts insert(AttributeFacts aAttributeFacts) throws SQLException, DuplicateRowException
   {
      return null; 
   }//----------------------------------------
   /**
    * 
    * 
    * @see org.solutions.form.dao.QuestionDAO#selectQuestionsByFormTypeCode(java.lang.String)
    */
   public Collection selectQuestionsByFormTypeCode(String aFormTypeCode) throws NoDataFoundException, SQLException
   {
      return this.selectObjectsByProperty(Question.class, "formTypeCode", aFormTypeCode);
   }// --------------------------------------------

   public FormType selectFormTypeByCode(String aFormTypeName) throws SQLException, NoDataFoundException
   {
      // TODO Auto-generated method stub
      return null;
      //----------------------------------------
   }

   public Questionaire constructQuestioniareByFormTypeCode(String aFormTypeName) throws NoDataFoundException, SQLException
   {
      // TODO Auto-generated method stub
      return null;
      //----------------------------------------
   }

   public Questionaire constructQuestioniareByFormTypeCode(int aFormTypePK) throws NoDataFoundException, SQLException
   {
      // TODO Auto-generated method stub
      return null;
      //----------------------------------------
   }

   /**
    * 
    * @see org.solutions.form.dao.QuestionDAO#selectSectionsByFormTypeCode(java.lang.String)
    */
   public Collection selectSectionsByFormTypeCode(String aFormTypeCode) throws NoDataFoundException, SQLException
   {
      // TODO Auto-generated method stub
      return null;
   }// --------------------------------------------
   /**
    * 
    * @see org.solutions.form.dao.QuestionDAO#saveResponseTable(org.solutions.form.data.ResponseTable)
    */
   public ResponseTable saveResponseTable(ResponseTable aResponseTable)
   {
      // TODO Auto-generated method stub
      return null;
   }
   /**
    * 
    * @see org.solutions.form.dao.QuestionDAO#saveResponseType(org.solutions.form.data.ResponseType)
    */
   public ResponseType saveResponseType(ResponseType aResponseType)
   {
      // TODO Auto-generated method stub
      return null;
   }// --------------------------------------------


}
