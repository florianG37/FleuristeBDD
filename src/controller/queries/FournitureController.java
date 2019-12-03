package controller.queries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import model.Categorie;
import model.Fournisseur;
import model.Fourniture;
import model.Produit;
/**
 * 
 * Requetes Fourniture
 *
 */
public class FournitureController {
	/**
	 * Ajouter une fourniture
	 * @param fourniture fourniture
	 * @return Id fourniture
	 */
	public static int ajouterFourniture(Fourniture fourniture){
		Connection con=ConnexionController.connexion();
		String sql = "INSERT INTO fourniture(Date , IdFournisseur) VALUES(?,?)";
		int idFourniture = 0;
		try{
			PreparedStatement pst = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			pst.setDate(1,java.sql.Date.valueOf(fourniture.getDate().toString()));
			pst.setInt(2, fourniture.getIdFournisseur());
			pst.executeUpdate();
			
			//Permet de trouver la cle de l'objet, ici l'idFourniture
			ResultSet keys = pst.getGeneratedKeys();
			keys.next();
			idFourniture = keys.getInt(1);
		}catch (SQLException e) {
			e.printStackTrace();
		}
		ConnexionController.Deconnexion(con);
		return idFourniture;
	}
	/**
	 * Supprimer une fourniture
	 * @param idFourniture fourniture
	 */
	public static void supprimerFourniture(int idFourniture){
		Connection con=ConnexionController.connexion();
		String sql= "DELETE FROM fourniture WHERE fourniture.IdFourniture=?";
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, idFourniture);
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnexionController.Deconnexion(con);
	}
	/**
	 * Modifier fourniture
	 * @param fourniture nouvelle fourniture
	 * @param idFournitureAModifier fourniture a modifier
	 */
	public static void modifierFourniture(Fourniture fourniture, int idFournitureAModifier){
		Connection con=ConnexionController.connexion();
		String sql="UPDATE fourniture SET Date= ?, IdFournisseur =? WHERE fourniture.IdFourniture =?";
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setDate(1,java.sql.Date.valueOf(fourniture.getDate().toString()));
			pst.setInt(2, fourniture.getIdFournisseur());
			pst.setInt(3, idFournitureAModifier);
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnexionController.Deconnexion(con);
	}
	/**
	 * Voir toutes  les fournitures
	 * @return liste de fourniture
	 */
	public static ArrayList<Fourniture> voirFourniture(){
		Connection con=ConnexionController.connexion();
		ArrayList<Fourniture> listeFournitures = new ArrayList<Fourniture>();
		String sql = "SELECT * FROM fourniture";
		try {
			Statement stmt = con.createStatement();
			ResultSet resultats = stmt.executeQuery(sql);
			while (resultats.next()) {
				Fourniture fourniture = new Fourniture();
				fourniture.setId(resultats.getInt("IdFourniture"));	
				fourniture.setIdFournisseur(resultats.getInt("IdFournisseur"));	
				fourniture.setDate(LocalDate.parse(resultats.getString("Date")));
				listeFournitures.add(fourniture);
				}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		ConnexionController.Deconnexion(con);
		return listeFournitures;
	}
	
	/**
	 * Permet de trouver le fournisseur qui a fournit la fourniture
	 * @param fourniture
	 * @return le fournisseur
	 */
	public static Fournisseur trouverLeFournisseurDeLaFourniture(Fourniture fourniture)
	{
		Connection con=ConnexionController.connexion();
		String sql = "SELECT * FROM fournisseur WHERE IdFournisseur = "+fourniture.getIdFournisseur()+"";
		Fournisseur fournisseur = new Fournisseur();
		try 
		{
			Statement stmt = con.createStatement();
			ResultSet resultats = stmt.executeQuery(sql);
			
			//Sauter l'entete
			resultats.next(); 
			
			fournisseur.setIdPersonne(resultats.getInt("IdFournisseur"));	
			fournisseur.setNom(resultats.getString("Nom"));
			fournisseur.setPrenom(resultats.getString("Prenom"));
			fournisseur.setAdresse(resultats.getString("Adresse"));
			fournisseur.setVille(resultats.getString("Ville"));
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		ConnexionController.Deconnexion(con);
		return fournisseur;
	}
	
	/**
	 * Calculer le nombre de produits appartenant a la fourniture
	 * @param fourniture
	 * @return le nombre de produits
	 */
	public static double calculterNombreProduitsFourniture(Fourniture fourniture)
	{
		Connection con=ConnexionController.connexion();
		String sql = "SELECT SUM(Quantite) FROM livrer WHERE IdFourniture = "+fourniture.getId()+"";
		double quantite = 0;
		
		try 
		{
			Statement stmt = con.createStatement();
			ResultSet resultats = stmt.executeQuery(sql);
			
			//Sauter l'entete
			resultats.next(); 
			//Affecter le resultat de la requete dans quantite
			quantite = resultats.getDouble(1);
		}catch (SQLException e) {
			e.printStackTrace();
		}
		ConnexionController.Deconnexion(con);
		return quantite;
	}
	
	/**
	 * Calculer le montant de la fourniture 
	 * @param fourniture
	 * @return le montant 
	 */
	public static double calculerMontantFourniture(Fourniture fourniture)
	{
		Connection con=ConnexionController.connexion();
		String sql = "SELECT * FROM livrer INNER JOIN produit ON livrer.IdProduit = produit.IdProduit WHERE livrer.IdFourniture ="+fourniture.getId()+"";
		double montant = 0;
		int quantite = 0;
		double prix = 0;
		
		try {
				Statement stmt = con.createStatement();
				ResultSet resultats = stmt.executeQuery(sql);
				while (resultats.next()) 
				{
					quantite = resultats.getInt("livrer.Quantite");
					prix = resultats.getDouble("Prix");	
				
					montant = montant + quantite*prix;
				}
			}catch (SQLException e){
			e.printStackTrace();
			}
		ConnexionController.Deconnexion(con);
		return montant;
	}
	
	/**
	 * Afficher les produits de la fourniture
	 * @return les produits
	 */
	public static ArrayList<Produit> voirProduitDeLaFourniture(Fourniture fourniture){
		Connection con=ConnexionController.connexion();
		ArrayList<Produit> listeProduits = new ArrayList<Produit>();
		String sql = "SELECT * FROM livrer INNER JOIN produit ON livrer.IdProduit = produit.IdProduit WHERE livrer.IdFourniture =?";
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1,fourniture.getId());
			ResultSet resultats = pst.executeQuery();
			
			while (resultats.next()) {
				Produit produit = new Produit();
				produit.setIdProduit(resultats.getInt("IdProduit"));	
				produit.setNom(resultats.getString("Nom"));
				produit.setCategorie(Categorie.valueOf(resultats.getString("Categorie")));
				produit.setEspece(resultats.getString("Espece"));
				produit.setPrix(resultats.getDouble("Prix"));
				produit.setStock(resultats.getInt("Quantite"));
				listeProduits.add(produit);
				}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		ConnexionController.Deconnexion(con);
		return listeProduits;
	}
}
