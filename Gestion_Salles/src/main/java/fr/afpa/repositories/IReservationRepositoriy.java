package fr.afpa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.afpa.entitespersistees.ReservationBDD;
import fr.afpa.entitespersistees.SalleBDD;

@Repository
public interface IReservationRepositoriy extends JpaRepository<ReservationBDD, Integer> {

	List<ReservationBDD> findBySalle(SalleBDD salleBDD);

}
