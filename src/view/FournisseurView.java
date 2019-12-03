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
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import controller.view.FournisseurControllerView;
import controller.view.table.FournisseurTableTemplate;

public class FournisseurView extends JPanel
{
	private static JButton ajouterFournisseur, supprimerFournisseur, modifierFournisseur,affilier,filter, clearFilter;
	private static JTable table;
	private static FournisseurTableTemplate modele = new FournisseurTableTemplate();
	private static TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(modele);
	
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
		ajouterFournisseur = new JButton ("Ajouter",new ImageIcon("images"+File.separator+"add.png"));
		verticalBox.add(ajouterFournisseur);
		
		//Creation bouton supprimer 
		supprimerFournisseur = new JButton ("Supprimer",new ImageIcon("images"+File.separator+"delete.png"));
		verticalBox.add(supprimerFournisseur);
		
		//Creation bouton modifier 
		modifierFournisseur = new JButton ("Modifier",new ImageIcon("images"+File.separator+"modifier.png"));
		verticalBox.add(modifierFournisseur);
		//Creation bouton Affilier 
		affilier = new JButton ("Affilier",new ImageIcon("images"+File.separator+"link.png"));
		verticalBox.add(affilier);
		
		//Creation bouton filtre 
		filter = new JButton ("filtre",new ImageIcon("images"+File.separator+"filtre.png"));
		verticalBox.add(filter);
		
		//Creation bouton clearFilter 
		clearFilter = new JButton ("Enlever Filtre",new ImageIcon("images"+File.separator+"clear.png"));
		verticalBox.add(clearFilter);
		
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
	public static void affilierListener(ActionListener listenAffilier){
		affilier.addActionListener(listenAffilier);
	}
	public static void filterListener(ActionListener listenFilter)
	{
		filter.addActionListener(listenFilter);
	}
	public static void clearFilterListener(ActionListener listenClearFilter)
	{
		clearFilter.addActionListener(listenClearFilter);
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

	public static TableRowSorter<TableModel> getSorter() {
		return sorter;
	}

	public static void setSorter(TableRowSorter<TableModel> sorter) {
		FournisseurView.sorter = sorter;
	}
	
}
