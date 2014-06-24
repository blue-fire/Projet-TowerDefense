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

	private boolean continuerLAction;

	private boolean tourEnAttente;
	private ActionUtilisateur actionChoisie;

	private Position positionChoisi;
	private boolean positionEnAttente;

	/**
	 * Creer les variable pour le passage d'element.
	 */
	public IHMTowerDefense() {
		super();
		continuerLAction = true;

		tourEnAttente = true;
		positionEnAttente = true;

		actionChoisie = null;
		positionChoisi = null;
	}

	@Override
	// TODO Passer finirLeTour en actionUtilisateur.
	public ActionUtilisateur choisirUneAction() throws FinirLeTourException {
		// carte.desactiverLaCarte();

		while (tourEnAttente && continuerLAction) {
			// TODO Supprimer ça en faisant que la boucle marche toujours.
			System.out.println("a");
		}
		if (!continuerLAction) {
			continuerLAction = true;
			throw new FinirLeTourException();
		}

		tourEnAttente = true;
		// carte.activerLaCarte();
		return actionChoisie;
	}

	@Override
	public Position choisirUnePosition() throws AnnulerException {
		menu.desactiverLeMenu();

		while (positionEnAttente && continuerLAction) {
			// TODO RE - supprimer ça en faisant que la boucle marche toujours.
			System.out.println("b");
		}

		if (!continuerLAction) {
			continuerLAction = true;
			menu.activerLeMenu();
			throw new AnnulerException();
		}

		positionEnAttente = true;
		menu.activerLeMenu();
		return positionChoisi;

	}

	@Override
	public void afficherLaCarte(Carte carte) {
		this.carte.mettreAJour(carte);
	}

	@Override
	public void afficherLeMenu(int vies, int niveauVague, int nombreDeTours,
			int nombreDEnnemis) {
		this.menu.mettreAJour(vies, niveauVague, nombreDeTours, nombreDEnnemis);
	}

	@Override
	public void actionPerformed(ActionEvent even) {

		JComponent source = (JComponent) even.getSource();

		// Soit c'est un bouton de la carte.
		if (source instanceof BouttonCarte) {
			positionChoisi = new Position(((BouttonCarte) source).obtenirX(),
					((BouttonCarte) source).obtenirY());
			positionEnAttente = false;

		}
		// Soit un bouton d'action.
		else if (source.getName() == "finirLAction")
			continuerLAction = false;
		else {
			if (source.getName() == "creer")
				actionChoisie = ActionUtilisateur.creerTour;
			else if (source.getName() == "supprimer")
				actionChoisie = ActionUtilisateur.supprimerTour;
			else if (source.getName() == "ameliorer")
				actionChoisie = ActionUtilisateur.ameliorerTour;
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

		menu = new Menu(this);
		carte = new AffichageCarte(this);

		splitPane.setRightComponent(menu);
		splitPane.setLeftComponent(carte);

		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		fenetre.setVisible(true);
	}

}
