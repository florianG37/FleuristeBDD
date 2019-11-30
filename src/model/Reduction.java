package model;

import java.time.LocalDate;

public class Reduction 
{
	private int idReduction;
	private int idClient;
	private LocalDate dateDebut;
	private LocalDate dateFin;
	private int BonAchat;
	
	/**
	 * Constructeur de confort
	 * @param idClient
	 * @param dateDebut
	 * @param dateFin
	 * @param bonAchat
	 */
	public Reduction(int idClient, int bonAchat) {
		super();
		this.idClient = idClient;
		this.dateDebut = LocalDate.now();
		this.dateFin = null;
		BonAchat = bonAchat;
	}
	
	/**
	 * Constructeur par défaut
	 */
	public Reduction() {
		super();
		this.idClient = -1;
		this.dateDebut = LocalDate.now();
		this.dateFin = null;
		BonAchat = 0;
	}
	
	//GETTERS ET SETTERS
	public int getIdReduction() {
		return idReduction;
	}
	public void setIdReduction(int idReduction) {
		this.idReduction = idReduction;
	}
	public int getIdClient() {
		return idClient;
	}
	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}
	public LocalDate getDateDebut() {
		return dateDebut;
	}
	public void setDateDebut(LocalDate dateDebut) {
		this.dateDebut = dateDebut;
	}
	public LocalDate getDateFin() {
		return dateFin;
	}
	public void setDateFin(LocalDate dateFin) {
		this.dateFin = dateFin;
	}
	public int getBonAchat() {
		return BonAchat;
	}
	public void setBonAchat(int bonAchat) {
		BonAchat = bonAchat;
	}
	
	
}
