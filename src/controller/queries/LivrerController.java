package controller.queries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
/**
 * 
 * Requetes Livrer
 *
 */
public class LivrerController {
	/**
	 * Ajouter un produit dans une fourniture
	 * @param idFourniture fourniture
	 * @param idProduit produit
	 * @param quantite quantite
	 */
	public static void ajouterLivrer(int idFourniture,int idProduit,int quantite){
		Connection con=ConnexionController.connexion();
		String sql ="INSERT INTO livrer ( IdFourniture, IdProduit,Quantite) VALUES (?,?,?)";
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, idFourniture);
			pst.setInt(2, idProduit);
			pst.setInt(3, quantite);
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnexionController.Deconnexion(con);
	}
	/**
	 * Supprimer un produit dune fourniture
	 * @param idFourniture fourniture 
	 * @param idProduit produit
	 */
	public static void supprimerLivrer(int idFournisseur, int idProduit){
		Connection con=ConnexionController.connexion();
		String sql= "DELETE FROM livrer WHERE livrer.IdFourniture = ? AND livrer.IdProduit = ?";
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
	 * Modifier un objet de la table Livrer
	 * @param quantite quantite
	 * @param idFournitureAModifier fourniture a modifier 
	 * @param idProduitAModifier produit a modifier
	 */
	public static void modifierLivrer(int quantite, int idFournitureAModifier, int idProduitAModifier){
		Connection con=ConnexionController.connexion();
		String sql= "UPDATE livrer SET Quantite= ? WHERE livrer.IdFourniture = ? AND livrer.IdProduit =?";
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, quantite);
			pst.setInt(2, idFournitureAModifier);
			pst.setInt(3, idProduitAModifier);
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnexionController.Deconnexion(con);
	}
}
