package org.solutions.form.security;

import nyla.solutions.global.security.data.SecurityCredential;

import org.solutions.form.data.Form;


/**
 * <pre>
 * EditCheckPoint chekc if form is read only
 * </pre> 
 * @author Gregory Green
 * @version 1.0
 */
public class EditCheckPoint extends AbstractCheckPoint
{

   /**
    * Constructor for EditCheckPoint initalizes internal 
    * data settings.
    * 
    */
   public EditCheckPoint()
   {
      super();
   }//--------------------------------------------

   /**
    * 
    * @see org.solutions.form.security.CheckPoint#canProceed(org.solutions.form.data.Form, com.bms.informatics.gcsm.security.data.SecurityCredential)
    * @return !aForm.isReadOnly(aUser)
    */
   public boolean canProceed(Form aForm, SecurityCredential aUser)
   {
      return !aForm.isReadOnly();
   }//--------------------------------------------
}
