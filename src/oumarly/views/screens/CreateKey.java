package oumarly.views.screens;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.KeyPair;

import javax.crypto.SecretKey;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import oumarly.app.crypto.MyKey;
import oumarly.app.dao.KeyPairDao;
import oumarly.app.dao.SecretKeyDao;
import oumarly.app.model.KeyPairModel;
import oumarly.app.model.SecretKeyModel;

@SuppressWarnings("serial")
public class CreateKey extends JPanel implements ActionListener {

	private JPanel panelCentre;
	private JPanel panelSud;
	private JLabel labelNom;
	private JLabel labelAlgo;
	private JLabel labelTaille;
	private JTextField txtNom;
	private JTextField txtAlgo;
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
		panelCentre = new JPanel();
		panelSud = new JPanel();
		labelNom = new JLabel("Nom de votre clé (juste pour nommer)");
		labelAlgo = new JLabel("Algorithme ou transformation");
		labelTaille = new JLabel("Taille de la clé");
		txtNom = new JTextField();
		txtAlgo = new JTextField();
		txtTaille = new JTextField();
		bSymmetric = new JButton("Créer cle secrete");
		bAsymmetric = new JButton("Créer paire de clés");
		
		// position
		panelCentre.setLayout(new GridLayout(6, 1, 0, 5));
		panelCentre.add(labelNom);
		panelCentre.add(txtNom);
		panelCentre.add(labelAlgo);
		panelCentre.add(txtAlgo);
		panelCentre.add(labelTaille);
		panelCentre.add(txtTaille);

		panelSud.setLayout(new GridLayout(1, 2, 50, 0));
		panelSud.add(bSymmetric);
		panelSud.add(bAsymmetric);
		panelSud.setBorder(new EmptyBorder(30, 0, 0, 0));
		
		// JPanel
		panelCentre.setOpaque(false);
		panelSud.setOpaque(false);
		
		// JTextField
		txtNom.setOpaque(false);
		txtAlgo.setOpaque(false);
		txtTaille.setOpaque(false);
		
		// Buttons
		bSymmetric.setPreferredSize(new Dimension(100, 50));
		bAsymmetric.setPreferredSize(new Dimension(100, 50));

		// addActionListener
		bSymmetric.addActionListener(this);
		bAsymmetric.addActionListener(this);

		// this
		setLayout(new BorderLayout());
		add(panelCentre, BorderLayout.CENTER);
		add(panelSud, BorderLayout.SOUTH);
		setBorder(new EmptyBorder(150, 100, 200, 100));
		setOpaque(false);
	}
	
	private void resetData() {
		txtNom.setText("");
		txtAlgo.setText("");
		txtTaille.setText("");
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		// TODO Create secret key
		if (ae.getSource() == bSymmetric) {
			try {
				SecretKey secretKey = MyKey.getSecretKey(txtAlgo.getText(), Integer.valueOf(txtTaille.getText()));
				SecretKeyModel secretKeyModel = new SecretKeyModel(txtNom.getText(), secretKey);
				secretKeyDao.store(secretKeyModel);
				resetData();
				JOptionPane.showMessageDialog(this, "Votre clé est enregisrée avec succes.");
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, e.getMessage(), "Erreur", JOptionPane.WARNING_MESSAGE);
			}
		}

		// TODO Create key pair
		if (ae.getSource() == bAsymmetric) {
			try {
				KeyPair keyPair = MyKey.getKeyPair(txtAlgo.getText(), Integer.valueOf(txtTaille.getText()));
				KeyPairModel keyPairModel = new KeyPairModel(txtNom.getText(), txtAlgo.getText(), keyPair);
				keyPairDao.store(keyPairModel);
				resetData();
				JOptionPane.showMessageDialog(this, "Votre clé est enregisrée avec succes.");
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, e.getMessage(), "Erreur", JOptionPane.WARNING_MESSAGE);
			}
		}
	}
}
