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

	public Annuaire( String fichierSource, String fichierSortie ) {

		this.cheminBinaire = fichierSortie ;
		gb = new GestionBinaire(fichierSource,cheminBinaire);


	}

	public List<Stagiaire> afficherTout() {
		return null ;
	}

	public List<Stagiaire> rechercher(String nom) {

		return null ;
	}

	private List<Stagiaire> rechercher(String nom, Noeud arbre , List<Stagiaire> liste){

		if (arbre == null) {
			return liste ;
		} else {
			if (nom.compareTo(arbre.getDonnee().getNom()) < 0 ) {
				liste = rechercher(nom , arbre.getFilsG() , liste);
			} else {
				if (nom.compareTo(arbre.getDonnee().getNom()) > 0 ) {
					liste = rechercher(nom , arbre.getFilsD() , liste);
				} else {
					liste.add(arbre.getDonnee());
					liste = rechercher ( nom , arbre.getFilsG() , liste);
					liste = rechercher ( nom , arbre.getFilsD() , liste);
				}
			}

		}
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
