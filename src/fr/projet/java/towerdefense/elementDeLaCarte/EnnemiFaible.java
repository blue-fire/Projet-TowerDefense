package fr.projet.java.towerdefense.elementDeLaCarte;

import java.util.Random;

import javax.swing.ImageIcon;

public class EnnemiFaible extends Ennemi {
	
	public EnnemiFaible(int numeroEnnemi, int vie) {
		super(numeroEnnemi, vie);
	}

	@Override
	protected void affecterUnIcon() {
				Random generateur = new Random();
				int image = generateur.nextInt(3);
				
				switch (image) {
				case 1 :
					this.icon = new ImageIcon("ressources/images/ennemis/ennemi_faible_1.png");
					break;
				case 2 :
					this.icon = new ImageIcon("ressources/images/ennemis/ennemi_faible_2.png");
					break;
				default :
					this.icon =  new ImageIcon("ressources/images/ennemis/ennemi_faible_3.png");
				}

	}
}

