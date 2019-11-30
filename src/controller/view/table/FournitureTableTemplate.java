package controller.view.table;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import controller.queries.FournitureController;
import model.Client;
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
		switch(columnIndex)
		{
	        case 0:
	            return fournitures.get(rowIndex);
	        case 1:
	            return fournitures.get(rowIndex);
	        case 2:
	        	return fournitures.get(rowIndex);
	        
	        case 3:
	        	return fournitures.get(rowIndex);
	        
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
	public void actualiserFournitures(int rowIndex) {
		this.fournitures = FournitureController.voirFourniture();
		fireTableDataChanged();
	}
}
