import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * implementation de l'interface ListeSimple avec une liste simplement chainee
 */
public class ListeSimpleImpl<E> implements ListeSimple<E> {
	
	private Noeud tete;
	private int taille;
	

	public ListeSimpleImpl(){
		tete = null;
		taille=0;
	}
	
	public int taille(){
		return taille;
	}
		
	public boolean estVide(){
		return taille==0;
	}


	// renvoie l element contenu dans le noeud de tete
	public E premier()throws ListeVideException{
		if (estVide())
			throw new ListeVideException();
		return tete.element;
	}


	// insere un nouveau noeud en tete de liste avec l element 
	public void insererEnTete(E element) {
        tete = new Noeud(element, tete);
		taille++;
	}
	

	// verifie la presence d un noeud contenant l element passe en parametre
	public boolean contient(E element){
		Noeud baladeur = tete;
		while(baladeur!=null) {
			if(baladeur.element.equals(element))
				return true;
			baladeur=baladeur.suivant;
		}
		return false;
	}


	// insere un nouveaud noeud apres le noeud contenant la premiere occurrence de l'element passe en parametre
	public boolean insererApres(E element, E elementAInserer){
		Noeud nouveauNoeud = new Noeud(elementAInserer, null);
		Noeud baladeur = tete;
		while(baladeur!=null) {
			if(baladeur.element.equals(element)) {
				nouveauNoeud.suivant = baladeur.suivant;
				baladeur.suivant = nouveauNoeud;
				taille++;
				return true;
			}
			baladeur=baladeur.suivant;
		}
		return false;
	
	}


	//supprime le noeud contenant la premiere occurrence de l'element passe en parametre
	public boolean supprimer(E element){
		Noeud baladeur = tete;
		Noeud precedent = null;
		while(baladeur!=null) {
			if(baladeur.element.equals(element)) {
				if(precedent==null) {
					tete = baladeur.suivant;
				}else {
					precedent.suivant = baladeur.suivant;
				}
				taille--;
				return true;
			}
			precedent = baladeur;
			baladeur=baladeur.suivant;
		}
		return false;
		
	}
	
	@Override
	public Iterator<E> iterator() {
		return new IterateurImpl();
	}

	
	private class IterateurImpl implements Iterator{
		
		private Noeud noeudNext;
		
	
		// Au depart le noeud "next" est le noeud de tete
		private IterateurImpl() {
			noeudNext =  tete;
		}
		
		@Override
		// verifie si le noeud "next" est null
		public boolean hasNext() {
			Noeud baladeur = noeudNext;
			while (baladeur != null) {
				if (baladeur.element != null)
					return true;
				baladeur = baladeur.suivant;
			}
			return false;
		}

		
		@Override
		// renvoie l element qui se trouve dans le noeud "next"
		// le noeud "next" passe au noeud suivant
		public E next() {
			if (!hasNext())
				throw new NoSuchElementException();
			Noeud baladeur = noeudNext;
			noeudNext = baladeur.suivant;
			return baladeur.element;
		   // pensez a consulter la JAVADOC de la classe Iterator!!!
		}

		
	}

	private class Noeud{
		private E element;
		private Noeud suivant;

		public Noeud(E element, Noeud suivant){
			this.element = element;
			this.suivant = suivant;
		}
	}


	// A NE PAS MODIFIER --> POUR LES TESTS!!!
	public String toString(){
		String aRenvoyer="";
		Noeud baladeur=tete;
		int cpt = 0;
		while(baladeur!=null) {
			cpt++;
			if(cpt>taille){
				aRenvoyer = "boucle infinie dans toString(), chainage a verifier";
				return aRenvoyer;
			}
			aRenvoyer+=" "+baladeur.element;
			baladeur=baladeur.suivant;
		}
		return aRenvoyer;
	}


	// A NE PAS MODIFIER --> POUR LES TESTS!!!
	public ListeSimpleImpl(Object[] table) {
		if(table==null)
			throw new IllegalArgumentException();
		this.taille = table.length;
		for (int i = table.length-1; i>=0; i--) {
			E element = (E)table[i];
			this.tete=new Noeud(element,tete);
		}
	}

}
