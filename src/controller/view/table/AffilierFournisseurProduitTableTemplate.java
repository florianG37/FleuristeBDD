package controller.view.table;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import view.AffilierFournisseurProduitView;
import model.Produit;
import controller.queries.FournirController;
import controller.queries.ProduitController;

public class AffilierFournisseurProduitTableTemplate extends AbstractTableModel{

	private String[] entetes = {"Nom", "Categorie", "Espece", "Appartenir"};
	private ArrayList<Produit> produits = ProduitController.voirProduit();
	private ArrayList<Integer> appartenir = FournirController.voirAssociation(AffilierFournisseurProduitView.getIdFournisseur());
	
	/**
	 * Obtenir un produit selon l'index d'une ligne
	 * @param rowIndex l'index de la ligne
	 * @return le produit
	 */
	public Produit returnProduit(int rowIndex)
	{
		return produits.get(rowIndex);
	}
	
	@Override
	public int getColumnCount() {
		return entetes.length;
	}

	@Override
	public int getRowCount() {
		return produits.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch(columnIndex)
		{
	        case 0:
	            return produits.get(rowIndex).getNom();
	        case 1:
	            return produits.get(rowIndex).getCategorie();
	        case 2:
	        	return produits.get(rowIndex).getEspece();
	        case 3:
	        	if((appartenir.contains(produits.get(rowIndex).getIdProduit()))==true){
	        		return "Oui";
	        	}else{
	        		return "Non";	
	        	}
	        default:
	            return null; //Ne devrait jamais arriver
		}
	}
	public String getColumnName(int columnIndex) {
        return entetes[columnIndex];
    }
	
	public void actualiserProduits(){
		this.produits = ProduitController.voirProduit();
		this.appartenir =FournirController.voirAssociation(AffilierFournisseurProduitView.getIdFournisseur());
		fireTableDataChanged();
	}

}
