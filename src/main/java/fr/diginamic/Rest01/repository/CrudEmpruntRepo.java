package fr.diginamic.Rest01.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import fr.diginamic.Rest01.entities.Emprunt;
import fr.diginamic.Rest01.entities.Livre;

public interface CrudEmpruntRepo extends CrudRepository<Emprunt, Integer> {
	/**
	 * Ajout d'une requête me retournant la liste des Livres d'un Emprunt
	 * @see https://docs.jboss.org/hibernate/orm/4.3/devguide/en-US/html/ch11.html
	 * On utilise MEMBER OF l.empruntLivres car c'est un objet Set 
	 * @param id
	 * @return
	 */
	 @Query("select l from Livre l where :emp MEMBER OF l.empruntLivres")
	 public Iterable<Livre> findByLivre(Emprunt emp);

}
