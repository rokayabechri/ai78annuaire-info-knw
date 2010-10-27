package fr.afcepf.ai78.projet1.fileManager;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.List;

import fr.afcepf.ai78.projet1.objets.Noeud;
import fr.afcepf.ai78.projet1.objets.Stagiaire;

public class GestionBinaire {

	private final int TAILLE_NOM = 60 ;			//Integer = 50 octets
	private final int TAILLE_PRENOM = 60 ;		//Integer = 30 octets
	private final int TAILLE_DEPARTEMENT = 4 ;	//Integer = 25 octets
	private final int TAILLE_PROMOTION = 20 ;	//Integer = 20 octets

	private final int TAILLE_STAGIAIRE = (TAILLE_NOM + TAILLE_PRENOM + TAILLE_DEPARTEMENT + TAILLE_PROMOTION + 4);
	
	private RandomAccessFile fichier ;
	private Noeud racine ; 

	private String formater (int taille ,String chaine){

	} 

	private String lireChaine(int taille){

	}

	public static void gestionBinaire() {
		
		public Stagiaire lireStagiaire(int indice) {

		}
		
		public Stagiaire ecrire(int indice, Stagiaire ajout) {

		}

	}
}
