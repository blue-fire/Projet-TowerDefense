package fr.projet.java.towerdefense.elementDeLaCarte;

public class Ennemi extends ElementAPacerSuRLaCarte {
	private static final int VIE_PAR_DEFAUT = 5;
	private int positionSurLeChemin;
	private int vie;
	private int numeroEnnemi;
	
	public Ennemi(int numeroEnnemi) {
		this.positionSurLeChemin = 0;
		this.vie = VIE_PAR_DEFAUT;
		this.numeroEnnemi = numeroEnnemi;
	}
	
	public Ennemi(int numeroEnnemi, int vie) {
		this.positionSurLeChemin = 0;
		this.vie = vie;
		this.numeroEnnemi = numeroEnnemi;
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
}
