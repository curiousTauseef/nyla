package org.solutions.form.ejb.session;
import java.rmi.RemoteException;
import java.util.Collection;

import javax.ejb.EJBException;
import javax.ejb.EJBObject;

import nyla.solutions.global.exception.NoDataFoundException;
import nyla.solutions.global.security.data.SecurityCredential;

import org.solutions.form.FormSearchCriteria;
import org.solutions.form.FormService;
import org.solutions.form.data.Application;
import org.solutions.form.data.Form;
import org.solutions.form.data.FormStatus;
import org.solutions.form.data.FormType;
import org.solutions.form.data.Questionaire;
import org.solutions.form.exception.BreException;

/**
 * 
 * <pre>
 * FormBeanRemote provides remote EJB interface for the form bean
 * </pre> 
 * @author Gregory Green
 * @version 1.0
 */
public interface FormBeanRemote extends EJBObject, FormService
{
   /**
    * The originator can request to edit submitted forms that have not 
    * been completed, approved, rejected, or not file in ESF. The forms 
    * cannot be edited if it has been submitted to ESF.
    * 
    * Users will be able to create a new version of a report that
    *  has been filed in ESF. The new version of the form is a copy 
    * of the original form. The answers and questions from the original 
    * form are transfers to the new version. Only the originator can renew 
    * a completed form.
    * @param aForm the form the renew
    * @return the renewed form
    */
   public Form renew(Form aForm)
   throws EJBException, NoDataFoundException, RemoteException;   
   
   /**
    * Find Forms object by a given form search criteria
    * @param aFormSearchCriteria the form search criteria
    * @return collection of the Form objects
    * @throws EJBException
    * @throws NoDataFoundException
    */
   public Collection findForms(FormSearchCriteria aFormSearchCriteria)
   throws EJBException, NoDataFoundException, RemoteException;
   
   /**
    * Retrieve all non deleted form statuses
    * @param aUser the user
    * @return Collection of FormStatus objects
    * @throws EJBException
    * @throws NoDataFoundException
    */
   public Collection retrieveFormStatuses(SecurityCredential aUser)
   throws EJBException, NoDataFoundException, RemoteException;
   
   /**
    * Retrieve form by its unique ID
    * @param aFormID the form ID
    * @param aUser the user
    * @return form with ID
    * @throws Exception
    */
   public Application retrieveForm(Integer aFormID)
   throws EJBException, NoDataFoundException, BreException, RemoteException;

   /**
    * Insert form information into the database
    * @param aForm the form to create
    * @return created form
    * @throws EJBException
    */
   public Form create(Form aForm)
   throws EJBException, RemoteException;

   /**
    * Edit one of more forms
    * @param aForms list of forms to edit
    * @return edited from
    * @throws EJBException
    */
   public Application []  edits(Application[] aForms, SecurityCredential aUser)
   throws EJBException, RemoteException;
   
   /**
    * 
    * @param aForm the form to edit
    * @return edited from
    * @throws EJBException
    */
   public Form edit(Form aForm)
   throws EJBException, RemoteException;
   
   /**
    * 
    * @param aForm the form to remove
    * @return removed form
    * @throws EJBException
    */
   public Form removeForm(Form aForm)
   throws EJBException, RemoteException;

   /**
    * 
    * @param aFormName the form name
    * @return form type
    * @throws EJBException
    */
   public FormType retrieveFormType(String aFormTypeName, SecurityCredential aUser)
   throws EJBException, NoDataFoundException, RemoteException;

   /**
    * Retrieve form type that matches a given ID
    * @param aFormTypeID form type ID
    * @param aUser the access user
    * @return the GCSM Form type information
    * @throws EJBException
    * @throws NoDataFoundException
    */
   public FormType retrieveFormType(int aFormTypeID, SecurityCredential aUser)
   throws EJBException, NoDataFoundException, RemoteException;


   /**
    * 
    * @param aFormStatusName form status name
    * @param aUser the access user
    * @return Latest version of the Form Status matching the status name
    * @throws EJBException
    * @throws NoDataFoundException
    */
   public FormStatus retrieveFormStatus(String aFormStatusName, SecurityCredential aUser)
   throws EJBException, NoDataFoundException, RemoteException;

   /**
    * Retireve latest form types
    * @param aUser the access user
    * @return Collection for GCSMFormTypes
    * @throws EJBException
    * @throws NoDataFoundException
    */
   public Collection retrieveFormTypes(SecurityCredential aUser)
   throws EJBException, NoDataFoundException, RemoteException;

   /**
    * Retrieve instance Questionaire
    * @param aFormTypePK the form type PK
    * @return Question with questions for form type
    * @return
    * @throws EJBException
    * @throws NoDataFoundException
    */
   public Questionaire constructQuestioniare(int aFormTypePK)
   throws EJBException, NoDataFoundException, RemoteException;

   /**
    * Retrieve instance Questionaire
    * @param aFormTypeName the form type name
    * @return Question with questions for form type
    * @throws EJBException
    * @throws NoDataFoundException
    */
   public Questionaire constructQuestioniare(String aFormTypeName)
   throws EJBException, NoDataFoundException, RemoteException;

   /**
    * Use business rule engine to execute operations on a given form
    * @param aForm the form 
    * @throws EJBException
    */
   public Form applyBusinessRules(Form aForm)
   throws EJBException, RemoteException;
}
