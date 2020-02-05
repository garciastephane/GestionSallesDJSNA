package fr.afpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.afpa.entitespersistees.MessageBDD;


@Repository
public interface IMessageRepository extends JpaRepository<MessageBDD, Integer> {

}
