package fr.afpa.repositories;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.afpa.entitespersistees.ProfilBDD;

@Repository
@ComponentScan("fr.afpa.repositories")
public interface IProfilRepository extends JpaRepository<ProfilBDD, Integer> {

	
	
}
