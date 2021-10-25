package fr.diginamic.Rest01.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.aop.ThrowsAdvice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
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

import fr.diginamic.Rest01.entities.Client;
import fr.diginamic.Rest01.entities.Emprunt;
import fr.diginamic.Rest01.exceptions.ClientError;
import fr.diginamic.Rest01.repository.CrudClientRepo;

@RestController
@RequestMapping("/clients")
public class ClientRest {
	@Autowired
	CrudClientRepo cr;

	public ClientRest() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * http://localhost:8080/clients
	 * @return
	 */
	@GetMapping
	public String welcome() {
		return "Welcome REST CONTROLLER : CLIENTS";
	}
	
	/**
	 * http://localhost:8080/clients/all
	 * @return
	 */
	@GetMapping("/all")
	public List<Client> clientAll() {
		
		return (List<Client>) cr.findAll();
	}
	
	/**
	 * http://localhost:8080/clients/one/1
	 * @return
	 * @throws ClientError 
	 */
	@GetMapping("/one/{id}")
	public Client clientOne(@PathVariable("id") Integer pid) throws ClientError {
		Optional<Client> c = cr.findById(pid);
		if(c.isEmpty())  {
			/**
			 * Je déclenche l'exception ClientError
			 * package fr.diginamic.Rest01.exceptions;
			 * Exception Fonctionnelle = métier
			 */
			throw( new ClientError("Client id :"+pid+" non trouvé !"));
		}
		/**
		 * Appel d'un requête JPL @Query dans le l'interface
		 * interface CrudClientRepo extends CrudRepository<Client, Integer>
		 */
		System.err.println(cr.findByEmprunt(c.get().getId()));
		return c.get();
	}
	
	/**
	 * Supprimer un emprunt
	 * @throws ClientError 
	 */
	@DeleteMapping("/delete/{id}")
	public String empruntDeleteOne(@PathVariable("id") Integer pid) throws ClientError {
		Optional<Client> c = cr.findById(pid);
		if(c.isEmpty()) {
			throw( new ClientError("Client id :"+pid+" non trouvé : impossible pour faire un delete"));
		}
		cr.deleteById(pid);
		return "Delete Ok : " + c.get().getId();
	}
	
	/**
	 * Modifier
	 * @throws ClientError 
	 */
	@PutMapping("update/{id}")
	public String clientUpdateOne(@PathVariable("id") Integer pid,
		@Valid @RequestBody Client pc) throws ClientError {
		Optional<Client> c = cr.findById(pid);
		if(c.isEmpty()) {
			throw( new ClientError("Client id :"+pid+" non trouvé : impossible pour faire un update"));
		}
		/**
		 * Transactionnel avec commit ou rollback
		 */
		cr.save(pc);//save insert ou update selon l'id !
		return "Update Ok : " + c.get().getId();
	}

	/**
	 * Créer un client
	 */
	@PostMapping("create")
	public String clientCreate(@Valid @RequestBody Client pc) {
		/**
		 * Transactionnel avec commit ou rollback
		 */
		cr.save(pc);//save insert ou update selon l'id !
		return "Create Ok : " + pc.getId();
	}
	
	/**
	 * J'attrape l'exception : ClientError : @ExceptionHandler(value = {ClientError.class})
	 * ClientError est une exception que j'ai créé
	 * package fr.diginamic.Rest01.exceptions;
	 * @param e
	 * @return
	 */
	@ExceptionHandler(value = {ClientError.class})
	  @ResponseStatus(value = HttpStatus.NOT_FOUND)
	  public String errorClientException(ClientError e) {
		//"Client id :"+pid+" non trouvé !"
		String message = "Soucis sur le controlleur : "+this.getClass().getSimpleName()+":"+e.getMessage();
	    
	    return message;
	  }
}
