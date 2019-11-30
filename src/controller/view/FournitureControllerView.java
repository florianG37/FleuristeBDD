package controller.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTable;

import controller.queries.CommandeController;
import controller.queries.FournitureController;
import controller.view.table.FournitureTableTemplate;
import model.Fourniture;
import view.FournitureView;

public class FournitureControllerView 
{
	public FournitureControllerView()
	{
		FournitureView.ajouterFournitureListener(new AjouterFournitureListener());  
		FournitureView.supprimerFournitureListener(new SupprimerFournitureListener());
		FournitureView.modifierFournitureListener(new ModifierFournitureListener());
	}
	
	class AjouterFournitureListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			System.out.println("J'ai cliqué sur ajouter une fourniture");
		}
	}
	
	class ModifierFournitureListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			System.out.println("J'ai cliqué sur modifier fourniture");
		}
	}
	
	class SupprimerFournitureListener implements ActionListener
	{
		private JTable table = FournitureView.getTable();
		private FournitureTableTemplate modele = FournitureView.getModele();
		
		public void actionPerformed(ActionEvent e)
		{
			int ligneSelectionee = table.getSelectedRow();
			//Si il y a une ligne selectionnee
			if(ligneSelectionee != -1)
			{
				Fourniture fourniture = modele.returnFourniture(ligneSelectionee);
				
				FournitureController.supprimerFourniture(fourniture.getId());
				
				modele.actualiserFournitures();
			}
		}
	}
}
