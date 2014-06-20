package fr.projet.java.towerdefense.elementDeLaCarte;

import java.util.Random;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public class Ennemi extends ElementAPacerSuRLaCarte {
	private static final int VIE_PAR_DEFAUT = 5;
	private int positionSurLeChemin;
	private int vie;
	private int numeroEnnemi;
	private Icon icon;
	
	public Ennemi(int numeroEnnemi) {
		this.positionSurLeChemin = 0;
		this.vie = VIE_PAR_DEFAUT;
		this.numeroEnnemi = numeroEnnemi;
		affecterUnIcon();
	}
	
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
	
	private void affecterUnIcon() {
		Random generateur = new Random();
		int image = generateur.nextInt(3);
		
		switch (image) {
		case 1 :
			this.icon = new ImageIcon("ressources/images/ennemis/ennemi_faible_1.png");
			break;
		case 2 :
			this.icon = new ImageIcon("ressources/images/ennemis/ennemi_faible_2.png");
			break;
		default :
			this.icon =  new ImageIcon("ressources/images/ennemis/ennemi_faible_3.png");
		}
	}
}
