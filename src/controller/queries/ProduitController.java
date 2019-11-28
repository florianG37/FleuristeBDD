package controller.queries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.Produit;

public class ProduitController {
	
	public static void ajouterProduit(Produit produit){
		Connection con=ConnexionController.connexion();
		String sql = "INSERT INTO produit(Nom, Categorie, Espece,Prix, Quantite) VALUES(?,?,?,?,?)";
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, produit.getNom());
			pst.setString(2,produit.getCategorie().name());
			pst.setString(3, produit.getEspece());
			pst.setFloat(4, (float) produit.getPrix());
			pst.setInt(5, produit.getStock());
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
				
		ConnexionController.Deconnexion(con);
	}
	public static void supprimerProduit(int IdProduit){
		Connection con=ConnexionController.connexion();
		String sql= "DELETE FROM produit WHERE produit.IdProduit = ?";
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, IdProduit);
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
				
		ConnexionController.Deconnexion(con);
	}
	public static void modifierProduit(Produit produit, int IdProduitAModifier){
		Connection con=ConnexionController.connexion();
		String sql= "UPDATE produit SET Nom = ?, Categorie = ?, Espece = ?, Prix = ?, Quantite = ? WHERE produit.IdProduit = ?";
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, produit.getNom());
			pst.setString(2,produit.getCategorie().name());
			pst.setString(3, produit.getEspece());
			pst.setFloat(4, (float) produit.getPrix());
			pst.setInt(5, produit.getStock());
			pst.setInt(6, IdProduitAModifier);
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
				
		ConnexionController.Deconnexion(con);
	}
		
}
