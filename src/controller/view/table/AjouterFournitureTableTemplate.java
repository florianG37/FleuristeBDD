package controller.view.table;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import controller.queries.FournisseurController;
import controller.queries.ProduitController;
import model.Fournisseur;
import model.Produit;

public class AjouterFournitureTableTemplate extends AbstractTableModel
{
	private String[] entetes = {"Nom", "Categorie", "Espece", "Prix HT", "Prix TTC"};
	private ArrayList<Produit> produits; 
	
	public AjouterFournitureTableTemplate(Fournisseur fournisseur)
	{
		produits = FournisseurController.voirProduitsFournisseur(fournisseur.getIdPersonne());
	}
	
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
	 * Obtenir le produit du modele à partir de son id
	 * @param idProduit l'id du produit
	 */
	public Produit idToProduit(int idProduit)
	{
		for(Produit produit : produits)
		{
			if(produit.getIdProduit() == idProduit)
			{
				return produit;
			}
		}
		return null;
	}
	
	/**
	 * Ajouter un produit au modele
	 * @param produit le produit à ajouter
	 */
	public void addProduit(Produit produit)
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
	        	return produits.get(rowIndex).getPrix()*1.15;
	        	
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
	
	//GETTERS ET SETTERS
	public void setProduits(ArrayList<Produit> produits) {
		this.produits = produits;
	}

	public ArrayList<Produit> getProduits()
	{
		return produits;
	}
}
