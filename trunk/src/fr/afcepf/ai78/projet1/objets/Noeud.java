package fr.afcepf.ai78.projet1.objets;

/**
 * Noeud is a data container which reprensent a node of a binary tree containing a {@link Stagiaire},
 * the new data are the position of the ancestor and descendants.
 * 
 * @author Chouaib
 * @version 1.1
 * @see Stagiaire
 */
public class Noeud extends Stagiaire{
	
	/**
	 * The position (index) of the ancestor, the value -1 is for no ancestor.
	 */
	private int posParent = -1;
	/**
	 * The position (index) of the left descendant, the value -1 is for no left descendant.
	 */
	private int posFilsG = -1;
	/**
	 * The position (index) of the right descendant, the value -1 is for no right descendant.
	 */
	private int posFilsD = -1;
	
	/**
	 * The Default constructor,
	 * Build an empty node.
	 */
	public Noeud() {
		super();
	}
	
	/**
	 * Build a node with the provided data.
	 * 
	 * @param nom The name.
	 * @param prenom The firstname.
	 * @param departement The two-letter zip code.
	 * @param promotion The class.
	 * @param annee The year.
	 */
	public Noeud(String nom, String prenom, String departement,String promotion, int annee) {
		super(nom, prenom, departement, promotion, annee);
	}

	/**
	 * Return the current right descendant.
	 * @return The right descendant.
	 */
	public int getFilsD() {
		return posFilsD;
	}

	/**
	 * Return the current left descendant.
	 * @return The left descendant.
	 */
	public int getFilsG() {
		return posFilsG;
	}
	
	/**
	 * Return the current ancestor.
	 * @return The ancestor.
	 */
	public int getParent() {
		return posParent;
	}
	
	/**
	 * Return <code>true</code> or <code>false</code> depending on the right descendant.
	 * @return <code>true</code> if the node has a right descendant.
	 */
	public boolean hasFilsD() {
		return !(posFilsD==-1);
	}
	
	/**
	 * Return <code>true</code> or <code>false</code> depending on the left descendant.
	 * @return <code>true</code> if the node has a left descendant.
	 */
	public boolean hasFilsG() {
		return !(posFilsG==-1);
	}
	
	/**
	 * Return <code>true</code> or <code>false</code> depending on the ancestor.
	 * @return <code>true</code> if the node is the root.
	 */
	public boolean isRacine(){
		return (posParent==-1);
	}
	
	/**
	 * Set the right descendant with the provided data.
	 * @param filsD The right descendant.
	 */
	public void setFilsD(int filsD) {
		this.posFilsD = filsD;
	}
	
	/**
	 * Set the left descendant with the provided data.
	 * @param filsG The left descendant.
	 */
	public void setFilsG(int filsG) {
		this.posFilsG = filsG;
	}
	
	/**
	 * Set the ancestor with the provided data.
	 * @param parent The ancestor.
	 */
	public void setParent(int parent) {
		this.posParent = parent;
	}

	/* (non-Javadoc)
	 * @see fr.afcepf.ai78.projet1.objets.Stagiaire#toString()
	 */
	@Override
	public String toString() {
		return super.toString()+" [posParent=" + posParent + ", posFilsG=" + posFilsG
				+ ", posFilsD=" + posFilsD + "]";
	}
}
