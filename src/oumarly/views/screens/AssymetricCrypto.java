package oumarly.views.screens;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.DSAPrivateKey;
import java.security.interfaces.DSAPublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.crypto.interfaces.DHPrivateKey;
import javax.crypto.interfaces.DHPublicKey;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileSystemView;

import oumarly.Kit;
import oumarly.app.crypto.MyCipher;
import oumarly.app.dao.KeyPairDao;
import oumarly.app.model.KeyPairModel;

@SuppressWarnings("serial")
public class AssymetricCrypto extends JPanel implements ActionListener {

	private JPanel panelNord;
	private JPanel panelCentre;
	private JPanel panelSud;
	private JPanel panelTitre;
	private JPanel panelAlerte;
	private JPanel panelFile;
	private JPanel panelFolder;
	private JLabel labelTitre;
	private JLabel labelAlert;
	private JLabel labelFile;
	private JLabel labelFolder;
	private JLabel labelKey;
	private JLabel labelProvider;
	private JTextField txtFile;
	private JTextField txtFolder;
	private JComboBox<String> comboKey;
	private JTextField txtProvider;
	private JButton bFile;
	private JButton bFolder;
	private JButton bCrypt;
	private JButton bDecrypt;
	
	private ArrayList<KeyPairModel> keys;
	private KeyPairDao keyPairDao;
	
	private String filename;

	public AssymetricCrypto() {
		super();
		keys = new ArrayList<KeyPairModel>();
		keyPairDao = new KeyPairDao();
		filename = "";

		// create
		panelNord = new JPanel();
		panelCentre = new JPanel();
		panelSud = new JPanel();
		panelTitre = new JPanel();
		panelAlerte = new JPanel();
		panelFile = new JPanel();
		panelFolder = new JPanel();
		labelTitre = new JLabel("Crypto à clé publique");
		labelAlert = new JLabel("Alerte Information");
		labelFile = new JLabel("Fichier à chiffrer");
		labelFolder = new JLabel("Dossier destination");
		labelKey = new JLabel("Choisissez votre cle");
		labelProvider = new JLabel("Provider (optionnel)");
		txtFile = new JTextField();
		txtFolder = new JTextField();
		comboKey = new JComboBox<String>();
		txtProvider = new JTextField();
		bFile = new JButton("Parcourir");
		bFolder = new JButton("Parcourir");
		bCrypt = new JButton("Chiffrer");
		bDecrypt = new JButton("Dechiffrer");
		
		// position
		panelTitre.add(labelTitre);
		panelAlerte.add(labelAlert);

		panelNord.setLayout(new GridLayout(2, 1));
		panelNord.add(panelTitre);
		panelNord.add(panelAlerte);
		panelNord.setBorder(new EmptyBorder(0, 0, 20, 0));

		panelFile.setLayout(new BorderLayout());
		panelFile.add(txtFile, BorderLayout.CENTER);
		panelFile.add(bFile, BorderLayout.EAST);

		panelFolder.setLayout(new BorderLayout());
		panelFolder.add(txtFolder, BorderLayout.CENTER);
		panelFolder.add(bFolder, BorderLayout.EAST);

		panelCentre.setLayout(new GridLayout(8, 1, 0, 5));
		panelCentre.add(labelFile);
		panelCentre.add(panelFile);
		panelCentre.add(labelFolder);
		panelCentre.add(panelFolder);
		panelCentre.add(labelKey);
		panelCentre.add(comboKey);
		panelCentre.add(labelProvider);
		panelCentre.add(txtProvider);

		panelSud.setLayout(new GridLayout(1, 2, 50, 0));
		panelSud.add(bCrypt);
		panelSud.add(bDecrypt);
		panelSud.setBorder(new EmptyBorder(30, 0, 0, 0));
		
		// Design JPanel
		panelNord.setOpaque(false);
		panelCentre.setOpaque(false);
		panelSud.setOpaque(false);
		panelTitre.setOpaque(false);
		panelFile.setOpaque(false);
		panelFolder.setOpaque(false);
		
		// Design UI
		Kit.designTitre(labelTitre);
		Kit.makeAlertInfo(panelAlerte, labelAlert);
		Kit.designLabel(labelFile);
		Kit.designLabel(labelFolder);
		Kit.designLabel(labelKey);
		Kit.designLabel(labelProvider);
		Kit.designTextField(txtFile);
		Kit.designTextField(txtFolder);
		Kit.designComboBox(comboKey);
		Kit.designTextField(txtProvider);
		Kit.designButton(bCrypt);
		Kit.designButton(bDecrypt);
		Kit.designButtonFileChosser(bFile);
		Kit.designButtonFileChosser(bFolder);
		
		// addActionListener
		bFile.addActionListener(this);
		bFolder.addActionListener(this);
		bCrypt.addActionListener(this);
		bDecrypt.addActionListener(this);

		// this
		setOpaque(false);
		setLayout(new BorderLayout());
		add(panelNord, BorderLayout.NORTH);
		add(panelCentre, BorderLayout.CENTER);
		add(panelSud, BorderLayout.SOUTH);
		setBorder(new EmptyBorder(40, 100, 40, 100));
	}
	
	private void resetData() {
		txtFile.setText("");
		txtFolder.setText("");
		txtProvider.setText("");
	}
	
