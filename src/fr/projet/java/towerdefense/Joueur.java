package fr.projet.java.towerdefense;

import fr.projet.java.towerdefense.exception.AnnulerException;
import fr.projet.java.towerdefense.exception.FinirLeTourException;
import fr.projet.java.towerdefense.exception.PositionInvalideException;

/**
 * @author Romain
 * 
 *         Demande au joueur des information.
 */
public interface Joueur {

	/**
	 * Le joueur choisi une action a effectuer.
	 * 
	 * @return L'action choisie.
	 * @throws FinirLeTourException
	 *             Le joueur veut finir le tour.
	 */
	public ActionUtilisateur choisirUneAction() throws FinirLeTourException;

	/**
	 * Le joueur choisie une position.
	 * 
	 * @return La posiiton choisie.
	 * @throws AnnulerException
	 *             Le joueur annule l'action.
	 * @throws PositionInvalideException
	 *             La position n'est pas valide.
	 */
	public Position choisirUnePosition() throws AnnulerException,
			PositionInvalideException;

	/**
	 * Obtenir la vie du joueur.
	 * 
	 * @return La vie du joueur.
	 */
	public int obtenirVie();

	/**
	 * Modifier la vie du joueur.
	 * 
	 * @param modificationVie
	 *            La modification de la vie du joueur.
	 */
	public void modifierVie(int modificationVie);
	
	/**
	 * @return L'argent a disposition du joueur.
	 */
	public int obtenirArgent();
	
	/**
	 * Modifie la tresorerie du joueur.
	 * @param modificationArgent La somme a debiter ou crediter au joueur.
	 */
	public void modifierArgent(int modificationArgent);
}
