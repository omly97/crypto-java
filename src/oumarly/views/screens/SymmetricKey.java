package oumarly.views.screens;

import java.awt.GridLayout;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

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
		
		model = new DefaultTableModel();
		model.setColumnIdentifiers(new String[] {"NOM", "ALGO", "TAILLE", "FORMAT"});
		
		table = new JTable(model);
		table.setRowHeight(40);
		table.setShowVerticalLines(false);
		table.setShowHorizontalLines(false);
		//table.setSelectionBackground(SELECTION_BACKGROUNG);
		//table.setBackground(Theme.UI_BACKGROUND);
		//table.setForeground(Theme.APP_FOREGROUND);
		//table.setFont(Theme.APP_FONT);

		//table.getTableHeader().setBackground(Theme.UI_BACKGROUND);
		//table.getTableHeader().setForeground(Theme.APP_FOREGROUND);
		//table.getTableHeader().setFont(Theme.APP_FONT);
		//table.getTableHeader().setPreferredSize(new Dimension(10, 30));
		
		scPane = new JScrollPane(table);
		//scPane.setBorder(new LineBorder(Theme.UI_BACKGROUND));
		//scPane.getViewport().setBackground(Theme.UI_BACKGROUND);

		setLayout(new GridLayout(0, 1));
		add(scPane);
		setBorder(new EmptyBorder(20, 20, 20, 20));
	}
	
	public void fetchSecretKeys() throws SQLException {
		secretKeyDao = new SecretKeyDao();
		ArrayList<SecretKeyModel> secretKeys = secretKeyDao.all();
		model.setRowCount(0);
		for (SecretKeyModel key : secretKeys) {
			model.addRow(new Object[] {key.getNom(), key.getAlgorithme(), key.getTaille(), key.getFormat()});
		}
	}
}
