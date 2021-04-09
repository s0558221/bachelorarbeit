INSERT INTO Quiz_Lookup_Schwierigkeiten (Schwierigkeit) VALUES('Leicht');
INSERT INTO Quiz_Lookup_Schwierigkeiten (Schwierigkeit) VALUES('Mittel');
INSERT INTO Quiz_Lookup_Schwierigkeiten (Schwierigkeit) VALUES('Schwer');

INSERT INTO Quiz_Lookup_Themengebiete (Themengebiet) VALUES('Allgemein');
INSERT INTO Quiz_Lookup_Themengebiete (Themengebiet) VALUES('Rechnergeschichte');
INSERT INTO Quiz_Lookup_Themengebiete (Themengebiet) VALUES('Speichertechnologien');
INSERT INTO Quiz_Lookup_Themengebiete (Themengebiet) VALUES('Geschichte des Rechnens');

INSERT INTO Quiz_Fragen (Frage,Id_Schwierigkeit,Id_Themengebiet)VALUES('Welches ist die neueste Speichertechnologie?',1,3);
INSERT INTO Quiz_Fragen (Frage,Id_Schwierigkeit,Id_Themengebiet)VALUES('Wie wird der Prozessor noch genannt?',1,1);
INSERT INTO Quiz_Fragen (Frage,Id_Schwierigkeit,Id_Themengebiet)VALUES('Wie heißt der erste jemals gebaute Computer?',1,2);
INSERT INTO Quiz_Fragen (Frage,Id_Schwierigkeit,Id_Themengebiet)VALUES('Wer baute den Z3?',1,2);
INSERT INTO Quiz_Fragen (Frage,Id_Schwierigkeit,Id_Themengebiet)VALUES('Wie wurde die Diskette noch bezeichnet?',1,3);
INSERT INTO Quiz_Fragen (Frage,Id_Schwierigkeit,Id_Themengebiet)VALUES('Wann wurde der Zuse Z3 gebaut?',1,2);
INSERT INTO Quiz_Fragen (Frage,Id_Schwierigkeit,Id_Themengebiet)VALUES('Wann wurde die Firma Intel gegründet?',1,2);
INSERT INTO Quiz_Fragen (Frage,Id_Schwierigkeit,Id_Themengebiet)VALUES('Wie viel MB können maximal auf einer 3,5 Zoll Diskette gespeichert werden?',1,3);
INSERT INTO Quiz_Fragen (Frage,Id_Schwierigkeit,Id_Themengebiet)VALUES('Welcher Heimcomputer war ein Konkurrent des Atari 2600?',2,2);
INSERT INTO Quiz_Fragen (Frage,Id_Schwierigkeit,Id_Themengebiet)VALUES('Wie hoch war der damalige Einführungspreis des Apple Imac?',2,2);
INSERT INTO Quiz_Fragen (Frage,Id_Schwierigkeit,Id_Themengebiet)VALUES('In welchem berliner Stadtbezirk liegt das Museum?',1,1);
INSERT INTO Quiz_Fragen (Frage,Id_Schwierigkeit,Id_Themengebiet)VALUES('Unter welchem Namen ist der Apple Classic II noch bekannt?',2,2);
INSERT INTO Quiz_Fragen (Frage,Id_Schwierigkeit,Id_Themengebiet)VALUES('Wie wird das Rechenbrett noch genannt?',1,4);
INSERT INTO Quiz_Fragen (Frage,Id_Schwierigkeit,Id_Themengebiet)VALUES('Wie wird die Festplatte häufig abgekürzt?',1,3);
INSERT INTO Quiz_Fragen (Frage,Id_Schwierigkeit,Id_Themengebiet)VALUES('Wie lautet der Name einer nicht mehr produzierten Art von Wechselplattenlaufwerken?',2,3);
INSERT INTO Quiz_Fragen (Frage,Id_Schwierigkeit,Id_Themengebiet)VALUES('Wie heißt der erste Mikroprozessor der Firma Intel?',2,2);
INSERT INTO Quiz_Fragen (Frage,Id_Schwierigkeit,Id_Themengebiet)VALUES('Wann wurde die Von-Neumann-Architektur vorgestellt?',2,2);
INSERT INTO Quiz_Fragen (Frage,Id_Schwierigkeit,Id_Themengebiet)VALUES('Wann wurde Adam Ries geboren?',1,4);
INSERT INTO Quiz_Fragen (Frage,Id_Schwierigkeit,Id_Themengebiet)VALUES('Wie lange halten Magentbänder im Durchschnitt?',2,3);
INSERT INTO Quiz_Fragen (Frage,Id_Schwierigkeit,Id_Themengebiet)VALUES('Wann wurde die Firma AMD gegründet?',1,2);
INSERT INTO Quiz_Fragen (Frage,Id_Schwierigkeit,Id_Themengebiet)VALUES('Wie viele Zeichen passen auf eine Lochkarte?',2,3);

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

