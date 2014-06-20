package fr.projet.java.towerdefense.IHM;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import fr.projet.java.towerdefense.Position;
import fr.projet.java.towerdefense.elementDeLaCarte.Carte;

public class AffichageCarte extends JPanel {

	private GridLayout gridLayout;
	private BouttonCarte[][] carteDeBouton;

	public AffichageCarte(ActionListener lis) {
		super();

		carteDeBouton = new BouttonCarte[Carte.TAILLE_X_CARTE][Carte.TAILLE_Y_CARTE];

		for (int caseCouranteX = 0; caseCouranteX < Carte.TAILLE_X_CARTE; caseCouranteX++)
			for (int caseCouranteY = 0; caseCouranteY < Carte.TAILLE_Y_CARTE; caseCouranteY++)
				this.carteDeBouton[caseCouranteX][caseCouranteY] = new BouttonCarte(
						caseCouranteX, caseCouranteY);

		gridLayout = new GridLayout(Carte.TAILLE_X_CARTE, Carte.TAILLE_Y_CARTE);

		this.setLayout(gridLayout);

		for (int caseCouranteX = 0; caseCouranteX < Carte.TAILLE_X_CARTE; caseCouranteX++)
			for (int caseCouranteY = 0; caseCouranteY < Carte.TAILLE_Y_CARTE; caseCouranteY++) {
				carteDeBouton[caseCouranteX][caseCouranteY]
						.addActionListener(lis);
				this.add(carteDeBouton[caseCouranteX][caseCouranteY]);
			}
	}

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
					this.carteDeBouton[caseCouranteX][caseCouranteY]
							.setIcon(new ImageIcon(
									"ressources/images/tour_faible.png"));
				}
				// Afficher les ennemis.
				else if (carte.estUnEnnemi(positionCourante)) {
					this.carteDeBouton[caseCouranteX][caseCouranteY]
							.setIcon( carte.obtenirLEnnemi(positionCourante).obtenirLIcone() );
					this.carteDeBouton[caseCouranteX][caseCouranteY]
							.setText(Integer.toString(carte.obtenirLEnnemi(
									new Position(caseCouranteX, caseCouranteY))
									.obtenirVie()));
					this.carteDeBouton[caseCouranteX][caseCouranteY]
							.setIconTextGap(0);

				}
				// Sinon ne rien afficher.
				else {
					this.carteDeBouton[caseCouranteX][caseCouranteY]
							.setIcon(new ImageIcon());
					this.carteDeBouton[caseCouranteX][caseCouranteY]
							.setText("");
				}
			}
	}
	
	public void desactiverLaCarte() {
		for (int caseCouranteX = 0; caseCouranteX < Carte.TAILLE_X_CARTE; caseCouranteX++)
			for (int caseCouranteY = 0; caseCouranteY < Carte.TAILLE_Y_CARTE; caseCouranteY++) {
				this.carteDeBouton[caseCouranteX][caseCouranteY].setEnabled(false);
			}
	}

	public void activerLaCarte() {
		for (int caseCouranteX = 0; caseCouranteX < Carte.TAILLE_X_CARTE; caseCouranteX++)
			for (int caseCouranteY = 0; caseCouranteY < Carte.TAILLE_Y_CARTE; caseCouranteY++) {
				this.carteDeBouton[caseCouranteX][caseCouranteY].setEnabled(true);
			}
	}
}
