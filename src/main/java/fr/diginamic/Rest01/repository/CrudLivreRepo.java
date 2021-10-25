package fr.diginamic.Rest01.repository;

import fr.diginamic.Rest01.entities.Livre;
import org.springframework.data.repository.CrudRepository;

public interface CrudLivreRepo extends CrudRepository<Livre, Integer> {
}
