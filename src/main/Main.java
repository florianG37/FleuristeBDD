package main;

import java.sql.Connection;

import model.Categorie;
import model.Produit;
import controller.queries.ConnexionController;
import controller.queries.ProduitController;

public class Main {

	public static void main(String[] args) {
		Produit p = new Produit("nom", Categorie.Fleur, "espece", 30, 50);
		ProduitController.modifierProduit(p, 1);
	}

}
