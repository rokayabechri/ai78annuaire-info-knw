package fr.afcepf.ai78.projet1.fileManager;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import fr.afcepf.ai78.projet1.constante.AnnuaireConstante;
import fr.afcepf.ai78.projet1.objets.Noeud;
import fr.afcepf.ai78.projet1.objets.Stagiaire;

public class GestionBinaire {
	
	private RandomAccessFile fichier ;
	private List<Integer> fantome = new ArrayList<Integer>();
	
	
	public GestionBinaire(){
	}
	
	public GestionBinaire(String fichierSource, String FichierSortie) {
		creationArbreBinaire(fichierSource, FichierSortie);
	}
	
	public boolean creationArbreBinaire(String fichierSource, String FichierSortie) {
		try {
			FileReader fr = new FileReader(fichierSource);
			BufferedReader br  = new BufferedReader(fr);
			setFichier(new RandomAccessFile(FichierSortie, "rw"));
			
			String ligne = "";
			int indiceTableau = 0;
			String []elements = new String [5];
			
			while((ligne=br.readLine())!=null){
				
				if(!ligne.equals("*")){
					
					elements[indiceTableau] = ligne;
					indiceTableau++;

					
				}else{
					Noeud unNoeud = new Noeud(elements[0], elements[1], elements[2], elements[3], Integer.parseInt(elements[4]));
					indiceTableau = 0;
					
					if(!ajoutElementArbreBinaire(unNoeud,-1,0,false)){
						getFichier().seek(getFichier().length());
						ecrireNoeud(unNoeud);
					}
				}
			}
			
			return true;
			
		} catch (FileNotFoundException e) {
			return false;
		} catch (IOException e) {
			return false;
		}catch(Exception e) {
			return false;
		}
	}
	
	public boolean ajoutElementArbreBinaire(Noeud stagiaire, int posParent, int posArbre, Boolean existeDeja) {
		
		try{
			Noeud parent = lireNoeud(posParent);
			Noeud arbre = lireNoeud(posArbre);
			if(arbre == null){
				stagiaire.setParent(posParent);
				existeDeja = false;
				
				if(parent != null){
					if(parent.compareTo(stagiaire)<0){
						ecrireInt((stagiaire.getParent()*AnnuaireConstante.TAILLE_NOEUD)+AnnuaireConstante.TAILLE_STAGIAIRE+AnnuaireConstante.TAILLE_PARENT+AnnuaireConstante.TAILLE_FILSG,(int)(getFichier().length()/AnnuaireConstante.TAILLE_NOEUD));
					}else{
						ecrireInt((stagiaire.getParent()*AnnuaireConstante.TAILLE_NOEUD)+AnnuaireConstante.TAILLE_STAGIAIRE+AnnuaireConstante.TAILLE_PARENT,(int)(getFichier().length()/AnnuaireConstante.TAILLE_NOEUD));
						
					}
				}
				
			}else{
				if(stagiaire.compareTo(arbre)<0){
					existeDeja = ajoutElementArbreBinaire(stagiaire, posArbre, arbre.getFilsG(), existeDeja);
					
				}else{
					if(stagiaire.compareTo(arbre)>0){
						existeDeja =  ajoutElementArbreBinaire(stagiaire, posArbre, arbre.getFilsD(), existeDeja);
					}else{
						existeDeja =  true;
					}
				}
			}
			return existeDeja;
			
		}catch (FileNotFoundException e) {
			//e.printStackTrace();
			return false;
		} catch (IOException e) {
			//e.printStackTrace();
			return false;
		}
	}
	
	public List<Stagiaire> afficherTout(int posArbre, List<Stagiaire> listeNoeud)
	{
		Noeud arbre = lireNoeud(posArbre);
		
			if(arbre.hasFilsG()){
				listeNoeud = afficherTout(arbre.getFilsG(),listeNoeud);	
			}
			listeNoeud.add(arbre);
			if(arbre.hasFilsD()){
				listeNoeud = afficherTout(arbre.getFilsD(),listeNoeud);		
			}
	
		return listeNoeud;	
	}
	
	public List<Stagiaire> afficherTout(int posArbre, boolean gauche, boolean droite, List<Stagiaire> listeNoeud)

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
	
