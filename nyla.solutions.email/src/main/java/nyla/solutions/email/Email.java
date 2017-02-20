package nyla.solutions.email;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.StringTokenizer;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.AuthenticationFailedException;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.naming.InitialContext;

import nyla.solutions.core.data.Data;
import nyla.solutions.core.exception.CommunicationException;
import nyla.solutions.core.exception.SecurityException;
import nyla.solutions.core.exception.SetupException;
import nyla.solutions.core.operations.logging.Log;
import nyla.solutions.core.patterns.Connectable;
import nyla.solutions.core.patterns.Disposable;
import nyla.solutions.core.patterns.conversion.TextToEmailsConverter;
import nyla.solutions.core.util.Config;
import nyla.solutions.core.util.Debugger;
import nyla.solutions.core.util.Text;
import nyla.solutions.email.data.EmailMessage;

//import javax.activation.*;

/**
 * <pre>
 * <b>Email </b> is a client interface for sending emails.
 * This class is used for most application email management.
 * 
 * 
 * 
 * Java Mail Properties
 * 
 * Secure email properties:
mail.from=website@morningstarccc.org
mail.from.password={cryption}-35 -57 79 -68 -25 124 94 109 66 75 -40 -37 -80 -109 -103 -52
mail.host=smtp.1and1.com
mail.auth.required=true
 * 
 * <b>mail.store.protocol</b> Protocol for retrieving email.
 * 
 * 
 * Example:
 * 
 * mail.store.protocol=imap
 * The bundled JavaMail library is IMAP.
 * 
 * <b>mail.transport.protocol</b> Protocol for sending email.
 * 
 * Example:
 * 
 * mail.transport.protocol=smtp
 * 
 * The bundled JavaMail library has supports for SMTP.
 * 
 * 
 * 
 * <b>mail.host</b> * The name of the mail host machine.
 * 
 * Example:
 * 
 * mail.host=mailserver mail.smtp.host= mail.smtp.port= Local machine.
 * 
 * 
 * 
 * <b>mail.user</b> Name of the default user for retrieving email.
 * 
 * Example:
 * 
 * 
 * 
 * mail.user=postmaster
 * 
 * Value of the user.name Java system property.
 * 
 * 
 * 
 * <b>mail.protocol.host</b> Mail host for a specific protocol.
 * 
 * For example, you can set mail.SMTP.host and mail.IMAP.host to
 * 
 * different machine names.
 * 
 * 
 * 
 * Examples:
 * 
 * mail.smtp.host=mail.mydom.com mail.imap.host=localhost
 * 
 * Value of the mail.host property.
 * 
 * 
 * 
 * <b>mail.protocol.user</b>
 * 
 * Protocol-specific default user name for logging into a mailer server.
 * 
 * Examples:
 * 
 * mail.smtp.user=weblogicmail.imap.user=appuser
 * 
 * Value of the mail.user property.
 * 
 * 
 * 
 * <b>mail.from</b> The default return address.
 * 
 * Examples:
 * 
 * mail.from=master@mydom.com
 * 
 * username@host
 * 
 * mail.smtp.auth.required=true
 * 
 * <b>mail.debug</b>
 * 
 * Set to True to enable JavaMail debug output.
 *  #==========================================================
 *          #Email Settings mail.debug=true mail.transport.protocol=smtp
 *          mail.from=XXXX mail.smtp.host=host mail.smtp.auth=false
 *          mail.smtp.user=XXX mail.password=
 * 
 * </pre>
 * 
 * @version 1.0
 *          
 */
public class Email implements EmailTags, Disposable, SendMail, Connectable
{	

