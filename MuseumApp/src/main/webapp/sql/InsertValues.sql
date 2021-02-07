INSERT INTO Quiz_Lookup_Schwierigkeiten (Schwierigkeit) VALUES('Leicht');
INSERT INTO Quiz_Lookup_Schwierigkeiten (Schwierigkeit) VALUES('Mittel');
INSERT INTO Quiz_Lookup_Schwierigkeiten (Schwierigkeit) VALUES('Schwer');

INSERT INTO Quiz_Lookup_Themengebiete (Themengebiet) VALUES('Allgemein');
INSERT INTO Quiz_Lookup_Themengebiete (Themengebiet) VALUES('Rechnergeschichte');
INSERT INTO Quiz_Lookup_Themengebiete (Themengebiet) VALUES('Speichertechnologien');
INSERT INTO Quiz_Lookup_Themengebiete (Themengebiet) VALUES('Mensch-Computer-Umwelt');

INSERT INTO Quiz_Fragen (Frage,Id_Schwierigkeit,Id_Themengebiet)VALUES('Welches ist die neueste Speichertechnologie?',1,1);
INSERT INTO Quiz_Fragen (Frage,Id_Schwierigkeit,Id_Themengebiet)VALUES('Wie wird der Prozessor noch genannt?',1,1);
INSERT INTO Quiz_Fragen (Frage,Id_Schwierigkeit,Id_Themengebiet)VALUES('Wie heißt der erste jemals gebaute Computer?',1,2);
INSERT INTO Quiz_Fragen (Frage,Id_Schwierigkeit,Id_Themengebiet)VALUES('Wer baute den Z3?',1,2);

INSERT INTO Quiz_Antworten (Antwort,Id_Frage,IstRichtig) VALUES('Floppy',1,false);
INSERT INTO Quiz_Antworten (Antwort,Id_Frage,IstRichtig) VALUES('SSD',1,true);
INSERT INTO Quiz_Antworten (Antwort,Id_Frage,IstRichtig) VALUES('HDD',1,false);
INSERT INTO Quiz_Antworten (Antwort,Id_Frage,IstRichtig) VALUES('CD',1,false);

INSERT INTO Quiz_Antworten (Antwort,Id_Frage,IstRichtig) VALUES('CPU',2,true);
INSERT INTO Quiz_Antworten (Antwort,Id_Frage,IstRichtig) VALUES('GPU',2,false);
INSERT INTO Quiz_Antworten (Antwort,Id_Frage,IstRichtig) VALUES('RAM',2,false);
INSERT INTO Quiz_Antworten (Antwort,Id_Frage,IstRichtig) VALUES('ROM',2,false);

INSERT INTO Quiz_Antworten (Antwort,Id_Frage,IstRichtig) VALUES('A1',3,false);
INSERT INTO Quiz_Antworten (Antwort,Id_Frage,IstRichtig) VALUES('Z5',3,false);
INSERT INTO Quiz_Antworten (Antwort,Id_Frage,IstRichtig) VALUES('Z3',3,true);
INSERT INTO Quiz_Antworten (Antwort,Id_Frage,IstRichtig) VALUES('M4',3,false);

INSERT INTO Quiz_Antworten (Antwort,Id_Frage,IstRichtig) VALUES('Bill Gates',4,false);
INSERT INTO Quiz_Antworten (Antwort,Id_Frage,IstRichtig) VALUES('Steve Jobs',4,false);
INSERT INTO Quiz_Antworten (Antwort,Id_Frage,IstRichtig) VALUES('Konrad Zuse',4,true);
INSERT INTO Quiz_Antworten (Antwort,Id_Frage,IstRichtig) VALUES('Max Neumann',4,false);