package oumarly.app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import oumarly.app.model.SecretKeyModel;

public class SecretKeyDao extends KeyDao {
	
	
	public static SecretKeyModel getSecretKeyModel(ResultSet rs) throws SQLException {
		SecretKeyModel keyModel = new SecretKeyModel();
		keyModel.setId(rs.getLong("id"));
		keyModel.setNom(rs.getString("nom"));
		keyModel.setAlgorithme(rs.getString("algorithme"));
		keyModel.setTaille(rs.getInt("taille"));
		keyModel.setProvider(rs.getString("provider"));
		keyModel.setEncodageHex(rs.getString("encodage_hex"));
		return keyModel;
	}


	public ArrayList<SecretKeyModel> all() throws SQLException {
		String query = "SELECT * FROM secret_keys";
		ArrayList<SecretKeyModel> res = new ArrayList<SecretKeyModel>();
		Connection cnx = this.getConnection();
		Statement stmt = cnx.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		while (rs.next())
			res.add(getSecretKeyModel(rs));
		cnx.close();
		return res;
	}


	public int store(SecretKeyModel keyModel) throws SQLException {
		String query = "INSERT INTO secret_keys(nom, algorithme, taille, provider, encodage_hex) VALUES (?,?,?,?,?)";
		Connection cnx = this.getConnection();
		PreparedStatement pStmt = cnx.prepareStatement(query);
		pStmt.setString(1, keyModel.getNom());
		pStmt.setString(2, keyModel.getAlgorithme());
		pStmt.setInt(3, keyModel.getTaille());
		pStmt.setString(4, keyModel.getProvider());
		pStmt.setString(5, keyModel.getEncodageHex());
		int res = pStmt.executeUpdate();
		cnx.close();
		return res;
	}


	public int delete(SecretKeyModel keyModel) throws SQLException {
		String query = "DELETE FROM secret_keys WHERE id = ?";
		Connection cnx = this.getConnection();
		PreparedStatement pStmt = cnx.prepareStatement(query);
		pStmt.setLong(1, keyModel.getId());
		int res = pStmt.executeUpdate();
		cnx.close();
		return res;
	}
}
