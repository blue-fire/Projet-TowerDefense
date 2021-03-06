package fr.projet.java.towerdefense;

import fr.projet.java.towerdefense.IA.*;
import fr.projet.java.towerdefense.IHM.Menu;
import fr.projet.java.towerdefense.elementDeLaCarte.Carte;
import fr.projet.java.towerdefense.elementDeLaCarte.Tour;
import fr.projet.java.towerdefense.exception.*;

/**
 * @author Romain Une partie de tower defense.
 */
public class PartieDeTowerDefense {

	private IntelligenceArtificiel intelligenceArtificiel;
	private Affichage affichage;
	private Joueur joueur;
	private Carte carte;
	private Chemin cheminEnnemi;

	/**
	 * Une nouvelle partie.
	 * 
	 * @param joueur
	 *            Le joueur.
	 * @param affichage
	 *            L'affichage.
	 */
	public PartieDeTowerDefense(Joueur joueur, Affichage affichage) {
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

	/**
	 * Permet de joueur une partie. Quitte quand le jouer n'as plus de vie.
	 */
	public void jouer() {

		int niveauActuel = 1;
		while (this.joueur.obtenirVie() > 0) {
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
			actionJoueur = joueur.choisirUneAction();
			if (actionJoueur == ActionUtilisateur.finirLeTour)
				break;

			// Le joueur choisi la position a laquelle il souhaite effectuer
			// l'action.
			try {
				position = joueur.choisirUnePosition();

				// On effectue l'action.
				if (actionJoueur == ActionUtilisateur.creerTour) {
					// On verifie si le joueur a l'argent.
					if (joueur.obtenirArgent() >= Tour.PRIX_DE_CONSTRUCTION) {
						tour = new Tour(position);
						carte.placerUneTour(tour);
						try {
							cheminEnnemi = intelligenceArtificiel
									.obtenirUnChemin(carte);
							joueur.modifierArgent(-Tour.PRIX_DE_CONSTRUCTION);
						}
						catch (CheminImpossibleException e) {
							carte.supprimerLaTourLaCarte(tour);
							System.err
									.println("Chemin impossible, action annul�e.");
						}
					}
				}
				if (actionJoueur == ActionUtilisateur.supprimerTour) {
					tour = carte.obtenirLaTourDeCase(position);
					// On rend l'argent au joueur et on supprime la tour.
					joueur.modifierArgent(tour.prixDeDestruction());
					carte.supprimerLaTourLaCarte(tour);
					try {
						cheminEnnemi = intelligenceArtificiel
								.obtenirUnChemin(carte);
					}
					catch (CheminImpossibleException e) {
						carte.supprimerLaTourLaCarte(tour);
						System.err
								.println("Chemin impossible, action annul�e.");
					}
				}
				if (actionJoueur == ActionUtilisateur.ameliorerTour) {
					tour = carte.obtenirLaTourDeCase(position);
					// On verifie si le joueur a l'argent.
					if (joueur.obtenirArgent() >= tour.prixDeLAmelioration()) {
						joueur.modifierArgent(-tour.prixDeLAmelioration());
						tour.augmenterNiveau();

						try {
							cheminEnnemi = intelligenceArtificiel
									.obtenirUnChemin(carte);
						}
						catch (CheminImpossibleException e) {
							carte.supprimerLaTourLaCarte(tour);
							System.err
									.println("Chemin impossible, action annul�e.");
						}
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

			affichage.afficherLeMenu(Menu.NE_PAS_METTRE_A_JOUR,
					carte.obtenirLeNombreDeTours());
		}
	}

	private void lancerUneNouvelleVague(int niveau) {

		Vague vagueCourante = new Vague(niveau);
		int nombreDEnnemiLances = 0;
		carte.mettreAJourLeChemin(cheminEnnemi);

		while (true) {
			try {
				Thread.sleep(400);
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (nombreDEnnemiLances < vagueCourante.obtenirNbEnnemis()) {
				carte.placerUnEnnemi(Carte.POSTION_DEPART,
						vagueCourante.obtenirEnnemi(nombreDEnnemiLances));
				nombreDEnnemiLances++;
			}

			joueur.modifierVie(-carte.avancerEnnemi());
			carte.endommagerLesEnnemis();
			joueur.modifierArgent(carte.verifierVieEnnemi() * 25);
			affichage.afficherLaCarte(carte);
			affichage.afficherLeMenu(niveau, carte.obtenirLeNombreDeTours());
			if (carte.plusDEnnemiSurLaCarte())
				break;
		}

	}

}