	/**
	 * Default constructor
	 */
	public Email()
	{
	} // ----------------------------------------------------------
	/**
	 * Connect to the email server
	 */
	public synchronized void connect()
	{
		if(this.isConnected())
			return;
		try
		{
			if (Config.getPropertyBoolean(MAIL_SESSION_JNDI_USED, false)
					.booleanValue())
			{

				initFromJNDI();

			}
			else
			{

				init();
			}
			
		}
		catch (SetupException e)
		{

			throw e;
		}
		catch (Exception e)
		{

			throw new SetupException(e);
		}
		
	}//------------------------------------------------
	/**
	 * @return if the mail session is connected
	 */
	@Override
	public synchronized boolean isConnected()
	{
		return this.mailSession != null;
	}//------------------------------------------------
	/**
	 * Close the transport connection
	 * @see nyla.solutions.core.patterns.Disposable#dispose()
	 */
	public void dispose()
	{
		try
		{

			if (this.mailTransport != null)
				this.mailTransport.close();
			
			this.mailSession = null;

		}
		catch (Exception e)
		{
			this.logger.warn(e);
		}
		
		try
		{
			if(this.store != null)
				this.store.close();
			
			this.store = null;
		}
		catch(Exception e)
		{
			this.logger.warn(e);
		}
	}// --------------------------------------------
	/**
	 * 
	 * @param aMap the map variables
	 * @throws javax.mail.MessagingException unknown error
	 * @throws IOException  unknown error
	 * @throws Exception unknown error
	 */
	public void sendMail(Map<Object, Object> aMap)
	throws javax.mail.MessagingException, IOException, Exception
	{

		String toMail = (String) aMap.get(TO);

		toMail = Config.getProperty(Email.class, "to.email." + toMail);

		String templateName = (String) aMap.get(TEMPLATE_NAME);

		this.sendMail(toMail, templateName, aMap, Locale.getDefault());

	}// --------------------------------------------

	/**
	 * ending out E-mails through SMTP E-mail server. From the system
	 * @param to E-mail TO: field in Internet E-mail address format. If there
	 *            are more than one E-mail addresses
	 * @param subject E-mail subject line.
	 * @param messageBody  E-mail body.
	 */
	public synchronized void sendMail(String to, String subject,
			String messageBody)
	{

		sendMail(to, Config.getProperty(EmailTags.MAIL_FROM_ADDRESS_PROP),
				subject, messageBody);

	}// --------------------------------------------

	/**
	 * Sending out E-mails through SMTP E-mail server.
	 * 
	 * @param aTo E-mail TO: field in Internet E-mail address format. If there
	 *            are more than one E-mail addresses, separate them by
	 *            SysConst.EMAIL_DELIMITER_IND.
	 * @param aFrom from address
	 * @param aSubject E-mail subject line.
	 * @param aMessageBody E-mail body.
	 */

	public synchronized void sendMail(String aTo, String aFrom,
			String aSubject, String aMessageBody)
	{

		sendMail(aTo, aFrom, aSubject, aMessageBody, (File) null);
	}// --------------------------------------------
	
	/**
	 * Sending out E-mails through SMTP E-mail server.
	 * @param aTo the email to
	 * @param aSubject the email subject
	 * @param aMessageBody the message body
	 * @param aFile the file to send
	 * @throws Exception unknown error occurs
	 */

	public synchronized void sendMail(String aTo, String aSubject,
			String aMessageBody, File aFile) throws Exception
	{
		sendMail(aTo, Config.getProperty(EmailTags.MAIL_FROM_ADDRESS_PROP),
				aSubject, aMessageBody, aFile);
	}// --------------------------------------------
	/**
	 * 
	 * @param to the list bcc address
	 * @param from from user
	 * @param subject the subject
	 * @param messageBody the message body
	 */
	public synchronized void sendMailBcc(String to, String from,
	String subject, String messageBody)
	{
		sendMail(to,Message.RecipientType.BCC, from,
		subject, messageBody, null);
	}//------------------------------------------------
	public synchronized void sendMail(String to, String from,
	String subject, String aMessageBody, File aFile)
	{
		sendMail(to,Message.RecipientType.TO, from,
		subject, aMessageBody, aFile);
	}//------------------------------------------------
	/**
	 * Sending out E-mails through SMTP E-mail server.
	 * 
	 * @param to E-mail TO: field in internesendMailt E-mail address format. If
	 *            there are more than one E-mail addresses, separate them by
	 *            SysConst.EMAIL_DELIMITER_IND.
	 * @param aFrom from address
	 * @param aFile the file to send
	 * @param aSubject E-mail subject line.
	 * @param aMessageBody E-mail body.
	 */

