package fr.projet.java.towerdefense.elementDeLaCarte;

import javax.swing.Icon;

public abstract class Ennemi extends ElementAPacerSuRLaCarte {
	private int positionSurLeChemin;
	private int vie;
	private int numeroEnnemi;
	protected Icon icon;
	
	public Ennemi(int numeroEnnemi, int vie) {
		this.positionSurLeChemin = 0;
		this.vie = vie;
		this.numeroEnnemi = numeroEnnemi;
		affecterUnIcon();
	}

	public void avancer() {
		this.positionSurLeChemin ++;
	}
	
	public int getPositionSurLeChemin() {
		return this.positionSurLeChemin;
	}
	
	public int getPositionFutureSurLeChemin() {
		return (this.positionSurLeChemin+1);
	}

	public void recevoirDommage(int dommage) {
		this.vie -= dommage;
	}
	
	public int obtenirVie() {
		return this.vie;
	}
	
	public boolean estVivant() {
		return (this.vie > 0);
	}
	
	public int obtenirNumeroEnnemi() {
		return this.numeroEnnemi;
	}
	
	public Icon obtenirLIcone() {
		return this.icon;
	}
	
	protected abstract void affecterUnIcon();
}
