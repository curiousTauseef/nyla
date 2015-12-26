package nyla.solutions.blueprint.jee.ejb;

import javax.ejb.*;


/**

 * 

 * <pre>

 * EJB provides a set of functions to the Entreprise Java Beans

 * </pre> 

 * @author Gregory Green

 * @version 1.0

 */

public abstract class EJB

{

   /**

    * 

    * Default constructor

    *

    */

    public EJB()

    {

        sessionContext = null;

    }//--------------------------------------------

    public void ejbCreate()

        throws CreateException

    {

    }//--------------------------------------------



    public void setSessionContext(SessionContext aSessionContext)

        throws EJBException

    {

        sessionContext = aSessionContext;

    }//--------------------------------------------



    public void ejbRemove()

        throws EJBException

    {//--------------------------------------------

    }



    public void ejbActivate()

        throws EJBException

    {

    }//--------------------------------------------



    public void ejbPassivate()

        throws EJBException

    {

    }//--------------------------------------------

    


   /**
    * @return Returns the sessionContext.
    */
   protected final SessionContext getSessionContext()
   {
      return sessionContext;
   }


   private SessionContext sessionContext;

}

