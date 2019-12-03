package controller.queries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import model.Commande;
import model.Reduction;
/**
 * 
 * Requetes Reduction
 *
 */
public class ReductionController 
{
	/**
	 * Ajouter reduction
	 * @param reduction
	 */
	public static void ajouterReduction(Reduction reduction){
		Connection con=ConnexionController.connexion();
		String sql = "INSERT INTO reduction(IdClient, DateDebut,BonAchat) VALUES(?,?,?)";
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, reduction.getIdClient());
			pst.setDate(2, java.sql.Date.valueOf(reduction.getDateDebut().toString()));
			pst.setInt(3, reduction.getBonAchat());
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		ConnexionController.Deconnexion(con);
	}
	/**
	 * Finir une reduction
	 * @param idClient client
	 */
	public static void finReduction(int idClient)
	{
		Connection con=ConnexionController.connexion();
		
		String sql="UPDATE reduction SET DateFin =? WHERE IdClient ="+idClient+" AND DateFin is null";
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setDate(1, java.sql.Date.valueOf(LocalDate.now().minusDays(1).toString()));
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
				
		ConnexionController.Deconnexion(con);	
	}
	
	/**
	 * Trouver la reduction applique a  une commande
	 * @param commande
	 * @return la reduction
	 */
	public static int reductionDeLaCommande(Commande commande)
	{
		Connection con=ConnexionController.connexion();
		String sql = "SELECT * FROM reduction WHERE IdClient ="+commande.getIdClient()+" AND DateFin is null";
		int reduction = 0;
		try {
				Statement stmt = con.createStatement();
				ResultSet resultats = stmt.executeQuery(sql);
				
				//Une seule ligne de resultat
				resultats.next();
				LocalDate dateDebut = resultats.getDate("DateDebut").toLocalDate();
				//Si la date de debut de reduction est avant la date de la commande ou est la même date
				if(dateDebut.isBefore(commande.getDate()) || dateDebut.isEqual(commande.getDate()))
				{
					reduction = resultats.getInt("BonAchat");
				}
				else
				{
					String sql2 = "SELECT * FROM reduction WHERE IdClient ="+commande.getIdClient()+"";
					resultats = stmt.executeQuery(sql2);
					while(resultats.next())
					{
						LocalDate dateDebut2 = resultats.getDate("DateDebut").toLocalDate();
						LocalDate dateFin2 = resultats.getDate("DateFin").toLocalDate();
						LocalDate dateCmd = commande.getDate();
						
						if(dateCmd.isEqual(dateFin2) || dateCmd.isEqual(dateDebut2))
						{
							return resultats.getInt("BonAchat");
						}
						else 
						{
							if(dateCmd.isBefore(dateFin2) && dateCmd.isAfter(dateDebut2))
							{
								return resultats.getInt("BonAchat");
							}
						}
					}
				}	
			}catch (SQLException e){
			e.printStackTrace();
			}
		ConnexionController.Deconnexion(con);
		return reduction;
	}
	
	/**
	 * Trouver la reduction en cours du client
	 * @param idClient
	 * @return la reduction
	 */
	public static int reductionEnCours(int idClient)
	{
		Connection con=ConnexionController.connexion();
		String sql="SELECT * FROM reduction WHERE IdClient ="+idClient+" AND DateFin is null";
		int reduction = 0;
		try {
			Statement stmt = con.createStatement();
			ResultSet resultats = stmt.executeQuery(sql);
			
			resultats.next();
			reduction = resultats.getInt("BonAchat");
		}catch (SQLException e) {
			e.printStackTrace();
		}
		ConnexionController.Deconnexion(con);
		return reduction;
	}
	/**
	 * Trouver la date de la reduction en cours du client
	 * @param idClient
	 * @return la reduction
	 */
	public static LocalDate dateReductionEnCours(int idClient)
	{
		Connection con=ConnexionController.connexion();
		String sql="SELECT * FROM reduction WHERE IdClient ="+idClient+" AND DateFin is null";
		LocalDate dateDebut = LocalDate.now();
		try {
			Statement stmt = con.createStatement();
			ResultSet resultats = stmt.executeQuery(sql);
			
			resultats.next();
			dateDebut = resultats.getDate("DateDebut").toLocalDate();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		ConnexionController.Deconnexion(con);
		return dateDebut;
	}
}
