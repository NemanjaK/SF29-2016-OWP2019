CREATE TABLE IF NOT EXISTS `bioskop`.`tipProjekcije` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `naziv` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`id`));


CREATE TABLE IF NOT EXISTS `bioskop`.`sala_sediste` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `sala_id` INT NOT NULL,
  `sediste_redniBroj` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_sala_sediste_sala1_idx` (`sala_id` ASC) VISIBLE,
  INDEX `fk_sala_sediste_sediste1_idx` (`sediste_redniBroj` ASC) VISIBLE,
  CONSTRAINT `fk_sala_sediste_sala_id`
    FOREIGN KEY (`sala_id`)
    REFERENCES `bioskop`.`sala` (`id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
  CONSTRAINT `fk_sala_sediste_sediste_redniBroj`
    FOREIGN KEY (`sediste_redniBroj`)
    REFERENCES `bioskop`.`sediste` (`redniBroj`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE);

CREATE TABLE IF NOT EXISTS `bioskop`.`film` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `naziv` VARCHAR(100) NOT NULL,
  `reziser` VARCHAR(80) NOT NULL,
  `glumci` VARCHAR(245) NOT NULL,
  `zanrovi` VARCHAR(60) NOT NULL,
  `distributer` VARCHAR(45) NOT NULL,
  `godinaProizvodnje` SMALLINT NOT NULL,
  `opis` TEXT NOT NULL,
  `trajanje` INT NOT NULL,
  `zemljaPorekla` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`));

CREATE TABLE IF NOT EXISTS `bioskop`.`sediste` (
  `redni_broj` INT(11) NOT NULL,
  PRIMARY KEY (`redni_broj`));



CREATE TABLE IF NOT EXISTS `bioskop`.`korisnik` (
  `korisnickoIme` VARCHAR(45) NOT NULL,
  `lozinka` VARCHAR(45) NOT NULL,
  `datumRegistracije` DATE NOT NULL,
  `uloga` ENUM('KORISNIK', 'ADMIN') NOT NULL,
  PRIMARY KEY (`korisnickoIme`));


CREATE TABLE IF NOT EXISTS `bioskop`.`projekcija` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `tipProjekcije_id` INT NOT NULL,
  `sala_id` INT NOT NULL,
  `vremePrikazivanja` DATETIME NOT NULL,
  `cenaKarte` DOUBLE NOT NULL,
  `korisnickoIme` VARCHAR(45) NOT NULL,
  `film_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_projekcija_tipProjekcije_id_idx` (`tipProjekcije_id` ASC) VISIBLE,
  INDEX `fk_projekcija_sala_id_idx` (`sala_id` ASC) VISIBLE,
  INDEX `fk_projekcija_korisnik_korisnickoIme_idx` (`korisnickoIme` ASC) VISIBLE,
  INDEX `fk_projekcija_film1_idx` (`film_id` ASC) VISIBLE,
  CONSTRAINT `fk_projekcija_tipProjekcije_id`
    FOREIGN KEY (`tipProjekcije_id`)
    REFERENCES `bioskop`.`tipProjekcije` (`id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
  CONSTRAINT `fk_projekcija_sala_id`
    FOREIGN KEY (`sala_id`)
    REFERENCES `bioskop`.`sala` (`id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
  CONSTRAINT `fk_projekcija_korisnik_korisnickoIme`
    FOREIGN KEY (`korisnickoIme`)
    REFERENCES `bioskop`.`korisnik` (`korisnickoIme`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
  CONSTRAINT `fk_projekcija_film`
    FOREIGN KEY (`film_id`)
    REFERENCES `bioskop`.`film` (`id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE);

