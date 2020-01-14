package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.TipProjekcije;

public class tipProjekcijeDAO {

	public static TipProjekcije getOne(int id) {

		Connection conn = ConnectionManager.getConnection();

		PreparedStatement pstmt = null;
		ResultSet rset = null;

		try {
			String query = "SELECT naziv FROM tipprojekcije WHERE id = ?";

			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, id);
			rset = pstmt.executeQuery();

			if (rset.next()) {
				int index = 1;
				String naziv = rset.getString(index++);

				return new TipProjekcije(id, naziv);
			}
		} catch (SQLException ex) {
			System.out.println("Greska u SQL upitu!");
			ex.printStackTrace();

		}
		return null;

	}
}
