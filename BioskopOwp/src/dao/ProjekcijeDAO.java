package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Film;
import model.Korisnik;
import model.Projekcija;
import model.Sala;
import model.TipProjekcije;

public class ProjekcijeDAO {
	public static SimpleDateFormat DATETIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm");

	public static List<Projekcija> getAll(double minCenaKarte, double maxCenaKarte, 
			String nazivFilm, String tipProjekcije, String sala, String sort) throws ParseException {

		Connection conn = ConnectionManager.getConnection();

		PreparedStatement pstmt = null;
		ResultSet rset = null;

		ArrayList<Projekcija> projekcije = new ArrayList<Projekcija>();

//		LocalDateTime now = LocalDateTime.now();
//		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//		String formatDateTime = now.format(formatter);
//		System.out.println(formatDateTime);
//		
		try {
			String query = "select projekcija.id, projekcija.cenaKarte, projekcija.vremePrikazivanja,projekcija.obrisan, film.id, film.naziv,tipProjekcije.id, tipProjekcije.naziv, sala.id, sala.naziv, korisnik.korisnickoIme\n" + 
					"from projekcija\n" + 
					"join film on film.id = projekcija.film_id\n" + 
					"join tipProjekcije on tipProjekcije.id = projekcija.tipProjekcije_id\n" + 
					"join sala on sala.id = projekcija.sala_id\n" + 
					"join korisnik on korisnik.korisnickoIme = projekcija.korisnickoIme\n" + 
					"WHERE projekcija.cenaKarte >= ? AND projekcija.cenaKarte <= ? AND film.naziv LIKE ?\n" + 
					"AND tipProjekcije.naziv LIKE ? AND sala.naziv LIKE ? AND projekcija.obrisan = 0" + 
					" "+ sort +"";

			pstmt = conn.prepareStatement(query);
			int index = 1;
			pstmt.setDouble(index++, minCenaKarte);
			pstmt.setDouble(index++, maxCenaKarte);
			pstmt.setString(index++, "%" + nazivFilm + "%");
			pstmt.setString(index++, "%" + tipProjekcije + "%");
			pstmt.setString(index++, "%" + sala + "%");
	

		
			System.out.println(pstmt);

			rset = pstmt.executeQuery();
			//SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			while (rset.next()) {
				index = 1;
				Integer id = rset.getInt(index++);
				
				Double cenaKarte = rset.getDouble(index++);
				
				java.sql.Date projekcijaDatumSql = rset.getDate(index++);
				Timestamp projekcijaDatum = new Timestamp(projekcijaDatumSql.getTime());
				
				Integer obrisan = rset.getInt(index++);

				Integer idFilm = rset.getInt(index++);
				String nazivFilmA = rset.getString(index++);
				Film film = new Film(idFilm, nazivFilmA);
							
				Integer idTipProjekcije = rset.getInt(index++);
				String nazivProjekcije = rset.getString(index++);				
				TipProjekcije tipProjekcijeP = new TipProjekcije(idTipProjekcije, nazivProjekcije);
				
				Integer idSala = rset.getInt(index++);
				String nazivSale = rset.getString(index++);
				Sala salaP = new Sala(idSala, nazivSale);
								
				String korisnickoIme = rset.getString(index++);
				Korisnik korisnik = new Korisnik(korisnickoIme);
						
				Projekcija projekcija = new Projekcija(id, tipProjekcijeP, salaP, projekcijaDatum,cenaKarte, korisnik, film,obrisan);
				projekcije.add(projekcija);
				
			}
			System.out.println(projekcije);
			return projekcije; 	
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
	
	public static boolean delete(int id) {
		Connection conn = ConnectionManager.getConnection();

		PreparedStatement pstmt = null;
		try {
			String query = "UPDATE projekcija set obrisan=1 WHERE id = ?";

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

	public static Projekcija getOne(int id) throws ParseException {
		Connection conn = ConnectionManager.getConnection();

		PreparedStatement pstmt = null;
		ResultSet rset = null;

		try {
			String query = "select projekcija.id, projekcija.cenaKarte, projekcija.vremePrikazivanja,projekcija.obrisan, film.id, film.naziv,tipProjekcije.id, tipProjekcije.naziv, sala.id, sala.naziv, korisnik.korisnickoIme\n" + 
					"from projekcija \n" + 
					"join film on film.id = projekcija.film_id\n" + 
					"join tipProjekcije on tipProjekcije.id = projekcija.tipProjekcije_id\n" + 
					"join sala on sala.id = projekcija.sala_id\n" + 
					"join korisnik on korisnik.korisnickoIme = projekcija.korisnickoIme where projekcija.id = ?";

			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, id);
			System.out.println(pstmt);

			rset = pstmt.executeQuery();
			
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");

			if (rset.next()) {
				int index = 2;
				Double cenaKarte = rset.getDouble(index++);
				
				java.sql.Date projekcijaDatumSql = rset.getDate(index++);
				Timestamp projekcijaDatum = new Timestamp(projekcijaDatumSql.getTime());
		
				Integer obrisan = rset.getInt(index++);

				Integer idFilm = rset.getInt(index++);
				String nazivFilm = rset.getString(index++);
				Film film = new Film(idFilm, nazivFilm);
							
				Integer idTipProjekcije = rset.getInt(index++);
				String nazivProjekcije = rset.getString(index++);				
				TipProjekcije tipProjekcije = new TipProjekcije(idTipProjekcije, nazivProjekcije);
				
				Integer idSala = rset.getInt(index++);
				String nazivSale = rset.getString(index++);
				Sala sala = new Sala(idSala, nazivSale);
								
				String korisnickoIme = rset.getString(index++);
				Korisnik korisnik = new Korisnik(korisnickoIme);
						
				Projekcija projekcija = new Projekcija(id, tipProjekcije, sala, projekcijaDatum, cenaKarte, korisnik, film, obrisan);
				return projekcija;
			}
		} catch (SQLException ex) {
			System.out.println("Greska u SQL upitu A!");
			ex.printStackTrace();

		} finally {
			try {pstmt.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {rset.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {conn.close();} catch (Exception ex1) {ex1.printStackTrace();}
		}
		return null;
	}
	
	public static boolean add(Projekcija projekcija) throws Exception {

		Connection conn = ConnectionManager.getConnection();

		PreparedStatement pstmt = null;
		try {
			String query = "INSERT INTO projekcija (tipProjekcije_id, sala_id, vremePrikazivanja, cenaKarte, korisnickoIme, film_id)"
					+ "VALUES (?, ?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(query);


			int index = 1;
			pstmt.setInt(index++, projekcija.getTipProjekcije().getId());
			pstmt.setInt(index++, projekcija.getSala().getId());
			pstmt.setTimestamp(index++, (java.sql.Timestamp) projekcija.getDatumVreme());
			pstmt.setDouble(index++, projekcija.getCenaKarte());
			pstmt.setString(index++, projekcija.getAdminDodaoProjekciju().getKorisnickoIme());
			pstmt.setInt(index++, projekcija.getPrikazaniFilm().getId());
			
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
	
	public static int getProjekcijaId() {
		Connection conn = ConnectionManager.getConnection();
		int id = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			String query = "SELECT MAX(id) FROM projekcija";
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();

			if (rset.next()) {
				id = rset.getInt(1);

			}
			id++;
			return id;
		} catch (Exception ex) {
			System.out.println("Greska u SQL upitu!");
			ex.printStackTrace();
		}
		return 0;
	}

}
