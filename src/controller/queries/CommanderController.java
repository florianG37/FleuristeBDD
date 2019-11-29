package controller.queries;

import java.sql.Connection;

import model.Commande;

public class CommanderController {

	public static void ajouterCommander(Commande cmd){
		Connection con=ConnexionController.connexion();
		ConnexionController.Deconnexion(con);
	}
}
