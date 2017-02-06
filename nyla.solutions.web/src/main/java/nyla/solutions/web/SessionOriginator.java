package nyla.solutions.web;
import javax.servlet.http.*;

/**

 * <pre>

 * SessionOriginator interface to create and initialize a HTTP session

 * </pre> 

 * @author Gregory Green

 * @version 1.0

 */

public interface SessionOriginator

{

   /**

    * Create and initialize the session information

    * @param aRequest http request

    * @param aResponse http session

    */
   public void createSession(HttpServletRequest aRequest, HttpServletResponse aResponse);   

}

