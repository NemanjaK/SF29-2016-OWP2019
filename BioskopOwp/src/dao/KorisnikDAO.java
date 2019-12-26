package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Korisnik;
import model.Korisnik.Role;

public class KorisnikDAO {

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
				Date date = rset.getDate(index++);
				String datumRegistracije = DateConverter.dateToString(date);
				Role role = Role.valueOf(rset.getString(index++));

				return new Korisnik(korisnickoIme, password, datumRegistracije, role);
			}
		} catch (SQLException ex) {
			System.out.println("Greska u SQL upitu!");
			ex.printStackTrace();

		}
		return null;
	}
	
	public static ArrayList<Korisnik> getAll(){
		
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Korisnik> korisnici = new ArrayList<Korisnik>();
		try {
			String query = "SELECT * FROM korisnik";
			
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				int index = 1;
				String korisnickoIme = rset.getString(index++);
				String password = rset.getString(index++);
				Date date = rset.getDate(index++);
				String datumRegistracije = DateConverter.dateToString(date);
				Role role = Role.valueOf(rset.getString(index++));
				
				Korisnik korisnik = new Korisnik(korisnickoIme, password, datumRegistracije, role);
				korisnici.add(korisnik);
				
			}
			return korisnici;
		}catch (SQLException ex) {
			System.out.println("Greska u SQL upitu!");
			ex.printStackTrace();

		}
		return null;
		
		
	}

}
