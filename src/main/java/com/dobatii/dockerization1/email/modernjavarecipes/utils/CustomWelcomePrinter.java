package com.dobatii.dockerization1.email.modernjavarecipes.utils;

import java.time.ZonedDateTime;
import java.util.function.Supplier;

/**
 * Print's welcome message. 
 * 
 * @author 9386-2142 Qc inc
 * @version 1.0
 * 2019-12-20
 *
 */

@FunctionalInterface
public interface CustomWelcomePrinter {
	
	Supplier<String> print(String... params);
	
	/**
	 * Takes a name of the application and makes a welcome message. 
	 * 
	 * @param appName
	 * @return a supplier interface fonctionnal of the string with a application's name and the instant 
	 */
	static Supplier<String> print(String appName) {
		
		return () -> String.format("Allo, you are welcome on %s at %s".toUpperCase(), appName, 
						ZonedDateTime.now()
							.toInstant());
	}
}
