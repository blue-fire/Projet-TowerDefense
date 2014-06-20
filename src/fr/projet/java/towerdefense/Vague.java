package fr.projet.java.towerdefense;

import java.util.ArrayList;

import fr.projet.java.towerdefense.elementDeLaCarte.Ennemi;
import fr.projet.java.towerdefense.elementDeLaCarte.EnnemiFaible;
import fr.projet.java.towerdefense.elementDeLaCarte.EnnemiPuissant;

public class Vague {

	private ArrayList<Ennemi> ennemis = new ArrayList<Ennemi>();
	private int nbEnnemis;
	
	public Vague(int niveau) {
		
		switch (niveau) {
		case 1:
			vague1();
			break;
		case 2:
			vague2();
			break;
		case 3:
			vague3();
			break;
		default:
			int nombreDEnnemisDuNiveau = niveau*5;
			for (int ennemiCourant = nombreDEnnemisDuNiveau; ennemiCourant > 0 ; ennemiCourant--)
				this.ennemis.add(new EnnemiFaible(ennemiCourant, niveau*2));
			this.nbEnnemis = this.ennemis.size();
		}
	}
	
	public int obtenirNbEnnemis() {
		return nbEnnemis;
	}
	
	public Ennemi obtenirEnnemi(int index) {
		return ennemis.get(index);
	}
	
	private void vague1() {
		for (int ennemiCourant = 10; ennemiCourant > 0 ; ennemiCourant--)
			this.ennemis.add(new EnnemiFaible(ennemiCourant, 5));
		this.nbEnnemis = this.ennemis.size();
	}
	
	private void vague2() {
		for (int ennemiCourant = 10; ennemiCourant > 0 ; ennemiCourant--)
			this.ennemis.add(new EnnemiFaible(ennemiCourant, 5));
		this.ennemis.add(new EnnemiPuissant(11, 15));
		this.nbEnnemis = this.ennemis.size();
	}
	
	private void vague3() {
		for (int ennemiCourant = 20; ennemiCourant > 0 ; ennemiCourant--)
			if (ennemiCourant%5 == 0)
				this.ennemis.add(new EnnemiPuissant(ennemiCourant, 40));
			else 
				this.ennemis.add(new EnnemiFaible(ennemiCourant, 10));
		this.nbEnnemis = this.ennemis.size();
	}
}
