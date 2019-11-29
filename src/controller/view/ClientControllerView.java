package controller.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTable;

import controller.queries.ClientController;
import controller.queries.ProduitController;
import controller.view.table.ClientTableTemplate;
import model.Client;
import view.ClientView;

public class ClientControllerView 
{
	public ClientControllerView()
	{
		ClientView.ajouterClientListener(new AjouterClientListener());  
		ClientView.supprimerClientListener(new SupprimerClientListener());
		ClientView.modifierClientListener(new ModifierClientListener());
	}
	
	class AjouterClientListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			System.out.println("J'ai cliqué sur ajouter client");
		}
	}
	
	class ModifierClientListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			System.out.println("J'ai cliqué sur modifier client");
		}
	}
	
	class SupprimerClientListener implements ActionListener
	{
		private JTable table = ClientView.getTable();
		private ClientTableTemplate modele = ClientView.getModele();
		
		public void actionPerformed(ActionEvent e)
		{
			int ligneSelectionee = table.getSelectedRow();
			//Si il y a une ligne selectionnee
			if(ligneSelectionee != -1)
			{
				Client client = modele.returnClient(ligneSelectionee);
				
				ClientController.supprimerClient(client.getIdPersonne());
				
				modele.actualiserClients(ligneSelectionee);
			}
		}
	}
}
