package main;

import java.time.LocalDateTime;
import java.util.Date;

import controller.queries.ClientController;
import controller.queries.CommandeController;
import controller.queries.FournirController;
import controller.queries.FournisseurController;
import controller.queries.ProduitController;
import model.Categorie;
import model.Client;
import model.Commande;
import model.Fournisseur;
import model.Produit;
import view.HomeView;



public class Main {

	public static void main(String[] args) {
		/*Fournisseur f = new Fournisseur("nom2","prenom","Adresse", "Ville");
		Client c = new Client("nom","prenom","Adresse", "Ville", 50);
		Commande cmd= new Commande(1,LocalDateTime.now(),4,10);*/
		/*Produit p = new Produit("ddd", Categorie.Plante,"espece", 14.2, 12);
		ProduitController.ajouterProduit(p);*/
		
		new HomeView();
		
	}

}