	private synchronized void sendMail(String to,Message.RecipientType recipientType, String aFrom,
	String aSubject, String aMessageBody, File aFile)
	{
		
		if(to == null || to.length() == 0)
					throw new IllegalArgumentException("to is required");
		
		this.connect();
		
		try
		{
			MimeMessage mailMessage = new MimeMessage(this.mailSession);

			mailMessage.setFrom(new InternetAddress(aFrom));

			if (aMessageBody == null)
			{
				aMessageBody = "";

			}

			//Message.RecipientType.TO
			mailMessage.setRecipients(recipientType,
					this.getAllEmailAddress(to));

			String subject = aSubject == null ? this.defaultSubject : aSubject;
			mailMessage.setSubject(subject);

			// attach file
			if (aFile != null)
			{
				this.attach(mailMessage, aFile, aMessageBody);
			}
			else
			{
				mailMessage.setContent(aMessageBody, this.contentType);
			}

			sendMessage(mailMessage);
		}
		catch (Exception e)
		{
			throw new CommunicationException(e);
		}
	}// --------------------------------------------

	/**
	 * 
	 * @param aMailMessage
	 * @throws MessagingException
	 */
	private void sendMessage(MimeMessage aMailMessage)
			throws MessagingException
	{
		boolean authNeeed = this.isAuthenicationRequired();
		Debugger.println(this, "authNeeed=" + authNeeed);

		if (authNeeed)
		{
			// -------------------------------------------
			// set properties
			Properties props = System.getProperties();
			props.put("mail.smtp.auth", String.valueOf(authNeeed));

			// try to connect and send it
			aMailMessage.saveChanges();
			Transport tp = this.mailSession.getTransport("smtp");

			tp.connect(this.smtpHost,
					this.mailFromUser,
					String.valueOf(this.mailFromPassword));
			// send the thing off
			Transport.send(aMailMessage, aMailMessage.getAllRecipients());
			tp.close();
			// -------------------------------------------
			// aMailMessage.saveChanges();
			// this.the_mailTransport.sendMessage(aMailMessage,
			// aMailMessage.getAllRecipients());

		}
		else

		{

			Transport.send(aMailMessage);

		}
	}// --------------------------------------------

	/**
	 * 
	 * Check to see if there are more than one E-mail addresses separated
	 * 
	 * by EMAIL_DELIMITER_IND and put them into array, so SMTP
	 * 
	 * E-mail server can send them out in one single call.
	 * 
	 * 
	 * 
	 * @param ToEmailAddress E-mail TO: field in internet E-mail address format.
	 *            If there are more than one E-mail addresses, separate them by
	 *            SysConst.EMAIL_DELIMITER_IND.
	 * 
	 * 
	 * 
	 * @return Array of E-mail addresses.
	 */

	private InternetAddress[] getAllEmailAddress(String aToEmailAddress)

	throws AddressException

	{

		if (aToEmailAddress == null)

			throw new IllegalArgumentException(
					"aToEmailAddress required in Email");

		StringTokenizer tokens = new StringTokenizer(aToEmailAddress,
				EMAIL_DELIMITER_IND);

		if (tokens.countTokens() <= 0)

		{

			throw (new IllegalArgumentException(
					"No TO E-mail addresses passed in."));

		}

		InternetAddress[] emailAddresses = new InternetAddress[tokens
				.countTokens()];

		int i = 0;

		while (tokens.hasMoreTokens())

		{

			emailAddresses[i] = new InternetAddress(tokens.nextToken());

			i++;

		}

		return (emailAddresses);

	}// --------------------------------------------

	/**
	 * Sends a email message to specified address
	 * @param to to email
	 * @param aFrom from email
	 * @param aSubject subject line 
	 * @param aTemplateNM the text template
	 * @param aMap the bind variables
	 * @param aLocale the local
	 * @throws javax.mail.MessagingException when unknown error occurs
	 * @throws IOException when unknown error occurs
	 * @throws Exception when unknown error occurs
	 */
	public void sendMail(String to, String aFrom, String aSubject,
			String aTemplateNM, Map<Object, Object> aMap, Locale aLocale)

	throws javax.mail.MessagingException, IOException, Exception

	{

		aMap.put(TO, to);

		aMap.put(EmailTags.FROM_EMAIL, aFrom);

		aMap.put(EmailTags.SUBJECT, aSubject);

		sendMail(to, aTemplateNM, aMap, aLocale);

	}// --------------------------------------------

