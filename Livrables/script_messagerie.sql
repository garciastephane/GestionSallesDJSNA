-- Nom de la BDD : jspjsa
-- Mot de passe de la BDD : admin

CREATE TABLE typeprofil (
	id_type_profil int4 NOT NULL,
	libelle varchar NULL,
	CONSTRAINT typeprofil_pk PRIMARY KEY (id_type_profil)
);

create table roleprofil(
	id_role int4 not null,
	libelle varchar null,
	constraint roleprofil_pk primary key (id_role)
);


create table message(
	id_message int NOT NULL,
	objet varchar(50) not null,
	contenu varchar(255),
	"date" timestamp NOT NULL,
	archive boolean NOT NULL,
	constraint message_pk PRIMARY KEY (id_message)
);

CREATE TABLE profil (
	id_profil int4 NOT NULL,
	nom varchar NULL,
	prenom varchar NULL,
	datenaissance date NULL,
	actif boolean NOT NULL,
	mail varchar(255) NULL,
	adresse varchar NULL,
	id_role int4 not NULL,
	id_type_profil int4 not null,
	CONSTRAINT profil_fk FOREIGN KEY (id_role) REFERENCES roleprofil(id_role),
	CONSTRAINT profil_fk2 FOREIGN KEY (id_type_profil) REFERENCES typeprofil(id_type_profil),
	CONSTRAINT profil_pk PRIMARY KEY (id_profil)
);

CREATE TABLE login (
	id_profil int4 NOT NULL,
	login varchar NOT NULL,
	motdepasse varchar NOT NULL,
	CONSTRAINT log_pk PRIMARY KEY (login),
	CONSTRAINT log_fk FOREIGN KEY (id_profil) REFERENCES profil(id_profil)
);


CREATE TABLE archive (
	id_archive int4 NOT NULL,
	nom varchar NULL,
	prenom varchar NULL,
	datenaissance date NULL,
	mail varchar(255) NULL,
	adresse varchar NULL,
	id_role int4 NULL,
	id_type_profil int4 NULL,
	CONSTRAINT archive_pk PRIMARY KEY (id_archive),
	CONSTRAINT archive_fk FOREIGN KEY (id_type_profil) REFERENCES typeprofil(id_type_profil),
	CONSTRAINT archive_fk2 FOREIGN KEY (id_role) REFERENCES roleprofil(id_role)
);

create table login_message(
	login varchar NOT NULL,
	id_message int NOT NULL,
	expediteur boolean NOT NULL,
	UNIQUE(login, id_message, expediteur),
	constraint login_message_fk1 FOREIGN KEY (login) REFERENCES  login(login),
	constraint login_message_fk2 FOREIGN KEY (id_message) REFERENCES  message(id_message)
);

CREATE TABLE centre (
	id_centre int4 NOT NULL,
	nom varchar(255) NULL,
	CONSTRAINT centre_pkey PRIMARY KEY (id_centre)
);

CREATE TABLE batiment (
	id int4 NOT NULL,
	nom varchar(255) NULL,
	id_centre int4 NULL,
	CONSTRAINT batiment_pkey PRIMARY KEY (id),
	CONSTRAINT batiment_fk FOREIGN KEY (id_centre) REFERENCES centre(id_centre)
);

CREATE TABLE typesalle (
	id int4 NOT NULL,
	"type" varchar(255) NULL,
	CONSTRAINT typesalle_pkey PRIMARY KEY (id)
);

CREATE TABLE salle (
	id int4 NOT NULL,
	capacite int4 NULL,
	nom varchar(255) NULL,
	numero varchar(255) NULL,
	surface float4 NULL,
	batiment int4 NULL,
	typesalle int4 NULL,
	CONSTRAINT salle_pkey PRIMARY KEY (id),
	CONSTRAINT salle_fk FOREIGN KEY (typesalle) REFERENCES typesalle(id),
	CONSTRAINT salle_fk2 FOREIGN KEY (batiment) REFERENCES batiment(id)
);

CREATE TABLE typemateriel (
	id_typemateriel int4 NOT NULL,
	"type" varchar(255) NULL,
	CONSTRAINT typemateriel_pkey PRIMARY KEY (id_typemateriel)
);

CREATE TABLE materiel (
	id int4 NOT NULL,
	quantite int4 NULL,
	salle int4 NULL,
	typemateriel int4 NULL,
	CONSTRAINT materiel_pkey PRIMARY KEY (id),
	CONSTRAINT materiel_fk FOREIGN KEY (salle) REFERENCES salle(id),
	CONSTRAINT materiel_fk2 FOREIGN KEY (typemateriel) REFERENCES typemateriel(id_typemateriel)
);

CREATE TABLE reservation (
	id int4 NOT NULL,
	datedebut date NULL,
	datefin date NULL,
	intitule varchar(255) NULL,
	salle int4 NULL,
	CONSTRAINT reservation_pkey PRIMARY KEY (id),
	CONSTRAINT reservation_fk FOREIGN KEY (salle) REFERENCES salle(id)
);

