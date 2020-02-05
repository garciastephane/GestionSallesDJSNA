package fr.afpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.afpa.entitespersistees.LoginMessageBDD;
import fr.afpa.entitespersistees.LoginMessageBDDId;

@Repository
public interface ILoginMessageRepository extends JpaRepository<LoginMessageBDD, LoginMessageBDDId>{

	
	
}
