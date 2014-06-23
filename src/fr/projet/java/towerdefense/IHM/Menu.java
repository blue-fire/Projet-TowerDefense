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
	private Grille1x2 ennemis;
	private Grille1x2 vague;
	private Grille1x2 vies;

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

		ennemis = new Grille1x2("Ennemis : ");
		this.add(ennemis);

		vague = new Grille1x2("Vague : ");
		this.add(vague);

		vies = new Grille1x2(new ImageIcon("ressources/images/coeur.png"));
		this.add(vies);

		boutonFinAction = new JButton("Lancer une nouvelle vague !");
		boutonFinAction.setName("finirLAction");
		boutonFinAction.addActionListener(lis);
		boutonFinAction.setSize(10, 10);
		this.add(boutonFinAction);

	}

	/**
	 * Met a jour le menu.
	 * 
	 * @param vies
	 *            les vies du joueur.
	 * @param niveauVague
	 *            Le niveau de la vague.
	 * @param nombreDeTours
	 *            Le nombre de tour du joueur.
	 * @param nombreDEnnemis
	 *            Le nombre d'ennemi sur la carte.
	 */
	public void mettreAJour(int vies, int niveauVague, int nombreDeTours,
			int nombreDEnnemis) {

		if (nombreDeTours != NE_PAS_METTRE_A_JOUR)
			this.tours.mettreAJour(nombreDeTours);
		if (nombreDEnnemis != NE_PAS_METTRE_A_JOUR)
			this.ennemis.mettreAJour(nombreDEnnemis);
		if (niveauVague != NE_PAS_METTRE_A_JOUR)
			this.vague.mettreAJour(niveauVague);
		if (vies >= 0)
			this.vies.mettreAJour(vies);

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
