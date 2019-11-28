package model;

public class Client extends Personne{
	private int bonAchat;

	/**
	 * Constructeur par défaut
	 */
	public Client() {
		super();
		this.bonAchat = 0;
	}
	/**
	 * Constructeur de confort
	 * @param nom
	 * @param prenom
	 * @param adresse
	 * @param ville
	 * @param bonAchat
	 */
	public Client(String nom, String prenom, String adresse, String ville, int bonAchat) {
		super(nom, prenom, adresse, ville);
		this.bonAchat = bonAchat;
	}

	/**
	 * Constructeur de confort
	 * @param bonAchat
	 */
	public Client(int bonAchat) {
		super();
		this.bonAchat = bonAchat;
	}

	public int getBonAchat() {
		return bonAchat;
	}

	public void setBonAchat(int bonAchat) {
		this.bonAchat = bonAchat;
	}
	
	
}
