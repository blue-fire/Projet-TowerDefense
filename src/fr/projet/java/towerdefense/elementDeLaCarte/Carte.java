package fr.projet.java.towerdefense.elementDeLaCarte;

import java.util.ArrayList;
import java.util.Iterator;

import fr.projet.java.towerdefense.Chemin;
import fr.projet.java.towerdefense.Position;
import fr.projet.java.towerdefense.exception.PositionInvalideException;

public class Carte {

	public static Position POSITION_ARRIVE = new Position(9, 5);
	public static Position POSTION_DEPART = new Position(0, 5);

	public static int TAILLE_X_CARTE = 10;
	public static int TAILLE_Y_CARTE = 10;

	private Case[][] carte;
	private Chemin cheminEnnemi;
	private ArrayList<Ennemi> ennemis;
	private ArrayList<Tour> tours;

	public Carte() {
		this.carte = new Case[TAILLE_X_CARTE][TAILLE_Y_CARTE];
		for (int y = 0; y < TAILLE_Y_CARTE; y++)
			for (int x = 0; x < TAILLE_X_CARTE; x++)
				this.carte[x][y] = new Case();
		ennemis = new ArrayList<Ennemi>();
		tours = new ArrayList<Tour>();
		//CARTE_TEST();
	}

	public int avancerEnnemi() {
		int nombreDennemiPasse = 0;
		Iterator<Ennemi> iteratorDEnnemi = ennemis.iterator();
		Ennemi ennemiCourant;

		while (iteratorDEnnemi.hasNext()) {
			ennemiCourant = iteratorDEnnemi.next();
			deplacerUnEnnemi(cheminEnnemi.getPosition(ennemiCourant
					.getPositionSurLeChemin()),
					cheminEnnemi.getPosition(ennemiCourant
							.getPositionFutureSurLeChemin()));
			ennemiCourant.avancer();

			if (ennemiCourant.getPositionSurLeChemin() == (cheminEnnemi
					.getTaille()) - 1) {
				nombreDennemiPasse++;
				iteratorDEnnemi.remove();
			}
		}

		return nombreDennemiPasse;
	}

	public ArrayList<Position> casesAdjacentes(Position position) {
		ArrayList<Position> positionAdjacente = new ArrayList<Position>();

		if ((position.getX() > 0)
				&& (!estUneTour(new Position(position.getX() - 1,
						position.getY()))))
			positionAdjacente.add(new Position(position.getX() - 1, position
					.getY()));
		if ((position.getX() + 1 < TAILLE_X_CARTE)
				&& (!estUneTour(new Position(position.getX() + 1,
						position.getY()))))
			positionAdjacente.add(new Position(position.getX() + 1, position
					.getY()));
		if ((position.getY() > 0)
				&& (!estUneTour(new Position(position.getX(),
						position.getY() - 1))))
			positionAdjacente.add(new Position(position.getX(),
					position.getY() - 1));
		if ((position.getY() + 1 < TAILLE_Y_CARTE)
				&& (!estUneTour(new Position(position.getX(),
						position.getY() + 1))))
			positionAdjacente.add(new Position(position.getX(),
					position.getY() + 1));

		return positionAdjacente;
	}

	public ArrayList<Position> casesAdjacentesAuRangN(Position pos, int n) {
		ArrayList<Position> positionsAdjacentes = new ArrayList<Position>();
		Position position;

		for (int y = 0; y < TAILLE_Y_CARTE; y++)
			for (int x = 0; x < TAILLE_X_CARTE; x++) {
				position = new Position(x, y);
				if ((Math.abs((Math.abs(x) - Math.abs(pos.getX())))
						+ Math.abs((Math.abs(pos.getY()) - Math.abs(y))) <= n)
						&& (!this.estUneTour(position))
						&& (!position.equals(pos)))
					positionsAdjacentes.add(position);
			}

		return positionsAdjacentes;
	}

	public void deplacerUnEnnemi(Position positionDepart,
			Position positionArrive) {
		this.carte[positionArrive.getX()][positionArrive.getY()]
				.placerUnElement(this.carte[positionDepart.getX()][positionDepart
						.getY()].obtenirLElement());
		this.carte[positionDepart.getX()][positionDepart.getY()]
				.supprimerLElement();
	}

	public void endommagerLesEnnemis(int nombreEnnemi) {
		ArrayList<Position> positions = new ArrayList<Position>();
		Iterator<Position> iteratorDePosition;

		Iterator<Tour> iteratorDeTour = tours.iterator();

		while (iteratorDeTour.hasNext()) {
			Position position = new Position(0,0);
			Tour tourCourante = iteratorDeTour.next();
			positions = positionDansLaPortee(tourCourante);
			iteratorDePosition = positions.iterator();
			int rangDeLEnnemi = nombreEnnemi;
			
			Position positionDeLEnnemiAAttaquer = new Position(0,0);
			
			while (iteratorDePosition.hasNext()) {
				position = iteratorDePosition.next();
				if (this.estUnEnnemi(position)) {
					if ( this.obtenirLEnnemi(position).obtenirNumeroEnnemi() < rangDeLEnnemi )
						positionDeLEnnemiAAttaquer = position;
				}
			}
			if (this.estUnEnnemi(positionDeLEnnemiAAttaquer))
				obtenirLEnnemi(positionDeLEnnemiAAttaquer).recevoirDommage(
					tourCourante.obtenirDommage());
		}
	}

