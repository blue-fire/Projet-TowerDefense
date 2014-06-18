package fr.projet.java.towerdefense.elementDeLaCarte;

import fr.projet.java.towerdefense.Position;

public class Tour extends ElementAPacerSuRLaCarte{
	
	private static final int DOMMAGE_PAR_DEFAUT = 1;
	private static final int PORTEE_PAR_DEFAUT = 4;
	
	private Position position;
	private int dommage = 1;
	private int portee;
	private int niveau;
	
	public Tour() {
		this.dommage = DOMMAGE_PAR_DEFAUT;
		this.portee = PORTEE_PAR_DEFAUT;
		this.niveau = 1;
	}
	
	public void determinerPosition(Position position) {
		this.position = position;
	}
	
	public void augmenterNiveau() {
		this.portee++;
		this.dommage *= 2;
		this.niveau++;
	}
	
	public Position obtenirPosition() {
		return this.position;
	}

	public int obtenirDommage() {
		return this.dommage ;
	}
	
	public int obtenirPortee() {
		return this.portee;
	}
	
	public int obtenirNiveau() {
		return this.niveau;
	}
}
