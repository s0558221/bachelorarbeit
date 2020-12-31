INSERT INTO Quiz_Lookup_Schwierigkeiten (Schwierigkeit) VALUES('Leicht');
INSERT INTO Quiz_Lookup_Schwierigkeiten (Schwierigkeit) VALUES('Mittel');
INSERT INTO Quiz_Lookup_Schwierigkeiten (Schwierigkeit) VALUES('Schwer');

INSERT INTO Quiz_Lookup_Themengebiete (Themengebiet) VALUES('Allgemein');
INSERT INTO Quiz_Lookup_Themengebiete (Themengebiet) VALUES('Rechnergeschichte');
INSERT INTO Quiz_Lookup_Themengebiete (Themengebiet) VALUES('Speichertechnologien');
INSERT INTO Quiz_Lookup_Themengebiete (Themengebiet) VALUES('Mensch-Computer-Umwelt');

INSERT INTO Quiz_Fragen (Frage,Id_Schwierigkeit,Id_Themengebiet)VALUES('Welches ist die neueste Speichertechnologie?',1,1);

INSERT INTO Quiz_Antworten (Antwort,Id_Frage,IstRichtig) VALUES('Floppy',1,false);
INSERT INTO Quiz_Antworten (Antwort,Id_Frage,IstRichtig) VALUES('SSD',1,true);
INSERT INTO Quiz_Antworten (Antwort,Id_Frage,IstRichtig) VALUES('HDD',1,false);
INSERT INTO Quiz_Antworten (Antwort,Id_Frage,IstRichtig) VALUES('CD',1,false);