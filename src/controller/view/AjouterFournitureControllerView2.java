package controller.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import controller.queries.FournitureController;
import controller.queries.LivrerController;
import controller.queries.ProduitController;
import controller.view.table.AjouterCommandeTableTemplate;
import controller.view.table.AjouterFournitureTableTemplate;
import controller.view.table.FournitureTableTemplate;
import model.Fournisseur;
import model.Fourniture;
import model.Produit;
import view.AjouterFournitureView2;
import view.FournitureView;

public class AjouterFournitureControllerView2 
{
	JFrame frame;
	private AjouterFournitureTableTemplate modeleListeProduits = AjouterFournitureView2.getModeleListeProduits();
	private AjouterCommandeTableTemplate modelePanier = AjouterFournitureView2.getModelePanier();
	private NumberFormat format=NumberFormat.getInstance(); 
	/**
	 * Constructeur
	 */
	public AjouterFournitureControllerView2(JFrame frame)
	{
		//Recuperer la fenetre de la vue
		this.frame = frame;
		//Activer les actions listener
		AjouterFournitureView2.ajouterProduitDansFournitureListener(new AjouterAjouterFournitureListener());  
		AjouterFournitureView2.supprimerProduitDansFournitureListener(new SupprimerAjouterFournitureListener());
		AjouterFournitureView2.enregistrerProduitDansFournitureListener(new EnregistrerAjouterFournitureListener());
	}
	/**
	 * 
	 * ajouter Produit dans Fourniture
	 *
	 */
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
						double total = modelePanier.montantDesProduits();
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
	/**
	 * 
	 * enregistrer Produit dans Fourniture
	 *
	 */
	class EnregistrerAjouterFournitureListener implements ActionListener
	{
		private FournitureTableTemplate modele = FournitureView.getModele();
		
		public void actionPerformed(ActionEvent e)
		{
			if(!modelePanier.getProduits().isEmpty())
			{
				Fournisseur fournisseur = AjouterFournitureView2.getFournisseur();
				Fourniture fourniture = new Fourniture(fournisseur.getIdPersonne());
				//Creer la commande dans la BDD et recuperer l'id de la commande
				int idFourniture = FournitureController.ajouterFourniture(fourniture);
				
				//Parcourir le panier du fournisseur et ajouter dans fournir
				for(Produit produit : modelePanier.getProduits())
				{
					LivrerController.ajouterLivrer(idFourniture, produit.getIdProduit(), produit.getStock());
				}
				//Actualiser la vue de toute les fournitures
				modele.actualiserFournitures();
				//Mettre à jour les stocks dans la BDD
				for(Produit produit : modelePanier.getProduits())
				{
					ArrayList<Produit> listeProduit = modeleListeProduits.getProduits();
					
					for(Produit prod : listeProduit)
					{
						if(produit.getIdProduit() == prod.getIdProduit())
						{
							produit.setStock(produit.getStock() + prod.getStock());
						
							ProduitController.modifierProduit(produit, produit.getIdProduit());
						}
					}
				}
				//Vider panier
				modelePanier.viderLePanier();
				
				modeleListeProduits.actualiserProduits();
			}
			frame.dispose();
		}
	}
	/**
	 * 
	 * supprimer Produit dans Fourniture
	 *
	 */
	class SupprimerAjouterFournitureListener implements ActionListener
	{
		private JTable tablePanier = AjouterFournitureView2.getTablePanier();
		
		public void actionPerformed(ActionEvent e)
		{
			int ligneSelectionnee = tablePanier.getSelectedRow();
			//Si une ligne est selectionnee
			if(ligneSelectionnee != -1)
			{
				modelePanier.removeProduit(ligneSelectionnee);
				modeleListeProduits.fireTableDataChanged();
				
				//Afficher le montant total avec deux chiffres apres la virgules
				double total = modelePanier.montantDesProduits();
				format.setMinimumFractionDigits(2); //nb de chiffres apres la virgule 
				String totalF = format.format(total);
				AjouterFournitureView2.getVuMontant().setText(totalF+" €");
			}
		}
	}
}
