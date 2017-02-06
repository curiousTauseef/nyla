package nyla.solutions.net.postit;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import nyla.solutions.core.exception.RequiredException;
import nyla.solutions.core.security.user.data.UserProfile;
import nyla.solutions.core.util.JavaBean;
import nyla.solutions.core.util.Text;
import nyla.solutions.email.Email;

@Component
public class PostItMgr
{
	
	
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
			throw new IllegalArgumentException("recipient.getId() is required");
		
		this.repository.save(recipient);
	}//------------------------------------------------
	/**
	 * Send a Package
	 * @param pack the package
	 */
	public void sendIt(Package pack)
	{
		String to = pack.getTo();
		String from = pack.getFrom();
		
		from = from == null || from.length() == 0 ? this.defaultFromEmail : from;
		String subject = pack.getSubject();
		
		
		List<UserProfile> list = this.repository.findByEmailLike(to);
		
		Map<Object,Object> map = null;
		
		String text = pack.getText();
		
		if (text == null || text.trim().length() == 0)
			throw new RequiredException("Email message body");
		
		StringBuilder toAddress = new StringBuilder();
		int toCount = 0;
		
		for (UserProfile recipient : list)
		{
			map = JavaBean.toMap(recipient);
			text = Text.format(text, map);
			
			if(toAddress.length() > 0)
			{
				toAddress.append(Email.EMAIL_DELIMITER_IND);
			}
			
			toAddress.append(recipient.getEmail());
			toCount++;
			
			//send email in batches
			if(toCount >= this.emailBatchSize)
			{
				email.sendMailBcc(toAddress.toString(), from, subject,text);	
				toAddress.setLength(0);
				toCount = 0;
			}
				
		}	
		
		if(toAddress.length() > 0)
		{
			//send last
			email.sendMailBcc(toAddress.toString(), from, subject,text);
		}
	}//------------------------------------------------
	
	/**
	 * @return the defaultFromEmail
	 */
	public String getDefaultFromEmail()
	{
		return defaultFromEmail;
	}

	/**
	 * @param defaultFromEmail the defaultFromEmail to set
	 */
	public void setDefaultFromEmail(String defaultFromEmail)
	{
		this.defaultFromEmail = defaultFromEmail;
	}


	@Value("${mail.batchSize}")
	private int emailBatchSize;
	
	@Autowired
	private Email email;
	
	
	@Value(value="${defaultFromEmail}")
	private String defaultFromEmail;
	
	@Autowired
	RecipientDAORepository repository;
	
	
}
