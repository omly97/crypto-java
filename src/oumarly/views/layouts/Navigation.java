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
	private JButton symCryptoBtn;
	private JButton symKeyBtn;
	private JButton assymCryptoBtn;
	private JButton assymKeyBtn;
	private JButton createKeyBtn;

	private Vector<JButton> veButtons;


	public Navigation() {
		super();

		welcomeBtn = createMenuButton("Accueil");
		symCryptoBtn = createMenuButton("Cryptographie symetrique");
		symKeyBtn = createMenuButton("Cle symetrique");
		assymCryptoBtn = createMenuButton("Cryptographie asymetrique");
		assymKeyBtn = createMenuButton("Cle asymetrique");
		createKeyBtn = createMenuButton("Creation de cles");
		
		setLayout(new GridLayout(6, 1));
		add(welcomeBtn);
		add(symCryptoBtn);
		add(assymCryptoBtn);
		add(createKeyBtn);
		add(symKeyBtn);
		add(assymKeyBtn);
		setBackground(Kit.XDARK);
		
		veButtons = new Vector<JButton>();
		veButtons.add(welcomeBtn);
		veButtons.add(symCryptoBtn);
		veButtons.add(assymCryptoBtn);
		veButtons.add(symKeyBtn);
		veButtons.add(assymKeyBtn);
		veButtons.add(createKeyBtn);
	}

	public JButton getWelcomeBtn() {
		return welcomeBtn;
	}

	public JButton getSymCryptoBtn() {
		return symCryptoBtn;
	}

	public JButton getSymKeyBtn() {
		return symKeyBtn;
	}

	public JButton getAssymCryptoBtn() {
		return assymCryptoBtn;
	}

	public JButton getAssymKeyBtn() {
		return assymKeyBtn;
	}
	
	public JButton getCreateKeyBtn() {
		return createKeyBtn;
	}

	private JButton createMenuButton(String text) {
		JButton b = new JButton(text);
		b.setBorderPainted(true);
		b.setFocusPainted(false);
		b.setBackground(Kit.XDARK);
		b.setForeground(Kit.XWHITE);
		b.setBorder(new MatteBorder(1, 1, 1, 1, Kit.XWHITE));
		b.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				for (JButton btn : veButtons) {
					btn.setBackground(Kit.XDARK);
					btn.setBorder(new MatteBorder(1, 1, 1, 1, Kit.XDARK));
				}
				b.setBackground(Kit.XGRAY);
				b.setBorder(new MatteBorder(0, 7, 0, 0, Kit.XBLUE));
			}
		});
		return b;
	}
}
