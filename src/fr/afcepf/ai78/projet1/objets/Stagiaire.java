package fr.afcepf.ai78.projet1.objets;

/**
 * Stagiaire contains all the data of a trainee.
 * 
 * @author Chouaib
 * @version 1.0
 *
 */
public class Stagiaire {
	
	/**
	 * The trainee's name.
	 */
	protected String nom;
	/**
	 * The trainee's firstname.
	 */
	protected String prenom;
	/**
	 * The trainee's two-letter zip code.
	 */
	protected String departement;
	/**
	 * The trainee's class.
	 */
	protected String promotion;
	/**
	 * The year during which the trainee was in class.
	 */
	protected int annee;
	
	/**
	 * The default constructor, Build an empty Stagiaire.
	 */
	public Stagiaire() {}

	/**
	 * Build a Stagiaire with the provided data.
	 * 
	 * @param nom The name.
	 * @param prenom The firstname.
	 * @param departement The two-letter zip code.
	 * @param promotion The class.
	 * @param annee The year.
	 */
	public Stagiaire(String nom, String prenom, String departement,
			String promotion, int annee) {
		this.nom = nom;
		this.prenom = prenom;
		this.departement = departement;
		this.promotion = promotion;
		this.annee = annee;
	}
	
	/**
	 * Compare two Stagiaire using in order : 
	 * the name, firstname, class, year and zip code.
	 * 
	 * @param unStagiaire The specified Stagiaire to be compared to this.
	 * @return A negative integer, zero, or a positive integer as the specified Stagiaire is greater than, equal to, or less than this Stagiaire, ignoring case considerations.
	 */
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

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
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
	
	/**
	 * Return the current year.
	 * @return The year.
	 */
	public int getAnnee() {
		return annee;
	}
	
	/**
	 * Return the current zip code.
	 * @return The zip code.
	 */
	public String getDepartement() {
		return departement;
	}
	
	/**
	 * Return the current name.
	 * @return The name data.
	 */
	public String getNom() {
		return nom;
	}
	
	/**
	 * Return the current firstname.
	 * @return The firstname data.
	 */
	public String getPrenom() {
		return prenom;
	}
	
	/**
	 * Return the current class.
	 * @return The class.
	 */
	public String getPromotion() {
		return promotion;
	}
	
	/**
	 * Set the year with the provided data.
	 * @param annee The year.
	 */
	public void setAnnee(int annee) {
		this.annee = annee;
	}
	
	/**
	 * Set the zip code with the provided data.
	 * @param departement The zip code.
	 */
	public void setDepartement(String departement) {
		this.departement = departement;
	}
	
	/**
	 * Set the name with the provided data.
	 * @param nom The new name.
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	/**
	 * Set the firstname with the provided data.
	 * @param prenom The new firstname.
	 */
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	
	/**
	 * Set the class with the provided data.
	 * @param promotion The class.
	 */
	public void setPromotion(String promotion) {
		this.promotion = promotion;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Stagiaire [nom=" + nom + ", prenom=" + prenom
				+ ", departement=" + departement + ", promotion=" + promotion
				+ ", annee=" + annee + "]";
	}
}
