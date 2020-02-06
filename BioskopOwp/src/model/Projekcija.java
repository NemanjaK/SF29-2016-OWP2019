package model;


import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Projekcija {
	
	private int id;
	private TipProjekcije tipProjekcije;
	private Sala sala;
	private Timestamp datumVreme;
	private double cenaKarte;
	private Korisnik adminDodaoProjekciju;
	private Film prikazaniFilm;
	
	public Projekcija() {
		
	}

	
	public Projekcija(TipProjekcije tipProjekcije, Sala sala, Timestamp datumVreme, double cenaKarte,
			Film prikazaniFilm) {
		super();
		this.tipProjekcije = tipProjekcije;
		this.sala = sala;
		this.datumVreme = datumVreme;
		this.cenaKarte = cenaKarte;
		this.prikazaniFilm = prikazaniFilm;
	}



	public Projekcija(TipProjekcije tipProjekcije, Sala sala, Timestamp datumVreme, double cenaKarte,
			Korisnik adminDodaoProjekciju, Film prikazaniFilm) {
		super();
		this.tipProjekcije = tipProjekcije;
		this.sala = sala;
		this.datumVreme = datumVreme;
		this.cenaKarte = cenaKarte;
		this.adminDodaoProjekciju = adminDodaoProjekciju;
		this.prikazaniFilm = prikazaniFilm;
	}



	public Projekcija(int id, TipProjekcije tipProjekcije, Sala sala, Timestamp datumVreme, double cenaKarte,
			Korisnik adminDodaoProjekciju, Film prikazaniFilm) {
		super();
		this.id = id;
		this.tipProjekcije = tipProjekcije;
		this.sala = sala;
		this.datumVreme = datumVreme;
		this.cenaKarte = cenaKarte;
		this.adminDodaoProjekciju = adminDodaoProjekciju;
		this.prikazaniFilm = prikazaniFilm;
	}

	public Projekcija(int id, TipProjekcije tipProjekcije, Sala sala, Timestamp datumVreme, Film prikazaniFilm) {
		super();
		this.id = id;
		this.tipProjekcije = tipProjekcije;
		this.sala = sala;
		this.datumVreme = datumVreme;
		this.prikazaniFilm = prikazaniFilm;
	}


	public Projekcija(int id, double cenaKarte, Film prikazaniFilm) {
		super();
		this.id = id;
		this.cenaKarte = cenaKarte;
		this.prikazaniFilm = prikazaniFilm;
	}
	
	public Projekcija(int id, double cenaKarte) {
		super();
		this.id = id;
		this.cenaKarte = cenaKarte;
	}

	public Projekcija(int id) {
		super();
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public TipProjekcije getTipProjekcije() {
		return tipProjekcije;
	}

	public void setTipProjekcije(TipProjekcije tipProjekcije) {
		this.tipProjekcije = tipProjekcije;
	}

	public Sala getSala() {
		return sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}

	public Timestamp getDatumVreme() {
		return datumVreme;	
	}

	public void setDatumVreme(Timestamp datumVreme) {
		this.datumVreme = datumVreme;
	}

	public double getCenaKarte() {
		return cenaKarte;
	}

	public void setCenaKarte(double cenaKarte) {
		this.cenaKarte = cenaKarte;
	}

	public Korisnik getAdminDodaoProjekciju() {
		return adminDodaoProjekciju;
	}

	public void setAdminDodaoProjekciju(Korisnik adminDodaoProjekciju) {
		this.adminDodaoProjekciju = adminDodaoProjekciju;
	}

	public Film getPrikazaniFilm() {
		return prikazaniFilm;
	}

	public void setPrikazaniFilm(Film prikazaniFilm) {
		this.prikazaniFilm = prikazaniFilm;
	}



	@Override
	public String toString() {
		return "Projekcija [id=" + id + ", tipProjekcije=" + tipProjekcije + ", sala=" + sala + ", datumVreme="
				+ datumVreme + ", cenaKarte=" + cenaKarte + ", adminDodaoProjekciju=" + adminDodaoProjekciju
				+ ", prikazaniFilm=" + prikazaniFilm + "]";
	}
	
	
	
	
	
	

}
