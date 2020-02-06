package fr.afpa.gestionsalles;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import fr.afpa.controles.ControleAuthentificationUtilisateur;
import fr.afpa.controles.ControleChoixUtilisateur;
import fr.afpa.controles.ControleCreationUtilisateur;
import fr.afpa.controles.ControleGeneral;
import fr.afpa.dto.DTOUtilisateur;
import fr.afpa.entites.Message;
import fr.afpa.entites.Personne;
import fr.afpa.entites.RolePersonne;
import fr.afpa.entites.Utilisateur;
import fr.afpa.interfaces.services.IServiceVisualisation;
import fr.afpa.interfaces.controles.IControleAuthentificationUtilisateur;
import fr.afpa.interfaces.controles.IControleChoixUtilisateur;
import fr.afpa.interfaces.controles.IControleCreationUtilisateur;
import fr.afpa.interfaces.controles.IControleGeneral;
import fr.afpa.interfaces.services.IServiceGeneral;
import fr.afpa.interfaces.services.IServiceModificationSalle;
import fr.afpa.interfaces.services.IServiceUtilisateur;
import fr.afpa.services.ServiceCreation;
import fr.afpa.services.ServiceGeneral;
import fr.afpa.services.ServiceModification;
import fr.afpa.services.ServiceUtilisateur;
import fr.afpa.services.ServiceVisualisation;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	private static String loginCourant;
	
	@Autowired
	private IServiceGeneral serviceGeneral;
	@Autowired
	private IServiceVisualisation serviceVisualisation;
	@Autowired
	private IServiceModificationSalle serviceModificationSalle;
	@Autowired
	private IServiceUtilisateur serviceUtilisateur;
	
	@Autowired
	private IControleAuthentificationUtilisateur controleAuthentificationUtilisateur;
	@Autowired
	private IControleChoixUtilisateur controleChoixUtilisateur;
	@Autowired
	private IControleCreationUtilisateur controleCreationUtilisateur;
	@Autowired
	private IControleGeneral controleGeneral;

	/**
	 * Return la vue index (la page d'accueil)
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		return "index";
	}

	/**
	 * Redirige vers le menu
	 * @return la page
	 */
	@RequestMapping(value = "/menu", method = RequestMethod.GET)
	public String home2() {
		return "menu";
	}

	/**
	 * Controller permettant de logguer la personne en fonction de son type de profil, admin ou utilisateur
	 * @param login de la personne
	 * @param password de la personne
	 * @return un model contenant l'utilisateur ou l'admin et la redirection vers les menus associ�s
	 */
	@RequestMapping(value = "/SAP", method = RequestMethod.POST)
	public ModelAndView authentificationPersonne(@RequestParam(value = "login") String login,
			@RequestParam(value = "password") String password) {
		ModelAndView mv = new ModelAndView();

		if (controleAuthentificationUtilisateur.controlePersonneInscrite(login, password)) {
			
			loginCourant = login;
			Personne personne = serviceUtilisateur.utilisateur(login, password);
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
	 * Controller permettant d'afficher l'utilisateur dans la gestion des utilisateurs
	 * @param choix
	 * @return
	 */
	@RequestMapping(value = "/SChU", method = RequestMethod.POST)
	public ModelAndView choixUser(@RequestParam(value = "choix") String choix) {
		ModelAndView mv = new ModelAndView();
	
		DTOUtilisateur dtou = new DTOUtilisateur();
		Map<Integer, Personne> listePersonnes = dtou.listePersonnes();
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
				mv.addObject("alluser", new ServiceVisualisation().afficherUser());
				
				mv.setViewName("choixuser");
		
			}
		} else {
			mv.addObject("choix", choix);
			mv.addObject("alluser", new ServiceVisualisation().afficherUser());
		
			mv.setViewName("choixuser");
		
		}
		 return mv;
	}

	/**
	 * Controller permettant de cr�er une personne
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
			@RequestParam(value = "login") String login, @RequestParam(value = "create") String create) {
	
		ServiceCreation sc = new ServiceCreation();
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
			roleOk = sc.conversionRole(role);
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
		if ("".equals(nomOk) || "".equals(prenomOk) || "".equals(mailOk) || "".equals(adresseOk) || "".equals(loginOk)) {
			mv.addObject("champIncorrect", true);
			if ("pageUser".equals(create)) {
				mv.setViewName("creationCompte");
			}
			else {
				mv.setViewName("creationutilisateur");
			}
		}
		else {
			if ("user".equals(create)) {
				sc.creationPersonne(nom, prenom, dateNaissance, mail, adresse, true, roleOk, login, password, false);
				mv.setViewName("gestionuser");
			} else if ("admin".equals(create)) {
				sc.creationPersonne(nom, prenom, dateNaissance, mail, adresse, true, roleOk, login, password, true);
				mv.setViewName("gestionuser");
			} else if ("pageUser".equals(create)) {
				sc.creationPersonne(nom, prenom, dateNaissance, mail, adresse, false, roleOk, login, password, false);
				mv.setViewName("index");
			}
		}
		
		return mv;

	}
	/**
	 * Controller de deconnexion
	 * @return
	 */
	@RequestMapping(value = "/SD", method = RequestMethod.POST)
	public String deconnexion() {

		return "index";
		
	}

	/**
	 * Controller permettant de modifier un utilisateur (gestion de compte)
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
			@RequestParam(value = "datenaissance") String datenaissance, @RequestParam(value = "id") String id) {
		ModelAndView mv = new ModelAndView();

		String req = modif;
		ServiceModification sm = new ServiceModification();
		switch (req) {
		case "valider":
			if (password.equals(password2)) {
				Personne user = new Utilisateur();
				user.setNom(nom);
				user.setPrenom(prenom);
				user.setEmail(mail);
				user.setAdresse(adresse);
				user.setDateNaissance(serviceGeneral.conversionDate(datenaissance));
				sm.modifierUtilisateur(user, Integer.parseInt(id), password);
			}
			break;
		case "desactiver":
			sm.activerDesactiverUtilisateur(Integer.parseInt(id));
			break;
		case "supprimer":
			sm.supprimerUtilisateur(Integer.parseInt(id));
			break;
		default:
			break;
		}
		mv.setViewName("gestionuser");

		return mv;
	}
	
	/**
	 * Controller permettant de rediriger la personne vers choix ou alluser
	 * @return
	 */
	@RequestMapping(value = "/SRCU", method = RequestMethod.GET)
	public ModelAndView redirectionChoixUser() {
		ModelAndView mv = new ModelAndView();

		mv.addObject("choix", ServiceModification.CHOIX);
		mv.addObject("alluser", new ServiceVisualisation().afficherUser());

		mv.setViewName("choixuser");
	
		return mv;
	}

	/**
	 * Redirection sur le formulaire cr�ationutilisateur
	 * @return
	 */
	@RequestMapping(value = "/SRC", method = RequestMethod.GET)
	public String redirectionCreation() {
		
		return "creationutilisateur";

	}

	/**
	 *Controlleur du Bouton retour
	 * @return
	 */
	@RequestMapping(value = "/Retour", method = RequestMethod.GET)
	public String retour() {
	
		if (new ControleAuthentificationUtilisateur().controleAdmin(loginCourant)) {
			return "gestionuser";
		} else {
			return "menuUser";
		}

	}

	/**
	 * Controlleur permettant de visualiser la liste des utilisateur ( gestion utilisateur )
	 * @return la liste des personnes
	 */
	@RequestMapping(value = "/SVU", method = RequestMethod.GET)
	public ModelAndView visualisationUtilisateur() {
		//ServiceVisualisation sv = new ServiceVisualisation();
		ModelAndView mv = new ModelAndView();
		Map<Integer, Personne> listePersonnes = serviceVisualisation.listeTousPersonnes();
		mv.addObject("listePersonnes", listePersonnes);
		mv.setViewName("visualisationutilisateur");

		return mv;
	}

	/**
	 * Redirection � la cr�ation du compte utilisateur
	 * @return
	 */
	@RequestMapping(value = "/CCPU", method = RequestMethod.GET)
	public String creationCompteParUtilisateur() {
		return "creationCompte";
	}

	/**
	 * Redirection � la cr�ation du message
	 * @return
	 */
	@RequestMapping(value = "/NM", method = RequestMethod.GET)
	public String creationMessage() {
		return "creationMessage";
	}

	/**
	 * Controller permettant d'envoyer un message
	 * @param destinataire
	 * @param objet
	 * @param contenu
	 * @return un boolean pour savoir si le destinaire et valide ou pas
	 */
	@RequestMapping(value = "/EM", method = RequestMethod.POST)
	public ModelAndView envoyerMessage(@RequestParam(value = "destinataire") String destinataire,
			@RequestParam(value = "objet") String objet, @RequestParam(value = "contenu") String contenu) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("destinataire", destinataire);
		mv.addObject("objet", objet);
		mv.addObject("contenu", contenu);
		if (new ControleAuthentificationUtilisateur().controleDestinataire(destinataire)) {
			ServiceCreation sc = new ServiceCreation();
			if (sc.creationMessage(loginCourant, destinataire, objet, contenu, LocalDateTime.now())) {
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
	 * @param id r�cup�ration de l'id pour mettre � true l'archivage du message dans la base de donn�e
	 * @return redirection vers boite de r�ception
	 */
	@RequestMapping(value = "/ARC", method = RequestMethod.POST)
	public ModelAndView archivage(@RequestParam(value="id") int id, @RequestParam(value="page") String page) {
		ModelAndView mv = new ModelAndView();
		ServiceModification sm = new ServiceModification();
		//System.out.println("toto "+id);
		sm.archiverMsg(id);
		if ("boiteReception".equals(page)) {
			mv.setViewName("boiteReception");
		}
		else {
			mv.setViewName("messageEnvoye");
		}
		return mv;
		
	}
	
	/**
	 * Controller permettant d'afficher la liste des messages archiver dans la page Messages archiv�s
	 * @return le model contenant la liste des messages archiv�s et la redirection
	 */
	@RequestMapping(value = "/MA", method = RequestMethod.GET)
	public ModelAndView boiteArchive() {
		ModelAndView mv = new ModelAndView();
		ServiceVisualisation sv = new ServiceVisualisation();
		ArrayList<Message> lm = (ArrayList<Message>) sv.afficherListeMessage(loginCourant);
		lm.addAll(sv.afficherListeMessageEnvoyer(loginCourant));
		mv.addObject("listeMessages", lm);
		mv.setViewName("boiteArchives");
		return mv;
	}
	
	
	/**
	 * Controller permettant d'afficher la liste des messages dans la boite de r�ception
	 * @return les donn�es du model et la vue
	 */
	@RequestMapping(value = "/BR", method = RequestMethod.GET)
	public ModelAndView boiteReception() {
		ModelAndView mv = new ModelAndView();
		// DAOLecture daol = new DAOLecture();
		ServiceVisualisation sv = new ServiceVisualisation();
		ArrayList<Message> lm = (ArrayList<Message>) sv.afficherListeMessage(loginCourant);

		mv.addObject("listeMessages", lm);

		mv.setViewName("boiteReception");
		return mv;
	}
	
	
	
	/**
	 * Controller qui permet d'afficher la liste des messages envoy�s
	 * @return les donn�es du model et la redirection
	 */
	@RequestMapping(value = "/ME", method = RequestMethod.GET)
	public ModelAndView messageEnvoye() {
		ModelAndView mv = new ModelAndView();
		// DAOLecture daol = new DAOLecture();
		ServiceVisualisation sv = new ServiceVisualisation();
		ArrayList<Message> lm = (ArrayList<Message>) sv.afficherListeMessageEnvoyer(loginCourant);
		mv.addObject("listeMessages", lm);

		mv.setViewName("messageEnvoye");
		return mv;
	}
	
	/**
	 * Controller permettant de visualiser un message envoy�
	 * @param destinataire
	 * @param objet
	 * @param contenu
	 * @param date
	 * @return un model contenant le message et la redirection
	 */
	@RequestMapping(value = "/voirE", method = RequestMethod.GET, params = {"destinataire","objet","contenu","date"})
	public ModelAndView voirMessage(@RequestParam(value = "destinataire") List<String> destinataire, @RequestParam(value = "objet") String objet, @RequestParam(value = "contenu") String contenu, @RequestParam(value = "date") String date) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("destinataires",destinataire);
		mv.addObject("objet",objet);
		mv.addObject("contenu",contenu);
		mv.addObject("date",date);
		mv.setViewName("voirMessageEnvoye");
		return mv;
	}

	/**
	 * Controller permettant de visualiser un message recu dans la boite de r�ception
	 * @param expediteur
	 * @param objet
	 * @param contenu
	 * @param date
	 * @return le model contenant le message et la redirection
	 */
	@RequestMapping(value = "/voirR", method = RequestMethod.GET, params = {"expediteur","objet","contenu","date"})
	public ModelAndView voirMessage(@RequestParam(value = "expediteur") String expediteur, @RequestParam(value = "objet") String objet, @RequestParam(value = "contenu") String contenu, @RequestParam(value = "date") String date) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("expediteur", expediteur);
		mv.addObject("objet",objet);
		mv.addObject("contenu",contenu);
		mv.addObject("date",date);
		mv.setViewName("voirMessageRecu");
		return mv;
	}
	
	/**
	 * Return la vue choix de la salle
	 */
	@RequestMapping(value = "/cs", method = RequestMethod.GET)
	public ModelAndView choixSalle() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("allroom", serviceModificationSalle.voirSalle());
		mv.setViewName("choixsalle");
		return mv;
	}
	
	/**
	 * Return la vue modification de la salle
	 */
	@RequestMapping(value = "/ms", method = RequestMethod.GET)
	public String modifSalle(@RequestParam String id) {
		
		return "modifiersalle";
	}
	@RequestMapping(value="/crs", method = RequestMethod.GET)
	public String createSalle() {
		return "creationSalle";
	}
}
