package model;

import java.time.LocalDate;


public class Fourniture {

	private int id;
	private int idFournisseur;
	private LocalDate date;
	
	/**
	 * Constructeur par d�faut
	 */
	public Fourniture() 
	{
		this.id = -1;
		this.idFournisseur = -1;
		this.date = LocalDate.now();
	}
	/**
	 * Construteur de confort
	 * @param idFournisseur
	 * @param listeProduits
	 * @param prixTotal
	 */
	public Fourniture(int idFournisseur) {
		this.id = -1;
		this.idFournisseur = idFournisseur;
		this.date = LocalDate.now();
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
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	
}
