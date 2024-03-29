package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import model.Film;
import model.Karta;
import model.Korisnik;
import model.Projekcija;
import model.Sala;
import model.Sediste;
import model.TipProjekcije;

public class KartaDAO {
	
	public static SimpleDateFormat DATETIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public static List<Karta> getKarteByProj(int id){
		
		
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		ArrayList<Karta> karte = new ArrayList<Karta>();
		
		try {
			String query = "select karta.id, karta.sediste_redniBroj, karta.vremeProdaje, karta.korisnickoIme, projekcija.id\r\n" + 
					"from karta\r\n" + 
					"join projekcija on projekcija.id = karta.projekcija_id\r\n" + 
					"where projekcija.id = ?";
			
			pstmt = conn.prepareStatement(query);
			int index = 1;
			pstmt.setInt(1, id);
			rset = pstmt.executeQuery();

			while(rset.next()) {
				index = 1;
				int idK = rset.getInt(index++);
				
				int sediste = rset.getInt(index++);
				Sediste sedisteK = new Sediste(sediste);
				
				java.sql.Date kartaDatumSql = rset.getDate(index++);
				Timestamp kartaDatum = new Timestamp(kartaDatumSql.getTime());
				
				String korisnickoIme = rset.getString(index++);
				Korisnik korisnik = new Korisnik(korisnickoIme);
				
				Integer idProjekcije = rset.getInt(index++);
				Projekcija projekcijaK = new Projekcija(idProjekcije);
				
				Karta karta =  new Karta(idK, projekcijaK, sedisteK, kartaDatum, korisnik);
				karte.add(karta);
			}
			return karte;
		} catch (SQLException ex) {
			System.out.println("Greska u SQL upitu");
			ex.printStackTrace();
			}finally {
				try {pstmt.close();} catch (SQLException ex1) {ex1.printStackTrace();}
				try {rset.close();} catch (Exception ex1) {ex1.printStackTrace();}
				try {conn.close();} catch (Exception ex1) {ex1.printStackTrace();}
			}
			return null;
}
	public static boolean add(Karta karta) throws Exception {

		Connection conn = ConnectionManager.getConnection();

		PreparedStatement pstmt = null;
		try {
			String query = "INSERT INTO karta(projekcija_id, sediste_redniBroj, vremeProdaje, korisnickoIme)" + 
					"VALUES (?, ?, ?, ?)";
			pstmt = conn.prepareStatement(query);


			int index = 1;
			pstmt.setInt(index++, karta.getProjekcija().getId());
			pstmt.setInt(index++, karta.getSediste().getRedniBroj());
			pstmt.setTimestamp(index++, (java.sql.Timestamp) karta.getDatumProdaje());
			pstmt.setString(index++, karta.getKorisnikKupioKartu().getKorisnickoIme());
			
			System.out.println(pstmt);

			return pstmt.executeUpdate() == 1;

		} catch (Exception ex) {
			System.out.println("Greska SQL add");
			ex.printStackTrace();
		}finally {
			try {pstmt.close();} catch (SQLException ex1) {ex1.printStackTrace();}
			try {conn.close();} catch (Exception ex1) {ex1.printStackTrace();}
		}		
		return false;
	}
	
