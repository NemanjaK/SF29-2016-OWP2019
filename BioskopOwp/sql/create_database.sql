DROP TABLE IF EXISTS film;

CREATE TABLE film(
    id INTEGER PRIMARY KEY,  -- SQLite AUTO_INCREMENT
    naziv VARCHAR(60) NOT NULL,
    reziser VARCHAR(80) NOT NULL,
    glumci VARCHAR(120) NOT NULL,
    zanrovi VARCHAR(70) NOT NULL,
    distributer VARCHAR(45) NOT NULL,
    godinaProizvodnje INT(12) NOT NULL,
    opis TEXT NOT NULL,
    trajanje INT(12) NOT NULL,
    zemljaPorekla VARCHAR(45) NOT NULL
);

INSERT INTO film(naziv, reziser, glumci, zanrovi, distributer, godinaProizvodnje, opis, trajanje, zemljaPorekla) VALUES ('The Shawshank Redemption', 'Frank Darabont', 'Tim Robbins, 	Morgan Freeman, Bob Gunton', 'Drama', ' Castle Rock Entertainment', '1994', 'Two imprisoned men bond over a number of years, finding solace and eventual redemption through acts of common decency.', '144', 'USA');
INSERT INTO film(naziv, reziser, glumci, zanrovi, distributer, godinaProizvodnje, opis, trajanje, zemljaPorekla) VALUES ('The Dark Knight', 'Christopher Nolan', 'Christian Bale, Heath Ledger, Aaron Eckhart ', ' Action, Crime, Drama ', 'Warner Bros', '2008', 'When the menace known as the Joker wreaks havoc and chaos on the people of Gotham, Batman must accept one of the greatest psychological and physical tests of his ability to fight injustice.', '152', 'USA');
INSERT INTO film(naziv, reziser, glumci, zanrovi, distributer, godinaProizvodnje, opis, trajanje, zemljaPorekla) VALUES ('The Intouchables', ' Olivier Nakache', 'Francois Cluzet, Omar Sy, Anne Le Ny ', 'Biography, Comedy, Drama ', 'Quad Productions', '2011', 'After he becomes a quadriplegic from a paragliding accident, an aristocrat hires a young man from the projects to be his caregiver.', '112', 'FRANCE');


DROP TABLE IF EXISTS sala;

CREATE TABLE sala(
    id INTEGER PRIMARY KEY,
    naziv varchar(45) NOT NULL
);

INSERT INTO sala(naziv) VALUES ('SALA1');
INSERT INTO sala(naziv) VALUES ('SALA2');
INSERT INTO sala(naziv) VALUES ('SALA3');

DROP TABLE IF EXIStS  sediste;

CREATE TABLE sediste(
    redniBroj INTEGER PRIMARY KEY NOT NULL
);

INSERT INTO sediste VALUES(1);
INSERT INTO sediste VALUES(2);
INSERT INTO sediste VALUES(3);
INSERT INTO sediste VALUES(4);
INSERT INTO sediste VALUES(5);
INSERT INTO sediste VALUES(6);
INSERT INTO sediste VALUES(7);
INSERT INTO sediste VALUES(8);
INSERT INTO sediste VALUES(9);
INSERT INTO sediste VALUES(10);



CREATE TABLE sediste_sala(
    redniBroj INTEGER NOT NULL,
    sala_id INTEGER NOT NULL,
    FOREIGN KEY(redniBroj) REFERENCES sediste(redniBroj) ON DELETE RESTRICT,
    FOREIGN KEY(sala_id) REFERENCES sala(id) ON DELETE RESTRICT
);

INSERT INTO sediste_sala (redniBroj, sala_id) VALUES (1, 1);
INSERT INTO sediste_sala (redniBroj, sala_id) VALUES (2, 1);
INSERT INTO sediste_sala (redniBroj, sala_id) VALUES (3, 1);
INSERT INTO sediste_sala (redniBroj, sala_id) VALUES (4, 1);
INSERT INTO sediste_sala (redniBroj, sala_id) VALUES (5, 1);
INSERT INTO sediste_sala (redniBroj, sala_id) VALUES (1, 2);
INSERT INTO sediste_sala (redniBroj, sala_id) VALUES (2, 2);
INSERT INTO sediste_sala (redniBroj, sala_id) VALUES (3, 2);
INSERT INTO sediste_sala (redniBroj, sala_id) VALUES (4, 2);
INSERT INTO sediste_sala (redniBroj, sala_id) VALUES (5, 2);
INSERT INTO sediste_sala (redniBroj, sala_id) VALUES (1, 3);
INSERT INTO sediste_sala (redniBroj, sala_id) VALUES (2, 3);
INSERT INTO sediste_sala (redniBroj, sala_id) VALUES (3, 3);
INSERT INTO sediste_sala (redniBroj, sala_id) VALUES (4, 3);
INSERT INTO sediste_sala (redniBroj, sala_id) VALUES (5, 3);

