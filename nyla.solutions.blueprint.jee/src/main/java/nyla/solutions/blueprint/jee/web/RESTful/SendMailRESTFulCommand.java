package nyla.solutions.blueprint.jee.web.RESTful;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nyla.solutions.blueprint.jee.web.Web;
import nyla.solutions.global.exception.CommunicationException;
import nyla.solutions.global.exception.RequiredException;
import nyla.solutions.global.net.email.SendMail;
import nyla.solutions.global.util.Config;
import nyla.solutions.global.util.Debugger;
import nyla.solutions.global.util.Text;


/**
 * Service to send email messages
 * @author Gregory Green
 *
 */
public class SendMailRESTFulCommand implements RESTfulCommand
{
	/**
	 * Not implement
	 */
	public void delete(HttpServletRequest request, HttpServletResponse response)
	{			
	}//---------------------------------------------
	/**
	 * Not implement
	 */
	public void get(HttpServletRequest request, HttpServletResponse response)
	{		
	}//---------------------------------------------

	/**
	 * Send email message
	 * @param request the HTTP request
	 * @param response the HTTP request
	 */
	public void post(HttpServletRequest request, HttpServletResponse response)
	{
		try
		{
			Map<Object,Object> map = Config.getProperties();
			map.putAll(Web.toMap(request));
			sendEmail(map);
			
			Debugger.println(this,"forwarding request to:"+nextForwardView);
			
			request.getRequestDispatcher(nextForwardView).forward(request, response);
		} 
		catch (Exception e)
		{
			throw new CommunicationException(Debugger.stackTrace(e));
		}
	}//---------------------------------------------
	/**
	 * Calls the post to send email
	 */
	public void put(HttpServletRequest request, HttpServletResponse response)
	{
		post(request,response);
	}//---------------------------------------------
	/**
	 * Send the email message
	 * @param text the text the send
	 * @throws Exception
	 */
	protected 	void sendEmail(Map<?,?> map)
	throws Exception
	{
		if(this.sendMailService == null)
			throw new RequiredException("this.sendMail in SendMailRESTFulCommend");
		
		String message = Text.format(this.messageTemplate, map);
		this.sendMailService.sendMail(this.to, this.from, this.subject, message);
		
	}//---------------------------------------------	
	
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
		this.to = to;
	}//---------------------------------------------

	/**
	 * @return the from
	 */
	public String getFrom()
	{
		return from;
	}

	/**
	 * @param from the from to set
	 */
	public void setFrom(String from)
	{
		this.from = from;
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
		this.subject = subject;
	}

	/**
	 * @return the sendMail
	 */
	public SendMail getSendMailService()
	{
		return sendMailService;
	}

	/**
	 * @param sendMail the sendMail to set
	 */
	public void setSendMailService(SendMail sendMail)
	{
		this.sendMailService = sendMail;
	}//---------------------------------------------
	
	/**
	 * @return the nextForwardView
	 */
	public String getNextForwardView()
	{
		return nextForwardView;
	}
	/**
	 * @param nextForwardView the nextForwardView to set
	 */
	public void setNextForwardView(String nextForwardView)
	{
		this.nextForwardView = nextForwardView;
	}
	/**
	 * @return the messageTemplate
	 */
	public String getMessageTemplate()
	{
		return messageTemplate;
	}
	/**
	 * @param messageTemplate the messageTemplate to set
	 */
	public void setMessageTemplate(String messageTemplate)
	{
		this.messageTemplate = messageTemplate;
	}


	private String messageTemplate = Config.getProperty(this.getClass(),"messageTemplate","");
	private String nextForwardView = Config.getProperty(this.getClass(),"nextForwardView","");	
	private String to = Config.getProperty(SendMail.class,"to");
	private String from = Config.getProperty("mail.from","");
	private String subject = Config.getProperty(SendMail.class,"subject");	
	private SendMail sendMailService= null; 
	
}
