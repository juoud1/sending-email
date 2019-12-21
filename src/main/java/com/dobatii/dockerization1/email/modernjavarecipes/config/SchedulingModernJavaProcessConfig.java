package com.dobatii.dockerization1.email.modernjavarecipes.config;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

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
	
	@Autowired
	private ProcessFiles processFiles;
	
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
	}

}
