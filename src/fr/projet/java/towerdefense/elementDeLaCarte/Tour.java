package fr.projet.java.towerdefense.elementDeLaCarte;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import fr.projet.java.towerdefense.Position;

/**
 * @author Romain
 * 
 *         Une tour qui permet de blesser les ennemis.
 */
public class Tour extends ElementAPacerSuRLaCarte {

	/**
	 * Le prix de construction de ce type de tour.
	 */
	public static final int PRIX_DE_CONSTRUCTION = 100;
	
	private static final int DOMMAGE_PAR_DEFAUT = 1;
	private static final int PORTEE_PAR_DEFAUT = 1;
	private static final int PRIX_DE_PREMIERE_AMELIORATION = 150;

	private Icon icon;

	private final Position position;

	private int dommage;
	private int portee;
	private int niveau;
	
	private int prixAmelioration;

	/**
	 * Une tour qui a une portee un niveau et fait des dommage.
	 * determine aussi la position a laquelle sera placer la tour.
	 * @param position
	 *            la position de la tour.
	 */
	public Tour(Position position) {
		this.position = position;
		
		this.prixAmelioration = PRIX_DE_PREMIERE_AMELIORATION;
		
		this.dommage = DOMMAGE_PAR_DEFAUT;
		this.portee = PORTEE_PAR_DEFAUT;
		this.niveau = 1;
		affecterUnIcone();
	}

	/**
	 * La tour augmente d'un niveau
	 */
	public void augmenterNiveau() {
		this.portee++;
		this.dommage += 2;
		this.niveau++;
		this.prixAmelioration += 50;
		affecterUnIcone();
	}

	/**
	 * @return la position de la tour.
	 */
	public Position obtenirPosition() {
		return this.position;
	}

	/**
	 * @return les dommage de la tour.
	 */
	public int obtenirDommage() {
		return this.dommage;
	}

	/**
	 * @return La portee de la tour.
	 */
	public int obtenirPortee() {
		return this.portee;
	}

	/**
	 * @return Le niveau de la tour.
	 */
	public int obtenirNiveau() {
		return this.niveau;
	}

	/**
	 * @return l'icone de la tour.
	 */
	public Icon obtenirLIcone() {
		return this.icon;
	}

	/**
	 * @return Le prix d'une amelioration.
	 */
	public int prixDeLAmelioration() {
		return this.prixAmelioration;
	}
	
	/**
	 * @return Le prix rendu si la tour est detruite.
	 */
	public int prixDeDestruction() {
		return ( (this.niveau+1) * 50 );
	}
	
	
	private void affecterUnIcone() {
		if (this.niveau > 2)
			this.icon = new ImageIcon(
					"ressources/images/tours/tour_puissante.png");
		else
			this.icon = new ImageIcon("ressources/images/tours/tour_faible.png");
	}
}
