package controller.queries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.Categorie;
import model.Fournisseur;
import model.Produit;


/**
 * 
 * Requetes Fournisseur
 *
 */
public class FournisseurController {
	/**
	 * Ajouter un fournisseur
	 * @param fournisseur fournisseur
	 */
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
	/**
	 * Modifier un fournisseur
	 * @param fournisseur un fournisseur
	 * @param IdFournisseurAModifier fournisseur a modifier
	 */
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
	/**
	 * Voir tous les fournisseurs
	 * @return liste de fournisseur
	 */
	public static ArrayList<Fournisseur> voirFournisseur(){
		Connection con=ConnexionController.connexion();
		ArrayList<Fournisseur> listeFournisseurs = new ArrayList<Fournisseur>();
		String sql = "SELECT * FROM fournisseur";
		try {
			Statement stmt = con.createStatement();
			ResultSet resultats = stmt.executeQuery(sql);
			while (resultats.next()) {
				Fournisseur fournisseur = new Fournisseur();
				fournisseur.setIdPersonne(resultats.getInt("IdFournisseur"));	
				fournisseur.setNom(resultats.getString("Nom"));
				fournisseur.setPrenom(resultats.getString("Prenom"));
				fournisseur.setAdresse(resultats.getString("Adresse"));
				fournisseur.setVille(resultats.getString("Ville"));
				listeFournisseurs.add(fournisseur);
				}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		ConnexionController.Deconnexion(con);
		return listeFournisseurs;
	}
	/**
	 * Voir produit dun fournisseur
	 * @param idFournisseur un fournisseur
	 * @return liste produit
	 */
	public static ArrayList<Produit> voirProduitsFournisseur(int idFournisseur)
	{
		Connection con=ConnexionController.connexion();
		ArrayList<Produit> listeProduits = new ArrayList<Produit>();
		String sql = "SELECT * FROM produit p INNER JOIN fournir f on p.IdProduit = f.IdProduit WHERE f.IdFournisseur =?";
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1,idFournisseur);
			ResultSet resultats = pst.executeQuery();
			
			while(resultats.next())
			{
				Produit prod = new Produit();
				prod.setIdProduit(resultats.getInt("IdProduit"));	
				prod.setNom(resultats.getString("Nom"));
				prod.setCategorie(Categorie.valueOf(resultats.getString("Categorie")));
				prod.setEspece(resultats.getString("Espece"));
				prod.setPrix(resultats.getDouble("Prix"));
				prod.setStock(resultats.getInt("Quantite"));
				listeProduits.add(prod);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		ConnexionController.Deconnexion(con);
		return listeProduits;
	}
}
