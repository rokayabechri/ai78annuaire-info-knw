package fr.afcepf.ai78.projet1.interfaces;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import fr.afcepf.ai78.projet1.objets.Stagiaire;

public class ModeleStagiaire extends AbstractTableModel {


	private String[] entete = {"Nom","Prénom","Promotion","Année","Département"};
	private List<Stagiaire> donnees = new ArrayList<Stagiaire>();
	
	public ModeleStagiaire(){
	}
	
	public ModeleStagiaire(List<Stagiaire> donnees){
		this.donnees = donnees;
	}
	
	@Override
	public int getColumnCount() {
		return entete.length;
	}

	@Override
	public int getRowCount() {

		return donnees.size();
	}

	@Override
	public Object getValueAt(int row, int column) {
		Object result = null;
		switch (column) {
			case 0:
				result = donnees.get(row).getNom();
				break;
			case 1:
				result = donnees.get(row).getPrenom();
				break;
			case 2:
				result = donnees.get(row).getPromotion();
				break;
			case 3:
				result = donnees.get(row).getAnnee();
				break;
			case 4:
				result = donnees.get(row).getDepartement();
				break;

		}
		return result;
	}
	
	@Override
	public String getColumnName(int column) {
		return entete[column];
	}
	

}
