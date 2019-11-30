package controller.view.table;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import controller.queries.ClientController;
import controller.queries.ProduitController;
import model.Client;
import model.Produit;

public class ClientTableTemplate extends AbstractTableModel
{
	private String[] entetes = {"Nom", "Prenom", "Adresse", "Ville", "Bon d'achat (%)"};
	private ArrayList<Client> clients = ClientController.voirClient();
	
	/**
	 * Obtenir un client selon l'index d'une ligne
	 * @param rowIndex l'index de la ligne
	 * @return le client
	 */
	public Client returnClient(int rowIndex)
	{
		return clients.get(rowIndex);
	}
	
	@Override
	public int getRowCount() {
		
		return clients.size();
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
	            return clients.get(rowIndex).getNom();
	        case 1:
	            return clients.get(rowIndex).getPrenom();
	        case 2:
	        	return clients.get(rowIndex).getAdresse();
	        
	        case 3:
	        	return clients.get(rowIndex).getVille();
	        
	        case 4:
	        	return clients.get(rowIndex).getBonAchat();
	        	
	        default:
	            return null; //Ne devrait jamais arriver
		}
	}
	
	public String getColumnName(int columnIndex) {
        return entetes[columnIndex];
    }

	/**
	 * Permet d'actualiser la table lors d'un changement
	 */
	public void actualiserClients() {
		this.clients = ClientController.voirClient();
		fireTableDataChanged();
	}
}
