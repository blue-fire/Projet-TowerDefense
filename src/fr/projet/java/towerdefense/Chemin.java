package fr.projet.java.towerdefense;

import java.util.ArrayList;

/**
 * @author Romain
 * 
 *         Un chemin sur la carte.
 */
public class Chemin {

	private ArrayList<Position> positions = new ArrayList<Position>();
	private int taille;

	/**
	 * Construction du chemin avec un ensemble de positions.
	 * 
	 * @param positions
	 *            L'ensemble de positions du chemin.
	 */
	public Chemin(ArrayList<Position> positions) {
		this.positions = positions;
		this.taille = this.positions.size();
	}

	/**
	 * Pour obtenir la taille du chemin.
	 * 
	 * @return La taille du chemin en nombre de positions.
	 */
	public int getTaille() {
		return this.taille;
	}

	/**
	 * Pour obtenir une position particuli�re du chemin.
	 * 
	 * @param position
	 *            Le numero de la position a obtenir.
	 * @return La N ieme position du chemin.
	 */
	public Position getPosition(int position) {
		return this.positions.get(position);
	}

}