	public List<Stagiaire> rechercher(String nom, int posArbre , List<Stagiaire> liste){
		Noeud arbre = lireNoeud(posArbre);
		
		if (arbre == null) {
			return liste ;
		} else {
			if (nom.compareToIgnoreCase(arbre.getNom().trim()) < 0 ) {
				liste = rechercher(nom , arbre.getFilsG() , liste);
				
			} else {
				if (nom.compareToIgnoreCase(arbre.getNom().trim()) > 0 ) {
					liste = rechercher(nom , arbre.getFilsD() , liste);
					
				} else {
					
					liste = rechercher ( nom , arbre.getFilsG() , liste);
					liste.add(arbre);
					liste = rechercher ( nom , arbre.getFilsD() , liste);
				}
			}
		}
		return liste;
	}
	
	public void supprimer(Noeud unNoeud, int posArbre){

		Noeud arbre = lireNoeud(posArbre);
		try{
			if (arbre == null) {
				JOptionPane.showMessageDialog(null, "suppression impossible");
			} else {
				if (unNoeud.compareTo(arbre) < 0 ) {
					supprimer(unNoeud , arbre.getFilsG());
				} else {
					if (unNoeud.compareTo(arbre) > 0 ) {
						supprimer(unNoeud , arbre.getFilsD());
					} else {

						if (!arbre.hasFilsD()) {


							if(arbre.hasFilsG()){

								Noeud sousArbreGauche = lireNoeud(arbre.getFilsG());

								if(sousArbreGauche.hasFilsG()){
									ecrireInt((sousArbreGauche.getFilsG()*AnnuaireConstante.TAILLE_NOEUD)+AnnuaireConstante.TAILLE_STAGIAIRE, posArbre);
								}
								if(sousArbreGauche.hasFilsD()){
									ecrireInt((sousArbreGauche.getFilsD()*AnnuaireConstante.TAILLE_NOEUD)+AnnuaireConstante.TAILLE_STAGIAIRE, posArbre);
								}
								sousArbreGauche.setParent(arbre.getParent());
								ecrireNoeud(posArbre,sousArbreGauche);
							}else{
								ecrireInt((arbre.getParent()*AnnuaireConstante.TAILLE_NOEUD)+AnnuaireConstante.TAILLE_STAGIAIRE+AnnuaireConstante.TAILLE_PARENT, arbre.getFilsG());
							}

						} else {
							if (!arbre.hasFilsG()) {	
								Noeud sousArbreDroit = lireNoeud(arbre.getFilsD());

								if(sousArbreDroit.hasFilsG()){
									ecrireInt((sousArbreDroit.getFilsG()*AnnuaireConstante.TAILLE_NOEUD)+AnnuaireConstante.TAILLE_STAGIAIRE, posArbre);
								}
								if(sousArbreDroit.hasFilsD()){
									ecrireInt((sousArbreDroit.getFilsD()*AnnuaireConstante.TAILLE_NOEUD)+AnnuaireConstante.TAILLE_STAGIAIRE, posArbre);
								}			
								sousArbreDroit.setParent(arbre.getParent());
								ecrireNoeud(posArbre,sousArbreDroit);


							} else {
								remonter(arbre.getFilsG(), posArbre,0) ;

							}
						}
					}
				}
			}
		}catch(IOException e){
			e.printStackTrace();
		}
	}

	private void remonter(int posFils ,int posArbre,int compteur){

		Noeud sousArbre = lireNoeud(posFils);
		Noeud arbre = lireNoeud(posArbre);

		if (!sousArbre.hasFilsD()) {
			try {
				if(compteur == 0){
					
					if(sousArbre.hasFilsG()){
						ecrireInt((sousArbre.getFilsG()*AnnuaireConstante.TAILLE_NOEUD)+AnnuaireConstante.TAILLE_STAGIAIRE, posArbre);
					}
					sousArbre.setParent(arbre.getParent());
					sousArbre.setFilsD(arbre.getFilsD());
					ecrireNoeud(posArbre,sousArbre);
					fantome.add(posFils);
				}else{
					
					if(sousArbre.hasFilsG()){
						ecrireInt((sousArbre.getFilsG()*AnnuaireConstante.TAILLE_NOEUD)+AnnuaireConstante.TAILLE_STAGIAIRE, sousArbre.getParent());
					}					
					ecrireInt((sousArbre.getParent()*AnnuaireConstante.TAILLE_NOEUD)+AnnuaireConstante.TAILLE_STAGIAIRE+4+4, sousArbre.getFilsG());
					sousArbre.setParent(arbre.getParent());
					sousArbre.setFilsG(arbre.getFilsG());
					sousArbre.setFilsD(arbre.getFilsD());
					ecrireNoeud(posArbre,sousArbre);
					fantome.add(posFils);
				}

			} catch (IOException e) {
				e.printStackTrace();
			}

		} else {
			remonter(sousArbre.getFilsD(),posArbre,compteur+1);
		}
	}
		
