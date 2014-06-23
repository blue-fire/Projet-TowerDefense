package fr.projet.java.towerdefense;

import java.util.Scanner;

import fr.projet.java.towerdefense.exception.AnnulerException;
import fr.projet.java.towerdefense.exception.FinirLeTourException;

/**
 * @author Romain Un joueur jouant sur la console.
 */
public class JoueurConsole extends JoueurAbstrait {

	/**
	 * Le constructeur est initialiser grace au super constructeur.
	 */
	public JoueurConsole() {
		super();
	}

	@Override
	public ActionUtilisateur choisirUneAction() throws FinirLeTourException {
		Scanner scan = new Scanner(System.in);

		System.out.println("Vous souhaiter cr�er une tour :");

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

}