	/**
	 * 
	 * 
	 * 
	 * Sends a email message to specified address
	 * 
	 * 
	 * 
	 * @param aTo 
	 * 
	 * @param aTemplateNM 
	 * 
	 * @param aMap
	 * 
	 *          
	 * @throws 
	 */

	/**
	 * 
	 * @param aTo the recipient
	 * @param aTemplateNM the bind Template (see Text object)
	 * @param aMap   the bind template map
	 * @param aLocale the locale
	 * @throws javax.mail.MessagingException unknown error occurs
	 * @throws IOException  unknown error occurs
	 * @throws Exception  unknown error occurs
	 */
	public synchronized void sendMail(String aTo, String aTemplateNM,
			Map<Object, Object> aMap, Locale aLocale)

	throws javax.mail.MessagingException, IOException, Exception
	{

		logger.debug("sendMail");
		
		this.connect();

		Object o = aMap.get(SUBJECT);

		// process Subject

		String SubjectStr = "System Contact.";

		if (o != null && !Data.isNull(o.toString()))

			SubjectStr = o.toString();

		o = aMap.get(CATEGORY);

		// process Subject Category

		if (o != null && !Data.isNull(o.toString()))

			SubjectStr += " -- Category --" + o;

		// Create Mail Message

		MimeMessage mailMessage = new MimeMessage(mailSession);

		mailMessage.setFrom(new InternetAddress(Config
				.getProperty(Email.FROM_EMAIL),

		(String) aMap.get(Email.FROM_NAME)));

		// GET message body from template

		String MessageBody = Text
				.formatFromTemplate(aTemplateNM, aMap, aLocale);

		Debugger.println(MessageBody);

		InternetAddress[] addr = this.getAllEmailAddress(aTo);

		mailMessage.addRecipients(Message.RecipientType.TO, addr);

		mailMessage.setSubject(SubjectStr);

		mailMessage.setSentDate(new Date());

		// Process CC

		o = aMap.get(CC);

		String cc_string = null;

		if (o != null && !Data.isNull(o.toString()))
		{
			cc_string = o.toString();
		}

		if (cc_string != null)
		{

			InternetAddress[] cc_addr = this.getAllEmailAddress(cc_string);
			mailMessage.addRecipients(Message.RecipientType.CC, cc_addr);
		}

		Multipart mp = new MimeMultipart();

		MimeBodyPart mbp1 = new MimeBodyPart();

		mbp1.setContent(MessageBody, contentType);

		mp.addBodyPart(mbp1);

		// Debug
		javax.mail.Address[] addrList = mailMessage.getAllRecipients();
		for (int i = 0; i < addrList.length; i++)
		{
			logger.debug("TO:" + addrList[i]);
		}

		mailMessage.setContent(mp);

		if (isAuthenicationRequired())
		{
			logger.debug("CONNECTED=" + this.mailTransport.isConnected());
			this.mailTransport.sendMessage(mailMessage,
					mailMessage.getAllRecipients());
		}

		else
		{
			Transport.send(mailMessage);

		}

	} // ----------------------------------------------------------
	private synchronized void initFromJNDI() throws Exception
	{

		InitialContext initialContext = this.getContext();

		this.mailSession = (Session) initialContext.lookup(Config
				.getProperty(EmailTags.MAIL_SESSION_JNDI_NAME));

		this.mailTransport = this.mailSession.getTransport();

	}// --------------------------------------------
	public synchronized Collection<String> shouldRemoveEmails()
			throws javax.mail.MessagingException, IOException
	{
		int batchCount = 100;
		int startIndex = 1;
		
		String pattern = ".*delivered.*||.*unsubscribe.*||(.*invalid.*${AND}email.*)";
		
		Collection<EmailMessage>  emailMesssages = readMatches(batchCount, startIndex, pattern);
		if(emailMesssages == null || emailMesssages.isEmpty())
			return null;
		
		HashSet<String> results = new HashSet<String>(batchCount);
		
		String subject =null, body  =null;
		
		do
		{
			
			for (EmailMessage emailMessage : emailMesssages)
			{
				//Should remove unscribe subjects
				subject = emailMessage.getSubject();
				if(subject != null)
				{
					subject = subject.toLowerCase(Locale.US);
					
					if(subject.contains("unsubscribe"))
					{
						if(emailMessage.getFrom() != null)
						{
							results.addAll(emailMessage.getFrom());
						}
					}
				}
				
				body = emailMessage.getContent();
				if(body == null)
					continue;
				
				body = body.trim();
				if(body.length() == 0)
					continue;
				
				//find all emails
				Collection<String> emailsToRemove = findEmails(body);
				if(emailsToRemove != null)
					results.addAll(emailsToRemove);
			}
			
			//iterate
			startIndex = startIndex + batchCount;
			emailMesssages = readMatches(batchCount, startIndex, pattern);
			
		} while (emailMesssages != null && !emailMesssages.isEmpty());
		
		
		if(results.isEmpty())
			return null;
		
		return results;
	}//------------------------------------------------
	/**
	 * 
	 * @param context the email message text
	 * @return new TextToEmailsConverter().convert(context);
	 */
	public Collection<String> findEmails(String context)
	{
		return new TextToEmailsConverter().convert(context);
	}//------------------------------------------------
	
