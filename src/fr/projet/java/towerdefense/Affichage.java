package fr.projet.java.towerdefense;

import fr.projet.java.towerdefense.elementDeLaCarte.Carte;

public interface Affichage {

	public void afficherLaCarte(Carte carte);
	public void afficherLeMenu(int vies, int niveauVague, int nombreDeTours, int nombreDEnnemis);
}
