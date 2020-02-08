package fr.afpa.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.afpa.entites.Reservation;
import fr.afpa.entitespersistees.ReservationBDD;
import fr.afpa.interfaces.dto.IDTOGeneral;
import fr.afpa.interfaces.dto.IDTOVisualisation;
import fr.afpa.repositories.IReservationRepositoriy;
import fr.afpa.repositories.ISalleRepository;

@Service
public class DTOVisualisation implements IDTOVisualisation {
	
	@Autowired
	private IReservationRepositoriy reservationRepository;
	@Autowired
	private ISalleRepository salleRepository;
	@Autowired
	private IDTOGeneral dtoGeneral;
	
	@Override
	public List<Reservation> listeReservationSalle(int id) {
		List<ReservationBDD> listeReservations = new ArrayList<ReservationBDD>();
		listeReservations.addAll(reservationRepository.findBySalle(salleRepository.findById(id).get()));
		return dtoGeneral.listReservationBDDToListReservation(listeReservations);
	}

}
