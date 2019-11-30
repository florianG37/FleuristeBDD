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

import controller.view.CommandeControllerView;
import controller.view.FournitureControllerView;
import controller.view.table.CommandeTableTemplate;
import controller.view.table.FournitureTableTemplate;

public class FournitureView extends JPanel 
{
	private static JButton ajouterFourniture, supprimerFourniture, modifierFourniture;
	private static JTable table;
	private static FournitureTableTemplate modele = new FournitureTableTemplate();
	
	public FournitureView()
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
		ajouterFourniture = new JButton ("Ajouter");
		verticalBox.add(ajouterFourniture);
		
		//Creation bouton supprimer 
		supprimerFourniture = new JButton ("Supprimer");
		verticalBox.add(supprimerFourniture);
		
		//Creation bouton modifier 
		modifierFourniture = new JButton ("Modifier");
		verticalBox.add(modifierFourniture);
		
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
		
		//ACTIVER ACTION LISTENER
		new FournitureControllerView(); 
	}
	
	////////////////////ACTION LISTENER///////////////////////////////
	public static void ajouterFournitureListener(ActionListener listenAjouterFourniture)
	{
		ajouterFourniture.addActionListener(listenAjouterFourniture);
	}
	
	public static void supprimerFournitureListener(ActionListener listenSupprimerFourniture)
	{
		supprimerFourniture.addActionListener(listenSupprimerFourniture);
	}
	
	public static void modifierFournitureListener(ActionListener listenModifierFourniture)
	{
		modifierFourniture.addActionListener(listenModifierFourniture);
	}

	//GETTERS ET SETTERS
	public static JTable getTable() {
		return table;
	}

	public static void setTable(JTable table) {
		FournitureView.table = table;
	}

	public static FournitureTableTemplate getModele() {
		return modele;
	}

	public static void setModele(FournitureTableTemplate modele) {
		FournitureView.modele = modele;
	}
}
