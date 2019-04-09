package se.ifmo.ru.util;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class MailService {
    public static void sendMessage(String to, String text) {
        String username = "rubber.ducks.fanclub@yandex.ru";
        String password = "rubber.ducks.fanclub";

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.yandex.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS
        prop.put("mail.smtp.from","rubber.ducks.fanclub@yandex.ru");
        prop.put("mail.smtp.ssl.trust", "smtp.yandex.com");

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("rubber.ducks.fanclub@yandex.ru"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(to)
            );
            message.setSubject("Hey! Let's share ducks!");
            message.setText(text);
            Transport.send(message);
            System.out.println("Done");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

}