INSERT INTO Quiz_Antworten (Antwort,Id_Frage,IstRichtig) VALUES('Floppy Disk',5,true);
INSERT INTO Quiz_Antworten (Antwort,Id_Frage,IstRichtig) VALUES('Flippy Disk',5,false);
INSERT INTO Quiz_Antworten (Antwort,Id_Frage,IstRichtig) VALUES('Flappy Disk',5,false);
INSERT INTO Quiz_Antworten (Antwort,Id_Frage,IstRichtig) VALUES('Fleppy Disk',5,false);

INSERT INTO Quiz_Antworten (Antwort,Id_Frage,IstRichtig) VALUES('1935',6,false);
INSERT INTO Quiz_Antworten (Antwort,Id_Frage,IstRichtig) VALUES('1939',6,false);
INSERT INTO Quiz_Antworten (Antwort,Id_Frage,IstRichtig) VALUES('1941',6,true);
INSERT INTO Quiz_Antworten (Antwort,Id_Frage,IstRichtig) VALUES('1954',6,false);

INSERT INTO Quiz_Antworten (Antwort,Id_Frage,IstRichtig) VALUES('15. September 1965',7,false);
INSERT INTO Quiz_Antworten (Antwort,Id_Frage,IstRichtig) VALUES('16. August 1966',7,false);
INSERT INTO Quiz_Antworten (Antwort,Id_Frage,IstRichtig) VALUES('17. Juni 1967',7,false);
INSERT INTO Quiz_Antworten (Antwort,Id_Frage,IstRichtig) VALUES('18. Juli 1968',7,true);

INSERT INTO Quiz_Antworten (Antwort,Id_Frage,IstRichtig) VALUES('3,4',8,true);
INSERT INTO Quiz_Antworten (Antwort,Id_Frage,IstRichtig) VALUES('1,6',8,false);
INSERT INTO Quiz_Antworten (Antwort,Id_Frage,IstRichtig) VALUES('4,8',8,false);
INSERT INTO Quiz_Antworten (Antwort,Id_Frage,IstRichtig) VALUES('5,4',8,false);

INSERT INTO Quiz_Antworten (Antwort,Id_Frage,IstRichtig) VALUES('Commond 65',9,false);
INSERT INTO Quiz_Antworten (Antwort,Id_Frage,IstRichtig) VALUES('Atari 500',9,false);
INSERT INTO Quiz_Antworten (Antwort,Id_Frage,IstRichtig) VALUES('Atari 900',9,false);
INSERT INTO Quiz_Antworten (Antwort,Id_Frage,IstRichtig) VALUES('Commondore 64',9,true);

INSERT INTO Quiz_Antworten (Antwort,Id_Frage,IstRichtig) VALUES('2199 Mark',10,false);
INSERT INTO Quiz_Antworten (Antwort,Id_Frage,IstRichtig) VALUES('2999 Mark',10,true);
INSERT INTO Quiz_Antworten (Antwort,Id_Frage,IstRichtig) VALUES('2499 Mark',10,false);
INSERT INTO Quiz_Antworten (Antwort,Id_Frage,IstRichtig) VALUES('1999 Mark',10,false);

INSERT INTO Quiz_Antworten (Antwort,Id_Frage,IstRichtig) VALUES('Friedrichshain-Kreuzberg',11,false);
INSERT INTO Quiz_Antworten (Antwort,Id_Frage,IstRichtig) VALUES('Treptow-Köpenick',11,true);
INSERT INTO Quiz_Antworten (Antwort,Id_Frage,IstRichtig) VALUES('Marzahn-Hellersdorf',11,false);
INSERT INTO Quiz_Antworten (Antwort,Id_Frage,IstRichtig) VALUES('Lichtenberg',11,false);

INSERT INTO Quiz_Antworten (Antwort,Id_Frage,IstRichtig) VALUES('Performa 200',12,true);
INSERT INTO Quiz_Antworten (Antwort,Id_Frage,IstRichtig) VALUES('Performa 300',12,false);
INSERT INTO Quiz_Antworten (Antwort,Id_Frage,IstRichtig) VALUES('Performa 400',12,false);
INSERT INTO Quiz_Antworten (Antwort,Id_Frage,IstRichtig) VALUES('Performa 500',12,false);

