package model;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Commande 
{
	private int id; 
	private int idClient;
	private ArrayList<Produit> listeProduits;
	private double prixTotal;
	private LocalDateTime date;
	
	/**
	 * Constructeur de confort
	 * @param idClient
	 * @param listeProduits
	 * @param prixTotal
	 * @param date
	 */
	public Commande(int idClient, ArrayList<Produit> listeProduits,
			double prixTotal, LocalDateTime date) {
		this.idClient = idClient;
		this.listeProduits = listeProduits;
		this.prixTotal = prixTotal;
		this.date = date;
	}
	/**
	 * Construteur par défaut
	 */
	public Commande() {
		this.id = -1;
		this.idClient = -1;
		this.listeProduits = new ArrayList<Produit>();
		this.prixTotal = 0;
		this.date = LocalDateTime.now();
	}
	
	//GETTERS ET SETTERS
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdClient() {
		return idClient;
	}
	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}
	public ArrayList<Produit> getListeProduits() {
		return listeProduits;
	}
	public void setListeProduits(ArrayList<Produit> listeProduits) {
		this.listeProduits = listeProduits;
	}
	public double getPrixTotal() {
		return prixTotal;
	}
	public void setPrixTotal(double prixTotal) {
		this.prixTotal = prixTotal;
	}
	public LocalDateTime getDate() {
		return date;
	}
	public void setDate(LocalDateTime date) {
		this.date = date;
	}
	
}
