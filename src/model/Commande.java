package model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

public class Commande 
{
	private int id; 
	private int idClient;
	private LocalDateTime date;
	private int idProduit;
	private int quantite;
	
	/**
	 * Constructeur de confort
	 * @param idClient
	 * @param listeProduits
	 * @param prixTotal
	 * @param date
	 */
	public Commande(int idClient, LocalDateTime date,int idProduit,int quantite) {
		this.idClient = idClient;
		this.date = date;
		this.idProduit = idProduit;
		this.quantite = quantite;
	}
	/**
	 * Construteur par défaut
	 */
	public Commande() {
		this.id = -1;
		this.idClient = -1;
		this.date = LocalDateTime.now();
		this.idProduit = -1;
		this.quantite = 0;
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
	public LocalDateTime getDate() {
		return date;
	}
	public void setDate(LocalDateTime date) {
		this.date = date;
	}
	public int getIdProduit() {
		return idProduit;
	}
	public void setIdProduit(int idProduit) {
		this.idProduit = idProduit;
	}
	public int getQuantite() {
		return quantite;
	}
	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}
	
}
