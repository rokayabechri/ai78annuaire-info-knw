package fr.afcepf.ai78.projet1.objets;

public class Stagiaire {
	
	protected String nom;
	protected String prenom;
	protected String departement;
	protected String promotion;
	protected int annee;
	
	
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

	@Override
	public String toString() {
		return "Stagiaire [nom=" + nom + ", prenom=" + prenom
				+ ", departement=" + departement + ", promotion=" + promotion
				+ ", annee=" + annee + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Stagiaire other = (Stagiaire) obj;
		if (annee != other.annee)
			return false;
		if (departement == null) {
			if (other.departement != null)
				return false;
		} else if (!departement.equals(other.departement))
			return false;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		if (prenom == null) {
			if (other.prenom != null)
				return false;
		} else if (!prenom.equals(other.prenom))
			return false;
		if (promotion == null) {
			if (other.promotion != null)
				return false;
		} else if (!promotion.equals(other.promotion))
			return false;
		return true;
	}
	
	public int compareTo(Stagiaire unStagiaire){
		
		if(this.nom.trim().compareToIgnoreCase(unStagiaire.getNom().trim())<0){
			return -1;
		}else if(this.nom.trim().compareToIgnoreCase(unStagiaire.getNom().trim())>0){
			return 1;	
		}else{
			if(this.prenom.trim().compareToIgnoreCase(unStagiaire.getPrenom().trim())<0){
				return -1;
			}else if(this.prenom.trim().compareToIgnoreCase(unStagiaire.getPrenom().trim())>0){
				return 1;	
			}else{
				if(this.promotion.trim().compareToIgnoreCase(unStagiaire.getPromotion().trim())<0){
					return -1;
				}else if(this.promotion.trim().compareToIgnoreCase(unStagiaire.getPromotion().trim())>0){
					return 1;	
				}else{
					if(this.annee<unStagiaire.getAnnee()){
						return -1;
					}else if(this.annee>unStagiaire.getAnnee()){
						return 1;	
					}else{
						if(this.departement.trim().compareToIgnoreCase(unStagiaire.getDepartement().trim())<0){
							return -1;
						}else if(this.departement.trim().compareToIgnoreCase(unStagiaire.getDepartement().trim())>0){
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
