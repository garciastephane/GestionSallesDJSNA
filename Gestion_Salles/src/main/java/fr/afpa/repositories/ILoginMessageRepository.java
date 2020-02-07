package fr.afpa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.afpa.entitespersistees.LoginMessageBDD;
import fr.afpa.entitespersistees.LoginMessageBDDId;

@Repository
public interface ILoginMessageRepository extends JpaRepository<LoginMessageBDD, LoginMessageBDDId>{

	List<LoginMessageBDD> findByMessageBddAndExpDest(int nombre, boolean b);

	List<LoginMessageBDD> findByLogBddAndExpDest(String login, boolean b);

	
	
}
