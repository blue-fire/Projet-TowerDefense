package fr.projet.java.towerdefense.elementDeLaCarte;

import javax.swing.Icon;

/**
 * @author Romain
 *
 */
public abstract class Ennemi extends ElementAPacerSuRLaCarte {
	private int indexDeLaPositionSurLeChemin;
	private int vie;
	private int numeroEnnemi;
	
	/**
	 * L'icone de l'ennemi.
	 */
	protected Icon icon;
	
	/**
	 * Un ennemi.
	 * 
	 * @param numeroEnnemi
	 *            le numero de l'ennemi dans la vague.
	 * @param vie
	 *            La vie de l'ennemi.
	 */
	public Ennemi(int numeroEnnemi, int vie) {
		this.indexDeLaPositionSurLeChemin = 0;
		this.vie = vie;
		this.numeroEnnemi = numeroEnnemi;
		affecterUnIcon();
	}

	/**
	 * Avancer l'index de la position sur le chemin de l'ennemi.
	 */
	public void avancer() {
		this.indexDeLaPositionSurLeChemin ++;
	}
	
	/**
	 * @return l'index de la position sur le chemin.
	 */
	public int getPositionSurLeChemin() {
		return this.indexDeLaPositionSurLeChemin;
	}
	
	/**
	 * @return l'index de la future position sur le chemin.
	 */
	public int getPositionFutureSurLeChemin() {
		return (this.indexDeLaPositionSurLeChemin+1);
	}

	/**
	 * recevoir des degats.
	 * @param dommage les dommage recu
	 */
	public void recevoirDommage(int dommage) {
		this.vie -= dommage;
	}
	
	/**
	 * @return la vie de l'ennemi.
	 */
	public int obtenirVie() {
		return this.vie;
	}
	
	/**
	 * @return vrai si il est vivant faux sinon.
	 */
	public boolean estVivant() {
		return (this.vie > 0);
	}
	
	/**
	 * @return le numero de l'ennemi dans la vague.
	 */
	public int obtenirNumeroEnnemi() {
		return this.numeroEnnemi;
	}
	
	/**
	 * @return l'icone de l'ennemi.
	 */
	public Icon obtenirLIcone() {
		return this.icon;
	}
	
	/**
	 * Affecter un icone a l'ennemi.
	 */
	protected abstract void affecterUnIcon();
}