INSERT INTO Quiz_Antworten (Antwort,Id_Frage,IstRichtig) VALUES('Lochkarte',13,false);
INSERT INTO Quiz_Antworten (Antwort,Id_Frage,IstRichtig) VALUES('Tafelwerk',13,false);
INSERT INTO Quiz_Antworten (Antwort,Id_Frage,IstRichtig) VALUES('Taschenrechner',13,false);
INSERT INTO Quiz_Antworten (Antwort,Id_Frage,IstRichtig) VALUES('Abakus',13,true);

INSERT INTO Quiz_Antworten (Antwort,Id_Frage,IstRichtig) VALUES('RAM',14,false);
INSERT INTO Quiz_Antworten (Antwort,Id_Frage,IstRichtig) VALUES('ODD',14,false);
INSERT INTO Quiz_Antworten (Antwort,Id_Frage,IstRichtig) VALUES('HDD',14,true);
INSERT INTO Quiz_Antworten (Antwort,Id_Frage,IstRichtig) VALUES('ROM',14,false);

INSERT INTO Quiz_Antworten (Antwort,Id_Frage,IstRichtig) VALUES('XML',15,false);
INSERT INTO Quiz_Antworten (Antwort,Id_Frage,IstRichtig) VALUES('RAW',15,false);
INSERT INTO Quiz_Antworten (Antwort,Id_Frage,IstRichtig) VALUES('ZIP',15,true);
INSERT INTO Quiz_Antworten (Antwort,Id_Frage,IstRichtig) VALUES('BMP',15,false);

INSERT INTO Quiz_Antworten (Antwort,Id_Frage,IstRichtig) VALUES('6006',16,false);
INSERT INTO Quiz_Antworten (Antwort,Id_Frage,IstRichtig) VALUES('5005',16,false);
INSERT INTO Quiz_Antworten (Antwort,Id_Frage,IstRichtig) VALUES('4004',16,true);
INSERT INTO Quiz_Antworten (Antwort,Id_Frage,IstRichtig) VALUES('3003',16,false);

INSERT INTO Quiz_Antworten (Antwort,Id_Frage,IstRichtig) VALUES('1945',17,true);
INSERT INTO Quiz_Antworten (Antwort,Id_Frage,IstRichtig) VALUES('1946',17,false);
INSERT INTO Quiz_Antworten (Antwort,Id_Frage,IstRichtig) VALUES('1947',17,false);
INSERT INTO Quiz_Antworten (Antwort,Id_Frage,IstRichtig) VALUES('1948',17,false);

INSERT INTO Quiz_Antworten (Antwort,Id_Frage,IstRichtig) VALUES('1432',18,false);
INSERT INTO Quiz_Antworten (Antwort,Id_Frage,IstRichtig) VALUES('1492',18,true);
INSERT INTO Quiz_Antworten (Antwort,Id_Frage,IstRichtig) VALUES('1567',18,false);
INSERT INTO Quiz_Antworten (Antwort,Id_Frage,IstRichtig) VALUES('1637',18,false);

INSERT INTO Quiz_Antworten (Antwort,Id_Frage,IstRichtig) VALUES('5 Jahre',19,false);
INSERT INTO Quiz_Antworten (Antwort,Id_Frage,IstRichtig) VALUES('10 Jahre',19,false);
INSERT INTO Quiz_Antworten (Antwort,Id_Frage,IstRichtig) VALUES('20 Jahre',19,false);
INSERT INTO Quiz_Antworten (Antwort,Id_Frage,IstRichtig) VALUES('< 30 Jahr',19,true);

INSERT INTO Quiz_Antworten (Antwort,Id_Frage,IstRichtig) VALUES('1. Juni 1970',20,false);
INSERT INTO Quiz_Antworten (Antwort,Id_Frage,IstRichtig) VALUES('1. Mai 1969',20,true);
INSERT INTO Quiz_Antworten (Antwort,Id_Frage,IstRichtig) VALUES('1.Juli 1971',20,false);
INSERT INTO Quiz_Antworten (Antwort,Id_Frage,IstRichtig) VALUES('1.August 1968',20,false);

INSERT INTO Quiz_Antworten (Antwort,Id_Frage,IstRichtig) VALUES('70',21,false);
INSERT INTO Quiz_Antworten (Antwort,Id_Frage,IstRichtig) VALUES('80',21,true);
INSERT INTO Quiz_Antworten (Antwort,Id_Frage,IstRichtig) VALUES('90',21,false);
INSERT INTO Quiz_Antworten (Antwort,Id_Frage,IstRichtig) VALUES('100',21,false);
	