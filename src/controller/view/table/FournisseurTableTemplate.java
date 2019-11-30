package controller.view.table;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import controller.queries.FournisseurController;
import model.Client;
import model.Fournisseur;

public class FournisseurTableTemplate extends AbstractTableModel
{
	private String[] entetes = {"Nom", "Prenom", "Adresse", "Ville"};
	private ArrayList<Fournisseur> fournisseurs = FournisseurController.voirFournisseur();
	
	/**
	 * Obtenir un fournisseur selon l'index d'une ligne
	 * @param rowIndex l'index de la ligne
	 * @return le fournisseur
	 */
	public Fournisseur returnFournisseur(int rowIndex)
	{
		return fournisseurs.get(rowIndex);
	}
	
	@Override
	public int getRowCount() {
		
		return fournisseurs.size();
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
	            return fournisseurs.get(rowIndex).getNom();
	        case 1:
	            return fournisseurs.get(rowIndex).getPrenom();
	        case 2:
	        	return fournisseurs.get(rowIndex).getAdresse();
	        
	        case 3:
	        	return fournisseurs.get(rowIndex).getVille();
	        
	        default:
	            return null; //Ne devrait jamais arriver
		}
	}
	
	public String getColumnName(int columnIndex) {
        return entetes[columnIndex];
    }

	public void actualiserFournisseurs() {
		this.fournisseurs = FournisseurController.voirFournisseur();
		fireTableDataChanged();
	}
}
