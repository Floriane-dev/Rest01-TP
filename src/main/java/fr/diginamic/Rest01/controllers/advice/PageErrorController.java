package fr.diginamic.Rest01.controllers.advice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * Redirection des STATUS HTTP : 400 ..., 500 ...
 * @author chris
 *
 */
@RestController
public class PageErrorController {

	public PageErrorController() {
		// TODO Auto-generated constructor stub
	}
	
	@GetMapping("/notFound")
	public String pageNotFound() {
		return "Page non trouvée !";
	}
	
	@GetMapping("/badGateway")
	public String badGateway() {
		return "Problème de sécurité !";
	}

}
