package oumarly.views.layouts;

import java.awt.CardLayout;

import javax.swing.JPanel;

import oumarly.Kit;
import oumarly.views.screens.CryptoAssymetricScreen;
import oumarly.views.screens.CryptoSymmetricScreen;
import oumarly.views.screens.KeysScreen;
import oumarly.views.screens.WelcomeScreen;

@SuppressWarnings("serial")
public class CardLayoutVue extends JPanel {

	private CardLayout cardLayout;
	private WelcomeScreen welcomeScreen;
	private CryptoSymmetricScreen cryptoSymmetricScreen;
	private CryptoAssymetricScreen cryptoAssymetricScreen;
	private KeysScreen keysScreen;
	
	public final String PANEL_WELCOME = "PANEL_WELCOME";
	public final String PANEL_CRYPTO_SYMMETRIC = "PANEL_CRYPTO_SYMMETRIC";
	public final String PANEL_CRYPTO_ASSYMMETRIC = "PANEL_CRYPTO_ASSYMMETRIC";
	public final String PANEL_KEYS = "PANEL_KEYS";


	public CardLayoutVue() {
		welcomeScreen = new WelcomeScreen();
		cryptoSymmetricScreen = new CryptoSymmetricScreen();
		cryptoAssymetricScreen = new CryptoAssymetricScreen();
		keysScreen = new KeysScreen();

		cardLayout = new CardLayout();
		setLayout(cardLayout);
		add(PANEL_WELCOME, welcomeScreen);
		add(PANEL_CRYPTO_SYMMETRIC, cryptoSymmetricScreen);
		add(PANEL_CRYPTO_ASSYMMETRIC, cryptoAssymetricScreen);
		add(PANEL_KEYS, keysScreen);
		setBackground(Kit.DARK);
	}


	/**
	 * Show panel that name is passed as argument
	 * @param panelName is PANEL_WELCOME or PANEL_CRYPTO_SYMMETRIC or PANEL_CRYPTO_ASSYMMETRIC or PANEL_KEYS
	 */
	public void showPanel(String panelName) {
		cardLayout.show(this, panelName);
	}
}
