package fr.projet.java.towerdefense.IA;

import fr.projet.java.towerdefense.Chemin;
import fr.projet.java.towerdefense.elementDeLaCarte.Carte;
import fr.projet.java.towerdefense.exception.CheminImpossibleException;

public interface IntelligenceArtificiel {
	
	public Chemin obtenirUnChemin(Carte carte) throws CheminImpossibleException;
	
}
