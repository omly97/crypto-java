package oumarly.app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import oumarly.app.model.KeyPairModel;

public class KeyPairDao extends KeyDao {


	public static KeyPairModel getKeyPairModel(ResultSet rs) throws SQLException {
		KeyPairModel keyModel = new KeyPairModel();
		keyModel.setId(rs.getLong("id"));
		keyModel.setNom(rs.getString("nom"));
		keyModel.setAlgorithme(rs.getString("algorithme"));
		keyModel.setPublicKeyTaille(rs.getInt("public_key_taille"));
		keyModel.setPublicKeyFormat(rs.getString("public_key_format"));
		keyModel.setPublicKeyEncodageHex(rs.getString("public_key_encodage_hex"));
		keyModel.setPrivateKeyTaille(rs.getInt("private_key_taille"));
		keyModel.setPrivateKeyFormat(rs.getString("private_key_format"));
		keyModel.setPrivateKeyEncodageHex(rs.getString("private_key_encodage_hex"));
		return keyModel;
	}


	public ArrayList<KeyPairModel> all() throws SQLException {
		String query = "SELECT * FROM key_pairs";
		ArrayList<KeyPairModel> res = new ArrayList<KeyPairModel>();
		Connection cnx = this.getConnection();
		Statement stmt = cnx.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		while (rs.next())
			res.add(getKeyPairModel(rs));
		cnx.close();
		return res;
	}


	public int store(KeyPairModel keyModel) throws SQLException {
		String query = "INSERT INTO key_pairs(nom, algorithme, "
				+ "public_key_taille, public_key_format, public_key_encodage_hex, "
				+ "private_key_taille, private_key_format, private_key_encodage_hex) "
				+ "VALUES (?,?, ?,?,?, ?,?,?)";
		Connection cnx = this.getConnection();
		PreparedStatement pStmt = cnx.prepareStatement(query);
		pStmt.setString(1, keyModel.getNom());
		pStmt.setString(2, keyModel.getAlgorithme());
		pStmt.setInt(3, keyModel.getPublicKeyTaille());
		pStmt.setString(4, keyModel.getPublicKeyFormat());
		pStmt.setString(5, keyModel.getPublicKeyEncodageHex());
		pStmt.setInt(6, keyModel.getPrivateKeyTaille());
		pStmt.setString(7, keyModel.getPrivateKeyFormat());
		pStmt.setString(8, keyModel.getPrivateKeyEncodageHex());
		int res = pStmt.executeUpdate();
		cnx.close();
		return res;
	}


	public int delete(KeyPairModel keyModel) throws SQLException {
		String query = "DELETE FROM key_pairs WHERE id = ?";
		Connection cnx = this.getConnection();
		PreparedStatement pStmt = cnx.prepareStatement(query);
		pStmt.setLong(1, keyModel.getId());
		int res = pStmt.executeUpdate();
		cnx.close();
		return res;
	}
}
