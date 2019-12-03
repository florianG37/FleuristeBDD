package controller.view;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import controller.view.table.ClientTableTemplate;
import controller.view.table.CommandeTableTemplate;
import controller.view.table.FournisseurTableTemplate;
import controller.view.table.FournitureTableTemplate;
import controller.view.table.ProduitTableTemplate;
import view.AjouterCommandeView1;
import view.AjouterCommandeView2;
import view.AjouterFournitureView1;
import view.ClientView;
import view.CommandeView;
import view.FournisseurView;
import view.FournitureView;
import view.HomeView;
import view.ProduitView;

public class HomeControllerView 
{
	private ProduitTableTemplate modeleProduit = ProduitView.getModele();
	private ClientTableTemplate modeleClient = ClientView.getModele();
	private FournisseurTableTemplate modeleFournisseur = FournisseurView.getModele();
	private ClientTableTemplate modeleAjouterCommande1 = AjouterCommandeView1.getModele();
	private FournitureTableTemplate modeleFourniture = FournitureView.getModele();
	private CommandeTableTemplate modeleCommande = CommandeView.getModele();
	private FournisseurTableTemplate modeleAjouterFourniture = AjouterFournitureView1.getModele();
	private ProduitTableTemplate modeleAjouterCommande2 = AjouterCommandeView2.getModeleListeProduits();
	
	/**
	 * Constructeur
	 */
	public HomeControllerView()
	{
		HomeView.actualiserTable(new ActualiserTableListener());
	}
	/**
	 * 
	 * Actualisation des onglets
	 *
	 */
	class ActualiserTableListener implements ChangeListener
	{
		@Override
		public void stateChanged(ChangeEvent e) 
		{
			modeleProduit.actualiserProduits();
			modeleClient.actualiserClients();
			modeleFournisseur.actualiserFournisseurs();
			modeleAjouterCommande1.actualiserClients();
			modeleFourniture.actualiserFournitures();
			modeleCommande.actualiserCommandes();
			modeleAjouterFourniture.actualiserFournisseurs();
			modeleAjouterCommande2.actualiserProduits();
		}
	}
}
