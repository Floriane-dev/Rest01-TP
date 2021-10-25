package fr.diginamic.Rest01.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import fr.diginamic.Rest01.entities.Emprunt;
import fr.diginamic.Rest01.exceptions.ClientError;
import fr.diginamic.Rest01.exceptions.EmpruntError;
import fr.diginamic.Rest01.repository.CrudEmpruntRepo;

@RestController
@RequestMapping("/emprunts")
public class EmpruntRest {
	@Autowired
	CrudEmpruntRepo er;
	
	public EmpruntRest() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * http://localhost:8080/emprunts
	 * @return
	 */
	@GetMapping
	public String welcome() {
		return "Welcome REST CONTROLLER : EMPRUNTS";
	}
	
	/**
	 * http://localhost:8080/emprunts/all
	 * @return
	 */
	@GetMapping("/all")
	public List<Emprunt> empruntAll() {
		
		return (List<Emprunt>) er.findAll();
	}
	
	/**
	 * http://localhost:8080/emprunts/one/1
	 * @return
	 * @throws EmpruntError 
	 */
	@GetMapping("/one/{id}")
	public Emprunt empruntOne(@PathVariable("id") Integer pid) throws EmpruntError {
		Optional<Emprunt> e = er.findById(pid);
		if(e.isEmpty()) {
			throw( new EmpruntError("Emprunt id :"+pid+" non trouvé !"));
		}
		System.err.println(er.findByLivre(e.get()));
		return e.get();
	}
	
	/**
	 * Supprimer un emprunt
	 */
	@DeleteMapping("/delete/{id}")
	public String empruntDeleteOne(@PathVariable("id") Integer id) {
		Emprunt e = er.findById(id).get();
		er.deleteById(id);
		return "Delete Ok : " + e.getId();
	}
	
	/**
	 * Modifier
	 */
	@PutMapping("update/{id}")
	public String empruntUpdateOne(@PathVariable("id") Integer id,
			@Valid @RequestBody Emprunt pe) {
		Emprunt e = er.findById(id).get();
		/**
		 * Transactionnel avec commit ou rollback
		 */
		er.save(pe);//save insert ou update selon l'id !
		return "Update Ok : " + e.getId();
	}

	/**
	 * Créer
	 * Grâce à @Valid Spring va vérifier s'il y a dans la classe
	 * Emprunt des annotations de validations de contrôles
	 * @NotNull etc ....
	 */
	@PostMapping("create")
	public String empruntCreate(@Valid @RequestBody Emprunt pe) {
		//Emprunt e = er.findById(id).get();
		/**
		 * Transactionnel avec commit ou rollback
		 */
		er.save(pe);//save insert ou update selon l'id !
		return "Create Ok : " + pe.getId();
	}
	
	/**
	 * J'attrape l'exception : ClientError : @ExceptionHandler(value = {ClientError.class})
	 * ClientError est une exception que j'ai créé
	 * package fr.diginamic.Rest01.exceptions;
	 * @param e
	 * @return
	 */
	@ExceptionHandler(value = {EmpruntError.class})
	  @ResponseStatus(value = HttpStatus.NOT_FOUND)
	  public String errorEmpruntException(EmpruntError e) {
		//"Emprunt id :"+pid+" non trouvé !"
	    String message = "Soucis sur le controlleur : "+this.getClass().getSimpleName()+":"+e.getMessage();
	    
	    return message;
	  }
}
