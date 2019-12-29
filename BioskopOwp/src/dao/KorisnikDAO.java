package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import model.Korisnik;
import model.Korisnik.Role;

public class KorisnikDAO {
	
	public static SimpleDateFormat DATETIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm");

	
	public static Korisnik getOne(String korisnickoIme) {

		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		try {
			String query = "SELECT * FROM korisnik where korisnickoIme = ?";

			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, korisnickoIme);
			rset = pstmt.executeQuery();

			if (rset.next()) {
				int index = 2;
				String password = rset.getString(index++);
				Timestamp datumRegistracije = rset.getTimestamp(index++);
				Role role = Role.valueOf(rset.getString(index++));

				return new Korisnik(korisnickoIme, password, datumRegistracije, role);
			}
		} catch (SQLException ex) {
			System.out.println("Greska u SQL upitu!");
			ex.printStackTrace();

		}
		return null;
	}

	public static ArrayList<Korisnik> getAll() {

		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Korisnik> korisnici = new ArrayList<Korisnik>();
		try {
			String query = "SELECT * FROM korisnik";

			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();

			while (rset.next()) {
				int index = 1;
				String korisnickoIme = rset.getString(index++);
				String password = rset.getString(index++);
				//Date date = rset.getDate(index++);
				Timestamp datumRegistracije = rset.getTimestamp(index++);
				Role role = Role.valueOf(rset.getString(index++));

				Korisnik korisnik = new Korisnik(korisnickoIme, password, datumRegistracije, role);
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
			pstmt.setTimestamp(index++, korisnik.getDatumRegistracije());
			/*
			 * java.util.Date myDate =
			 * DateConverter.stringToDateForWrite(korisnik.getDatumRegistracije());
			 * java.sql.Date date = new java.sql.Date(myDate.getTime());
			 * pstmt.setDate(index++, date);
			 */
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
	

}