CREATE TABLE archivesalle (
	id int4 NOT NULL,
	capacite int4 NULL,
	nom varchar(255) NULL,
	numero varchar(255) NULL,
	surface float4 NULL,
	batiment int4 NULL,
	typesalle int4 NULL,
	CONSTRAINT archivesalle_pkey PRIMARY KEY (id),
	CONSTRAINT archivesalle_fk FOREIGN KEY (typesalle) REFERENCES typesalle(id),
	CONSTRAINT archivesalle_fk2 FOREIGN KEY (batiment) REFERENCES batiment(id)
);

CREATE OR REPLACE function archivage() returns trigger AS $archive$
	begin
		insert into archive(id_archive, nom, prenom, datenaissance, mail, adresse, id_role, id_type_profil) values (nextval('archive_seq'),old.nom, old.prenom, old. datenaissance, old.mail, old.adresse, old.id_role, old.id_type_profil);
		return old;
	end
$archive$ LANGUAGE plpgsql;

CREATE TRIGGER archiver before DELETE ON profil FOR EACH ROW EXECUTE FUNCTION archivage();


CREATE OR REPLACE function messageActivation() returns trigger AS $activation$
	begin
		if (select id_type_profil from login natural join profil where login=new.login) = 2 then
			insert into message values (nextval('message_seq'), 'Activation compte', 'Demande d activation de compte.', now(), false);
			insert into login_message values ('admin', currval('message_seq'), false);	
			insert into login_message values (new.login, currval('message_seq'), true);
		end if;
		return new;
	end
$activation$ LANGUAGE plpgsql;

CREATE TRIGGER activation after INSERT ON login FOR EACH ROW EXECUTE FUNCTION messageActivation();

create or replace function creerSalle() returns trigger as $a$
	begin
		insert into materiel(id, quantite, salle, typemateriel) values(nextval('materiel_seq'), 0, currval('salle_seq'), 1);
		insert into materiel(id, quantite, salle, typemateriel) values(nextval('materiel_seq'), 0, currval('salle_seq'), 2);
		insert into materiel(id, quantite, salle, typemateriel) values(nextval('materiel_seq'), 0, currval('salle_seq'), 3);
		return new;
	end;
$a$ language plpgsql;

create trigger creer after insert on salle execute function creerSalle();

create or replace function supprimerSalle() returns trigger as $a$
	begin
		insert into archivesalle(id, capacite, nom, numero, surface, typesalle, batiment) values(nextval('archive_salle_seq'), old.capacite, old.nom, old.numero, old.surface, old.typesalle, old.batiment);
		return old;
	end;
$a$ language plpgsql;

create trigger supprimer before delete on salle for each row execute function supprimerSalle();

INSERT INTO centre (id_centre, nom) VALUES(1, 'AFPA');
INSERT INTO batiment (id, nom, id_centre) VALUES(1, 'Principale', 1);
INSERT INTO typemateriel (id_typemateriel, "type") VALUES(1, 'ORDINATEUR');
INSERT INTO typemateriel (id_typemateriel, "type") VALUES(2, 'PRISE RESEAUX');
INSERT INTO typemateriel (id_typemateriel, "type") VALUES(3, 'RETROPROJECTEUR');
INSERT INTO typesalle (id, "type") VALUES(1, 'BUREAU');
INSERT INTO typesalle (id, "type") VALUES(2, 'FORMATION');
INSERT INTO typesalle (id, "type") VALUES(3, 'INFIRMERIE');
INSERT INTO typesalle (id, "type") VALUES(4, 'REUNION');
INSERT INTO salle (id, capacite, nom, numero, surface, batiment, typesalle) VALUES(nextval('salle_seq'), 19, 'CDA', 'C001', 20, 1, 2);
INSERT INTO salle (id, capacite, nom, numero, surface, batiment, typesalle) VALUES(nextval('salle_seq'), 3, 'CDD', 'C002', 10, 1, 1);
INSERT INTO salle (id, capacite, nom, numero, surface, batiment, typesalle) VALUES(nextval('salle_seq'), 25, 'CDI', 'C003', 25, 1, 4);
INSERT INTO typeprofil(id_type_profil, libelle)VALUES(1, 'Administrateur');
INSERT INTO typeprofil(id_type_profil, libelle)VALUES(2, 'Utilisateur');
INSERT INTO roleprofil(id_role, libelle)VALUES(1, 'Formateur');
INSERT INTO roleprofil(id_role, libelle)VALUES(2, 'Stagiaire');
INSERT INTO profil(id_profil, nom, prenom, datenaissance, actif, mail, adresse, id_role, id_type_profil) VALUES(1, 'Gali', 'Seti', '2020-01-17', true, 'sg@s.g', 'labas', 1, 1);
INSERT INTO login(id_profil, login, motdepasse)VALUES(1, 'admin', 'admin');