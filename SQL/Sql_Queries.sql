CREATE TABLE projects(
id SERIAL PRIMARY KEY,
nomProjet VARCHAR(50) NOT NULL,
margeBeneficiaire NUMERIC,
coutTotal NUMERIC,
tauxTva NUMERIC,
client_id INT ,
projectStatus etatProjet,
 CONSTRAINT fk_client
      FOREIGN KEY(client_id) 
	  REFERENCES clients(id)
	  ON DELETE CASCADE
	  ON UPDATE CASCADE
);


CREATE TABLE composant(
id SERIAL PRIMARY KEY,
nom VARCHAR(50)
);


CREATE TABLE devis(
id SERIAL PRIMARY KEY,
project_id INT,
montantEstime NUMERIC,
dateEmission DATE,
dateValidite DATE,
accepte BOOLEAN ,
 CONSTRAINT fk_project
      FOREIGN KEY(project_id) 
	  REFERENCES project(id)
	  ON DELETE CASCADE
	  ON UPDATE CASCADE
);



CREATE TABLE materiels(
PRIMARY KEY (id),
project_id INT,
coutUnitaire NUMERIC NOT NULL,
quantite NUMERIC NOT NULL,
coutTransport NUMERIC NOT NULL,
coefficientQualite NUMERIC NOT NULL,
 CONSTRAINT fk_project
      FOREIGN KEY(project_id) 
	  REFERENCES projects(id)
	  ON DELETE CASCADE
	  ON UPDATE CASCADE
)INHERITS(composants);

CREATE TABLE personnels(
PRIMARY KEY (id),
project_id INT,
tauxHoraire NUMERIC NOT NULL,
heuresTravail NUMERIC NOT NULL,
productiviteOuvrier NUMERIC NOT NULL,
 CONSTRAINT fk_project
      FOREIGN KEY(project_id) 
	  REFERENCES projects(id)
	  ON DELETE CASCADE
	  ON UPDATE CASCADE
)INHERITS(composants);

CREATE TYPE etatProjet AS ENUM('EN_COURS', 'TERMINE', 'ANNULE');


SELECT enum_range(null::etatProjet)