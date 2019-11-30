package controller.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTable;

import controller.queries.ClientController;
import controller.queries.CommandeController;
import controller.view.table.CommandeTableTemplate;
import model.Commande;
import view.CommandeView;

public class CommandeControllerView 
{
	public CommandeControllerView()
	{
		CommandeView.ajouterCommandeListener(new AjouterCommandeListener());  
		CommandeView.supprimerCommandeListener(new SupprimerCommandeListener());
		CommandeView.modifierCommandeListener(new ModifierCommandeListener());
	}
	
	class AjouterCommandeListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			System.out.println("J'ai cliqué sur ajouter une commande");
		}
	}
	
	class ModifierCommandeListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			System.out.println("J'ai cliqué sur modifier commande");
		}
	}
	
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
