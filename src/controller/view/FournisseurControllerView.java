package controller.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import controller.queries.FournisseurController;
import controller.view.table.FournisseurTableTemplate;
import model.Fournisseur;
import view.AffilierFournisseurProduitView;
import view.FournisseurView;

public class FournisseurControllerView 
{
	/**
	 * Constructeur
	 */
	public FournisseurControllerView()
	{
		FournisseurView.ajouterFournisseurListener(new AjouterFournisseurListener());  
		FournisseurView.supprimerFournisseurListener(new SupprimerFournisseurListener());
		FournisseurView.modifierFournisseurListener(new ModifierFournisseurListener());
		FournisseurView.affilierListener(new AffilierListener());
		FournisseurView.filterListener(new FilterListener());
		FournisseurView.clearFilterListener(new ClearFilterListener());
	}
	/**
	 * 
	 * ajouter Fournisseur
	 *
	 */
	class AjouterFournisseurListener implements ActionListener
	{
		private JTextField nom = new JTextField();
		private JTextField prenom = new JTextField();
		private JTextField adresse = new JTextField();
		private JTextField ville = new JTextField();
		private FournisseurTableTemplate modele = FournisseurView.getModele();
		public void actionPerformed(ActionEvent e)
		{
			Object[] fieldsAdd = {" Nom :", nom, 
					  "Prenom :", prenom,
					  "Adresse :",	adresse,
					  "Ville :", ville,
					  };
			int option = JOptionPane.showConfirmDialog(null, fieldsAdd, "Nouveau fournisseur", JOptionPane.OK_CANCEL_OPTION);
			if (option == JOptionPane.OK_OPTION) 
			{
				String nomF = nom.getText();
				String prenomF =prenom.getText();
				String adresseF = adresse.getText();
				String villeF = ville.getText();
				
						
				Fournisseur fournisseur = new Fournisseur(nomF,prenomF,adresseF,villeF);
						
				FournisseurController.ajouterFournisseur(fournisseur);
						
				modele.actualiserFournisseurs();
						
				nom.setText(null);
				prenom.setText(null);
				adresse.setText(null);
				ville.setText(null);
						
				}
		}
	}
	/**
	 * 
	 * modifier Fournisseur
	 *
	 */
	class ModifierFournisseurListener implements ActionListener
	{
		private JTextField nom = new JTextField();
		private JTextField prenom = new JTextField();
		private JTextField adresse = new JTextField();
		private JTextField ville = new JTextField();
		private FournisseurTableTemplate modele = FournisseurView.getModele();
		private JTable table = FournisseurView.getTable();
		public void actionPerformed(ActionEvent e)
		{
			int ligneSelectionee = table.getSelectedRow();
			
			if(ligneSelectionee != -1)
			{
				ligneSelectionee = table.getRowSorter().convertRowIndexToModel(ligneSelectionee);
				Fournisseur fournisseur = modele.returnFournisseur(ligneSelectionee);
				int idFournisseurAModifier = fournisseur.getIdPersonne();
				Object[] fieldsAdd = {" Nom :", nom, 
						  "Prenom :", prenom,
						  "Adresse :",	adresse,
						  "Ville :", ville, 
						  };
				nom.setText(fournisseur.getNom());
				prenom.setText(fournisseur.getPrenom());
				adresse.setText(fournisseur.getAdresse());
				ville.setText(fournisseur.getVille());
				int option = JOptionPane.showConfirmDialog(null, fieldsAdd, "modifier fournisseur", JOptionPane.OK_CANCEL_OPTION);
				if (option == JOptionPane.OK_OPTION) 
				{
					String nomF = nom.getText();
					String prenomF =prenom.getText();
					String adresseF = adresse.getText();
					String villeF = ville.getText();
					
					Fournisseur fournisseur2 = new Fournisseur(nomF,prenomF,adresseF,villeF);
					
					FournisseurController.modifierFournisseur(fournisseur2, idFournisseurAModifier);		
					modele.actualiserFournisseurs();

					nom.setText(null);
					prenom.setText(null);
					adresse.setText(null);
					ville.setText(null);	
					}
			}else{
				JOptionPane.showMessageDialog(null, "Selectionner une ligne", "Erreur", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	/**
	 * 
	 * supprimer Fournisseur
	 *
	 */
	class SupprimerFournisseurListener implements ActionListener
	{
		private JTable table = FournisseurView.getTable();
		private FournisseurTableTemplate modele = FournisseurView.getModele();
		
		public void actionPerformed(ActionEvent e)
		{
			int ligneSelectionee = table.getSelectedRow();
			if(ligneSelectionee != -1)
			{
				ligneSelectionee = table.getRowSorter().convertRowIndexToModel(ligneSelectionee);
				Fournisseur fournisseur = modele.returnFournisseur(ligneSelectionee);
				
				FournisseurController.supprimerFournisseur(fournisseur.getIdPersonne());
				
				modele.actualiserFournisseurs();
			}else{
				JOptionPane.showMessageDialog(null, "Selectionner une ligne", "Erreur", JOptionPane.ERROR_MESSAGE);
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
		private TableRowSorter<TableModel> sorter = FournisseurView.getSorter();
		@Override
		public void actionPerformed(ActionEvent arg0) {
			String regex = JOptionPane.showInputDialog("Filter by : ");
	        sorter.setRowFilter(RowFilter.regexFilter("(?i)"+regex, 0, 1, 2, 3));
			
		}
		
	}
	/**
	 * 
	 * Enlever filtre
	 *
	 */
	class ClearFilterListener implements ActionListener
	{
		private TableRowSorter<TableModel> sorter = FournisseurView.getSorter();
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(sorter != null)
			{
				sorter.setRowFilter(null);
			}
			
		}
		
	}
	/**
	 * 
	 * affilier fournisseur /produit
	 *
	 */
	class AffilierListener implements ActionListener
	{
		private JTable table = FournisseurView.getTable();
		private FournisseurTableTemplate modele = FournisseurView.getModele();
		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			int ligneSelectionee = table.getSelectedRow();
			
			if(ligneSelectionee != -1)
			{
				ligneSelectionee = table.getRowSorter().convertRowIndexToModel(ligneSelectionee);
				Fournisseur fournisseur = modele.returnFournisseur(ligneSelectionee);
				new AffilierFournisseurProduitView(fournisseur.getIdPersonne());
			}else{
				JOptionPane.showMessageDialog(null, "Selectionner une ligne", "Erreur", JOptionPane.ERROR_MESSAGE);
			}
		}
		
	}
}
