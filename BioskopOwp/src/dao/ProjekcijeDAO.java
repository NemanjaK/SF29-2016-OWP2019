package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Film;
import model.Korisnik;
import model.Projekcija;
import model.Sala;
import model.TipProjekcije;

public class ProjekcijeDAO {
	
	public static List<Projekcija> getAll() throws ParseException {

		Connection conn = ConnectionManager.getConnection();

		PreparedStatement pstmt = null;
		ResultSet rset = null;

		ArrayList<Projekcija> projekcije = new ArrayList<Projekcija>();


		try {
			String query = "select projekcija.id, projekcija.cenaKarte, projekcija.vremePrikazivanja, film.id, film.naziv,tipProjekcije.id, tipProjekcije.naziv, sala.id, sala.naziv, korisnik.korisnickoIme\n" + 
					"from projekcija\n" + 
					"join film on film.id = projekcija.film_id\n" + 
					"join tipProjekcije on tipProjekcije.id = projekcija.tipProjekcije_id\n" + 
					"join sala on sala.id = projekcija.sala_id\n" + 
					"join korisnik on korisnik.korisnickoIme = projekcija.korisnickoIme;";

			pstmt = conn.prepareStatement(query);
			int index = 1;
			//pstmt.setDouble(index++, minCenaKarte);
			//pstmt.setDouble(index++, maxCenaKarte);			
				
			System.out.println(pstmt);

			rset = pstmt.executeQuery();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			while (rset.next()) {
				index = 1;
				Integer id = rset.getInt(index++);
				Double cenaKarte = rset.getDouble(index++);
				Date date = (Date) formatter.parse(rset.getString(index++));
							
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
						
				Projekcija projekcija = new Projekcija(id, tipProjekcije, sala, date, cenaKarte, korisnik, film);
				projekcije.add(projekcija);
				
			}
			System.out.println(projekcije);
			return projekcije; 	
		} catch (SQLException ex) {
			System.out.println("Greska u SQL upitu");
			ex.printStackTrace();

		}
		return null;
	}
// BEZ JOINA
//	public static List<Projekcija> getAll(double minCenaKarte, double maxCenaKarte) throws ParseException {
//
//		Connection conn = ConnectionManager.getConnection();
//
//		PreparedStatement pstmt = null;
//		ResultSet rset = null;
//
//		ArrayList<Projekcija> projekcije = new ArrayList<Projekcija>();
//
//		try {
//			String query = "SELECT * FROM projekcija WHERE  cenaKarte >= ? AND cenaKarte <= ?";
//
//			pstmt = conn.prepareStatement(query);
//			int index = 1;
//			pstmt.setDouble(index++, minCenaKarte);
//			pstmt.setDouble(index++, maxCenaKarte);			
//				
//			System.out.println(pstmt);
//
//			rset = pstmt.executeQuery();
//			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
//			while (rset.next()) {
//				index = 1;
//				Integer id = rset.getInt(index++);
//				Integer tipProjekcije = rset.getInt(index++);
//				TipProjekcije tp = tipProjekcijeDAO.getOne(tipProjekcije);
//				Integer sala = rset.getInt(index++);
//				Sala sl = SalaDAO.getOne(sala);
//				System.out.print("=================ss=================");
//				Date date = (Date) formatter.parse(rset.getString(index++));
//
//				System.out.print("==================================FORMATED");
//
//				Double cenaKarte = rset.getDouble(index++);
//				String adminDdodaoProjekcijus = rset.getString(index++);
//				Korisnik ap = KorisnikDAO.getOne(adminDdodaoProjekcijus);
//				Integer prikazaniFilm = rset.getInt(index++);
//				Film pf = FilmoviDAO.getOne(prikazaniFilm);
//
//				Projekcija projekcija = new Projekcija(id, tp, sl, date, cenaKarte, ap, pf);
//				projekcije.add(projekcija);
//				
//				System.out.print(projekcije);
//				//OVO PRODJE
//
//			}
//			System.out.println(projekcije);
//			return projekcije; 	
//		} catch (SQLException ex) {
//			System.out.println("Greska u SQL upitu");
//			ex.printStackTrace();
//
//		}
//		return null;
//	}

	public static Projekcija getOne(int id) throws ParseException {
		Connection conn = ConnectionManager.getConnection();

		PreparedStatement pstmt = null;
		ResultSet rset = null;

		try {
			String query = "SELECT * FROM projekcija WHERE id = ?";

			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, id);
			System.out.println(pstmt);

			rset = pstmt.executeQuery();
			
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");

			if (rset.next()) {
				int index = 2;
				Integer tipProjekcije = rset.getInt(index++);
				TipProjekcije tp = tipProjekcijeDAO.getOne(tipProjekcije);
				Integer sala = rset.getInt(index++);
				Sala sl = SalaDAO.getOne(sala);
				Date date = (Date) formatter.parse(rset.getString(index++));

				Double cenaKarte = rset.getDouble(index++);
				String adminDdodaoProjekciju = rset.getString(index++);
				Korisnik ap = KorisnikDAO.getOne(adminDdodaoProjekciju);
				Integer prikazaniFilm = rset.getInt(index++);
				Film pf = FilmoviDAO.getOne(prikazaniFilm);

				return new Projekcija(id, tp, sl, date, cenaKarte, ap, pf);

			}
		} catch (SQLException ex) {
			System.out.println("Greska u SQL upitu A!");
			ex.printStackTrace();

		}
		return null;
	}

}
