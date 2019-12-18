package com.dobatii.dockerization1.email.config;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import lombok.extern.slf4j.Slf4j;

/**
 * Configuring mail sender
 * 
 * @author 9386-2142 Qc inc
 * @version 1.0
 * 2019-12-18
 * 
 */

//@Slf4j
//@Configuration
public class MailSenderConfig {
//	@Value("@{solibillemail.from}")
//	private String emailEmetteur;
//	
//	@Value("@{solibillemail.to}")
//	private String[] emailDestinataire;
//	
//	@Value("@{solibillemail.subject}")
//	private String emailSubject;
//	
//	@Value("@{solibillemail.text}")
//	private String emailTextMessage;
//	
//	@PostConstruct
//	public void printEmailProperties() {
//		log.info("E-mail émetteur {}, \nDestinateur {}, \nSubject {}, \\nMessage {}", emailEmetteur, emailDestinataire.toString(), emailSubject, emailTextMessage);
//	}
//	
//	@Bean
//	public ApplicationRunner startMailSender(JavaMailSender emailSender) {
//		
//		return (args) -> {
//			emailSender.send(msg -> {
//				MimeMessageHelper mimeMsgeHelper = new MimeMessageHelper(msg);
//				mimeMsgeHelper.setTo(emailDestinataire);
//				mimeMsgeHelper.setFrom(emailEmetteur);
//				mimeMsgeHelper.setSubject(emailSubject);
//				mimeMsgeHelper.setText(emailTextMessage);
//			});
//		};
//	}
}
