package controller.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTable;

import controller.view.table.AjouterCommandeTableTemplate;
import controller.view.table.ProduitTableTemplate;
import model.Produit;
import view.AjouterCommandeView;

public class AjouterCommandeControllerView 
{
	public AjouterCommandeControllerView()
	{
		AjouterCommandeView.ajouterProduitDansCommandeListener(new AjouterAjouterCommandeListener());  
		AjouterCommandeView.supprimerProduitDansCommandeListener(new SupprimerAjouterCommandeListener());
		AjouterCommandeView.modifierProduitDansCommandeListener(new ModifierAjouterCommandeListener());
		AjouterCommandeView.enregistrerProduitDansCommandeListener(new EnregistrerAjouterCommandeListener());
	}
	
	class AjouterAjouterCommandeListener implements ActionListener
	{
		private JTable table = AjouterCommandeView.getTableListeProduits();
		private ProduitTableTemplate modeleListeProduits = AjouterCommandeView.getModeleListeProduits();
		private AjouterCommandeTableTemplate modelePanier = AjouterCommandeView.getModelePanier();
		
		public void actionPerformed(ActionEvent e)
		{
			int ligneSelectionnee = table.getSelectedRow();
			System.out.println("Ligne selected "+ligneSelectionnee);
			//Si une ligne est selectionnee
			if(ligneSelectionnee != -1)
			{
				Produit produit = modeleListeProduits.returnProduit(ligneSelectionnee);
				modelePanier.addProduit(produit);
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
	
	class ModifierAjouterCommandeListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			System.out.println("Modifier");
		}
		
	}
	
	class SupprimerAjouterCommandeListener implements ActionListener
	{
		private JTable tablePanier = AjouterCommandeView.getTablePanier();
		private ProduitTableTemplate modeleListeProduits = AjouterCommandeView.getModeleListeProduits();
		private AjouterCommandeTableTemplate modelePanier = AjouterCommandeView.getModelePanier();
		
		public void actionPerformed(ActionEvent e)
		{
			int ligneSelectionnee = tablePanier.getSelectedRow();
			//Si une ligne est selectionnee
			if(ligneSelectionnee != -1)
			{
				modelePanier.removeProduit(ligneSelectionnee);
			}
		}
	}
}
