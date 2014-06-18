package fr.projet.java.towerdefense;

import fr.projet.java.towerdefense.elementDeLaCarte.Tour;
import fr.projet.java.towerdefense.exception.AnnulerException;
import fr.projet.java.towerdefense.exception.FinirLeTourException;

public interface Joueur {
	
	public Tour choisirUneTour() throws FinirLeTourException;
	public Position choisirUnePosition() throws AnnulerException;
}
