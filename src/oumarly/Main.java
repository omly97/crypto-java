package oumarly;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.Security;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JSplitPane;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import oumarly.views.layouts.CardLayoutVue;
import oumarly.views.layouts.Navigation;

public class Main {
	
	private static JSplitPane splitPane;
	private static Navigation navigation;
	private static CardLayoutVue cardLayoutVue;


	public static void main(String[] args) {
		// cryptographic provider
		Security.addProvider(new BouncyCastleProvider());

		// create components
		navigation = new Navigation();
		navigation.setMinimumSize(new Dimension(250, 550));
		cardLayoutVue = new CardLayoutVue();

		// link navigation and cardLayoutVue
		linkButtontoVue(navigation.getWelcomeBtn(), cardLayoutVue.PANEL_WELCOME);
		linkButtontoVue(navigation.getSymBtn(), cardLayoutVue.PANEL_CRYPTO_SYMMETRIC);
		linkButtontoVue(navigation.getAssymBtn(), cardLayoutVue.PANEL_CRYPTO_ASSYMMETRIC);
		linkButtontoVue(navigation.getKeysBtn(), cardLayoutVue.PANEL_KEYS);

		// create split pane
		splitPane = new JSplitPane();
		splitPane.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
		splitPane.setResizeWeight(0);
		splitPane.setDividerSize(5);
		splitPane.setLeftComponent(navigation);
		splitPane.setRightComponent(cardLayoutVue);

		// create frame
		JFrame frame = new JFrame("crypto m2tdsi");
		frame.add(splitPane);
		frame.setLocation(200, 50);
		frame.setSize(1000, 700);
		frame.setMinimumSize(new Dimension(900, 550));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}


	private static void linkButtontoVue(JButton button, String panelName) {
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				cardLayoutVue.showPanel(panelName);
			}
		});
	}
}
