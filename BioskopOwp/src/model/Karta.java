package model;

public class Karta {

	private int id;
	private Projekcija projekcija;
	private Sediste sediste;
	private String datumProdaje;
	private Korisnik korisnikKupioKartu;

	public Karta() {
	}

	public Karta(int id, Projekcija projekcija, Sediste sediste, String datumProdaje, Korisnik korisnikKupioKartu) {
		super();
		this.id = id;
		this.projekcija = projekcija;
		this.sediste = sediste;
		this.datumProdaje = datumProdaje;
		this.korisnikKupioKartu = korisnikKupioKartu;
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

	public String getDatumProdaje() {
		return datumProdaje;
	}

	public void setDatumProdaje(String datumProdaje) {
		this.datumProdaje = datumProdaje;
	}

	public Korisnik getKorisnikKupioKartu() {
		return korisnikKupioKartu;
	}

	public void setKorisnikKupioKartu(Korisnik korisnikKupioKartu) {
		this.korisnikKupioKartu = korisnikKupioKartu;
	}

}
