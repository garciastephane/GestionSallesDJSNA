package fr.afpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.afpa.entitespersistees.MessageBDD;

public interface IMessageRepository extends JpaRepository<MessageBDD, Integer> {

}
