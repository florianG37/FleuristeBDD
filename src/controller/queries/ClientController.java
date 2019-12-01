package controller.queries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.Client;

public class ClientController {
	/**
	 * Ajouter un client dans la BDD, et retourne son idClient
	 * @param client
	 * @return idClient
	 */
	public static int ajouterClient(Client client){
		Connection con=ConnexionController.connexion();
		String sql ="INSERT INTO client ( Nom, Prenom, Adresse, Ville) VALUES (?,?,?,?)";
		int idClient = 0;
		try {
			PreparedStatement pst = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			pst.setString(1, client.getNom());
			pst.setString(2, client.getPrenom());
			pst.setString(3, client.getAdresse());
			pst.setString(4, client.getVille());
			pst.executeUpdate();
			
			//Permet de trouver la cle de l'objet, ici l'idClient
			ResultSet keys = pst.getGeneratedKeys();
			keys.next();
			idClient = keys.getInt(1);
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnexionController.Deconnexion(con);
		return idClient;
	}
	
	public static void supprimerClient(int IdClient){
		Connection con=ConnexionController.connexion();
		String sql= "DELETE FROM client WHERE client.IdClient = ?";
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, IdClient);
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		ConnexionController.Deconnexion(con);
	}
	
	public static void modifierClient(Client client, int IdClientAModifier){
		Connection con=ConnexionController.connexion();
		String sql="UPDATE client SET Nom = ?, Prenom = ?, Adresse =? , Ville =? WHERE client.IdClient =?";
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, client.getNom());
			pst.setString(2, client.getPrenom());
			pst.setString(3, client.getAdresse());
			pst.setString(4, client.getVille());
			pst.setInt(5, IdClientAModifier);
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
				
		ConnexionController.Deconnexion(con);	
	}
	
	public static ArrayList<Client> voirClient(){
		Connection con=ConnexionController.connexion();
		ArrayList<Client> listeClients = new ArrayList<Client>();
		String sql = "SELECT * FROM client";
		try {
			Statement stmt = con.createStatement();
			ResultSet resultats = stmt.executeQuery(sql);
			while (resultats.next()) {
				Client client = new Client();
				client.setIdPersonne(resultats.getInt("IdClient"));	
				client.setNom(resultats.getString("Nom"));
				client.setPrenom(resultats.getString("Prenom"));
				client.setAdresse(resultats.getString("Adresse"));
				client.setVille(resultats.getString("Ville"));
				listeClients.add(client);
				}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		ConnexionController.Deconnexion(con);
		return listeClients;
	}
}
