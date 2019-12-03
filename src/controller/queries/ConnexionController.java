package controller.queries;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 * 
 * Gestion de la connection a la BDD
 *
 */
public class ConnexionController {
	/** 
	 * Ouvrir une connexion
	 * @return une connexion ouverte
	 */
	public static Connection connexion(){
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
		} 
		catch (ClassNotFoundException ex1) {
			System.out.println("Pilote non trouve!");
			System.exit(1);
		}
		try {
			Connection con=DriverManager.getConnection(
			"jdbc:mysql://localhost:3306/fleuriste?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC",
			"root","");
			return con;
			
		} catch (SQLException ex2) {
			System.out.println("Erreur JDBC: "+ex2);
			System.exit(1);
			}
		return null;
		
	}
	/**
	 * Fermer une connexion
	 * @param con une connexion ouverte
	 */
	public static void Deconnexion(Connection con){
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}
}
