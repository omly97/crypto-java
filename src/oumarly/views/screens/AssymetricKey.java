package oumarly.views.screens;

import java.awt.BorderLayout;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import oumarly.Kit;
import oumarly.app.dao.KeyPairDao;
import oumarly.app.model.KeyPairModel;

@SuppressWarnings("serial")
public class AssymetricKey extends JPanel {
	
	private JPanel panelNord;
	private JLabel labelTitre;
	private KeyPairDao keyPairDao;
	private DefaultTableModel model;
	private JTable table;
	private JScrollPane scPane;

	public AssymetricKey() {
		super();
		// Create
		panelNord = new JPanel();
		labelTitre = new JLabel("Liste des paires de clés");

		model = new DefaultTableModel() {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table = new JTable(model);
		scPane = new JScrollPane(table);
		model.setColumnIdentifiers(new String[] {"NOM", "ALGO", "TAILLE", "PROVIDER"});
		
		// position
		panelNord.add(labelTitre);
		panelNord.setOpaque(false);
		panelNord.setBorder(new EmptyBorder(0, 0, 30, 0));
		
		// Design UI
		Kit.designTitre(labelTitre);
		Kit.designTable(table);
		Kit.designScrollPane(scPane);

		setLayout(new BorderLayout());
		add(panelNord, BorderLayout.NORTH);
		add(scPane, BorderLayout.CENTER);
		setBorder(new EmptyBorder(20, 20, 20, 20));
		setOpaque(false);
	}
	
	public void fetchKeyPairs() throws SQLException {
		keyPairDao = new KeyPairDao();
		ArrayList<KeyPairModel> pairKeys = keyPairDao.all();
		model.setRowCount(0);
		for (KeyPairModel key : pairKeys) {
			model.addRow(new Object[] {key.getNom(), key.getAlgorithme(), key.getTaille(), key.getProvider()});
		}
	}
}
