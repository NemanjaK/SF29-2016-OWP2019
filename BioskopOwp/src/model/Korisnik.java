package model;

public class Korisnik {

	public enum Role {NEPRIJAVLJEN_KORISNIK, KORISNIK, ADMIN}

	private String korisnickoIme;
	private String password;
	private String datumRegistracije;
	private Role role;

	public Korisnik() {

	}

	public Korisnik(String korisnickoIme, String password, String datumRegistracije, Role role) {
		super();
		this.korisnickoIme = korisnickoIme;
		this.password = password;
		this.datumRegistracije = datumRegistracije;
		this.role = role;
	}

	public String getKorisnickoIme() {
		return korisnickoIme;
	}

	public void setKorisnickoIme(String korisnickoIme) {
		this.korisnickoIme = korisnickoIme;
	}

	public String getPassword() {
		return password;
	}

	public void setLozinka(String password) {
		this.password = password;
	}

	public String getDatumRegistracije() {
		return datumRegistracije;
	}

	public void setDatumRegistracije(String datumRegistracije) {
		this.datumRegistracije = datumRegistracije;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

}
