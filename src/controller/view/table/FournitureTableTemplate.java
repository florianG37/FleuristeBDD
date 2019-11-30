package controller.view.table;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import controller.queries.CommandeController;
import controller.queries.FournitureController;
import model.Fournisseur;
import model.Fourniture;

public class FournitureTableTemplate extends AbstractTableModel
{
	private String[] entetes = {"Nom", "Prenom", "Nombre de produits", "Montant", "Date"};
	private ArrayList<Fourniture> fournitures = FournitureController.voirFourniture();
	
	/**
	 * Obtenir une fourniture selon l'index d'une ligne
	 * @param rowIndex l'index de la ligne
	 * @return la fourniture
	 */
	public Fourniture returnFourniture(int rowIndex)
	{
		return fournitures.get(rowIndex);
	}
	
	@Override
	public int getRowCount() {
		
		return fournitures.size();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return entetes.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) 
	{
		Fourniture fourniture = returnFourniture(rowIndex);
		Fournisseur fournisseur =  FournitureController.trouverLeFournisseurDeLaFourniture(fourniture);
		
		switch(columnIndex)
		{
	        case 0:
	            return fournisseur.getPrenom();
	        case 1:
	            return fournisseur.getNom();
	        case 2:
	        	return FournitureController.calculterNombreProduitsFourniture(fourniture);
	        
	        case 3:
	        	return FournitureController.calculerMontantFourniture(fourniture);
	        
	        case 4:
	        	return fournitures.get(rowIndex).getDate();
	        	
	        default:
	            return null; //Ne devrait jamais arriver
		}
	}
	
	public String getColumnName(int columnIndex) {
        return entetes[columnIndex];
    }

	/**
	 * Permet d'actualiser la 
	 * @param rowIndex
	 */
	public void actualiserFournitures() {
		this.fournitures = FournitureController.voirFourniture();
		fireTableDataChanged();
	}
}
