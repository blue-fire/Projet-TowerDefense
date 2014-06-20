package fr.projet.java.towerdefense.IHM;

import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class BoutonTour extends JPanel {

	JButton boutonCreer;
	JButton boutonSupprimer;
	JButton boutonAmeliore;
	
	public BoutonTour(ActionListener lis) {
		super();
		this.setLayout(new GridLayout(1,3));
		
		boutonCreer = new JButton();
		boutonCreer.setName("creer");
		boutonCreer.setIcon(new ImageIcon("ressources/images/tour_faible.png"));
		boutonCreer.addActionListener(lis);
		this.add(boutonCreer);
		
		boutonSupprimer = new JButton();
		boutonSupprimer.setName("supprimer");
		boutonSupprimer.setIcon(new ImageIcon("ressources/images/tour_suppression.png"));
		boutonSupprimer.addActionListener(lis);
		this.add(boutonSupprimer);
		
		boutonAmeliore = new JButton();
		boutonAmeliore.setName("ameliorer");
		boutonAmeliore.setIcon(new ImageIcon("ressources/images/tour_amelioration.png"));
		boutonAmeliore.addActionListener(lis);
		this.add(boutonAmeliore);
	}

	public void desactiver() {
		boutonAmeliore.setEnabled(false);
		boutonSupprimer.setEnabled(false);
		boutonCreer.setEnabled(false);
	}
	
	public void activer() {
		boutonAmeliore.setEnabled(true);
		boutonSupprimer.setEnabled(true);
		boutonCreer.setEnabled(true);
	}
}
