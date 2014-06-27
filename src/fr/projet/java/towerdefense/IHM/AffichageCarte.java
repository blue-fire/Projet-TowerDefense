package fr.projet.java.towerdefense.IHM;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import fr.projet.java.towerdefense.Position;
import fr.projet.java.towerdefense.elementDeLaCarte.Carte;
import fr.projet.java.towerdefense.exception.PositionInvalideException;

/**
 * @author Romain Partie du programme destine a afficher la carte.
 */
public class AffichageCarte extends JPanel {

	private GridLayout gridLayout;
	private BouttonCarte[][] carteDeBouton;

	/**
	 * Constructeur de la carte de bouton.
	 * 
	 * @param lis
	 *            Ecouteur des boutons.
	 */
	public AffichageCarte(ActionListener lis) {

		carteDeBouton = new BouttonCarte[Carte.TAILLE_X_CARTE][Carte.TAILLE_Y_CARTE];

		for (int caseCouranteX = 0; caseCouranteX < Carte.TAILLE_X_CARTE; caseCouranteX++)
			for (int caseCouranteY = 0; caseCouranteY < Carte.TAILLE_Y_CARTE; caseCouranteY++)
				this.carteDeBouton[caseCouranteX][caseCouranteY] = new BouttonCarte(
						caseCouranteX, caseCouranteY, lis);

		gridLayout = new GridLayout(Carte.TAILLE_X_CARTE, Carte.TAILLE_Y_CARTE);

		this.setLayout(gridLayout);

		for (int caseCouranteX = 0; caseCouranteX < Carte.TAILLE_X_CARTE; caseCouranteX++)
			for (int caseCouranteY = 0; caseCouranteY < Carte.TAILLE_Y_CARTE; caseCouranteY++) {
				this.add(carteDeBouton[caseCouranteX][caseCouranteY]);
			}
	}

	/**
	 * Mise a jour de la carte.
	 * 
	 * @param carte
	 *            la carte que l'on doit afficher.
	 */
	public void mettreAJour(Carte carte) {
		for (int caseCouranteX = 0; caseCouranteX < Carte.TAILLE_X_CARTE; caseCouranteX++)
			for (int caseCouranteY = 0; caseCouranteY < Carte.TAILLE_Y_CARTE; caseCouranteY++) {
				Position positionCourante = new Position(caseCouranteX,
						caseCouranteY);
				// Afficher les position de depart et d'arriver.
				if (positionCourante.equals(Carte.POSITION_ARRIVE)) {
					this.carteDeBouton[caseCouranteX][caseCouranteY]
							.setIcon(new ImageIcon(
									"ressources/images/drapeau_arriver.png"));
					this.carteDeBouton[caseCouranteX][caseCouranteY]
							.setText("");
				}
				else if (positionCourante.equals(Carte.POSTION_DEPART)) {
					this.carteDeBouton[caseCouranteX][caseCouranteY]
							.setIcon(new ImageIcon(
									"ressources/images/drapeau_depart.png"));
					this.carteDeBouton[caseCouranteX][caseCouranteY]
							.setText("");
				}
				// Afficher les tours.
				else if (carte.estUneTour(positionCourante)) {
					try {
						this.carteDeBouton[caseCouranteX][caseCouranteY]
								.setIcon(carte.obtenirLaTourDeCase(
										positionCourante).obtenirLIcone());
						this.carteDeBouton[caseCouranteX][caseCouranteY]
								.setText(Integer.toString(carte
										.obtenirLaTourDeCase(positionCourante)
										.obtenirNiveau()));
					}
					catch (PositionInvalideException e) {
					}
					this.carteDeBouton[caseCouranteX][caseCouranteY]
							.setHorizontalTextPosition(SwingConstants.CENTER);
					this.carteDeBouton[caseCouranteX][caseCouranteY]
							.setVerticalTextPosition(SwingConstants.TOP);
					this.carteDeBouton[caseCouranteX][caseCouranteY]
							.setIconTextGap(-7);
				}
				// Afficher les ennemis.
				else if (carte.estUnEnnemi(positionCourante)) {
					this.carteDeBouton[caseCouranteX][caseCouranteY]
							.setIcon(carte.obtenirLEnnemi(positionCourante)
									.obtenirLIcone());
					this.carteDeBouton[caseCouranteX][caseCouranteY]
							.setText(Integer.toString(carte.obtenirLEnnemi(
									positionCourante).obtenirVie()));
					this.carteDeBouton[caseCouranteX][caseCouranteY]
							.setIconTextGap(0);

				}
				// Sinon ne rien afficher.
				else {
					this.carteDeBouton[caseCouranteX][caseCouranteY]
							.setIcon(new ImageIcon(""));
					this.carteDeBouton[caseCouranteX][caseCouranteY]
							.setText("");
				}
				this.carteDeBouton[caseCouranteX][caseCouranteY].repaint();
			}
	}

	/**
	 * Desactive les click sur la carte
	 * 
	 * Non utilise : provoque des bug d'affichage.
	 */
	public void desactiverLaCarte() {
		for (int caseCouranteX = 0; caseCouranteX < Carte.TAILLE_X_CARTE; caseCouranteX++)
			for (int caseCouranteY = 0; caseCouranteY < Carte.TAILLE_Y_CARTE; caseCouranteY++) {
				this.carteDeBouton[caseCouranteX][caseCouranteY]
						.setEnabled(false);
			}
	}

	/**
	 * Active les click sur la carte.
	 * 
	 * Non utilise : provoque des bug d'affichage.
	 */
	public void activerLaCarte() {
		for (int caseCouranteX = 0; caseCouranteX < Carte.TAILLE_X_CARTE; caseCouranteX++)
			for (int caseCouranteY = 0; caseCouranteY < Carte.TAILLE_Y_CARTE; caseCouranteY++) {
				this.carteDeBouton[caseCouranteX][caseCouranteY]
						.setEnabled(true);
			}
	}
}