	public List<Noeud> lireFichier(){
		List<Noeud> liste = new ArrayList<Noeud>();
		try {
			for (int i = 0; i < getFichier().length()/AnnuaireConstante.TAILLE_NOEUD; i++) {
				liste.add(lireNoeud(i));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return liste;
	}
	
	private void ecrireNoeud(int indice, Noeud ajout) throws IOException {
		getFichier().seek(indice*AnnuaireConstante.TAILLE_NOEUD);
		
		getFichier().writeChars(formater(AnnuaireConstante.TAILLE_NOM,ajout.getNom()));
		getFichier().writeChars(formater(AnnuaireConstante.TAILLE_PRENOM,ajout.getPrenom()));
		getFichier().writeChars(formater(AnnuaireConstante.TAILLE_PROMOTION,ajout.getPromotion()));
		getFichier().writeChars(formater(AnnuaireConstante.TAILLE_DEPARTEMENT,ajout.getDepartement()));
		getFichier().writeInt(ajout.getAnnee());
		getFichier().writeInt(ajout.getParent());
		getFichier().writeInt(ajout.getFilsG());
		getFichier().writeInt(ajout.getFilsD());
	}
	
	public void ecrireNoeud(Noeud ajout) throws IOException {
		
		getFichier().writeChars(formater(AnnuaireConstante.TAILLE_NOM,ajout.getNom()));
		getFichier().writeChars(formater(AnnuaireConstante.TAILLE_PRENOM,ajout.getPrenom()));
		getFichier().writeChars(formater(AnnuaireConstante.TAILLE_PROMOTION,ajout.getPromotion()));
		getFichier().writeChars(formater(AnnuaireConstante.TAILLE_DEPARTEMENT,ajout.getDepartement()));
		getFichier().writeInt(ajout.getAnnee());
		getFichier().writeInt(ajout.getParent());
		getFichier().writeInt(ajout.getFilsG());
		getFichier().writeInt(ajout.getFilsD());
	}
	
	private Noeud lireNoeud(int indice) {
		
		Noeud unNoeud = new Noeud();
		try {
			
			getFichier().seek(indice*AnnuaireConstante.TAILLE_NOEUD);

			unNoeud.setNom(lireChaine(AnnuaireConstante.TAILLE_NOM));
			unNoeud.setPrenom(lireChaine(AnnuaireConstante.TAILLE_PRENOM));
			unNoeud.setPromotion(lireChaine(AnnuaireConstante.TAILLE_PROMOTION));
			unNoeud.setDepartement(lireChaine(AnnuaireConstante.TAILLE_DEPARTEMENT));
			unNoeud.setAnnee(getFichier().readInt());
			unNoeud.setParent(getFichier().readInt());
			unNoeud.setFilsG(getFichier().readInt());
			unNoeud.setFilsD(getFichier().readInt());
			
		} catch (IOException e) {
			return null;
		}
		return unNoeud;
	}
	
	private void ecrireInt(int position, int element) throws IOException{
		getFichier().seek(position);
		getFichier().writeInt(element);
	}
	
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
			getFichier().read(tableauOctet);
			chaine = new String(tableauOctet,"UTF-16").trim();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return chaine;
	}

	public List<Integer> getFantome() {
		return fantome;
	}

	public void setFichier(RandomAccessFile fichier) {
		this.fichier = fichier;
	}

	public RandomAccessFile getFichier() {
		return fichier;
	}
	
}
