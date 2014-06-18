package fr.projet.java.towerdefense;

import fr.projet.java.towerdefense.IA.*;
import fr.projet.java.towerdefense.elementDeLaCarte.Carte;
import fr.projet.java.towerdefense.elementDeLaCarte.Tour;
import fr.projet.java.towerdefense.exception.AnnulerException;
import fr.projet.java.towerdefense.exception.CheminImpossibleException;
import fr.projet.java.towerdefense.exception.FinirLeTourException;

public class PartieDeTowerDefense {

	private IntelligenceArtificiel intelligenceArtificiel;
	private Affichage affichage;
	private Joueur joueur;
	private Carte carte;
	private Chemin cheminEnnemi;
	private int vie;

	public PartieDeTowerDefense(Joueur joueur, Affichage affichage) {
		vie = 100;
		this.affichage = affichage;
		this.joueur = joueur;
		this.carte = new Carte();
		this.intelligenceArtificiel = new IAParVagueAvance();
		try {
			this.cheminEnnemi = intelligenceArtificiel.obtenirUnChemin(carte);
		}
		catch (CheminImpossibleException e) {
		}
	}

	public void jouer() {

		int niveauActuel = 1;
		while (vie > 0) {
			jouerUnTour();
			lancerUneNouvelleVague(niveauActuel);
			niveauActuel++;
		}
		System.out.println("Plus de vie !");

	}

	private void jouerUnTour() {
		Tour tour;
		Position position;

		affichage.afficherLaCarte(carte);

		while (true) {
			try {
				tour = joueur.choisirUneTour();
			}
			catch (FinirLeTourException e) {
				break;
			}
			try {
				position = joueur.choisirUnePosition();
				carte.placerUneTour(position, tour);
				this.cheminEnnemi = this.intelligenceArtificiel
						.obtenirUnChemin(carte);
			}
			catch (AnnulerException e) {
				System.err.println("Action annulé.");
			}
			catch (CheminImpossibleException e) {
				carte.supprimerLaTourLaCarte(tour);
				System.err.println("Chemin impossible, action annulé.");
			}
			catch (positionInvalideException e) {
				System.err.println("Case occupé, action annuler.");
			}

			affichage.afficherLaCarte(carte);
		}
	}

	private void lancerUneNouvelleVague(int niveau) {

		Vague vagueCourante = new Vague(niveau);
		int nombreDEnnemiLances = 0;
		carte.mettreAJourLeChemin(cheminEnnemi);

		while (true) {
			try {
				Thread.sleep(200);
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (nombreDEnnemiLances < vagueCourante.obtenirNbEnnemis()) {
				carte.placerUnEnnemi(Carte.POSTION_DEPART,
						vagueCourante.obtenirEnnemi(nombreDEnnemiLances));
				nombreDEnnemiLances++;
			}

			vie -= carte.avancerEnnemi();
			carte.endommagerLesEnnemis(vagueCourante.obtenirNbEnnemis());
			carte.verifierVieEnnemi();
			affichage.afficherLaCarte(carte);
			if (carte.plusDEnnemiSurLaCarte())
				break;
		}

	}

}