package fr.projet.java.towerdefense.elementDeLaCarte;

import javax.swing.ImageIcon;

public class EnnemiPuissant extends Ennemi {

	public EnnemiPuissant(int numeroEnnemi, int vie) {
		super(numeroEnnemi, vie);
	}

	@Override
	protected void affecterUnIcon() {
		this.icon = new ImageIcon("ressources/images/ennemis/ennemi_puissant_1.png");
		}

	}
