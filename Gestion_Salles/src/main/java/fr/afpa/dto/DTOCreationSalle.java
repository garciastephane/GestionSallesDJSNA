package fr.afpa.dto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.afpa.entites.Reservation;
import fr.afpa.entites.Salle;
import fr.afpa.entitespersistees.ReservationBDD;
import fr.afpa.entitespersistees.SalleBDD;
import fr.afpa.interfaces.dto.IDTOCreationSalle;
import fr.afpa.interfaces.dto.IDTOGeneral;
import fr.afpa.repositories.IBatimentRepository;
import fr.afpa.repositories.ICentreRepository;
import fr.afpa.repositories.IReservationRepositoriy;
import fr.afpa.repositories.ISalleRepository;
import fr.afpa.repositories.ITypeSalleRepository;

@Service

public class DTOCreationSalle implements IDTOCreationSalle {
	
	@Autowired
	
	ITypeSalleRepository typeSalleRepository;
	@Autowired

	ISalleRepository salleRepository;
	@Autowired
	
	ICentreRepository centreRepository;
	@Autowired
	
	IBatimentRepository batimentRepository;
	
	@Autowired
	private IReservationRepositoriy reservationRepository;
	@Autowired
	private IDTOGeneral dtoGeneral;

	@Override
	
	public boolean ajoutSalle(Salle salle, String batiment, String type) {
		SalleBDD salleBDD = new SalleBDD();
		
		salleBDD.setBatiment(batimentRepository.findById(Integer.parseInt(batiment)).get());
		salleBDD.setCapacite(salle.getCapacite());
		salleBDD.setId(salle.getId());
		salleBDD.setNom(salle.getNom());
		salleBDD.setNumero(salle.getNumero());
		salleBDD.setSurface(salle.getSurface());
		salleBDD.setTypeSalle(typeSalleRepository.findById(Integer.parseInt(batiment)).get());
		salleRepository.save(salleBDD);

		return true;
	}

	@Override
	public boolean ajoutReservation(Reservation reservation, int idSalle) {
		try {
			ReservationBDD reservBDD = dtoGeneral.reservationToReservationBDD(reservation, idSalle);
			reservationRepository.save(reservBDD);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
