package fr.projet.java.towerdefense.IHM;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class BouttonTour extends JPanel {
	
	public BouttonTour(ActionListener lis) {
	super();
	
	GridLayout gridLayout = new GridLayout(3,1);
	JButton bouton = new JButton();
	bouton.addActionListener(lis);
	bouton.setOpaque(true);
	bouton.setBackground(Color.BLUE);
	this.add(bouton);
	
	bouton = new JButton();
	bouton.addActionListener(lis);
	bouton.setOpaque(true);
	bouton.setBackground(Color.RED);
	this.add(bouton);
	
	bouton = new JButton();
	bouton.setName("finirTour");
	bouton.setText("Fin Tour");
	bouton.addActionListener(lis);
	bouton.setOpaque(true);
	bouton.setBackground(Color.GREEN);
	this.add(bouton);
	
	this.setLayout(gridLayout);
	}
}
