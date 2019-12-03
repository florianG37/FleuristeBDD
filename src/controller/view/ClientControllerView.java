package controller.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import controller.queries.ClientController;
import controller.queries.ReductionController;
import controller.view.table.ClientTableTemplate;
import model.Client;
import model.Reduction;
import view.ClientView;

public class ClientControllerView 
{
	/**
	 * Constructeur
	 */
	public ClientControllerView()
	{
		ClientView.ajouterClientListener(new AjouterClientListener());  
		ClientView.supprimerClientListener(new SupprimerClientListener());
		ClientView.modifierClientListener(new ModifierClientListener());
		ClientView.filterListener(new FilterListener());
		ClientView.clearFilterListener(new ClearFilterListener());

	}
	/**
	 * 
	 * ajouter Client
	 *
	 */
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
					
						System.out.println(bonAchatC);
							if(bonAchatC < 0 || bonAchatC >100){
								System.out.println("dans le if");
								throw new Exception();
							} 
						
						Client client = new Client(nomC,prenomC,adresseC,villeC,bonAchatC);
						
						int idClient = ClientController.ajouterClient(client);
						Reduction reduction = new Reduction(idClient,bonAchatC);
						ReductionController.ajouterReduction(reduction);
						
						modele.actualiserClients();
						
						nom.setText(null);
						prenom.setText(null);
						adresse.setText(null);
						ville.setText(null);
						bonAchat.setText(null);
						
				}
			}catch (Exception e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "Nombre Interdit", "Erreur", JOptionPane.ERROR_MESSAGE);
				}
			
		}
	}
	/**
	 * 
	 * modifier Client
	 *
	 */
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
			
			if(ligneSelectionee != -1)
			{
				ligneSelectionee = table.getRowSorter().convertRowIndexToModel(ligneSelectionee);
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
							
							//Le bon d'achat a ete modifie
							if(bonAchatC != client.getBonAchat())
							{
								if(LocalDate.now().minusDays(1).equals(ReductionController.dateReductionEnCours(client.getIdPersonne()))){
									JOptionPane.showMessageDialog(null, "Une modification de date par jour", "Erreur", JOptionPane.ERROR_MESSAGE);
								}else{
									ReductionController.finReduction(client.getIdPersonne());
									Reduction reduction = new Reduction(client.getIdPersonne(),bonAchatC);
									ReductionController.ajouterReduction(reduction);
								}
								
							}
							
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
	/**
	 * 
	 * supprimer Client
	 *
	 */
	class SupprimerClientListener implements ActionListener
	{
		private JTable table = ClientView.getTable();
		private ClientTableTemplate modele = ClientView.getModele();
		
		public void actionPerformed(ActionEvent e)
		{
			int ligneSelectionee = table.getSelectedRow();
			if(ligneSelectionee != -1)
			{
				ligneSelectionee = table.getRowSorter().convertRowIndexToModel(ligneSelectionee);
				Client client = modele.returnClient(ligneSelectionee);
				
				ClientController.supprimerClient(client.getIdPersonne());
				
				modele.actualiserClients();
			}
		}
	}
	/**
	 * 
	 * Activer filtre
	 *
	 */
	class FilterListener implements ActionListener
	{
		private TableRowSorter<TableModel> sorter = ClientView.getSorter();
		@Override
		public void actionPerformed(ActionEvent arg0) {
			String regex = JOptionPane.showInputDialog("Filter by : ");
	        sorter.setRowFilter(RowFilter.regexFilter("(?i)"+regex, 0, 1, 2, 3,4));
			
		}
		
	}
	/**
	 * 
	 * Eblever filtre
	 *
	 */
	class ClearFilterListener implements ActionListener
	{
		private TableRowSorter<TableModel> sorter = ClientView.getSorter();
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(sorter != null)
			{
				sorter.setRowFilter(null);
			}
			
		}
		
	}
}
