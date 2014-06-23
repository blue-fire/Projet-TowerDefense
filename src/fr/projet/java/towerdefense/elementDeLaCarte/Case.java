package fr.projet.java.towerdefense.elementDeLaCarte;

/**
 * @author Romain Une case qui peut contenir un element.
 */
public class Case {

	private ElementAPacerSuRLaCarte contenu;

	/**
	 * @return vrai si la case est vide.
	 */
	public boolean estVide() {
		if (this.contenu == null)
			return true;
		return false;
	}

	/**
	 * Place l'element sur la carte.
	 * 
	 * @param elmentAPlacer
	 *            l'element
	 */
	public void placerUnElement(ElementAPacerSuRLaCarte elmentAPlacer) {
		this.contenu = elmentAPlacer;
	}

	/**
	 * Supprime le contenu de la case.
	 */
	public void supprimerLElement() {
		this.contenu = null;
	}

	/**
	 * @return vrai si la case contient une tour, faux sinon.
	 */
	public boolean estUneTour() {
		if (this.contenu instanceof Tour)
			return true;
		return false;
	}

	/**
	 * @return le contenu de la case.
	 */
	public ElementAPacerSuRLaCarte obtenirLElement() {
		return this.contenu;
	}

	/**
	 * 
	 * @return vrai si la case contient un ennemi, faux sinon.
	 */
	public boolean estUnEnnemi() {
		if (this.contenu instanceof Ennemi)
			return true;
		return false;
	}

}
