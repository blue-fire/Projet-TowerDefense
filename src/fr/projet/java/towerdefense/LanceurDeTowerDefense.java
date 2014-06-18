package fr.projet.java.towerdefense;

import javax.swing.SwingUtilities;

import fr.projet.java.towerdefense.IHM.IHMTowerDefense;

public class LanceurDeTowerDefense {

	public static void main(String[] args) {
		
		IHMTowerDefense ihm = new IHMTowerDefense();
		PartieDeTowerDefense partieDeTowerDefense = new PartieDeTowerDefense(ihm,ihm);
		
		SwingUtilities.invokeLater(ihm);
		
		//TODO Possiblité que jouer() se lance avant le thread de swing ... ( Plantage )
		try {
			Thread.sleep(3000);
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		partieDeTowerDefense.jouer();

	}

}
