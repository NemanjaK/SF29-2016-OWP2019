package model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Korisnik {

	public enum Role {NEPRIJAVLJEN_KORISNIK, KORISNIK, ADMIN}

	private String korisnickoIme;
	private String lozinka;
	private Date datumRegistracije;
	private Role role;
	
	public Korisnik(String korisnickoIme, String lozinka,  Role role) {
		super();
		this.korisnickoIme = korisnickoIme;
		this.lozinka = lozinka;
		this.role = role;
	}
	
	
	
	public Korisnik(String korisnickoIme) {
		super();
		this.korisnickoIme = korisnickoIme;
	}



	public Korisnik(String korisnickoIme, String lozinka, Date datumRegistracije, Role role) {
		super();
		this.korisnickoIme = korisnickoIme;
		this.lozinka = lozinka;
		this.datumRegistracije = datumRegistracije;
		this.role = role;
	}

	public Korisnik() {
	}

	public String getKorisnickoIme() {
		return korisnickoIme;
	}

	public void setKorisnickoIme(String korisnickoIme) {
		this.korisnickoIme = korisnickoIme;
	}

	public String getLozinka() {
		return lozinka;
	}

	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
	}	

	public String getDatumRegistracije() {
		String pattern = "MM/dd/yyyy";
		DateFormat df = new SimpleDateFormat(pattern);

		return df.format(datumRegistracije);
	}
	

	public void setDatumRegistracije(Date datumRegistracije) {
		this.datumRegistracije = datumRegistracije;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
	
}

