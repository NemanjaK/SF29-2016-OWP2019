package model;

import java.util.ArrayList;
import java.util.List;

public class Sala {
	
	
	private int id;
	private String naziv;
	private List<TipProjekcije> tipoviProjekcije = new ArrayList<>();
	
	
	
	public Sala(int id, String naziv, ArrayList<TipProjekcije> tipoviProjekcije) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.tipoviProjekcije = tipoviProjekcije;
	}

	public Sala() {
		
	}	
		
	public List<TipProjekcije> getTipoviProjekcije() {
		return tipoviProjekcije;
	}

	public void setTipoviProjekcije(ArrayList<TipProjekcije> tipoviProjekcije) {
		this.tipoviProjekcije = tipoviProjekcije;
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
