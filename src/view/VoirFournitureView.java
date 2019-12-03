package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog;
import java.io.File;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.JTableHeader;

import controller.view.table.VoirFournitureTableTemplate;

public class VoirFournitureView extends JFrame
{
	private JPanel panelwest = new JPanel();
	private JButton supprimer;
	private JTable table;
	
	public VoirFournitureView(VoirFournitureTableTemplate modele) {
		this.setTitle("Vu de la fourniture");
		
		table = new JTable(modele);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JTableHeader headt = table.getTableHeader();
		headt.setBackground(new Color(204, 44, 111 ));
		headt.setForeground(new Color(255, 255, 255));
		table.setBackground(new Color(197, 230, 229 ));

		JScrollPane pane = new JScrollPane(table);
		
		this.add(pane);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
}