	/**
	 * 
	 * @param count number of records to read
	 * @param startIndex start index 1 or higher
	 * @param pattern the search pattern
	 * @return collection of the message
	 * @throws javax.mail.MessagingException a message exception occurs
	 * @throws IOException IO exception occurs
	 */
	public synchronized Collection<EmailMessage> readMatches(int count,  int startIndex, String pattern)
	throws javax.mail.MessagingException, IOException
	{

		logger.debug("readMail");
		
		this.connect();
		
		try(ReadMail reader = new ReadMail(this.store))
		{
			return reader.readMatches(count, startIndex, pattern,false);
		}
	}//------------------------------------------------
	/**
	 * Initialize the email object
	 * 
	 * @throws Exception
	 */

	private synchronized void init()
	{

			try
			{
				Properties props = System.getProperties();
				
				Properties sysProperties = new Properties();
				for(Map.Entry<Object, Object> entry: props.entrySet())
				{
					sysProperties.setProperty(String.valueOf(entry.getKey()), String.valueOf(entry.getValue()));
				}
				sysProperties.put("mail.imap.host",
						Config.getProperty("mail.imap.host", ""));
				
				sysProperties.put("mail.imap.port",
						Config.getProperty("mail.imap.port", "143"));

				sysProperties.put("mail.smtp.ssl.enable",
						Config.getProperty("mail.smtp.ssl.enable", ""));
				sysProperties.put("mail.smtp.host",smtpHost);
				sysProperties.put("mail.host",smtpHost);
				//sysProperties.put("mail.smtp.host",
				//		Config.getProperty(MAIL_SERVER_PROP, ""));
				
				sysProperties.setProperty("mail.imap.auth.plain.disable", "false");
				sysProperties.setProperty("mail.imap.auth.ntlm.disable", "false");
				sysProperties.setProperty("mail.store.protocol", "imap");
				
				sysProperties.setProperty("mail.imap.starttls.enable","true");
				sysProperties.setProperty("mail.imap.starttls.required","true");
				sysProperties.setProperty("mail.imap.socketFactory.port","993");
				sysProperties.setProperty("mail.imap.socketFactory.class","javax.net.ssl.SSLSocketFactory");
				sysProperties.setProperty("mail.imap.auth.login.disable","false");
				sysProperties.setProperty("mail.imap.socketFactory.fallback","false");
				sysProperties.setProperty("mail.imap.debug", "true");
				sysProperties.setProperty("mail.imap.sasl.enable", "true");
				sysProperties.setProperty("mail.imap.ssl.enable","true");
				sysProperties.setProperty("mail.imap.auth.plain.disable","true");
				sysProperties.setProperty("mail.imap.auth.mechanisms","LOGIN");

				sysProperties.put(MAIL_AUTHENICATION_REQUIRED_PROP, this.authenicationRequired);
				
				sysProperties.put("mail.smtp.port",
						Config.getProperty("mail.smtp.port", "25"));

				sysProperties.put("mail.webdav.host",
						Config.getProperty("mail.webdav.host", ""));
				sysProperties.put("mail.weddav.port",
						Config.getProperty("mail.webdav.port", "143"));

				// mail.debug

				// SysProperties.put("mail.port",
				// Config.getProperty("mail.port","25"));

				sysProperties.put("mail.debug", "true");
				sysProperties.put("mail.from", this.defaultFrom);
				sysProperties.put("mail.smtp.from",
						Config.getProperty("mail.smtp.from",this.defaultFrom));

				// mail.imap.sasl.authorizationid

				sysProperties.put("mail.imap.sasl.authorizationid",
						Config.getProperty(EmailTags.MAIL_FROM_ADDRESS_PROP,this.defaultFrom));

				sysProperties.put("mail.smtp.auth",
						Config.getProperty("mail.smtp.auth", "false"));

				mailSession = Session.getDefaultInstance(sysProperties,

				new javax.mail.Authenticator()
				{

					protected PasswordAuthentication getPasswordAuthentication()
					{

						return new PasswordAuthentication(
								getMailFromUser(),
								Text.toString(getMailFromPassword()));
					}

				});

				mailTransport = mailSession.getTransport(Config.getProperty(
						"mail.protocol", "smtp"));

				initStore();

				
				
				Debugger.println(this, "properties=" + mailSession.getProperties());

				if (isAuthenicationRequired())
				{

					String server = this.smtpHost;
					int port = Config.getPropertyInteger("mail.port", 25)
							.intValue();
					String from = this.defaultFrom;
					char[] password = this.getMailFromPassword();

					mailTransport.connect(server, port, from, new String(password));

				}
			}
			catch (NoSuchProviderException e)
			{
				throw new SetupException(e);
			}
			catch (MessagingException e)
			{
				throw new CommunicationException(e);
			}

	} // -----------------------------------------------------------
	private void initStore()
	throws MessagingException
	{

		String protocol = Config.getProperty(
				"mail.read.protocol", "");
		if(protocol.length() == 0)
		{
			protocol  = "imap";
		}
	
		this.store = mailSession.getStore(protocol);
		
		if(store == null)
			return;
		
		if (!isAuthenicationRequired())
			return;
		
		String user = getMailFromUser();
		if(user == null|| user.trim().length() == 0)
					throw new SetupException("user is required. Set property:"+MAIL_FROM_ADDRESS_PROP);
		
		String mailReadHost = Config.getProperty("mail."+protocol+".host");
		
		char[] passwordArray = getMailFromPassword();
		
		String password = "";
		
		if(passwordArray != null && passwordArray.length > 0)
			password = new String(passwordArray);
		
		try{
			store.connect(mailReadHost, user,
					password);
		}
		catch(AuthenticationFailedException e)
		{
			if(password == null || password.trim().length() == 0)
				throw new SecurityException("Required password not provided in property:"+EmailTags.MAIL_FROM_PASSWORD_PROP);
			else
				throw new CommunicationException("Unable authenticate to host:"+mailReadHost+" user:"+user+" ERROR:"+e.getMessage(),e);
		}
		catch(MessagingException e)
		{
			throw new CommunicationException("Unable to connect to host:"+mailReadHost+" user:"+user+" ERROR:"+e.getMessage(),e);
		}
		catch(RuntimeException e)
		{
			throw new CommunicationException("Unable to connect to host:"+mailReadHost+" user:"+user+" ERROR:"+e.getMessage(),e);
		}		
		
		
	}//------------------------------------------------

