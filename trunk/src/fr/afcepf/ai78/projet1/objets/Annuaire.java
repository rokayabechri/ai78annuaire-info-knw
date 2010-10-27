package fr.afcepf.ai78.projet1.objets;

import java.util.List;

import fr.afcepf.ai78.projet1.fileManager.GestionBinaire;

public class Annuaire {

	/**
	 * @param args
	 */
	private String cheminBinaire = "" ;
	private GestionBinaire gb ;
	private List<Stagiaire> listePromo ;

	public Annuaire( String fichier ) {
		
		gb = new GestionBinaire(fichier);
		
		
	}

	public List<Stagiaire> afficherTout() {
		return null ;
	}

	public List<Stagiaire> rechercher(String nom) {
		return null ;
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
