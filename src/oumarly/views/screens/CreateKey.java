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
public class CreateKey extends JPanel {

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
		
		panelCentre = new JPanel();
		labelNom = new JLabel("Nom de votre clé (juste pour nommer)");
		txtNom = new JTextField();
		labelAlgo = new JLabel("Algorithme ou transformation");
		txtAlgo = new JTextField();
		labelTaille = new JLabel("Taille de la clé");
		txtTaille = new JTextField();
		panelCentre.setLayout(new GridLayout(6, 1, 0, 5));
		panelCentre.add(labelNom);
		panelCentre.add(txtNom);
		panelCentre.add(labelAlgo);
		panelCentre.add(txtAlgo);
		panelCentre.add(labelTaille);
		panelCentre.add(txtTaille);
		
		bSymmetric = new JButton("Créer cle secrete");
		bSymmetric.setPreferredSize(new Dimension(100, 50));
		bSymmetric.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				createSecretKey();
			}
		});
		
		bAsymmetric = new JButton("Créer paire de clés");
		bAsymmetric.setPreferredSize(new Dimension(100, 50));
		bAsymmetric.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				createPairKey();
			}
		});
		
		panelSud = new JPanel();
		panelSud.setLayout(new GridLayout(1, 2, 50, 0));
		panelSud.add(bSymmetric);
		panelSud.add(bAsymmetric);
		panelSud.setBorder(new EmptyBorder(30, 0, 0, 0));

		setLayout(new BorderLayout());
		add(panelCentre, BorderLayout.CENTER);
		add(panelSud, BorderLayout.SOUTH);
		setBorder(new EmptyBorder(150, 100, 200, 100));
	}
	
	private void resetData() {
		txtNom.setText("");
		txtAlgo.setText("");
		txtTaille.setText("");
	}

	private void createSecretKey() {
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

	private void createPairKey() {
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
