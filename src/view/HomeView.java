package view;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class HomeView extends JFrame{
	
	private static JTabbedPane tabbedPane;
	private JPanel produitPanel, clientPanel, commandePanel, fourniturePanel, fournisseurPanel;
	
	public HomeView() {
		
		this.setTitle("Application de gestion de stock");
	
		//Couleur gris du content pane
		this.getContentPane().setBackground(new Color(16, 41, 46));
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(new BorderLayout(0, 0));
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		this.getContentPane().add(tabbedPane);
		
		//Couleur du tabbedPaned
		tabbedPane.setBackground(new Color(20, 159, 152));
		tabbedPane.setForeground(Color.BLACK);
		
		//Ajout du panel produit dans le tabbedPane
		produitPanel = new ProduitView();
		tabbedPane.addTab("Produit", null, produitPanel, null);
		
		//Ajout du panel client dans le tabbedPane
		clientPanel = new ClientView();
		tabbedPane.addTab("Client", null, clientPanel, null);
		
		//Ajout du panel fournisseur dans le tabbedPane
		fournisseurPanel = new FournisseurView();
		tabbedPane.addTab("Fournisseur", null, fournisseurPanel, null);
		
		//Ajout du panel commande dans le tabbedPane
		commandePanel = new CommandeView();
		tabbedPane.addTab("Commande", null, commandePanel, null);
		
		//Ajout du panel fourniture dans le tabbedPane
		fourniturePanel = new FournitureView();
		tabbedPane.addTab("Fourniture", null, fourniturePanel, null);
		
		//Centrer la fenetre
		this.setLocationRelativeTo(null);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setVisible(true);
		}
}
