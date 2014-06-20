package fr.projet.java.towerdefense;

import fr.projet.java.towerdefense.exception.AnnulerException;
import fr.projet.java.towerdefense.exception.FinirLeTourException;
import fr.projet.java.towerdefense.exception.PositionInvalideException;

public interface Joueur {
	
	public ActionUtilisateur choisirUneAction() throws FinirLeTourException;
	public Position choisirUnePosition() throws AnnulerException, PositionInvalideException;
}
