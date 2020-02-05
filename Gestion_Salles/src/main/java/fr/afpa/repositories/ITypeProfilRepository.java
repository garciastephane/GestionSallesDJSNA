package fr.afpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.afpa.entitespersistees.TypeProfilBDD;

@Repository
public interface ITypeProfilRepository extends JpaRepository<TypeProfilBDD, Integer> {

}
