package oumarly.views.screens;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.KeyPair;
import java.sql.SQLException;

import javax.crypto.SecretKey;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import oumarly.Kit;
import oumarly.app.crypto.MyKey;
import oumarly.app.dao.KeyPairDao;
import oumarly.app.dao.SecretKeyDao;
import oumarly.app.model.KeyPairModel;
import oumarly.app.model.SecretKeyModel;

@SuppressWarnings("serial")
public class CreateKey extends JPanel implements ActionListener {

	private JPanel panelNord;
	private JPanel panelCentre;
	private JPanel panelSud;
	private JPanel panelTitre;
	private JPanel panelAlerte;
	private JLabel labelTitre;
	private JLabel labelAlert;
	private JLabel labelNom;
	private JLabel labelAlgo;
	private JLabel labelProvider;
	private JLabel labelTaille;
	private JTextField txtNom;
	private JTextField txtAlgo;
	private JTextField txtProvider;
	private JTextField txtTaille;
	private JButton bSymmetric;
	private JButton bAsymmetric;
	
	private SecretKeyDao secretKeyDao;
	private KeyPairDao keyPairDao;
	
	public CreateKey() {
		super();
		secretKeyDao = new SecretKeyDao();
		keyPairDao = new KeyPairDao();
		
		// create
		panelNord = new JPanel();
		panelCentre = new JPanel();
		panelSud = new JPanel();
		panelTitre = new JPanel();
		panelAlerte = new JPanel();
		labelTitre = new JLabel("Création de clés cryptos");
		labelAlert = new JLabel("Alerte Information");
		labelNom = new JLabel("Nom de votre clé (juste pour nommer)");
		labelAlgo = new JLabel("Algorithme ou transformation");
		labelProvider = new JLabel("Provider (optionnel)");
		labelTaille = new JLabel("Taille de la clé");
		txtNom = new JTextField();
		txtAlgo = new JTextField();
		txtProvider = new JTextField();
		txtTaille = new JTextField();
		bSymmetric = new JButton("Créer cle secrete");
		bAsymmetric = new JButton("Créer paire de clés");
		
		// position
		panelTitre.add(labelTitre);
		panelAlerte.add(labelAlert);

		panelNord.setLayout(new GridLayout(2, 1));
		panelNord.add(panelTitre);
		panelNord.add(panelAlerte);
		panelNord.setBorder(new EmptyBorder(0, 0, 20, 0));

		panelCentre.setLayout(new GridLayout(8, 1, 0, 5));
		panelCentre.add(labelNom);
		panelCentre.add(txtNom);
		panelCentre.add(labelAlgo);
		panelCentre.add(txtAlgo);
		panelCentre.add(labelProvider);
		panelCentre.add(txtProvider);
		panelCentre.add(labelTaille);
		panelCentre.add(txtTaille);

		panelSud.setLayout(new GridLayout(1, 2, 50, 0));
		panelSud.add(bSymmetric);
		panelSud.add(bAsymmetric);
		panelSud.setBorder(new EmptyBorder(30, 0, 0, 0));
		
		// Design JPanel
		panelNord.setOpaque(false);
		panelCentre.setOpaque(false);
		panelSud.setOpaque(false);
		panelTitre.setOpaque(false);
		
		// Design UI
		Kit.designTitre(labelTitre);
		Kit.makeAlertInfo(panelAlerte, labelAlert);
		Kit.designLabel(labelNom);
		Kit.designLabel(labelAlgo);
		Kit.designLabel(labelProvider);
		Kit.designLabel(labelTaille);
		Kit.designTextField(txtNom);
		Kit.designTextField(txtAlgo);
		Kit.designTextField(txtProvider);
		Kit.designTextField(txtTaille);
		Kit.designButton(bSymmetric);
		Kit.designButton(bAsymmetric);

		// addActionListener
		bSymmetric.addActionListener(this);
		bAsymmetric.addActionListener(this);

		// this
		setOpaque(false);
		setLayout(new BorderLayout());
		add(panelNord, BorderLayout.NORTH);
		add(panelCentre, BorderLayout.CENTER);
		add(panelSud, BorderLayout.SOUTH);
		setBorder(new EmptyBorder(40, 100, 40, 100));
	}
	
	private void resetData() {
		txtNom.setText("");
		txtAlgo.setText("");
		txtProvider.setText("");
		txtTaille.setText("");
	}
	
	/**
	 * To execute before cardLayout show this screen
	 * @throws SQLException
	 */
	public void init() {
		resetData();
		labelAlert.setText("Alertes informations");
		Kit.makeAlertInfo(panelAlerte, labelAlert);
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		// TODO Create secret key
		if (ae.getSource() == bSymmetric) {
			try {
				SecretKey secretKey = MyKey.getSecretKey(txtAlgo.getText(), Integer.valueOf(txtTaille.getText()));
				SecretKeyModel secretKeyModel = new SecretKeyModel(txtNom.getText(), txtProvider.getText(), secretKey);
				secretKeyDao.store(secretKeyModel);
				resetData();
				JOptionPane.showMessageDialog(this, "Votre clé est enregisrée avec succes.");
				labelAlert.setText("Votre clé est enregisrée avec succes");
				Kit.makeAlertSuccess(panelAlerte, labelAlert);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, e.getMessage(), "Erreur", JOptionPane.WARNING_MESSAGE);
				labelAlert.setText(e.getMessage());
				Kit.makeAlertDanger(panelAlerte, labelAlert);
			}
		}

		// TODO Create key pair
		if (ae.getSource() == bAsymmetric) {
			try {
				int taille = Integer.valueOf(txtTaille.getText());
				KeyPair keyPair = MyKey.getKeyPair(txtAlgo.getText(), taille);
				KeyPairModel keyPairModel = new KeyPairModel(txtNom.getText(), txtAlgo.getText(), txtProvider.getText(), taille, keyPair);
				keyPairDao.store(keyPairModel);
				resetData();
				JOptionPane.showMessageDialog(this, "Votre clé est enregisrée avec succes.");
				labelAlert.setText("Votre clé est enregisrée avec succes");
				Kit.makeAlertSuccess(panelAlerte, labelAlert);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, e.getMessage(), "Erreur", JOptionPane.WARNING_MESSAGE);
				labelAlert.setText(e.getMessage());
				Kit.makeAlertDanger(panelAlerte, labelAlert);
			}
		}
	}
}
