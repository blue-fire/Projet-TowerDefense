package fr.projet.java.towerdefense.IHM;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JSplitPane;

import fr.projet.java.towerdefense.ActionUtilisateur;
import fr.projet.java.towerdefense.Affichage;
import fr.projet.java.towerdefense.JoueurAbstrait;
import fr.projet.java.towerdefense.Position;
import fr.projet.java.towerdefense.elementDeLaCarte.Carte;
import fr.projet.java.towerdefense.exception.AnnulerException;
import fr.projet.java.towerdefense.exception.FinirLeTourException;

/**
 * @author Romain L'interfaces graphique.
 */
public class IHMTowerDefense extends JoueurAbstrait implements Affichage,
		Runnable, ActionListener {

	private JSplitPane splitPane;
	private AffichageCarte carte;
	private Menu menu;

	private boolean nouvelleAction;

	private boolean finDuTour;
	private ActionUtilisateur actionChoisie;

	private Position positionChoisi;
	private boolean nouvellePosition;

	/**
	 * Creer les variable pour le passage d'element.
	 */
	public IHMTowerDefense() {
		super();
		nouvelleAction = false;
		nouvellePosition = false;

		finDuTour = false;

		actionChoisie = null;
		positionChoisi = null;
	}

	@Override
	// TODO Passer finirLeTour en actionUtilisateur.
	public ActionUtilisateur choisirUneAction() throws FinirLeTourException {
		// carte.desactiverLaCarte();

		while (!finDuTour && !nouvelleAction) {
			// TODO Supprimer ça en faisant que la boucle marche toujours.
			System.out.println("a");
		}
		if (nouvelleAction) {
			nouvelleAction = false;
			throw new FinirLeTourException();
		}

		finDuTour = false;
		// carte.activerLaCarte();
		return actionChoisie;
	}

	@Override
	public Position choisirUnePosition() throws AnnulerException {
		menu.desactiverLeMenu();

		while (!nouvellePosition && !nouvelleAction) {
			// TODO RE - supprimer ça en faisant que la boucle marche toujours.
			System.out.println("b");
		}

		if (nouvelleAction) {
			nouvelleAction = false;
			menu.activerLeMenu();
			throw new AnnulerException();
		}

		nouvellePosition = false;
		menu.activerLeMenu();
		return positionChoisi;

	}

	@Override
	public void afficherLaCarte(Carte carte) {
		this.carte.mettreAJour(carte);
	}

	@Override
	public void afficherLeMenu(int niveauVague, int nombreDeTours) {
		this.menu.mettreAJour(this.vie, niveauVague, nombreDeTours, this.argent);
	}

	@Override
	public void actionPerformed(ActionEvent even) {

		JComponent source = (JComponent) even.getSource();

		// Soit c'est un bouton de la carte.
		if (source instanceof BouttonCarte) {
			positionChoisi = new Position(((BouttonCarte) source).obtenirX(),
					((BouttonCarte) source).obtenirY());
			nouvellePosition = true;

		}
		// Soit un bouton d'action.
		else if (source.getName() == "finirLAction")
			nouvelleAction = true;
		else {
			if (source.getName() == "creer")
				actionChoisie = ActionUtilisateur.creerTour;
			else if (source.getName() == "supprimer")
				actionChoisie = ActionUtilisateur.supprimerTour;
			else if (source.getName() == "ameliorer")
				actionChoisie = ActionUtilisateur.ameliorerTour;
			finDuTour = true;
		}

	}

	@Override
	public void run() {
		JFrame fenetre = new JFrame();
		splitPane = new JSplitPane();

		fenetre.setSize(1000, 800);
		fenetre.setContentPane(splitPane);

		splitPane.setEnabled(false);

		menu = new Menu(this);
		carte = new AffichageCarte(this);

		splitPane.setRightComponent(menu);
		splitPane.setLeftComponent(carte);

		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		fenetre.setVisible(true);
	}

}
