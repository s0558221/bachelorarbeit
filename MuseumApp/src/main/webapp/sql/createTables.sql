CREATE TABLE IF NOT EXISTS Quiz_Lookup_Schwierigkeiten(
	Id SERIAL PRIMARY KEY,
	Schwierigkeit TEXT NOT NULL);
	
CREATE TABLE IF NOT EXISTS Quiz_Lookup_Themengebiete(
	Id SERIAL PRIMARY KEY,
	Themengebiet TEXT NOT NULL);
	
CREATE TABLE IF NOT EXISTS Quiz_Fragen(
	Id SERIAL PRIMARY KEY,
	Frage TEXT NOT NULL,
	Id_Schwierigkeit INTEGER NOT NULL,
	Id_Themengebiet INTEGER NOT NULL,
	FOREIGN KEY (Id_Themengebiet)
      REFERENCES Quiz_Lookup_Themengebiete (Id),
	FOREIGN KEY (Id_Schwierigkeit)
      REFERENCES Quiz_Lookup_Schwierigkeiten (Id)
	  );
	
CREATE TABLE IF NOT EXISTS Quiz_Antworten(
	Id SERIAL PRIMARY KEY,
	Antwort TEXT NOT NULL,
	Id_Frage INTEGER NOT NULL,
	IstRichtig boolean NOT NULL,
	FOREIGN KEY (Id_Frage)
      REFERENCES Quiz_Fragen (Id)
	);
	
	
GRANT SELECT, INSERT, DELETE, UPDATE
ON TABLE Quiz_Lookup_Schwierigkeiten, Quiz_Lookup_Themengebiete, Quiz_Fragen, Quiz_Antworten  TO _s0558221__museum_app_db_generic;
GRANT USAGE, SELECT ON ALL SEQUENCES IN SCHEMA public TO _s0558221__museum_app_db_generic;