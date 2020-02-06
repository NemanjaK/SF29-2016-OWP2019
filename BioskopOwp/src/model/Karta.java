package model;

import java.sql.Timestamp;

public class Karta {

	private int id;
	private Projekcija projekcija;
	private Sediste sediste;
	private Timestamp datumProdaje;
	private Korisnik korisnikKupioKartu;

	public Karta() {
	}

	public Karta(int id, Projekcija projekcija, Sediste sediste, Timestamp datumProdaje, Korisnik korisnikKupioKartu) {
		super();
		this.id = id;
		this.projekcija = projekcija;
		this.sediste = sediste;
		this.datumProdaje = datumProdaje;
		this.korisnikKupioKartu = korisnikKupioKartu;
	}
	
	public Karta(Projekcija projekcija, Sediste sediste, Timestamp datumProdaje, Korisnik korisnikKupioKartu) {
		super();
		this.projekcija = projekcija;
		this.sediste = sediste;
		this.datumProdaje = datumProdaje;
		this.korisnikKupioKartu = korisnikKupioKartu;
	}
	
	public Karta(Projekcija projekcija, Sediste sediste,Korisnik korisnikKupioKartu) {
		super();
		this.projekcija = projekcija;
		this.sediste = sediste;
		this.korisnikKupioKartu = korisnikKupioKartu;
	}
		
	public Karta(int id, Projekcija projekcija) {
		super();
		this.id = id;
		this.projekcija = projekcija;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Projekcija getProjekcija() {
		return projekcija;
	}

	public void setProjekcija(Projekcija projekcija) {
		this.projekcija = projekcija;
	}

	public Sediste getSediste() {
		return sediste;
	}

	public void setSediste(Sediste sediste) {
		this.sediste = sediste;
	}

	public Timestamp getDatumProdaje() {
		return datumProdaje;
	}

	public void setDatumProdaje(Timestamp datumProdaje) {
		this.datumProdaje = datumProdaje;
	}

	public Korisnik getKorisnikKupioKartu() {
		return korisnikKupioKartu;
	}

	public void setKorisnikKupioKartu(Korisnik korisnikKupioKartu) {
		this.korisnikKupioKartu = korisnikKupioKartu;
	}

}
