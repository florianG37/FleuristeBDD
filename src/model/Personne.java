package model;

public class Personne {
	private int idPersonne;
	private String nom;
	private String prenom;
	private String adresse;
	private String ville;
	
	/**
	 * Contructeur par defaut
	 */
	public Personne() {
		this.idPersonne = -1;
		this.nom = "";
		this.prenom = "";
		this.adresse = "";
		this.ville = "";
	}


	/**
	 * Constructeur par de confort
	 * @param idPersonne
	 * @param nom
	 * @param prenom
	 * @param adresse
	 * @param ville
	 */
	public Personne( String nom, String prenom, String adresse,
			String ville) {
		this.idPersonne = -1;
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
		this.ville = ville;
	}
	
	
	
	public int getIdPersonne() {
		return idPersonne;
	}
	public void setIdPersonne(int idPersonne) {
		this.idPersonne = idPersonne;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
	
	
}
