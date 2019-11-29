package model;

public class Produit {
	
	private int idProduit;
	private String nom;
	private Categorie categorie;
	private String espece;
	private double prix;
	private int stock;
	
	public Produit() {
		this.idProduit = -1;
		this.nom = "";
		this.categorie = Categorie.Fleur;
		this.espece = "";
		this.prix = 0.0;
		this.stock = 0;
		
	}
	public Produit(String nom, Categorie categorie,String espece, double prix, int stock) {
		this.idProduit = -1;
		this.nom = nom;
		this.categorie = categorie;
		this.espece = espece;
		this.prix = prix;
		this.stock = stock;
	}
	
	@Override
	public String toString() {
		return "Produit : "+nom+" Prix : "+prix;
	}
	
	//GETTERS ET SETTERS
	public int getIdProduit() {
		return idProduit;
	}
	public void setIdProduit(int idProduit) {
		this.idProduit = idProduit;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public Categorie getCategorie() {
		return categorie;
	}
	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}
	public String getEspece() {
		return espece;
	}
	public void setEspece(String espece) {
		this.espece = espece;
	}
	public double getPrix() {
		return prix;
	}
	public void setPrix(double prix) {
		this.prix = prix;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}

	
    
}
