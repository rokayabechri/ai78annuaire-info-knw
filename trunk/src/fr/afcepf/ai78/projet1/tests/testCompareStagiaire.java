package fr.afcepf.ai78.projet1.tests;

import fr.afcepf.ai78.projet1.objets.Stagiaire;

public class testCompareStagiaire {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Stagiaire unStagiaire = new Stagiaire("kevin","augereau", "75000", "ai78", 1996);
		
		System.out.println(unStagiaire.compareTo(new Stagiaire("kevin","augereau", "75000", "ai78", 1996)));

	}

}
