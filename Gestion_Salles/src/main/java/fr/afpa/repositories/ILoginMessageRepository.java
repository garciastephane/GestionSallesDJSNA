package fr.afpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.afpa.entitespersistees.LoginMessageBDD;
import fr.afpa.entitespersistees.LoginMessageBDDId;

public interface ILoginMessageRepository extends JpaRepository<LoginMessageBDD, LoginMessageBDDId>{

	
	
}
