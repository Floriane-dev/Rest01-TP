package fr.diginamic.Rest01.controllers.advice;

import java.sql.SQLException;

import javax.validation.ConstraintDeclarationException;
import javax.validation.ConstraintDefinitionException;
import javax.validation.UnexpectedTypeException;
import javax.validation.ValidationException;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import fr.diginamic.Rest01.exceptions.ClientError;

/**
 * @RestControllerAdvice
 * me permet "d'attraper" les erreurs d'exceptions JAVA
 * @author chris
 *
 */

@RestControllerAdvice
public class ErrorController {

	public ErrorController() {
		// TODO Auto-generated constructor stub
	}


	 @ExceptionHandler(value = {ClientError.class})
	  @ResponseStatus(value = HttpStatus.NOT_FOUND)
	  public String errorClientException(ClientError e) {
	    String message = "Soucis sur le controlleur Client : "+e.getMessage();

	    return message;
	  }

	 @ExceptionHandler(value =
		 {ConstraintDeclarationException.class,ConstraintDefinitionException.class,
				 UnexpectedTypeException.class,ConstraintViolationException.class,
				 SQLException.class,ValidationException.class})
	  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
	  public String errorSqlException(Exception e) {
	    String message = "Erreur d'intégrité ou de validation : " + e.getMessage();

	    return message;
	  }

	 @ExceptionHandler(value = {Exception.class})
	  @ResponseStatus(value = HttpStatus.NOT_FOUND)
	  public String errorGeneralException(Exception e) {
	    String message = "Il y a une erreur : " + e.getMessage();

	    return message;
	  }


}