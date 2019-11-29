package controller.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTable;

import controller.queries.FournisseurController;
import controller.queries.ProduitController;
import controller.view.table.FournisseurTableTemplate;
import model.Client;
import model.Fournisseur;
import view.ClientView;
import view.FournisseurView;

public class FournisseurControllerView 
{
	public FournisseurControllerView()
	{
		FournisseurView.ajouterFournisseurListener(new AjouterFournisseurListener());  
		FournisseurView.supprimerFournisseurListener(new SupprimerFournisseurListener());
		FournisseurView.modifierFournisseurListener(new ModifierFournisseurListener());
	}
	
	class AjouterFournisseurListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			System.out.println("J'ai cliqué sur ajouter fournisseur");
		}
	}
	
	class ModifierFournisseurListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			System.out.println("J'ai cliqué sur modifier fournisseur");
		}
	}
	
	class SupprimerFournisseurListener implements ActionListener
	{
		private JTable table = FournisseurView.getTable();
		private FournisseurTableTemplate modele = FournisseurView.getModele();
		
		public void actionPerformed(ActionEvent e)
		{
			int ligneSelectionee = table.getSelectedRow();
			//Si il y a une ligne selectionnee
			if(ligneSelectionee != -1)
			{
				Fournisseur fournisseur = modele.returnFournisseur(ligneSelectionee);
				
				FournisseurController.supprimerFournisseur(fournisseur.getIdPersonne());
				
				modele.actualiserFournisseurs(ligneSelectionee);
			}
		}
	}
}
