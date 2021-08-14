package oumarly.views.screens;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import oumarly.Kit;

@SuppressWarnings("serial")
public class Welcome extends JPanel {
	
	private JPanel panelNord;
	private JPanel panelCentre;
	private JLabel labelTitre;

	public Welcome() {
		super();
		
		// create
		panelNord = new JPanel();
		panelCentre = new JPanel();
		labelTitre = new JLabel("Crypto mtdsi 2020-21");
		
		// position
		panelNord.add(labelTitre);
		
		// Design JPanel
		panelNord.setOpaque(false);
		panelCentre.setOpaque(false);
		
		// Design UI
		Kit.makeWelcomeMessage(panelNord, labelTitre);
		
		// this
		setOpaque(false);
		setLayout(new BorderLayout());
		add(panelNord, BorderLayout.NORTH);
		add(panelCentre, BorderLayout.CENTER);
		setBorder(new EmptyBorder(80, 100, 80, 100));
	}
}
