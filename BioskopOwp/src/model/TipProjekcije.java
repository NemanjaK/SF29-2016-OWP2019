package model;

public class TipProjekcije {
	
	private int id;
	private String naziv;
	
	public TipProjekcije() {

	}
	public TipProjekcije(int id) {
		super();
		this.id = id;
	}
	public TipProjekcije(int id, String naziv) {
		super();
		this.id = id;
		this.naziv = naziv;
	}
	
	public TipProjekcije(String naziv) {
		super();
		this.naziv = naziv;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	
	

}
