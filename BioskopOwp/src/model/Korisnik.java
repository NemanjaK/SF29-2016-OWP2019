package model;

import java.sql.Timestamp;

public class Korisnik {

	public enum Role {NEPRIJAVLJEN_KORISNIK, KORISNIK, ADMIN}

	private String korisnickoIme;
	private String lozinka;
	private Timestamp datumRegistracije;
	private Role role;
	
	public Korisnik(String korisnickoIme, String lozinka,  Role role) {
		super();
		this.korisnickoIme = korisnickoIme;
		this.lozinka = lozinka;
		this.role = role;
	}
	
	public Korisnik(String korisnickoIme, String lozinka, Timestamp datumRegistracije, Role role) {
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

	public Timestamp getDatumRegistracije() {
		return datumRegistracije;
	}

	public void setDatumRegistracije(Timestamp datumRegistracije) {
		this.datumRegistracije = datumRegistracije;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
	
}

