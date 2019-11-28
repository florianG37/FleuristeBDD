package model;

import java.util.ArrayList;

public class Fournisseur extends Personne{
	private ArrayList<Produit> listeProduits ;

	public Fournisseur() {
		super();
		this.listeProduits = listeProduits;
	}

	public Fournisseur(String nom, String prenom, String adresse, String ville) {
		super(nom, prenom, adresse, ville);
		this.listeProduits = listeProduits;
	}

	public Fournisseur(ArrayList<Produit> listeProduits) {
		this.listeProduits = listeProduits;
	}

	public ArrayList<Produit> getListeProduits() {
		return listeProduits;
	}

	public void setListeProduits(ArrayList<Produit> listeProduits) {
		this.listeProduits = listeProduits;
	}
}
