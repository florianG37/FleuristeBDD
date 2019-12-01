package controller.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import controller.view.table.AjouterCommandeTableTemplate;
import controller.view.table.ProduitTableTemplate;
import model.Produit;
import view.AjouterCommandeView2;

public class AjouterCommandeControllerView2 
{
	public AjouterCommandeControllerView2()
	{
		AjouterCommandeView2.ajouterProduitDansCommandeListener(new AjouterAjouterCommandeListener());  
		AjouterCommandeView2.supprimerProduitDansCommandeListener(new SupprimerAjouterCommandeListener());
		AjouterCommandeView2.enregistrerProduitDansCommandeListener(new EnregistrerAjouterCommandeListener());
	}
	
	class AjouterAjouterCommandeListener implements ActionListener
	{
		private JTable table = AjouterCommandeView2.getTableListeProduits();
		private ProduitTableTemplate modeleListeProduits = AjouterCommandeView2.getModeleListeProduits();
		private AjouterCommandeTableTemplate modelePanier = AjouterCommandeView2.getModelePanier();
		
		public void actionPerformed(ActionEvent e)
		{
			int ligneSelectionnee = table.getSelectedRow();
			//Si une ligne est selectionnee
			if(ligneSelectionnee != -1)
			{
				Produit produitCopy = modeleListeProduits.returnProduit(ligneSelectionnee);
				Produit produit = new Produit(modeleListeProduits.returnProduit(ligneSelectionnee));
				
				int quantite = Integer.parseInt(JOptionPane.showInputDialog(null, "Entrez la quantite")) ;
				
				if(quantite >= 0 && quantite <= produit.getStock())
				{
					produit.setStock(quantite);
					modelePanier.addProduit(produit);
					produitCopy.setStock(produitCopy.getStock()-quantite);
					
					double reduction = (100-AjouterCommandeView2.getClient().getBonAchat())/100.0;
					AjouterCommandeView2.getVuMontant().setText(modelePanier.montantDesProduits()*reduction+" €");
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Erreur sur la quantite");
				}
			}
		}
	}
	
	class EnregistrerAjouterCommandeListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			System.out.println("Enregistrer");
		}
	}
	
	class SupprimerAjouterCommandeListener implements ActionListener
	{
		private JTable tablePanier = AjouterCommandeView2.getTablePanier();
		private ProduitTableTemplate modeleListeProduits = AjouterCommandeView2.getModeleListeProduits();
		private AjouterCommandeTableTemplate modelePanier = AjouterCommandeView2.getModelePanier();
		
		public void actionPerformed(ActionEvent e)
		{
			int ligneSelectionnee = tablePanier.getSelectedRow();
			//Si une ligne est selectionnee
			if(ligneSelectionnee != -1)
			{
				Produit produitASupprimer = modelePanier.returnProduit(ligneSelectionnee);
				Produit produit = modeleListeProduits.idToProduit(produitASupprimer.getIdProduit());
				produit.setStock(produit.getStock() + produitASupprimer.getStock());
				modelePanier.removeProduit(ligneSelectionnee);
				modeleListeProduits.fireTableDataChanged();
				
				double reduction = (100-AjouterCommandeView2.getClient().getBonAchat())/100.0;
				AjouterCommandeView2.getVuMontant().setText(modelePanier.montantDesProduits()*reduction+" €");
			}
		}
	}
}
