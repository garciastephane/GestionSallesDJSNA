package fr.afpa.interfaces.services;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import fr.afpa.entites.RolePersonne;
import fr.afpa.entitespersistees.RoleBDD;

@Service
public interface IServiceGeneral {

	public LocalDate conversionDate(Date date);
	
	public Date conversionDate(LocalDate date);
	
	public LocalDateTime conversionDateTime(Timestamp dateTime);
	
	public Timestamp conversionDateTime(LocalDateTime dateTime);
	
	public LocalDate conversionDate(String date);
	
	public RoleBDD conversionRole(RolePersonne role);
	
	public List<String> conversionStringEnListe(String chaine);
	
}
