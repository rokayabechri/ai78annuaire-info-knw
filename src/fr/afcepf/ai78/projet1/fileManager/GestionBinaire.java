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
	private List<String> promo = new ArrayList<String>();
	
	
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
					
					if(!ajoutElementArbreBinaire(unNoeud,-1,0,false,getPositionAjout())){
						ecrireNoeud(getPositionAjout(),unNoeud);
						if(!fantome.isEmpty()){
							fantome.remove(0);
						}
						
					}
				}
			}
			
			return true;
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean ajoutElementArbreBinaire(Noeud stagiaire, int posParent, int posArbre, Boolean existeDeja,int positionAjout) {
		
			if(!PromoExist(stagiaire.getPromotion())){
				promo.add(stagiaire.getPromotion());
			}
		try{
			Noeud parent = lireNoeud(posParent);
			Noeud arbre = lireNoeud(posArbre);			
			if(arbre == null){
				stagiaire.setParent(posParent);
				existeDeja = false;
				
				if(parent != null){
					if(parent.compareTo(stagiaire)<0){
						ecrireInt((stagiaire.getParent()*AnnuaireConstante.TAILLE_NOEUD)+AnnuaireConstante.TAILLE_STAGIAIRE+AnnuaireConstante.TAILLE_PARENT+AnnuaireConstante.TAILLE_FILSG,positionAjout);
					}else{
						ecrireInt((stagiaire.getParent()*AnnuaireConstante.TAILLE_NOEUD)+AnnuaireConstante.TAILLE_STAGIAIRE+AnnuaireConstante.TAILLE_PARENT,positionAjout);						
					}
				}
				
			}else{
				if(stagiaire.compareTo(arbre)<0){
					existeDeja = ajoutElementArbreBinaire(stagiaire, posArbre, arbre.getFilsG(), existeDeja,positionAjout);
					
				}else{
					if(stagiaire.compareTo(arbre)>0){
						existeDeja =  ajoutElementArbreBinaire(stagiaire, posArbre, arbre.getFilsD(), existeDeja,positionAjout);
					}else{
						existeDeja =  true;
					}
				}
			}
			return existeDeja;
			
		}catch (FileNotFoundException e) {
			return false;
		} catch (IOException e) {
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
	
	public int rechercher(Noeud unNoeud, int posArbre){

		Noeud arbre = lireNoeud(posArbre);
		int position=-1;
		if (arbre == null) {
			return -1 ;
		} else {
			if (unNoeud.compareTo(arbre) < 0 ) {
				position = rechercher(unNoeud , arbre.getFilsG() );
				
			} else {
				if (unNoeud.compareTo(arbre) > 0 ) {
					position = rechercher(unNoeud , arbre.getFilsD() );
					
				} else {
					position = posArbre;
				}
			}
		}
		return position;
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
								fantome.add(arbre.getFilsG());
							}else{
								ecrireInt((arbre.getParent()*AnnuaireConstante.TAILLE_NOEUD)+AnnuaireConstante.TAILLE_STAGIAIRE+AnnuaireConstante.TAILLE_PARENT, arbre.getFilsG());
								fantome.add(posArbre);
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
								fantome.add(arbre.getFilsD());


							} else {
								remonter(arbre.getFilsG(), posArbre, false) ;

							}
						}
					}
				}
			}
		}catch(IOException e){
			e.printStackTrace();
		}
	}

	private void remonter(int posFils ,int posArbre,boolean unBoolean){

		Noeud sousArbre = lireNoeud(posFils);
		Noeud arbre = lireNoeud(posArbre);

		if (!sousArbre.hasFilsD()) {
			try {
				if(!unBoolean){
					
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
			remonter(sousArbre.getFilsD(),posArbre,true);
		}
	}
	
	public int getPositionAjout() throws IOException{
		if(fantome.isEmpty()){

			return ((int)(getFichier().length()/AnnuaireConstante.TAILLE_NOEUD));

		}else{

			return fantome.get(0);
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
	
	public void ecrireNoeud(int indice, Noeud ajout) throws IOException {
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

	private boolean PromoExist(String promotion)
	{
		boolean unBoolean = false;
		
		for (String string : promo) {
			
			if(string.equals(promotion)){
				unBoolean = true;
			}
			
		}
		return unBoolean;
	}
	
	public List<String> getPromo() {
		return promo;
	}

}
