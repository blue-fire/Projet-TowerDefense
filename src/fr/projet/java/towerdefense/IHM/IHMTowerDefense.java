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

/**
 * @author Romain L'interfaces graphique.
 */
public class IHMTowerDefense extends JoueurAbstrait implements Affichage,
		Runnable, ActionListener {

	private JSplitPane splitPane;
	private AffichageCarte carte;
	private Menu menu;

	private volatile boolean finDuTour;
	private ActionUtilisateur actionChoisie;

	private Position positionChoisi;
	private volatile boolean nouvellePosition;

	/**
	 * Creer les variable pour le passage d'element.
	 */
	public IHMTowerDefense() {
		super();
		nouvellePosition = false;

		finDuTour = false;

		actionChoisie = null;
		positionChoisi = null;
	}

	@Override
	public ActionUtilisateur choisirUneAction() {

		menu.activerLeMenu();
		
		while (!finDuTour) ;

		finDuTour = false;
		menu.desactiverLeMenu();
		return actionChoisie;
	}

	@Override
	public Position choisirUnePosition() throws AnnulerException {
		menu.desactiverLeMenu();

		while (!nouvellePosition) ;

		if (this.actionChoisie == ActionUtilisateur.finirLeTour) {
			throw new AnnulerException();
		}

		nouvellePosition = false;
		return positionChoisi;

	}

	@Override
	public void afficherLaCarte(Carte carte) {
		this.carte.mettreAJour(carte);
	}

	@Override
	public void afficherLeMenu(int niveauVague, int nombreDeTours) {
		this.menu
				.mettreAJour(this.vie, niveauVague, nombreDeTours, this.argent);
	}

	@Override
	public void actionPerformed(ActionEvent even) {

		JComponent source = (JComponent) even.getSource();

		// Soit la source est un bouton d'action.
		if (source.getName() == "finirLAction") {
			actionChoisie = ActionUtilisateur.finirLeTour;
			finDuTour = true;
		}
		else if (source.getName() == "creer") {
			actionChoisie = ActionUtilisateur.creerTour;
			finDuTour = true;
		}
		else if (source.getName() == "supprimer") {
			actionChoisie = ActionUtilisateur.supprimerTour;
			finDuTour = true;
		}
		else if (source.getName() == "ameliorer") {
			actionChoisie = ActionUtilisateur.ameliorerTour;
			finDuTour = true;
		}

		// Soit c'est un bouton de la carte.
		else {
			positionChoisi = new Position(((BouttonCarte) source).obtenirX(),
					((BouttonCarte) source).obtenirY());
			nouvellePosition = true;
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
