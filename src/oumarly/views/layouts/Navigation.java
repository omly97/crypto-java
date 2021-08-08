package oumarly.views.layouts;

import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;

import oumarly.Kit;

@SuppressWarnings("serial")
public class Navigation extends JPanel {
	
	private JButton welcomeBtn;
	private JButton symBtn;
	private JButton assymBtn;
	private JButton keysBtn;

	private Vector<JButton> veButtons;


	public Navigation() {
		super();
		setBackground(Kit.DARK);

		welcomeBtn = createMenuButton("Accueil");
		symBtn = createMenuButton("Cryptographie symetrique");
		assymBtn = createMenuButton("Cryptographie asymetrique");
		keysBtn = createMenuButton("Gestion de cles");
		
		setLayout(new GridLayout(4, 1));
		add(welcomeBtn);
		add(symBtn);
		add(assymBtn);
		add(keysBtn);
		
		veButtons = new Vector<JButton>();
		veButtons.add(welcomeBtn);
		veButtons.add(symBtn);
		veButtons.add(assymBtn);
		veButtons.add(keysBtn);
	}


	private JButton createMenuButton(String text) {
		JButton b = Kit.createDarkButton(text);
		b.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				for (JButton btn : veButtons) {
					btn.setBackground(Kit.DARK);
					btn.setBorder(new MatteBorder(1, 1, 1, 1, Kit.WHITE));
				}
				b.setBackground(Kit.GRAY);
				b.setBorder(new MatteBorder(0, 7, 0, 0, Kit.BLUE));
			}
		});
		return b;
	}


	public JButton getWelcomeBtn() {
		return welcomeBtn;
	}


	public JButton getSymBtn() {
		return symBtn;
	}


	public JButton getAssymBtn() {
		return assymBtn;
	}


	public JButton getKeysBtn() {
		return keysBtn;
	}
	
}
