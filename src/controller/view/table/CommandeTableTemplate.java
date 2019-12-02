package controller.view.table;

import java.text.NumberFormat;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import controller.queries.CommandeController;
import controller.queries.ReductionController;
import model.Client;
import model.Commande;

public class CommandeTableTemplate extends AbstractTableModel
{
	private String[] entetes = {"Nom", "Prenom", "Nombre de produits", "Bon d'achat (%)", "Montant TTC Av","Montant TTC Ap", "Date"};
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
		int reduction = ReductionController.reductionDeLaCommande(commande);
		Client client =  CommandeController.trouverLeClientDeLaCommande(commande);
		NumberFormat format=NumberFormat.getInstance(); 
    	format.setMinimumFractionDigits(2); //nb de chiffres apres la virgule 
		
		switch(columnIndex)
		{
	        case 0:
	            return client.getPrenom();
	        case 1:
	            return client.getNom();
	        case 2:
	        	return CommandeController.calculterNombreProduitsCommande(commande);
	        case 3:
	        	return reduction;
	        case 4:
	        	String montantAv = format.format(CommandeController.calculerMontantCommande(commande)*1.15);
	        	return montantAv;
	        case 5:
	        	String montantAp=format.format(CommandeController.calculerMontantCommande(commande)*((100-reduction)/100.0)*1.15); 
	        	return montantAp;
	        case 6:
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
