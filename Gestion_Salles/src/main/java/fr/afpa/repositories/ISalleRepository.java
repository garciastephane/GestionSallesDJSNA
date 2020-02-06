package fr.afpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.afpa.entitespersistees.SalleBDD;

@Repository
public interface ISalleRepository extends JpaRepository<SalleBDD, Integer> {
	
}
