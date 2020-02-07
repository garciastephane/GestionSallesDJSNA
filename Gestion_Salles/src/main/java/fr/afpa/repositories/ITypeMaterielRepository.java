package fr.afpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.afpa.entitespersistees.TypeMaterielBDD;

public interface ITypeMaterielRepository extends JpaRepository<TypeMaterielBDD, Integer> {

}
