package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.JTableHeader;

import controller.view.AjouterCommandeControllerView;
import controller.view.table.AjouterCommandeTableTemplate;
import controller.view.table.ProduitTableTemplate;

public class AjouterCommandeView extends JFrame
{
	private JPanel panelwest = new JPanel();
	private static JButton supprimer, modifier, ajouter, enregistrer;
	private static JTable tableListeProduits, tablePanier;
	private JFrame thisFrame = this;
	private static ProduitTableTemplate modeleListeProduits = new ProduitTableTemplate();
	private static AjouterCommandeTableTemplate modelePanier  = new AjouterCommandeTableTemplate();
	
	public AjouterCommandeView() {
		this.setTitle("Creer une commande");
		this.add(panelwest, BorderLayout.WEST);

		//Creation boite verticale pour inserer les composants du panel de gauche
		Box verticalBox = Box.createVerticalBox();
		panelwest.add(verticalBox);
		supprimer = new JButton("Supprimer");
		modifier = new JButton("Modifier");
		ajouter = new JButton("Ajouter");
		enregistrer = new JButton("Enregistrer");
		verticalBox.add(supprimer);
		verticalBox.add(modifier);
		verticalBox.add(ajouter);
		verticalBox.add(enregistrer);
	
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
		new AjouterCommandeControllerView(); 
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
	
	public static void modifierProduitDansCommandeListener(ActionListener listenModifierProduitDansCommande)
	{
		modifier.addActionListener(listenModifierProduitDansCommande);
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
		AjouterCommandeView.tableListeProduits = table;
	}

	public static JTable getTablePanier() {
		return tablePanier;
	}

	public static void setTablePanier(JTable tablePanier) {
		AjouterCommandeView.tablePanier = tablePanier;
	}

	public static ProduitTableTemplate getModeleListeProduits() {
		return modeleListeProduits;
	}

	public static void setModeleListeProduits(ProduitTableTemplate modeleListeProduits) {
		AjouterCommandeView.modeleListeProduits = modeleListeProduits;
	}

	public static AjouterCommandeTableTemplate getModelePanier() {
		return modelePanier;
	}

	public static void setModelePanier(AjouterCommandeTableTemplate modelePanier) {
		AjouterCommandeView.modelePanier = modelePanier;
	}
}