	private void loadKeys() throws SQLException {
		keys = keyPairDao.all();
		comboKey.removeAllItems();
		for (KeyPairModel key : keys) {
			comboKey.addItem(key.getNom());
		}
	}
	
	private KeyPairModel getSelectedKey() {
		return keys.get(comboKey.getSelectedIndex());
	}
	
	/**
	 * To execute before cardLayout show this screen
	 * @throws SQLException
	 */
	public void init() throws SQLException {
		resetData();
		loadKeys();
		labelAlert.setText("Alertes informations");
		Kit.makeAlertInfo(panelAlerte, labelAlert);
	}
	
	/*private Key formatKey(String algo, Key key) {
		switch (algo) {
		case "RSA":
			return 
			break;

		default:
			break;
		}
	}*/

	@Override
	public void actionPerformed(ActionEvent ae) {
		// TODO Select file
		if (ae.getSource() == bFile) {
			JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

	        int returnValue = jfc.showOpenDialog(null);
	        if (returnValue == JFileChooser.APPROVE_OPTION) {
	            File selectedFile = jfc.getSelectedFile();
	            filename = selectedFile.getName();
	            txtFile.setText(selectedFile.getAbsolutePath());
	        }
		}

		// TODO Select file
		if (ae.getSource() == bFolder) {
			JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
	        jfc.setDialogTitle("Choose a directory to save your file: ");
	        jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

	        int returnValue = jfc.showSaveDialog(null);
	        if (returnValue == JFileChooser.APPROVE_OPTION) {
	            if (jfc.getSelectedFile().isDirectory()) {
	            	txtFolder.setText("" + jfc.getSelectedFile());
	            }
	        }
		}

		// TODO encrypt file
		if (ae.getSource() == bCrypt) {
			try {
				String inputfile = txtFile.getText();
				String outputfile = txtFolder.getText() + "/chiffre-" + filename;
				KeyPairModel pairModel = getSelectedKey();
				PublicKey pubKey = pairModel.getPublicKey();
				String algo = pairModel.getAlgorithme();
				String provider = txtProvider.getText();
				
				switch (pairModel.getAlgorithme()) {
					case "RSA":
						if (provider.length() == 0)
							MyCipher.cryptFile(algo, (RSAPublicKey) pubKey, inputfile, outputfile);
						else
							MyCipher.cryptFile(algo, provider, (RSAPublicKey) pubKey, inputfile, outputfile);
						break;
						
					case "DSA":
						if (provider.length() == 0)
							MyCipher.cryptFile(algo, (DSAPublicKey) pubKey, inputfile, outputfile);
						else
							MyCipher.cryptFile(algo, provider, (DSAPublicKey) pubKey, inputfile, outputfile);
						break;
						
					case "DH":
						if (provider.length() == 0)
							MyCipher.cryptFile(algo, (DHPublicKey) pubKey, inputfile, outputfile);
						else
							MyCipher.cryptFile(algo, provider, (DSAPublicKey) pubKey, inputfile, outputfile);
						break;
		
					default:
						break;
				}
				
				JOptionPane.showMessageDialog(this, "Votre fichier est chiffre avec succes.");
				labelAlert.setText("Success -> " + outputfile);
				Kit.makeAlertSuccess(panelAlerte, labelAlert);
				resetData();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, e.getMessage(), "Erreur", JOptionPane.WARNING_MESSAGE);
				labelAlert.setText(e.getMessage());
				Kit.makeAlertDanger(panelAlerte, labelAlert);
			}
		}

		// TODO decrypt file
		if (ae.getSource() == bDecrypt) {
			try {
				String inputfile = txtFile.getText();
				String outputfile = txtFolder.getText() + "/dechiffre-" + filename;
				KeyPairModel pairModel = getSelectedKey();
				PrivateKey privKey = pairModel.getPrivateKey();
				String algo = pairModel.getAlgorithme();
				String provider = txtProvider.getText();

				switch (pairModel.getAlgorithme()) {
					case "RSA":
						if (provider.length() == 0)
							MyCipher.decryptFile(algo, (RSAPrivateKey) privKey, inputfile, outputfile);
						else
							MyCipher.decryptFile(algo, provider, (RSAPrivateKey) privKey, inputfile, outputfile);
						break;
						
					case "DSA":
						if (provider.length() == 0)
							MyCipher.decryptFile(algo, (DSAPrivateKey) privKey, inputfile, outputfile);
						else
							MyCipher.decryptFile(algo, provider, (DSAPrivateKey) privKey, inputfile, outputfile);
						break;
						
					case "DH":
						if (provider.length() == 0)
							MyCipher.decryptFile(algo, (DHPrivateKey) privKey, inputfile, outputfile);
						else
							MyCipher.decryptFile(algo, provider, (DHPrivateKey) privKey, inputfile, outputfile);
						break;
		
					default:
						break;
				}

				JOptionPane.showMessageDialog(this, "Votre fichier est dechiffre avec succes.");
				labelAlert.setText("Success -> " + outputfile);
				Kit.makeAlertSuccess(panelAlerte, labelAlert);
				resetData();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, e.getMessage(), "Erreur", JOptionPane.WARNING_MESSAGE);
				labelAlert.setText(e.getMessage());
				Kit.makeAlertDanger(panelAlerte, labelAlert);
			}
		}
	}
}
