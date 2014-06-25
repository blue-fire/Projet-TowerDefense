package fr.projet.java.towerdefense;

import java.util.ArrayList;

import fr.projet.java.towerdefense.elementDeLaCarte.Ennemi;
import fr.projet.java.towerdefense.elementDeLaCarte.EnnemiFaible;
import fr.projet.java.towerdefense.elementDeLaCarte.EnnemiPuissant;

/**
 * @author Romain Un nouvel ensemble d'ennemis que le joueur va devoir
 *         affronter.
 */
public class Vague {

	private ArrayList<Ennemi> ennemis = new ArrayList<Ennemi>();
	private int nbEnnemis;

	/**
	 * Creer une vague de diffuculte croissante avec le niveau
	 * 
	 * @param niveau
	 *            Le niveau de la vague.
	 */
	public Vague(int niveau) {

		switch (niveau) {
		case 1:
			vague1();
			break;
		case 2:
			vague2();
			break;
		case 3:
			vague3();
			break;
		default:
			int nombreDEnnemisDuNiveau = niveau * 5;
			for (int ennemiCourant = nombreDEnnemisDuNiveau; ennemiCourant > 0; ennemiCourant--) {
				if (ennemiCourant % 5 == 0)
					this.ennemis.add(new EnnemiPuissant(ennemiCourant,
							niveau * 2 + 20));
				else
					this.ennemis
							.add(new EnnemiFaible(ennemiCourant, niveau * 2));
			}
			this.nbEnnemis = this.ennemis.size();
		}
	}

	/**
	 * Obtenir le nombre d'ennimis de la vague.
	 * 
	 * @return Le nombre d'ennemis de la vague.
	 */
	public int obtenirNbEnnemis() {
		return nbEnnemis;
	}

	/**
	 * Retourne un ennemis precis.
	 * 
	 * @param index
	 *            Le numero de l'ennemi que l'on souhaite obtenir.
	 * @return L'ennemi desire.
	 */
	public Ennemi obtenirEnnemi(int index) {
		return ennemis.get(index);
	}

	private void vague1() {
		for (int ennemiCourant = 10; ennemiCourant > 0; ennemiCourant--)
			this.ennemis.add(new EnnemiFaible(ennemiCourant, 5));
		this.nbEnnemis = this.ennemis.size();
	}

	private void vague2() {
		for (int ennemiCourant = 11; ennemiCourant > 1; ennemiCourant--)
			this.ennemis.add(new EnnemiFaible(ennemiCourant, 5));
		this.ennemis.add(new EnnemiPuissant(1, 15));
		this.nbEnnemis = this.ennemis.size();
	}

	private void vague3() {
		for (int ennemiCourant = 20; ennemiCourant > 0; ennemiCourant--)
			if (ennemiCourant % 5 == 0)
				this.ennemis.add(new EnnemiPuissant(ennemiCourant, 40));
			else
				this.ennemis.add(new EnnemiFaible(ennemiCourant, 10));
		this.nbEnnemis = this.ennemis.size();
	}
}
