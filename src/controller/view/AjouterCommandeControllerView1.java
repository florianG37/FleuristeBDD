package controller.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import controller.view.table.ClientTableTemplate;
import model.Client;
import view.AjouterCommandeView1;
import view.AjouterCommandeView2;

public class AjouterCommandeControllerView1 
{
	private JFrame frame;
	public AjouterCommandeControllerView1(JFrame frame)
	{
		this.frame = frame;
		AjouterCommandeView1.validerClientListener(new ValiderClientListener());  
		AjouterCommandeView1.filterListener(new FilterListener());
		AjouterCommandeView1.clearFilterListener(new ClearFilterListener());

	}
	
	class ValiderClientListener implements ActionListener
	{
		private JTable table = AjouterCommandeView1.getTable();
		private ClientTableTemplate modele = AjouterCommandeView1.getModele();
		
		public void actionPerformed(ActionEvent arg0) 
		{
			int ligneSelectionnee = table.getRowSorter().convertRowIndexToModel(table.getSelectedRow());
			
			Client client = modele.returnClient(ligneSelectionnee);
			
			//Ferme la fenetre
			frame.dispose();
			
			//Nouvelle vue
			new AjouterCommandeView2(client);
		}
	}
	
	class FilterListener implements ActionListener
	{
		private TableRowSorter<TableModel> sorter = AjouterCommandeView1.getSorter();
		@Override
		public void actionPerformed(ActionEvent arg0) {
			String regex = JOptionPane.showInputDialog("Filter by : ");
	        sorter.setRowFilter(RowFilter.regexFilter("(?i)"+regex, 0, 1, 2, 3,4));
		}
	}
	
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
