package com.jntua.utility;

import java.util.Date;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class MailService
{

    public MailService()
    {
    }

    public String send(String p_to, String p_subject, String p_message)
    {
        String l_result = "<BR><BR><BR><BR><BR><BR><BR>";
        String l_host = "smtp.gmail.com";
        String p_from = "bhashahussain@gmail.com";
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.user", "bhashahussain@gmail.com");
        props.put("mail.smtp.auth", "true");
        props.put("mail.debug", "true");
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback", "false");
        Session l_session = Session.getDefaultInstance(props, getAuth());
        l_session.setDebug(true);
        try
        {
            MimeMessage l_msg = new MimeMessage(l_session);
            l_msg.setRecipients(javax.mail.Message.RecipientType.TO, InternetAddress.parse(p_to, false));
            l_msg.setSubject(p_subject);
            MimeBodyPart l_mbp = new MimeBodyPart();
            l_mbp.setText(p_message);
            Multipart l_mp = new MimeMultipart();
            l_mp.addBodyPart(l_mbp);
            l_msg.setContent(l_mp);
            l_msg.setSentDate(new Date());
            Transport.send(l_msg);
            l_result = (new StringBuilder(String.valueOf(l_result))).append("<FONT SIZE=4 COLOR=\"blue\"><B>Success!</B>").append("<FONT SIZE=4 COLOR=\"black\"> ").append("<HR><FONT color=green><B>Mail was successfully sent to </B></FONT>: ").append(p_to).append("<BR>").toString();
            l_result = (new StringBuilder(String.valueOf(l_result))).append("<BR><HR>").toString();
        }
        catch(MessagingException mex)
        {
            l_result = (new StringBuilder(String.valueOf(l_result))).append("<FONT SIZE=4 COLOR=\"blue\"> <B>Error : </B><BR><HR> ").append("<FONT SIZE=3 COLOR=\"black\">").append(mex.toString()).append("<BR><HR>").toString();
        }
        catch(Exception e)
        {
            l_result = (new StringBuilder(String.valueOf(l_result))).append("<FONT SIZE=4 COLOR=\"blue\"> <B>Error : </B><BR><HR> ").append("<FONT SIZE=3 COLOR=\"black\">").append(e.toString()).append("<BR><HR>").toString();
            e.printStackTrace();
        }
        return l_result;
    }

    public Authenticator getAuth()
    {
        return new Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication()
            {
                return new PasswordAuthentication("bhashahussain@gmail.com", "9asha@7299");
            }

           
        };
    }

    private static final String smtpServer = "smtp.gmail.com";
    private static final String default_from = "support@loadindia.com";
    private static final String gmail_user = "cstleavesku@gmail.com";
    private static final String gmail_password = "sku@7299";
    private static final String smtp_port = "465";
    private static final String ssl_factory = "javax.net.ssl.SSLSocketFactory";
}