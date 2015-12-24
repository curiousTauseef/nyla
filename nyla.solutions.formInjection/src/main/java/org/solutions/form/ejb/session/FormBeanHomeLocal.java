package org.solutions.form.ejb.session;

import javax.ejb.EJBLocalHome;
import javax.ejb.CreateException;

/**
 * <b>FormBeanHomeLocal</b> EJB local home implementation
 * @author Gregory Green
 * @version 1.0
 * 
 */

public interface FormBeanHomeLocal extends EJBLocalHome 
{
   /**
    * 
    * @return Local Form Bean EJB instance
    * @throws CreateException
    */
   public FormBeanLocal create() throws CreateException;
}
