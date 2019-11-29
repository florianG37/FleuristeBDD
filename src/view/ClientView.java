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

import controller.view.ClientControllerView;
import controller.view.table.ClientTableTemplate;

public class ClientView extends JPanel
{
	private static JButton ajouterClient, supprimerClient, modifierClient;
	private static JTable table;
	private static ClientTableTemplate modele = new ClientTableTemplate();
	
	public ClientView()
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
		ajouterClient = new JButton ("Ajouter");
		verticalBox.add(ajouterClient);
		
		//Creation bouton supprimer 
		supprimerClient = new JButton ("Supprimer");
		verticalBox.add(supprimerClient);
		
		//Creation bouton modifier 
		modifierClient = new JButton ("Modifier");
		verticalBox.add(modifierClient);
		
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
		new ClientControllerView(); 
	}
	
	////////////////////ACTION LISTENER///////////////////////////////
	public static void ajouterClientListener(ActionListener listenAjouterClient)
	{
		ajouterClient.addActionListener(listenAjouterClient);
	}
	
	public static void supprimerClientListener(ActionListener listenSupprimerClient)
	{
		supprimerClient.addActionListener(listenSupprimerClient);
	}
	
	public static void modifierClientListener(ActionListener listenModifierClient)
	{
		modifierClient.addActionListener(listenModifierClient);
	}

	//GETTERS ET SETTERS
	public static JTable getTable() {
		return table;
	}

	public static void setTable(JTable table) {
		ClientView.table = table;
	}

	public static ClientTableTemplate getModele() {
		return modele;
	}

	public static void setModele(ClientTableTemplate modele) {
		ClientView.modele = modele;
	}
}