CREATE TABLE IF NOT EXISTS `bioskop`.`karta` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `projekcija_id` INT NOT NULL,
  `sediste_redniBroj` INT NOT NULL,
  `vremeProdaje` DATETIME NOT NULL,
  `korisnickoIme` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_karta_projekcija_id_idx` (`projekcija_id` ASC) VISIBLE,
  INDEX `fk_karta_sediste_redniBroj_idx` (`sediste_redniBroj` ASC) VISIBLE,
  INDEX `fk_karta_korisnik_korisnickoIme_idx` (`korisnickoIme` ASC) VISIBLE,
  CONSTRAINT `fk_karta_projekcija_id`
    FOREIGN KEY (`projekcija_id`)
    REFERENCES `bioskop`.`projekcija` (`id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
  CONSTRAINT `fk_karta_sediste_redniBroj`
    FOREIGN KEY (`sediste_redniBroj`)
    REFERENCES `bioskop`.`sediste` (`redniBroj`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
  CONSTRAINT `fk_karta_korisnik_korisnickoIme`
    FOREIGN KEY (`korisnickoIme`)
    REFERENCES `bioskop`.`korisnik` (`korisnickoIme`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE);


CREATE TABLE IF NOT EXISTS `bioskop`.`sala_tipProjekcije` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `sala_id` INT NOT NULL,
  `tipProjekcije_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_sala_tipProjekcije_sala1_idx` (`sala_id` ASC) VISIBLE,
  INDEX `fk_sala_tipProjekcije_tipProjekcije1_idx` (`tipProjekcije_id` ASC) VISIBLE,
  CONSTRAINT `fk_sala_tipProjekcije_sala_id`
    FOREIGN KEY (`sala_id`)
    REFERENCES `bioskop`.`sala` (`id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
  CONSTRAINT `fk_sala_tipProjekcije_tipProjekcije_id`
    FOREIGN KEY (`tipProjekcije_id`)
    REFERENCES `bioskop`.`tipProjekcije` (`id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE);


INSERT INTO `bioskop`.`tipprojekcije` (`naziv`) VALUES ('2D');
INSERT INTO `bioskop`.`tipprojekcije` (`naziv`) VALUES ('3D');
INSERT INTO `bioskop`.`tipprojekcije` (`naziv`) VALUES ('4D');


INSERT INTO `bioskop`.`sediste` (`redniBroj`) VALUES ('1');
INSERT INTO `bioskop`.`sediste` (`redniBroj`) VALUES ('2');
INSERT INTO `bioskop`.`sediste` (`redniBroj`) VALUES ('3');
INSERT INTO `bioskop`.`sediste` (`redniBroj`) VALUES ('4');
INSERT INTO `bioskop`.`sediste` (`redniBroj`) VALUES ('5');
INSERT INTO `bioskop`.`sediste` (`redniBroj`) VALUES ('6');
INSERT INTO `bioskop`.`sediste` (`redniBroj`) VALUES ('7');
INSERT INTO `bioskop`.`sediste` (`redniBroj`) VALUES ('8');
INSERT INTO `bioskop`.`sediste` (`redniBroj`) VALUES ('9');
INSERT INTO `bioskop`.`sediste` (`redniBroj`) VALUES ('10');


INSERT INTO `bioskop`.`sala` (`naziv`) VALUES ('Sala1');
INSERT INTO `bioskop`.`sala` (`naziv`) VALUES ('Sala2');
INSERT INTO `bioskop`.`sala` (`naziv`) VALUES ('Sala3');
INSERT INTO `bioskop`.`sala` (`naziv`) VALUES ('Sala4');
INSERT INTO `bioskop`.`sala` (`naziv`) VALUES ('Sala5');
INSERT INTO `bioskop`.`sala` (`naziv`) VALUES ('Sala6');
INSERT INTO `bioskop`.`sala` (`naziv`) VALUES ('Sala7');
INSERT INTO `bioskop`.`sala` (`naziv`) VALUES ('Sala8');
INSERT INTO `bioskop`.`sala` (`naziv`) VALUES ('Sala9');
INSERT INTO `bioskop`.`sala` (`naziv`) VALUES ('Sala10');


INSERT INTO `bioskop`.`sala_sediste` (`sala_id`, `sediste_redniBroj`) VALUES ('1', '1');
INSERT INTO `bioskop`.`sala_sediste` (`sala_id`, `sediste_redniBroj`) VALUES ('1', '2');
INSERT INTO `bioskop`.`sala_sediste` (`sala_id`, `sediste_redniBroj`) VALUES ('1', '3');
INSERT INTO `bioskop`.`sala_sediste` (`sala_id`, `sediste_redniBroj`) VALUES ('2', '1');
INSERT INTO `bioskop`.`sala_sediste` (`sala_id`, `sediste_redniBroj`) VALUES ('2', '2');
INSERT INTO `bioskop`.`sala_sediste` (`sala_id`, `sediste_redniBroj`) VALUES ('3', '1');
INSERT INTO `bioskop`.`sala_sediste` (`sala_id`, `sediste_redniBroj`) VALUES ('4', '1');
INSERT INTO `bioskop`.`sala_sediste` (`sala_id`, `sediste_redniBroj`) VALUES ('5', '2');
INSERT INTO `bioskop`.`sala_sediste` (`sala_id`, `sediste_redniBroj`) VALUES ('6', '1');
INSERT INTO `bioskop`.`sala_sediste` (`sala_id`, `sediste_redniBroj`) VALUES ('6', '3');
INSERT INTO `bioskop`.`sala_sediste` (`sala_id`, `sediste_redniBroj`) VALUES ('7', '1');
INSERT INTO `bioskop`.`sala_sediste` (`sala_id`, `sediste_redniBroj`) VALUES ('8', '3');
INSERT INTO `bioskop`.`sala_sediste` (`sala_id`, `sediste_redniBroj`) VALUES ('9', '1');
INSERT INTO `bioskop`.`sala_sediste` (`sala_id`, `sediste_redniBroj`) VALUES ('9', '2');
INSERT INTO `bioskop`.`sala_sediste` (`sala_id`, `sediste_redniBroj`) VALUES ('10', '3');
INSERT INTO `bioskop`.`sala_sediste` (`sala_id`, `sediste_redniBroj`) VALUES ('10', '2');

INSERT INTO `bioskop`.`korisnik` (`korisnickoIme`, `lozinka`, `datumRegistracije`, `uloga`) VALUES ('aca', '123', '2019-12-9', 'Admin');
INSERT INTO `bioskop`.`korisnik` (`korisnickoIme`, `lozinka`, `datumRegistracije`, `uloga`) VALUES ('pera', '123', '2019-8-12', 'Korisnik');

INSERT INTO `bioskop`.`projekcija` (`tipProjekcije_id`, `sala_id`, `vremePrikazivanja`, `cenaKarte`, `korisnickoIme`, `film_id`) VALUES ('1', '1', '2019-12-6 20:00', '250.00', 'aca', '1');
INSERT INTO `bioskop`.`projekcija` (`tipProjekcije_id`, `sala_id`, `vremePrikazivanja`, `cenaKarte`, `korisnickoIme`, `film_id`) VALUES ('2', '2', '2019-12-6 20:00', '350.50', 'pera', '2');
INSERT INTO `bioskop`.`projekcija` (`tipProjekcije_id`, `sala_id`, `vremePrikazivanja`, `cenaKarte`, `korisnickoIme`, `film_id`) VALUES ('3', '3', '2019-12-6 20:00', '550.20', 'aca', '3');


INSERT INTO `bioskop`.`film` (`naziv`, `reziser`, `glumci`, `zanrovi`, `distributer`, `godinaProizvodnje`, `opis`, `trajanje`, `zemljaPorekla`) VALUES ('The Shawshank Redemption', 'Frank Darabont', 'Tim Robbins, 	Morgan Freeman, Bob Gunton', 'Drama', ' Castle Rock Entertainment', '1994', 'Two imprisoned men bond over a number of years, finding solace and eventual redemption through acts of common decency.', '144', 'USA');
INSERT INTO `bioskop`.`film` (`naziv`, `reziser`, `glumci`, `zanrovi`, `distributer`, `godinaProizvodnje`, `opis`, `trajanje`, `zemljaPorekla`) VALUES ('The Dark Knight', 'Christopher Nolan', 'Christian Bale, Heath Ledger, Aaron Eckhart ', ' Action, Crime, Drama ', 'Warner Bros', '2008', 'When the menace known as the Joker wreaks havoc and chaos on the people of Gotham, Batman must accept one of the greatest psychological and physical tests of his ability to fight injustice.', '152', 'USA');
INSERT INTO `bioskop`.`film` (`naziv`, `reziser`, `glumci`, `zanrovi`, `distributer`, `godinaProizvodnje`, `opis`, `trajanje`, `zemljaPorekla`) VALUES ('The Intouchables', ' Olivier Nakache', 'Francois Cluzet, Omar Sy, Anne Le Ny ', 'Biography, Comedy, Drama ', 'Quad Productions', '2011', 'After he becomes a quadriplegic from a paragliding accident, an aristocrat hires a young man from the projects to be his caregiver.', '112', 'FRANCE');


INSERT INTO `bioskop`.`projekcija` (`tipProjekcije_id`, `sala_id`, `vremePrikazivanja`, `cenaKarte`, `korisnickoIme`, `film_id`) VALUES ('1', '1', '2019/20/12 20:52', '250.00', 'Aca', '1');

INSERT INTO `bioskop`.`sala_tipprojekcije` (`sala_id`, `tipProjekcije_id`) VALUES ('1', '1');
INSERT INTO `bioskop`.`sala_tipprojekcije` (`sala_id`, `tipProjekcije_id`) VALUES ('1', '2');
INSERT INTO `bioskop`.`sala_tipprojekcije` (`sala_id`, `tipProjekcije_id`) VALUES ('1', '3');
INSERT INTO `bioskop`.`sala_tipprojekcije` (`sala_id`, `tipProjekcije_id`) VALUES ('2', '1');
INSERT INTO `bioskop`.`sala_tipprojekcije` (`sala_id`, `tipProjekcije_id`) VALUES ('2', '1');
INSERT INTO `bioskop`.`sala_tipprojekcije` (`sala_id`, `tipProjekcije_id`) VALUES ('3', '1');
INSERT INTO `bioskop`.`sala_tipprojekcije` (`sala_id`, `tipProjekcije_id`) VALUES ('3', '3');
INSERT INTO `bioskop`.`sala_tipprojekcije` (`sala_id`, `tipProjekcije_id`) VALUES ('4', '1');
INSERT INTO `bioskop`.`sala_tipprojekcije` (`sala_id`, `tipProjekcije_id`) VALUES ('4', '2');
INSERT INTO `bioskop`.`sala_tipprojekcije` (`sala_id`, `tipProjekcije_id`) VALUES ('6', '1');
INSERT INTO `bioskop`.`sala_tipprojekcije` (`sala_id`, `tipProjekcije_id`) VALUES ('6', '2');
INSERT INTO `bioskop`.`sala_tipprojekcije` (`sala_id`, `tipProjekcije_id`) VALUES ('6', '3');
INSERT INTO `bioskop`.`sala_tipprojekcije` (`sala_id`, `tipProjekcije_id`) VALUES ('5', '1');
INSERT INTO `bioskop`.`sala_tipprojekcije` (`sala_id`, `tipProjekcije_id`) VALUES ('7', '1');
INSERT INTO `bioskop`.`sala_tipprojekcije` (`sala_id`, `tipProjekcije_id`) VALUES ('7', '2');
INSERT INTO `bioskop`.`sala_tipprojekcije` (`sala_id`, `tipProjekcije_id`) VALUES ('8', '3');
INSERT INTO `bioskop`.`sala_tipprojekcije` (`sala_id`, `tipProjekcije_id`) VALUES ('8', '3');
INSERT INTO `bioskop`.`sala_tipprojekcije` (`sala_id`, `tipProjekcije_id`) VALUES ('9', '1');
INSERT INTO `bioskop`.`sala_tipprojekcije` (`sala_id`, `tipProjekcije_id`) VALUES ('10', '3');

INSERT INTO `bioskop`.`sediste` (`redni_broj`) VALUES ('1');
INSERT INTO `bioskop`.`sediste` (`redni_broj`) VALUES ('2');
INSERT INTO `bioskop`.`sediste` (`redni_broj`) VALUES ('3');
INSERT INTO `bioskop`.`sediste` (`redni_broj`) VALUES ('4');
INSERT INTO `bioskop`.`sediste` (`redni_broj`) VALUES ('5');
INSERT INTO `bioskop`.`sediste` (`redni_broj`) VALUES ('6');
INSERT INTO `bioskop`.`sediste` (`redni_broj`) VALUES ('7');
INSERT INTO `bioskop`.`sediste` (`redni_broj`) VALUES ('8');
INSERT INTO `bioskop`.`sediste` (`redni_broj`) VALUES ('9');
INSERT INTO `bioskop`.`sediste` (`redni_broj`) VALUES ('10');



INSERT INTO `bioskop`.`karta` (`projekcija_id`, `sediste_redniBroj`, `vremeProdaje`, `korisnickoIme`) VALUES ('1', '1', '2019-12-5 19:00', 'aca');
INSERT INTO `bioskop`.`karta` (`projekcija_id`, `sediste_redniBroj`, `vremeProdaje`, `korisnickoIme`) VALUES ('3', '2', '2019-12-5 19:00', 'aca');
INSERT INTO `bioskop`.`karta` (`projekcija_id`, `sediste_redniBroj`, `vremeProdaje`, `korisnickoIme`) VALUES ('3', '1', '2019-12-5 19:00', 'aca');