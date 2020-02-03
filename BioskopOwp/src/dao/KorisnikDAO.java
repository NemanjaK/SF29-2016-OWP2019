package dao;

import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import model.Film;
import model.Korisnik;
import model.Korisnik.Role;

public class KorisnikDAO {
	
	public static SimpleDateFormat DATETIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

	public static Korisnik get(String korisnickoIme, String lozinka) {
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		try {
			String query = "SELECT uloga FROM korisnik WHERE korisnickoIme = ? and lozinka = ?";
					
			pstmt = conn.prepareStatement(query);
			int index = 1;
			pstmt.setString(index++, korisnickoIme);
			pstmt.setString(index++, lozinka);
			System.out.println(pstmt);

			rset = pstmt.executeQuery();
			
			if (rset.next()) {
				Role role = Role.valueOf(rset.getString(1));

				return new Korisnik(korisnickoIme, lozinka, role);

			}
		}catch (SQLException ex) {
			System.out.println("Greska u SQL upitu!");
			ex.printStackTrace();

		}
		return null;
	}
	public static Korisnik getOneProj(String korisnickoIme) throws ParseException {

		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		try {
			String query = "SELECT korisnickoIme FROM korisnik where korisnickoIme = ?";

			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, korisnickoIme);
			rset = pstmt.executeQuery();

			return new Korisnik(korisnickoIme);
			
		} catch (SQLException ex) {
			System.out.println("Greska u SQL upitu!");
			ex.printStackTrace();

		}
		return null;
	}
	

	
	public static Korisnik getOne(String korisnickoIme) throws ParseException {

		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		try {
			String query = "SELECT * FROM korisnik where korisnickoIme = ?";

			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, korisnickoIme);
			rset = pstmt.executeQuery();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

			if (rset.next()) {
				int index = 2;
				String lozinka = rset.getString(index++);
				Date date = (Date) formatter.parse(rset.getString(index++));
				Role role = Role.valueOf(rset.getString(index++));

				return new Korisnik(korisnickoIme, lozinka, date, role);
			}
		} catch (SQLException ex) {
			System.out.println("Greska u SQL upitu!");
			ex.printStackTrace();

		}
		return null;
	}

	public static ArrayList<Korisnik> getAll() throws ParseException {

		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Korisnik> korisnici = new ArrayList<Korisnik>();
		try {
			String query = "SELECT * FROM korisnik";

			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

			while (rset.next()) {
				int index = 1;
				String korisnickoIme = rset.getString(index++);
				String password = rset.getString(index++);
				
				Date date = (Date) formatter.parse(rset.getString(index++));

				Role role = Role.valueOf(rset.getString(index++));

				Korisnik korisnik = new Korisnik(korisnickoIme, password, date, role);
				korisnici.add(korisnik);

			}
			return korisnici;
		} catch (SQLException ex) {
			System.out.println("Greska u SQL upitu!");
			ex.printStackTrace();

		}
		return null;

	}

	public static boolean add(Korisnik korisnik) throws Exception {
		Connection conn = ConnectionManager.getConnection();

		PreparedStatement pstmt = null;

		try {
			String query = "INSERT INTO korisnik (korisnickoIme, lozinka, datumRegistracije, uloga)"
					+ " VALUES (?, ?, ?, ?)";
			
			pstmt = conn.prepareStatement(query);

			int index = 1;
			pstmt.setString(index++, korisnik.getKorisnickoIme());
			pstmt.setString(index++, korisnik.getLozinka());
			pstmt.setString(index++, korisnik.getDatumRegistracije());
			pstmt.setString(index++, korisnik.getRole().toString());
			System.out.println(pstmt);

			
			return pstmt.executeUpdate() == 1;
		} catch (SQLException ex) {
			System.out.println("Greska u SQL upitu!");
			ex.printStackTrace();
		}
		return false;

	}
	
	public static boolean delete(String korisnickoIme) {
		Connection conn = ConnectionManager.getConnection();

		PreparedStatement pstmt = null;
		try {
			String query = "DELETE FROM korisnik WHERE korisnickoIme = ?";

			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, korisnickoIme);
			System.out.println(pstmt);

			return pstmt.executeUpdate() == 1;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}
	
	public static boolean updateKorisnika(Korisnik korisnik, String updatedKosrisnickoIme) throws Exception {
		Connection conn = ConnectionManager.getConnection();

		PreparedStatement pstmt = null;

		try {
			if(updatedKosrisnickoIme == null || updatedKosrisnickoIme.isEmpty()) {
				return false;
			}
			String query = "UPDATE korisnik SET korisnickoIme = '" + updatedKosrisnickoIme +"', uloga = ? WHERE korisnickoIme = ?";

			pstmt = conn.prepareStatement(query);

			int index = 1;
			pstmt.setString(index++, korisnik.getRole().toString());
			pstmt.setString(index++, korisnik.getKorisnickoIme());

			System.out.println(pstmt);

			return pstmt.executeUpdate() == 1;
		} catch (SQLException ex) {
			System.out.println("Greska u SQL upitu!");
			ex.printStackTrace();
		}
		return false;
	}

}
