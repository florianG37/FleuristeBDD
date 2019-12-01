package controller.view.table;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import model.Produit;
import controller.queries.ProduitController;

public class AlerteTableTemplate extends AbstractTableModel{
	private String[] entetes = {"Nom", "Categorie", "Espece", "Prix HT", "Prix TTC", "Quantite"};
	private ArrayList<Produit> produits = ProduitController.voirProduitSansStock();
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
	        	return produits.get(rowIndex).getPrix()*1.15;
	        
	        case 5:
	        	return produits.get(rowIndex).getStock();
	        	
	        default:
	            return null; //Ne devrait jamais arriver
		}
	}
	public String getColumnName(int columnIndex) {
        return entetes[columnIndex];
    }
	
}

