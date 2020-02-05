package fr.afpa.services;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import fr.afpa.entites.RolePersonne;
import fr.afpa.entitespersistees.RoleBDD;

public class ServiceGeneral {

	private ServiceGeneral() {
	}
	
	/**
	 * Permet de convertir une date de type sql.Date en date de type LocalDate
	 * 
	 * @param date : la date a convertir
	 * @return la date convertie en LocalDate
	 */
	public static LocalDate conversionDate(Date date) {
		return date.toLocalDate();
	}

	/**
	 * Permet de convertir une date de type LocalDate en date de type sql.TimeStamp
	 * 
	 * @param date : la date a convertir
	 * @return la date convertie en sql.Date
	 */
	public static Date conversionDate(LocalDate date) {
		return Date.valueOf(date);
	}
	
	/**
	 * Permet de convertir une date et une heure de type sql.TimeStamp 
	 * en date et heure de type LocalDateTime
	 * 
	 * @param date : la date a convertir
	 * @return la date convertie en LocalDateTime
	 */
	public static LocalDateTime conversionDateTime(Timestamp dateTime) {
		return dateTime.toLocalDateTime();
	}

	/**
	 * Permet de convertir une date et une heure de type LocalDateTime en
	 * date et heure de type sql.TimeStamp
	 * 
	 * @param dateTime : la date a convertir
	 * @return la date convertie en sql.TimeStamp
	 */
	public static Timestamp conversionDateTime(LocalDateTime dateTime) {
		return Timestamp.valueOf(dateTime);
	}

	/**
	 * Permet de convertir une date de type String en date de type LocalDate
	 * 
	 * @param date la date a convertir
	 * @return la date convertie
	 */
	public static LocalDate conversionDate(String date) {
		LocalDate lDate = null;
		try {
			lDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		} catch (DateTimeParseException e) {
			lDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		}
		return lDate;
	}

	/**
	 * Permet de convertir un role de type RolePersonne en role de type RoleBDD
	 * 
	 * @param role le role a convertir
	 * @return le role converti
	 */
	public static RoleBDD conversionRole(RolePersonne role) {
		return new RoleBDD(role.getRole());
	}
	
	/**
	 * Permet de convertir une chaine de caracteres en liste de chaines de 
	 * caracteres avec ";" comme separateur
	 * @param chaine : la chaine de caracteres a decouper
	 * @return la chaine de caracteres decoupee dans une liste
	 */
	public static List<String> conversionStringEnListe(String chaine) {
		List<String> res = new ArrayList<String>();
		String[] tableau = chaine.split(";");
		for (String string : tableau) {
			res.add(string);
		}
		return res;
	}
}
