package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import model.Film;
import model.Korisnik;
import model.Projekcija;
import model.Sala;
import model.TipProjekcije;

public class ProjekcijeDAO {

	public static List<Projekcija> getAll(double minCenaKarte, double maxCenaKarte) {

		Connection conn = ConnectionManager.getConnection();

		PreparedStatement pstmt = null;
		ResultSet rset = null;

		ArrayList<Projekcija> projekcije = new ArrayList<Projekcija>();

		try {
			String query = "SELECT * FROM projekcija WHERE cenaKarte >= ? AND cenaKarte <= ?";

			pstmt = conn.prepareStatement(query);
			int index = 1;
			pstmt.setDouble(index++, minCenaKarte);
			pstmt.setDouble(index++, maxCenaKarte);			
			
			rset = pstmt.executeQuery();

			while (rset.next()) {
				index = 1;
				Integer id = rset.getInt(index++);
				Integer tipProjekcije = rset.getInt(index++);
				TipProjekcije tp = tipProjekcijeDAO.getOne(tipProjekcije);
				Integer sala = rset.getInt(index++);
				Sala sl = SalaDAO.getOne(sala);
				Timestamp datumVreme = rset.getTimestamp(index++);
				Double cenaKarte = rset.getDouble(index++);
				String adminDdodaoProjekciju = rset.getString(index++);
				Korisnik ap = KorisnikDAO.getOne(adminDdodaoProjekciju);
				Integer prikazaniFilm = rset.getInt(index++);
				Film pf = FilmoviDAO.getOne(prikazaniFilm);

				Projekcija projekcija = new Projekcija(id, tp, sl, datumVreme, cenaKarte, ap, pf);
				projekcije.add(projekcija);
			}
			return projekcije;
		} catch (SQLException ex) {
			System.out.println("Greska u SQL upitu A!");
			ex.printStackTrace();

		}
		return null;
	}

	public static Projekcija getOne(int id) {
		Connection conn = ConnectionManager.getConnection();

		PreparedStatement pstmt = null;
		ResultSet rset = null;

		try {
			String query = "SELECT * FROM projekcija WHERE id = ?";

			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, id);
			System.out.println(pstmt);

			rset = pstmt.executeQuery();

			if (rset.next()) {
				int index = 2;
				Integer tipProjekcije = rset.getInt(index++);
				TipProjekcije tp = tipProjekcijeDAO.getOne(tipProjekcije);
				Integer sala = rset.getInt(index++);
				Sala sl = SalaDAO.getOne(sala);
				Timestamp datumVreme = rset.getTimestamp(index++);
				Double cenaKarte = rset.getDouble(index++);
				String adminDdodaoProjekciju = rset.getString(index++);
				Korisnik ap = KorisnikDAO.getOne(adminDdodaoProjekciju);
				Integer prikazaniFilm = rset.getInt(index++);
				Film pf = FilmoviDAO.getOne(prikazaniFilm);

				return new Projekcija(id, tp, sl, datumVreme, cenaKarte, ap, pf);

			}
		} catch (SQLException ex) {
			System.out.println("Greska u SQL upitu A!");
			ex.printStackTrace();

		}
		return null;
	}

}
