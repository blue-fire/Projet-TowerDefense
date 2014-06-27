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
		
		this.menu = new Menu(this);
		this.carte = new AffichageCarte(this);
		
		this.nouvellePosition = false;

		this.finDuTour = false;

		this.actionChoisie = null;
		this.positionChoisi = null;
	}

	@Override
	public void actionPerformed(ActionEvent even) {

		JComponent source = (JComponent) even.getSource();

		// Soit la source est un bouton d'action.
		if (source.getName() == "finirLAction") {
			this.actionChoisie = ActionUtilisateur.finirLeTour;
			this.finDuTour = true;
		}
		else if (source.getName() == "creer") {
			this.actionChoisie = ActionUtilisateur.creerTour;
			this.finDuTour = true;
		}
		else if (source.getName() == "supprimer") {
			this.actionChoisie = ActionUtilisateur.supprimerTour;
			this.finDuTour = true;
		}
		else if (source.getName() == "ameliorer") {
			this.actionChoisie = ActionUtilisateur.ameliorerTour;
			this.finDuTour = true;
		}

		// Soit c'est un bouton de la carte.
		else {
			this.positionChoisi = new Position(((BouttonCarte) source).obtenirX(),
					((BouttonCarte) source).obtenirY());
			this.nouvellePosition = true;
		}

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
	public ActionUtilisateur choisirUneAction() {

		this.menu.activerLeMenu();
		
		while (!this.finDuTour) ;

		this.finDuTour = false;
		this.menu.desactiverLeMenu();
		return this.actionChoisie;
	}

	@Override
	public Position choisirUnePosition() throws AnnulerException {

		while (!this.nouvellePosition) ;

		if (this.actionChoisie == ActionUtilisateur.finirLeTour) {
			throw new AnnulerException();
		}

		this.nouvellePosition = false;
		return this.positionChoisi;

	}

	@Override
	public void run() {
		
		JFrame fenetre = new JFrame();
		this.splitPane = new JSplitPane();

		fenetre.setSize(1000, 800);
		fenetre.setContentPane(splitPane);

		this.splitPane.setEnabled(false);

		this.splitPane.setRightComponent(menu);
		this.splitPane.setLeftComponent(carte);

		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		fenetre.setVisible(true);
		
	}

}
