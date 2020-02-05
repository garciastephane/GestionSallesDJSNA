package fr.afpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.afpa.entitespersistees.LogBDD;

@Repository
public interface ILogRepository extends JpaRepository<LogBDD, String>{

	
	
}
