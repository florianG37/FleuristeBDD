package controller.queries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 
 * Requetes commander
 *
 */
public class CommanderController {
	/**
	 * ajouter un produit a une commande
	 * @param idCommande la commande
	 * @param idProduit le produit
	 * @param quantite quantite a ajouter
	 */
	public static void ajouterCommander(int idCommande, int idProduit, int quantite){
		Connection con=ConnexionController.connexion();
		String sql ="INSERT INTO commander (IdCommande, IdProduit, quantite) VALUES (?,?,?)";
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, idCommande);
			pst.setInt(2, idProduit);
			pst.setInt(3, quantite);
			pst.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		ConnexionController.Deconnexion(con);
	}
	/**
	 * Supprimer un produit dune commande
	 * @param idCommande commande
	 * @param idProduit produit
	 */
	public static void supprimerCommander(int idCommande, int idProduit){
		Connection con=ConnexionController.connexion();
		String sql="DELETE FROM commander WHERE commander.IdCommande=? AND commander.idProduit=? ";
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, idCommande);
			pst.setInt(2, idProduit);
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		ConnexionController.Deconnexion(con);
	}
	/**
	 * Modifier un produit dune commande
	 * @param idCommande la commande
	 * @param idProduit le produit
	 * @param quantite la quantite
	 */
	public static void modifierCommander(int idCommande, int idProduit, int quantite){
		Connection con=ConnexionController.connexion();
		String sql= "UPDATE commander SET Quantite= ? WHERE commander.IdCommande = ? AND livrer.IdProduit =?";
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, quantite);
			pst.setInt(2, idCommande);
			pst.setInt(3, idProduit);
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
}
