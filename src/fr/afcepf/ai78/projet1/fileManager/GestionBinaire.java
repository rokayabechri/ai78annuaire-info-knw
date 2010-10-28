package fr.afcepf.ai78.projet1.fileManager;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
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
	private Noeud racine =null ; 

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

	public GestionBinaire(String fichierSource, String FichierSortie) {
		
		try {
			FileReader fr = new FileReader(fichierSource);
			BufferedReader br  = new BufferedReader(fr);
			fichier = new RandomAccessFile(FichierSortie, "rw");
			
			String ligne = "";
			int indiceTableau = 0;
			String []elements = new String [5];
			while((ligne=br.readLine())!=null){
				
				if(!ligne.equals("*")){
					
					elements[indiceTableau] = ligne;
					indiceTableau++;
					
				}else{
					
					boolean unBoolean = false;
					Stagiaire unStagiaire = new Stagiaire(elements[0], elements[1], elements[2], elements[3], Integer.parseInt(elements[4]));
					indiceTableau = 0;
					Noeud unNoeud = new Noeud(unStagiaire);
					
					if(!ajoutElementArbreBinaire(unNoeud,null,racine,unBoolean)){
						
						ecrire((int)fichier.length()/TAILLE_STAGIAIRE,unStagiaire);
						unNoeud.setPosition();
					}			
				}		
			}
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
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
		
	private boolean ajoutElementArbreBinaire(Noeud stagiaire, Noeud parent, Noeud arbre, Boolean existeDeja) {

	  if(arbre == null){
		  
		  arbre = stagiaire;
		  existeDeja = false;
		  arbre.setParent(parent);
		  
		  if(parent != null && parent.getDonnee().compareTo(arbre.getDonnee())<0){
			  
			  parent.setFilsD(arbre);
			  
		  }else if(parent != null){
			  
			  parent.setFilsG(arbre);  
		  }
		  
	  }else if(stagiaire.getDonnee().compareTo(arbre.getDonnee())<0){
		  
		  existeDeja = ajoutElementArbreBinaire(stagiaire, arbre, arbre.getFilsG(), existeDeja);
		  
	  }else if(stagiaire.getDonnee().compareTo(arbre.getDonnee())>0){
		  
		   existeDeja =  ajoutElementArbreBinaire(stagiaire, arbre, arbre.getFilsD(), existeDeja);
	  
	  }else{
		  
		  existeDeja = true;
	  }
	return existeDeja;
	}
	
}
