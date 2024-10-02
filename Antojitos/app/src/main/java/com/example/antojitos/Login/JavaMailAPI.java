package com.example.antojitos.Login;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import android.util.Log;

public class JavaMailAPI {
    private final String email;
    private final String subject;
    private final String message;
    private static final String TAG = "JavaMailAPI";

    public JavaMailAPI(String email, String subject, String message) {
        this.email = email;
        this.subject = subject;
        this.message = message;
    }

    public void sendEmail() {
        final String username = "miniempresaantojitos@gmail.com"; // Tu correo de Gmail
        final String password = "nays sabb ualg fqpz"; // Contraseña de aplicación generada

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(email));
            message.setSubject(subject);
            message.setText(this.message);

            Transport.send(message);
            Log.d(TAG, "Email sent successfully");
        } catch (MessagingException e) {
            Log.e(TAG, "Error sending email", e);
        }
    }
}
