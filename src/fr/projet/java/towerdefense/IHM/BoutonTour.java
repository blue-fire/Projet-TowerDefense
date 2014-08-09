package fr.projet.java.towerdefense.IHM;

import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * @author Romain Les boutons permettant de creer supprimer et ameliorer une
 *         tour.
 */
public class BoutonTour extends JPanel {

	private JButton boutonCreer;
	private JButton boutonSupprimer;
	private JButton boutonAmeliore;

	/**
	 * Creation des boutons.
	 * 
	 * @param lis
	 *            L'ecouteur de bouton.
	 */
	public BoutonTour(ActionListener lis) {
		super();
		this.setLayout(new GridLayout(1, 3));

		boutonCreer = new JButton();
		boutonCreer.setName("creer");
		boutonCreer.setIcon(new ImageIcon(getClass().getResource(
				"/ressources/images/bouton_construction.png")));
		boutonCreer.addActionListener(lis);
		this.add(boutonCreer);

		boutonSupprimer = new JButton();
		boutonSupprimer.setName("supprimer");
		boutonSupprimer.setIcon(new ImageIcon(getClass().getResource(
				"/ressources/images/bouton_suppression.png")));
		boutonSupprimer.addActionListener(lis);
		this.add(boutonSupprimer);

		boutonAmeliore = new JButton();
		boutonAmeliore.setName("ameliorer");
		boutonAmeliore.setIcon(new ImageIcon(getClass().getResource(
				"/ressources/images/bouton_amelioration.png")));
		boutonAmeliore.addActionListener(lis);
		this.add(boutonAmeliore);
	}

	/**
	 * desactive les boutons du menu.
	 */
	public void desactiver() {
		boutonAmeliore.setEnabled(false);
		boutonSupprimer.setEnabled(false);
		boutonCreer.setEnabled(false);
	}

	/**
	 * active les boutons du menu.
	 */
	public void activer() {
		boutonAmeliore.setEnabled(true);
		boutonSupprimer.setEnabled(true);
		boutonCreer.setEnabled(true);
	}
}
