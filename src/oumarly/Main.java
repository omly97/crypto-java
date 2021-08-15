package oumarly;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.Security;

import javax.swing.JFrame;
import javax.swing.JSplitPane;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import oumarly.views.layouts.Navigation;
import oumarly.views.layouts.Router;

public class Main {
	
	private static JSplitPane splitPane;
	private static Navigation navigation;
	private static Router router;


	public static void main(String[] args) {
		// cryptographic provider
		Security.addProvider(new BouncyCastleProvider());

		// create components
		navigation = new Navigation();
		navigation.setMinimumSize(new Dimension(250, 550));
		router = new Router();
		
		
		// link navigation to router
		navigation.getWelcomeBtn().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				router.showWelcome();
			}
		});

		navigation.getSymCryptoBtn().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				router.showSymmetricCrypto();
			}
		});

		navigation.getAssymCryptoBtn().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				router.showAsymmetricCrypto();;
			}
		});

		navigation.getSymKeyBtn().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				router.showSymmetricKey();
			}
		});

		navigation.getAssymKeyBtn().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				router.showAsymmetricKey();
			}
		});
		
		navigation.getCreateKeyBtn().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				router.showCreateKey();
			}
		});

		// create split pane
		splitPane = new JSplitPane();
		splitPane.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
		splitPane.setResizeWeight(0);
		splitPane.setDividerSize(5);
		splitPane.setLeftComponent(navigation);
		splitPane.setRightComponent(router);

		// create frame
		JFrame frame = new JFrame("crypto m2tdsi");
		frame.add(splitPane);
		frame.setLocation(200, 50);
		frame.setSize(900, 700);
		frame.setMinimumSize(new Dimension(900, 650));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
