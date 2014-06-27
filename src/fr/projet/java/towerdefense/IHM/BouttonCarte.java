package fr.projet.java.towerdefense.IHM;

import java.awt.event.ActionListener;

import javax.swing.JButton;

/**
 * @author Romain Une classe de bouton contenant des coordonnes.
 */
public class BouttonCarte extends JButton {

	private int x;
	private int y;

	/**
	 * Un bouton de la carte.
	 * 
	 * @param x
	 *            La position en x du bouton sur la carte.
	 * @param y
	 *            La position en y du bouton sur la carte.
	 * @param auditeurBoutons
	 *            L'auditeur du bouton.
	 */
	public BouttonCarte(int x, int y,
			ActionListener auditeurBoutons) {
		super();
		this.setFocusPainted(false);
		this.x = x;
		this.y = y;

		this.addActionListener(auditeurBoutons);
	}

	/**
	 * obtenir la position x du bouton.
	 * 
	 * @return La position x du bouton sur la carte.
	 */
	public int obtenirX() {
		return x;
	}

	/**
	 * obtenir la position y du bouton.
	 * 
	 * @return La position y du bouton sur la carte.
	 */
	public int obtenirY() {
		return y;
	}
}
