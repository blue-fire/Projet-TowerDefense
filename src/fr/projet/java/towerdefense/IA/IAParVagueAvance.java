package fr.projet.java.towerdefense.IA;

import java.util.ArrayList;
import java.util.Iterator;

import fr.projet.java.towerdefense.Chemin;
import fr.projet.java.towerdefense.Position;
import fr.projet.java.towerdefense.elementDeLaCarte.Carte;
import fr.projet.java.towerdefense.exception.CheminImpossibleException;
public class IAParVagueAvance implements IntelligenceArtificiel {

	private static final Integer EST_UNE_TOUR = -1;
	private Integer[][] carteCoefficient;

	public IAParVagueAvance() {
		this.carteCoefficient = new Integer[Carte.TAILLE_X_CARTE][Carte.TAILLE_Y_CARTE];
	}

	@Override
	public Chemin obtenirUnChemin(Carte carte) throws CheminImpossibleException {

		ArrayList<Position> positionDeCoeffActuel = new ArrayList<Position>();
		ArrayList<Position> positionACoefficiente = new ArrayList<Position>();

		ArrayList<Position> cheminDefinitif = new ArrayList<Position>();
		ArrayList<Position> positionAdjacente = new ArrayList<Position>();

		Position positionCheminActuel = Carte.POSITION_ARRIVE;
		Position positionATesterPourCoefficient;

		int coefficientActuel = 0;
		int coefficientDeReference = 1000;

		viderLaCarteDesCoefficient();
		placerTour(carte);

		remplirCase(Carte.POSTION_DEPART, 0);

		// Creation de le carte des coefficients.
		while (!carteRemplie(coefficientActuel)) {

			// Mise a zero de positionACoefficiente
			positionACoefficiente = new ArrayList<Position>();

			// Les positions contenant le coefficient actuel.
			positionDeCoeffActuel = positionContenant(coefficientActuel);

			// Augmentation du coefficient.
			coefficientActuel++;

			// Les voisines de ces positions.
			Iterator<Position> positionActuel = positionDeCoeffActuel
					.iterator();
			while (positionActuel.hasNext()) {
				positionACoefficiente.addAll(carte
						.casesAdjacentes(positionActuel.next()));
			}

			// Affectation des coefficients.
			Iterator<Position> iteratorDePositionACoefficiente = positionACoefficiente
					.iterator();
			while (iteratorDePositionACoefficiente.hasNext()) {
				Position position = iteratorDePositionACoefficiente.next();
				remplirCase(position,
						coefficientActuel + carte.nombreDeTourAPortee(position));
			}

		}

		// Creation du chemin.
		while (!positionCheminActuel.equals(Carte.POSTION_DEPART)) {
			
			// Ajout de la position au chemin
			if ( carte.estUneTour(positionCheminActuel) ) throw new CheminImpossibleException();
			cheminDefinitif.add(0, positionCheminActuel);
			
			// Si il n'y a pas de coefficient dans les cases adjacentes le chemin est impossible.
			positionAdjacente = casesAdjacentesCoefficientees(positionCheminActuel);
			if ( positionAdjacente.size() == 0 ) throw new CheminImpossibleException();
			Iterator<Position> iteratorDePositionAdjacente = positionAdjacente
					.iterator();
			
			// Determinisation de la position adjacente la moins coefficienté.
			while (iteratorDePositionAdjacente.hasNext()) {
				positionATesterPourCoefficient = iteratorDePositionAdjacente
						.next();
				// Vérifie si le coefficient est plus petit.
				if (carteCoefficient[positionATesterPourCoefficient
										.getX()][positionATesterPourCoefficient.getY()] != null
						&& carteCoefficient[positionATesterPourCoefficient
								.getX()][positionATesterPourCoefficient.getY()] != EST_UNE_TOUR
						&& carteCoefficient[positionATesterPourCoefficient.getX()][positionATesterPourCoefficient
						                                   						.getY()] < coefficientDeReference) {
					positionCheminActuel = new Position(
							positionATesterPourCoefficient.getX(),
							positionATesterPourCoefficient.getY());
					coefficientDeReference = carteCoefficient[positionATesterPourCoefficient
							.getX()][positionATesterPourCoefficient.getY()];
				}
				
			}


		}
		
		//Ajout de la dernière position du chemin
		cheminDefinitif.add(0, positionCheminActuel);

		// Affectation et retour du chemin
		Chemin chemin = new Chemin(cheminDefinitif);
		return chemin;
	}

	private ArrayList<Position> casesAdjacentesCoefficientees(Position position) {
		ArrayList<Position> positionAdjacente = new ArrayList<Position>();

		if ( (position.getX() > 0)
				&& ( carteCoefficient[position.getX()-1][position.getY()] != null)
				&& ( carteCoefficient[position.getX()-1][position.getY()] != EST_UNE_TOUR ) )
			positionAdjacente.add(new Position(position.getX() - 1, position
					.getY()));
		if ((position.getX() + 1 < Carte.TAILLE_X_CARTE)
				&& ( carteCoefficient[position.getX()+1][position.getY()] != null)
				&& ( carteCoefficient[position.getX()+1][position.getY()] != EST_UNE_TOUR ) )
			positionAdjacente.add(new Position(position.getX() + 1, position
					.getY()));
		if ((position.getY() > 0)
				&& ( carteCoefficient[position.getX()][position.getY()-1] != null)
				&& ( carteCoefficient[position.getX()][position.getY()-1] != EST_UNE_TOUR ) )
			positionAdjacente.add(new Position(position.getX(),
					position.getY() - 1));
		if ((position.getY() + 1 < Carte.TAILLE_Y_CARTE)
				&& ( carteCoefficient[position.getX()][position.getY()+1] != null)
				&& ( carteCoefficient[position.getX()][position.getY()+1] != EST_UNE_TOUR ) )
			positionAdjacente.add(new Position(position.getX(),
					position.getY() + 1));

		return positionAdjacente;
	}

	private boolean carteRemplie(int coeff) {
		for (int x = 0; x < Carte.TAILLE_X_CARTE; x++)
			for (int y = 0; y < Carte.TAILLE_Y_CARTE; y++)
				if ((carteCoefficient[x][y] != null)
						&& (carteCoefficient[x][y] >= coeff))
					return false;
		return true;
	}

	private ArrayList<Position> positionContenant(Integer coefficient) {
		ArrayList<Position> positionContenantLeCoeff = new ArrayList<Position>();
		for (int x = 0; x < Carte.TAILLE_X_CARTE; x++)
			for (int y = 0; y < Carte.TAILLE_Y_CARTE; y++) {
				if ( coefficient.equals(carteCoefficient[x][y]) )
					positionContenantLeCoeff.add(new Position(x, y));
			}
		return positionContenantLeCoeff;
	}

	private void remplirCase(Position position, int coeff) {
		if (carteCoefficient[position.getX()][position.getY()] == null)
			carteCoefficient[position.getX()][position.getY()] = coeff;
	}

	private void viderLaCarteDesCoefficient() {
		for (int x = 0; x < Carte.TAILLE_X_CARTE; x++)
			for (int y = 0; y < Carte.TAILLE_Y_CARTE; y++)
				this.carteCoefficient[x][y] = null;
	}

	private void placerTour(Carte carte) {
		for (int x = 0; x < Carte.TAILLE_X_CARTE; x++)
			for (int y = 0; y < Carte.TAILLE_Y_CARTE; y++) {
				Position position = new Position(x, y);
				if (carte.estUneTour(position))
					this.carteCoefficient[x][y] = EST_UNE_TOUR;
			}

	}

}
