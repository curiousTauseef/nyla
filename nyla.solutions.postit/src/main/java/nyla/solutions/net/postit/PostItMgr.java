package nyla.solutions.net.postit;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import nyla.solutions.global.net.email.Email;
import nyla.solutions.net.postit.data.Recipient;

@Component
public class PostItMgr
{
	/**
	 * 
	 * @return all recipients
	 */
	public Iterable<Recipient> findRecipients()
	{
		return this.repository.findAll();
	}//------------------------------------------------
	
	public void saveRecipient(Recipient recipient)
	{
		if (recipient == null)
			throw new IllegalArgumentException("recipient is required");
		
		if (recipient.getRecipientId() == null || recipient.getRecipientId().length() == 0)
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
		
		email.sendMail(to, from, subject, pack.getText());
		
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


	@Autowired
	private Email email;
	
	
	@Value(value="${defaultFromEmail}")
	private String defaultFromEmail;
	
	@Autowired
	RecipientDAORepository repository;
	
	
}
