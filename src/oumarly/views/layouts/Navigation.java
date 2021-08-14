package oumarly.views.layouts;

import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
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
		symCryptoBtn = createMenuButton("Cryptographie symétrique");
		symKeyBtn = createMenuButton("Clé secrètes");
		assymCryptoBtn = createMenuButton("Cryptographie asymétrique");
		assymKeyBtn = createMenuButton("Clés publiques");
		createKeyBtn = createMenuButton("Création de clés");
		
		setLayout(new GridLayout(6, 1));
		add(welcomeBtn);
		add(symCryptoBtn);
		add(assymCryptoBtn);
		add(createKeyBtn);
		add(symKeyBtn);
		add(assymKeyBtn);
		setBackground(Kit.NAV_BG);
		setBorder(new EmptyBorder(0, 0, 180, 0));
		
		selectMenu(welcomeBtn);
		
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
	
	private void selectMenu(JButton b) {
		b.setBackground(Kit.NAV_MENU_ACTIVE);
		b.setBorder(new CompoundBorder(new MatteBorder(0, 3, 0, 0, Kit.NAV_FG), new EmptyBorder(0, 25, 0, 25)));
	}
	
	private void unselectMenu(JButton b) {
		b.setBackground(Kit.NAV_BG);
		b.setBorder(new CompoundBorder(new MatteBorder(1, 1, 1, 1, Kit.NAV_BG), new EmptyBorder(0, 25, 0, 25)));
	}

	private JButton createMenuButton(String text) {
		JButton b = new JButton(text);
		b.setBorderPainted(true);
		b.setFocusPainted(false);
		b.setBackground(Kit.NAV_BG);
		b.setForeground(Kit.NAV_FG);
		b.setBorder(new CompoundBorder(new MatteBorder(1, 1, 1, 1, Kit.NAV_BG), new EmptyBorder(0, 25, 0, 25)));
		b.setHorizontalAlignment(SwingConstants.LEFT);
		b.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				for (JButton btn : veButtons) unselectMenu(btn);
				selectMenu(b);
			}
		});
		return b;
	}
}
