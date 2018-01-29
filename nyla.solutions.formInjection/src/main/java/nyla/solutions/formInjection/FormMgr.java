package nyla.solutions.formInjection;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.Collection;

import nyla.solutions.formInjection.dao.BreDAO;
import nyla.solutions.formInjection.dao.FormDAO;
import nyla.solutions.formInjection.dao.FormDAOFactory;
import nyla.solutions.formInjection.dao.QuestionDAO;
import nyla.solutions.formInjection.data.Application;
import nyla.solutions.formInjection.data.Form;
import nyla.solutions.formInjection.data.FormStatus;
import nyla.solutions.formInjection.data.FormType;
import nyla.solutions.formInjection.data.Questionaire;
import nyla.solutions.formInjection.exception.BreException;
import nyla.solutions.global.exception.NoDataFoundException;
import nyla.solutions.global.exception.SummaryException;
import nyla.solutions.global.exception.SystemException;
import nyla.solutions.global.operations.logging.Log;
import nyla.solutions.global.security.data.SecurityCredential;
import nyla.solutions.global.util.Debugger;

import org.apache.log4j.LogManager;import org.apache.log4j.Logger;


public class FormMgr implements FormService
{

   /**
    * 
    *
    * @see nyla.solutions.formInjection.FormService#retrieveNewForm(java.lang.String)
    */
   public Form retrieveNewForm(String formTypeCode) throws RemoteException
   {
      if (formTypeCode == null || formTypeCode.length() == 0)
         throw new IllegalArgumentException(
         "formTypeCode required in retrieveNewForm");
      
      FormDAO dao = null;
      try
      {
         dao = FormDAOFactory.createFormDAO();
         Questionaire  questionaire = dao.constructQuestioniareByFormTypeCode(formTypeCode);
                  
         
         FormType formType = dao.selectFormTypeByCode(formTypeCode);
         
         Form form = new Form();
         form.setQuestionaire(questionaire);
         form.setFormType(formType);
         
         BRE bre = dao.constructBRE(form);
         
         if(bre != null)
         {
            //auto file the dynamic questions and answer default answers
            bre.autofill(form);
         }
         
         return form;
         
      }
      catch(Exception e)
      {
         throw new SystemException(Debugger.stackTrace(e));
      }
      finally
      {
         if(dao != null)
            dao.dispose();
      }
      
   }//--------------------------------------------
   /**
    * Retrieve form by its unique ID
    * @param aFormID the form ID
    * @param aUser the user
    * @return form with ID
    * @throws Exception
    */
   public Form retrieveForm(Integer aFormID, String formTypeCode, SecurityCredential aUser)
   throws NoDataFoundException, BreException
   {
      if (aFormID == null)
         throw new IllegalArgumentException(
         "aFormID required in FormBean.retrieveForm");
      
      FormDAO dao = null;
      Form form = null;
      BRE bre = null;
      try
      {
         dao = FormDAOFactory.createFormDAO();
         
         form = dao.selectFormByPK(aFormID.intValue(),formTypeCode);         
         
         form.addAttribute(FormGuide.EVENT_ATTRIB_NM, "retrieveForm");
         
         //apply business rules
         bre = dao.constructBRE(form);
         
         dao.commit();
      }
      catch(SQLException e)
      {
         if(dao != null) 
            dao.rollback();
         
         throw new SystemException(Debugger.stackTrace(e));
      }
      finally
      {
         if(dao != null)
            dao.dispose();
      }
      
      bre.applyRules(form);
      return form;
   }//--------------------------------------------
   
