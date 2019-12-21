package com.dobatii.dockerization1.email;

import java.io.IOException;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
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
	
	public static void main(String[] args) throws IOException {
		SpringApplication.run(Dockerization1ServiceEmailApplication.class, args);
		
//		log.info("Press [ENTER] to quit:".toUpperCase());
//		System.in.read();
	}
	
	@PostConstruct
	public void printEmailProperties() {
		log.info("E-mail émetteur {}, \nDestinateur {}, \nSubject {}, \nMessage {}", emailEmetteur, emailDestinataire.toString(), emailSubject, emailTextMessage);
	}
	
//	@Bean
//	public ApplicationRunner startMailSender(JavaMailSender emailSender) {
//		log.info("Sending courriel ...".toUpperCase());
//		
//		return args -> 
//			emailSender.send(msg -> {
//				MimeMessageHelper mimeMsgeHelper = new MimeMessageHelper(msg);
//				mimeMsgeHelper.setTo(emailDestinataire);
//				mimeMsgeHelper.setFrom(emailEmetteur);
//				mimeMsgeHelper.setSubject(emailSubject);
//				mimeMsgeHelper.setText(emailTextMessage);
//			});
//	}
}
