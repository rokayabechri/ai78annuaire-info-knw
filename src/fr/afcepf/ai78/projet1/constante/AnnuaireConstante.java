package fr.afcepf.ai78.projet1.constante;

import fr.afcepf.ai78.projet1.objets.Noeud;

/**
 * 
 * AnnuaireConstante contains all constants for various uses in the project
 * like the size in bytes of all the {@link Noeud} data.
 * 
 * @author Lepante
 * @version 1.0
 * @see Noeud
 * 
 */
public class AnnuaireConstante {

	/**
	 * The bytes allowed in the file for the 'nom' field.
	 */
	public static final int TAILLE_NOM = 60 ;
	
	/**
	 * The bytes allowed in the file for the 'prenom' field.
	 */
	public static final int TAILLE_PRENOM = 60 ;
	
	/**
	 * The bytes allowed in the file for the 'departement' field.
	 */
	public static final int TAILLE_DEPARTEMENT = 4 ;
	
	/**
	 * The bytes allowed in the file for the 'promotion' field.
	 */
	public static final int TAILLE_PROMOTION = 20 ;
	
	/**
	 * The bytes allowed in the file for the 'annee' field.
	 */
	public static final int TAILLE_ANNEE = 4 ;
	
	/**
	 * The total size of Stagiaire data.
	 */
	public static final int TAILLE_STAGIAIRE = (TAILLE_NOM + TAILLE_PRENOM + TAILLE_DEPARTEMENT + TAILLE_PROMOTION + TAILLE_ANNEE);

	/**
	 * The bytes allowed in the file for the 'parent' field.
	 */
	public static final int TAILLE_PARENT = 4 ;	
	
	/**
	 * The bytes allowed in the file for the 'filsG' field.
	 */
	public static final int TAILLE_FILSG = 4 ;
	
	/**
	 * The bytes allowed in the file for the 'flisD' field.
	 */
	public static final int TAILLE_FILSD = 4 ;
	
	/**
	 * The total size of Noeud data.
	 */
	public static final int TAILLE_NOEUD = TAILLE_STAGIAIRE + TAILLE_PARENT + TAILLE_FILSG + TAILLE_FILSD ;
	
}
