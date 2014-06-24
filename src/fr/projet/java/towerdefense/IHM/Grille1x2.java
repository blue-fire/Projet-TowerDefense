package fr.projet.java.towerdefense.IHM;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.SwingConstants;

/**
 * @author Romain Un element contenant a gauche un element fixe et a droite un
 *         nombre qui peut etre modifier.
 */
public class Grille1x2 extends JPanel {

	private JLabel label;

	/**
	 * Creation de la partie fixe (texte) et de la partie variable vide.
	 * 
	 * @param string
	 *            La partie fixe.
	 */
	public Grille1x2(String string) {
		super();

		JSplitPane splitPane = new JSplitPane();
		splitPane.setEnabled(false);
		splitPane.setBorder(null);
		splitPane.setDividerSize(0);
		this.add(splitPane);

		// Text de gauche.
		JLabel labelGauche = new JLabel(string);
		labelGauche.setVerticalAlignment(SwingConstants.CENTER);
		splitPane.setLeftComponent(labelGauche);

		// Text de droite qui peut être mis à jour.
		label = new JLabel();
		label.setVerticalAlignment(SwingConstants.CENTER);
		splitPane.setRightComponent(label);
	}

	/**
	 * Creation de la partie fixe (image) et de la partie variable vide.
	 * 
	 * @param imageIcon
	 *            Image de la partie fixe.
	 */
	public Grille1x2(ImageIcon imageIcon) {
		super();

		JSplitPane splitPane = new JSplitPane();
		splitPane.setEnabled(false);
		splitPane.setBorder(null);
		splitPane.setDividerSize(0);
		this.add(splitPane);

		// Image de gauche.
		JLabel labelGauche = new JLabel(imageIcon);
		labelGauche.setVerticalAlignment(SwingConstants.CENTER);
		labelGauche.setHorizontalAlignment(SwingConstants.CENTER);
		splitPane.setLeftComponent(labelGauche);

		// Text de droite qui peut être mis à jour.
		label = new JLabel();
		label.setVerticalAlignment(SwingConstants.CENTER);
		splitPane.setRightComponent(label);
	}

	/**
	 * Met a jour la partie variable.
	 * 
	 * @param chiffre
	 *            Le chiffre a afficher.
	 */
	public void mettreAJour(int chiffre) {
		label.setText(Integer.toString(chiffre));
	}
}
