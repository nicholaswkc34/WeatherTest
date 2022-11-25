package com.axonNetworks.manager;

import com.aspose.email.*;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;


public class MailerManager {
	/*
	props.put("mail.smtp.auth", "true");
	props.put("mail.smtp.starttls.enable", "true");
	props.put("mail.smtp.host", host);
	props.put("mail.smtp.port", "587");	
	mail.protocol.proxy.host" and "mail.protocol.proxy.port
*/
	
	private Logger log = LogManager.getLogger(MailerManager.class);

	
	public MailerManager() {
	}
	
	/*public synchronized void sendEmail() {
		try {
			// Create the attachment
			EmailAttachment attachment = new EmailAttachment();
		    attachment.setPath(".\\report\\TestReport-Spark.html");
		    attachment.setDisposition(EmailAttachment.ATTACHMENT);
		    attachment.setDescription("AT Report");
		    attachment.setName("AT Report.html");
		    
		    // Create the email message
		    MultiPartEmail email = new MultiPartEmail();
		    email.setHostName("smtp.gmail.com");
		    email.setSmtpPort(587);
		    email.setAuthenticator(new DefaultAuthenticator("peterapiit@gmail.com", "giqqpudmcjjmpbcc"));
		    email.setFrom("peterapiit@gmail.com", "Nicholas Wong");
		    email.addTo("kwong@who.int", "Nicholas Wong");
		    email.addTo("gann@who.int", "Nicholas Gann");
		    email.setBounceAddress("peterwkc30@gmail.com");
		    email.setSubject("ePQS Automation Testing Report");
		    email.setMsg("Please find the ePQS Automation Testing Report from attachment");
		    email.setStartTLSEnabled(true);
		    email.setSSLCheckServerIdentity(true);	
		    email.setDebug(true); 
		   
		    email.getMailSession().getProperties().setProperty("mail.smtp.auth", "true");
		    email.getMailSession().getProperties().setProperty("mail.smtp.starttls.enable", "true");
		    email.getMailSession().getProperties().setProperty("mail.smtp.port", "587");
			//email.getMailSession().getProperties().setProperty("mail.smtp.proxy.host", "http://10.64.150.9");
			//email.getMailSession().getProperties().setProperty("mail.smtp.proxy.port", "8080");
			
		    // add the attachment
		    email.attach(attachment);

		    // send the email
		    email.send();
			
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}

	}*/

	public synchronized void sendEmail() {
		MailMessage message = new MailMessage();

		message.setSubject("Automation Testing Report");
		message.setBody("Dear Stakeholders, This is the automation testing report for your reference.");

		// Set Sender
		message.setFrom(new MailAddress("from@outlook.com", "Sender Name", false));
		message.getTo().addItem(new MailAddress("to1@outlook.com", "Recipient 1", false));
		message.getTo().addItem(new MailAddress("to1@outlook.com", "Recipient 1", false));

		// Add attachments
		message.getAttachments().addItem(new Attachment(".\\report\\TestReport-Spark.html"));

		message.save("EmailMessage.msg", SaveOptions.getDefaultMsgUnicode());

		// Create an instance of SmtpClient Class
		SmtpClient client = new SmtpClient();

		// Specify your mailing host server, Username, Password, Port
		client.setHost("smtp.server.com");
		client.setUsername("Username");
		client.setPassword("Password");
		client.setPort(25);

		try {
			// Client.Send will send this message
			client.send(message);
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}




	}
}
// ==============================================================================================



/*
 * https://blog.aspose.com/email/create-and-send-outlook-email-messages-asynchronously-using-java/
 *
 *
 * https://jodd.org/email/
 * 
 * 
 * 
 */




