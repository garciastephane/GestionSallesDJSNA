package fr.afpa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.afpa.entitespersistees.LogBDD;
import fr.afpa.entitespersistees.LoginMessageBDD;
import fr.afpa.entitespersistees.LoginMessageBDDId;
import fr.afpa.entitespersistees.MessageBDD;

@Repository
public interface ILoginMessageRepository extends JpaRepository<LoginMessageBDD, LoginMessageBDDId>{

	List<LoginMessageBDD> findByMessageBddAndExpDest(MessageBDD messageBDD, boolean b);

	List<LoginMessageBDD> findByLogBddAndExpDest(LogBDD logBDD, boolean b);

	
	
}
