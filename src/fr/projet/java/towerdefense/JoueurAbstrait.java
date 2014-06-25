package fr.projet.java.towerdefense;

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
	
	/**
	 * L'argent du joueur en debut de partie.
	 */
	public static final int ARGENT_PAR_DEFAUT = 1000;
	
	/**
	 * La vie du joueur.
	 */
	protected int vie;
	/**
	 * L'argent du joueur.
	 */
	protected int argent;

	/**
	 * Il possede une vie et de l'argent.
	 */
	public JoueurAbstrait() {
		this.vie = VIE_PAR_DEFAUT;
		this.argent = ARGENT_PAR_DEFAUT;
	}

	@Override
	public void modifierVie(int modificationVie) {
		this.vie += modificationVie;
	}

	@Override
	public int obtenirVie() {
		return this.vie;
	}
	
	@Override
	public int obtenirArgent() {
		return this.argent;
	}
	
	@Override
	public void modifierArgent(int modificationArgent) {
		this.argent += modificationArgent;
	}

}
