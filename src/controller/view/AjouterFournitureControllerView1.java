package controller.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import controller.view.table.FournisseurTableTemplate;
import model.Fournisseur;
import view.AjouterCommandeView1;
import view.AjouterFournitureView1;
import view.AjouterFournitureView2;

public class AjouterFournitureControllerView1 
{
	private JFrame frame;
	/**
	 * Constructeur
	 */
	public AjouterFournitureControllerView1(JFrame frame)
	{
		this.frame = frame;
		AjouterFournitureView1.validerFournisseurListener(new ValiderFournitureListener());  
		AjouterFournitureView1.filterListener(new FilterListener());
		AjouterFournitureView1.clearFilterListener(new ClearFilterListener());

	}
	/**
	 * 
	 *valider Fournisseur
	 *
	 */
	class ValiderFournitureListener implements ActionListener
	{
		private JTable table = AjouterFournitureView1.getTable();
		private FournisseurTableTemplate modele = AjouterFournitureView1.getModele();
		
		public void actionPerformed(ActionEvent arg0) 
		{
			int ligneSelectionnee = table.getSelectedRow();
			
			if(ligneSelectionnee != -1 )
			{
				ligneSelectionnee = table.getRowSorter().convertRowIndexToModel(ligneSelectionnee);
				Fournisseur fournisseur = modele.returnFournisseur(ligneSelectionnee);
				
				//Ferme la fenetre
				frame.dispose();
				//Nouvelle vue
				new AjouterFournitureView2(fournisseur);
			}
			else
			{
				JOptionPane.showMessageDialog(null,"Selectionnez une ligne");
			}
		}
	}
	/**
	 * 
	 * Activer filtre
	 *
	 */
	class FilterListener implements ActionListener
	{
		private TableRowSorter<TableModel> sorter = AjouterCommandeView1.getSorter();
		@Override
		public void actionPerformed(ActionEvent arg0) {
			String regex = JOptionPane.showInputDialog("Recherchez par : ");
	        sorter.setRowFilter(RowFilter.regexFilter("(?i)"+regex, 0, 1, 2, 3,4));
		}
	}
	/**
	 * 
	 *Enlever filtre
	 *
	 */
	class ClearFilterListener implements ActionListener
	{
		private TableRowSorter<TableModel> sorter = AjouterCommandeView1.getSorter();
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(sorter != null)
			{
				sorter.setRowFilter(null);
			}
		}
	}
}
