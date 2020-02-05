package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Sala;
import model.Sediste;

public class SedisteDAO {
	public static Sediste getOne(int redniBroj) {

		Connection conn = ConnectionManager.getConnection();

		PreparedStatement pstmt = null;
		ResultSet rset = null;

		try {
			String query = "SELECT redniBroj FROM sediste WHERE redniBroj = ?";

			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, redniBroj);
			rset = pstmt.executeQuery();

			if (rset.next()) {
				int index = 1;
				int redniBrojS = rset.getInt(index++);

				return new Sediste(redniBrojS);
			}
		} catch (SQLException ex) {
			System.out.println("Greska u SQL upitu!");
			ex.printStackTrace();

		} finally {
			try {pstmt.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {rset.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {conn.close();} catch (Exception ex1) {ex1.printStackTrace();}
		}
		return null;

	}
}
