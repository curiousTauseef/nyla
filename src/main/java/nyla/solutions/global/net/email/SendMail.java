package nyla.solutions.global.net.email;

public interface SendMail
{

   /**
    * 
    * Sending out E-mails through SMTP E-mail server.
    * 
    * From the system
    * 
    * @param aTo
    *           E-mail TO: field in Internet E-mail address format. If there are
    *           more than one E-mail addresses, separate them by
    *           SysConst.EMAIL_DELIMITER_IND.
    * 
    * @param aFrom from address
    * 
    * @param aSubject E-mail subject line.
    * 
    * @param aMessageBody  E-mail body.
    * 
    */

	   public void sendMail(String aTo,

               String aFrom,

               String aSubject,

               String aMessageBody)

	   throws Exception;

}