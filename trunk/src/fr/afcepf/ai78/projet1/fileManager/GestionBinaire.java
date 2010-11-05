package fr.afcepf.ai78.projet1.fileManager;

import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import fr.afcepf.ai78.projet1.constante.AnnuaireConstante;
import fr.afcepf.ai78.projet1.interfaces.FenetrePrincipale;
import fr.afcepf.ai78.projet1.objets.Noeud;
import fr.afcepf.ai78.projet1.objets.Stagiaire;

public class GestionBinaire extends SwingWorker<Boolean, String>{

	private List<Integer> fantome = new ArrayList<Integer>();
	private List<String> promo = new ArrayList<String>();
	private String fichierSource = "";
	private String fichierSortie = "";
	private FenetrePrincipale interfaceAnnuaire;


	public GestionBinaire(FenetrePrincipale interfaceAnnuaire, String fichierSource, String fichierSortie) {
		this.fichierSource = fichierSource;
		this.fichierSortie = fichierSortie;
		this.interfaceAnnuaire = interfaceAnnuaire;
	}

	public List<Stagiaire> rechercheRec(int posArbre, String nom , String prenom , String promotion , int annee , String departement , List<Stagiaire> liste) {
		Noeud arbre = lireNoeud(posArbre);
		boolean ajout = true ;
		
		if (arbre.hasFilsG()) {
			liste = rechercheRec(arbre.getFilsG(), nom, prenom, promotion, annee, departement, liste);
		}
		if (ajout && !nom.equalsIgnoreCase("") && !arbre.getNom().equalsIgnoreCase(nom)) {
			ajout = false ;
		}
		if (ajout && !prenom.equalsIgnoreCase("") && !arbre.getPrenom().equalsIgnoreCase(prenom)) {
			ajout = false ;
		}
		if (ajout && !promotion.equalsIgnoreCase("") && !arbre.getPromotion().equalsIgnoreCase(promotion)) {
			ajout = false ;
		}
		if (ajout && annee != -1 &&  arbre.getAnnee()!= annee) {
			ajout = false ;
		}
		if (ajout && !departement.equalsIgnoreCase("") && !arbre.getDepartement().equalsIgnoreCase(departement)) {
			ajout = false ;
		}
		
		if (ajout) {
		liste.add(arbre);
		}
		
		if (arbre.hasFilsD()) {
			liste = rechercheRec(arbre.getFilsD(), nom, prenom, promotion, annee, departement, liste);
		}
		return liste;
	}

	public List<Stagiaire> rechercherDynamique(String nom, int posArbre, List<Stagiaire> liste){

		
		Noeud arbre = lireNoeud(posArbre);
		if (arbre == null) {
			return liste ;
		} else {
			if (nom.compareToIgnoreCase(arbre.getNom().substring(0, nom.length())) < 0 ) {
				liste = rechercherDynamique(nom , arbre.getFilsG() , liste);

			} else {
				if (nom.compareToIgnoreCase(arbre.getNom().substring(0, nom.length())) > 0 ) {
					liste = rechercherDynamique(nom , arbre.getFilsD() , liste);

				} else {

					liste = rechercherDynamique ( nom , arbre.getFilsG() , liste);
					liste.add(arbre);
					liste = rechercherDynamique ( nom , arbre.getFilsD() , liste);
				}
			}
		}
		return liste;
	}
	
