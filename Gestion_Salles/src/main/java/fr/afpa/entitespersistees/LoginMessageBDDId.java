package fr.afpa.entitespersistees;

import java.io.Serializable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@EqualsAndHashCode

public class LoginMessageBDDId implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String logBdd;
	private int messageBdd;
	private boolean expDest;
	
}
