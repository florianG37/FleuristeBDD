package controller.queries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import model.Categorie;
import model.Client;
import model.Commande;
import model.Produit;
/**
 * 
 * Requetes pour commande
 *
 */
public class CommandeController {
	/**
	 * ajouter une commande
	 * @param cmd commande a ajouter
	 * @return commande
	 */
	public static int ajouterCommande(Commande cmd){
		Connection con=ConnexionController.connexion();
		String sql ="INSERT INTO commande (Date, IdClient) VALUES (?,?)";
		int idCommande = 0;
		try {
			PreparedStatement pst = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			pst.setDate(1,java.sql.Date.valueOf(cmd.getDate().toString()));
			pst.setInt(2, cmd.getIdClient());
			pst.executeUpdate();
			
			//Permet de trouver la cle de l'objet, ici l'idCommande
			ResultSet keys = pst.getGeneratedKeys();
			keys.next();
			idCommande = keys.getInt(1);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnexionController.Deconnexion(con);
		return idCommande;
	}
	/**
	 * Supprimer une commande
	 * @param idCommande commande a supprime
	 */
	public static void supprimerCommande(int idCommande){
		Connection con=ConnexionController.connexion();
		String sql= "DELETE FROM commande WHERE commande.IdCommande=?";
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, idCommande);
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		ConnexionController.Deconnexion(con);
	}
	/**
	 * Modifier une commande
	 * @param cmd nouvelle commande 
	 * @param idCommandeAModifier commande a modifier
	 */
	public static void modifierCommande(Commande cmd, int idCommandeAModifier){
		Connection con=ConnexionController.connexion();
		String sql="UPDATE commande SET Date= ?, IdClient= ? WHERE commande.IdCommande = ?";
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setDate(1,java.sql.Date.valueOf(cmd.getDate().toString()));
			pst.setInt(2, cmd.getIdClient());
			pst.setInt(3, idCommandeAModifier);
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		ConnexionController.Deconnexion(con);
	}
	/**
	 * Recuperer tous les commandes de la table
	 * @return liste de commande
	 */
	public static ArrayList<Commande> voirCommande(){
		Connection con=ConnexionController.connexion();
		ArrayList<Commande> listeCommandes = new ArrayList<Commande>();
		String sql = "SELECT * FROM commande";
		try {
			Statement stmt = con.createStatement();
			ResultSet resultats = stmt.executeQuery(sql);
			while (resultats.next()) {
				Commande commande = new Commande();
				commande.setId(resultats.getInt("IdCommande"));	
				commande.setIdClient(resultats.getInt("IdClient"));	
				commande.setDate(LocalDate.parse(resultats.getString("Date")));
				listeCommandes.add(commande);
				}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		ConnexionController.Deconnexion(con);
		return listeCommandes;
	}
	
	/**
	 * Afficher les produits de la commande
	 * @return les produits
	 */
	public static ArrayList<Produit> voirProduitDeLaCommande(Commande commande){
		Connection con=ConnexionController.connexion();
		ArrayList<Produit> listeProduits = new ArrayList<Produit>();
		String sql = "SELECT * FROM commander INNER JOIN produit ON commander.IdProduit = produit.IdProduit WHERE commander.IdCommande =?";
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1,commande.getId());
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
	
	/**
	 * Permet de trouver le client qui a passe la commande
	 * @param commande 
	 * @return le client
	 */
	public static Client trouverLeClientDeLaCommande(Commande commande)
	{
		Connection con=ConnexionController.connexion();
		String sql = "SELECT * FROM client WHERE IdClient = "+commande.getIdClient()+"";
		Client client = new Client();
		try 
		{
			Statement stmt = con.createStatement();
			ResultSet resultats = stmt.executeQuery(sql);
			
			//Sauter l'entete
			resultats.next(); 
			
			client.setIdPersonne(resultats.getInt("IdClient"));	
			client.setNom(resultats.getString("Nom"));
			client.setPrenom(resultats.getString("Prenom"));
			client.setAdresse(resultats.getString("Adresse"));
			client.setVille(resultats.getString("Ville"));
			//client.setBonAchat(resultats.getInt("BonAchat"));
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		ConnexionController.Deconnexion(con);
		return client;
	}
	
	/**
	 * Calculer le nombre de produits appartenant a une commande
	 * @param commande
	 * @return le nombre de produits
	 */
	public static double calculterNombreProduitsCommande(Commande commande)
	{
		Connection con=ConnexionController.connexion();
		String sql = "SELECT SUM(Quantite) FROM commander WHERE IdCommande = "+commande.getId()+"";
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
	 * Calculer le montant d'une commande
	 * @param commande
	 * @return le montant 
	 */
	public static double calculerMontantCommande(Commande commande)
	{
		Connection con=ConnexionController.connexion();
		String sql = "SELECT * FROM commander INNER JOIN produit ON commander.IdProduit = produit.IdProduit WHERE commander.IdCommande ="+commande.getId()+"";
		double montant = 0;
		int quantite = 0;
		double prix = 0;
		
		try {
				Statement stmt = con.createStatement();
				ResultSet resultats = stmt.executeQuery(sql);
				while (resultats.next()) 
				{
					quantite = resultats.getInt("commander.Quantite");
					prix = resultats.getDouble("Prix");	
				
					montant = montant + quantite*prix;
				}
			}catch (SQLException e){
			e.printStackTrace();
			}
		ConnexionController.Deconnexion(con);
		return montant;
	}
}
