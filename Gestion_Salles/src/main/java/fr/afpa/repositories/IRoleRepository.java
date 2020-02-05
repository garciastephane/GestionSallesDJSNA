package fr.afpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.afpa.entitespersistees.RoleBDD;

@Repository
public interface IRoleRepository extends JpaRepository<RoleBDD, Integer> {

	
	
}
