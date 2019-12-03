package controller.queries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.Categorie;
import model.Produit;
/**
 * 
 * Requetes produit
 *
 */
public class ProduitController {
	/**
	 * 
	 * Ajouter produit
	 */
	public static void ajouterProduit(Produit produit){
		Connection con=ConnexionController.connexion();
		String sql = "INSERT INTO produit(Nom, Categorie, Espece,Prix, Quantite) VALUES(?,?,?,?,?)";
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, produit.getNom());
			pst.setString(2,produit.getCategorie().name());
			pst.setString(3, produit.getEspece());
			pst.setDouble(4, produit.getPrix());
			pst.setInt(5, produit.getStock());
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
				
		ConnexionController.Deconnexion(con);
	}
	/**
	 * Supprimer produit 
	 * @param IdProduit le produit
	 */
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
	/**
	 * Produit a modifier
	 * @param produit nouveau produit
	 * @param IdProduitAModifier produit a modifier
	 */
	public static void modifierProduit(Produit produit, int IdProduitAModifier){
		Connection con=ConnexionController.connexion();
		String sql= "UPDATE produit SET Nom = ?, Categorie = ?, Espece = ?, Prix = ?, Quantite = ? WHERE produit.IdProduit = ?";
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, produit.getNom());
			pst.setString(2,produit.getCategorie().name());
			pst.setString(3, produit.getEspece());
			pst.setDouble(4, produit.getPrix());
			pst.setInt(5, produit.getStock());
			pst.setInt(6, IdProduitAModifier);
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
				
		ConnexionController.Deconnexion(con);
	}
	/**
	 * Voir tous les produits
	 * @return liste produit
	 */
	public static ArrayList<Produit> voirProduit(){
		Connection con=ConnexionController.connexion();
		ArrayList<Produit> listeProduit = new ArrayList<Produit>();
		String sql = "SELECT * FROM produit";
		try {
			Statement stmt = con.createStatement();
			ResultSet resultats = stmt.executeQuery(sql);
			while (resultats.next()) {
				Produit prod = new Produit();
				prod.setIdProduit(resultats.getInt("IdProduit"));	
				prod.setNom(resultats.getString("Nom"));
				prod.setCategorie(Categorie.valueOf(resultats.getString("Categorie")));
				prod.setEspece(resultats.getString("Espece"));
				prod.setPrix(resultats.getDouble("Prix"));
				prod.setStock(resultats.getInt("Quantite"));
				listeProduit.add(prod);
				}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		ConnexionController.Deconnexion(con);
		return listeProduit;
	}
	/**
	 * Voir produits sans stocke
	 * @return liste produit
	 */
	public static ArrayList<Produit> voirProduitSansStock(){
		Connection con=ConnexionController.connexion();
		ArrayList<Produit> listeProduit= new ArrayList<Produit>();
		String sql = "SELECT * FROM produit WHERE produit.Quantite = 0";
		try {
			Statement stmt = con.createStatement();
			ResultSet resultats = stmt.executeQuery(sql);
			while (resultats.next()) {
				Produit prod = new Produit();
				prod.setIdProduit(resultats.getInt("IdProduit"));	
				prod.setNom(resultats.getString("Nom"));
				prod.setCategorie(Categorie.valueOf(resultats.getString("Categorie")));
				prod.setEspece(resultats.getString("Espece"));
				prod.setPrix(resultats.getDouble("Prix"));
				prod.setStock(resultats.getInt("Quantite"));
				listeProduit.add(prod);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		ConnexionController.Deconnexion(con);
		return listeProduit;
		
	}
}
