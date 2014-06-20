package fr.projet.java.towerdefense.IHM;

import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Menu extends JPanel {

	public static final int NE_PAS_METTRE_A_JOUR = -1;
	
	JButton boutonFinAction;
	BoutonTour boutonsDesTours;
	
	Grille1x2 tours;
	Grille1x2 ennemis;
	Grille1x2 vague;
	Grille1x2 vies;
	
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

	public void mettreAJour(int vies, int niveauVague, int nombreDeTours, int nombreDEnnemis) {

		if (nombreDeTours != NE_PAS_METTRE_A_JOUR) this.tours.mettreAJour( nombreDeTours );
		if (nombreDEnnemis != NE_PAS_METTRE_A_JOUR) this.ennemis.mettreAJour( nombreDEnnemis );
		if (niveauVague != NE_PAS_METTRE_A_JOUR) this.vague.mettreAJour( niveauVague );
		if (vies >= 0) this.vies.mettreAJour( vies );

	}
	
	public void desactiverLeMenu() {
		boutonFinAction.setText("Annuler l'action");
		boutonsDesTours.desactiver();
	}
	
	public void activerLeMenu() {
		boutonFinAction.setText("Lancer une nouvelle vague !");
		boutonsDesTours.activer();
	}
}
