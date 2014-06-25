package fr.projet.java.towerdefense.elementDeLaCarte;

import java.util.ArrayList;
import java.util.Iterator;

import fr.projet.java.towerdefense.Chemin;
import fr.projet.java.towerdefense.Position;
import fr.projet.java.towerdefense.exception.PositionInvalideException;

/**
 * @author Romain Une carte su laquelle on place des tour et des ennemis.
 */
public class Carte {

	/**
	 * La position de depart des ennemis.
	 */
	public static Position POSITION_ARRIVE = new Position(9, 5);
	/**
	 * La position d'arriver des ennemis.
	 */
	public static Position POSTION_DEPART = new Position(0, 5);

	/**
	 * La taille x de la carte.
	 */
	public static int TAILLE_X_CARTE = 10;
	/**
	 * La tzille y de la carte.
	 */
	public static int TAILLE_Y_CARTE = 10;

	private Case[][] carte;
	private Chemin cheminEnnemi;
	private ArrayList<Ennemi> ennemis;
	private ArrayList<Tour> tours;

	/**
	 * La carte initialiser avec des cases.
	 */
	public Carte() {
		this.carte = new Case[TAILLE_X_CARTE][TAILLE_Y_CARTE];
		for (int y = 0; y < TAILLE_Y_CARTE; y++)
			for (int x = 0; x < TAILLE_X_CARTE; x++)
				this.carte[x][y] = new Case();
		ennemis = new ArrayList<Ennemi>();
		tours = new ArrayList<Tour>();
	}

	/**
	 * Avencer tous les ennemis present sur la carte sur leurs chemin.
	 * 
	 * @return Le nombre d'ennemi qui se trouve sur la case d'arrive.
	 */
	public int avancerEnnemi() {
		int nombreDennemiPasse = 0;
		Iterator<Ennemi> iteratorDEnnemi = ennemis.iterator();
		Ennemi ennemiCourant;

		// Pour tout les ennemis on regarde leurs position sur le chemin
		// ( attribut ) et on l'avance à la position suivante.
		while (iteratorDEnnemi.hasNext()) {
			ennemiCourant = iteratorDEnnemi.next();
			deplacerUnEnnemi(cheminEnnemi.getPosition(ennemiCourant
					.getPositionSurLeChemin()),
					cheminEnnemi.getPosition(ennemiCourant
							.getPositionFutureSurLeChemin()));
			ennemiCourant.avancer();

			// Si il se trouve sur la derniére case on le supprime et on
			// endommage le joueur.
			if (ennemiCourant.getPositionSurLeChemin() == (cheminEnnemi
					.getTaille()) - 1) {
				nombreDennemiPasse++;
				iteratorDEnnemi.remove();
			}
		}

		return nombreDennemiPasse;
	}

	/**
	 * Obtenir les position adjacente a une position.
	 * 
	 * @param position
	 *            La position dont a va regarder les position autour.
	 * @return La liste des position adjacentes.
	 */
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

	/**
	 * Obtenir la liste des position adjacente a n case.
	 * 
	 * @param pos
	 *            La position a partir de laquelle on va regarder autou.
	 * @param n
	 *            Le nombre de case autour de la position.
	 * @return La liste des position adjacente a n cases.
	 */
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

	/**
	 * Deplace un ennemis sur la carte.
	 * 
	 * @param positionDepart
	 *            la position ou se trouve l'ennemi.
	 * @param positionArrive
	 *            La position a laquelle se trouvera l'ennemi.
	 */
	public void deplacerUnEnnemi(Position positionDepart,
			Position positionArrive) {
		this.carte[positionArrive.getX()][positionArrive.getY()]
				.placerUnElement(this.carte[positionDepart.getX()][positionDepart
						.getY()].obtenirLElement());
		this.carte[positionDepart.getX()][positionDepart.getY()]
				.supprimerLElement();
	}

	/**
	 * Endommage la vie des ennemi en fonction des tours dont ils sont la cible.
	 */
	public void endommagerLesEnnemis() {
		ArrayList<Position> positions = new ArrayList<Position>();
		Iterator<Position> iteratorDePosition;

		Iterator<Tour> iteratorDeTour = tours.iterator();

		// Pour toutes les tours on vérifie les position qu'elles peuvent
		// toucher.
		while (iteratorDeTour.hasNext()) {
			Position position = null;
			Tour tourCourante = iteratorDeTour.next();
			positions = positionDansLaPortee(tourCourante);
			iteratorDePosition = positions.iterator();
			int rangDeLEnnemi = 0;

			Position positionDeLEnnemiAAttaquer = null;

			// On parcourt les positions et on note l'ennemi de rang le plus
			// faible ( le plus avancé sur le chemin ).
			while (iteratorDePosition.hasNext()) {
				position = iteratorDePosition.next();
				if (this.estUnEnnemi(position)) {
					if (this.obtenirLEnnemi(position).obtenirNumeroEnnemi() > rangDeLEnnemi)
						positionDeLEnnemiAAttaquer = position;
				}
			}
			// Si un ennemi a été trouvé on lui inflige des dégâts.
			if ((positionDeLEnnemiAAttaquer != null)
					&& (this.estUnEnnemi(positionDeLEnnemiAAttaquer)))
				obtenirLEnnemi(positionDeLEnnemiAAttaquer).recevoirDommage(
						tourCourante.obtenirDommage());
		}
	}

