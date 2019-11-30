package controller.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import controller.queries.ProduitController;
import controller.view.table.ProduitTableTemplate;
import model.Produit;
import view.ProduitView;

public class ProduitControllerView 
{
	public ProduitControllerView()
	{
		ProduitView.ajouterProduitListener(new AjouterProduitListener());  
		ProduitView.supprimerProduitListener(new SupprimerProduitListener());
		ProduitView.modifierProduitListener(new ModifierProduitListener());
	}
	
	class AjouterProduitListener implements ActionListener
	{
		private JTextField nom = new JTextField();
		private JTextField espece = new JTextField();
		private JTextField prix = new JTextField();
		private JTextField quantite = new JTextField();
		
		public void actionPerformed(ActionEvent e)
		{
			Object[] fieldsAdd = {" Nom :", nom, 
					  "Espece :",	espece,
					  "Prix unitaire :", prix, 
					  "Quantite :", quantite,
					  };
			
			int option = JOptionPane.showConfirmDialog(null, fieldsAdd, "Nouveau produit", JOptionPane.OK_CANCEL_OPTION);
			if (option == JOptionPane.OK_OPTION) 
			{
						//Produit produit = new Produit(nom.getText(),);
						
						nom.setText(null);
						espece.setText(null);
						prix.setText(null);
						quantite.setText(null);
						
				}
			}
	}
	
	class ModifierProduitListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			System.out.println("J'ai cliqué sur modifier produit");
		}
	}
	
	class SupprimerProduitListener implements ActionListener
	{
		private JTable table = ProduitView.getTable();
		private ProduitTableTemplate modele = ProduitView.getModele();
		
		public void actionPerformed(ActionEvent e)
		{
			int ligneSelectionee = table.getSelectedRow();
			//Si il y a une ligne selectionnee
			if(ligneSelectionee != -1)
			{
				Produit produit = modele.returnProduit(ligneSelectionee);
				
				ProduitController.supprimerProduit(produit.getIdProduit());
				
				modele.actualiserProduits(ligneSelectionee);
			}
		}
	}
}
