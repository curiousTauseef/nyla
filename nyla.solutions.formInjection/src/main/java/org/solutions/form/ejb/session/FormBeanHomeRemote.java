package org.solutions.form.ejb.session;

import javax.ejb.EJBHome;
import javax.ejb.CreateException;
import java.rmi.RemoteException;

/**
 * Form bean EJB remote home interface
 *
 * @author Gregory Green
 * @version 1
 */
public interface FormBeanHomeRemote extends EJBHome 
{
   /**
    * 
    * @return New instance of the Form bean.
    * @throws RemoteException
    * @throws CreateException
    */
    public FormBeanRemote create() throws RemoteException, CreateException;
}
