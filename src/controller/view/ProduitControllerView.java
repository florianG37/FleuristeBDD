package controller.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import controller.queries.ProduitController;
import controller.view.table.ProduitTableTemplate;
import model.Categorie;
import model.Produit;
import view.AlerteView;
import view.ProduitView;

public class ProduitControllerView 
{
	/**
	 * Constructeur
	 */
	public ProduitControllerView()
	{
		ProduitView.ajouterProduitListener(new AjouterProduitListener());  
		ProduitView.supprimerProduitListener(new SupprimerProduitListener());
		ProduitView.modifierProduitListener(new ModifierProduitListener());
		ProduitView.alerteListener(new AlerteListener());
		ProduitView.filterListener(new FilterListener());
		ProduitView.clearFilterListener(new ClearFilterListener());
	}
	/**
	 * 
	 * ajouter Produit
	 *
	 */
	class AjouterProduitListener implements ActionListener
	{
		private JTextField nom = new JTextField();
		private JTextField espece = new JTextField();
		private JTextField prix = new JTextField();
		private JTextField quantite = new JTextField();
		private JComboBox<Categorie> categorie= new  JComboBox<Categorie>(Categorie.values());
		private ProduitTableTemplate modele = ProduitView.getModele();
		
		public void actionPerformed(ActionEvent e)
		{
			Object[] fieldsAdd = {" Nom :", nom, 
					  "Categorie :", categorie,
					  "Espece :",	espece,
					  "Prix unitaire :", prix, 
					  "Quantite :", quantite,
					  };
			
			int option = JOptionPane.showConfirmDialog(null, fieldsAdd, "Nouveau produit", JOptionPane.OK_CANCEL_OPTION);
			try {
				if (option == JOptionPane.OK_OPTION) 
				{
						String nomP = nom.getText();
						Categorie catP =categorie.getItemAt(categorie.getSelectedIndex());
						String espP = espece.getText();
						Double prixP = Double.valueOf(prix.getText().replace(",", "."));
						int quantiteP = Integer.parseInt(quantite.getText());
					
							if(prixP < 0 || quantiteP < 0){
							
								throw new Exception();
							} 
						
						
						Produit prod = new Produit(nomP,catP,espP,prixP,quantiteP);
						
						ProduitController.ajouterProduit(prod);
						
						modele.actualiserProduits();
						
						nom.setText(null);
						espece.setText(null);
						prix.setText(null);
						quantite.setText(null);
						
				}
			}catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Nombre Negatif Interdit", "Erreur", JOptionPane.ERROR_MESSAGE);
				}
			}
			
	}
	/**
	 * 
	 * modifier Produit
	 *
	 */
	class ModifierProduitListener implements ActionListener
	{
		private JTextField nom = new JTextField();
		private JTextField espece = new JTextField();
		private JTextField prix = new JTextField();
		private JTextField quantite = new JTextField();
		private JComboBox<Categorie> categorie= new  JComboBox<Categorie>(Categorie.values());
		private JTable table = ProduitView.getTable();
		private ProduitTableTemplate modele = ProduitView.getModele();
		public void actionPerformed(ActionEvent e)
		{
			
			int ligneSelectionee = table.getSelectedRow();
			if(ligneSelectionee != -1)
			{
				ligneSelectionee = table.getRowSorter().convertRowIndexToModel(ligneSelectionee);
				Produit produit = modele.returnProduit(ligneSelectionee);
				int idProduitAModifier= produit.getIdProduit();
				Object[] fieldsAdd = {" Nom :", nom, 
						  "Categorie :", categorie,
						  "Espece :",	espece,
						  "Prix unitaire :", prix, 
						  "Quantite :", quantite,
						  };
				nom.setText(produit.getNom());
				espece.setText(produit.getEspece());
				categorie.setSelectedItem(produit.getCategorie());
				prix.setText(produit.getPrix()+"");
				quantite.setText(produit.getStock()+"");
				int option = JOptionPane.showConfirmDialog(null, fieldsAdd, "Modifier produit", JOptionPane.OK_CANCEL_OPTION);
				try {
					if (option == JOptionPane.OK_OPTION) 
					{
							String nomP = nom.getText();
							Categorie catP =categorie.getItemAt(categorie.getSelectedIndex());
							System.out.println(catP);
							String espP = espece.getText();
							Double prixP = Double.valueOf(prix.getText().replace(",", "."));
							int quantiteP = Integer.parseInt(quantite.getText());
						
								if(prixP < 0 || quantiteP < 0){
								
									throw new Exception();
								} 
							
							nom.setText(null);
							espece.setText(null);
							prix.setText(null);
							quantite.setText(null);
							Produit prod = new Produit(nomP,catP,espP,prixP,quantiteP);
							ProduitController.modifierProduit(prod, idProduitAModifier);
							modele.actualiserProduits();
							
					}
				}catch (Exception e1) {
						JOptionPane.showMessageDialog(null, "Nombre Negatif Interdit", "Erreur", JOptionPane.ERROR_MESSAGE);
					}
								
				
			}else{
				JOptionPane.showMessageDialog(null, "Selectionner une ligne", "Erreur", JOptionPane.ERROR_MESSAGE);
			}

	
			}
		}
	/**
	 * 
	 * supprimer Produit
	 *
	 */
	class SupprimerProduitListener implements ActionListener
	{
		private JTable table = ProduitView.getTable();
		private ProduitTableTemplate modele = ProduitView.getModele();
		
		public void actionPerformed(ActionEvent e)
		{
			int ligneSelectionee = table.getSelectedRow();
			if(ligneSelectionee != -1)
			{
				ligneSelectionee = table.getRowSorter().convertRowIndexToModel(ligneSelectionee);
				Produit produit = modele.returnProduit(ligneSelectionee);
				
				ProduitController.supprimerProduit(produit.getIdProduit());
				
				modele.actualiserProduits();
			}
		}
	}
	/**
	 * 
	 * alerte
	 *
	 */
	class AlerteListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			new AlerteView();
			
		}
		
	}
	/**
	 * 
	 * Activer filtre
	 *
	 */
	class FilterListener implements ActionListener
	{
		private TableRowSorter<TableModel> sorter = ProduitView.getSorter();
		@Override
		public void actionPerformed(ActionEvent arg0) {
			String regex = JOptionPane.showInputDialog("Filter by : ");
	        sorter.setRowFilter(RowFilter.regexFilter("(?i)"+regex, 0, 1, 2, 3,4,5));
			
		}
		
	}
	/**
	 * 
	 * Enlever filtre
	 *
	 */
	class ClearFilterListener implements ActionListener
	{
		private TableRowSorter<TableModel> sorter = ProduitView.getSorter();
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(sorter != null)
			{
				sorter.setRowFilter(null);
			}
			
		}
		
	}
}
