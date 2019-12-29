package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Film;

public class FilmoviDAO {

	public static List<Film> getAll(String naziv)  {
		List<Film> filmovi = new ArrayList<>();

		Connection conn = ConnectionManager.getConnection();

		PreparedStatement pstmt = null;
		ResultSet rset = null;

		try {
			String query = "SELECT * FROM film WHERE naziv LIKE ?";

			pstmt = conn.prepareStatement(query);
			int index = 1;
			pstmt.setString(index++, "%" + naziv + "%");
			System.out.println(pstmt);

			
			rset = pstmt.executeQuery();

			while (rset.next()) {
				index = 1;
				int id = rset.getInt(index++);
				String nazivF = rset.getString(index++);
				String reziser = rset.getString(index++);
				String glumci = rset.getString(index++);
				String zanrovi = rset.getString(index++);
				String distributer = rset.getString(index++);
				Integer godinaProizvodnje = rset.getInt(index++);
				String opis = rset.getString(index++);
				Integer trajanje = rset.getInt(index++);
				String zemljaPorekla = rset.getString(index++);

				Film film = new Film(id, nazivF, reziser, glumci, zanrovi, distributer, godinaProizvodnje, opis,
						trajanje, zemljaPorekla);
				filmovi.add(film);
			}
			return filmovi;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;

	}

	public static Film getOne(int id)  {
		Connection conn = ConnectionManager.getConnection();

		PreparedStatement pstmt = null;
		ResultSet rset = null;

		try {
			String query = "SELECT * FROM film WHERE id = ?";

			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, id);
			System.out.println(pstmt);
			rset = pstmt.executeQuery();

			if (rset.next()) {
				int index = 2;
				String naziv = rset.getString(index++);
				String reziser = rset.getString(index++);
				String glumci = rset.getString(index++);
				String zanrovi = rset.getString(index++);
				String distributer = rset.getString(index++);
				Integer godinaProizvodnje = rset.getInt(index++);
				String opis = rset.getString(index++);
				Integer trajanje = rset.getInt(index++);
				String zemljaPorekla = rset.getString(index++);

				return new Film(id, naziv, reziser, glumci, zanrovi, distributer, godinaProizvodnje, opis, trajanje,
						zemljaPorekla);

			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;

	}

	public static boolean update(Film film) throws Exception {
		Connection conn = ConnectionManager.getConnection();

		PreparedStatement pstmt = null;

		try {
			String query = "UPDATE film SET naziv = ?, reziser = ?, glumci = ?, zanrovi = ?, distributer = ?, godinaProizvodnje = ?, opis = ?, trajanje = ?, zemljaPorekla = ?"
					+ "WHERE id = ?";

			pstmt = conn.prepareStatement(query);

			int index = 1;
			pstmt.setString(index++, film.getNaziv());
			pstmt.setString(index++, film.getReziser());
			pstmt.setString(index++, film.getGlumci());
			pstmt.setString(index++, film.getZanrovi());
			pstmt.setString(index++, film.getDistributer());
			pstmt.setInt(index++, film.getGodinaProizvodnje());
			pstmt.setString(index++, film.getOpis());
			pstmt.setInt(index++, film.getTrajanje());
			pstmt.setString(index++, film.getZemljaPorekla());

			pstmt.setInt(index++, film.getId());

			System.out.println(pstmt);

			return pstmt.executeUpdate() == 1;
		} catch (SQLException ex) {
			System.out.println("Greska u SQL upitu!");
			ex.printStackTrace();
		}
		return false;
	}

	public static boolean delete(int id) {
		Connection conn = ConnectionManager.getConnection();

		PreparedStatement pstmt = null;
		try {
			String query = "DELETE FROM film where id = ?";

			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, id);
			System.out.println(pstmt);

			return pstmt.executeUpdate() == 1;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}

	public static int getFilmId() {
		Connection conn = ConnectionManager.getConnection();
		int id = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			String query = "SELECT MAX(id) FROM film";
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

	public static boolean add(Film film) throws Exception {
		Connection conn = ConnectionManager.getConnection();

		PreparedStatement pstmt = null;
		try {
			String query = "INSERT INTO film (naziv, reziser, glumci, zanrovi, distributer, godinaProizvodnje, opis, trajanje, zemljaPorekla)"
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(query);


			int index = 1;
			pstmt.setString(index++, film.getNaziv());
			pstmt.setString(index++, film.getReziser());
			pstmt.setString(index++, film.getGlumci());
			pstmt.setString(index++, film.getZanrovi());
			pstmt.setString(index++, film.getDistributer());
			pstmt.setInt(index++, film.getGodinaProizvodnje());
			pstmt.setString(index++, film.getOpis());
			pstmt.setInt(index++, film.getTrajanje());
			pstmt.setString(index++, film.getZemljaPorekla());
			
			System.out.println(pstmt);

			return pstmt.executeUpdate() == 1;

		} catch (Exception ex) {
			System.out.println("Greska SQL add");
			ex.printStackTrace();
		}finally {
			try {pstmt.close();} catch (SQLException ex1) {ex1.printStackTrace();}
		}		
		return false;
	}
}
