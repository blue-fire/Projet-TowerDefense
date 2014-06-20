package fr.projet.java.towerdefense;

import fr.projet.java.towerdefense.IA.*;
import fr.projet.java.towerdefense.IHM.Menu;
import fr.projet.java.towerdefense.elementDeLaCarte.Carte;
import fr.projet.java.towerdefense.elementDeLaCarte.Tour;
import fr.projet.java.towerdefense.exception.AnnulerException;
import fr.projet.java.towerdefense.exception.CheminImpossibleException;
import fr.projet.java.towerdefense.exception.FinirLeTourException;
import fr.projet.java.towerdefense.exception.PositionInvalideException;

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
		ActionUtilisateur actionJoueur;
		Position position;
		Tour tour;

		affichage.afficherLaCarte(carte);

		while (true) {
			// Le joueur choisi l'action qu'il souhaite effectuer.
			try {
				actionJoueur = joueur.choisirUneAction();
			}
			catch (FinirLeTourException e) {
				break;
			}

			// Le joueur choisi la position a laquelle il souhaite effectuer
			// l'action.
				try {
					position = joueur.choisirUnePosition();
					
					// On effectue l'action.
					if (actionJoueur == ActionUtilisateur.creerTour) {
						tour = new Tour();
						tour.determinerPosition(position);
						carte.placerUneTour(position, tour);
						try {
							cheminEnnemi = intelligenceArtificiel.obtenirUnChemin(carte);
						}
						catch (CheminImpossibleException e) {
							carte.supprimerLaTourLaCarte(tour);
							System.err.println("Chemin imposiible, action annulée.");
						}
					}
					if (actionJoueur == ActionUtilisateur.supprimerTour) {
						tour = carte.obtenirLaTourDeCase(position);
						carte.supprimerLaTourLaCarte(tour);
						try {
							cheminEnnemi = intelligenceArtificiel.obtenirUnChemin(carte);
						}
						catch (CheminImpossibleException e) {
							carte.supprimerLaTourLaCarte(tour);
							System.err.println("Chemin imposiible, action annulée.");
						}
					}
					if (actionJoueur == ActionUtilisateur.ameliorerTour) {
						tour = carte.obtenirLaTourDeCase(position);
						tour.augmenterNiveau();
						try {
							cheminEnnemi = intelligenceArtificiel.obtenirUnChemin(carte);
						}
						catch (CheminImpossibleException e) {
							carte.supprimerLaTourLaCarte(tour);
							System.err.println("Chemin imposiible, action annulée.");
						}
					}
					
				}
				catch (AnnulerException e) {
					System.err.println("Action annuler par l'utilisateur.");
				}
				catch (PositionInvalideException e) {
					System.err.println("La position n'est pas valide.");
				}
				
				
				


			affichage.afficherLaCarte(carte);
			// TODO Un peu sale non ?
			affichage.afficherLeMenu(vie, Menu.NE_PAS_METTRE_A_JOUR,
					carte.obtenirLeNombreDeTours(),
					carte.obtenirLeNombreDEnnemis());
		}
	}

	private void lancerUneNouvelleVague(int niveau) {

		Vague vagueCourante = new Vague(niveau);
		int nombreDEnnemiLances = 0;
		carte.mettreAJourLeChemin(cheminEnnemi);

		while (true) {
			try {
				Thread.sleep(600);
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
			carte.endommagerLesEnnemis();
			carte.verifierVieEnnemi();
			affichage.afficherLaCarte(carte);
			affichage.afficherLeMenu(vie, niveau,
					carte.obtenirLeNombreDeTours(),
					carte.obtenirLeNombreDEnnemis());
			if (carte.plusDEnnemiSurLaCarte())
				break;
		}

	}

}