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

/**
 * GestionBinaire is the FileManager layout of our directory, using a RandomAccessFile to read and write in the file,
 * it extends SwingWorker in order to build the binary file in a different thread.
 * 
 * @author Augereau, Chouaib, Lepante
 * @version 1.1
 * @see SwingWorker
 */
public class GestionBinaire extends SwingWorker<Boolean, String>{

	/**
	 * The list of ghost data in our file.
	 */
	private List<Integer> fantome = new ArrayList<Integer>();
	/**
	 * The list of class in our directory.
	 */
	private List<String> promo = new ArrayList<String>();
	/**
	 * The path to the source file.
	 */
	private String fichierSource = "";
	/**
	 * The path to the binary file.
	 */
	private String fichierSortie = "";
	/**
	 * The UI for this directory.
	 */
	private FenetrePrincipale interfaceAnnuaire;


	/**
	 * Build a new GestionBinaire with the provided data.
	 * 
	 * @param interfaceAnnuaire The UI corresponding to the new GestionBinaire.
	 * @param fichierSource The path to the source file, can be null if the directory is created from scratch.
	 * @param fichierSortie The path to the binary file.
	 */
	public GestionBinaire(FenetrePrincipale interfaceAnnuaire, String fichierSource, String fichierSortie) {
		this.fichierSource = fichierSource;
		this.fichierSortie = fichierSortie;
		this.interfaceAnnuaire = interfaceAnnuaire;
	}

