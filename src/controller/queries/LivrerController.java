package controller.queries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LivrerController {
	public static void ajouterLivrer(int idFournisseur,int idProduit,int quantite){
		Connection con=ConnexionController.connexion();
		String sql ="INSERT INTO livrer ( IdFournisseur, IdProduit,Quantite) VALUES (?,?,?)";
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, idFournisseur);
			pst.setInt(2, idProduit);
			pst.setInt(3, quantite);
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnexionController.Deconnexion(con);
	}
	public static void supprimerLivrer(int idFournisseur, int idProduit){
		Connection con=ConnexionController.connexion();
		String sql= "DELETE FROM livrer WHERE livrer.IdFournisseur = ? AND livrer.IdProduit = ?";
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
	public static void modifierLivrer(int quantite, int idCommandeAModifier, int idProduitAModifier){
		Connection con=ConnexionController.connexion();
		String sql= "UPDATE livrer SET Quantite= ? WHERE livrer.IdCommande = ? AND livrer.IdProduit =?";
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, quantite);
			pst.setInt(2, idCommandeAModifier);
			pst.setInt(3, idProduitAModifier);
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnexionController.Deconnexion(con);
	}
}
