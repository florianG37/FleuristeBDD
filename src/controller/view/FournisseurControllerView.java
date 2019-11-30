package controller.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import controller.queries.ClientController;
import controller.queries.FournisseurController;
import controller.queries.ProduitController;
import controller.view.table.ClientTableTemplate;
import controller.view.table.FournisseurTableTemplate;
import model.Client;
import model.Fournisseur;
import view.ClientView;
import view.FournisseurView;

public class FournisseurControllerView 
{
	public FournisseurControllerView()
	{
		FournisseurView.ajouterFournisseurListener(new AjouterFournisseurListener());  
		FournisseurView.supprimerFournisseurListener(new SupprimerFournisseurListener());
		FournisseurView.modifierFournisseurListener(new ModifierFournisseurListener());
	}
	
	class AjouterFournisseurListener implements ActionListener
	{
		private JTextField nom = new JTextField();
		private JTextField prenom = new JTextField();
		private JTextField adresse = new JTextField();
		private JTextField ville = new JTextField();
		private FournisseurTableTemplate modele = FournisseurView.getModele();
		public void actionPerformed(ActionEvent e)
		{
			Object[] fieldsAdd = {" Nom :", nom, 
					  "Prenom :", prenom,
					  "Adresse :",	adresse,
					  "Ville :", ville,
					  };
			int option = JOptionPane.showConfirmDialog(null, fieldsAdd, "Nouveau fournisseur", JOptionPane.OK_CANCEL_OPTION);
			if (option == JOptionPane.OK_OPTION) 
			{
				String nomF = nom.getText();
				String prenomF =prenom.getText();
				String adresseF = adresse.getText();
				String villeF = ville.getText();
				
						
				Fournisseur fournisseur = new Fournisseur(nomF,prenomF,adresseF,villeF);
						
				FournisseurController.ajouterFournisseur(fournisseur);
						
				modele.actualiserFournisseurs();
						
				nom.setText(null);
				prenom.setText(null);
				adresse.setText(null);
				ville.setText(null);
						
				}
		}
	}
	
	class ModifierFournisseurListener implements ActionListener
	{
		private JTextField nom = new JTextField();
		private JTextField prenom = new JTextField();
		private JTextField adresse = new JTextField();
		private JTextField ville = new JTextField();
		private FournisseurTableTemplate modele = FournisseurView.getModele();
		private JTable table = FournisseurView.getTable();
		public void actionPerformed(ActionEvent e)
		{
			int ligneSelectionee = table.getSelectedRow();
			//Si il y a une ligne selectionnee
			if(ligneSelectionee != -1)
			{
				Fournisseur fournisseur = modele.returnFournisseur(ligneSelectionee);
				int idFournisseurAModifier = fournisseur.getIdPersonne();
				Object[] fieldsAdd = {" Nom :", nom, 
						  "Prenom :", prenom,
						  "Adresse :",	adresse,
						  "Ville :", ville, 
						  };
				nom.setText(fournisseur.getNom());
				prenom.setText(fournisseur.getPrenom());
				adresse.setText(fournisseur.getAdresse());
				ville.setText(fournisseur.getVille());
				int option = JOptionPane.showConfirmDialog(null, fieldsAdd, "modifier fournisseur", JOptionPane.OK_CANCEL_OPTION);
				if (option == JOptionPane.OK_OPTION) 
				{
					String nomF = nom.getText();
					String prenomF =prenom.getText();
					String adresseF = adresse.getText();
					String villeF = ville.getText();
					
					Fournisseur fournisseur2 = new Fournisseur(nomF,prenomF,adresseF,villeF);
					
					FournisseurController.modifierFournisseur(fournisseur2, idFournisseurAModifier);		
					modele.actualiserFournisseurs();

					nom.setText(null);
					prenom.setText(null);
					adresse.setText(null);
					ville.setText(null);	
					}
			}else{
				JOptionPane.showMessageDialog(null, "Selectionner une ligne", "Erreur", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	class SupprimerFournisseurListener implements ActionListener
	{
		private JTable table = FournisseurView.getTable();
		private FournisseurTableTemplate modele = FournisseurView.getModele();
		
		public void actionPerformed(ActionEvent e)
		{
			int ligneSelectionee = table.getSelectedRow();
			//Si il y a une ligne selectionnee
			if(ligneSelectionee != -1)
			{
				Fournisseur fournisseur = modele.returnFournisseur(ligneSelectionee);
				
				FournisseurController.supprimerFournisseur(fournisseur.getIdPersonne());
				
				modele.actualiserFournisseurs();
			}
		}
	}
}
