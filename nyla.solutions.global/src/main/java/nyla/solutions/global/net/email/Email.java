package nyla.solutions.global.net.email;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.StringTokenizer;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.annotation.concurrent.NotThreadSafe;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.naming.InitialContext;

import nyla.solutions.global.data.Data;
import nyla.solutions.global.exception.CommunicationException;
import nyla.solutions.global.exception.SetupException;
import nyla.solutions.global.operations.logging.Log;
import nyla.solutions.global.patterns.Disposable;
import nyla.solutions.global.util.Config;
import nyla.solutions.global.util.Debugger;
import nyla.solutions.global.util.Text;

//import javax.activation.*;

/**
 * 
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
 * <b>mail.protocol.user</a>
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
 * <b>mail.from</a> The default return address.
 * 
 * Examples:
 * 
 * mail.from=master@mydom.com
 * 
 * username@host
 * 
 * mail.smtp.auth.required=true
 * 
 * <b>mail.debug</>
 * 
 * Set to True to enable JavaMail debug output.
 * 
 * 
 * 
 * @version 1.0 #==========================================================
 *          #Email Settings mail.debug=true mail.transport.protocol=smtp
 *          mail.from=XXXX mail.smtp.host=host mail.smtp.auth=false
 *          mail.smtp.user=XXX mail.password=
 */
@NotThreadSafe
public class Email implements EmailTags, Disposable, SendMail
{

	public static final String FROM_SYSTEM = Config
			.getProperty(Email.MAIL_FROM_ADDRESS_PROP);

	/**
	 * Default constructor
	 */
	public Email()
	{
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
		catch (Exception e)
		{

			throw new SetupException(e);

		}

	} // ----------------------------------------------------------

	/**
	 * Close the transport connection
	 * 
	 * @see com.merck.mrl.eln.global.patterns.Disposable#dispose()
	 */
	public void dispose()

	{

		try

		{

			if (this.mailTransport != null)

				this.mailTransport.close();

		}

		catch (Exception e)

		{

			this.logger.warn(e);

		}

	}// --------------------------------------------

	/**
	 * 
	 * Sends an email message
	 * 
	 * 
	 * 
	 * @param aMap
	 */

	public void sendMail(Map<Object, Object> aMap)

	throws javax.mail.MessagingException,

	IOException, Exception

	{

		String toMail = (String) aMap.get(TO);

		toMail = Config.getProperty(Email.class, "to.email." + toMail);

		String templateName = (String) aMap.get(TEMPLATE_NAME);

		this.sendMail(toMail, templateName, aMap, Locale.getDefault());

	}// --------------------------------------------

