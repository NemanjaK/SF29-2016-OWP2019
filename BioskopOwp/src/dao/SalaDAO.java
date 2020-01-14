package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Sala;

public class SalaDAO {

	public static Sala getOne(int id) {

		Connection conn = ConnectionManager.getConnection();

		PreparedStatement pstmt = null;
		ResultSet rset = null;

		try {
			String query = "SELECT naziv FROM sala WHERE id = ?";

			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, id);
			rset = pstmt.executeQuery();

			if (rset.next()) {
				int index = 1;
				String naziv = rset.getString(index++);

				return new Sala(id, naziv);
			}
		} catch (SQLException ex) {
			System.out.println("Greska u SQL upitu!");
			ex.printStackTrace();

		}
		return null;

	}

}
