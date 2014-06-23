package fr.projet.java.towerdefense.IHM;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

/**
 * @author Romain
 * Un element contenant a droite un element fixe et a gauche un nombre qui peut etre modifier.
 */
public class Grille1x2 extends JPanel {

	private JLabel label;

	/**
	 * Creation de la partie fixe (texte) et de la partie variable vide.
	 * @param string La partie fixe.
	 */
	public Grille1x2(String string) {
		super();

		JSplitPane splitPane = new JSplitPane();
		splitPane.setEnabled(false);
		splitPane.setBorder(null);
		this.add(splitPane);

		// Text de gauche.
		JLabel labelGauche = new JLabel(string);
		labelGauche.setAlignmentX(CENTER_ALIGNMENT);
		labelGauche.setAlignmentY(CENTER_ALIGNMENT);
		splitPane.setLeftComponent(labelGauche);

		// Text de droite qui peut être mis à jour.
		label = new JLabel();
		label.setAlignmentY(CENTER_ALIGNMENT);
		label.setAlignmentX(CENTER_ALIGNMENT);
		splitPane.setRightComponent(label);
	}

	/**
	 * Creation de la partie fixe (image) et de la partie variable vide.
	 * @param imageIcon Image de la partie fixe.
	 */
	public Grille1x2(ImageIcon imageIcon) {
		super();

		JSplitPane splitPane = new JSplitPane();
		splitPane.setEnabled(false);
		splitPane.setBorder(null);
		this.add(splitPane);

		// Image de gauche.
		JLabel labelGauche = new JLabel(imageIcon);
		labelGauche.setAlignmentX(CENTER_ALIGNMENT);
		labelGauche.setAlignmentY(CENTER_ALIGNMENT);
		splitPane.setLeftComponent(labelGauche);
		
		// Text de droite qui peut être mis à jour.
		label = new JLabel();
		label.setAlignmentY(CENTER_ALIGNMENT);
		label.setAlignmentX(CENTER_ALIGNMENT);
		splitPane.setRightComponent(label);
	}

	/**
	 * Met a jour la partie variable.
	 * @param chiffre Le chiffre a afficher.
	 */
	public void mettreAJour(int chiffre) {
		label.setText(Integer.toString(chiffre));
	}
}
