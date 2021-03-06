package fr.afpa.gestionsalles;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import fr.afpa.entites.Administrateur;
import fr.afpa.entites.Message;
import fr.afpa.entites.Personne;
import fr.afpa.entites.RolePersonne;
import fr.afpa.entites.Salle;
import fr.afpa.entites.TypeSalle;
import fr.afpa.entites.Utilisateur;
import fr.afpa.interfaces.controles.IControleAuthentificationUtilisateur;
import fr.afpa.interfaces.controles.IControleChoixUtilisateur;
import fr.afpa.interfaces.controles.IControleCreationUtilisateur;
import fr.afpa.interfaces.controles.IControleGeneral;
import fr.afpa.interfaces.dto.IDTOUtilisateurs;
import fr.afpa.interfaces.services.IServiceCreation;
import fr.afpa.interfaces.services.IServiceGeneral;
import fr.afpa.interfaces.services.IServiceModification;
import fr.afpa.interfaces.services.IServiceModificationSalle;
import fr.afpa.interfaces.services.IServiceUtilisateur;
import fr.afpa.interfaces.services.IServiceVisualisation;
import fr.afpa.interfaces.services.IServicesCreationSalle;
import fr.afpa.services.ServiceModification;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	@Autowired
	private IServiceGeneral serviceGeneral;
	@Autowired
	private IServiceVisualisation serviceVisualisation;
	@Autowired
	private IServiceModification serviceModification;
	@Autowired
	private IServiceModificationSalle serviceModificationSalle;
	@Autowired
	private IServiceUtilisateur serviceUtilisateur;
	@Autowired
	private IServiceCreation serviceCreation;

	@Autowired
	private IDTOUtilisateurs dtoUtilisateur;

	@Autowired
	private IControleAuthentificationUtilisateur controleAuthentificationUtilisateur;
	@Autowired
	private IControleChoixUtilisateur controleChoixUtilisateur;
	@Autowired
	private IControleCreationUtilisateur controleCreationUtilisateur;
	@Autowired
	private IControleGeneral controleGeneral;
	@Autowired
	private IServicesCreationSalle serviceCreationSalle;

	/**
	 * Return la vue index (la page d'accueil)
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		return "index";
	}

	/**
	 * Redirige vers le menu
	 * 
	 * @return la page
	 */
	@RequestMapping(value = "/menu", method = RequestMethod.GET)
	public String home2(HttpServletRequest request) {
		if (request.getSession().getAttribute("personneCourante") == null) {
			return "index";
		}
		return "menu";
	}
	

	/**
	 * Controller permettant de logguer la personne en fonction de son type de
	 * profil, admin ou utilisateur
	 * 
	 * @param login    de la personne
	 * @param password de la personne
	 * @return un model contenant l'utilisateur ou l'admin et la redirection vers
	 *         les menus associ�s
	 */
	@RequestMapping(value = "/SAP", method = RequestMethod.POST)
	public ModelAndView authentificationPersonne(@RequestParam(value = "login") String login,
			@RequestParam(value = "password") String password, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();

		if (controleAuthentificationUtilisateur.controlePersonneInscrite(login, password)) {
			Personne personne = serviceUtilisateur.utilisateur(login, password);
			request.getSession().setAttribute("personneCourante", personne);
			request.getSession().setAttribute("loginCourant", login);
			if (personne instanceof Utilisateur) {
				mv.addObject("personne", personne);
				mv.setViewName("menuUser");
			} else {
				mv.addObject("personne", personne);
				mv.setViewName("gestionuser");
			}

		} else {
			mv.setViewName("index");

		}
		return mv;
	}

	/**
	 * Controller permettant d'afficher l'utilisateur dans la gestion des
	 * utilisateurs
	 * 
	 * @param choix
	 * @return
	 */
	@RequestMapping(value = "/SChU", method = RequestMethod.POST)
	public ModelAndView choixUser(@RequestParam(value = "choix") String choix, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		if (request.getSession().getAttribute("personneCourante") == null) {
			mv.setViewName("index");
			return mv;
		}
		else if (request.getSession().getAttribute("personneCourante") instanceof Utilisateur) {
			mv.setViewName(retour(request));
			return mv;
		}
		
		Map<Integer, Personne> listePersonnes = dtoUtilisateur.listePersonnes();
		if (controleChoixUtilisateur.verificationChoix(choix)) {
			Personne personne = listePersonnes.get(Integer.parseInt(choix));
			if (personne instanceof Utilisateur) {
				mv.addObject("personne", personne);
				mv.addObject("id", Integer.parseInt(choix));
				mv.addObject("datenaissance",
						personne.getDateNaissance().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));

				mv.setViewName("modifierutilisateur");

			} else {
				mv.addObject("choix", choix);
				mv.addObject("alluser", serviceVisualisation.afficherUser());

				mv.setViewName("choixuser");

			}
		} else {
			mv.addObject("choix", choix);
			mv.addObject("alluser", serviceVisualisation.afficherUser());

			mv.setViewName("choixuser");

		}
		return mv;
	}

	/**
	 * Controller permettant de cr�er une personne
	 * 
	 * @param nom
	 * @param prenom
	 * @param mail
	 * @param adresse
	 * @param role
	 * @param datenaissance
	 * @param password
	 * @param password2
	 * @param login
	 * @param create
	 * @return
	 */
	@RequestMapping(value = "/SCU", method = RequestMethod.POST)
	public ModelAndView createUser(@RequestParam(value = "nom") String nom,
			@RequestParam(value = "prenom") String prenom, @RequestParam(value = "mail") String mail,
			@RequestParam(value = "adresse") String adresse, @RequestParam(value = "role") String role,
			@RequestParam(value = "datenaissance") String datenaissance,
			@RequestParam(value = "password") String password, @RequestParam(value = "password2") String password2,
			@RequestParam(value = "login") String login, @RequestParam(value = "create") String create,
			HttpServletRequest request) {

		ModelAndView mv = new ModelAndView();
		
		String nomOk = "";
		String prenomOk = "";
		String mailOk;
		String adresseOk;
		RolePersonne roleOk = RolePersonne.STAGIAIRE;
		LocalDate dateNaissance = LocalDate.now();
		String loginOk = "";
		String passwordOk = null;

		if (controleGeneral.controleNomPrenom(nom)) {
			nomOk = nom;
		}
		if (controleGeneral.controleNomPrenom(prenom)) {
			prenomOk = prenom;
		}
		mailOk = mail;
		adresseOk = adresse;
		if (controleGeneral.controleRole(role)) {
			roleOk = serviceCreation.conversionRole(role);
		}
		if (controleGeneral.controleDateDeNaissance(datenaissance)) {
			dateNaissance = serviceGeneral.conversionDate(datenaissance);
		}
		if (password.equals(password2) && controleCreationUtilisateur.controleLogin(login)) {
			loginOk = login;
			passwordOk = password;
		} else {
			mv.addObject("nom", nom);
			mv.addObject("prenom", prenom);
			mv.addObject("mail", mail);
			mv.addObject("adresse", adresse);
			mv.addObject("role", role);
			mv.addObject("datenaissance", datenaissance);
			mv.addObject("login", login);

			if (controleCreationUtilisateur.controleLogin(login)) {
				mv.addObject("existe", false);

			} else {
				mv.addObject("existe", true);

			}
		}
		if ("".equals(nomOk) || "".equals(prenomOk) || "".equals(mailOk) || "".equals(adresseOk)
				|| "".equals(loginOk)) {
			mv.addObject("champIncorrect", true);
			if ("pageUser".equals(create)) {
				mv.setViewName("creationCompte");
			} else {
				mv.setViewName("creationutilisateur");
			}
		} else {
			if ("user".equals(create)) {
				serviceCreation.creationPersonne(nom, prenom, dateNaissance, mail, adresse, true, roleOk, login,
						password, false);
				mv.setViewName("gestionuser");
			} else if ("admin".equals(create)) {
				serviceCreation.creationPersonne(nom, prenom, dateNaissance, mail, adresse, true, roleOk, login,
						password, true);
				mv.setViewName("gestionuser");
			} else if ("pageUser".equals(create)) {
				serviceCreation.creationPersonne(nom, prenom, dateNaissance, mail, adresse, false, roleOk, login,
						password, false);
				mv.setViewName("index");
			}
		}

		return mv;

	}

	/**
	 * Controller de deconnexion
	 * 
	 * @return
	 */
	@RequestMapping(value = "/SD", method = RequestMethod.POST)
	public String deconnexion(HttpServletRequest request) {
		request.getSession().invalidate();
		return "index";

	}

	/**
	 * Controller permettant de modifier un utilisateur (gestion de compte)
	 * 
	 * @param modif
	 * @param password
	 * @param password2
	 * @param nom
	 * @param prenom
	 * @param mail
	 * @param adresse
	 * @param datenaissance
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/SMU", method = RequestMethod.POST)
	public ModelAndView modificationUtilisateur(@RequestParam(value = "modif") String modif,
			@RequestParam(value = "password") String password, @RequestParam(value = "password2") String password2,
			@RequestParam(value = "nom") String nom, @RequestParam(value = "prenom") String prenom,
			@RequestParam(value = "mail") String mail, @RequestParam(value = "adresse") String adresse,
			@RequestParam(value = "datenaissance") String datenaissance, @RequestParam(value = "id") String id,
			HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		if (request.getSession().getAttribute("personneCourante") == null) {
			mv.setViewName("index");
			return mv;
		}
		else if (request.getSession().getAttribute("personneCourante") instanceof Utilisateur) {
			mv.setViewName(retour(request));
			return mv;
		}
		String req = modif;
		switch (req) {
		case "valider":
			if (password.equals(password2)) {
				Personne user = new Utilisateur();
				user.setNom(nom);
				user.setPrenom(prenom);
				user.setEmail(mail);
				user.setAdresse(adresse);
				user.setDateNaissance(serviceGeneral.conversionDate(datenaissance));
				serviceModification.modifierUtilisateur(user, Integer.parseInt(id), password);
			}
			break;
		case "desactiver":
			serviceModification.activerDesactiverUtilisateur(Integer.parseInt(id));
			break;
		case "supprimer":
			serviceModification.supprimerUtilisateur(Integer.parseInt(id));
			break;
		default:
			break;
		}
		mv.setViewName("gestionuser");

		return mv;
	}

	/**
	 * Controller permettant de rediriger la personne vers choix ou alluser
	 * 
	 * @return
	 */
	@RequestMapping(value = "/SRCU", method = RequestMethod.GET)
	public ModelAndView redirectionChoixUser(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		if (request.getSession().getAttribute("personneCourante") == null) {
			mv.setViewName("index");
			return mv;
		}
		else if (request.getSession().getAttribute("personneCourante") instanceof Utilisateur) {
			mv.setViewName(retour(request));
			return mv;
		}
		mv.addObject("choix", ServiceModification.CHOIX);
		mv.addObject("alluser", serviceVisualisation.afficherUser());

		mv.setViewName("choixuser");

		return mv;
	}

	/**
	 * Redirection sur le formulaire cr�ationutilisateur
	 * 
	 * @return
	 */
	@RequestMapping(value = "/SRC", method = RequestMethod.GET)
	public String redirectionCreation(HttpServletRequest request) {
		if (request.getSession().getAttribute("personneCourante") == null) {
			return "index";
		}
		else if (request.getSession().getAttribute("personneCourante") instanceof Utilisateur) {
			return retour(request);
		}
		return "creationutilisateur";

	}

	/**
	 * Controlleur du Bouton retour
	 * 
	 * @return
	 */
	@RequestMapping(value = "/Retour", method = RequestMethod.GET)
	public String retour(HttpServletRequest request) {
		if (request.getSession().getAttribute("personneCourante") == null) {
			return "index";
		}
		if (request.getSession().getAttribute("personneCourante") instanceof Administrateur) {
			return "gestionuser";
		} else {
			return "menuUser";
		}

	}

	/**
	 * Controlleur permettant de visualiser la liste des utilisateur ( gestion
	 * utilisateur )
	 * 
	 * @return la liste des personnes
	 */
	@RequestMapping(value = "/SVU", method = RequestMethod.GET)
	public ModelAndView visualisationUtilisateur(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		if (request.getSession().getAttribute("personneCourante") == null) {
			mv.setViewName("index");
			return mv;
		}
		else if (request.getSession().getAttribute("personneCourante") instanceof Utilisateur) {
			mv.setViewName(retour(request));
			return mv;
		}
		Map<Integer, Personne> listePersonnes = serviceVisualisation.listeTousPersonnes();
		mv.addObject("listePersonnes", listePersonnes);
		mv.setViewName("visualisationutilisateur");

		return mv;
	}

	/**
	 * Redirection � la cr�ation du compte utilisateur
	 * 
	 * @return
	 */
	@RequestMapping(value = "/CCPU", method = RequestMethod.GET)
	public String creationCompteParUtilisateur() {
		
		return "creationCompte";
	}

	/**
	 * Redirection � la cr�ation du message
	 * 
	 * @return
	 */
	@RequestMapping(value = "/NM", method = RequestMethod.GET)
	public String creationMessage() {
		return "creationMessage";
	}

	/**
	 * Controller permettant d'envoyer un message
	 * 
	 * @param destinataire
	 * @param objet
	 * @param contenu
	 * @return un boolean pour savoir si le destinaire et valide ou pas
	 */
	@RequestMapping(value = "/EM", method = RequestMethod.POST)
	public ModelAndView envoyerMessage(@RequestParam(value = "destinataire") String destinataire,
			@RequestParam(value = "objet") String objet, @RequestParam(value = "contenu") String contenu, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		if (request.getSession().getAttribute("personneCourante") == null) {
			mv.setViewName("index");
			return mv;
		}
		mv.addObject("destinataire", destinataire);
		mv.addObject("objet", objet);
		mv.addObject("contenu", contenu);
		if (controleAuthentificationUtilisateur.controleDestinataire(destinataire)) {
			if (serviceCreation.creationMessage((String) request.getSession().getAttribute("loginCourant"), destinataire, objet, contenu, LocalDateTime.now())) {
				mv.setViewName("confirmationMessageEnvoye");
			} else {
				mv.addObject("invalide", true);
				mv.setViewName("creationMessage");
			}
		} else {
			mv.addObject("invalide", true);
			mv.setViewName("creationMessage");
		}
		return mv;
	}

	/**
	 * Controller permettant d'achiver un message
	 * 
	 * @param id r�cup�ration de l'id pour mettre � true l'archivage du message dans
	 *           la base de donn�e
	 * @return redirection vers boite de r�ception
	 */
	@RequestMapping(value = "/ARC", method = RequestMethod.POST)
	public ModelAndView archivage(@RequestParam(value = "id") int id, @RequestParam(value = "page") String page, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		if (request.getSession().getAttribute("personneCourante") == null) {
			mv.setViewName("index");
			return mv;
		}
		serviceModification.archiverMsg(id);
		if ("boiteReception".equals(page)) {
			return boiteReception(request);
		} else {
			return messageEnvoye(request);
		}
	}

	/**
	 * Controller permettant d'afficher la liste des messages archiver dans la page
	 * Messages archiv�s
	 * 
	 * @return le model contenant la liste des messages archiv�s et la redirection
	 */
	@RequestMapping(value = "/MA", method = RequestMethod.GET)
	public ModelAndView boiteArchive(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		if (request.getSession().getAttribute("personneCourante") == null) {
			mv.setViewName("index");
			return mv;
		}
		ArrayList<Message> lm = (ArrayList<Message>) serviceVisualisation.afficherListeMessage((String) request.getSession().getAttribute("loginCourant"));
		lm.addAll(serviceVisualisation.afficherListeMessageEnvoyer((String) request.getSession().getAttribute("loginCourant")));
		mv.addObject("listeMessages", lm);
		mv.setViewName("boiteArchives");
		return mv;
	}

	/**
	 * Controller permettant d'afficher la liste des messages dans la boite de
	 * r�ception
	 * 
	 * @return les donn�es du model et la vue
	 */
	@RequestMapping(value = "/BR", method = RequestMethod.GET)
	public ModelAndView boiteReception(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		if (request.getSession().getAttribute("personneCourante") == null) {
			mv.setViewName("index");
			return mv;
		}
		ArrayList<Message> lm = (ArrayList<Message>) serviceVisualisation.afficherListeMessage((String) request.getSession().getAttribute("loginCourant"));

		mv.addObject("listeMessages", lm);
		mv.setViewName("boiteReception");
		return mv;
	}

	/**
	 * Controller qui permet d'afficher la liste des messages envoy�s
	 * 
	 * @return les donn�es du model et la redirection
	 */
	@RequestMapping(value = "/ME", method = RequestMethod.GET)
	public ModelAndView messageEnvoye(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		if (request.getSession().getAttribute("personneCourante") == null) {
			mv.setViewName("index");
			return mv;
		}
		ArrayList<Message> lm = (ArrayList<Message>) serviceVisualisation.afficherListeMessageEnvoyer((String) request.getSession().getAttribute("loginCourant"));
		mv.addObject("listeMessages", lm);

		mv.setViewName("messageEnvoye");
		return mv;
	}

	/**
	 * Controller permettant de visualiser un message envoy�
	 * 
	 * @param destinataire
	 * @param objet
	 * @param contenu
	 * @param date
	 * @return un model contenant le message et la redirection
	 */
	@RequestMapping(value = "/voirE", method = RequestMethod.GET, params = { "destinataire", "objet", "contenu",
			"date" })
	public ModelAndView voirMessage(@RequestParam(value = "destinataire") List<String> destinataire,
			@RequestParam(value = "objet") String objet, @RequestParam(value = "contenu") String contenu,
			@RequestParam(value = "date") String date, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		if (request.getSession().getAttribute("personneCourante") == null) {
			mv.setViewName("index");
			return mv;
		}
		mv.addObject("destinataires", destinataire);
		mv.addObject("objet", objet);
		mv.addObject("contenu", contenu);
		mv.addObject("date", date);
		mv.setViewName("voirMessageEnvoye");
		return mv;
	}

	/**
	 * Controller permettant de visualiser un message recu dans la boite de
	 * r�ception
	 * 
	 * @param expediteur
	 * @param objet
	 * @param contenu
	 * @param date
	 * @return le model contenant le message et la redirection
	 */
	@RequestMapping(value = "/voirR", method = RequestMethod.GET, params = { "expediteur", "objet", "contenu", "date" })
	public ModelAndView voirMessage(@RequestParam(value = "expediteur") String expediteur,
			@RequestParam(value = "objet") String objet, @RequestParam(value = "contenu") String contenu,
			@RequestParam(value = "date") String date, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		if (request.getSession().getAttribute("personneCourante") == null) {
			mv.setViewName("index");
			return mv;
		}
		mv.addObject("expediteur", expediteur);
		mv.addObject("objet", objet);
		mv.addObject("contenu", contenu);
		mv.addObject("date", date);
		mv.setViewName("voirMessageRecu");
		return mv;
	}

	/**
	 * Return la vue choix de la salle
	 */
	@RequestMapping(value = "/cs", method = RequestMethod.GET)
	public ModelAndView choixSalle(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		if (request.getSession().getAttribute("personneCourante") == null) {
			mv.setViewName("index");
			return mv;
		}
		else if (request.getSession().getAttribute("personneCourante") instanceof Utilisateur) {
			mv.setViewName(retour(request));
			return mv;
		}
		mv.addObject("allroom", serviceModificationSalle.voirSalle());
		mv.setViewName("choixsalle");
		return mv;
	}

	@RequestMapping(value = "/vs", method = RequestMethod.GET)
	public ModelAndView voirSalle(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		if (request.getSession().getAttribute("personneCourante") == null) {
			mv.setViewName("index");
			return mv;
		}
		mv.addObject("allroom", serviceModificationSalle.listeSalleComplete());
		mv.setViewName("visualisationrsalle");
		return mv;
	}
	
	@RequestMapping(value = "/vrc", method = RequestMethod.GET)
	public ModelAndView voirSalleComplete(@RequestParam(name = "id") String id, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		if (request.getSession().getAttribute("personneCourante") == null) {
			mv.setViewName("index");
			return mv;
		}
		mv.addObject("id", serviceModificationSalle.getSalleComplete(id));
		mv.setViewName("visualisationrsallecomplete");
		return mv;
	}

	@RequestMapping(value = "/sc", method = RequestMethod.POST)
	public ModelAndView salleChoisi(@RequestParam(name = "id") String id, @RequestParam(name = "res") String res, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		if (request.getSession().getAttribute("personneCourante") == null) {
			mv.setViewName("index");
			return mv;
		}
		else if (request.getSession().getAttribute("personneCourante") instanceof Utilisateur) {
			mv.setViewName(retour(request));
			return mv;
		}
		mv.addObject("id", id);
		Salle salle = serviceModificationSalle.getSalle(id);
		if (salle != null) {
			if ("Reserver".equals(res)) {
				mv.addObject("reservations", serviceVisualisation.listeReservations(Integer.parseInt(id)));
				mv.setViewName("reserverSalle");
			} else {
				mv.addObject("materiel", serviceModificationSalle.voirMateriel(Integer.parseInt(id)));
				mv.addObject("salle", salle);
				mv.setViewName("modifiersalle");
			}
		} else {
			mv.addObject("allroom", serviceModificationSalle.voirSalle());
			mv.setViewName("choixsalle");
		}
		return mv;
	}

	/**
	 * Return la vue modification de la salle
	 */
	@RequestMapping(value = "/ms", method = RequestMethod.POST)
	public String modifSalle(@RequestParam String id, HttpServletRequest request) {
		if (request.getSession().getAttribute("personneCourante") == null) {
			return "index";
		}
		else if (request.getSession().getAttribute("personneCourante") instanceof Utilisateur) {
			return retour(request);
		}
		return "modifiersalle";
	}

	@RequestMapping(value = "/crs", method = RequestMethod.GET)
	public ModelAndView createSalle(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		if (request.getSession().getAttribute("personneCourante") == null) {
			mv.setViewName("index");
			return mv;
		}
		else if (request.getSession().getAttribute("personneCourante") instanceof Utilisateur) {
			mv.setViewName(retour(request));
			return mv;
		}
		mv.addObject("listebatiment", serviceModificationSalle.listerBatiment());
		mv.setViewName("creationSalle");
		mv.addObject("listeTypeSalle", TypeSalle.values());
		return mv;
	}

	@RequestMapping(value = "/rms", method = RequestMethod.POST)
	public ModelAndView redirectionModifSalle(@RequestParam(value = "numsalle") String numsalle,
			@RequestParam(value = "nomsalle") String nomsalle, @RequestParam(value = "surface") String surface,
			@RequestParam(value = "capacite") String capacite, @RequestParam(value = "type") String type,
			@RequestParam(value = "modif") String modif, @RequestParam(value = "id") String id,
			@RequestParam(name = "1") String retro, @RequestParam(name = "2") String ordi,
			@RequestParam(name = "3") String reseau, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		if (request.getSession().getAttribute("personneCourante") == null) {
			mv.setViewName("index");
			return mv;
		}
		else if (request.getSession().getAttribute("personneCourante") instanceof Utilisateur) {
			mv.setViewName(retour(request));
			return mv;
		}
		switch (modif) {
		case "valider":
			Salle salle = new Salle(numsalle, nomsalle, Integer.parseInt(capacite), Float.parseFloat(surface),
					TypeSalle.valueOf(type.toUpperCase()));
			salle.setId(Integer.parseInt(id));
			serviceModificationSalle.updateSalle(salle, Integer.parseInt(ordi), Integer.parseInt(reseau),
					Integer.parseInt(retro));
			break;
		case "supprimer":
			serviceModificationSalle.supprimerSalle(Integer.parseInt(id));
			break;

		default:
			break;
		}
		mv.addObject("allroom", serviceModificationSalle.voirSalle());
		mv.setViewName("choixsalle");
		return mv;
	}

	/**
	 * @param batiment
	 * @param numero   de salle
	 * @param nom      de salle
	 * @param surface  de la salle
	 * @param capacite de la salle
	 * @param type     de salle Return la vue modification de la salle
	 */
	@RequestMapping(value = "/asbdd", method = RequestMethod.POST)
	public String ajoutSalleBdd(@RequestParam(value = "batiment") String batiment,
			@RequestParam(value = "numsalle") String numsalle, @RequestParam(value = "nomsalle") String nomsalle,
			@RequestParam(value = "surface") String surface, @RequestParam(value = "capacite") String capacite,
			@RequestParam(value = "type") String type, HttpServletRequest request) {
		if (request.getSession().getAttribute("personneCourante") == null) {
			return "index";
		}
		else if (request.getSession().getAttribute("personneCourante") instanceof Utilisateur) {
			return retour(request);
		}
		Salle salle = new Salle(numsalle, nomsalle, Integer.parseInt(capacite), Float.parseFloat(surface),
				TypeSalle.values()[Integer.parseInt(type)]);
		serviceCreationSalle.ajoutSalleBdd(salle, batiment, type);
		return "creationSalle";
	}


	@RequestMapping(value = "/Reserver", method = RequestMethod.POST)
	public ModelAndView reserver(@RequestParam(value = "debut") String debut,
			@RequestParam(value = "duree") String duree,
			@RequestParam(value = "id") String idSalle,
			@RequestParam(value = "intitule") String intitule, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		if (request.getSession().getAttribute("personneCourante") == null) {
			mv.setViewName("index");
			return mv;
		}
		else if (request.getSession().getAttribute("personneCourante") instanceof Utilisateur) {
			mv.setViewName(retour(request));
			return mv;
		}
		if (controleGeneral.controleDateDeNaissance(debut) 
			&& controleGeneral.controleTailleObjetMesage(intitule)
			&& controleChoixUtilisateur.verificationChoix(duree)
			&& controleGeneral.controleDateObsolete(serviceGeneral.conversionDate(debut))
			&& controleGeneral.controleCollisionDates(serviceVisualisation.listeReservations(Integer.parseInt(idSalle))
					, serviceGeneral.conversionDate(debut), Integer.parseInt(duree))
			&& serviceCreationSalle.creationReservation(intitule, serviceGeneral.conversionDate(debut)
					, Integer.parseInt(duree), Integer.parseInt(idSalle))) {
				mv.setViewName("confirmationReservationEffectuee");
		}
		else {
			mv.addObject("id", idSalle);
			mv.addObject("reservations", serviceVisualisation.listeReservations(Integer.parseInt(idSalle)));
			mv.addObject("invalide", true);
			mv.setViewName("reserverSalle");
		}
		return mv;
	}

}
