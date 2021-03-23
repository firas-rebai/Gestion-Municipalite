package classes;

public class Personnel {
	private int id;
	private String nom;
	private String prenom;
	private String dateNaissaince;
	private int numTel;
	private String email;
	private double salaire;
	private String equip;
	private String poste;

	public Personnel() {
	}
	
	public Personnel(int id, String nom, String prenom, String dateNaissaince, int numTel, String email, double salaire,
			String equip, String poste) {
		
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.dateNaissaince = dateNaissaince;
		this.numTel = numTel;
		this.email = email;
		this.salaire = salaire;
		this.equip = equip;
		this.poste = poste;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getDateNaissaince() {
		return dateNaissaince;
	}

	public void setDateNaissaince(String dateNaissaince) {
		this.dateNaissaince = dateNaissaince;
	}

	public int getNumTel() {
		return numTel;
	}

	public void setNumTel(int numTel) {
		this.numTel = numTel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public double getSalaire() {
		return salaire;
	}

	public void setSalaire(double salaire) {
		this.salaire = salaire;
	}

	public String getEquip() {
		return equip;
	}

	public void setEquip(String equip) {
		this.equip = equip;
	}

	public String getPoste() {
		return poste;
	}

	public void setPoste(String poste) {
		this.poste = poste;
	}

	

}
