package fr.projet.java.towerdefense;

import java.util.Scanner;

import fr.projet.java.towerdefense.elementDeLaCarte.Tour;
import fr.projet.java.towerdefense.exception.AnnulerException;
import fr.projet.java.towerdefense.exception.FinirLeTourException;

public class JoueurConsole implements Joueur {

	public int vie = 100;

	@Override
	public Tour choisirUneTour() throws FinirLeTourException {
		Scanner scan = new Scanner(System.in);

		System.out.println("Choisir une tour :");
		
		int nbTour = scan.nextInt();

		if (nbTour == -1)
			throw new FinirLeTourException();
		
		return new Tour();
	}

	@Override
	public Position choisirUnePosition() throws AnnulerException {
		Scanner scan = new Scanner(System.in);

		System.out.println("Choisir une position :");
		
		int x = scan.nextInt();

		if (x == -1)
			throw new AnnulerException();

		int y = scan.nextInt();

		return new Position(x, y);

	}

	public void modifierVie(int vie) {
		this.vie += vie;
	}

	public int getVie() {
		return this.vie;
	}

}
