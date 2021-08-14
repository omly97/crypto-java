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
		keyModel.setTaille(rs.getInt("taille"));
		keyModel.setProvider(rs.getString("provider"));
		keyModel.setPublicKeyEncodageHex(rs.getString("public_key_encodage_hex"));
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
		String query = "INSERT INTO key_pairs(nom, algorithme, provider, taille,"
				+ " public_key_encodage_hex, private_key_encodage_hex) VALUES (?,?,?,?,?,?)";
		Connection cnx = this.getConnection();
		PreparedStatement pStmt = cnx.prepareStatement(query);
		pStmt.setString(1, keyModel.getNom());
		pStmt.setString(2, keyModel.getAlgorithme());
		pStmt.setString(3, keyModel.getProvider());
		pStmt.setInt(4, keyModel.getTaille());
		pStmt.setString(5, keyModel.getPublicKeyEncodageHex());
		pStmt.setString(6, keyModel.getPrivateKeyEncodageHex());
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
