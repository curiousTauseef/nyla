package org.solutions.form;


import java.sql.SQLException;
import java.util.Collection;

import nyla.solutions.dao.jdo.JDODAO;
import nyla.solutions.dao.jdo.JDOQueryBuilder;
import nyla.solutions.global.exception.NoDataFoundException;
import nyla.solutions.global.operations.logging.Log;
import nyla.solutions.global.security.data.SecurityCredential;
import nyla.solutions.global.security.user.data.User;
import nyla.solutions.global.util.Debugger;

import org.apache.log4j.Category;

/**
 * <pre>
 * <p/>
 * SecurityDAO is a data access object for security
 * <p/>
 * management.
 * <p/>
 * </pre>
 *
 * @author Gregory Green
 * @version 1.0
 */

public class SecurityDAO extends JDODAO {

    /**
     * Constructor for SecurityDAO initalizes internal
     * <p/>
     * data settings.
     */

    protected SecurityDAO() {

        super();

    }//--------------------------------------------

    /**
     * Constructor for SecurityDAO initalizes internal
     * <p/>
     * data settings.
     *
     * @param aUser
     */

    protected SecurityDAO(SecurityCredential aUser) {

        super(aUser);

    }//--------------------------------------------

    /**
     * Constructor for SecurityDAO initalizes internal
     * <p/>
     * data settings.
     *
     * @param aDAO
     */

    protected SecurityDAO(JDODAO aDAO) {

        super(aDAO);

    }//--------------------------------------------


    /**
     * @param aUser the user accessing security
     *
     * @return
     */

    public static SecurityDAO getSecurityDAOInstance(SecurityCredential aUser) {

        return new SecurityDAO(aUser);

    }//--------------------------------------------

/**
 * getSecurityDAOInstance returns Security DAO Instance.
 * @return SecurityDAO
 */
    public static SecurityDAO getSecurityDAOInstance() {

        return new SecurityDAO();

    }//--------------------------------------------

    /**
     * @param aLoginName the login name
     *
     * @return user
     */

    public User selectUserByLoginName(String aLoginName)

            throws SQLException, NoDataFoundException {
/*
    // Get the session from session.xml named  'System'

        DatabaseSession session =
                (DatabaseSession) SessionManager.getManager().getSession("System");
    // Create expression builder.
        ExpressionBuilder builder = new ExpressionBuilder();

    // Create parameterized expression,
    // 'loginID' is the parameter to be bound while executing this query.

    //Expression exp = (builder.get("tablefield").greaterThan(builder.getParameter("paramfield")));
        Expression exp = builder.get("loginID").equalsIgnoreCase(aLoginName);
        //        exp = exp.and(builder.get("email").equalsIgnoreCase(emailID));
       // exp.and(builder.get("email").equalsIgnoreCase(emailID));
    // TO add more conditions to expression
    // exp.and()   exp.or() etc
        logger.debug("Dumping EXP in SecurityDAO");
        //Debugger.dump(exp);

    // Create parameterized query based on the above expression

        ReadAllQuery query = new ReadAllQuery();
        query.setReferenceClass(GCSMUser.class);
        query.setSelectionCriteria(exp);
        query.addArgument("loginID");
//        query.addArgument("email");

    // enable parameter binding and prepared statement caching
        query.setShouldBindAllParameters(true);
        query.setShouldCacheStatement(true);

    // create vector of values to bound to the query
        Vector value = new Vector();
        value.addElement(aLoginName);

//        value.addElement(emailID);
        logger.debug("Dumping Parameter to Query SecurityDAO");
        //Debugger.dump(value);

    // execute the parameterized query
        logger.debug("Dumping Query in SecurityDAO");
        //Debugger.dump(query);

        Collection users = (Collection) session.executeQuery(query, value);
        logger.debug("Dumping User Object From SecurityDAO");
        //Debugger.dump(users);
        if (users.size() > 1) {
            throw new SQLException("Too Many Users Found");
        }
*/
/*
 TODO: Fix Me : This following commented code does not return
        any other user then Greeng
*/

        JDOQueryBuilder builder = this.createQueryBuilder(User.class);

         logger.debug("login Id sent to builder" + aLoginName);
        builder=builder.getColumn("loginID").equalsIgnoreCase(aLoginName);
        logger.debug("SELECT QUERY"+builder);
        Collection users = (Collection) this.select(builder);

        return (User)users.iterator().next();
//        return user;

    }//--------------------------------------------
    public SecurityCredential retrieveUserCredential()
               throws SQLException, NoDataFoundException {
        JDOQueryBuilder builder = this.createQueryBuilder(User.class);
        logger.debug("SELECT QUERY retrieveUserCredential"+builder);
        Collection users = (Collection) this.select(builder);
        return (SecurityCredential) users.iterator().next();

    }

    protected transient Log logger = Debugger.getLog(this.getClass());

	
}

