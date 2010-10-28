package fr.afcepf.ai78.projet1.tests;

import java.util.ArrayList;
import java.util.List;

import fr.afcepf.ai78.projet1.fileManager.GestionBinaire;
import fr.afcepf.ai78.projet1.objets.Noeud;

public class testCreationAnnuaire {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		GestionBinaire gb = new GestionBinaire("STAGIAIRES.DON", "c:/STAGIAIRES.bin");
		List<Noeud> liste = new ArrayList<Noeud>();
		//liste= gb.lireFichier(); 
		liste = gb.afficherTout(0, true, true, liste);
		
		
		for (Noeud noeud : liste) {
			System.out.println(noeud);
		}
	}

}
