package fr.projet.java.towerdefense.IHM;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

public class Grille1x2 extends JPanel {

	JLabel label;

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

	public void mettreAJour(int chiffre) {
		label.setText(Integer.toString(chiffre));
	}
}
