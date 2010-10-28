package fr.afcepf.ai78.projet1.tests;

import java.util.ArrayList;
import java.util.List;

import fr.afcepf.ai78.projet1.fileManager.GestionBinaire;
import fr.afcepf.ai78.projet1.objets.Noeud;
import fr.afcepf.ai78.projet1.objets.Stagiaire;

public class testCreationAnnuaire {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		GestionBinaire gb = new GestionBinaire("c:/STAGIAIRES.DON", "c:/STAGIAIRES.bin");
		List<Stagiaire> liste = new ArrayList<Stagiaire>();
		//liste= gb.lireFichier(); 
		//liste = gb.afficherTout(0, true, true, liste);
		liste = gb.rechercher("Lepante",0 , liste);
		
		
		for (Stagiaire noeud : liste) {
			System.out.println(noeud);
		}
	}

}
