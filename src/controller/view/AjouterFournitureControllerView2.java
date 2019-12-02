package controller.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import controller.queries.CommandeController;
import controller.queries.CommanderController;
import controller.queries.ProduitController;
import controller.view.table.AjouterCommandeTableTemplate;
import controller.view.table.AjouterFournitureTableTemplate;
import controller.view.table.CommandeTableTemplate;
import model.Client;
import model.Commande;
import model.Produit;
import view.AjouterCommandeView2;
import view.AjouterFournitureView2;
import view.CommandeView;

public class AjouterFournitureControllerView2 
{
	JFrame frame;
	private AjouterFournitureTableTemplate modeleListeProduits = AjouterFournitureView2.getModeleListeProduits();
	private AjouterCommandeTableTemplate modelePanier = AjouterFournitureView2.getModelePanier();
	private NumberFormat format=NumberFormat.getInstance(); 
	
	public AjouterFournitureControllerView2(JFrame frame)
	{
		//Recuperer la fenetre de la vue
		this.frame = frame;
		//Activer les actions listener
		AjouterFournitureView2.ajouterProduitDansFournitureListener(new AjouterAjouterFournitureListener());  
		AjouterFournitureView2.supprimerProduitDansFournitureListener(new SupprimerAjouterFournitureListener());
		AjouterFournitureView2.enregistrerProduitDansFournitureListener(new EnregistrerAjouterFournitureListener());
	}
	
	class AjouterAjouterFournitureListener implements ActionListener
	{
		private JTable table = AjouterFournitureView2.getTableListeProduits();

		public void actionPerformed(ActionEvent e)
		{
			int ligneSelectionnee = table.getSelectedRow();
			//Si une ligne est selectionnee
			if(ligneSelectionnee != -1)
			{
				Produit produit = new Produit(modeleListeProduits.returnProduit(ligneSelectionnee));
				
				try
				{
					int quantite = Integer.parseInt(JOptionPane.showInputDialog(null, "Entrez la quantite")) ;
				
					if(quantite >= 0)
					{
						produit.setStock(quantite);
						modelePanier.addProduit(produit);
						
						//Afficher le montant total avec deux chiffres apres la virgules
						double total = modelePanier.montantDesProduits()*1.15;
						format.setMinimumFractionDigits(2); //nb de chiffres apres la virgule 
						String totalF = format.format(total);
						AjouterFournitureView2.getVuMontant().setText(totalF+" €");
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Erreur sur la quantite");
					}
				}catch (Exception e1) {
					//e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "Erreur valeur impossible", "Erreur", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}
	
	class EnregistrerAjouterFournitureListener implements ActionListener
	{
		private CommandeTableTemplate modele = CommandeView.getModele();
		
		public void actionPerformed(ActionEvent e)
		{
			Client client = AjouterCommandeView2.getClient();
			Commande commande = new Commande(client.getIdPersonne());
			//Creer la commande dans la BDD et recuperer l'id de la commande
			//int idCommande = CommandeController.ajouterCommande(commande);
			
			//Parcourir le panier du client et ajouter dans commander
			for(Produit produit : modelePanier.getProduits())
			{
				//CommanderController.ajouterCommander(idCommande, produit.getIdProduit(), produit.getStock());
			}
			//Actualiser la vue de toute les commandes
			modele.actualiserCommandes();
			//Vider panier
			modelePanier.viderLePanier();
			
			//Mettre à jour les stocks dans la BDD
			for(Produit produit : modeleListeProduits.getProduits())
			{
				//ProduitController.modifierProduit(produit, produit.getIdProduit());
			}
			
			modeleListeProduits.actualiserProduits();
			frame.dispose();
		}
	}
	
	class SupprimerAjouterFournitureListener implements ActionListener
	{
		private JTable tablePanier = AjouterCommandeView2.getTablePanier();
		
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
				
				//Afficher le montant total avec deux chiffres apres la virgules
				double total = modelePanier.montantDesProduits()*1.15;
				format.setMinimumFractionDigits(2); //nb de chiffres apres la virgule 
				String totalF = format.format(total);
				AjouterCommandeView2.getVuMontant().setText(totalF+" €");
			}
		}
	}
}
