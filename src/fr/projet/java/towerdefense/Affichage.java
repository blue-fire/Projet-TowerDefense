package fr.projet.java.towerdefense;

import fr.projet.java.towerdefense.elementDeLaCarte.Carte;

/**
 * @author Romain
 * 
 *         L'affichage ˆ l'Žcran des informations importantes.
 */
public interface Affichage {

	/**
	 * Afficher la carte de la partie.
	 * 
	 * @param carte
	 *            La carte a afficher.
	 */
	public void afficherLaCarte(Carte carte);

	/**
	 * Afficher un menu permettant de choisir les action a effectuer.
	 * 
	 * @param vies
	 *            Le nombres de vies restantes au joueur.
	 * @param niveauVague
	 *            Le niveau de la vague.
	 * @param nombreDeTours
	 *            Le nombre de tours du joueur.
	 * @param nombreDEnnemis
	 *            Le nombre d'ennemis se trouvant sur la carte.
	 */
	public void afficherLeMenu(int vies, int niveauVague, int nombreDeTours,
			int nombreDEnnemis);
}