	private boolean creationArbreBinaire(double progressStart, double progressEnd) {
		interfaceAnnuaire.getContentPane().add(interfaceAnnuaire.getProgressBar(),BorderLayout.SOUTH);
		interfaceAnnuaire.getContentPane().revalidate();
		
		RandomAccessFile fichier = null;
		try {
			File f = new File(fichierSource);
			BufferedReader br  = new BufferedReader(new FileReader(f));
			LineNumberReader lnr = new LineNumberReader(new FileReader(f));
			
			lnr.skip(f.length()*2);
			
			fichier = new RandomAccessFile(fichierSortie, "rw");
			String ligne = "";
			int indiceTableau = 0;
			String []elements = new String [5];
			
			double step = (progressEnd - progressStart) / ((lnr.getLineNumber()+1)/AnnuaireConstante.NB_LIGNE_STAGIAIRE);

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
							fantome.remove(fantome.size()-1);
						}

					}
					progressStart += step;
					publish(unNoeud.toString());
					setProgress((int) progressStart);
				}
			}
			br.close();
			fichier.close();
			return true;

		} catch (FileNotFoundException e) {
			//e.printStackTrace();
			return false;
		} catch (IOException e) {
			//e.printStackTrace();
			return false;
		}catch(Exception e) {
			//e.printStackTrace();
			return false;
		} finally {
			try {
				fichier.close();
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("RAF OUVERT !");
			}
		}
		
		
	}

	public boolean ajoutElementArbreBinaire(Noeud stagiaire, int posParent, int posArbre, Boolean existeDeja,int positionAjout) {

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
	}

	public List<Stagiaire> afficherTout(){
		List<Stagiaire> liste = new ArrayList<Stagiaire>();
		if(fantome.indexOf(0)==-1){
			liste = afficherTout(0, new ArrayList<Stagiaire>());
		}
		
		return liste;
	}
	
	private List<Stagiaire> afficherTout(int posArbre, List<Stagiaire> listeNoeud)
	{

		Noeud arbre = lireNoeud(posArbre);
		

		if(arbre != null){
			
			if(!promoExist(arbre.getPromotion())){

				promo.add(arbre.getPromotion());
			}
			if(arbre.hasFilsG()){
				listeNoeud = afficherTout(arbre.getFilsG(),listeNoeud);	
			}
			listeNoeud.add(arbre);
			if(arbre.hasFilsD()){
				listeNoeud = afficherTout(arbre.getFilsD(),listeNoeud);		
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
							if(!arbre.isRacine()){
								Noeud noeudParent = lireNoeud(arbre.getParent());
								
								if(noeudParent.getFilsD()== posArbre){
									ecrireInt((arbre.getParent()*AnnuaireConstante.TAILLE_NOEUD)+AnnuaireConstante.TAILLE_STAGIAIRE+AnnuaireConstante.TAILLE_PARENT+AnnuaireConstante.TAILLE_FILSG, arbre.getFilsD());
								}else{
									ecrireInt((arbre.getParent()*AnnuaireConstante.TAILLE_NOEUD)+AnnuaireConstante.TAILLE_STAGIAIRE+AnnuaireConstante.TAILLE_PARENT, arbre.getFilsG());
								}
							}
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
	}

	private void remonter(int posFils ,int posArbre,boolean unBoolean){

		Noeud sousArbre = lireNoeud(posFils);
		Noeud arbre = lireNoeud(posArbre);

		if (!sousArbre.hasFilsD()) {
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

		} else {
			remonter(sousArbre.getFilsD(),posArbre,true);
		}
	}

	public int getPositionAjout(){
		
		RandomAccessFile fichier = null;
		int position = 0;
		
		try {
			fichier = new RandomAccessFile(fichierSortie, "rw");
			
			if(fantome.isEmpty()){
				position = ((int)(fichier.length()/AnnuaireConstante.TAILLE_NOEUD));
			}else{
				position = fantome.get(fantome.size()-1);
			}
						
		} catch (FileNotFoundException e) {
			//e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("ERREUR DE LENGTH");
		}finally {
			try {
				fichier.close();
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("RAF OUVERT !");
			}
		}
		return position;
	}

	public void ecrireNoeud(int indice, Noeud ajout)  {
		
		RandomAccessFile fichier = null;
		try {
			fichier = new RandomAccessFile(fichierSortie, "rw");
			
			fichier.seek(indice*AnnuaireConstante.TAILLE_NOEUD);
	
			fichier.writeChars(formater(AnnuaireConstante.TAILLE_NOM,ajout.getNom()));
			fichier.writeChars(formater(AnnuaireConstante.TAILLE_PRENOM,ajout.getPrenom()));
			fichier.writeChars(formater(AnnuaireConstante.TAILLE_PROMOTION,ajout.getPromotion()));
			fichier.writeChars(formater(AnnuaireConstante.TAILLE_DEPARTEMENT,ajout.getDepartement()));
			fichier.writeInt(ajout.getAnnee());
			fichier.writeInt(ajout.getParent());
			fichier.writeInt(ajout.getFilsG());
			fichier.writeInt(ajout.getFilsD());
		} catch (FileNotFoundException e) {
			//e.printStackTrace();
		} catch (IOException e) {
			//e.printStackTrace();
		} finally {
			try {
				fichier.close();
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("RAF OUVERT !");
			}
		}
	}

	private Noeud lireNoeud(int indice) {
		
		Noeud unNoeud = new Noeud();
		RandomAccessFile fichier = null;
		try {
			
			fichier = new RandomAccessFile(fichierSortie, "rw");
			fichier.seek(indice*AnnuaireConstante.TAILLE_NOEUD);

			unNoeud.setNom(lireChaine(AnnuaireConstante.TAILLE_NOM,fichier));
			unNoeud.setPrenom(lireChaine(AnnuaireConstante.TAILLE_PRENOM,fichier));
			unNoeud.setPromotion(lireChaine(AnnuaireConstante.TAILLE_PROMOTION,fichier));
			unNoeud.setDepartement(lireChaine(AnnuaireConstante.TAILLE_DEPARTEMENT,fichier));
			unNoeud.setAnnee(fichier.readInt());
			unNoeud.setParent(fichier.readInt());
			unNoeud.setFilsG(fichier.readInt());
			unNoeud.setFilsD(fichier.readInt());
			
		} catch (IOException e) {
			return null;
		} catch (Exception e){
			return null;
		} finally {
			try {
				fichier.close();
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("RAF OUVERT !");
			}
		}
		return unNoeud;
	}

	private void ecrireInt(int position, int element){
		
		RandomAccessFile fichier = null;
		try {
			fichier = new RandomAccessFile(fichierSortie, "rw");
			fichier.seek(position);
			fichier.writeInt(element);
		} catch (FileNotFoundException e) {
			//e.printStackTrace();
		} catch (IOException e) {
			//e.printStackTrace();
		} finally{
			try {
				fichier.close();
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("RAF OUVERT !");
			}
		}
		
	}

	private String formater (int taille ,String chaine){

		for (int i = chaine.length(); i < taille/2; i++) {

			chaine = chaine+ " ";
		}
		return chaine;
	} 

	private String lireChaine(int taille,RandomAccessFile fichier) throws IOException{

		String chaine="";

		byte [] tableauOctet = new byte [taille];
		fichier.read(tableauOctet);
		chaine = new String(tableauOctet,"UTF-16").trim();
		return chaine;
	}
		
	private boolean promoExist(String promotion){
		
		boolean unBoolean = false;

		for (String string : promo) {

			if(string.equals(promotion)){
				unBoolean = true;
			}

		}
		return unBoolean;
	}
		
	public List<String> getPromo() {
		Collections.sort(promo);
		return promo;
	}

	public List<Integer> getFantome() {
		return fantome;
	}
	
	@Override
	public Boolean doInBackground() throws Exception {
		return creationArbreBinaire(0, 100);
	}

	@Override
	protected void done() {
		try {
			interfaceAnnuaire.appelAffichage(get());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}

}
