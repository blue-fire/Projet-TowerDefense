package fr.projet.java.towerdefense;

import java.util.ArrayList;

public class Chemin {

	private ArrayList<Position> positions = new ArrayList<Position>();
	private int taille;
	
	public Chemin(ArrayList<Position> positions) {
		this.positions = positions;
		this.taille = this.positions.size();
	}
	
	public int getTaille() {
		return this.taille;
	}
	
	public Position getPosition(int position) {
		return this.positions.get(position);
	}

}