	/**
	 * The method used for the multi-test search, 
	 * it will retrun a new List of Stagiaire containing all the trainee corresponding to the search test.
	 * 
	 * @param posArbre The position (index) of the current node in the file.
	 * @param nom The searched name, can be an empty String if the user doesn't test the name. 
	 * @param prenom The searched firstname, can be an empty String if the user doesn't test the name. 
	 * @param promotion The searched class, can be an empty String if the user doesn't test the name.
	 * @param annee The searched year, can be -1 if the user doesn't test the name.
	 * @param departement The searched zip code, can be an empty String if the user doesn't test the name.
	 * @param liste The list of trainee corresponding to the search.
	 * @return A List of trainee corresponding to the search.
	 */
	public List<Stagiaire> rechercheRec(int posArbre, String nom , String prenom , String promotion , int annee , String departement , List<Stagiaire> liste) {
		Noeud arbre = lireNoeud(posArbre);
		boolean ajout = true ;
		
		if (arbre.hasFilsG()) {
			liste = rechercheRec(arbre.getFilsG(), nom, prenom, promotion, annee, departement, liste);
		}
		if (ajout && !nom.equalsIgnoreCase("") && !arbre.getNom().trim().equalsIgnoreCase(nom)) {
			ajout = false ;
		}
		if (ajout && !prenom.equalsIgnoreCase("") && !arbre.getPrenom().trim().equalsIgnoreCase(prenom)) {
			ajout = false ;
		}
		if (ajout && !promotion.equalsIgnoreCase("") && !arbre.getPromotion().trim().equalsIgnoreCase(promotion)) {
			ajout = false ;
		}
		if (ajout && annee != -1 &&  arbre.getAnnee()!= annee) {
			ajout = false ;
		}
		if (ajout && !departement.equalsIgnoreCase("") && !arbre.getDepartement().trim().equalsIgnoreCase(departement)) {
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

	/**
	 * The method used for dynamic search, test only if the name of the trainee begin with the provided String.
	 *  
	 * @param nom The String searched.
	 * @param posArbre The position (index) of the current node in the file.
	 * @param liste The list of trainee corresponding to the search.
	 * @return A List of trainee corresponding to the search.
	 */
	public List<Stagiaire> rechercherDynamique(String nom, int posArbre, List<Stagiaire> liste){
		try{
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
		}catch (Exception e) {
			//e.printStackTrace();
		}
		return liste;
		
	}
	
	/**
	 * A private method called in background when GestionBinaire is executed,
	 * it creates the binary file from a source file and update the JProgressBar of interfaceAnnuaire,
	 * return <code>true</code> if the file is successfuly created.
	 * 
	 * @param progressStart The starting value of the JProgressBar.
	 * @param progressEnd The ending value of the JProgressBar.
	 * @return <code>true</code> or <code>false</code> depending of the successfull creation of the binary file.
	 */
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

	/**
	 * The method used to add a node to the binary tree (not to add a node in the binary file),
	 * return <code>true</code> if the trainee is already in the tree.
	 * 
	 * @param stagiaire The trainee data.
	 * @param posParent The position (index) of the ancestor node in the file.
	 * @param posArbre The position (index) of the current node in the file.
	 * @param existeDeja The boolean to check if the trainee already exist.
	 * @param positionAjout The position (index) where the node will be add in the binary file.
	 * @return <code>true</code> or <code>false</code> depending of the successfull creation of the binary file.
	 */
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

	/**
	 * The method used to get the whole list of trainee,
	 * return a List of Stagiaire.
	 * 
	 * @return A List containing all the trainee in the file.
	 */
	public List<Stagiaire> afficherTout(){
		List<Stagiaire> liste = new ArrayList<Stagiaire>();
			
		if(fantome.indexOf(0)==-1){
			liste = afficherTout(0, new ArrayList<Stagiaire>());
		}
		
		return liste;
	}
	
	/**
	 * The private method used to recursively browse the binary tree,
	 * return a List of Stagiaire sorted by name.
	 * 
	 * @param posArbre The position (index) of the current node in the file.
	 * @param listeNoeud The List containing all the trainee.
	 * @return A List containing all the trainee in the file.
	 */
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

	/**
	 * The method used to delete a node in the tree, the will be physically override by his placetaker,
	 * and his placetaker will be recursively override during the ascension of the branch,
	 * this method lets ghost entry in the file which are stored in the ghost data List.
	 * 
	 * @param unNoeud The node to be delete.
	 * @param posArbre The position (index) of the current node in the file.
	 */
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

	/**
	 * The private method used to ascend node in the binary tree during the deletion.
	 * 
	 * @param posFils The position (index) of the descendant node in the file.
	 * @param posArbre The position (index) of the current node in the file.
	 * @param rappel Used to know if this method has been called more than once.
	 */
	private void remonter(int posFils ,int posArbre,boolean rappel){

		Noeud sousArbre = lireNoeud(posFils);
		Noeud arbre = lireNoeud(posArbre);

		if (!sousArbre.hasFilsD()) {
			if(!rappel){

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

	/**
	 * The method used to know the appropriate position (index) to add a new node in the binary file,
	 * return the last ghost data position if there is one, or the end of the file.
	 * 
	 * @return The position (index) where to write the node.
	 */
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

	/**
	 * Directly write a node at the given index.
	 * 
	 * @param indice The position (index) where to write the node.
	 * @param ajout The node to be added.
	 */
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

	/**
	 * Directly read a node at the given index,
	 * return null if it fails to read the node at the givent index.
	 * 
	 * @param indice The position (index) where to write the node.
	 * @return The readed node or null if the reading has failed.
	 */
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

	/**
	 * Directly write an integer at the given position (byte),
	 * used to modify the reference to the position of the ancestor ot the descendant.
	 * 
	 * @param position The position (byte) where to write the givent data.
	 * @param element The data to write.
	 */
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

	/**
	 * Format the given String into a new String with spaces to match the given size.
	 * 
	 * @param taille The size of the new String.
	 * @param chaine The string to be format.
	 * @return A new String formated with spaces to match the given size.
	 */
	private String formater (int taille ,String chaine){

		for (int i = chaine.length(); i < taille/2; i++) {

			chaine = chaine+ " ";
		}
		return chaine;
	} 

	/**
	 * Read a given number of bytes in the file and return it into a String.
	 * 
	 * @param taille The number of bytes to read.
	 * @param fichier The RandomAccessFile used to read the file.
	 * @return A String created from the readed bytes.
	 * @throws IOException Throws an exception if it fails to read the bytes.
	 */
	private String lireChaine(int taille,RandomAccessFile fichier) throws IOException{

		String chaine="";

		byte [] tableauOctet = new byte [taille];
		fichier.read(tableauOctet);
		chaine = new String(tableauOctet,"UTF-16").trim();
		return chaine;
	}
		
	/**
	 * A private method used to test if the given class already exist in the 
	 * List of class.
	 * 
	 * @param promotion The given class to be checked.
	 * @return <code>true</code> if the class is already listed, <code>false</code> if not.
	 */
	private boolean promoExist(String promotion){
		
		boolean unBoolean = false;

		for (String string : promo) {

			if(string.equals(promotion)){
				unBoolean = true;
			}

		}
		return unBoolean;
	}
		
	/**
	 * Return the List of registered class.
	 * 
	 * @return The List of registered class.
	 */
	public List<String> getPromo() {
		Collections.sort(promo);
		return promo;
	}

	/**
	 * Return the List of ghost position (index).
	 * 
	 * @return The List of ghost position (index).
	 */
	public List<Integer> getFantome() {
		return fantome;
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.SwingWorker#doInBackground()
	 */
	@Override
	public Boolean doInBackground() throws Exception {
		return creationArbreBinaire(0, 100);
	}

	/* (non-Javadoc)
	 * @see javax.swing.SwingWorker#done()
	 */
	@Override
	protected void done() {
		interfaceAnnuaire.setEnabled(true);
		try {
			interfaceAnnuaire.appelAffichage(get());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Return the name of the binary file.
	 * 
	 * @return The name of the binary file.
	 */
	public String getFichierSortie() {
		return fichierSortie;
	}
}
