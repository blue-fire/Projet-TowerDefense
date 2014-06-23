package fr.projet.java.towerdefense;

import fr.projet.java.towerdefense.exception.AnnulerException;
import fr.projet.java.towerdefense.exception.FinirLeTourException;
import fr.projet.java.towerdefense.exception.PositionInvalideException;

/**
 * Un joueur abstrait.
 * 
 * @author Romain
 * 
 */
public abstract class JoueurAbstrait implements Joueur {

	/**
	 * La vie par defaut du joueur.
	 */
	public static final int VIE_PAR_DEFAUT = 100;
	private int vie;

	/**
	 * Il possede une vie.
	 */
	public JoueurAbstrait() {
		this.vie = VIE_PAR_DEFAUT;
	}

	@Override
	public abstract ActionUtilisateur choisirUneAction()
			throws FinirLeTourException;

	@Override
	public abstract Position choisirUnePosition() throws AnnulerException,
			PositionInvalideException;

	@Override
	public void modifierVie(int Modificationvie) {
		this.vie += Modificationvie;
	}

	@Override
	public int obtenirVie() {
		return this.vie;
	}

}
