package nyla.solutions.net.postit.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import nyla.solutions.core.exception.NoDataFoundException;
import nyla.solutions.core.util.Debugger;
import nyla.solutions.email.Email;
import nyla.solutions.net.postit.PostItService;
import nyla.solutions.net.postit.Postable;
import nyla.solutions.net.postit.RecipientDAORepository;
import nyla.solutions.net.postit.data.Recipient;
import nyla.solutions.net.postit.exception.PostItException;

@Component
public class MailPostItService implements PostItService
{

   public MailPostItService()
   {
      super();
   }// --------------------------------------------
   /**
    * 
    * Post using email
    * @see nyla.solutions.net.postit.PostItService#post(java.lang.String, nyla.solutions.net.postit.Postable)
    */
   public void post(String aTo, Postable aPostable)
   throws PostItException
   {
      try
      {
        Email email = new Email();
        
         
        email.sendMail(aTo, aPostable.getName(), aPostable.getText(), aPostable.getAttachment());
      }
      catch (Exception e)
      {
         throw new PostItException(Debugger.stackTrace(e));
      }
   }// --------------------------------------------
   /**
    * 
    * @param aPostable
    */
   public void post(Postable aPostable)
   throws PostItException
   {
     
      try
      {
    	  Iterable<Recipient> recipients = recipientDAORepository.findAll();
    	  
         for (Recipient recipient : recipients)
		{
        	 this.post(recipient.getEmail(), aPostable);
		}
        
      }
      catch(NoDataFoundException aNoDataFoundException)
      {
         Debugger.printWarn(this,aNoDataFoundException);
      }      
   }// --------------------------------------------
   @Autowired
   private RecipientDAORepository recipientDAORepository;
   
}
