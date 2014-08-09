package fr.projet.java.towerdefense.IHM;

import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * @author Romain Affiche le menu.
 */
public class Menu extends JPanel {

	/**
	 * Une constante qui permet d'empecher la mise a jour d'un element.
	 */
	public static final int NE_PAS_METTRE_A_JOUR = -1;

	private JButton boutonFinAction;
	private BoutonTour boutonsDesTours;

	private Grille1x2 tours;
	private Grille1x2 argent;
	private Grille1x2 vague;
	private Grille1x2 vie;

	/**
	 * Construit le menu
	 * 
	 * @param lis
	 *            l'ecouteur de bouton du menu.
	 */
	public Menu(ActionListener lis) {
		super();

		GridLayout gridLayout = new GridLayout(6, 1);
		this.setLayout(gridLayout);

		boutonsDesTours = new BoutonTour(lis);
		this.add(boutonsDesTours);

		tours = new Grille1x2("Tours : ");
		this.add(tours);

		vague = new Grille1x2("Vague : ");
		this.add(vague);
		
		argent = new Grille1x2("Argent : ");
		this.add(argent);

		vie = new Grille1x2(new ImageIcon(getClass().getResource("/ressources/images/coeur.png")));
		this.add(vie);

		boutonFinAction = new JButton("Lancer une nouvelle vague !");
		boutonFinAction.setName("finirLAction");
		boutonFinAction.addActionListener(lis);
		boutonFinAction.setSize(10, 10);
		this.add(boutonFinAction);

	}

	/**
	 * Met a jour le menu.
	 * 
	 * @param vieJoueur
	 *            les vies du joueur.
	 * @param niveauVague
	 *            Le niveau de la vague.
	 * @param nombreDeTours
	 *            Le nombre de tour du joueur.
	 * @param argentJoueur
	 *            Le nombre d'ennemi sur la carte.
	 */
	public void mettreAJour(int vieJoueur, int niveauVague, int nombreDeTours,
			int argentJoueur) {

		if (nombreDeTours != NE_PAS_METTRE_A_JOUR)
			this.tours.mettreAJour(nombreDeTours);
		if (argentJoueur != NE_PAS_METTRE_A_JOUR)
			this.argent.mettreAJour(argentJoueur);
		if (niveauVague != NE_PAS_METTRE_A_JOUR)
			this.vague.mettreAJour(niveauVague);
		if (vieJoueur >= 0)
			this.vie.mettreAJour(vieJoueur);

	}

	/**
	 * Desactive les boutons du menu.
	 */
	public void desactiverLeMenu() {
		boutonFinAction.setText("Annuler l'action");
		boutonsDesTours.desactiver();
	}

	/**
	 * Active les boutons du menu.
	 */
	public void activerLeMenu() {
		boutonFinAction.setText("Lancer une nouvelle vague !");
		boutonsDesTours.activer();
	}
}
