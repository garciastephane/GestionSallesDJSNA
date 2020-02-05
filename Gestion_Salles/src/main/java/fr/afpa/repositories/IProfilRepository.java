package fr.afpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.afpa.entitespersistees.ProfilBDD;


public interface IProfilRepository extends JpaRepository<ProfilBDD, Integer> {

	
	
}
