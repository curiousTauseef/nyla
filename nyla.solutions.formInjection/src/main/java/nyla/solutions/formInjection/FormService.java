package nyla.solutions.formInjection;
import java.rmi.RemoteException;
import java.util.Collection;

import nyla.solutions.formInjection.data.Application;
import nyla.solutions.formInjection.data.Form;
import nyla.solutions.formInjection.data.FormStatus;
import nyla.solutions.formInjection.data.FormType;
import nyla.solutions.formInjection.data.Questionaire;
import nyla.solutions.formInjection.exception.BreException;
import nyla.solutions.global.exception.NoDataFoundException;
import nyla.solutions.global.security.data.SecurityCredential;


/**
 * 
 * <pre>
 * FormService provides interface for the form bean
 * </pre> 
 * @author Gregory Green
 * @version 1.0
 */
public interface FormService
{
   
   /**
    * Find Forms object by a given form search criteria
    * @param aFormSearchCriteria the form search criteria
    * @return collection of the Form objects
    * @throws EJBException
    * @throws NoDataFoundException
    */
   public Collection findForms(FormSearchCriteria aFormSearchCriteria)
   throws NoDataFoundException, RemoteException;
   
   /**
    * Retrieve all non deleted form statuses
    * @param aUser the user
    * @return Collection of FormStatus objects
    * @throws EJBException
    * @throws NoDataFoundException
    */
   public Collection retrieveFormStatuses(SecurityCredential aUser)
   throws NoDataFoundException, RemoteException;
   
   /**
    * Retrieve form by its unique ID
    * @param aFormID the form ID
    * @param aUser the user
    * @return form with ID
    * @throws Exception
    */
   public Application retrieveForm(Integer aFormID)
   throws NoDataFoundException, BreException, RemoteException;

   /**
    * Insert form information into the database
    * @param aForm the form to create
    * @return created form
    * @throws EJBException
    */
   public Form create(Form aForm)
   throws RemoteException;

   /**
    * Edit one of more forms
    * @param aForms list of forms to edit
    * @return edited from
    * @throws EJBException
    */
   public Application []  edits(Application[] aForms, SecurityCredential aUser)
   throws RemoteException;
   
   /**
    * 
    * @param aForm the form to edit
    * @return edited from
    * @throws EJBException
    */
   public Form edit(Form aForm)
   throws RemoteException;
   
   /**
    * 
    * @param aForm the form to remove
    * @return removed form
    * @throws EJBException
    */
   public Form removeForm(Form aForm)
   throws RemoteException;

   /**
    * 
    * @param aFormName the form name
    * @return form type
    * @throws EJBException
    */
   public FormType retrieveFormType(String aFormTypeName, SecurityCredential aUser)
   throws NoDataFoundException, RemoteException;

   /**
    * Retrieve form type that matches a given ID
    * @param aFormTypeID form type ID
    * @param aUser the access user
    * @return the GCSM Form type information
    * @throws EJBException
    * @throws NoDataFoundException
    */
   public FormType retrieveFormType(int aFormTypeID, SecurityCredential aUser)
   throws NoDataFoundException, RemoteException;


   /**
    * 
    * @param aFormStatusName form status name
    * @param aUser the access user
    * @return Latest version of the Form Status matching the status name
    * @throws EJBException
    * @throws NoDataFoundException
    */
   public FormStatus retrieveFormStatus(String aFormStatusName, SecurityCredential aUser)
   throws NoDataFoundException, RemoteException;

   /**
    * Retireve latest form types
    * @param aUser the access user
    * @return Collection for GCSMFormTypes
    * @throws EJBException
    * @throws NoDataFoundException
    */
   public Collection retrieveFormTypes(SecurityCredential aUser)
   throws NoDataFoundException, RemoteException;

   /**
    * Retrieve instance Questionaire
    * @param aFormTypePK the form type PK
    * @return Question with questions for form type
    * @return
    * @throws EJBException
    * @throws NoDataFoundException
    */
   public Questionaire constructQuestioniare(int aFormTypePK)
   throws NoDataFoundException, RemoteException;

   /**
    * Retrieve instance Questionaire
    * @param aFormTypeName the form type name
    * @return Question with questions for form type
    * @throws EJBException
    * @throws NoDataFoundException
    */
   public Questionaire constructQuestioniare(String aFormTypeName)
   throws NoDataFoundException, RemoteException;

   /**
    * Use business rule engine to execute operations on a given form
    * @param aForm the form 
    * @throws EJBException
    */
   public Form applyBusinessRules(Form aForm)
   throws RemoteException;
   
   /**
    * Create a new form
    * @param formTypeCode the form type code
    * @return the autofilled new form
    * @throws EJBException
    * @throws RemoteException
    */
   public Form retrieveNewForm(String formTypeCode)
   throws RemoteException;
}