	public static Karta getOne(int id) {

		Connection conn = ConnectionManager.getConnection();

		PreparedStatement pstmt = null;
		ResultSet rset = null;

		try {
			String query = "select film.id, film.naziv, projekcija.id, projekcija.vremePrikazivanja, tipProjekcije.naziv, sala.naziv, karta.id, karta.sediste_redniBroj, karta.korisnickoIme\r\n" + 
					"from karta\r\n" + 
					"left join projekcija on projekcija.id = karta.projekcija_id\r\n" + 
					"left join film on film.id = projekcija.film_id\r\n" + 
					"left join tipProjekcije on tipProjekcije.id = projekcija.tipProjekcije_id\r\n" + 
					"left join sala on sala.id = projekcija.sala_id where karta.id = ?";

			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, id);
			rset = pstmt.executeQuery();
			
			System.out.println(pstmt);
			if (rset.next()) {
				int index = 1;

				int idF = rset.getInt(index++);
				String nazivF = rset.getString(index++);
				
				int idP = rset.getInt(index++);
				java.sql.Date vremePrikazivanjaSql = rset.getDate(index++);
				Timestamp vremePrikazivanja = new Timestamp(vremePrikazivanjaSql.getTime());			
				String tipProjekcijeNaziv = rset.getString(index++);
				String salaNaziv = rset.getString(index++);		
				
				int idKarta = rset.getInt(index++);
				
				int sediste = rset.getInt(index++);
				
				String korisnik = rset.getString(index++);
				
				Film film = new Film(idF, nazivF);
				Sala sl = new Sala(salaNaziv);
				TipProjekcije tp = new TipProjekcije(tipProjekcijeNaziv);
				Sediste sed = new Sediste(sediste);
				Korisnik kor = new Korisnik(korisnik);
				
				Projekcija projekcija = new Projekcija(idP,tp,sl,vremePrikazivanja,film);
						
				return new Karta(idKarta, projekcija, sed, kor);

			}
		} catch (SQLException ex) {
			System.out.println("Greska u SQL upitu!");
			ex.printStackTrace();

		} finally {
			try {pstmt.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {rset.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {conn.close();} catch (Exception ex1) {ex1.printStackTrace();}
		}
		return null;

	}
	public static List<Karta> getAll(String sort) {
		List<Karta> karte = new ArrayList<>();
		Connection conn = ConnectionManager.getConnection();

		PreparedStatement pstmt = null;
		ResultSet rset = null;

		try {
			String query = "select film.id, film.naziv, projekcija.id, projekcija.vremePrikazivanja, tipProjekcije.naziv, sala.naziv, karta.id, karta.sediste_redniBroj, karta.korisnickoIme\r\n" + 
					"from karta\r\n" + 
					"left join projekcija on projekcija.id = karta.projekcija_id\r\n" + 
					"left join film on film.id = projekcija.film_id\r\n" + 
					"left join tipProjekcije on tipProjekcije.id = projekcija.tipProjekcije_id\r\n" + 
					"left join sala on sala.id = projekcija.sala_id " + sort + "";

			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			
			System.out.println(pstmt);
			while (rset.next()) {
				int index = 1;

				int idF = rset.getInt(index++);
				String nazivF = rset.getString(index++);
				
				int idP = rset.getInt(index++);
				java.sql.Date vremePrikazivanjaSql = rset.getDate(index++);
				Timestamp vremePrikazivanja = new Timestamp(vremePrikazivanjaSql.getTime());			
				String tipProjekcijeNaziv = rset.getString(index++);
				String salaNaziv = rset.getString(index++);		
				
				int kartaId = rset.getInt(index++);
				int sediste = rset.getInt(index++);
				
				String korisnik = rset.getString(index++);
				
				Film film = new Film(idF, nazivF);
				Sala sl = new Sala(salaNaziv);
				TipProjekcije tp = new TipProjekcije(tipProjekcijeNaziv);
				Sediste sed = new Sediste(sediste);
				Korisnik kor = new Korisnik(korisnik);
				
				Projekcija projekcija = new Projekcija(idP,tp,sl,vremePrikazivanja,film);
						
				Karta karta = new Karta(kartaId, projekcija, sed, kor);
				karte.add(karta);

			}
			return karte;
		} catch (SQLException ex) {
			System.out.println("Greska u SQL upitu!");
			ex.printStackTrace();

		} finally {
			try {pstmt.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {rset.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {conn.close();} catch (Exception ex1) {ex1.printStackTrace();}
		}
		return null;

	}
	
	public static List<Karta> getIzvesajKarti(){
		List<Karta> karte = new ArrayList<>();
		
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		try {
			String query = "select film.naziv, count(distinct projekcija.id), SUM(cenaKarte), COUNT(karta.id)\r\n" + 
					"from projekcija\r\n" + 
					"left join karta on projekcija.id = karta.projekcija_id\r\n" + 
					"left join film on film.id = projekcija.film_id\r\n" + 
					"Group by film.naziv";
			
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				int index = 1;
				String nazivFilma = rset.getString(index++);
				Film film = new Film(nazivFilma);
				
				int brojProjekcija = rset.getInt(index++);
				int ukupnaCena = rset.getInt(index++);

				int brojKarti = rset.getInt(index++);
				
				Projekcija projekcija = new Projekcija(brojProjekcija,ukupnaCena, film);

				Karta karta = new Karta(brojKarti, projekcija);
				karte.add(karta);

			}
			return karte;
		}catch (SQLException ex) {
			System.out.println("Greska u SQL upitu");
			ex.printStackTrace();
			}finally {
				try {pstmt.close();} catch (SQLException ex1) {ex1.printStackTrace();}
				try {rset.close();} catch (Exception ex1) {ex1.printStackTrace();}
				try {conn.close();} catch (Exception ex1) {ex1.printStackTrace();}
			}
			return null;
		
	}
	public static Karta getCeoIzvestaj(){
		
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		try {
			String query = "select count(distinct projekcija.id), SUM(cenaKarte), COUNT(karta.id)\r\n" + 
					"from projekcija\r\n" + 
					"left join karta on projekcija.id = karta.projekcija_id";
			
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				int index = 1;

				int brojProjekcija = rset.getInt(index++);
				int ukupnaCena = rset.getInt(index++);

				int brojKarti = rset.getInt(index++);
				
				Projekcija projekcija = new Projekcija(brojProjekcija,ukupnaCena);

				Karta karta = new Karta(brojKarti, projekcija);
				return karta;

			}
		}catch (SQLException ex) {
			System.out.println("Greska u SQL upitu");
			ex.printStackTrace();
			}finally {
				try {pstmt.close();} catch (SQLException ex1) {ex1.printStackTrace();}
				try {rset.close();} catch (Exception ex1) {ex1.printStackTrace();}
				try {conn.close();} catch (Exception ex1) {ex1.printStackTrace();}
			}
			return null;
		
	}
	
	public static boolean delete(int id) {
		Connection conn = ConnectionManager.getConnection();

		PreparedStatement pstmt = null;
		try {
			String query = "DELETE FROM karta WHERE id = ?";

			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, id);
			System.out.println(pstmt);

			return pstmt.executeUpdate() == 1;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {pstmt.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {conn.close();} catch (Exception ex1) {ex1.printStackTrace();}
		}
		return false;
	}
	
	public static List<Karta> getKarteKorisnik(String korisnickoIme) {
		List<Karta> karte = new ArrayList<>();
		Connection conn = ConnectionManager.getConnection();

		PreparedStatement pstmt = null;
		ResultSet rset = null;

		try {
			String query = "select film.id, film.naziv, projekcija.id, projekcija.vremePrikazivanja, tipProjekcije.naziv, sala.naziv, karta.id, karta.sediste_redniBroj, karta.korisnickoIme\r\n" + 
					"from karta\r\n" + 
					"left join projekcija on projekcija.id = karta.projekcija_id\r\n" + 
					"left join film on film.id = projekcija.film_id\r\n" + 
					"left join tipProjekcije on tipProjekcije.id = projekcija.tipProjekcije_id\r\n" + 
					"left join sala on sala.id = projekcija.sala_id WHERE karta.korisnickoIme = ?";

			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, korisnickoIme);
			
			rset = pstmt.executeQuery();

			System.out.println(pstmt);
			while (rset.next()) {
				int index = 1;

				int idF = rset.getInt(index++);
				String nazivF = rset.getString(index++);
				
				int idP = rset.getInt(index++);
				java.sql.Date vremePrikazivanjaSql = rset.getDate(index++);
				Timestamp vremePrikazivanja = new Timestamp(vremePrikazivanjaSql.getTime());			
				String tipProjekcijeNaziv = rset.getString(index++);
				String salaNaziv = rset.getString(index++);		
				
				int kartaId = rset.getInt(index++);
				int sediste = rset.getInt(index++);
				
				String korisnik = rset.getString(index++);
				
				Film film = new Film(idF, nazivF);
				Sala sl = new Sala(salaNaziv);
				TipProjekcije tp = new TipProjekcije(tipProjekcijeNaziv);
				Sediste sed = new Sediste(sediste);
				Korisnik kor = new Korisnik(korisnik);
				
				Projekcija projekcija = new Projekcija(idP,tp,sl,vremePrikazivanja,film);
						
				Karta karta = new Karta(kartaId, projekcija, sed, kor);
				karte.add(karta);

			}
			return karte;
		} catch (SQLException ex) {
			System.out.println("Greska u SQL upitu!");
			ex.printStackTrace();

		} finally {
			try {pstmt.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {rset.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {conn.close();} catch (Exception ex1) {ex1.printStackTrace();}
		}
		return null;

	}
}
