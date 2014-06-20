package fr.projet.java.towerdefense.IHM;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JSplitPane;

import fr.projet.java.towerdefense.ActionUtilisateur;
import fr.projet.java.towerdefense.Affichage;
import fr.projet.java.towerdefense.Joueur;
import fr.projet.java.towerdefense.Position;
import fr.projet.java.towerdefense.elementDeLaCarte.Carte;
import fr.projet.java.towerdefense.exception.AnnulerException;
import fr.projet.java.towerdefense.exception.FinirLeTourException;

public class IHMTowerDefense implements Affichage, Joueur, Runnable,
		ActionListener {

	private JSplitPane splitPane;
	private AffichageCarte carte;
	private Menu menu;

	//TODO Changer le nom de la variable.
	private boolean continuerLAction;

	private boolean tourEnAttente;
	private ActionUtilisateur actionChoisie;

	private Position positionChoisi;
	private boolean positionEnAttente;

	public IHMTowerDefense() {
		continuerLAction = true;

		tourEnAttente = true;
		positionEnAttente = true;
		
		actionChoisie = null;
		positionChoisi = null;
	}

	@Override
	public ActionUtilisateur choisirUneAction() throws FinirLeTourException {
		//carte.desactiverLaCarte();
		
		while (tourEnAttente && continuerLAction) {
			// TODO Supprimer ça en faisant que la boucle marche toujours.
			System.out.println("a");

		}
		if (!continuerLAction) {
			continuerLAction = true;
			throw new FinirLeTourException();
		}
		
		tourEnAttente = true;
		//carte.activerLaCarte();
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

		if (source instanceof BouttonCarte) {
			positionChoisi = new Position(
					((BouttonCarte) source).obtenirX(),
					((BouttonCarte) source).obtenirY());
			positionEnAttente = false;

		}
		else if (source.getName() == "finirLAction")
			continuerLAction = false;
		else {
			if (source.getName() == "creer") actionChoisie = ActionUtilisateur.creerTour;
			else if (source.getName() == "supprimer") actionChoisie = ActionUtilisateur.supprimerTour;
			else if (source.getName() == "ameliorer") actionChoisie = ActionUtilisateur.ameliorerTour;
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
