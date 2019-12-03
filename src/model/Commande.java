package model;

import java.time.LocalDate;

public class Commande 
{
	private int id; 
	private int idClient;
	private LocalDate date;

	
	/**
	 * Constructeur de confort
	 * @param idClient
	 * @param listeProduits
	 * @param prixTotal
	 * @param date
	 */
	public Commande(int idClient) {
		this.id = -1;
		this.idClient = idClient;
		this.date = LocalDate.now();

	}
	/**
	 * Construteur par d�faut
	 */
	public Commande() {
		this.id = -1;
		this.idClient = -1;
		this.date = LocalDate.now();
	
	}
	
	@Override
	public String toString() {
		return "Commande [idClient=" + idClient + ", date=" + date + "]";
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
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
}
