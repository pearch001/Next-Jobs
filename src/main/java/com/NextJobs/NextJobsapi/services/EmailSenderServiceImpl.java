package com.NextJobs.NextJobsapi.services;

import com.NextJobs.NextJobsapi.exceptions.EmailNotValidException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import sendinblue.ApiClient;
import sendinblue.Configuration;
import sendinblue.auth.ApiKeyAuth;
import sibApi.TransactionalEmailsApi;
import sibModel.*;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Service
@AllArgsConstructor
@Slf4j
public class EmailSenderServiceImpl implements EmailSenderServiceInt{
    private final JavaMailSender mailSender;



    @Async
    @Override
    public void send(String to, String email) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper =
                    new MimeMessageHelper(mimeMessage, "utf-8");
            helper.setText(email, true);
            helper.setTo(to);
            helper.setSubject("Confirm your email");
            helper.setFrom("kasimoluwasegun@gmail.com");
            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            log.error("failed to send email", e);
            throw new IllegalStateException("failed to send email");
        }
    }

    public void sendMail(String top, String email){
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        // Configure API key authorization: api-key
        ApiKeyAuth apiKey = (ApiKeyAuth) defaultClient.getAuthentication("api-key");
        apiKey.setApiKey("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");

        try {

            TransactionalEmailsApi api = new TransactionalEmailsApi();
            SendSmtpEmailSender sender = new SendSmtpEmailSender();
            sender.setEmail("kasimoluwasegun@gmail.com");
            sender.setName("Next Jobs");
            List<SendSmtpEmailTo> toList = new ArrayList<SendSmtpEmailTo>();
            SendSmtpEmailTo to = new SendSmtpEmailTo();
            to.setEmail(top);
            to.setName("");
            toList.add(to);
            SendSmtpEmailReplyTo replyTo = new SendSmtpEmailReplyTo();
            replyTo.setEmail("kasimoluwasegun@gmail.com");
            replyTo.setName("kasimoluwasegun@gmail.com");
            Properties headers = new Properties();
            headers.setProperty("Some-Custom-Name", "unique-id-1234");
            Properties params = new Properties();
            params.setProperty("parameter", "");
            params.setProperty("subject", "Confirm your email");
            SendSmtpEmail sendSmtpEmail = new SendSmtpEmail();
            sendSmtpEmail.setSender(sender);
            sendSmtpEmail.setTo(toList);
            sendSmtpEmail.setHtmlContent(email);
            sendSmtpEmail.setSubject("My {{params.subject}}");
            sendSmtpEmail.setReplyTo(replyTo);
            sendSmtpEmail.setHeaders(headers);
            sendSmtpEmail.setParams(params);
            List<SendSmtpEmailTo1> toList1 = new ArrayList<SendSmtpEmailTo1>();
            SendSmtpEmailTo1 to1 = new SendSmtpEmailTo1();
            to1.setEmail(top);
            to1.setName("John Doe");
            toList1.add(to1);
            List<SendSmtpEmailMessageVersions> messageVersions = new ArrayList<>();
            SendSmtpEmailMessageVersions versions1 = new SendSmtpEmailMessageVersions();
            //SendSmtpEmailMessageVersions versions2 = new SendSmtpEmailMessageVersions();
            versions1.to(toList1);
            //versions2.to(toList1);
            messageVersions.add(versions1);
            //messageVersions.add(versions2);
            sendSmtpEmail.setMessageVersions(messageVersions);
            //sendSmtpEmail.setTemplateId(1L);
            CreateSmtpEmail response = api.sendTransacEmail(sendSmtpEmail);
            System.out.println(response.toString());
        } catch (Exception e) {
            throw new EmailNotValidException("Exception occurred:- " + e.getMessage());

        }
    }

}
