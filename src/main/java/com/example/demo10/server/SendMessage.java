package com.example.demo10.server;

import java.util.Date;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
public class SendMessage {
    public void SendMessage(String reason, String toEmail) {

        Properties props = new Properties();
        props.put("mail.transport.protocol", "smtps");
        props.put("mail.smtps.host", "smtp.mail.ru");
        props.put("mail.smtps.auth", "true");
        props.put("mail.smtp.sendpartial", "true");
        props.put("mail.smtp.ssl.enable", "true");

        Session session = Session.getDefaultInstance(props);

        MimeMessage message = new MimeMessage(session);

        try {
            message.setSubject("Уведомление на тему предстоящего обучения!");

            message.setText(reason);

            message.addRecipient(Message.RecipientType.TO, new InternetAddress("tcipkoalina2000@gmail.com"));

            message.setSentDate(new Date());
            String userLogin;
            message.addFrom(new Address[]{new InternetAddress("tcipkoalina2000@mail.ru")});

            userLogin = "tcipkoalina2000@mail.ru";
            String userPassword = "KauejMGUpa3edMn9Z8Cv";

            Transport transport = session.getTransport();
            transport.connect(userLogin, userPassword);

            transport.sendMessage(message, message.getRecipients(Message.RecipientType.TO));

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

}
