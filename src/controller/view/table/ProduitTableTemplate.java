package controller.view.table;

import java.time.Duration;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import controller.queries.ProduitController;
import model.Produit;

public class ProduitTableTemplate extends AbstractTableModel 
{
		private String[] entetes = {"Nom", "Categorie", "Espece", "Prix", "Quantite"};
		private ArrayList<Produit> produits = ProduitController.voirProduit();
		
		/**
		 * Obtenir un produit selon l'index d'une ligne
		 * @param rowIndex l'index de la ligne
		 * @return le produit
		 */
		public Produit returnProduit(int rowIndex)
		{
			return produits.get(rowIndex);
		}
		
		/**
		 * Ajouter un produit au modele
		 * @param produit le teacher à ajouter
		 */
		public void addTeacher(Produit produit)
		{
			produits.add(produit);
			fireTableRowsInserted(produits.size() -1, produits.size() -1);
		}
		
		/**
		 * Supprimer un produit du modele
		 * @param rowIndex l'indice de ligne
		 */
		public void removeProduit(int rowIndex)
		{
			produits.remove(rowIndex);
			fireTableRowsDeleted(rowIndex, rowIndex);
		}
		
		@Override
		public int getRowCount() {
			
			return produits.size();
		}

		@Override
		public int getColumnCount() {
			// TODO Auto-generated method stub
			return entetes.length;
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) 
		{
			switch(columnIndex)
			{
		        case 0:
		            return produits.get(rowIndex).getNom();
		        case 1:
		            return produits.get(rowIndex).getCategorie();
		        case 2:
		        	return produits.get(rowIndex).getEspece();
		        
		        case 3:
		        	return produits.get(rowIndex).getPrix();
		        
		        case 4:
		        	return produits.get(rowIndex).getStock();
		        	
		        default:
		            return null; //Ne devrait jamais arriver
			}
		}
		
		public String getColumnName(int columnIndex) {
	        return entetes[columnIndex];
	    }
		
		public void actualiserProduits(){
			this.produits = ProduitController.voirProduit();
			fireTableDataChanged();
		}
}
