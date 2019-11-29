package controller.queries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.Client;

public class ClientController {
	public static void ajouterClient(Client client){
		Connection con=ConnexionController.connexion();
		String sql ="INSERT INTO client ( Nom, Prenom, Adresse, Ville, BonAchat) VALUES (?,?,?,?,?)";
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, client.getNom());
			pst.setString(2, client.getPrenom());
			pst.setString(3, client.getAdresse());
			pst.setString(4, client.getVille());
			pst.setInt(5, client.getBonAchat());
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnexionController.Deconnexion(con);
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
		String sql="UPDATE client SET Nom = ?, Prenom = ?, Adresse =? , Ville =?, BonAchat=? WHERE client.IdClient =?";
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, client.getNom());
			pst.setString(2, client.getPrenom());
			pst.setString(3, client.getAdresse());
			pst.setString(4, client.getVille());
			pst.setInt(5, client.getBonAchat());
			pst.setInt(6, IdClientAModifier);
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
				
		ConnexionController.Deconnexion(con);	
	}
}
