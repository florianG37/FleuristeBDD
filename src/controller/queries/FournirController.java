package controller.queries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

public class FournirController {
	public static void associerFournisseurAProduit(int idFournisseur, int idProduit){
		Connection con=ConnexionController.connexion();
		String sql ="INSERT INTO fournir (IdFournisseur, IdProduit) VALUES (?,?)";
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, idFournisseur);
			pst.setInt(2, idProduit);
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnexionController.Deconnexion(con);
	}
	public static void desassocierFournisseurAProduit(int idFournisseur, int idProduit){
		Connection con=ConnexionController.connexion();
		String sql= "DELETE FROM fournir WHERE fournir.IdFournisseur=? AND fournir.IdProduit=?";
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, idFournisseur);
			pst.setInt(2, idProduit);
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnexionController.Deconnexion(con);
	}
	
	public static HashMap<Integer, Integer> voirAssociation(){
		Connection con=ConnexionController.connexion();
		HashMap<Integer, Integer> appartenir = new HashMap<Integer, Integer>();
		String sql = "SELECT * FROM fournir";
		try {
			Statement stmt = con.createStatement();
			ResultSet resultats = stmt.executeQuery(sql);
			while (resultats.next()) {
				int idFournisseur =resultats.getInt("IdFournisseur");
				int idProduit =resultats.getInt("IdProduit");
				appartenir.put(idFournisseur, idProduit);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		ConnexionController.Deconnexion(con);
		return appartenir;
	}
}
