package main;

import controller.queries.ClientController;
import controller.queries.FournirController;
import controller.queries.FournisseurController;
import controller.queries.ProduitController;
import model.Categorie;
import model.Client;
import model.Fournisseur;
import model.Produit;



public class Main {

	public static void main(String[] args) {
		Fournisseur f = new Fournisseur("nom2","prenom","Adresse", "Ville");
		Client c = new Client("nom","prenom","Adresse", "Ville", 50);
		Produit p = new Produit("nom", Categorie.Plante,"espece", 14.2, 12);
		FournirController.desassocierFournisseurAProduit(3, 4);
	}

}
