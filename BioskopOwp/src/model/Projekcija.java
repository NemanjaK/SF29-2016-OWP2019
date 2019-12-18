package model;

public class Projekcija {
	
	private int id;
	private Film prikazaniFilm;
	private TipProjekcije tipProjekcije;
	private Sala sala;
	private String datumVreme;
	private double cenaKarte;
	private Korisnik adminDodaoProjekciju;
	
	public Projekcija() {
		
	}
	public Projekcija(int id, Film prikazaniFilm, TipProjekcije tipProjekcije, Sala sala, String datumVreme,
			double cenaKarte, Korisnik adminDodaoProjekciju) {
		super();
		this.id = id;
		this.prikazaniFilm = prikazaniFilm;
		this.tipProjekcije = tipProjekcije;
		this.sala = sala;
		this.datumVreme = datumVreme;
		this.cenaKarte = cenaKarte;
		this.adminDodaoProjekciju = adminDodaoProjekciju;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Film getPrikazaniFilm() {
		return prikazaniFilm;
	}
	public void setPrikazaniFilm(Film prikazaniFilm) {
		this.prikazaniFilm = prikazaniFilm;
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
	public String getDatumVreme() {
		return datumVreme;
	}
	public void setDatumVreme(String datumVreme) {
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
	
	
	
	
	

}
