package fr.afcepf.ai78.projet1.objets;

public class Noeud {
	
	private static int nbStagiaire = 0; 
	private Stagiaire donnee;
	private Noeud parent = null;
	private Noeud filsG = null;
	private Noeud filsD = null;
	private int position =0;
	
	
	
	public Noeud(Stagiaire donnee) {
		this.donnee = donnee;
		
	}
	
	

	public void setPosition() {
		this.position = nbStagiaire++;
	}



	public boolean hasFilsG() {
		return !(filsG==null);
	}
	public boolean hasFilsD() {

		return !(filsD==null);
	}
	public Stagiaire getDonnee() {
		return donnee;
	}

	public Noeud getParent() {
		return parent;
	}

	public Noeud getFilsG() {
		return filsG;
	}

	public Noeud getFilsD() {
		return filsD;
	}
	public void setParent(Noeud parent) {
		this.parent = parent;
	}
	public void setFilsG(Noeud filsG) {
		this.filsG = filsG;
	}
	public void setFilsD(Noeud filsD) {
		this.filsD = filsD;
	}

	public int getPosition() {
		return position;
	}

	

	
	

}