   /**
    * Insert form information into the database
    * @param aForm the form to create
    * @return created form
    * @throws SystemException
    */
   public Form create(Form aForm)
   throws SystemException
   {
      if (aForm == null)
         throw new IllegalArgumentException(
         "aForm required in FormBean.createForm");
      
      if (aForm.getFormTypeCode() == null || aForm.getFormTypeCode().length() == 0)
         throw new IllegalArgumentException("Valid aForm.getFormTypeCode() > 0 required in FormBean.create");
      
      FormDAO dao = null;
      BRE bre = null;
      
      try
      {
         dao = FormDAOFactory.createFormDAO();
         
         if(!aForm.hasQuestionaire())
            aForm.setQuestionaire(dao.constructQuestioniareByFormTypeCode(aForm.getFormTypeCode()));
         
         
         //Set event for registered operations
         aForm.addAttribute(FormGuide.EVENT_ATTRIB_NM, "create");
         
         bre = dao.constructBRE(aForm);
         
         try
         {
            bre.applyRules(aForm);
         }
         catch(Exception e)
         {
            logger.warn(e);
         }
         
         
         //dao.setAutoCommit(false);
         aForm = dao.insertForm(aForm);
         dao.commit();
         
         return aForm;
         //dao.commit();
      }
      catch(Exception e)
      {
         if(dao != null) 
            dao.rollback();         
         throw new SystemException(Debugger.stackTrace(e));
      }
      finally
      {
         if(dao != null)
            dao.dispose();
      }
      
      
   }//--------------------------------------------
   /**
    * Edit one or more forms
    * @param aForms list of forms to edit
    * @return edited from
    * @throws SystemException
    */
   public Form []  edits(Form[] aForms, SecurityCredential aUser)
   throws SystemException
   {
      if (aForms == null)
         throw new IllegalArgumentException("aForm required in FormBean.edit");
      
      SummaryException exceptions = null;
           
      
      Form[] gForms = new Form[aForms.length];
      
      for (int i = 0; i < aForms.length; i++)
      {
         try
         {
            
            gForms[i] = edit(aForms[i]);
         }
         catch(Exception e)
         {
            if(exceptions == null)
               exceptions = new SummaryException();
            
            exceptions.addException(e);
         }
      }
      
      if(exceptions != null)
      {
         throw new SystemException(exceptions.toString());
      }
       
      return gForms;
   }//--------------------------------------------
   /**
    * 
    * @param aForm the form to edit
    * @return edited from
    * @throws SystemException
    */
   public Form edit(Form aForm)
   throws SystemException
   {
      
      if (aForm == null)
         throw new IllegalArgumentException(
         "aForm required in FormBean.editForm");
      
      if (aForm.isNew())
         throw new IllegalArgumentException("aForm PK is required in FormBean.edit");
      
      
      logger.debug("editing form "+aForm.getPrimaryKey());
      
      //Set event for registered operations
      aForm.addAttribute(FormGuide.EVENT_ATTRIB_NM, "edit");
      logger.debug("1");

      try
      {        
         logger.debug("2");
         aForm = this.applyBusinessRules(aForm);
         logger.debug("3");
      }
      catch(Exception e)
      {
         logger.debug("WARNING");
         logger.warn(e);
      }
      
      FormDAO dao = null;
      
      try
      {
         
         
         dao = FormDAOFactory.createFormDAO();
         
         logger.debug("4");         
         //dao.setAutoCommit(false);
         
         aForm = dao.updateForm(aForm);
         logger.debug("5");
         dao.commit();
      }
      catch(Exception e)
      {
         logger.error(e);
         
         if(dao != null) 
            dao.rollback();
         throw new SystemException(Debugger.stackTrace(e));
      }
      finally
      {
         if(dao != null)
            dao.dispose();
      }
      
      return aForm;
   }//--------------------------------------------   
   /**
    * 
    * @param aForm the form to remove
    * @return removed form
    * @throws SystemException
    */
   public Form removeForm(Form aForm, SecurityCredential aUser)
   throws SystemException
   {
      if (aForm == null)
         throw new IllegalArgumentException(
         "aForm required in FormBean.remove");
      
      if( aUser == null)
         throw new IllegalArgumentException(" aUser required in FormBean.removeForm");
      
      FormDAO dao = null;
      BRE bre = null;
      
      try
      {
         
         dao = FormDAOFactory.createFormDAO();
         
         //Set event for registered operations
         aForm.addAttribute(FormGuide.EVENT_ATTRIB_NM, "removeForm");
         
         bre = dao.constructBRE(aForm); 
         
         dao.deleteFormByPK(aForm.getPrimaryKey(), aForm.getFormTypeCode());
         
         dao.commit();
   
      }
      catch(Exception e)
      {
         if(dao != null) 
            dao.rollback();
         throw new SystemException(Debugger.stackTrace(e));
      }
      finally
      {
         if(dao != null)
            dao.dispose();
      }
      
      try
      {
         bre.applyRules(aForm);
      }
      catch(Exception e)
      {
         logger.warn(e);
      }
      
      return aForm;
   }//--------------------------------------------
   /**
    * 
    * @param aFormName the form name
    * @return form type
    * @throws SystemException
    */
   public FormType retrieveFormType(String aFormTypeName, SecurityCredential aUser)
   throws NoDataFoundException
   {
      if (aFormTypeName == null)
         throw new IllegalArgumentException(
         "aFormTypeName required in FormBean.retrieveFormType");
      
      FormDAO dao = null;
      try
      {
         
         dao = FormDAOFactory.createFormDAO();
         FormType type = dao.selectFormTypeByCode(aFormTypeName);
         dao.commit();
         return type;
           
      }
      catch(SQLException e)
      {
         if(dao != null) 
            dao.rollback();
         throw new SystemException(Debugger.stackTrace(e));
      }
      finally
      {
         if(dao != null)
            dao.dispose();
      }
   }//--------------------------------------------
   /**
    * 
    * @param aFormStatusName form status name
    * @param aUser the access user
    * @return Latest version of the Form Status matching the status name
    * @throws SystemException
    * @throws NoDataFoundException
    */
   public FormStatus retrieveFormStatus(String aFormStatusName, SecurityCredential aUser)
   throws NoDataFoundException
   {
      if (aFormStatusName == null)
         throw new IllegalArgumentException(
         "aFormStatusName required in FormBean.retrieveFormStatus");
      
      FormDAO dao = null;
      try
      {
         
         dao = FormDAOFactory.createFormDAO();
         FormStatus status = dao.selectStatusByName(aFormStatusName);
         dao.commit();
         return status;
      }
      catch(SQLException e)
      {
         if(dao != null) 
            dao.rollback();
         throw new SystemException(Debugger.stackTrace(e));
      }
      finally
      {
         if(dao != null)
            dao.dispose();
      }
   }//--------------------------------------------
   /**
    * Retrieve all non deleted form statuses
    * @param aUser the user
    * @return Collection of FormStatus objects
    * @throws SystemException
    * @throws NoDataFoundException
    */
   public Collection retrieveFormStatuses(SecurityCredential aUser)
   throws NoDataFoundException
   {    
      
      FormDAO dao = null;
      try
      {
         
         dao = FormDAOFactory.createFormDAO();
         
         Collection c = dao.selectStatuses();
         dao.commit();
         return c;
      }
      catch(SQLException e)
      {
         if(dao != null) 
            dao.rollback();
         throw new SystemException(Debugger.stackTrace(e));
      }
      finally
      {
         if(dao != null)
            dao.dispose();
      }
   }//--------------------------------------------
   /**
    * Retireve latest form types
    * @param aUser the access user
    * @return Collection for GCSMFormTypes
    * @throws SystemException
    * @throws NoDataFoundException
    */
   public Collection retrieveFormTypes(SecurityCredential aUser)
   throws NoDataFoundException
   {     
      FormDAO dao = null;
      try
      {
         
         dao = FormDAOFactory.createFormDAO();
         
         Collection c = dao.selectFormTypes();
         dao.commit();
         return c;
      }
      catch(SQLException e)
      {
         if(dao != null) 
            dao.rollback();
         throw new SystemException(Debugger.stackTrace(e));
      }
      finally
      {
         if(dao != null)
            dao.dispose();
      }
   }//--------------------------------------------
   /**
    * Find Forms object by a given form search criteria
    * @param aFormSearchCriteria the form search criteria
    * @return collection of the Form objects
    * @throws SystemException
    * @throws NoDataFoundException
    */
   public Collection findForms(FormSearchCriteria aFormSearchCriteria)
   throws NoDataFoundException
   {  
      if (aFormSearchCriteria == null)
         throw new IllegalArgumentException(
         "aFormSearchCriteria required in FormBean.searchClinicalFormOverviews");
      
      FormDAO dao = null;
      try
      {
         dao = FormDAOFactory.createFormDAO();
         
         Collection c = dao.searchForForms(aFormSearchCriteria);
         dao.commit();
         return c;
      }
      catch(SQLException e)
      {
          if (dao != null)
              dao.rollback();
         throw new SystemException(Debugger.stackTrace(e));
      }
      finally
      {
         if(dao != null)
            dao.dispose();
      }
   }//--------------------------------------------   
   /**
    * Retrieve instance Questionaire
    * @param aFormTypeName the form type name
    * @return Question with questions for form type
    * 
    * @throws SystemException
    * @throws NoDataFoundException
    */
   public Questionaire constructQuestioniare(String aFormTypeName)
   throws NoDataFoundException
   {        
      QuestionDAO dao = null;
      try
      {
         
         dao = FormDAOFactory.createQuestionDAO();
         
         Questionaire q = dao.constructQuestioniareByFormTypeCode(aFormTypeName);
         dao.commit();
         return q;
      }
      catch(SQLException e)
      {
          if (dao != null)
              dao.rollback();
         throw new SystemException(Debugger.stackTrace(e));
      }
      finally
      {
         if(dao != null)
            dao.dispose();
      }
   }//--------------------------------------------
   /**
    * Use business rule engine to execute operations on a given form
    * @param aForm the form 
    * @throws SystemException
    */
   public Form applyBusinessRules(Form aForm)
   throws SystemException
   {
      if (aForm == null)
         throw new IllegalArgumentException(
         "aForm required in FormBean.applyBusinessRules");
      
      
      logger.debug("Applying rules for formId="+aForm.getPrimaryKey());
      
      BreDAO dao = null;
      try
      {
         dao = FormDAOFactory.createBreDAO();
         
         BRE bre = dao.constructBRE(aForm);
         bre.applyRules(aForm);
         dao.commit();
         
         return aForm;
      }
      catch(Exception e)
      {
          if (dao != null)
              dao.rollback();
         logger.error(e);
         throw new SystemException(Debugger.stackTrace(e));
      }
      finally
      {
         if(dao != null) 
            dao.dispose();
      }
   }//--------------------------------------------

   public Questionaire constructQuestioniare(int formTypePK) throws NoDataFoundException, RemoteException
   {
      // TODO Auto-generated method stub
      return null;
   }
   public Application[] edits(Application[] forms, SecurityCredential user) throws RemoteException
   {
      // TODO Auto-generated method stub
      return null;
   }
   public Form removeForm(Form form) throws RemoteException
   {
      // TODO Auto-generated method stub
      return null;
   }
   public Application retrieveForm(Integer formID) throws NoDataFoundException, BreException, RemoteException
   {
      // TODO Auto-generated method stub
      return null;
   }
   public FormType retrieveFormType(int formTypeID, SecurityCredential user) throws NoDataFoundException, RemoteException
   {
      // TODO Auto-generated method stub
      return null;
   }//--------------------------------------------
   private Log logger = Debugger.getLog(this.getClass());


}
