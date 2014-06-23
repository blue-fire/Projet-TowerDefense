package fr.projet.java.towerdefense.elementDeLaCarte;

import javax.swing.ImageIcon;

/**
 * @author Romain Un ennemi d'apparence puisant.
 */
public class EnnemiPuissant extends Ennemi {

	/**
	 * Un ennemi.
	 * 
	 * @param numeroEnnemi
	 *            le numero de l'ennemi dans la vague.
	 * @param vie
	 *            La vie de l'ennemi.
	 */
	public EnnemiPuissant(int numeroEnnemi, int vie) {
		super(numeroEnnemi, vie);
	}

	@Override
	protected void affecterUnIcon() {
		this.icon = new ImageIcon(
				"ressources/images/ennemis/ennemi_puissant_1.png");
	}

}
