package oumarly.views.layouts;

import java.awt.CardLayout;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import oumarly.Kit;
import oumarly.views.screens.AssymetricCrypto;
import oumarly.views.screens.AssymetricKey;
import oumarly.views.screens.CreateKey;
import oumarly.views.screens.SymmetricCrypto;
import oumarly.views.screens.SymmetricKey;
import oumarly.views.screens.Welcome;

@SuppressWarnings("serial")
public class Router extends JPanel {

	private CardLayout cardLayout;
	private Welcome welcomeScreen;
	private SymmetricCrypto symmetricCrypto;
	private SymmetricKey symmetricKey;
	private AssymetricCrypto assymetricCrypto;
	private AssymetricKey assymetricKey;
	private CreateKey createKey;
	
	private final String WELCOME = "WELCOME";
	private final String SYMMETRIC_CRYPTO = "SYMMETRIC_CRYPTO";
	private final String SYMMETRIC_KEY = "SYMMETRIC_KEY";
	private final String ASYMMETRIC_CRYPTO = "ASSYMMETRIC_CRYPTO";
	private final String ASYMMETRIC_KEY = "CREATE_KEY";
	private final String CREATE_KEY = "ASSYMMETRIC_KEY";

	public Router() {
		welcomeScreen = new Welcome();
		symmetricCrypto = new SymmetricCrypto();
		symmetricKey = new SymmetricKey();
		assymetricCrypto = new AssymetricCrypto();
		assymetricKey = new AssymetricKey();
		createKey = new CreateKey();

		cardLayout = new CardLayout();
		setLayout(cardLayout);

		add(WELCOME, welcomeScreen);
		add(SYMMETRIC_CRYPTO, symmetricCrypto);
		add(SYMMETRIC_KEY, symmetricKey);
		add(ASYMMETRIC_CRYPTO, assymetricCrypto);
		add(ASYMMETRIC_KEY, assymetricKey);
		add(CREATE_KEY, createKey);
		setBackground(Kit.getAppBackground());
	}
	
	public void showWelcome() {
		cardLayout.show(this, WELCOME);
	}
	
	public void showSymmetricCrypto() {
		try {
			symmetricCrypto.init();
			cardLayout.show(this, SYMMETRIC_CRYPTO);
		} catch (SQLException e) {
			dialogError(e);
		}
	}
	
	public void showSymmetricKey() {
		try {
			symmetricKey.fetchSecretKeys();
			cardLayout.show(this, SYMMETRIC_KEY);
		} catch (SQLException e) {
			dialogError(e);
		}
	}
	
	public void showAsymmetricCrypto() {
		try {
			assymetricCrypto.init();
			cardLayout.show(this, ASYMMETRIC_CRYPTO);
		} catch (SQLException e) {
			dialogError(e);
		}
	}
	
	public void showAsymmetricKey() {
		try {
			assymetricKey.fetchKeyPairs();
			cardLayout.show(this, ASYMMETRIC_KEY);
		} catch (Exception e) {
			dialogError(e);
		}
	}
	
	public void showCreateKey() {
		createKey.init();
		cardLayout.show(this, CREATE_KEY);
	}
	
	private void dialogError(Exception e) {
		JOptionPane.showMessageDialog(this, e.getMessage(), "Erreur", JOptionPane.WARNING_MESSAGE);
	}
}
