package controller.queries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.Fournisseur;



public class FournisseurController {
	public static void ajouterFournisseur(Fournisseur fournisseur){
		Connection con=ConnexionController.connexion();
		String sql ="INSERT INTO fournisseur ( Nom, Prenom, Adresse, Ville) VALUES (?,?,?,?)";
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, fournisseur.getNom());
			pst.setString(2, fournisseur.getPrenom());
			pst.setString(3, fournisseur.getAdresse());
			pst.setString(4, fournisseur.getVille());
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnexionController.Deconnexion(con);
	}

	public static void supprimerFournisseur(int IdFournisseur){
		Connection con=ConnexionController.connexion();
		String sql= "DELETE FROM fournisseur WHERE fournisseur.IdFournisseur = ?";
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, IdFournisseur);
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		ConnexionController.Deconnexion(con);
	}
	public static void modifierFournisseur(Fournisseur fournisseur, int IdFournisseurAModifier){
		Connection con=ConnexionController.connexion();
		String sql="UPDATE fournisseur SET Nom = ?, Prenom = ?, Adresse =? , Ville =? WHERE fournisseur.IdFournisseur =?";
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, fournisseur.getNom());
			pst.setString(2, fournisseur.getPrenom());
			pst.setString(3, fournisseur.getAdresse());
			pst.setString(4, fournisseur.getVille());
			pst.setInt(5, IdFournisseurAModifier);
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
				
		ConnexionController.Deconnexion(con);	
	}


}
