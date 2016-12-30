package nyla.solutions.global.net.email;

import nyla.solutions.core.patterns.decorator.TextDecorator;
import nyla.solutions.core.util.Config;
import nyla.solutions.global.patterns.command.Command;

/**
 * @author Gregory Green
 *
 */
public class EmailCommand<ReturnType,DecoratorType> implements Command<Object, DecoratorType>
{
	/**
	 * Send the mail
	 * @see nyla.solutions.global.patterns.command.Command#execute(java.lang.Object)
	 */
	@Override
	public ReturnType execute(DecoratorType input)
	{
		textDecorator.setTarget(input);
		
		
		Email email = new Email();
		email.sendMail(to, subject, textDecorator.getText());
		return null;
	}// --------------------------------------------------------
	
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
	 * @return the textDecorator
	 */
	public TextDecorator<DecoratorType> getTextDecorator()
	{
		return textDecorator;
	}
	/**
	 * @param textDecorator the textDecorator to set
	 */
	public void setTextDecorator(TextDecorator<DecoratorType> textDecorator)
	{
		this.textDecorator = textDecorator;
	}


	private String subject;
	private String to;
	private TextDecorator<DecoratorType> textDecorator;

}