	/**
	 * 
	 * @param aMessage the message to add to
	 * 
	 * @param aFile the file attachment
	 */

	private synchronized void attach(MimeMessage aMessage, File aFile, String aMessageText)
    throws Exception
	{

		// DocumentDelegate d = new DocumentDelegate();
		Multipart mp = new MimeMultipart();

		if (aMessageText != null && aMessageText.length() > 0)
		{
			MimeBodyPart textPart = new MimeBodyPart();

			textPart.setContent(aMessageText, this.contentType);

			mp.addBodyPart(textPart);
		}

		MimeBodyPart mbp = new MimeBodyPart();

		mbp.setDataHandler(new DataHandler(new FileDataSource(aFile)));

		mbp.setFileName(aFile.getName());

		mp.addBodyPart(mbp);

		aMessage.setContent(mp);

	}// --------------------------------------------

	
	/**
	 * 
	 * @param map the map
	 * @param appendtext the text to append
	 */
	public void appendCC(Map<Object, Object> map, String appendtext)
	{
		StringBuffer cc = null;

		if (map.get(EmailTags.CC) == null)
		{

			cc = new StringBuffer();

		}
		else
		{

			cc = new StringBuffer((String) map.get(EmailTags.CC));

		}

		if (cc.length() == 0)
		{
			cc.append(appendtext);
		}
		else
		{
			cc.append(";" + appendtext);
		}

		map.put(EmailTags.CC, cc.toString());

	}// --------------------------------------------
	private InitialContext getContext() throws Exception
	{

		return new InitialContext();

	}// --------------------------------------------

