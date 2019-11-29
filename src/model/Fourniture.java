package model;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Fourniture {

	private int id;
	private int idFournisseur;
	private LocalDateTime date;
	
	/**
	 * Constructeur par défaut
	 */
	public Fourniture() 
	{
		this.id = -1;
		this.idFournisseur = -1;
		this.date = LocalDateTime.now();
	}
	/**
	 * Construteur de confort
	 * @param idFournisseur
	 * @param listeProduits
	 * @param prixTotal
	 */
	public Fourniture(int idFournisseur,
			ArrayList<Produit> listeProduits, double prixTotal,LocalDateTime date) {
		this.id = id;
		this.idFournisseur = idFournisseur;
		this.date = date;
	}
	//GETTERS ET SETTERS
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdFournisseur() {
		return idFournisseur;
	}
	public void setIdFournisseur(int idFournisseur) {
		this.idFournisseur = idFournisseur;
	}
	public LocalDateTime getDate() {
		return date;
	}
	public void setDate(LocalDateTime date) {
		this.date = date;
	}
	
}
