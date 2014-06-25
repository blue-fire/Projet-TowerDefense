package fr.projet.java.towerdefense;

import fr.projet.java.towerdefense.elementDeLaCarte.Carte;

/**
 * @author Romain
 * 
 *         L'affichage du jeu en console. Il n'affiche pas de menu.
 */
public class AffichageConsole implements Affichage {

	@Override
	public void afficherLaCarte(Carte carte) {

		String aAfficher = "";
		for (int x = 0; x < Carte.TAILLE_X_CARTE; x++) {
			for (int y = 0; y < Carte.TAILLE_Y_CARTE; y++)
				if (carte.laCaseEstVide(new Position(x, y)))
					aAfficher += '_';
				else if (carte.estUneTour(new Position(x, y)))
					aAfficher += 'T';
				else
					aAfficher += 'E';
			aAfficher += "\n";
		}

		System.out.println(aAfficher);

	}

	@Override
	public void afficherLeMenu(int niveauVague, int nombreDeTours) {

	}

}
