package controller.queries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FournirController {
	public static void associerFournisseurAProduit(int idFournisseur, int idProduit){
		Connection con=ConnexionController.connexion();
		String sql ="INSERT INTO fournir (IdFournisseur, IdProduit) VALUES (?,?)";
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, idFournisseur);
			pst.setInt(2, idProduit);
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnexionController.Deconnexion(con);
	}
	public static void desassocierFournisseurAProduit(int idFournisseur, int idProduit){
		Connection con=ConnexionController.connexion();
		String sql= "DELETE FROM fournir WHERE fournir.IdFournisseur=? AND fournir.IdProduit=?";
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, idFournisseur);
			pst.setInt(2, idProduit);
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnexionController.Deconnexion(con);
	}
}
