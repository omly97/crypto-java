package oumarly.views.screens;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileSystemView;

import oumarly.app.crypto.MyCipher;
import oumarly.app.dao.SecretKeyDao;
import oumarly.app.model.SecretKeyModel;

@SuppressWarnings("serial")
public class SymmetricCrypto extends JPanel implements ActionListener {
	
	private JPanel panelCentre;
	private JPanel panelSud;
	private JPanel panelFile;
	private JPanel panelFolder;
	private JLabel labelFile;
	private JLabel labelFolder;
	private JLabel labelKey;
	private JTextField txtFile;
	private JTextField txtFolder;
	private JComboBox<String> comboKey;
	private JButton bFile;
	private JButton bFolder;
	private JButton bCrypt;
	private JButton bDecrypt;
	
	private ArrayList<SecretKeyModel> keys;
	private SecretKeyDao secretKeyDao;
	
	private String filename;

	public SymmetricCrypto() {
		super();
		keys = new ArrayList<SecretKeyModel>();
		secretKeyDao = new SecretKeyDao();
		filename = "";

		// create
		panelCentre = new JPanel();
		panelSud = new JPanel();
		panelFile = new JPanel();
		panelFolder = new JPanel();
		labelFile = new JLabel("Fichier à chiffrer");
		labelFolder = new JLabel("Dossier destination");
		labelKey = new JLabel("Choisissez votre cle");
		txtFile = new JTextField();
		txtFolder = new JTextField();
		comboKey = new JComboBox<String>();
		bFile = new JButton("Parcourir");
		bFolder = new JButton("Parcourir");
		bCrypt = new JButton("Chiffrer");
		bDecrypt = new JButton("Dechiffrer");
		
		// position
		panelFile.setLayout(new BorderLayout());
		panelFile.add(txtFile, BorderLayout.CENTER);
		panelFile.add(bFile, BorderLayout.EAST);
		panelFile.setOpaque(false);
		
		panelFolder.setLayout(new BorderLayout());
		panelFolder.add(txtFolder, BorderLayout.CENTER);
		panelFolder.add(bFolder, BorderLayout.EAST);
		
		panelCentre.setLayout(new GridLayout(6, 1, 0, 5));
		panelCentre.add(labelFile);
		panelCentre.add(panelFile);
		panelCentre.add(labelFolder);
		panelCentre.add(panelFolder);
		panelCentre.add(labelKey);
		panelCentre.add(comboKey);
		
		panelSud.setLayout(new GridLayout(1, 2, 50, 0));
		panelSud.add(bCrypt);
		panelSud.add(bDecrypt);
		panelSud.setBorder(new EmptyBorder(30, 0, 0, 0));
		
		// JPanel
		panelCentre.setOpaque(false);
		panelSud.setOpaque(false);
		panelFile.setOpaque(false);
		panelFolder.setOpaque(false);
		
		// JTextField
		txtFile.setEditable(false);
		txtFile.setOpaque(false);
		txtFolder.setEditable(false);
		txtFolder.setOpaque(false);
		
		// Buttons
		bCrypt.setPreferredSize(new Dimension(100, 50));
		bDecrypt.setPreferredSize(new Dimension(100, 50));
		
		// addActionListener
		bFile.addActionListener(this);
		bFolder.addActionListener(this);
		bCrypt.addActionListener(this);
		bDecrypt.addActionListener(this);

		// this
		setLayout(new BorderLayout());
		add(panelCentre, BorderLayout.CENTER);
		add(panelSud, BorderLayout.SOUTH);
		setBorder(new EmptyBorder(150, 100, 200, 100));
		setOpaque(false);
	}
	
	public void loadKeys() throws SQLException {
		keys = secretKeyDao.all();
		comboKey.removeAllItems();
		for (SecretKeyModel key : keys) {
			comboKey.addItem(key.getNom());
		}
	}
	
	private SecretKeyModel getSelectedKey() {
		return keys.get(comboKey.getSelectedIndex());
	}


	@Override
	public void actionPerformed(ActionEvent ae) {
		// TODO select file
		if (ae.getSource() == bFile) {
			JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

	        int returnValue = jfc.showOpenDialog(null);
	        if (returnValue == JFileChooser.APPROVE_OPTION) {
	            File selectedFile = jfc.getSelectedFile();
	            filename = selectedFile.getName();
	            txtFile.setText(selectedFile.getAbsolutePath());
	        }
		}
		
		// TODO select folder
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
			String inputfile = txtFile.getText();
			String outputfile = txtFolder.getText() + "/chiffre-" + filename;
			SecretKeyModel KeyModel = getSelectedKey();
			try {
				MyCipher.cryptFile(KeyModel.getAlgorithme(), KeyModel.getSecretKey(), inputfile, outputfile);
				JOptionPane.showMessageDialog(this, "Votre fichier est chiffre avec succes.");
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, e.getMessage(), "Erreur", JOptionPane.WARNING_MESSAGE);
			}
		}
		
		// TODO decrypt file
		if (ae.getSource() == bDecrypt) {
			String inputfile = txtFile.getText();
			String outputfile = txtFolder.getText() + "/dechiffre-" + filename;
			SecretKeyModel KeyModel = getSelectedKey();
			try {
				MyCipher.decryptFile(KeyModel.getAlgorithme(), KeyModel.getSecretKey(), inputfile, outputfile);
				JOptionPane.showMessageDialog(this, "Votre fichier est dechiffre avec succes.");
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, e.getMessage(), "Erreur", JOptionPane.WARNING_MESSAGE);
			}
		}
	}
}
