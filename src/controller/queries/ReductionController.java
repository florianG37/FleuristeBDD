package controller.queries;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

import model.Reduction;

public class ReductionController 
{
	public static void ajouterReduction(Reduction reduction){
		Connection con=ConnexionController.connexion();
		String sql = "INSERT INTO reduction(IdClient, DateDebut,BonAchat) VALUES(?,?,?)";
		System.out.println(reduction.getIdClient());
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, reduction.getIdClient());
			//pst.setDate(2,java.sql.Date.valueOf(reduction.getDateFin().toString()));
			pst.setDate(2, java.sql.Date.valueOf(reduction.getDateDebut().toString()));
			pst.setInt(3, reduction.getBonAchat());
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		ConnexionController.Deconnexion(con);
	}
	
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
}
