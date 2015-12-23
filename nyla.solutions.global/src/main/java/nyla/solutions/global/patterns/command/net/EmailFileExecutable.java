package nyla.solutions.global.patterns.command.net;


import nyla.solutions.global.data.Textable;
import nyla.solutions.global.exception.ConfigException;
import nyla.solutions.global.exception.SystemException;
import nyla.solutions.global.io.Fileable;
import nyla.solutions.global.net.email.Email;
import nyla.solutions.global.patterns.command.Environment;
import nyla.solutions.global.patterns.command.Executable;
import nyla.solutions.global.util.Config;


/**
 * @author Gregory Green
 *
 */
public class EmailFileExecutable implements Executable
{
	/**
	 * Send a file via email
	 */
	public Integer execute(Environment env)
	{
		sendMail();
		
		return 0;
	}//---------------------------------------------

	public void sendMail()
	{
		try
		{
			if(textable == null)
				throw new ConfigException("Textable object not provided in "+this.getClass().getName());
			
			//create send mail client
			email.sendMail(to, subject, textable.getText(), fileable.getFile());
		
		} 
		catch (Exception e)
		{
			throw new SystemException(e);
		}
	}// --------------------------------------------------------

	
	/**
	 * @return the to
	 */
	public String getTo()
	{
		return to;
	}
	/**
	 * @param to the to to set
	 */
	public void setTo(String to)
	{
		this.to = Config.interpret(to);
	}
	/**
	 * @return the subject
	 */
	public String getSubject()
	{
		return subject;
	}
	/**
	 * @param subject the subject to set
	 */
	public void setSubject(String subject)
	{
		this.subject = Config.interpret(subject);
	}
	/**
	 * @return the email
	 */
	public Email getEmail()
	{
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(Email email)
	{
		this.email = email;
	}
	/**
	 * @return the textable
	 */
	public Textable getTextable()
	{
		return textable;
	}
	/**
	 * @param textable the textable to set
	 */
	public void setTextable(Textable textable)
	{
		this.textable = textable;
	}

	
	/**
	 * @return the fileable
	 */
	public Fileable getFileable()
	{
		return fileable;
	}

	/**
	 * @param fileable the fileable to set
	 */
	public void setFileable(Fileable fileable)
	{
		this.fileable = fileable;
	}


	private String to = Config.getProperty(this.getClass(),"to");
	private String subject = Config.getProperty(this.getClass(),"subject");
	private Email email = new Email();
	private Fileable fileable = null;
	private Textable textable = null;

}
