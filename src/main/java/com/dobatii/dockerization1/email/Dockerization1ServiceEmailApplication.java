package com.dobatii.dockerization1.email;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class Dockerization1ServiceEmailApplication {
	
	@Value("${solibillemail.from}")
	private String emailEmetteur;
	
	@Value("${solibillemail.to}")
	private String emailDestinataire;
	
	@Value("${solibillemail.subject}")
	private String emailSubject;
	
	@Value("${solibillemail.text}")
	private String emailTextMessage;
	
	public static void main(String[] args) {
		SpringApplication.run(Dockerization1ServiceEmailApplication.class, args);
	}
	
	@PostConstruct
	public void printEmailProperties() {
		log.info("E-mail émetteur {}, \nDestinateur {}, \nSubject {}, \nMessage {}", emailEmetteur, emailDestinataire.toString(), emailSubject, emailTextMessage);
	}
	
	@Bean
	public ApplicationRunner startMailSender(JavaMailSender emailSender) {
		log.info("Sending courriel ...".toUpperCase());
		
		return args -> 
			emailSender.send(msg -> {
				MimeMessageHelper mimeMsgeHelper = new MimeMessageHelper(msg);
				mimeMsgeHelper.setTo(emailDestinataire);
				mimeMsgeHelper.setFrom(emailEmetteur);
				mimeMsgeHelper.setSubject(emailSubject);
				mimeMsgeHelper.setText(emailTextMessage);
			});
	}
}
