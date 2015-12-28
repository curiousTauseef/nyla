package nyla.solutions.formInjection.dao.jdo;
import java.sql.SQLException;
import java.util.Collection;

import nyla.solutions.dao.jdo.JDODAO;
import nyla.solutions.dao.jdo.JDOQueryBuilder;
import nyla.solutions.formInjection.dao.QuestionDAO;
import nyla.solutions.formInjection.data.AttributeFacts;
import nyla.solutions.formInjection.data.FormType;
import nyla.solutions.formInjection.data.ManagedQuestionAttribute;
import nyla.solutions.formInjection.data.Question;
import nyla.solutions.formInjection.data.QuestionAttribute;
import nyla.solutions.formInjection.data.Questionaire;
import nyla.solutions.formInjection.data.ResponseTable;
import nyla.solutions.formInjection.data.ResponseType;
import nyla.solutions.formInjection.data.Section;
import nyla.solutions.global.data.Data;
import nyla.solutions.global.exception.DuplicateRowException;
import nyla.solutions.global.exception.NoDataFoundException;
import nyla.solutions.global.operations.logging.Log;
import nyla.solutions.global.security.data.SecurityCredential;
import nyla.solutions.global.util.Debugger;


/**
 * <pre>
 * QuestionDAO is a data access object for Question data management
 * </pre> 
 * @author Gregory Green
 * @version 1.0
 */
public class QuestionJDODAO extends JDODAO implements  QuestionDAO
{
   /**
    * Constructor for QuestionDAO initalizes internal 
    * data settings.
    * 
    */
   protected QuestionJDODAO()
   {
      super();
   }//--------------------------------------------
   /**
    * Constructor for QuestionDAO initalizes internal 
    * data settings.
    * @param aUser
    */
   protected QuestionJDODAO(SecurityCredential aUser)
   {
      super(aUser);
   }//--------------------------------------------
   /**
    * Constructor for QuestionDAO initalizes internal 
    * data settings.
    * @param aDAO
    */
   protected QuestionJDODAO(JDODAO aDAO)
   {
      super(aDAO);
   }//--------------------------------------------
   /**
    * 
    * @return new instance of Question DAO
    */
   public static QuestionDAO getQuestionDAOInstance()
   {
      return new QuestionJDODAO();
   }//--------------------------------------------   
   /**
    * 
    * @return new instance of Question DAO
    */
   public static QuestionDAO getQuestionDAOInstance(SecurityCredential aUser)
   {
      return new QuestionJDODAO(aUser);
   }//--------------------------------------------
   
   /**
    * 
    * @see nyla.solutions.formInjection.dao.QuestionDAO#saveQuestionAttribute(nyla.solutions.formInjection.data.QuestionAttribute)
    */
   public QuestionAttribute saveQuestionAttribute(QuestionAttribute attribute) {
       Integer formTypeId = attribute.getFormTypeId();
       Integer questionId = attribute.getQuestionId();
       String name = attribute.getKey().toString();
       QuestionAttribute toSave = attribute;
       if (formTypeId != null && questionId != null) {
           try {
               toSave = selectQuestionAttributeByPK(formTypeId, questionId, name);
               toSave.copy(attribute);
           }
           catch (Exception e) {
           	logger.error(e);
           }
       }
       logger.debug("Calling make persistant");
       //Debugger.dump(toSave);
       this.makePersistent(toSave);
       logger.debug("Called make persistant");
       return toSave;
   }
   /**
    * 
    * @see nyla.solutions.formInjection.dao.QuestionDAO#selectQuestionAttributeByPK(java.lang.Integer, java.lang.Integer, java.lang.String)
    */
   public QuestionAttribute selectQuestionAttributeByPK(Integer formTypeId, Integer questionId, String attrName) throws SQLException, NoDataFoundException {
       JDOQueryBuilder query = this.createQueryBuilder(ManagedQuestionAttribute.class);      
       JDOQueryBuilder pkQuery = query.getColumn("formTypeId").equal(formTypeId)
       					.and(query.getColumn("questionId").equal(questionId))
       					.and(query.getColumn("key").equalsIgnoreCase(attrName));
       QuestionAttribute attr = (QuestionAttribute) ((Collection) super.select(pkQuery)).iterator().next();
       return attr;
   }
   /**
    * 
    * @see nyla.solutions.formInjection.dao.QuestionDAO#insert(nyla.solutions.formInjection.data.AttributeFacts)
    */
   public AttributeFacts insert(AttributeFacts aAttributeFacts)
   throws SQLException, DuplicateRowException
   {
      if (aAttributeFacts == null)
         throw new IllegalArgumentException(
         "aAttributeFacts required in QuestionDAO.insert");
      
      super.insert(aAttributeFacts);
      
      return aAttributeFacts;
   }//--------------------------------------------

