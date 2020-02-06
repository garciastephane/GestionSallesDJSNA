package fr.afpa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.afpa.entitespersistees.BatimentBDD;

@Repository

public interface IBatimentRepository extends JpaRepository<BatimentBDD, Integer> {

	List<BatimentBDD> findByNom(String nom);

}
