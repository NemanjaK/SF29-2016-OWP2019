package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Film;

public class FilmoviDAO {

	

	public static List<Film> getAll() {
		List<Film> filmovi = new ArrayList<>();

		Connection conn = ConnectionManager.getConnection();

		PreparedStatement pstmt = null;
		ResultSet rset = null;

		try {
			String query = "SELECT * FROM film";

			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();

			while (rset.next()) {
				int index = 1;
				int id = rset.getInt(index++);
				String naziv = rset.getString(index++);
				String reziser = rset.getString(index++);
				String glumci = rset.getString(index++);
				String zanrovi = rset.getString(index++);
				String distributer = rset.getString(index++);
				Integer godinaProizvodnje = rset.getInt(index++);
				String opis = rset.getString(index++);
				Integer trajanje = rset.getInt(index++);
				String zemljaPorekla = rset.getString(index++);

				Film film = new Film(id, naziv, reziser, glumci, zanrovi, distributer, godinaProizvodnje, opis,
						trajanje, zemljaPorekla);
				filmovi.add(film);
			}
			return filmovi;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;

	}

	

	

	


}
