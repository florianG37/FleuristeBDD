package controller.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import controller.queries.ClientController;
import controller.queries.ProduitController;
import controller.view.table.ClientTableTemplate;
import model.Categorie;
import model.Client;
import model.Produit;
import view.ClientView;
import view.ProduitView;

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
		private JTextField nom = new JTextField();
		private JTextField prenom = new JTextField();
		private JTextField adresse = new JTextField();
		private JTextField ville = new JTextField();
		private JTextField bonAchat = new JTextField();
		private ClientTableTemplate modele = ClientView.getModele();

		
		public void actionPerformed(ActionEvent e)
		{
			Object[] fieldsAdd = {" Nom :", nom, 
					  "Prenom :", prenom,
					  "Adresse :",	adresse,
					  "Ville :", ville, 
					  "Bon Achat :", bonAchat,
					  };
			int option = JOptionPane.showConfirmDialog(null, fieldsAdd, "Nouveau client", JOptionPane.OK_CANCEL_OPTION);
			try {
				if (option == JOptionPane.OK_OPTION) 
				{
						String nomC = nom.getText();
						String prenomC =prenom.getText();
						String adresseC = adresse.getText();
						String villeC = ville.getText();
						int bonAchatC = Integer.parseInt(bonAchat.getText());
					
							if(bonAchatC < 0 || bonAchatC >100){
							
								throw new Exception();
							} 
						
						
						Client client = new Client(nomC,prenomC,adresseC,villeC,bonAchatC);
						
						ClientController.ajouterClient(client);
						
						modele.actualiserClients();
						
						nom.setText(null);
						prenom.setText(null);
						adresse.setText(null);
						ville.setText(null);
						bonAchat.setText(null);
						
				}
			}catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Nombre Interdit", "Erreur", JOptionPane.ERROR_MESSAGE);
				}
			
		}
	}
	
	class ModifierClientListener implements ActionListener
	{
		private JTextField nom = new JTextField();
		private JTextField prenom = new JTextField();
		private JTextField adresse = new JTextField();
		private JTextField ville = new JTextField();
		private JTextField bonAchat = new JTextField();
		private JTable table = ClientView.getTable();
		private ClientTableTemplate modele = ClientView.getModele();
		public void actionPerformed(ActionEvent e)
		{
			int ligneSelectionee = table.getSelectedRow();
			//Si il y a une ligne selectionnee
			if(ligneSelectionee != -1)
			{
				Client client = modele.returnClient(ligneSelectionee);
				int idClientAModifier = client.getIdPersonne();
				Object[] fieldsAdd = {" Nom :", nom, 
						  "Prenom :", prenom,
						  "Adresse :",	adresse,
						  "Ville :", ville, 
						  "Bon Achat :", bonAchat,
						  };
				nom.setText(client.getNom());
				prenom.setText(client.getPrenom());
				adresse.setText(client.getAdresse());
				ville.setText(client.getVille());
				bonAchat.setText(client.getBonAchat()+"");
				int option = JOptionPane.showConfirmDialog(null, fieldsAdd, "modifier client", JOptionPane.OK_CANCEL_OPTION);
				try {
					if (option == JOptionPane.OK_OPTION) 
					{
							String nomC = nom.getText();
							String prenomC =prenom.getText();
							String adresseC = adresse.getText();
							String villeC = ville.getText();
							int bonAchatC = Integer.parseInt(bonAchat.getText());
						
								if(bonAchatC < 0 || bonAchatC >100){
								
									throw new Exception();
								} 
							
							
							Client client2 = new Client(nomC,prenomC,adresseC,villeC,bonAchatC);
							
							ClientController.modifierClient(client2, idClientAModifier);
							
							modele.actualiserClients();
							
							nom.setText(null);
							prenom.setText(null);
							adresse.setText(null);
							ville.setText(null);
							bonAchat.setText(null);
							
					}
				}catch (Exception e1) {
						JOptionPane.showMessageDialog(null, "Nombre Interdit", "Erreur", JOptionPane.ERROR_MESSAGE);
					}
			}else{
				JOptionPane.showMessageDialog(null, "Selectionner une ligne", "Erreur", JOptionPane.ERROR_MESSAGE);
			}
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
				
				modele.actualiserClients();
			}
		}
	}
}
