package fr.afcepf.ai78.projet1.objets;

public class Noeud extends Stagiaire{
	
	private static int nbStagiaire = 0; 
	private int posParent = -1;
	private int posFilsG = -1;
	private int posFilsD = -1;

	
	
	
	public Noeud() {
		super();
	}
	
	
	

	public Noeud(String nom, String prenom, String departement,
			String promotion, int annee) {
		super(nom, prenom, departement, promotion, annee);
		
	}

	public boolean isRacine(){
		return (posParent==-1);
	}


	public boolean hasFilsG() {
		return !(posFilsG==-1);
	}
	
	public boolean hasFilsD() {

		return !(posFilsD==-1);
	}

	public int getParent() {
		return posParent;
	}

	public int getFilsG() {
		return posFilsG;
	}

	public int getFilsD() {
		return posFilsD;
	}
	
	public void setParent(int parent) {
		this.posParent = parent;
	}
	
	public void setFilsG(int filsG) {
		this.posFilsG = filsG;
	}
	
	public void setFilsD(int filsD) {
		this.posFilsD = filsD;
	}

	@Override
	public String toString() {
		return "Noeud [Nom="+nom+" posParent=" + posParent + ", posFilsG=" + posFilsG
				+ ", posFilsD=" + posFilsD + "]";
	}


	

	
	

}
