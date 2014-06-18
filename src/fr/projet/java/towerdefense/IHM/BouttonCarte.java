package fr.projet.java.towerdefense.IHM;

import javax.swing.JButton;

public class BouttonCarte extends JButton {

	private int x;
	private int y;
	
	public BouttonCarte(int x, int y) {
		super();
		
		this.setOpaque(true);
		
		this.x = x;
		this.y = y;
	}

	public int obtenirX() {
		return x;
	}

	public int obtenirY() {
		return y;
	}
}
