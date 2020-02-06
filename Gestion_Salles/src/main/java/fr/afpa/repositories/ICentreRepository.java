package fr.afpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.afpa.entitespersistees.CentreBDD;

@Repository

public interface ICentreRepository extends JpaRepository<CentreBDD, Integer> {

}
