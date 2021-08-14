package oumarly.views.screens;

import java.awt.GridLayout;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import oumarly.Kit;
import oumarly.app.dao.SecretKeyDao;
import oumarly.app.model.SecretKeyModel;

@SuppressWarnings("serial")
public class SymmetricKey extends JPanel {
	
	private SecretKeyDao secretKeyDao;
	private DefaultTableModel model;
	private JTable table;
	private JScrollPane scPane;

	public SymmetricKey() {
		super();
		// Create
		model = new DefaultTableModel() {
			public boolean isCellEditable(int row, int column) {
				return false;//This causes all cells to be not editable
			}
		};
		table = new JTable(model);
		scPane = new JScrollPane(table);

		model.setColumnIdentifiers(new String[] {"NOM", "ALGO", "TAILLE", "PROVIDER"});
		
		// Design UI
		Kit.designTable(table);
		Kit.designScrollPane(scPane);

		setLayout(new GridLayout(0, 1));
		add(scPane);
		setBorder(new EmptyBorder(20, 20, 20, 20));
		setOpaque(false);
	}
	
	public void fetchSecretKeys() throws SQLException {
		secretKeyDao = new SecretKeyDao();
		ArrayList<SecretKeyModel> secretKeys = secretKeyDao.all();
		model.setRowCount(0);
		for (SecretKeyModel key : secretKeys) {
			model.addRow(new Object[] {key.getNom(), key.getAlgorithme(), key.getTaille(), key.getProvider()});
		}
	}
}
