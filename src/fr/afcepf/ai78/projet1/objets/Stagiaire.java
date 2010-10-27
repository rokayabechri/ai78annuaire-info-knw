package fr.afcepf.ai78.projet1.objets;

public class Stagiaire {
	
	private String nom;
	private String prenom;
	private String departement;
	private String promotion;
	private int annee;
	
	
	public Stagiaire() {

	}


	public Stagiaire(String nom, String prenom, String departement,
			String promotion, int annee) {
		this.nom = nom;
		this.prenom = prenom;
		this.departement = departement;
		this.promotion = promotion;
		this.annee = annee;
	}

	public int compareTo(Stagiaire unStagiaire){
		
		if(this.nom.compareToIgnoreCase(unStagiaire.getNom())<0){
			return -1;
		}else if(this.nom.compareToIgnoreCase(unStagiaire.getNom())>0){
			return 1;	
		}else{
			if(this.prenom.compareToIgnoreCase(unStagiaire.getPrenom())<0){
				return -1;
			}else if(this.prenom.compareToIgnoreCase(unStagiaire.getPrenom())>0){
				return 1;	
			}else{
				if(this.promotion.compareToIgnoreCase(unStagiaire.getPromotion())<0){
					return -1;
				}else if(this.promotion.compareToIgnoreCase(unStagiaire.getPromotion())>0){
					return 1;	
				}else{
					if(this.annee<unStagiaire.getAnnee()){
						return -1;
					}else if(this.annee>unStagiaire.getAnnee()){
						return 1;	
					}else{
						if(this.departement.compareToIgnoreCase(unStagiaire.getDepartement())<0){
							return -1;
						}else if(this.departement.compareToIgnoreCase(unStagiaire.getDepartement())>0){
							return 1;	
						}else{
							return 0;
						}
					}
				}
			}
		}
	

	}
	
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getDepartement() {
		return departement;
	}

	public void setDepartement(String departement) {
		this.departement = departement;
	}

	public String getPromotion() {
		return promotion;
	}

	public void setPromotion(String promotion) {
		this.promotion = promotion;
	}

	public int getAnnee() {
		return annee;
	}

	public void setAnnee(int annee) {
		this.annee = annee;
	}
	
	
	

}
