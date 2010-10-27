package fr.afcepf.ai78.projet1.fileManager;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.List;
import fr.afcepf.ai78.projet1.objets.Noeud;
import fr.afcepf.ai78.projet1.objets.Stagiaire;

public class GestionBinaire {

	private final int TAILLE_NOM = 60 ;			//Integer = 60 octets
	private final int TAILLE_PRENOM = 60 ;		//Integer = 30 octets
	private final int TAILLE_DEPARTEMENT = 4 ;	//Integer = 25 octets
	private final int TAILLE_PROMOTION = 20 ;	//Integer = 20 octets

	private final int TAILLE_STAGIAIRE = (TAILLE_NOM + TAILLE_PRENOM + TAILLE_DEPARTEMENT + TAILLE_PROMOTION + 4);
	
	private RandomAccessFile fichier ;
	private Noeud racine ; 

	private String formater (int taille ,String chaine){
		
		for (int i = chaine.length(); i < taille/2; i++) {
			
			chaine = chaine+ " ";
		}
		return chaine;
	} 

	private String lireChaine(int taille){
		
		String chaine="";
		
		try {
			byte [] tableauOctet = new byte [taille];
			fichier.read(tableauOctet);
			chaine = new String(tableauOctet,"UTF-16").trim();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return chaine;

	}

	public GestionBinaire(String fichierBinaire) {
		
		try {
			fichier = new RandomAccessFile(fichierBinaire, "rw");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	public Stagiaire lireStagiaire(int indice) {
		
		Stagiaire unStagiaire = new Stagiaire();
		try {
			
			fichier.seek(indice*TAILLE_STAGIAIRE);

			unStagiaire.setNom(lireChaine(TAILLE_NOM));
			unStagiaire.setPrenom(lireChaine(TAILLE_PRENOM));
			unStagiaire.setPromotion(lireChaine(TAILLE_PROMOTION));
			unStagiaire.setDepartement(lireChaine(TAILLE_DEPARTEMENT));
			
			int annee = fichier.readInt();
			unStagiaire.setAnnee(annee);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return unStagiaire;
	}
	
	public void ecrire(int indice, Stagiaire ajout) {
		
		try {
			fichier.seek(indice*TAILLE_STAGIAIRE);
			fichier.writeChars(formater(TAILLE_NOM,ajout.getNom()));
			fichier.writeChars(formater(TAILLE_PRENOM,ajout.getPrenom()));
			fichier.writeChars(formater(TAILLE_PROMOTION,ajout.getPromotion()));
			fichier.writeChars(formater(TAILLE_DEPARTEMENT,ajout.getDepartement()));
			fichier.writeInt(ajout.getAnnee());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
