CREATE TABLE IF NOT EXISTS Quiz_Lookup_Schwierigkeiten(
	Id INT AUTO_INCREMENT PRIMARY KEY,
	Schwierigkeit TEXT NOT NULL);
	
CREATE TABLE IF NOT EXISTS Quiz_Lookup_Themengebiete(
	Id INT AUTO_INCREMENT PRIMARY KEY,
	Themengebiet TEXT NOT NULL);
	
CREATE TABLE IF NOT EXISTS Quiz_Fragen(
	Id INT AUTO_INCREMENT PRIMARY KEY,
	Frage TEXT NOT NULL,
	Id_Schwierigkeit INTEGER NOT NULL,
	Id_Themengebiet INTEGER NOT NULL,
	FOREIGN KEY (Id_Themengebiet)
      REFERENCES Quiz_Lookup_Themengebiete (Id),
	FOREIGN KEY (Id_Schwierigkeit)
      REFERENCES Quiz_Lookup_Schwierigkeiten (Id)
	  );
	
CREATE TABLE IF NOT EXISTS Quiz_Antworten(
	Id INT AUTO_INCREMENT PRIMARY KEY,
	Antwort TEXT NOT NULL,
	Id_Frage INTEGER NOT NULL,
	IstRichtig boolean NOT NULL,
	FOREIGN KEY (Id_Frage)
      REFERENCES Quiz_Fragen (Id)
	);