package fr.afcepf.ai78.projet1.fileManager;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import fr.afcepf.ai78.projet1.objets.Noeud;
import fr.afcepf.ai78.projet1.objets.Stagiaire;

public class GestionBinaire {

	private static final int TAILLE_NOM = 60 ;			//Integer = 60 octets
	private static final int TAILLE_PRENOM = 60 ;		//Integer = 30 octets
	private static final int TAILLE_DEPARTEMENT = 4 ;	//Integer = 25 octets
	private static final int TAILLE_PROMOTION = 20 ;	//Integer = 20 octets
	private static final int TAILLE_STAGIAIRE = (TAILLE_NOM + TAILLE_PRENOM + TAILLE_DEPARTEMENT + TAILLE_PROMOTION + 4);

	private final int TAILLE_NOEUD = (TAILLE_NOM + TAILLE_PRENOM + TAILLE_DEPARTEMENT + TAILLE_PROMOTION + 4+4+4+4);
	
	private RandomAccessFile fichier ;
	

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
	
	public List<Noeud> lireFichier(){
		List<Noeud> liste = new ArrayList<Noeud>();
		try {
			for (int i = 0; i < fichier.length()/TAILLE_NOEUD; i++) {
				liste.add(lireNoeud(i));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return liste;
	}

	
	private void ecrireInt(int position, int element){
		
		try {
			fichier.seek(position);
			fichier.writeInt(element);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
					Noeud unNoeud = new Noeud(elements[0], elements[1], elements[2], elements[3], Integer.parseInt(elements[4]));
					indiceTableau = 0;
					//System.out.println("Ajout arbre");
					boolean doublon = ajoutElementArbreBinaire(unNoeud,-1,0,unBoolean);
					
					if(!doublon){
						//System.out.println("ecriture");
						fichier.seek(fichier.length());
						ecrireNoeud(unNoeud);
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
	
	public Noeud lireNoeud(int indice) {
		
		Noeud unNoeud = new Noeud();
		try {
			
			fichier.seek(indice*TAILLE_NOEUD);

			unNoeud.setNom(lireChaine(TAILLE_NOM));
			unNoeud.setPrenom(lireChaine(TAILLE_PRENOM));
			unNoeud.setPromotion(lireChaine(TAILLE_PROMOTION));
			unNoeud.setDepartement(lireChaine(TAILLE_DEPARTEMENT));
			unNoeud.setAnnee(fichier.readInt());
			unNoeud.setParent(fichier.readInt());
			unNoeud.setFilsG(fichier.readInt());
			unNoeud.setFilsD(fichier.readInt());

			
		} catch (IOException e) {
			//e.printStackTrace();
			return null;
		}

		return unNoeud;
	}
	
	public void ecrireNoeud(int indice, Noeud ajout) {
		
		try {
			fichier.seek(indice*TAILLE_NOEUD);
			fichier.writeChars(formater(TAILLE_NOM,ajout.getNom()));
			fichier.writeChars(formater(TAILLE_PRENOM,ajout.getPrenom()));
			fichier.writeChars(formater(TAILLE_PROMOTION,ajout.getPromotion()));
			fichier.writeChars(formater(TAILLE_DEPARTEMENT,ajout.getDepartement()));
			fichier.writeInt(ajout.getAnnee());
			fichier.writeInt(ajout.getParent());
			fichier.writeInt(ajout.getFilsG());
			fichier.writeInt(ajout.getFilsD());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void ecrireNoeud(Noeud ajout) {
		
		try {
			fichier.writeChars(formater(TAILLE_NOM,ajout.getNom()));
			fichier.writeChars(formater(TAILLE_PRENOM,ajout.getPrenom()));
			fichier.writeChars(formater(TAILLE_PROMOTION,ajout.getPromotion()));
			fichier.writeChars(formater(TAILLE_DEPARTEMENT,ajout.getDepartement()));
			fichier.writeInt(ajout.getAnnee());
			fichier.writeInt(ajout.getParent());
			fichier.writeInt(ajout.getFilsG());
			fichier.writeInt(ajout.getFilsD());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private boolean ajoutElementArbreBinaire(Noeud stagiaire, int posParent, int posArbre, Boolean existeDeja) throws IOException {

		//System.out.println("###############################");
		Noeud parent = lireNoeud(posParent);
		Noeud arbre = lireNoeud(posArbre);
//		System.out.println("Stagiaire: "+stagiaire);
//		System.out.println("Parent: "+parent);
//		System.out.println("Arbre: "+arbre);
		if(arbre == null){
			stagiaire.setParent(posParent);
			existeDeja = false;
			
			if(parent != null){
				if(parent.compareTo(stagiaire)<0){
					ecrireInt((stagiaire.getParent()*TAILLE_NOEUD)+TAILLE_STAGIAIRE+4+4,(int)(fichier.length()/TAILLE_NOEUD));
					//System.out.println("Droite :"+lireNoeud(posParent));

				}else{

					ecrireInt((stagiaire.getParent()*TAILLE_NOEUD)+TAILLE_STAGIAIRE+4,(int)(fichier.length()/TAILLE_NOEUD)); 
					//System.out.println("Gauche :"+lireNoeud(posParent));
				}
			}
			
		}else{
			//System.out.println(stagiaire.compareTo(arbre));
			if(stagiaire.compareTo(arbre)<0){
				return ajoutElementArbreBinaire(stagiaire, posArbre, arbre.getFilsG(), existeDeja);
				
			}else{
				if(stagiaire.compareTo(arbre)>0){
					return ajoutElementArbreBinaire(stagiaire, posArbre, arbre.getFilsD(), existeDeja);
				}else{
					//System.out.println("boolean");
					return true;
				}
			}
		}
		return existeDeja;
	}
	
	
	public List<Noeud> afficherTout(int posArbre, boolean gauche, boolean droite, List<Noeud> listeNoeud)
	{
		Noeud arbre = lireNoeud(posArbre);
		if(gauche && arbre.hasFilsG()){
			
			afficherTout(arbre.getFilsG(),true,true,listeNoeud);
		}else{
			if(gauche || droite){
				listeNoeud.add(arbre);
			}
			
			
			if(droite && arbre.hasFilsD()){
				afficherTout(arbre.getFilsD(),true,true,listeNoeud);
			}else if(!arbre.isRacine()){
				Noeud parent = lireNoeud(arbre.getParent());
				if(parent.getFilsG() == posArbre){
					afficherTout(arbre.getParent(),false,true,listeNoeud);
				}else{
					afficherTout(arbre.getParent(),false,false,listeNoeud);
				}
				
			}
		}
		return listeNoeud;
		
	}
	
}
