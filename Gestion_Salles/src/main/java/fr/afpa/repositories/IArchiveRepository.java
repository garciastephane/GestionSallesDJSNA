package fr.afpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.afpa.entitespersistees.ArchiveBDD;


public interface IArchiveRepository extends JpaRepository<ArchiveBDD, Integer> {
	
	
	
}
