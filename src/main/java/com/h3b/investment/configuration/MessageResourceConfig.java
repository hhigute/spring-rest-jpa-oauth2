package com.h3b.investment.configuration;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

/* Message Source Configuration*/
@Configuration
public class MessageResourceConfig {
	
	@Bean
	public LocalValidatorFactoryBean validator(MessageSource messageSource) {
	    LocalValidatorFactoryBean validatorFactoryBean = new LocalValidatorFactoryBean();
	    validatorFactoryBean.setValidationMessageSource(messageSource);
	    return validatorFactoryBean;
	}
}
