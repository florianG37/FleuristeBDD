package controller.queries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * 
 * Requetes Fournir
 *
 */
public class FournirController {
	/**
	 * Associer un fournisseur a un produit
	 * @param idFournisseur le fournisseur
	 * @param idProduit le produit
	 */
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
	/**
	 * Desassocier un fournisseur dun produit
	 * @param idFournisseur un fournisseur
	 * @param idProduit un produit
	 */
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
	/**
	 * Voir toutes les associations fournisseur/produit dun fournisseur
	 * @param idFournisseur un fournisseur
	 * @return liste de d ID produit
	 */
	public static ArrayList<Integer> voirAssociation(int idFournisseur){
		Connection con=ConnexionController.connexion();
		ArrayList<Integer> appartenir = new ArrayList<Integer>();
		String sql = "SELECT * FROM fournir WHERE fournir.IdFournisseur=?";
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, idFournisseur);
			ResultSet resultats = pst.executeQuery();
			
			while (resultats.next()) {
				int idProduit =resultats.getInt("IdProduit");
				appartenir.add(idProduit);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		ConnexionController.Deconnexion(con);
		return appartenir;
	}
}
