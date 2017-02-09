package nyla.solutions.net.postit;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.gemfire.GemfireTemplate;
import org.springframework.stereotype.Component;

import nyla.solutions.core.exception.CommunicationException;
import nyla.solutions.core.exception.RequiredException;
import nyla.solutions.core.security.user.data.UserProfile;
import nyla.solutions.core.util.Debugger;
import nyla.solutions.core.util.JavaBean;
import nyla.solutions.core.util.Text;
import nyla.solutions.email.Email;

@Component
public class PostItMgr
{
	/**
	 * 
	 * @param emails
	 */
	public void removeRecipientsByEmails(String emails)
	{
		if(email == null)
			return;
		
		String[] emailArray = toEmails(emails); 
		
		removeRecipientsByEmails(emailArray);
	}//------------------------------------------------
	/**
	 * 
	 * @param emails the emails to find
	 * @return  user profiles
	 */
	public Iterable<UserProfile> findRecipientsByEmails(String emails)
	{
		return findRecipientsByEmails(toEmails(emails));
	}//------------------------------------------------
	/**
	 * 
	 * @param emailArray the emails to find
	 * @return  user profiles
	 */
	public Iterable<UserProfile> findRecipientsByEmails(String[] emailArray)
	{
		if(emailArray == null || emailArray.length == 0)
			return null;
		
		Iterable<UserProfile> users = this.repository.findAll(Arrays.asList(emailArray));
		
		
		if(users == null)
			return null;
		
		ArrayList<UserProfile> results = new ArrayList<UserProfile>();
		
		for (UserProfile userProfile : users)
		{
			if(userProfile == null)
				continue;
			
			results.add(userProfile);
		}
		
		if(results.isEmpty())
			return null;
		
		return results;
	}//------------------------------------------------
	/**
	 * 
	 * @param emailArray the emails to remove
	 */
	public void removeRecipientsByEmails(String[] emailArray)
	{
		if(emailArray == null)
			return;
		
		for (String email : emailArray)
		{
			if(email == null)
				continue;
			
			email = email.trim();
			
			this.repository.delete(email);
		}
	}//------------------------------------------------
	/**
	 * 
	 * @return all recipients
	 */
	public Iterable<UserProfile> findRecipients()
	{
		return this.repository.findAll();
	}//------------------------------------------------
	
	public void saveRecipient(UserProfile recipient)
	{
		if (recipient == null)
			throw new IllegalArgumentException("recipient is required");
		
		if (recipient.getId() == null || recipient.getId().length() == 0)
			recipient.setId(recipient.getEmail());
		
		if (recipient.getId() == null || recipient.getId().length() == 0)
			throw new IllegalArgumentException("recipient.getId() or email is required");
		
		
		this.template.put(recipient.getEmail(), recipient);
		//this.repository.save(recipient);
	}//------------------------------------------------
	/**
	 * Send a Package
	 * @param pack the package
	 */
	public void sendIt(Package pack)
	{
		String to = pack.getTo();
		
		if (to == null || to.length() == 0)
			throw new RequiredException("package.to");
		
		
		String from = pack.getFrom();
		
		from = from == null || from.length() == 0 ? this.defaultFromEmail : from;
		String subject = pack.getSubject();
		

		Map<Object,Object> map = null;
		
		String text = pack.getText();
		
		if (text == null || text.trim().length() == 0)
			throw new RequiredException("Email message body");
		
		//StringBuilder toAddress = new StringBuilder();
		//int toCount = 0;
		
		
		List<UserProfile> emailsToSend =  new ArrayList<UserProfile>();
		//List<UserProfile> emailsToSave =  new ArrayList<UserProfile>();
		
		String[] emails = toEmails(to);
		
		UserProfile user = null;
		
		for (String emailTo : emails)
		{
			if(emailTo == null)
				continue;
			
			emailTo = emailTo.trim();
			
			if(emailTo.length() == 0)
				continue;
			
			
			//check exact
			user = this.repository.findOne(emailTo);
			if(user != null)
			{
				//add single users
				emailsToSend.add(user);
			}
			else
			{
				List<UserProfile> tmp = this.repository.findByEmailLike(to);
				
				if(tmp == null || tmp.isEmpty())
				{
					//add new user
					user = new UserProfile();
					user.setEmail(emailTo);
				
					emailsToSend.add(user);
					//emailsToSave.add(user);
				}
				else
				{
					emailsToSend.addAll(tmp);
				}
				
			}
			
				
		}
		
		
		
		
		
		
		for (UserProfile recipient : emailsToSend)
		{
			map = JavaBean.toMap(recipient);
			text = Text.format(text, map);
			
			try
			{
				email.sendMail(recipient.getEmail(), from, subject,text);
				
				try
				{
					this.saveRecipient(recipient);
				}
				catch(Exception e)
				{
					Debugger.printError(e);
				}
			}
			catch(CommunicationException e)
			{
				Debugger.printError(e);
				
				//removing email
				if(e.getMessage() != null && (e.getMessage().contains("SMTPAddressFailedException") || 
						e.getMessage().contains("Invalid Addresses")))
				{
					Debugger.printWarn("removing:"+recipient.getEmail());
					this.removeRecipientsByEmails(recipient.getEmail());
				}
			}
			
		
			
			//toCount++;
			
			//send email in batches
			//if(toCount >= this.emailBatchSize)
			//{
				
			//	
			//	toCount = 0;
			//}
				
		}	
		
//		if(toAddress.length() > 0)
//		{
//			//send last
//			email.sendMailBcc(toAddress.toString(), from, subject,text);
//		}
		
		//save new emails
//		if(!emailsToSave.isEmpty())
//		{
//			for (UserProfile userProfile : emailsToSave)
//			{
//				this.saveRecipient(userProfile);
//			}
//		}
	}//------------------------------------------------
	
	/**
	 * @return the defaultFromEmail
	 */
	public String getDefaultFromEmail()
	{
		return defaultFromEmail;
	}//------------------------------------------------

	/**
	 * @param defaultFromEmail the defaultFromEmail to set
	 */
	public void setDefaultFromEmail(String defaultFromEmail)
	{
		this.defaultFromEmail = defaultFromEmail;
	}//------------------------------------------------
	private String[] toEmails(String emails)
	{
		String[] results = emails.split("[ ;:,]");
		
		return results;
	}//------------------------------------------------

	//@Value("${mail.batchSize}")
	//private int emailBatchSize;
	
	@Autowired
	private Email email;
	
	
	@Value(value="${defaultFromEmail}")
	private String defaultFromEmail;
	
	@Autowired
	RecipientDAORepository repository;
	
	@Autowired
	GemfireTemplate template;
	
	
}
