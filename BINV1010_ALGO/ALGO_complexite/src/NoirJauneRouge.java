public class NoirJauneRouge {
	
	private Ecolier[] tableTriee;   // table triee d'abord les noirs puis les jaunes puis les rouges
									// cette table ne peut pas contenir d'homonyme.
	private int nombreNoirs;
	private int nombreJaunes;
	private int nombreRouges;
	private static final int NOMBRE_MAX_ECOLIERS = 10;
	
	
	public NoirJauneRouge(){
		this.tableTriee = new Ecolier[NOMBRE_MAX_ECOLIERS];
		this.nombreNoirs = 0;
		this.nombreJaunes = 0;
		this.nombreRouges = 0;
	}

	/**
	 * determine le nombre d ecoliers contenu dans la table
	 * @return le nombre d ecoliers
	 */
	public int nombreEcoliers(){
		return nombreNoirs + nombreJaunes + nombreRouges;
	}
	
	
	public int getNombreNoirs() {
		return nombreNoirs;
	}	

	
	public int getNombreJaunes() {
		return nombreJaunes;
	}
	
	
	public int getNombreRouges() {
		return nombreRouges;
	}


	/**
	 * recherche dans la table un ecolier qui a le nom passe en parametre
	 * @param nom le nom recherche
	 * @return l'indice de la table avec un ecolier de ce nom recherche
	 *         ou -1 si la table ne contient pas d'ecolier avec ce nom
	 */
	private int trouverIndiceEcolier(String nom){

		for (int i = 0; i < nombreEcoliers(); i++) {
			if(tableTriee[i].getNom().equals(nom))
				return i;
		}
		return -1;

	}


	/**
	 * ajoute l ecolier, s'il n'y a pas deja un ecolier avec ce nom et si le nombre max d'ecoliers n'est pas atteint
	 * @param nom le nom de l'ecolier a ajouter
	 * @param couleur la couleur de l'ecolier a ajouter
	 * @return true si l'ajout a pu etre fait, false sinon
	 * @throws IllegalArgumentException en cas de parametre invalide
	 */

	public boolean ajouter(String nom, char couleur){

		if(nom == null||nom.equals(""))
			throw new IllegalArgumentException();

		if(couleur!='n' && couleur!='j' && couleur!='r')
			throw new IllegalArgumentException();

		// Pensez a utiliser la methode trouverIndiceEcolier() donnee ci-dessus
		// pour verifier que le nom n'est pas encore present
		// (les doublons sont refuses)

		// CONTRAINTE :
		// Utilisez l'algorithme d'ajout explique dans le document DrapeauBelge

		//Si l'ajout est possible, il faudra creer un ecolier --> new Ecolier()

		Ecolier ecolier = new Ecolier(nom, couleur);

		if(trouverIndiceEcolier(nom) == -1){
			if (couleur == 'r'){
				tableTriee[NOMBRE_MAX_ECOLIERS-1] = ecolier; //NOMBRE_MAX_ECOLIERS-1 car
				nombreRouges++;								 // 0,1,2,3,4,5,6,7,8,9
			} else if (couleur == 'j') {
				Ecolier ecolier1 = tableTriee[NOMBRE_MAX_ECOLIERS-1-nombreRouges];
				tableTriee[NOMBRE_MAX_ECOLIERS-1-nombreRouges] = ecolier;
				tableTriee[NOMBRE_MAX_ECOLIERS-1] = ecolier1;
				nombreJaunes++;
			} else {
				Ecolier ecolier2 = tableTriee[NOMBRE_MAX_ECOLIERS-1-nombreRouges];
				Ecolier ecolier3 = tableTriee[NOMBRE_MAX_ECOLIERS-1-nombreRouges-nombreJaunes];
				tableTriee[NOMBRE_MAX_ECOLIERS-1] = ecolier2;
				tableTriee[NOMBRE_MAX_ECOLIERS-1-nombreRouges] = ecolier3;
				tableTriee[NOMBRE_MAX_ECOLIERS-1-nombreRouges-nombreJaunes] = ecolier;
				nombreNoirs++;
			}
			return true;
		}
		return false;

	}
	
	
	/**
	 * supprime le ecolier dont le nom est passe en parametre
	 * @param nom le nom du ecolier a supprimer
	 * @return true si le ecolier a ete supprime, false sinon
	 * @throws IllegalArgumentException en cas de parametre invalide
	 */
	public boolean supprimer(String nom){

		if(nom == null||nom.equals(""))
			throw new IllegalArgumentException();

		// Pensez a utiliser la methode trouverIndiceEcolier() donnee ci-dessus
		// L'indice est necessaire pour savoir ou faire des decalages!

		// Grace a l'indice, il est possible de recuperer un ecolier.
		// Pour connaitre sa couleur, pensez a utiliser la methode getCouleur() de la classe Ecolier
		
		// Utilisez l'algorithme de suppression explique dans le document DrapeauBelge

		int indiceEcolier = trouverIndiceEcolier(nom);
		if (indiceEcolier == -1){
			return false;
		}
		char couleurEco = tableTriee[indiceEcolier].getCouleur();
		if (couleurEco == 'r'){
			tableTriee[indiceEcolier] = tableTriee[nombreEcoliers() -1];
			nombreRouges--;
		} else if (couleurEco == 'j') {
			tableTriee[indiceEcolier] = tableTriee[nombreNoirs+nombreJaunes-1];
			tableTriee[nombreNoirs+nombreJaunes-1] = tableTriee[nombreEcoliers() -1];
			nombreJaunes--;
		} else if (couleurEco == 'n') {
			tableTriee[indiceEcolier] = tableTriee[nombreNoirs-1];
			tableTriee[nombreNoirs -1] = tableTriee[nombreNoirs + nombreJaunes -1];
			tableTriee[nombreNoirs + nombreJaunes -1] = tableTriee[nombreEcoliers()-1];
			nombreNoirs--;
		}
		return true;

	}

	// A NE PAS MODIFIER
	// VA SERVIR POUR LES TESTS
	public NoirJauneRouge(Ecolier[] tableARecopier, int nombreNoirs, int nombreJaunes, int nombreRouges) {
		this.tableTriee = new Ecolier[NOMBRE_MAX_ECOLIERS];
		for (int i = 0; i < tableARecopier.length; i++) {
			tableTriee[i]=tableARecopier[i];
		}
		this.nombreNoirs = nombreNoirs;
		this.nombreJaunes = nombreJaunes;
		this.nombreRouges = nombreRouges;
	}

	// A NE PAS MODIFIER
	// VA SERVIR POUR LES TESTS
	public String toString() {
		String aRenvoyer="";
		for (int i = 0; i < nombreEcoliers(); i++) {
			aRenvoyer += "\n" + tableTriee[i];
		}
		return aRenvoyer;
	}

}
