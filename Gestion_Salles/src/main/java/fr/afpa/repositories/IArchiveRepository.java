package fr.afpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.afpa.entitespersistees.ArchiveBDD;

@Repository
public interface IArchiveRepository extends JpaRepository<ArchiveBDD, Integer> {
	
	
	
}
