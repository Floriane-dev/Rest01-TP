package fr.diginamic.Rest01.entities;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

@Entity
@Table(name="CLIENT")
public class Client {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@NotNull
	@Length(min = 2,max= 30)
	private String nom;
	
	@NotNull
	@Length(min = 2,max= 30)		
	private String prenom;
	/*
	@OneToMany(mappedBy="clientE")
	private Set<Emprunt> emprunts;
	
	public Set<Emprunt> getEmprunts() {
		return emprunts;
	}

	public void setEmprunts(Set<Emprunt> emprunts) {
		this.emprunts = emprunts;
	}
	 **/
	public Client() {
		super();
		// TODO Auto-generated constructor stub*
		/**
		 * Instancie emprunts
		 * pour �viter des collections
		 * � nullet des incoh�rences
		 * quand le client a rien
		 * emprunter
		 */
		//emprunts = new HashSet<Emprunt>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	@Override
	public String toString() {
		return "Client [id=" + id + ", nom=" + nom + ", prenom=" + prenom + "]";
	}

	
}
