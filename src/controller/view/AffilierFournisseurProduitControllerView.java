package controller.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTable;

import controller.view.table.AffilierFournisseurProduitTableTemplate;
import model.Produit;
import view.AffilierFournisseurProduitView;

public class AffilierFournisseurProduitControllerView {
	
	public AffilierFournisseurProduitControllerView() 
	{
		AffilierFournisseurProduitView.affilierFournisseurListener(new AffilierFournisseurListener());
	}
	
	class AffilierFournisseurListener implements ActionListener
	{
		private AffilierFournisseurProduitTableTemplate modele = new AffilierFournisseurProduitTableTemplate();
		private JTable table = AffilierFournisseurProduitView.getTable();
		public void actionPerformed(ActionEvent arg0) 
		{
			int ligneSelectionnee = table.getSelectedRow();
			//Une ligne est selectionnee
			if(ligneSelectionnee != -1)
			{
				ligneSelectionnee = table.getRowSorter().convertRowIndexToModel(ligneSelectionnee);
				Produit produit = modele.returnProduit(ligneSelectionnee);
				
				
				
			}
		}
	}
	
}
