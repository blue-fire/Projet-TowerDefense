package fr.projet.java.towerdefense.IA;

import fr.projet.java.towerdefense.Chemin;
import fr.projet.java.towerdefense.elementDeLaCarte.Carte;
import fr.projet.java.towerdefense.exception.CheminImpossibleException;

/**
 * @author Romain
 * Defini une intelligence artificiel pour l'otencion d'un chemin.
 */
public interface IntelligenceArtificiel {
	
	/**
	 * Obtenir un chemin possible du point de depart vers l'arrive de la carte.
	 * @param carte La carte sur laquelle on veux obtenir le chemin.
	 * @return Un chemin possible
	 * @throws CheminImpossibleException Aucun chemin n'a été trouvé.
	 */
	public Chemin obtenirUnChemin(Carte carte) throws CheminImpossibleException;
	
}
