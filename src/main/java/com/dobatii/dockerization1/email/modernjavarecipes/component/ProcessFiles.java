package com.dobatii.dockerization1.email.modernjavarecipes.component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

import org.springframework.stereotype.Component;

import com.dobatii.dockerization1.email.modernjavarecipes.utils.consts.Constant;

import lombok.extern.slf4j.Slf4j;

/**
 * Process file with the new capabilities of java nio.2 extention
 * 
 * @author 9386-2142 Qc inc
 * @version 1.0
 * 2019-12-20
 * 
 */

@Slf4j
@Component
public class ProcessFiles {
	
	/**
	 * Find the 7 longuest words in the application.yml of the application 
	 * @return 
	 * 
	 * @return an instance of supplier of a new thread
	 * 
	 */
	public void getTextFileContent() {
		log.info("Process lines starts".toUpperCase());
		
		Path path = Paths.get(Constant.PATH_STR);
		log.info("current path {}".toUpperCase(), path);
				
		try (Stream<String> textFileLines = Files.lines(path)) {
			Predicate<String> wordLengthPredicate = s -> s.length() > Constant.LINE_TEXT_LENGTH;
			
			textFileLines.filter(wordLengthPredicate)
				.limit(7)
				.sorted(Comparator.comparingInt(String::length).reversed())
				.forEach(w -> log.info("{}, size = {} \n", w, w.length()));
			
			log.info("Process lines finishes with success.".toUpperCase());
			
		} catch (IOException ioe) {
			log.error("get text file content error : \n{}, \n{}", ioe.getLocalizedMessage(), ioe.getCause());
		}
	}
	
	public void getWordFileContent() {
		log.info("Process words starts".toUpperCase());
		
		Path path = Paths.get(Constant.PATH_STR);
		log.info("current path {}".toUpperCase(), path);
		
		try (Stream<String> textFileLines = Files.lines(path)) {
			Function<String, String[]> wordsFunction = s -> s.split(" ");
			Function<String[], String> wordFunction = Arrays::toString;
			Predicate<String> wordPredicate = s -> !s.contains(":");
			Predicate<String> wordLengthPredicate = s -> s.length() > Constant.WORD_TEXT_LENGTH;
			
			textFileLines.map(wordsFunction)
				.map(wordFunction)
				.filter(wordPredicate
						.and(wordLengthPredicate))
				.limit(12)
				.sorted(Comparator.comparing(String::length).reversed())
				.forEach(w -> log.info("{}, size = {} \n", w, w.length()));
			
			log.info("Process words finishes with success.".toUpperCase());
		
		} catch (IOException ioe) {
			log.error("get text file content error : \n{}, \n{}", ioe.getLocalizedMessage(), ioe.getCause());
		}
		
	}
	
//	private Stream<String> getStreamFileContent(String strPath) {
//		
//		if (StringUtils.isNotBlank(strPath)) {
//			log.info("current path's string {} is valid.".toUpperCase(), strPath);
//			
//			Path path = Paths.get(strPath);
//			try (Stream<String> streamFileLines = Files.lines(path)) {
//				log.info("Streams of lines {}\nin the text file {}".toUpperCase(), streamFileLines.toString(), strPath);
//				return streamFileLines;
//				
//			} catch (IOException ioe) {
//				log.error("get text file content error : \n{}, \n{}", ioe.getLocalizedMessage(), ioe.getCause());
//				return Stream.empty();
//				
//			} 
//		}
//		
//		log.info("current path's string {} is blank.".toUpperCase(), strPath);
//		return Stream.empty();				
//	}
}
