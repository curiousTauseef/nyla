package nyla.solutions.formInjection.dao.hibernate;

import java.sql.SQLException;
import java.util.Collection;

import nyla.solutions.dao.hibernate.HibernateDAO;
import nyla.solutions.formInjection.dao.QuestionDAO;
import nyla.solutions.formInjection.data.AttributeFacts;
import nyla.solutions.formInjection.data.FormType;
import nyla.solutions.formInjection.data.Question;
import nyla.solutions.formInjection.data.QuestionAttribute;
import nyla.solutions.formInjection.data.Questionaire;
import nyla.solutions.formInjection.data.ResponseTable;
import nyla.solutions.formInjection.data.ResponseType;
import nyla.solutions.global.exception.DuplicateRowException;
import nyla.solutions.global.exception.NoDataFoundException;

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
    * @see nyla.solutions.formInjection.dao.QuestionDAO#insert(nyla.solutions.formInjection.data.AttributeFacts)
    */
   public AttributeFacts insert(AttributeFacts aAttributeFacts) throws SQLException, DuplicateRowException
   {
      return null; 
   }//----------------------------------------
   /**
    * 
    * 
    * @see nyla.solutions.formInjection.dao.QuestionDAO#selectQuestionsByFormTypeCode(java.lang.String)
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
    * @see nyla.solutions.formInjection.dao.QuestionDAO#selectSectionsByFormTypeCode(java.lang.String)
    */
   public Collection selectSectionsByFormTypeCode(String aFormTypeCode) throws NoDataFoundException, SQLException
   {
      // TODO Auto-generated method stub
      return null;
   }// --------------------------------------------
   /**
    * 
    * @see nyla.solutions.formInjection.dao.QuestionDAO#saveResponseTable(nyla.solutions.formInjection.data.ResponseTable)
    */
   public ResponseTable saveResponseTable(ResponseTable aResponseTable)
   {
      // TODO Auto-generated method stub
      return null;
   }
   /**
    * 
    * @see nyla.solutions.formInjection.dao.QuestionDAO#saveResponseType(nyla.solutions.formInjection.data.ResponseType)
    */
   public ResponseType saveResponseType(ResponseType aResponseType)
   {
      // TODO Auto-generated method stub
      return null;
   }// --------------------------------------------


}
