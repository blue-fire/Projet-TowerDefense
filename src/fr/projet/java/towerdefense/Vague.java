package fr.projet.java.towerdefense;

import java.util.ArrayList;

import fr.projet.java.towerdefense.elementDeLaCarte.Ennemi;

public class Vague {

	private ArrayList<Ennemi> ennemis = new ArrayList<Ennemi>();
	private int nbEnnemis;
	
	public Vague(int niveau) {
		for (int nombreEnnemi = 0; nombreEnnemi < niveau*5; nombreEnnemi++)
			this.ennemis.add(new Ennemi(nombreEnnemi, niveau*2));
		this.nbEnnemis = this.ennemis.size();
	}
	
	public int obtenirNbEnnemis() {
		return nbEnnemis;
	}
	
	public Ennemi obtenirEnnemi(int index) {
		return ennemis.get(index);
	}
}
