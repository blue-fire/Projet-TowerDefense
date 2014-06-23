package fr.projet.java.towerdefense.IA;

import java.util.ArrayList;

import fr.projet.java.towerdefense.Chemin;
import fr.projet.java.towerdefense.Position;
import fr.projet.java.towerdefense.elementDeLaCarte.Carte;
import fr.projet.java.towerdefense.exception.CheminImpossibleException;

/**
 * @author Romain
 * Une IA de test qui renvoi un chemin prédéfini.
 */
public class IATest implements IntelligenceArtificiel {

	@Override
	public Chemin obtenirUnChemin(Carte carte) throws CheminImpossibleException {
		
		ArrayList<Position> positions = new ArrayList<Position>();
		
		positions.add(new Position(0, 5));
		positions.add(new Position(1, 5));
		positions.add(new Position(2, 5));
		positions.add(new Position(3, 5));
		
		positions.add(new Position(3, 6));
		positions.add(new Position(4, 6));
		positions.add(new Position(5, 6));
		
		positions.add(new Position(5, 5));
		positions.add(new Position(6, 5));
		positions.add(new Position(7, 5));
		positions.add(new Position(8, 5));
		positions.add(new Position(9, 5));

		Chemin chemin = new Chemin(positions);
		return chemin;
	}

}
