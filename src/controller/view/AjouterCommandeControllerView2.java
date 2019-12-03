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
import controller.view.table.CommandeTableTemplate;
import controller.view.table.ProduitTableTemplate;
import model.Client;
import model.Commande;
import model.Produit;
import view.AjouterCommandeView2;
import view.CommandeView;

public class AjouterCommandeControllerView2 
{
	JFrame frame;
	private ProduitTableTemplate modeleListeProduits = AjouterCommandeView2.getModeleListeProduits();
	private AjouterCommandeTableTemplate modelePanier = AjouterCommandeView2.getModelePanier();
	private NumberFormat format=NumberFormat.getInstance(); 
	/**
	 * Constructeur
	 */
	public AjouterCommandeControllerView2(JFrame frame)
	{
		//Recuperer la fenetre de la vue
		this.frame = frame;
		
		//Activer les actions listener
		AjouterCommandeView2.ajouterProduitDansCommandeListener(new AjouterAjouterCommandeListener());  
		AjouterCommandeView2.supprimerProduitDansCommandeListener(new SupprimerAjouterCommandeListener());
		AjouterCommandeView2.enregistrerProduitDansCommandeListener(new EnregistrerAjouterCommandeListener());
	}
	/**
	 * 
	 * ajouter Produit Dans Commande
	 *
	 */
	class AjouterAjouterCommandeListener implements ActionListener
	{
		private JTable table = AjouterCommandeView2.getTableListeProduits();

		public void actionPerformed(ActionEvent e)
		{
			int ligneSelectionnee = table.getSelectedRow();
			//Si une ligne est selectionnee
			if(ligneSelectionnee != -1)
			{
				Produit produitCopy = modeleListeProduits.returnProduit(ligneSelectionnee);
				Produit produit = new Produit(modeleListeProduits.returnProduit(ligneSelectionnee));
				
				try
				{
					int quantite = Integer.parseInt(JOptionPane.showInputDialog(null, "Entrez la quantite")) ;
				
					if(quantite >= 0 && quantite <= produit.getStock())
					{
						produit.setStock(quantite);
						modelePanier.addProduit(produit);
						produitCopy.setStock(produitCopy.getStock()-quantite);
						
						//Afficher le montant total avec deux chiffres apres la virgules
						double reduction = (100-AjouterCommandeView2.getClient().getBonAchat())/100.0;
						double total = modelePanier.montantDesProduits()*reduction;
						format.setMinimumFractionDigits(2); //nb de chiffres apres la virgule 
						String totalF = format.format(total);
						AjouterCommandeView2.getVuMontant().setText(totalF+" €");
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
	/**
	 * 
	 * enregistrer Produit Dans Commande
	 *
	 */
	class EnregistrerAjouterCommandeListener implements ActionListener
	{
		private CommandeTableTemplate modele = CommandeView.getModele();
		
		public void actionPerformed(ActionEvent e)
		{
			if(!modelePanier.getProduits().isEmpty())
			{	
				System.out.println(modelePanier.getProduits());
				Client client = AjouterCommandeView2.getClient();
				Commande commande = new Commande(client.getIdPersonne());
				//Creer la commande dans la BDD et recuperer l'id de la commande
				int idCommande = CommandeController.ajouterCommande(commande);
				
				//Parcourir le panier du client et ajouter dans commander
				for(Produit produit : modelePanier.getProduits())
				{
					CommanderController.ajouterCommander(idCommande, produit.getIdProduit(), produit.getStock());
				}
				//Actualiser la vue de toute les commandes
				modele.actualiserCommandes();
				//Vider panier
				modelePanier.viderLePanier();
				
				//Mettre à jour les stocks dans la BDD
				for(Produit produit : modeleListeProduits.getProduits())
				{
					ProduitController.modifierProduit(produit, produit.getIdProduit());
				}
				
				modeleListeProduits.actualiserProduits();
			}	
			frame.dispose();
		}
	}
	/**
	 * 
	 * supprimer Produit Dans Commande
	 *
	 */
	class SupprimerAjouterCommandeListener implements ActionListener
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
				double reduction = (100-AjouterCommandeView2.getClient().getBonAchat())/100.0;
				double total = modelePanier.montantDesProduits()*reduction*1.15;
				format.setMinimumFractionDigits(2); //nb de chiffres apres la virgule 
				String totalF = format.format(total);
				AjouterCommandeView2.getVuMontant().setText(totalF+" €");
			}
		}
	}
}
