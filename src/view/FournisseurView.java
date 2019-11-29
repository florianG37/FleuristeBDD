package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.JTableHeader;

import controller.view.FournisseurControllerView;
import controller.view.table.FournisseurTableTemplate;

public class FournisseurView extends JPanel
{
	private static JButton ajouterFournisseur, supprimerFournisseur, modifierFournisseur;
	private static JTable table;
	private static FournisseurTableTemplate modele = new FournisseurTableTemplate();
	
	public FournisseurView()
	{
		//Creation du panel principale
		this.setLayout(new BorderLayout(0, 0));
		
		//Creation panel de gauche pour mettre les boutons
		JPanel panel = new JPanel();
		this.add(panel, BorderLayout.WEST);
		
		//Creation boite verticale pour inserer les composants du panel de gauche
		Box verticalBox = Box.createVerticalBox();
		panel.add(verticalBox);
		
		//Creation bouton ajouter 
		ajouterFournisseur = new JButton ("Ajouter");
		verticalBox.add(ajouterFournisseur);
		
		//Creation bouton supprimer 
		supprimerFournisseur = new JButton ("Supprimer");
		verticalBox.add(supprimerFournisseur);
		
		//Creation bouton modifier 
		modifierFournisseur = new JButton ("Modifier");
		verticalBox.add(modifierFournisseur);
		
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
		
		//APPEL ACTION LISTENER
		new FournisseurControllerView(); 
	}
	
	////////////////////ACTION LISTENER///////////////////////////////
	public static void ajouterFournisseurListener(ActionListener listenAjouterFournisseur)
	{
		ajouterFournisseur.addActionListener(listenAjouterFournisseur);
	}
	
	public static void supprimerFournisseurListener(ActionListener listenSupprimerFournisseur)
	{
		supprimerFournisseur.addActionListener(listenSupprimerFournisseur);
	}
	
	public static void modifierFournisseurListener(ActionListener listenModifierFournisseur)
	{
		modifierFournisseur.addActionListener(listenModifierFournisseur);
	}

	//GETTERS ET SETTERS
	public static JTable getTable() {
		return table;
	}

	public static void setTable(JTable table) {
		FournisseurView.table = table;
	}

	public static FournisseurTableTemplate getModele() {
		return modele;
	}

	public static void setModele(FournisseurTableTemplate modele) {
		FournisseurView.modele = modele;
	}
}
