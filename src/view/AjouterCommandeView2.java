package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.JTableHeader;

import controller.view.AjouterCommandeControllerView2;
import controller.view.table.AjouterCommandeTableTemplate;
import controller.view.table.ProduitTableTemplate;
import model.Client;

public class AjouterCommandeView2 extends JFrame
{
	private JPanel panelwest = new JPanel();
	private static JButton supprimer, ajouter, enregistrer;
	private static JTable tableListeProduits, tablePanier;
	private JFrame thisFrame = this;
	private static ProduitTableTemplate modeleListeProduits = new ProduitTableTemplate();
	private static AjouterCommandeTableTemplate modelePanier  = new AjouterCommandeTableTemplate();
	private static JLabel montant, vuMontant, nom, prenom;
	private static Client client;
	
	public AjouterCommandeView2(Client client) {
		
		this.client = client;
		this.setTitle("Creer une commande");
		this.add(panelwest, BorderLayout.WEST);

		//Creation boite verticale pour inserer les composants du panel de gauche
		Box verticalBox = Box.createVerticalBox();
		Box horizontalBox = Box.createHorizontalBox();
		panelwest.add(verticalBox);
		prenom = new JLabel(client.getPrenom());
		nom = new JLabel(" "+client.getNom());
		supprimer = new JButton("Supprimer",new ImageIcon("images"+File.separator+"delete.png"));
		ajouter = new JButton("Ajouter",new ImageIcon("images"+File.separator+"add.png"));
		enregistrer = new JButton("Enregistrer",new ImageIcon("images"+File.separator+"panier.png"));
		montant = new JLabel("MONTANT HT Ap");
		vuMontant = new JLabel("0.0 €");
		verticalBox.add(horizontalBox);
		horizontalBox.add(prenom);
		horizontalBox.add(nom);
		verticalBox.add(supprimer);
		verticalBox.add(ajouter);
		verticalBox.add(enregistrer);
		verticalBox.add(montant);
		verticalBox.add(vuMontant);
		
		tableListeProduits = new JTable(modeleListeProduits);
		tableListeProduits.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JPanel panelCenter = new JPanel();
		this.add(panelCenter,BorderLayout.CENTER);
		
		JTableHeader headt = tableListeProduits.getTableHeader();
		headt.setBackground(new Color(204, 44, 111 ));
		headt.setForeground(new Color(255, 255, 255));
		tableListeProduits.setBackground(new Color(197, 230, 229 ));

		JPanel panelTableProduits = new JPanel();
		panelCenter.add(panelTableProduits);
		JScrollPane pane = new JScrollPane(tableListeProduits);
		panelTableProduits.add(pane);
		
		JPanel panelTablePanier = new JPanel();
		panelCenter.add(panelTablePanier);
		tablePanier = new JTable(modelePanier);
		tablePanier.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane pane2 = new JScrollPane(tablePanier);
		panelTablePanier.add(pane2);
	
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
		//ACTIVER ACTION LISTENER
		new AjouterCommandeControllerView2(thisFrame); 
		
		//Action listener quand on exit la fenetre
		addWindowListener(new WindowAdapter() 
		{
	        @Override
	        public void windowClosing(WindowEvent windowEvent) 
	        {
	            modelePanier.viderLePanier();
	            modeleListeProduits.actualiserProduits();
	        }
	    });
	}
	
	////////////////////ACTION LISTENER///////////////////////////////
	public static void ajouterProduitDansCommandeListener(ActionListener listenAjouterProduitDansCommande)
	{
		ajouter.addActionListener(listenAjouterProduitDansCommande);
	}
	
	public static void supprimerProduitDansCommandeListener(ActionListener listenSupprimerProduitDansCommande)
	{
		supprimer.addActionListener(listenSupprimerProduitDansCommande);
	}
	
	public static void enregistrerProduitDansCommandeListener(ActionListener listenEnregistrerProduitDansCommande)
	{
		enregistrer.addActionListener(listenEnregistrerProduitDansCommande);
	}
	
	//GETTERS ET SETTERS
	public static JTable getTableListeProduits() {
		return tableListeProduits;
	}

	public static void setTableListeProduits(JTable table) {
		AjouterCommandeView2.tableListeProduits = table;
	}

	public static JTable getTablePanier() {
		return tablePanier;
	}

	public static void setTablePanier(JTable tablePanier) {
		AjouterCommandeView2.tablePanier = tablePanier;
	}

	public static ProduitTableTemplate getModeleListeProduits() {
		return modeleListeProduits;
	}

	public static void setModeleListeProduits(ProduitTableTemplate modeleListeProduits) {
		AjouterCommandeView2.modeleListeProduits = modeleListeProduits;
	}

	public static AjouterCommandeTableTemplate getModelePanier() {
		return modelePanier;
	}

	public static void setModelePanier(AjouterCommandeTableTemplate modelePanier) {
		AjouterCommandeView2.modelePanier = modelePanier;
	}

	public static JLabel getVuMontant() {
		return vuMontant;
	}

	public static void setVuMontant(JLabel vuMontant) {
		AjouterCommandeView2.vuMontant = vuMontant;
	}

	public static Client getClient() {
		return client;
	}

	public static void setClient(Client client) {
		AjouterCommandeView2.client = client;
	}
}
