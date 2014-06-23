package fr.projet.java.towerdefense.IHM;

import javax.swing.JButton;

/**
 * @author Romain Une classe de bouton contenant des coordonnes.
 */
public class BouttonCarte extends JButton {

	private int x;
	private int y;

	/**
	 * Creation du bouton
	 * 
	 * @param x
	 *            la coordonne x
	 * @param y
	 *            la coordonne y
	 */
	public BouttonCarte(int x, int y) {
		super();

		this.setOpaque(true);

		this.x = x;
		this.y = y;
	}

	/**
	 * @return la coordonnees x.
	 */
	public int obtenirX() {
		return x;
	}

	/**
	 * @return la coordonnees y.
	 */
	public int obtenirY() {
		return y;
	}
}