	/**
	 * 
	 * Sending out E-mails through SMTP E-mail server. From the system
	 * 
	 * @param aTo E-mail TO: field in Internet E-mail address format. If there
	 *            are more than one E-mail addresses, separate them by
	 *            SysConst.EMAIL_DELIMITER_IND.
	 * @param aFrom from address
	 * @param aSubject E-mail subject line.
	 * @param aMessageBody E-mail body.
	 */
	public synchronized void sendMail(String aTo, String aSubject,
			String aMessageBody)
	{

		sendMail(aTo, Config.getProperty(EmailTags.MAIL_FROM_ADDRESS_PROP),
				aSubject, aMessageBody);

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
	 * 
	 * Sending out E-mails through SMTP E-mail server.
	 * 
	 * 
	 * 
	 * @param aTo E-mail TO: field in internet E-mail address format. If there
	 *            are more than one E-mail addresses, separate them by
	 *            SysConst.EMAIL_DELIMITER_IND.
	 * @param aFrom from address
	 * @param aSubject E-mail subject line.
	 * @param aMessageBody E-mail body.
	 */

	public synchronized void sendMail(String aTo, String aSubject,
			String aMessageBody, File aFile) throws Exception
	{
		sendMail(aTo, Config.getProperty(EmailTags.MAIL_FROM_ADDRESS_PROP),
				aSubject, aMessageBody, aFile);
	}// --------------------------------------------

	/**
	 * Sending out E-mails through SMTP E-mail server.
	 * 
	 * @param aTo E-mail TO: field in internesendMailt E-mail address format. If
	 *            there are more than one E-mail addresses, separate them by
	 *            SysConst.EMAIL_DELIMITER_IND.
	 * @param aFrom from address
	 * @param aSubject E-mail subject line.
	 * @param aMessageBody E-mail body.
	 */

	public synchronized void sendMail(String aTo,String aFrom,
	String aSubject, String aMessageBody, File aFile)
	{

		try
		{
			MimeMessage mailMessage = new MimeMessage(this.mailSession);

			mailMessage.setFrom(new InternetAddress(aFrom));

			if (aMessageBody == null)
			{
				aMessageBody = "";

			}

			mailMessage.setRecipients(Message.RecipientType.TO,
					this.getAllEmailAddress(aTo));

			mailMessage.setSubject(aSubject);

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
		boolean authNeeed = needsAuthenication();
		Debugger.println(this, "authNeeed=" + authNeeed);

		if (authNeeed)
		{
			// -------------------------------------------
			// set properties
			Properties props = System.getProperties();
			props.put("mail.smtp.auth", authNeeed + "");

			// try to connect and send it
			aMailMessage.saveChanges();
			Transport tp = this.mailSession.getTransport("smtp");

			tp.connect(Config.getProperty(Email.MAIL_SERVER_PROP),
					Config.getProperty(Email.MAIL_FROM_ADDRESS_PROP),
					Config.getProperty(Email.MAIL_FROM_PASSWORD_PROP));
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
	 * 
	 * 
	 * 
	 * Sends a email message to specified address
	 * 
	 * 
	 * 
	 * @param aTo the recipient
	 * 
	 * @param aTemplateNM the bind Template (see Text object)
	 * 
	 * @param aMap
	 * 
	 *            the bind template map
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
	 * @param aTo the recipient
	 * 
	 * @param aTemplateNM the bind Template (see Text object)
	 * 
	 * @param aMap
	 * 
	 *            the bind template map
	 */

	public void sendMail(String aTo, String aTemplateNM,
			Map<Object, Object> aMap, Locale aLocale)

	throws javax.mail.MessagingException, IOException, Exception

	{

		logger.debug("sendMail");

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

		// if (aMap.containsKey(INCLUDE_FILES)

		// && ((String) aMap.get(INCLUDE_FILES)).equalsIgnoreCase("Y"))

		// {

		// this.addFilestoMultipart(mp, aMap);

		/*
		 * 
		 * if (((String)aMap.get(INCLUDE_FILES)).equals("Y") == true){ String
		 * 
		 * doc_type = (String)aMap.get(EmailTags.FILE_TYPE);
		 * 
		 * 
		 * 
		 * File temp_file; MimeBodyPart mbp; DocumentDelegate d = new
		 * 
		 * DocumentDelegate();
		 * 
		 * 
		 * 
		 * if (aMap.containsKey(FILE_LIST)) { String needed_file_list[] =
		 * 
		 * (String [])aMap.get(FILE_LIST); for (int k=0;k
		 * 
		 * <needed_file_list.length; k++) {
		 * 
		 * 
		 * 
		 * mbp = new MimeBodyPart();
		 * 
		 * 
		 * 
		 * mbp.setDataHandler(new DataHandler(new
		 * 
		 * FileDataSource(d.getFileAsFile(doc_type,needed_file_list[k]))));
		 * 
		 * mbp.setFileName(needed_file_list[k]); mp.addBodyPart(mbp); } } }
		 */

		// }

		mailMessage.setContent(mp);

		if (needsAuthenication())
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

	/**
	 * @return
	 */

	private boolean needsAuthenication()
	{
		return Config.getPropertyBoolean(MAIL_AUTHENICATION_REQUIRED_PROP,
				Boolean.FALSE).booleanValue();

	}// --------------------------------------------

	private synchronized void initFromJNDI() throws Exception
	{

		InitialContext initialContext = this.getContext();

		this.mailSession = (Session) initialContext.lookup(Config
				.getProperty(EmailTags.MAIL_SESSION_JNDI_NAME));

		this.mailTransport = this.mailSession.getTransport();

	}// --------------------------------------------

	/**
	 * Initialize the email object
	 * 
	 * @throws Exception
	 */

	private synchronized void init()
	{

			try
			{
				Properties sysProperties = System.getProperties();
				sysProperties.put("mail.imap.host",
						Config.getProperty("mail.imap.host", ""));
				sysProperties.put("mail.imap.port",
						Config.getProperty("mail.imap.port", "143"));

				sysProperties.put("mail.smtp.ssl.enable",
						Config.getProperty("mail.smtp.ssl.enable", ""));
				sysProperties.put("mail.smtp.host",
						Config.getProperty(MAIL_SERVER_PROP));
				//sysProperties.put("mail.smtp.host",
				//		Config.getProperty(MAIL_SERVER_PROP, ""));
				sysProperties.put(MAIL_AUTHENICATION_REQUIRED_PROP, Boolean.valueOf(
						needsAuthenication()));
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
				sysProperties.put("mail.from", Config.getProperty("mail.from"));
				sysProperties.put("mail.smtp.from",
						Config.getProperty("mail.smtp.from",""));

				// mail.imap.sasl.authorizationid

				sysProperties.put("mail.imap.sasl.authorizationid",
						Config.getProperty(EmailTags.MAIL_FROM_ADDRESS_PROP));

				sysProperties.put("mail.smtp.auth",
						Config.getProperty("mail.smtp.auth", "false"));

				mailSession = Session.getDefaultInstance(sysProperties,

				new javax.mail.Authenticator()
				{

					protected PasswordAuthentication getPasswordAuthentication()
					{

						return new PasswordAuthentication(
								Config.getProperty(EmailTags.MAIL_FROM_ADDRESS_PROP),
								Config.getProperty(EmailTags.MAIL_FROM_PASSWORD_PROP));
					}

				});

				mailTransport = mailSession.getTransport(Config.getProperty(
						"mail.protocol", "smtp"));

				Debugger.println(this, "properties=" + mailSession.getProperties());

				if (needsAuthenication())
				{

					String server = Config.getProperty(EmailTags.MAIL_SERVER_PROP);
					int port = Config.getPropertyInteger("mail.port", 25)
							.intValue();
					String from = Config
							.getProperty(EmailTags.MAIL_FROM_ADDRESS_PROP);
					char[] password = Config
							.getPropertyPassword(EmailTags.MAIL_FROM_PASSWORD_PROP);

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

	/**
	 * 
	 * @param aMessage the message to add to
	 * 
	 * @param aFile the file attachment
	 */

	private void attach(MimeMessage aMessage, File aFile, String aMessageText)
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
	 * 
	 * 
	 * @param map
	 * 
	 * @param appendtext
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
	public String getContentType()
	{
		return contentType;
	}

	/**
	 * @param contentType the contentType to set
	 */
	public void setContentType(String contentType)
	{
		this.contentType = contentType;
	}

	private Session mailSession = null;
	private String contentType = Config.getProperty(Email.class.getName()
			+ ".contentType", "text/html");
	private Transport mailTransport = null;
	private Log logger = Debugger.getLog(getClass());

}
