package fr.afpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.afpa.entitespersistees.TypeProfilBDD;


public interface ITypeProfilRepository extends JpaRepository<TypeProfilBDD, Integer> {

}
