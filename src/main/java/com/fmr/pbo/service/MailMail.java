package com.fmr.pbo.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.MailParseException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class MailMail {
	private JavaMailSender mailSender;
	private SimpleMailMessage simpleMailMessage;

	public void setSimpleMailMessage(SimpleMailMessage simpleMailMessage) {
		this.simpleMailMessage = simpleMailMessage;
	}

	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	public void sendMail(String dear, String content, String path) {

		MimeMessage message = mailSender.createMimeMessage();

		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true);

			helper.setFrom(simpleMailMessage.getFrom());
			helper.setTo(simpleMailMessage.getTo());
			helper.setSubject(simpleMailMessage.getSubject());
			helper.setText(String.format(simpleMailMessage.getText(), dear, content));

			/*
			 * FileSystemResource file = new FileSystemResource(path);
			 * 
			 * helper.addAttachment(file.getFilename(), file);
			 */

			ClassPathResource file = new ClassPathResource(path);
			helper.addAttachment(path, file);

		} catch (MessagingException e) {
			throw new MailParseException(e);
		}
		mailSender.send(message);

	}

}
