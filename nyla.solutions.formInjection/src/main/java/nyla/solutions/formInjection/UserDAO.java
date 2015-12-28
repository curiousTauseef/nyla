package nyla.solutions.formInjection;

import java.sql.SQLException;
import java.util.Collection;

import nyla.solutions.dao.jdo.JDODAO;
import nyla.solutions.dao.jdo.JDOQueryBuilder;
import nyla.solutions.global.exception.NoDataFoundException;
import nyla.solutions.global.security.data.SecurityCredential;
import nyla.solutions.global.security.user.data.User;

//import org.solutions.dao.jdo.JDODAO;
//import org.solutions.dao.jdo.JDOQueryBuilder;
//import org.solutions.exception.NoDataFoundException;
//import org.solutions.security.data.SecurityCredential;
//import org.solutions.security.user.data.User;



/**
 * <pre>
 * 
 *  
 *   &lt;p/&gt;
 *   &lt;p/&gt;
 *    UserDAO is a data access object for USER management
 *   &lt;p/&gt;
 *   &lt;p/&gt;
 *   
 *  
 * </pre>
 * 
 * @author Gregory Green, DMane
 * @version 1.0
 */

public class UserDAO extends SecurityDAO
{

   /**
    * Constructor for UserDAO initalizes internal <p/>data settings.
    */

   protected UserDAO()
   {

      super();

   }//--------------------------------------------

   /**
    * Constructor for UserDAO initalizes internal <p/>data settings.
    * 
    * @param aUser
    */

   protected UserDAO(SecurityCredential aUser)
   {
      super(aUser);
   }//--------------------------------------------

   /**
    * Constructor for UserDAO initalizes internal <p/>data settings.
    * 
    * @param aDAO
    */

   protected UserDAO(JDODAO aDAO)
   {

      super(aDAO);

   }//--------------------------------------------


   /**
    * @param aUser
    *           the user accessing security
    * 
    * @return
    */

   public static UserDAO getUserDAOInstance(SecurityCredential aUser)
   {

      return new UserDAO(aUser);

   }//--------------------------------------------
   
   protected static UserDAO getUserDAOInstance()
   {
      return new UserDAO();
   }// --------------------------------------------
   /**
    * Select user by primary key ID
    * @param aPrimaryKey the primary key
    * @return user information
    * @throws SQLException
    * @throws NoDataFoundException
    */
   public User selectUserByPrimaryKey(Integer aPrimaryKey)

   throws SQLException, NoDataFoundException
   {

      JDOQueryBuilder builder = this.createQueryBuilder(User.class);
      
      builder.getColumn("primaryKey").equal(aPrimaryKey);
      return (User)((Collection) this.select(builder)).iterator().next();

   }//--------------------------------------------
   public Collection selectUsers()

   throws SQLException, NoDataFoundException
   {

      JDOQueryBuilder builder = this.createQueryBuilder(User.class);

      return (Collection) this.select(builder);

   }//--------------------------------------------
   /**
    * 
    * @param aUserIDs
    * @return
    * @throws SQLException
    * @throws NoDataFoundException
    */
   public Collection selectUsers(Integer[] aUserIDs) throws SQLException, NoDataFoundException
   {
      JDOQueryBuilder userQuery = this.createQueryBuilder(User.class);

      return (Collection) super.select(userQuery.getColumn("primaryKey").in(
      aUserIDs));

   }//--------------------------------------------

   

}
