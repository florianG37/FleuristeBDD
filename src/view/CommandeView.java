package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.JTableHeader;

import controller.view.ClientControllerView;
import controller.view.CommandeControllerView;
import controller.view.table.ClientTableTemplate;
import controller.view.table.CommandeTableTemplate;

public class CommandeView extends JPanel
{
	private static JButton ajouterCommande, supprimerCommande, voirCommande;
	private static JTable table;
	private static CommandeTableTemplate modele = new CommandeTableTemplate();
	
	public CommandeView()
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
		ajouterCommande = new JButton ("Ajouter",new ImageIcon("images"+File.separator+"add.png"));
		verticalBox.add(ajouterCommande);
		
		//Creation bouton supprimer 
		supprimerCommande = new JButton ("Supprimer",new ImageIcon("images"+File.separator+"delete.png"));
		verticalBox.add(supprimerCommande);
		
		//Creation bouton modifier 
		voirCommande = new JButton ("Voir",new ImageIcon("images"+File.separator+"search.png"));
		verticalBox.add(voirCommande);
		
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
		new CommandeControllerView(); 
	}
	
	////////////////////ACTION LISTENER///////////////////////////////
	public static void ajouterCommandeListener(ActionListener listenAjouterCommande)
	{
		ajouterCommande.addActionListener(listenAjouterCommande);
	}
	
	public static void supprimerCommandeListener(ActionListener listenSupprimerCommande)
	{
		supprimerCommande.addActionListener(listenSupprimerCommande);
	}
	
	public static void voirCommandeListener(ActionListener listenVoirCommande)
	{
		voirCommande.addActionListener(listenVoirCommande);
	}

	//GETTERS ET SETTERS
	public static JTable getTable() {
		return table;
	}

	public static void setTable(JTable table) {
		CommandeView.table = table;
	}

	public static CommandeTableTemplate getModele() {
		return modele;
	}

	public static void setModele(CommandeTableTemplate modele) {
		CommandeView.modele = modele;
	}
}
