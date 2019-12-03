package controller.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JTable;

import controller.queries.FournirController;
import controller.view.table.AffilierFournisseurProduitTableTemplate;
import model.Produit;
import view.AffilierFournisseurProduitView;


public class AffilierFournisseurProduitControllerView {
	/**
	 * Constructeur
	 */
	public AffilierFournisseurProduitControllerView() 
	{
		AffilierFournisseurProduitView.affilierFournisseurListener(new AffilierFournisseurListener());
	}
	/**
	 * 
	 * Action associer ou dissocier un fournisseur dun produit
	 *
	 */
	class AffilierFournisseurListener implements ActionListener
	{
		private AffilierFournisseurProduitTableTemplate modele = AffilierFournisseurProduitView.getModele();
		private JTable table = AffilierFournisseurProduitView.getTable();
		public void actionPerformed(ActionEvent arg0) 
		{
			int ligneSelectionnee = table.getSelectedRow();
			//Une ligne est selectionnee
			if(ligneSelectionnee != -1)
			{
				ligneSelectionnee = table.getRowSorter().convertRowIndexToModel(ligneSelectionnee);
				Produit produit = modele.returnProduit(ligneSelectionnee);
				ArrayList<Integer> appartenir = FournirController.voirAssociation(AffilierFournisseurProduitView.getIdFournisseur());
				Boolean appartient = false;
				for(int idProduit : appartenir){
					if(idProduit == produit.getIdProduit()){
						appartient =true;
						FournirController.desassocierFournisseurAProduit(AffilierFournisseurProduitView.getIdFournisseur(), idProduit);
					}
				}
				if(appartient == false){
					FournirController.associerFournisseurAProduit(AffilierFournisseurProduitView.getIdFournisseur(), produit.getIdProduit());
				}
				modele.actualiserProduits();
				
				
				
			}
		}
	}
	
}
