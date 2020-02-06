package fr.afpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.afpa.entitespersistees.LogBDD;
import fr.afpa.entitespersistees.ProfilBDD;

@Repository
public interface IProfilRepository extends JpaRepository<ProfilBDD, Integer> {

	ProfilBDD findByLoginMdp(LogBDD findByLoginAndMotdepasse);

	
	
}
