package com.dobatii.dockerization1.email.modernjavarecipes.config;

import java.util.Collections;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import com.dobatii.dockerization1.email.modernjavarecipes.component.ProcessFiles;
import com.dobatii.dockerization1.email.modernjavarecipes.utils.CustomWelcomePrinter;

import lombok.extern.slf4j.Slf4j;

/**
 * Configure method's scheduling using java to limit the amount of annotation : Cannot place @Scheduled on method
 * 
 * @author 9386-2142 Qc inc
 * @version 1.0
 * 2019-12-20
 * 
 */

@Slf4j
@Configuration
@EnableScheduling
public class SchedulingModernJavaProcessConfig implements SchedulingConfigurer {
	
	@Value("${app.custom-name}") 
	private String appName;
	
	@Value("${solibillemail.from}")
	private String emailEmetteur;
	
	@Value("${solibillemail.to}")
	private String[] emailDestinataire;
	
	@Value("${solibillemail.subject}")
	private String emailSubject;
	
	@Value("${solibillemail.text}")
	private String emailTextMessage;
	
	@Autowired
	private ProcessFiles processFiles;
	
	@Autowired
	private JavaMailSender emailSender;
	
	@PostConstruct
	public void printWelcomeMessage() {
		log.info(CustomWelcomePrinter.print(appName)
				.get());
	}
	
	/**
	 * Schedules 2 tasks 
	 */
	@Override
	public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
		taskRegistrar.addFixedRateTask(() -> processFiles.getTextFileContent(), 270930);
		taskRegistrar.addFixedRateTask(() -> processFiles.getWordFileContent(), 307827);
		
		taskRegistrar.addFixedRateTask(() -> emailSender.send(msg -> {
			MimeMessageHelper mimeMsgeHelper = new MimeMessageHelper(msg);
			mimeMsgeHelper.setTo(emailDestinataire);
			mimeMsgeHelper.setFrom(emailEmetteur);
			mimeMsgeHelper.setSubject(emailSubject);
			mimeMsgeHelper.setText(emailTextMessage);
			
//			Context ctxt = new Context(LocaleContextHolder.getLocale(), Collections.singletonMap("emailbody", "Dongongo!!!"));
//			
//			String emailBody= thymeleafTemplateEngine.process("shedulinglimitedfilecontentemail.html", ctxt);
//			
//			mimeMsgeHelper.setText(emailBody, true);
			
		}), 2009);
		
	}		
}
