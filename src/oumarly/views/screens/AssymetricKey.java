package oumarly.views.screens;

import java.awt.GridLayout;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import oumarly.app.dao.KeyPairDao;
import oumarly.app.model.KeyPairModel;

@SuppressWarnings("serial")
public class AssymetricKey extends JPanel {
	
	private KeyPairDao keyPairDao;
	private DefaultTableModel model;
	private JTable table;
	private JScrollPane scPane;

	public AssymetricKey() {
		super();
		
		model = new DefaultTableModel();
		model.setColumnIdentifiers(new String[] {"NOM", "ALGO"});
		
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
	
	public void fetchKeyPairs() throws SQLException {
		keyPairDao = new KeyPairDao();
		ArrayList<KeyPairModel> pairKeys = keyPairDao.all();
		model.setRowCount(0);
		for (KeyPairModel key : pairKeys) {
			model.addRow(new Object[] {key.getNom(), key.getAlgorithme()});
		}
	}
}
