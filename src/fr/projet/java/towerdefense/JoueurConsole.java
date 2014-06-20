package fr.projet.java.towerdefense;

import java.util.Scanner;

import fr.projet.java.towerdefense.exception.AnnulerException;
import fr.projet.java.towerdefense.exception.FinirLeTourException;

public class JoueurConsole implements Joueur {

	public int vie = 100;

	@Override
	public ActionUtilisateur choisirUneAction() throws FinirLeTourException {
		Scanner scan = new Scanner(System.in);

		System.out.println("Vous souhaiter créer une tour :");
		
		int nbTour = scan.nextInt();

		if (nbTour == -1)
			throw new FinirLeTourException();
		
		return ActionUtilisateur.creerTour;
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