	public boolean estUnEnnemi(Position position) {
		if (this.carte[position.getX()][position.getY()].estUnEnnemi())
			return true;
		return false;
	}

	public boolean estUneTour(Position position) {
		if (this.carte[position.getX()][position.getY()].estUneTour())
			return true;
		return false;
	}

	public boolean laCaseEstVide(Position position) {
		return this.carte[position.getX()][position.getY()].estVide();
	}

	
	public void mettreAJourLeChemin(Chemin cheminEnnemi) {
		this.cheminEnnemi = cheminEnnemi;
	}

	public int nombreDeTourAPortee(Position position) {
		Iterator<Tour> tourCourante = this.tours.iterator();
		int nombreDeTourDansLaPortee = 0;
		ArrayList<Position> positionPossible;
		Iterator<Position> positionCourante;
		while (tourCourante.hasNext()) {
			positionPossible = this.positionDansLaPortee(tourCourante.next());
			positionCourante = positionPossible.iterator();
			while (positionCourante.hasNext()) {
				if (position.equals(positionCourante.next()))
					nombreDeTourDansLaPortee++;
			}

		}
		return nombreDeTourDansLaPortee;
	}

	public Ennemi obtenirLEnnemi(Position position) {
		if (estUnEnnemi(position))
			return (Ennemi) this.carte[position.getX()][position.getY()]
					.obtenirLElement();
		return null;
	}

	public void placerUnEnnemi(Position position, Ennemi ennemiAPlacer) {
		this.carte[position.getX()][position.getY()]
				.placerUnElement(ennemiAPlacer);
		this.ennemis.add(ennemiAPlacer);
	}

	public void placerUneTour(Position position, Tour tourAPlacer)
			throws PositionInvalideException {
		if (this.estUneTour(position))
			throw new PositionInvalideException();
		tourAPlacer.determinerPosition(position);
		this.tours.add(tourAPlacer);
		this.carte[position.getX()][position.getY()]
				.placerUnElement(tourAPlacer);
	}

	public boolean plusDEnnemiSurLaCarte() {
		if (this.ennemis.size() == 0)
			return true;
		return false;
	}

	public void supprimerLaTourLaCarte(Tour tour) {
		carte[tour.obtenirPosition().getX()][tour.obtenirPosition().getY()]
				.supprimerLElement();
		this.tours.remove(tour);
	}


	public void verifierVieEnnemi() {
		Iterator<Ennemi> iteratorDEnnemi = ennemis.iterator();

		while (iteratorDEnnemi.hasNext()) {
			Ennemi ennemiCourant = iteratorDEnnemi.next();
			if (!ennemiCourant.estVivant()) {
				iteratorDEnnemi.remove();
				this.supprimerUnEnnemiDeLaCarte(cheminEnnemi
						.getPosition(ennemiCourant.getPositionSurLeChemin()));
			}
		}
	}

	private ArrayList<Position> positionDansLaPortee(Tour tourCourante) {
		ArrayList<Position> positions;
		positions = this.casesAdjacentesAuRangN(tourCourante.obtenirPosition(),
				tourCourante.obtenirPortee());
		return positions;
	}
	
	private void supprimerUnEnnemiDeLaCarte(Position position) {
		this.carte[position.getX()][position.getY()].supprimerLElement();
	}
	
	@SuppressWarnings("unused")
	private void CARTE_TEST() {
		try {
			this.placerUneTour(new Position(5, 8), new Tour());
			this.placerUneTour(new Position(5, 7), new Tour());
			this.placerUneTour(new Position(5, 6), new Tour());
			this.placerUneTour(new Position(5, 5), new Tour());
			this.placerUneTour(new Position(5, 4), new Tour());
			this.placerUneTour(new Position(5, 3), new Tour());
			this.placerUneTour(new Position(5, 2), new Tour());
			this.placerUneTour(new Position(5, 1), new Tour());
			this.placerUneTour(new Position(5, 0), new Tour());

			this.placerUneTour(new Position(3, 1), new Tour());
			this.placerUneTour(new Position(2, 1), new Tour());
			this.placerUneTour(new Position(1, 1), new Tour());
			this.placerUneTour(new Position(1, 2), new Tour());
			this.placerUneTour(new Position(1, 3), new Tour());
			this.placerUneTour(new Position(1, 4), new Tour());
			this.placerUneTour(new Position(1, 5), new Tour());
			this.placerUneTour(new Position(1, 6), new Tour());
			this.placerUneTour(new Position(1, 7), new Tour());
			this.placerUneTour(new Position(0, 7), new Tour());

			this.placerUneTour(new Position(4, 5), new Tour());
			this.placerUneTour(new Position(3, 5), new Tour());
		}
		catch (PositionInvalideException e) {
		}
	}

	public int obtenirLeNombreDeTours() {
		return this.tours.size();
	}

	public int obtenirLeNombreDEnnemis() {
		return this.ennemis.size();
	}

	public Tour obtenirLaTourDeCase(Position position) throws PositionInvalideException {
		if (!this.estUneTour(position)) throw new PositionInvalideException();
		return (Tour) carte[position.getX()][position.getY()].obtenirLElement();
	}

}
