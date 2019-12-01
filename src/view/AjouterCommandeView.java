package view;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.JTableHeader;

import controller.view.table.AjouterCommandeTableTemplate;
import controller.view.table.ProduitTableTemplate;

public class AjouterCommandeView extends JFrame
{
	private JPanel panelwest = new JPanel();
	private JButton supprimer, modifier, ajouter;
	private JTable table, tablePanier;
	private JFrame thisFrame = this;
	private ProduitTableTemplate modeleListeProduits = new ProduitTableTemplate();
	private AjouterCommandeTableTemplate modelePanier  = new AjouterCommandeTableTemplate();
	
	public AjouterCommandeView() {
		this.setTitle("Creer une commande");
		this.add(panelwest, BorderLayout.WEST);

		//Creation boite verticale pour inserer les composants du panel de gauche
		Box verticalBox = Box.createVerticalBox();
		panelwest.add(verticalBox);
		supprimer = new JButton("Supprimer");
		modifier = new JButton("Modifier");
		ajouter = new JButton("Ajouter");
		verticalBox.add(supprimer);
		verticalBox.add(modifier);
		verticalBox.add(ajouter);
	
		table = new JTable(modeleListeProduits);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JPanel panelCenter = new JPanel();
		this.add(panelCenter,BorderLayout.CENTER);
		
		JTableHeader headt = table.getTableHeader();
		headt.setBackground(new Color(204, 44, 111 ));
		headt.setForeground(new Color(255, 255, 255));
		table.setBackground(new Color(197, 230, 229 ));

		JPanel panelTableProduits = new JPanel();
		panelCenter.add(panelTableProduits);
		JScrollPane pane = new JScrollPane(table);
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
	}
}