	/**
	 * @return the contentType
	 */
	public synchronized String getContentType()
	{
		return contentType;
	}

	/**
	 * @param contentType the contentType to set
	 */
	public synchronized void setContentType(String contentType)
	{
		this.contentType = contentType;
	}//------------------------------------------------

	
	/**
	 * @return the defaultFrom
	 */
	public String getDefaultFrom()
	{
		return defaultFrom;
	}//------------------------------------------------

	/**
	 * @param defaultFrom the defaultFrom to set
	 */
	public void setDefaultFrom(String defaultFrom)
	{
		this.defaultFrom = defaultFrom;
	}//------------------------------------------------
	
	/**
	 * @return the smtpHost
	 */
	public String getSmtpHost()
	{
		return smtpHost;
	}
	/**
	 * @param smtpHost the smtpHost to set
	 */
	public void setSmtpHost(String smtpHost)
	{
		this.smtpHost = smtpHost;
	}


	/**
	 * @return the authenicationRequired
	 */
	public boolean isAuthenicationRequired()
	{
		return authenicationRequired;
	}
	/**
	 * @param authenicationRequired the authenicationRequired to set
	 */
	public void setAuthenicationRequired(boolean authenicationRequired)
	{
		this.authenicationRequired = authenicationRequired;
	}


	/**
	 * @return the mailFromUser
	 */
	public String getMailFromUser()
	{
		return mailFromUser;
	}
	/**
	 * @param mailFromUser the mailFromUser to set
	 */
	public void setMailFromUser(String mailFromUser)
	{
		this.mailFromUser = mailFromUser;
	}
	/**
	 * @return the mailFromPassword
	 */
	public char[] getMailFromPassword()
	{
		if(mailFromPassword == null || mailFromPassword.length == 0)
			return null;
		
		return Arrays.copyOf(mailFromPassword,mailFromPassword.length);
	}//------------------------------------------------
	/**
	 * @param mailFromPassword the mailFromPassword to set
	 */
	public void setMailFromPassword(char[] mailFromPassword)
	{
		if(mailFromPassword == null || mailFromPassword.length == 0)
		{
			this.mailFromPassword = null;
		}
		else
		{
			this.mailFromPassword = Arrays.copyOf(mailFromPassword,mailFromPassword.length);	
		}
			
		
	}//------------------------------------------------
	/**
	 * @return the mailTransport
	 */
	public synchronized Transport getMailTransport()
	{
		return mailTransport;
	}
	/**
	 * @param mailTransport the mailTransport to set
	 */
	public synchronized void setMailTransport(Transport mailTransport)
	{
		this.mailTransport = mailTransport;
	}

	/**
	 * @return the defaultSubject
	 */
	public String getDefaultSubject()
	{
		return defaultSubject;
	}
	/**
	 * @param defaultSubject the defaultSubject to set
	 */
	public void setDefaultSubject(String defaultSubject)
	{
		this.defaultSubject = defaultSubject;
	}


	private String defaultSubject = Config.getProperty(Email.MAIL_SUBJECT_PROP,"");
	private String mailFromUser = Config.getProperty(Email.MAIL_FROM_ADDRESS_PROP,"");
	private char[] mailFromPassword = Config
			.getPropertyPassword(EmailTags.MAIL_FROM_PASSWORD_PROP,"");
	private boolean authenicationRequired = Config.getPropertyBoolean(MAIL_AUTHENICATION_REQUIRED_PROP,
			Boolean.FALSE).booleanValue();
	private String smtpHost = Config.getProperty(MAIL_SERVER_PROP,"");
	private Session mailSession = null;
	private Store store = null;
	private String contentType = Config.getProperty(Email.class.getName()
			+ ".contentType", "text/html");
	private Transport mailTransport = null;
	private Log logger = Debugger.getLog(getClass());
	private String defaultFrom = Config.getProperty(Email.MAIL_FROM_ADDRESS_PROP,"");	

}
