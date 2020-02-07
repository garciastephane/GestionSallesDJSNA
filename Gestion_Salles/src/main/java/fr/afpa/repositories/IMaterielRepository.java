package fr.afpa.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import fr.afpa.entitespersistees.MaterielBDD;
import fr.afpa.entitespersistees.SalleBDD;

@Repository
public interface IMaterielRepository extends JpaRepository<MaterielBDD, Integer> {
	
	List<MaterielBDD> findBySalle(SalleBDD salle);
	
	@Query(value = "select quantite from materiel m, typemateriel t where m.typemateriel=t.id_typemateriel and salle = ?1 and t.\"type\" = ?2", nativeQuery = true)
	List<Integer> findQuantiteByTypeMateriel(int id, String type);
	
}
