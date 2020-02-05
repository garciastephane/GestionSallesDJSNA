package fr.afpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.afpa.entitespersistees.LogBDD;

public interface ILogRepository extends JpaRepository<LogBDD, String>{

	
	
}