   /**
    * 
    * @see nyla.solutions.formInjection.dao.QuestionDAO#selectQuestionsByFormTypePK(int)
    */
   public Collection selectQuestionsByFormTypePK(int aFormTypePK)
   throws NoDataFoundException, SQLException
   {
      JDOQueryBuilder questionQuery = this.createQueryBuilder(Question.class);      
      JDOQueryBuilder formTypeQuery = questionQuery.getColumn("formTypeId").equal(aFormTypePK);
     
      return (Collection)super.select(formTypeQuery.and(questionQuery.getColumn("deletedCode").equal(Data.NO)));
      //return (Collection)super.select(questionQuery);
   }//--------------------------------------------
   /**
    * 
    * @see nyla.solutions.formInjection.dao.QuestionDAO#selectSectionsByFormTypePK(int)
    */
   public Collection selectSectionsByFormTypeCode(String aFormTypeCode)
   throws NoDataFoundException, SQLException
   {
      JDOQueryBuilder sectionQuery = this.createQueryBuilder(Section.class);
      JDOQueryBuilder formTypeQuery = sectionQuery.getColumn("formTypeCode").equal(aFormTypeCode);
      return (Collection)super.select(formTypeQuery.and(sectionQuery.getColumn("deletedCode").equal(Data.NO)));
   }//--------------------------------------------
   /**
    * 
    * @see nyla.solutions.formInjection.dao.QuestionDAO#selectQuestionsByFormTypeCode(java.lang.String)
    */
   public Collection selectQuestionsByFormTypeCode(String aFormTypeName)
   throws NoDataFoundException, SQLException   
   {
            
      return selectQuestionsByFormTypePK(selectFormTypeByCode(aFormTypeName).getPrimaryKey());      
   }//--------------------------------------------
   /**
    * 
    * @see nyla.solutions.formInjection.dao.QuestionDAO#selectFormTypeByCode(java.lang.String)
    */
   public FormType selectFormTypeByCode(String aFormTypeName)
   throws SQLException,NoDataFoundException
   {
      if (aFormTypeName == null)
         throw new IllegalArgumentException("aFormTypeName required in FormDAO");

      JDOQueryBuilder query = this.createQueryBuilder(FormType.class);
      JDOQueryBuilder notDeleted = query.getColumn("deletedCode")
      .equal(Data.NO);
      JDOQueryBuilder nameQuery = query.getColumn("name").trim().toUpperCase()
      .equal(aFormTypeName.toUpperCase());

     

      query = this.createMaxFormTypeQuery(nameQuery);
      
      return (FormType) ((Collection) this
      .select(nameQuery.and(notDeleted))).iterator().next();
   }//--------------------------------------------
   /**
    * @param aFormTypeQuery
    * @return
    */
   protected JDOQueryBuilder createMaxFormTypeQuery(JDOQueryBuilder aFormTypeQuery)
   {
      //Add max form type version query
      StringBuffer sql = new StringBuffer(" FORM_TYPE_NM||FORM_TYPE_VERSION_NBR ")                               
      .append(" IN ( select ft.FORM_TYPE_NM||MAX(ft.FORM_TYPE_VERSION_NBR)   ") 
      .append("      from form_type_tbl ft   ")
        .append("    group by ft.form_type_nm)");
      
      JDOQueryBuilder maxVersion = aFormTypeQuery.appendSQL(sql.toString());
      return maxVersion;
   }//--------------------------------------------
   /**
    * 
    * @see nyla.solutions.formInjection.dao.QuestionDAO#constructQuestioniareByFormTypeCode(java.lang.String)
    */
   public Questionaire constructQuestioniareByFormTypeCode(String aFormTypeName)
   throws NoDataFoundException, SQLException
   {
       FormType type = this.selectFormTypeByCode(aFormTypeName);
      return new Questionaire(type.getFormTypeId(), selectQuestionsByFormTypeCode(aFormTypeName));
   }//--------------------------------------------
   /**
    * 
    * @see nyla.solutions.formInjection.dao.QuestionDAO#selectFormTypeByPK(int)
    */
   public FormType selectFormTypeByPK(int aFormTypeID)
   throws NoDataFoundException, SQLException
   {
      if (aFormTypeID < 1)
         throw new IllegalArgumentException(
         "aFormTypeID < 1 required in FormDAO.selectFormTypeByPK");
      
      JDOQueryBuilder formTypeQuery = this.createQueryBuilder(FormType.class);
      return (FormType)((Collection)super.select(formTypeQuery.getColumn("formTypeId").equal(aFormTypeID))).iterator().next();
   }//--------------------------------------------   
   /**
    * 
    * @see nyla.solutions.formInjection.dao.QuestionDAO#saveQuestion(nyla.solutions.formInjection.data.Question)
    */
   public Question saveQuestion(Question aQuestion)
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
   }
   
   protected Log logger = Debugger.getLog(getClass());

   
}