	/**
	 * La position cible est un ennemis.
	 * 
	 * @param position
	 *            la position cible
	 * @return Vrai si c'est un ennemi faux sinon.
	 */
	public boolean estUnEnnemi(Position position) {
		if (this.carte[position.getX()][position.getY()].estUnEnnemi())
			return true;
		return false;
	}

	/**
	 * La position cible est un tour.
	 * 
	 * @param position
	 *            la position cible
	 * @return Vrai si c'est un tour faux sinon.
	 */
	public boolean estUneTour(Position position) {
		if (this.carte[position.getX()][position.getY()].estUneTour())
			return true;
		return false;
	}

	/**
	 * La case cible est vide
	 * 
	 * @param position
	 *            La case cible.
	 * @return Vrai si la case est vide faux sinon.
	 */
	public boolean laCaseEstVide(Position position) {
		return this.carte[position.getX()][position.getY()].estVide();
	}

	/**
	 * Met a jour le chemin des ennemi sur la carte.
	 * 
	 * @param cheminEnnemi
	 *            Le nouveau chemin.
	 */
	public void mettreAJourLeChemin(Chemin cheminEnnemi) {
		this.cheminEnnemi = cheminEnnemi;
	}

	/**
	 * Obtenir le nombre de tour a portee d'une case.
	 * 
	 * @param position
	 *            la position de la case.
	 * @return Le nombre de tour a portee.
	 */
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

	/**
	 * obtenir l'ennemis d'une case.
	 * 
	 * @param position
	 *            la position de la case
	 * @return l'ennemi.
	 */
	public Ennemi obtenirLEnnemi(Position position) {
		if (estUnEnnemi(position))
			return (Ennemi) this.carte[position.getX()][position.getY()]
					.obtenirLElement();
		return null;
	}

	/**
	 * Place un ennemi sur la case
	 * 
	 * @param position
	 *            la postion de la case
	 * @param ennemiAPlacer
	 *            l'ennemi a placer.
	 */
	public void placerUnEnnemi(Position position, Ennemi ennemiAPlacer) {
		this.carte[position.getX()][position.getY()]
				.placerUnElement(ennemiAPlacer);
		this.ennemis.add(ennemiAPlacer);
	}

	/**
	 * Place un tour sur la case
	 * 
	 * @param tourAPlacer
	 *            la tour a placer.
	 * @throws PositionInvalideException
	 *             La contient deja quelque chose.
	 */
	public void placerUneTour(Tour tourAPlacer)
			throws PositionInvalideException {
		if (!this.laCaseEstVide(tourAPlacer.obtenirPosition()))
			throw new PositionInvalideException();
		this.tours.add(tourAPlacer);
		this.carte[tourAPlacer.obtenirPosition().getX()][tourAPlacer
				.obtenirPosition().getY()].placerUnElement(tourAPlacer);
	}

	/**
	 * Il n'y a plus d'ennemi sur la carte.
	 * 
	 * @return vrai si il n'y a plus d'ennemi sur la carte, faux sinon.
	 */
	public boolean plusDEnnemiSurLaCarte() {
		if (this.ennemis.size() == 0)
			return true;
		return false;
	}

	/**
	 * Supprimer la tour de la carte.
	 * 
	 * @param tour
	 *            la tour a supprimer.
	 */
	public void supprimerLaTourLaCarte(Tour tour) {
		carte[tour.obtenirPosition().getX()][tour.obtenirPosition().getY()]
				.supprimerLElement();
		this.tours.remove(tour);
	}

	/**
	 * Si la vie des ennemi present sur la carte est inferieur ou egal a zero il
	 * sont supprimer.
	 * 
	 * @return le nombre d'ennemis detruits.
	 */
	public int verifierVieEnnemi() {
		Iterator<Ennemi> iteratorDEnnemi = ennemis.iterator();
		int nombreDennemisDetruits = 0;

		while (iteratorDEnnemi.hasNext()) {
			Ennemi ennemiCourant = iteratorDEnnemi.next();
			if (!ennemiCourant.estVivant()) {
				iteratorDEnnemi.remove();
				this.supprimerUnEnnemiDeLaCarte(cheminEnnemi
						.getPosition(ennemiCourant.getPositionSurLeChemin()));
				nombreDennemisDetruits++;
			}
		}
		return nombreDennemisDetruits;
	}

	/**
	 * Obtenir la tour de la case
	 * 
	 * @param position
	 *            la position de la case
	 * @return la tour de la case
	 * @throws PositionInvalideException
	 *             la position ne contient pas de tour.
	 */
	public Tour obtenirLaTourDeCase(Position position)
			throws PositionInvalideException {
		if (!this.estUneTour(position))
			throw new PositionInvalideException();
		return (Tour) carte[position.getX()][position.getY()].obtenirLElement();
	}

	/**
	 * Obtenir le nombre de tour de la carte.
	 * 
	 * @return le nombre de tour
	 */
	public int obtenirLeNombreDeTours() {
		return tours.size();
	}

	/**
	 * Obtenir le nombre d'ennemi sur la carte.
	 * 
	 * @return le nombre d'ennemi.
	 */
	public int obtenirLeNombreDEnnemis() {
		return ennemis.size();
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

}
