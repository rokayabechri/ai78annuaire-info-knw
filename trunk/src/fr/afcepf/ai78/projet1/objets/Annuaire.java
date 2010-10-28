package fr.afcepf.ai78.projet1.objets;

import java.util.ArrayList;
import java.util.List;

import fr.afcepf.ai78.projet1.fileManager.GestionBinaire;

public class Annuaire {

	/**
	 * @param args
	 */
	private String cheminBinaire = "" ;
	private GestionBinaire gb ;
	private List<String> listePromo ;

	public Annuaire( String fichierSource, String fichierSortie ) {

		this.cheminBinaire = fichierSortie ;
		gb = new GestionBinaire(fichierSource,cheminBinaire);


	}

	public List<Stagiaire> afficherTout() {
		return gb.afficherTout(0, true, true, new ArrayList<Stagiaire>()) ;
	}

	public List<Stagiaire> rechercher(String nom) {
		return gb.rechercher(nom,0,new ArrayList<Stagiaire>());
	}

	

	public List<Stagiaire> lister(String promotion) {
		return null ;
	}

	public boolean ajouter(Stagiaire st) {
		return true ;
	}

	public boolean ajouter(String nom ,String prenom ,String departement ,String promotion ,int annee) {
		return true ;
	}

	public boolean ajouter(List<Stagiaire> st) {
		return true ;
	}

	public boolean supprimer(Stagiaire st) {
		return true ;
	}

	public boolean modifier( Stagiaire modifier ,int indice ) {
		return true ;
	}


}
