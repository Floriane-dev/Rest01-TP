package fr.diginamic.Rest01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@SpringBootApplication
public class Rest01Application  implements WebMvcConfigurer {
	
	
	public static void main(String[] args) {
		SpringApplication.run(Rest01Application.class, args);
	}
	
	@Bean
	public WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> containerCustomizer() {
	    return container -> {
	    	ErrorPage[] errs = new ErrorPage[2];
	    	/**
	    	 * les uri : "/notFound" et "/badGateway"
	    	 * seront dans un @RestController particulier :
	    	 * fr.diginamic.Rest01.controllers.advice
	    	 */
	    	errs[0] = new ErrorPage(HttpStatus.NOT_FOUND,"/notFound");
	    	errs[1] = new ErrorPage(HttpStatus.BAD_GATEWAY,"/badGateway");
	        container.addErrorPages(errs);
	    };
	  }
	
}
