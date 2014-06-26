package fr.projet.java.towerdefense;

import javax.swing.SwingUtilities;

import fr.projet.java.towerdefense.IHM.IHMTowerDefense;

/**
 * @author Romain
 * La classe min de l'application qui initialise les partie et les lance.
 */
public class LanceurDeTowerDefense {

	/**
	 * La classe main...
	 * @param args Ici nul
	 */
	public static void main(String[] args) {
		
		IHMTowerDefense ihm = new IHMTowerDefense();
		PartieDeTowerDefense partieDeTowerDefense = new PartieDeTowerDefense(ihm,ihm);
		
		SwingUtilities.invokeLater(ihm);
		
		//TODO Possiblité que jouer() se lance avant le thread de swing ... ( Plantage )
		try {
			Thread.sleep(5000);
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		partieDeTowerDefense.jouer();

	}

}
