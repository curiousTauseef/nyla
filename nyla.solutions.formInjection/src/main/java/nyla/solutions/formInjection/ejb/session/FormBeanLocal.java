package nyla.solutions.formInjection.ejb.session;
import java.sql.SQLException;
import java.util.Collection;

import javax.ejb.EJBException;
import javax.ejb.EJBLocalObject;

import nyla.solutions.formInjection.FormSearchCriteria;
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
 * <b>FormBeanLocal</b> local EJB implementation
 * </pre> 
 * @author Gregory Green
 * @version 1.0
 */

public interface FormBeanLocal extends EJBLocalObject 
{
   /**
    * Update ESF file date for form
    * @param aForm
    * @return
    * @throws SQLException
    * @throws NoDataFoundException
    */
   public Application updateFiling(Application aForm, SecurityCredential aUser)
   throws EJBException, NoDataFoundException;
   
   /**
    * The application must maintain a relationship between some created forms 
    * and the orginating form (i.e. SMV). 
    * It will added as a form property "relatedFormID". 
    * @param aSiteID the site ID
    * @param aFormTypeName the form type name
    * @param aRelatedFormID the related form ID
    * @param aUser the who initiate the creation
    * @return instance of the GCSMForm
    */
   public Application saveUnscheduledRelatedSiteFormForUser(int aSiteID, String aFormTypeName, Integer aRelatedFormID,SecurityCredential aUser )
   throws NoDataFoundException, EJBException;
   
   /**
    * Create or return an existing "INCOMPLETE" form a given type in a site
    * Only a single form that matched the search criteria will be returned
    * @param aSiteID the site is
    * @param aFormTypeID the form type ID
    * @return
    */
   public Application saveUnscheduledSiteForm(int aSiteID, String aFormTypeName, SecurityCredential aUser)
   throws NoDataFoundException, EJBException;
   
   /**
    * Create or return an existing "INCOMPLETE" form for the user of a given type.
    * Only a single form that matched the search criteria will be returned
    * @param aFormTypeName the form type
    * @param aFormTypeID the form type ID
    * @return
    */
   public Application saveUnscheduledFormForUser(String aFormTypeName, SecurityCredential aUser)
   throws NoDataFoundException, EJBException;
   
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
   throws EJBException, NoDataFoundException;
   
   /**
    * Find Forms object by a given form search criteria
    * @param aFormSearchCriteria the form search criteria
    * @return collection of the Form objects
    * @throws EJBException
    * @throws NoDataFoundException
    */
   public Collection findForms(FormSearchCriteria aFormSearchCriteria)
   throws EJBException, NoDataFoundException;
   
   /**
    * Find clinical form for a given search criteria
    * @param aFormSearchCriteria the form search criteria 
    * @return collection of ClinicalFormOverviews
    * @throws EJBException
    * @throws NoDataFoundException
    */
   public Collection findClinicalFormOverviews(FormSearchCriteria aFormSearchCriteria)
   throws EJBException, NoDataFoundException;
   
   /**
    * Select the forms overview information for a given site
    * @param aSiteID the site ID
    * @param aUser the user
    * @return Collection of ClinicalFormOverview object
    * @throws EJBException
    * @throws NoDataFoundException
    */
   public Collection retrieveSiteClinicalFormOverviews(Integer aSiteID, SecurityCredential aUser)
   throws EJBException, NoDataFoundException;
   
   /**
    * Retrieve all non deleted form statuses
    * @param aUser the user
    * @return Collection of FormStatus objects
    * @throws EJBException
    * @throws NoDataFoundException
    */
   public Collection retrieveFormStatuses(SecurityCredential aUser)
   throws EJBException, NoDataFoundException;
   
   /**
    * Create or return an existing "INCOMPLETE" form for the user of a given type.
    * Only a single form that matched the search criteria will be returned
    * @param aFormTypeName the form type
    * @param aFormTypeID the form type ID
    * @return
    */
   public Application saveUnscheduledSiteFormForUser(int aSiteID, String aFormTypeName, SecurityCredential aUser)
   throws NoDataFoundException, EJBException;

   /**
    * Retrieve form by its unique ID
    * @param aFormID the form ID
    * @param aUser the user
    * @return form with ID
    * @throws Exception
    */
   public Application retrieveForm(Integer aFormID, SecurityCredential aUser)
   throws EJBException, NoDataFoundException, BreException;



   /**
    * Insert form information into the database
    * @param aForm the form to create
    * @return created form
    * @throws EJBException
    */
   public Application create(Application aForm)
   throws EJBException;

   
   /**
    * Edit one or more forms
    * @param aForms list of forms to edit
    * @return edited from
    * @throws EJBException
    */
   public Form []  edits(Form[] aForms, SecurityCredential aUser)
   throws EJBException;
   
   /**
    * 
    * @param aForm the form to edit
    * @return edited from
    * @throws EJBException
    */
   public Form edit(Form aForm)
   throws EJBException;
 
   /**
    * 
    * @param aForm the form to remove
    * @return removed form
    * @throws EJBException
    */
   public Form removeForm(Form aForm, SecurityCredential aUser)
   throws EJBException;

   /**
    * 
    * @param aFormName the form name
    * @return form type
    * @throws EJBException
    */
   public FormType retrieveFormType(String aFormTypeName, SecurityCredential aUser)
   throws EJBException, NoDataFoundException;

   /**
    * Retrieve form type that matches a given ID
    * @param aFormTypeID form type ID
    * @param aUser the access user
    * @return the GCSM Form type information
    * @throws EJBException
    * @throws NoDataFoundException
    */
   public FormType retrieveFormType(int aFormTypeID, SecurityCredential aUser)
   throws EJBException, NoDataFoundException;


   /**
    * 
    * @param aFormStatusName form status name
    * @param aUser the access user
    * @return Latest version of the Form Status matching the status name
    * @throws EJBException
    * @throws NoDataFoundException
    */
   public FormStatus retrieveFormStatus(String aFormStatusName, SecurityCredential aUser)
   throws EJBException, NoDataFoundException;

   /**
    * Retireve latest form types
    * @param aUser the access user
    * @return Collection for GCSMFormTypes
    * @throws EJBException
    * @throws NoDataFoundException
    */
   public Collection retrieveFormTypes(SecurityCredential aUser)
   throws EJBException, NoDataFoundException;

   public Collection retrieveSiteForms(Integer aSiteID, SecurityCredential aUser)
   throws EJBException, NoDataFoundException;

   /**
    * Retrieve instance Questionaire
    * @param aFormTypePK the form type PK
    * @return Question with questions for form type
    * @return
    * @throws EJBException
    * @throws NoDataFoundException
    */
   public Questionaire constructQuestioniare(int aFormTypePK)
   throws EJBException, NoDataFoundException;

   /**
    * Retrieve instance Questionaire
    * @param aFormTypeName the form type name
    * @return Question with questions for form type
    * @throws EJBException
    * @throws NoDataFoundException
    */
   public Questionaire constructQuestioniare(String aFormTypeName)
   throws EJBException, NoDataFoundException;

   /**
    * Use business rule engine to execute operations on a given form
    * @param aForm the form 
    * @throws EJBException
    */
   public Form applyBusinessRules(Form aForm, SecurityCredential aUser)
   throws EJBException;
   
   public void reconcileOsif(Form osif) throws EJBException;
}
