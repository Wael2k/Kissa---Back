package PFA.project.config.mail;

import PFA.project.utils.Constants;
import PFA.project.utils.TemplateConstants;
import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
@Service
public class EmailService {
    @Autowired
    private Configuration configuration;
    @Autowired
    private JavaMailSender javaMailSender;

    public void sendEmail(String email, String tokenMail, String otpEmail) throws MessagingException, IOException, TemplateException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
        helper.setSubject(Constants.SUBJECT_VERIFICATION_MAIL);
        helper.setTo(email);
        String emailContent = getEmailContent(tokenMail,otpEmail);
        helper.setText(emailContent, true);
        javaMailSender.send(mimeMessage);
    }
    public void sendEmailConfirm(String email,String password) throws MessagingException, IOException, TemplateException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
        helper.setSubject(Constants.SUBJECT_CONFIRM_MAIL);
        helper.setTo(email);
        String emailContent = getEmailContentForConfirmation(email,password);
        helper.setText(emailContent, true);
        javaMailSender.send(mimeMessage);
    }
    String getEmailContent(String tokenMail, String otpEmail) throws IOException, TemplateException {
        StringWriter stringWriter = new StringWriter();
        Map<String, Object> model = new HashMap<>();
        model.put(Constants.TOKEN_MAIL, tokenMail);
        model.put(Constants.OTP_MAIL, otpEmail);
        configuration.getTemplate(TemplateConstants.EMAIL_TEMPLATE.getValue()).process(model, stringWriter);
        return stringWriter.getBuffer().toString();
    }
    String getEmailContentForConfirmation(String email,String password) throws IOException, TemplateException {
        StringWriter stringWriter = new StringWriter();
        Map<String, Object> model = new HashMap<>();
        model.put(Constants.EMAIL, email);
        model.put(Constants.PASSWORD, password);
        configuration.getTemplate(TemplateConstants.EMAIL_CONFIRMATION_ACCOUNT_TEMPLATE.getValue()).process(model, stringWriter);
        return stringWriter.getBuffer().toString();
    }
    String getEmailContentForConfirmationClient(String email) throws IOException, TemplateException {
        StringWriter stringWriter = new StringWriter();
        Map<String, Object> model = new HashMap<>();
        model.put(Constants.EMAIL, email);
        configuration.getTemplate(TemplateConstants.EMAIL_CONFIRMATION_CLIENT_TEMPLATE.getValue()).process(model, stringWriter);
        return stringWriter.getBuffer().toString();
    }

    public void sendEmailConfirmClient(String email) throws MessagingException, TemplateException, IOException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
        helper.setSubject(Constants.SUBJECT_CONFIRM_MAIL_CLIENT);
        helper.setTo(email);
        String emailContent = getEmailContentForConfirmationClient(email);
        helper.setText(emailContent, true);
        javaMailSender.send(mimeMessage);
    }
}
