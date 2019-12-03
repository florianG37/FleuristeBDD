package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import model.Produit;
import controller.queries.ProduitController;
import controller.view.AffilierFournisseurProduitControllerView;
import controller.view.table.AffilierFournisseurProduitTableTemplate;

public class AffilierFournisseurProduitView extends JFrame{
	private static JTable table;
	private ArrayList<Produit> listeProduit = ProduitController.voirProduit();
	private static  AffilierFournisseurProduitTableTemplate modele = new AffilierFournisseurProduitTableTemplate();
	private static TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(modele);
	private static int idFournisseur;
	private static JButton associer;
	
	public AffilierFournisseurProduitView(int idFournisseur){
		this.idFournisseur=idFournisseur;
		//Creation du panel principale
		this.setLayout(new BorderLayout(0, 0));
				
		//Creation panel de gauche pour mettre les boutons
		JPanel panel = new JPanel();
		this.add(panel, BorderLayout.WEST);
				
		//Creation boite verticale pour inserer les composants du panel de gauche
		Box verticalBox = Box.createVerticalBox();
		panel.add(verticalBox);
				
		//Creation bouton ajouter 
		associer = new JButton ("Associer/Dissocier",new ImageIcon("images"+File.separator+"link.png"));
		verticalBox.add(associer);
				
		JPanel depPanel = new JPanel();
		depPanel.setLayout(new BorderLayout(0, 0));
		this.add(depPanel);
					
		//Creation du tableau façonné à partir du modele, le tableau dans un scrollPane
		 table = new JTable(modele);
		 table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		 JScrollPane scrollPane = new JScrollPane(table);
		 depPanel.add(scrollPane);
					
		 ///////////////COLOR//////////////////////////				
		 panel.setForeground(new Color(16, 41, 46));
		panel.setBackground(new Color(16, 41, 46));
					
					
		JTableHeader headt = table.getTableHeader();
		headt.setBackground(new Color(204, 44, 111 ));
		headt.setForeground(new Color(255, 255, 255));
					
		scrollPane.getViewport().setBackground(new Color(213, 213, 213));
		table.setBackground(new Color(197, 230, 229 ));
					
		table.setAutoCreateRowSorter(true);
		table.setRowSorter(sorter);
		table.getRowSorter().toggleSortOrder(0);
				
		//this.add(panel);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
				
		//APPEL ACTION LISTENER
		new AffilierFournisseurProduitControllerView();
		modele.actualiserProduits();
	}
	////////////////////ACTION LISTENER///////////////////////////////
	public static void affilierFournisseurListener(ActionListener listenAffilierFournisseur)
	{
		associer.addActionListener(listenAffilierFournisseur);
	}

	//GETTERS ET SETTERS
	public static JTable getTable() {
		return table;
	}
	public void setTable(JTable table) {
		AffilierFournisseurProduitView.table = table;
	}
	public ArrayList<Produit> getListeProduit() {
		return listeProduit;
	}
	public void setListeProduit(ArrayList<Produit> listeProduit) {
		this.listeProduit = listeProduit;
	}
	public static  AffilierFournisseurProduitTableTemplate getModele() {
		return modele;
	}
	public static void setModele( AffilierFournisseurProduitTableTemplate modele) {
		AffilierFournisseurProduitView.modele = modele;
	}
	public static TableRowSorter<TableModel> getSorter() {
		return sorter;
	}
	public static void setSorter(TableRowSorter<TableModel> sorter) {
		AffilierFournisseurProduitView.sorter = sorter;
	}
	public static int getIdFournisseur() {
		return idFournisseur;
	}
	public static void setIdFournisseur(int idFournisseur) {
		AffilierFournisseurProduitView.idFournisseur = idFournisseur;
	}
}
