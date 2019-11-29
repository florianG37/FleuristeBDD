package controller.queries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


import model.Commande;

public class CommandeController {
	public static void ajouterCommande(Commande cmd){
		Connection con=ConnexionController.connexion();
		String sql ="INSERT INTO commande (Date, IdClient) VALUES (?,?)";
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setDate(1,java.sql.Date.valueOf(cmd.getDate().toString()));
			pst.setInt(2, cmd.getIdClient());
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnexionController.Deconnexion(con);
	}
	public static void supprimerCommande(int idCommande){
		Connection con=ConnexionController.connexion();
		String sql= "DELETE FROM commande WHERE commande.IdCommande";
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, idCommande);
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		ConnexionController.Deconnexion(con);
	}
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
}
