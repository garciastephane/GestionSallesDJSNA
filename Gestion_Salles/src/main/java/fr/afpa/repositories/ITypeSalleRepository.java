package fr.afpa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.afpa.entitespersistees.TypeSalleBDD;

@Repository

public interface ITypeSalleRepository extends JpaRepository<TypeSalleBDD, Integer> {
List<TypeSalleBDD> findByType(String type);
}
