package fr.afcepf.ai78.projet1.constante;

public class AnnuaireConstante {

	public static final int TAILLE_NOM = 60 ;			
	public static final int TAILLE_PRENOM = 60 ;		
	public static final int TAILLE_DEPARTEMENT = 4 ;	
	public static final int TAILLE_PROMOTION = 20 ;		
	public static final int TAILLE_ANNEE = 4 ;		
	public static final int TAILLE_STAGIAIRE = (TAILLE_NOM + TAILLE_PRENOM + TAILLE_DEPARTEMENT + TAILLE_PROMOTION + TAILLE_ANNEE);

	public static final int TAILLE_PARENT = 4 ;			
	public static final int TAILLE_FILSG = 4 ;		
	public static final int TAILLE_FILSD = 4 ;	
	public static final int TAILLE_NOEUD = TAILLE_STAGIAIRE + TAILLE_PARENT + TAILLE_FILSG + TAILLE_FILSD ;
	
}
