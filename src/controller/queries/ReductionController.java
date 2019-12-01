package controller.queries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.Reduction;

public class ReductionController 
{
	public static void ajouterReduction(Reduction reduction){
		Connection con=ConnexionController.connexion();
		String sql = "INSERT INTO reduction(IdClient, DateFin, DateDebut,BonAchat) VALUES(?,?,?,?)";
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, reduction.getIdClient());
			pst.setDate(2,java.sql.Date.valueOf(reduction.getDateFin().toString()));
			pst.setDate(3, java.sql.Date.valueOf(reduction.getDateDebut().toString()));
			pst.setInt(4, reduction.getBonAchat());
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
				
		ConnexionController.Deconnexion(con);
	}
}
