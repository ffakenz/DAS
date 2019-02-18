package ar.edu.ubp.das.src.jobs.sorteo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class SendEmail {

    private static final Logger log = LoggerFactory.getLogger(SendEmail.class);

    private static final String username = "ubpdas2019@gmail.com";
    private static final String password = "Das12345";

    private static Session session = Session.getDefaultInstance(setAndGetEmailProperties(),
            new Authenticator(){
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }});

    public void to(String emailRecipient, String subject, String body) throws MessagingException {

        try{
            submitEmail(emailRecipient, subject, body);
        } catch (MessagingException mex) {
            throw mex;
        }
    }

    private void submitEmail(String emailRecipient, String subject, String body) throws MessagingException {
        // Create a default MimeMessage object.
        MimeMessage message = new MimeMessage(session);

        // Set From: header field of the header.
        message.setFrom(new InternetAddress(username));

        // Set To: header field of the header.
        message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(emailRecipient,false));

        // Set Subject: header field
        message.setSubject(subject);

        // Now set the actual message
        message.setContent(body, "text/html");

        // Send message
        Transport.send(message);
        log.debug("Sent message successfully to: " + emailRecipient);
    }


    private static Properties setAndGetEmailProperties() {

        // Setup mail server
        final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";

        Properties props = System.getProperties();
        props.setProperty("mail.smtp.host", "smtp.gmail.com");
        props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.port", "465");
        props.setProperty("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.auth", "true");
        props.put("mail.debug", "true");
        props.put("mail.store.protocol", "pop3");
        props.put("mail.transport.protocol", "smtp");

        return props;
    }
}

class SendEmailStubFail extends SendEmail {

    @Override
    public void to(String emailRecipient, String subject, String body) throws MessagingException {
        throw new MessagingException("test email fail");
    }
}

class SendEmailStubSuccess extends SendEmail {

    @Override
    public void to(String emailRecipient, String subject, String body) throws MessagingException {
        System.out.println("ENVIO EMAIL SUCCESS");
    }
}