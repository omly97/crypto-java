package oumarly;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;

public class Kit {
	
	public static final Color DARK = new Color(44, 48, 55);
	public static final Color WHITE = new Color(188, 186, 187);
	public static final Color BLUE = new Color(89, 131, 208);
	public static final Color GRAY = new Color(60, 60, 60);


	public static JPanel createDarkPanel() {
		JPanel panel = new JPanel();
		panel.setBackground(DARK);
		return panel;
	}
	
	public static JButton createDarkButton(String text) {
		JButton button = new JButton(text);
		button.setBorderPainted(true);
		button.setFocusPainted(false);
		button.setBackground(DARK);
		button.setForeground(WHITE);
		button.setBorder(new MatteBorder(1, 1, 1, 1, WHITE));
		return button;
	}
}
