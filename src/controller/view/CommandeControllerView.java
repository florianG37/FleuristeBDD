package controller.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTable;

import controller.queries.CommandeController;
import controller.view.table.CommandeTableTemplate;
import controller.view.table.VoirCommandeTableTemplate;
import model.Commande;
import view.AjouterCommandeView1;
import view.CommandeView;
import view.VoirCommandeView;

public class CommandeControllerView 
{
	/**
	 * Constructeur
	 */
	public CommandeControllerView()
	{
		CommandeView.ajouterCommandeListener(new AjouterCommandeListener());  
		CommandeView.supprimerCommandeListener(new SupprimerCommandeListener());
		CommandeView.voirCommandeListener(new VoirCommandeListener());
	}
	/**
	 * 
	 * ajouter Commande
	 *
	 */
	class AjouterCommandeListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			new AjouterCommandeView1();
		}
	}
	/**
	 * 
	 * voir Commande
	 *
	 */
	class VoirCommandeListener implements ActionListener
	{
		private JTable table = CommandeView.getTable();
		private CommandeTableTemplate modele = CommandeView.getModele();
		
		public void actionPerformed(ActionEvent e)
		{
			
			int ligneSelectionnee = table.getSelectedRow();
			if(ligneSelectionnee != -1)
			{
				Commande commande = modele.returnCommande(ligneSelectionnee);
				
				//Créé le modele de table a partir de la commande
				VoirCommandeTableTemplate modeleVoir = new VoirCommandeTableTemplate(commande);
				new VoirCommandeView(modeleVoir);
			}
		}
	}
	/**
	 *  
	 * supprimer Commande
	 *
	 */
	class SupprimerCommandeListener implements ActionListener
	{
		private JTable table = CommandeView.getTable();
		private CommandeTableTemplate modele = CommandeView.getModele();
		
		public void actionPerformed(ActionEvent e)
		{
			int ligneSelectionee = table.getSelectedRow();
			//Si il y a une ligne selectionnee
			if(ligneSelectionee != -1)
			{
				Commande commande = modele.returnCommande(ligneSelectionee);
				
				CommandeController.supprimerCommande(commande.getId());
				
				modele.actualiserCommandes();
			}
		}
	}
}
