package nyla.solutions.email;
import java.io.BufferedInputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

import javax.mail.Address;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.Store;

import nyla.solutions.core.exception.ConnectionException;
import nyla.solutions.core.io.IO;
import nyla.solutions.core.util.Text;
import nyla.solutions.email.data.EmailMessage;

public class ReadMail implements Closeable
{
	public ReadMail(Store store)
	{
		this(store,"Inbox");
	}//------------------------------------------------
	public ReadMail(Store store, String folderName)
	{
		if(store == null)
					throw new IllegalArgumentException("store is required");
		
		if(folderName == null || folderName.trim().length() == 0)
					throw new IllegalArgumentException("folderName is required");
		try
		{
			inbox = store.getFolder(folderName);
			
			if(inbox == null)
				throw new NullPointerException(folderName+" does not exists");
		}
		catch (MessagingException e)
		{
			throw new ConnectionException(e.getMessage(),e);
		}
	}//------------------------------------------------
	

	public Collection<EmailMessage> readWhereSubjectContains(int count, int startIndex, String subjectPattern) 
	throws MessagingException, IOException
	{
		inbox.open(Folder.READ_ONLY);

		if(startIndex< 1)
			throw new IllegalArgumentException("startIndex must be greater than 0");
		
		if(count < 1)
			throw new IllegalArgumentException("Count must be greater than 0");
			
		/* Get the messages which is unread in the Inbox */
		Message[] msgs = inbox.getMessages(startIndex,startIndex+ count-1);
		
		if(msgs == null)
			return null;
		
			ArrayList<EmailMessage> results = Arrays.asList(msgs).stream().filter(msg -> {
							try
							{
								return msg != null 
										&& 
										msg.getSubject() !=null && 
										Text.matches(msg.getSubject(),subjectPattern);
							}
							catch (MessagingException e)
							{
								throw new nyla.solutions.core.exception.CommunicationException(e.getMessage());
							}
						}
					).map(message -> {
						try
						{
							return toEmailMessage(message);
						}
						catch (MessagingException | IOException e)
						{
							throw new RuntimeException(e);
						}
					}  ).collect(Collectors.toCollection(ArrayList::new));
			
			return results;
		
	}//------------------------------------------------

	private void printAllMessages(Message[] msgs) 
	throws MessagingException, IOException
	{
		for (int i = 0; i < msgs.length; i++)
		{
			System.out.println("MESSAGE #" + (i + 1) + ":");
			toEmailMessage(msgs[i]);
		}
	}

	/* Print the envelope(FromAddress,ReceivedDate,Subject) */
	private EmailMessage toEmailMessage(Message message) 
	throws MessagingException, IOException 
	{
		EmailMessage emailMessage = new EmailMessage();
		// FROM
		Address[] froms = message.getFrom();
		
		if (froms != null)
		{
			for (int j = 0; j < froms.length; j++)
			{
				emailMessage.addFrom(froms[j].toString());
			}
		}
		// TO
		
		Address[] tos = message.getRecipients(Message.RecipientType.TO);

		if (tos != null)
		{
			for (int j = 0; j < tos.length; j++)
			{
				emailMessage.addTo(tos[j].toString());
			}
		}
		
		emailMessage.setSubject(message.getSubject());
		emailMessage.setRecievedDate(message.getReceivedDate());
		emailMessage.setContent(message.getContent().toString());
		
		System.out.println("emailMessage : " + emailMessage);
		
		return emailMessage;
		//getContent(message);
	}//------------------------------------------------

	private String getContent(Message msg) 
	throws MessagingException, IOException
	{

			String contentType = msg.getContentType();
			System.out.println("Content Type : " + contentType);
			Multipart mp = (Multipart) msg.getContent();
			int count = mp.getCount(); 
			
			StringBuilder results = new StringBuilder();
			
			for (int i = 0; i < count; i++)
			{
				results.append(readTextPart(mp.getBodyPart(i)));
			}

			return results.toString();
	}//------------------------------------------------

	private String readTextPart(Part p) 
	throws IOException, MessagingException
	{
		// Dump input stream ..
		InputStream is = p.getInputStream();
		// If "is" is not already buffered, wrap a BufferedInputStream
		// around it.
		if (!(is instanceof BufferedInputStream))
		{
			is = new BufferedInputStream(is);
		}
		return IO.readText(is, true);
	}//------------------------------------------------

	@Override
	public synchronized void close() throws IOException
	{
		if(isClosed)
			return;
		try
		{
			inbox.close(false);
		}
		catch (MessagingException e)
		{
			throw new nyla.solutions.core.exception.CommunicationException(e.getMessage(),e);
		}
		
		this.isClosed =  true;
		
	}//------------------------------------------------
	private final Folder inbox;
	private boolean isClosed = false;
}
