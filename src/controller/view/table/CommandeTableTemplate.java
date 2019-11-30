package controller.view.table;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import controller.queries.CommandeController;
import model.Client;
import model.Commande;

public class CommandeTableTemplate extends AbstractTableModel
{
	private String[] entetes = {"Nom", "Prenom", "Nombre de produits", "Montant", "Date"};
	private ArrayList<Commande> commandes = CommandeController.voirCommande();
	
	/**
	 * Obtenir une commande selon l'index d'une ligne
	 * @param rowIndex l'index de la ligne
	 * @return la commande
	 */
	public Commande returnCommande(int rowIndex)
	{
		return commandes.get(rowIndex);
	}
	
	@Override
	public int getRowCount() {
		
		return commandes.size();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return entetes.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) 
	{
		Commande commande = returnCommande(rowIndex);
		Client client =  CommandeController.trouverLeClientDeLaCommande(commande);
		
		switch(columnIndex)
		{
	        case 0:
	            return client.getPrenom();
	        case 1:
	            return client.getNom();
	        case 2:
	        	return CommandeController.calculterNombreProduitsCommande(commande);
	        
	        case 3:
	        	return CommandeController.calculerMontantCommande(commande);
	        
	        case 4:
	        	return commandes.get(rowIndex).getDate();
	        	
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
	public void actualiserCommandes() {
		this.commandes = CommandeController.voirCommande();
		fireTableDataChanged();
	}
}
