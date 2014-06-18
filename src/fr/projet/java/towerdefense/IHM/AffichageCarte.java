package fr.projet.java.towerdefense.IHM;

import java.awt.Color;
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
				carteDeBouton[caseCouranteX][caseCouranteY].addActionListener(lis);
				this.add(carteDeBouton[caseCouranteX][caseCouranteY]);
			}
	}

	public void mettreAJour(Carte carte) {
		for (int caseCouranteX = 0; caseCouranteX < Carte.TAILLE_X_CARTE; caseCouranteX++)
			for (int caseCouranteY = 0; caseCouranteY < Carte.TAILLE_Y_CARTE; caseCouranteY++)
				if (carte
						.estUneTour(new Position(caseCouranteX, caseCouranteY))) {
					this.carteDeBouton[caseCouranteX][caseCouranteY].setBackground(Color.BLUE);
					this.carteDeBouton[caseCouranteX][caseCouranteY].setIcon(new ImageIcon("ressources/images/tour_depart.png"));
				}
				else if (carte.estUnEnnemi(new Position(caseCouranteX,
						caseCouranteY))) {
					this.carteDeBouton[caseCouranteX][caseCouranteY].setBackground(Color.RED);
					this.carteDeBouton[caseCouranteX][caseCouranteY].setIcon(new ImageIcon("ressources/images/ennemi_depart.png"));
					this.carteDeBouton[caseCouranteX][caseCouranteY]
							.setText(Integer.toString(carte.obtenirLEnnemi(new Position(caseCouranteX,caseCouranteY)).obtenirVie()));
					this.carteDeBouton[caseCouranteX][caseCouranteY].setIconTextGap(0);
					
				}
				else {
					this.carteDeBouton[caseCouranteX][caseCouranteY].setIcon(new ImageIcon());
					this.carteDeBouton[caseCouranteX][caseCouranteY].setBackground(Color.WHITE);
					this.carteDeBouton[caseCouranteX][caseCouranteY].setText("");
				}
	}
}
