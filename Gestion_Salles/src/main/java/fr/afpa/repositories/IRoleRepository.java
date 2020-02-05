package fr.afpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.afpa.entitespersistees.RoleBDD;

public interface IRoleRepository extends JpaRepository<RoleBDD, Integer> {

	
	
}
