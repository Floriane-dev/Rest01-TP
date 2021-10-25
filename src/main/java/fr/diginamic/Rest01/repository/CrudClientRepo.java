package fr.diginamic.Rest01.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import fr.diginamic.Rest01.entities.Client;
import fr.diginamic.Rest01.entities.Emprunt;

/**
 * T : entities à gérer de la BDD
 * ID : type d'objet de ma KEY PRIMARY
 * comme j'hérite d'une interface spring : org.springframework.data.repository.CrudRepository
 * ça devient un Bean Spring
 * @author chris
 *
 */
public interface CrudClientRepo extends CrudRepository<Client, Integer> {
	/**
	 * Ajout d'une requête me retournant la liste des emprunts d'un client
	 * @param id
	 * @return
	 */
	 @Query("select e from Emprunt e where e.clientE.id = :id")
	 public Iterable<Emprunt> findByEmprunt(int id);

}
