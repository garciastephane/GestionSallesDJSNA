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


INSERT INTO typeprofil(id_type_profil, libelle)VALUES(1, 'Administrateur');
INSERT INTO typeprofil(id_type_profil, libelle)VALUES(2, 'Utilisateur');
INSERT INTO roleprofil(id_role, libelle)VALUES(1, 'Formateur');
INSERT INTO roleprofil(id_role, libelle)VALUES(2, 'Stagiaire');
INSERT INTO profil VALUES(1, 'Gali', 'Seti', '2020-01-17', true, 'sg@s.g', 'labas', 1, 1);
INSERT INTO login(id_profil, login, motdepasse)VALUES(1, 'admin', 'admin');