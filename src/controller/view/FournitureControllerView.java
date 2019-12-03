package controller.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTable;

import controller.queries.FournitureController;
import controller.view.table.FournitureTableTemplate;
import controller.view.table.VoirCommandeTableTemplate;
import controller.view.table.VoirFournitureTableTemplate;
import model.Fourniture;
import view.AjouterFournitureView1;
import view.FournitureView;
import view.VoirFournitureView;

public class FournitureControllerView 
{
	public FournitureControllerView()
	{
		FournitureView.ajouterFournitureListener(new AjouterFournitureListener());  
		FournitureView.supprimerFournitureListener(new SupprimerFournitureListener());
		FournitureView.voirFournitureListener(new VoirFournitureListener());
	}
	
	class AjouterFournitureListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{	
			new AjouterFournitureView1();
		}
	}
	
	class VoirFournitureListener implements ActionListener
	{
			private JTable table = FournitureView.getTable();
			private FournitureTableTemplate modele = FournitureView.getModele();
			
			public void actionPerformed(ActionEvent e)
			{
				int ligneSelectionnee = table.getSelectedRow();
				if(ligneSelectionnee != -1)
				{
					Fourniture fourniture = modele.returnFourniture(ligneSelectionnee);
					
					//Créé le modele de table a partir de la commande
					VoirFournitureTableTemplate modeleVoir = new VoirFournitureTableTemplate(fourniture);
					new VoirFournitureView(modeleVoir);
				}
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
