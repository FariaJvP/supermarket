package com.sm.supermarket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import java.util.Locale;

@SpringBootApplication
public class SupermarketApplication {

	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource
				= new ReloadableResourceBundleMessageSource();
		messageSource.setBasenames(
				"classpath:/messages"
		);
		messageSource.setDefaultEncoding("UTF-8");
		messageSource.setDefaultLocale(Locale.US);
		return messageSource;
	}

	public static void main(String[] args) {
		SpringApplication.run(SupermarketApplication.class, args);
	}

}
