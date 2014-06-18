package fr.projet.java.towerdefense.IHM;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JSplitPane;

import fr.projet.java.towerdefense.Affichage;
import fr.projet.java.towerdefense.Joueur;
import fr.projet.java.towerdefense.Position;
import fr.projet.java.towerdefense.elementDeLaCarte.Carte;
import fr.projet.java.towerdefense.elementDeLaCarte.Tour;
import fr.projet.java.towerdefense.exception.AnnulerException;
import fr.projet.java.towerdefense.exception.FinirLeTourException;

public class IHMTowerDefense implements Affichage, Joueur, Runnable,
		ActionListener {

	private JSplitPane splitPane;
	private AffichageCarte carte;
	private BouttonTour menu;

	private boolean leTourEstPasFini;

	private boolean tourEnAttente;
	private Tour tourChoisi;

	private Position positionChoisi;
	private boolean positionEnAttente;

	public IHMTowerDefense() {
		leTourEstPasFini = true;

		tourEnAttente = true;
		positionEnAttente = true;
		
		tourChoisi = null;
		positionChoisi = null;
	}

	@Override
	public Tour choisirUneTour() throws FinirLeTourException {
		while (tourEnAttente && leTourEstPasFini) {
			// TODO Supprimer ça en faisant que la boucle marche toujours.
			System.out.println("a");

		}
		;
		if (!leTourEstPasFini) {
			leTourEstPasFini = true;
			throw new FinirLeTourException();
		}
		tourEnAttente = true;
		return tourChoisi;
	}

	@Override
	public Position choisirUnePosition() throws AnnulerException {
		while (positionEnAttente) {
			// TODO RE - supprimer ça en faisant que la boucle marche toujours.
			System.out.println("b");
		}
		;
		positionEnAttente = true;
		return positionChoisi;
	}

	@Override
	public void afficherLaCarte(Carte carte) {
		//this.splitPane.setLeftComponent(new AffichageCarte(carte, this));
		this.carte.mettreAJour(carte);
	}

	@Override
	public void actionPerformed(ActionEvent even) {

		JComponent source = (JComponent) even.getSource();

		if (source instanceof BouttonCarte) {
			positionChoisi = new Position(
					((BouttonCarte) source).obtenirX(),
					((BouttonCarte) source).obtenirY());
			positionEnAttente = false;

		}
		else if (source.getName() == "finirTour")
			leTourEstPasFini = false;
		else {
			tourChoisi = new Tour();
			tourEnAttente = false;
		}

	}

	@Override
	public void run() {
		JFrame fenetre = new JFrame();
		splitPane = new JSplitPane();

		fenetre.setSize(1000, 800);
		fenetre.setContentPane(splitPane);

		splitPane.setEnabled(false);

		menu = new BouttonTour(this);
		carte = new AffichageCarte(this);

		splitPane.setRightComponent(menu);
		splitPane.setLeftComponent(carte);

		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		fenetre.setVisible(true);
	}
}
