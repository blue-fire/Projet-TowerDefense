package fr.projet.java.towerdefense.elementDeLaCarte;


public class Case {

	ElementAPacerSuRLaCarte contenu;

	public boolean estVide() {
		if (this.contenu == null) return true;
		return false;
	}
	
	public void placerUnElement(ElementAPacerSuRLaCarte elmentAPlacer) {
		this.contenu = elmentAPlacer;
	}

	public void supprimerLElement() {
		this.contenu = null;
	}
	
	public boolean estUneTour() {
		if (this.contenu instanceof Tour) return true;
		return false;
	}

	public ElementAPacerSuRLaCarte obtenirLElement() {
		return this.contenu;
	}

	public boolean estUnEnnemi() {
		if (this.contenu instanceof Ennemi) return true;
		return false;
	}
	
}