DROP TABLE IF EXISTS korisnik

    CREATE TABLE korisnik(
    korisnickoIme varchar(45) NOT NULL PRIMARY KEY,
    lozinka varchar(60) NOT NULL,
    datumRegistracije date NOT NULL,
    uloga VARCHAR(20) NOT NULL DEFAULT 'KORISNIK'
);

INSERT INTO korisnik (korisnickoIme, lozinka, datumRegistracije, uloga) VALUES ('aca', '123', '2019-12-20', 'ADMIN');
INSERT INTO korisnik (korisnickoIme, lozinka, datumRegistracije, uloga) VALUES ('pera', '123', '2019-11-24', 'KORISNIK');
INSERT INTO korisnik (korisnickoIme, lozinka, datumRegistracije, uloga) VALUES ('zika', '123', '2019-09-10', 'KORISNIK');

DROP TABLE IF EXISTS tipProjekcije

CREATE TABLE tipProjekcije(
    id INTEGER PRIMARY KEY NOT NULL,
    naziv VARCHAR(15) NOT NULL
);


INSERT INTO tipProjekcije(naziv) VALUES ('2D'),('3D'),('4D')

DROP TABLE IF EXISTS projekcija

CREATE TABLE projekcija(
    id INTEGER NOT NULL PRIMARY KEY,
    tipProjekcije_id  INTEGER NOT NULL,
    sala_id INTEGER NOT NULL,
    vremePrikazivanja DATETIME NOT NULL,
    cenaKarte DOUBLE NOT NULL,
    korisnickoIme VARCHAR(45) NOT NULL,
    film_id INTEGER NOT NULL,
    FOREIGN KEY(tipProjekcije_id) REFERENCES tipProjekcije(id) ON DELETE RESTRICT,
    FOREIGN KEY(sala_id) REFERENCES sala(id) ON DELETE RESTRICT,
    FOREIGN KEY(korisnickoIme) REFERENCES korisnik(korisnickoIme) ON DELETE RESTRICT,
    FOREIGN KEY(film_id) REFERENCES film(id) ON DELETE RESTRICT   
);

INSERT INTO projekcija (tipProjekcije_id, sala_id, vremePrikazivanja, cenaKarte, korisnickoIme, film_id) VALUES (1, 1, '2019-12-20 20:00', 250.00, 'aca', 1);
INSERT INTO projekcija (tipProjekcije_id, sala_id, vremePrikazivanja, cenaKarte, korisnickoIme, film_id) VALUES (2, 2, '2019-12-21 20:00', 350.00, 'aca', 2);
INSERT INTO projekcija (tipProjekcije_id, sala_id, vremePrikazivanja, cenaKarte, korisnickoIme, film_id) VALUES (3, 3, '2019-12-22 20:00', 450.00, 'aca', 2);


DROP TABLE IF EXISTS karta;

CREATE TABLE karta(
    id INTEGER NOT NULL PRIMARY KEY,
    projekcija_id INTEGER NOT NULL,
    sediste_redniBroj INTEGER NOT NULL,
    vremeProdaje DATETIME NOT NULL,
    korisnickoIme VARCHAR(45) NOT NULL,
    FOREIGN KEY(projekcija_id) REFERENCES projekcija(id),
    FOREIGN KEY(sediste_redniBroj) REFERENCES sediste(redniBroj),
    FOREIGN KEY(korisnickoIme) REFERENCES korisnik(korisnickoIme)  
);

INSERT INTO karta(projekcija_id, sediste_redniBroj, vremeProdaje, korisnickoIme) VALUES (1, 1,'2019-12-10 13:00', 'pera');
INSERT INTO karta(projekcija_id, sediste_redniBroj, vremeProdaje, korisnickoIme) VALUES (2, 2,'2019-12-12 14:00', 'pera');
INSERT INTO karta(projekcija_id, sediste_redniBroj, vremeProdaje, korisnickoIme) VALUES (3, 1,'2019-12-20 16:00', 'pera');
INSERT INTO karta(projekcija_id, sediste_redniBroj, vremeProdaje, korisnickoIme) VALUES (1, 4,'2019-12-10 11:00', 'pera');