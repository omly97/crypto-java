package oumarly;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableCellRenderer;

public class Kit {
	
	public static final Color NAV_BG = new Color(52, 58, 64);
	public static final Color NAV_FG = new Color(248, 249, 250);
	public static final Color NAV_MENU_ACTIVE = new Color(73, 80, 87);

	
	private static final Color COLOR_WHITE = new Color(255, 255, 255);
	private static final Color COLOR_INDIGO = new Color(71, 65, 124);
	private static final Color COLOR_BLACK_LIGHT = new Color(226, 227, 229);
	private static final Color COLOR_BLUE_LIGHT = new Color(173, 232, 244);
	private static final Color COLOR_BLUE_DARK = new Color(2, 62, 138);
	private static final Color COLOR_GREEN_LIGHT = new Color(183, 228, 199);
	private static final Color COLOR_GREEN_DARK = new Color(27, 67, 50);
	private static final Color COLOR_RED_LIGHT = new Color(251, 195, 188);
	private static final Color COLOR_RED_DARK = new Color(148, 27, 12);
	private static final Color COLOR_YELLOW_LIGHT = new Color(255, 225, 105);
	private static final Color COLOR_YELLOW_DARK = new Color(128, 91, 16);

	private static final Font FONT_VERDANA_12 = new Font("Verdana", Font.BOLD, 12);
	
	private static final Border BORDER_ALERT = new EmptyBorder(20, 15, 20, 15);
	
	
	/**
	 * Background color to use in Router
	 * @return
	 */
	public static Color getAppBackground() {
		return COLOR_WHITE;
	}
	

	/**
	 * Design label
	 * @param label
	 */
	public static void designLabel(JLabel label) {
		label.setFont(FONT_VERDANA_12);
		label.setForeground(COLOR_INDIGO);
	}
	

	/**
	 * Design screen title
	 * @param label
	 */
	public static void designTitre(JLabel label) {
		label.setFont(FONT_VERDANA_12);
		label.setForeground(COLOR_INDIGO);
		label.setFont(label.getFont().deriveFont(18.0f));
	}
	

	/**
	 * Design welcome screen title
	 * @param label
	 */
	public static void makeWelcomeMessage(JPanel panel, JLabel label) {
		label.setFont(FONT_VERDANA_12);
		label.setForeground(COLOR_INDIGO);
		label.setFont(label.getFont().deriveFont(30.0f));
		panel.setOpaque(false);
		panel.setBorder(new MatteBorder(0, 0, 5, 0, COLOR_INDIGO));
	}
	
	/**
	 * Design text field
	 * @param field
	 */
	public static void designTextField(JTextField field) {
		field.setOpaque(false);
		field.setFont(FONT_VERDANA_12);
		field.setForeground(COLOR_INDIGO);
		field.setBorder(BorderFactory.createCompoundBorder(new MatteBorder(1, 1, 1, 1, COLOR_INDIGO), new EmptyBorder(5, 8, 5, 8)));
	}
	
	/**
	 * Design combo box
	 * @param box
	 */
	public static void designComboBox(JComboBox<String> box) {
		box.setOpaque(false);
		box.setFont(FONT_VERDANA_12);
		box.setForeground(COLOR_INDIGO);
	}

	/**
	 * Design for encryption button
	 * @param button
	 */
	public static void designButton(JButton button) {
		button.setBorderPainted(true);
		button.setFocusPainted(false);
		button.setBackground(COLOR_WHITE);
		button.setForeground(COLOR_INDIGO);
		button.setPreferredSize(new Dimension(100, 50));
		button.setBorder(new MatteBorder(2, 2, 2, 2, COLOR_INDIGO));
	}
	
	/**
	 * Design for file chooser button
	 * @param button
	 */
	public static void designButtonFileChosser(JButton button) {
		button.setBorderPainted(true);
		button.setFocusPainted(false);
		button.setBackground(COLOR_BLACK_LIGHT);
		button.setForeground(COLOR_INDIGO);
	}
	
	/**
	 * Design table
	 * @param table
	 */
	public static void designTable(JTable table) {
		table.setRowHeight(40);
		table.setShowVerticalLines(false);
		table.setShowHorizontalLines(false);
		//table.setSelectionBackground(SELECTION_BACKGROUNG);
		table.setBackground(COLOR_WHITE);
		table.setForeground(COLOR_INDIGO);
		table.setFont(FONT_VERDANA_12);

		// design table header
		table.getTableHeader().setBackground(COLOR_WHITE);
		table.getTableHeader().setForeground(COLOR_INDIGO);
		table.getTableHeader().setFont(FONT_VERDANA_12);
		table.getTableHeader().setPreferredSize(new Dimension(10, 30));
		
		// center cell content
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		for (int i = 0; i < table.getColumnCount(); i++) {
			table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
		}
	}

	/**
	 * Design scroll pane
	 * @param scrollPane
	 */
	public static void designScrollPane(JScrollPane scrollPane) {
		scrollPane.setBorder(new LineBorder(COLOR_WHITE));
		scrollPane.getViewport().setBackground(COLOR_WHITE);
	}

	/**
	 * Design alert info
	 * @param panel
	 */
	public static void makeAlertInfo(JPanel panel, JLabel label) {
		label.setForeground(COLOR_BLUE_DARK);
		panel.setBackground(COLOR_BLUE_LIGHT);
		panel.setBorder(BORDER_ALERT);
	}

	/**
	 * Design alert success
	 * @param panel
	 */
	public static void makeAlertSuccess(JPanel panel, JLabel label) {
		label.setForeground(COLOR_GREEN_DARK);
		panel.setBackground(COLOR_GREEN_LIGHT);
		panel.setBorder(BORDER_ALERT);
	}

	/**
	 * Design alert danger
	 * @param panel
	 */
	public static void makeAlertDanger(JPanel panel, JLabel label) {
		label.setForeground(COLOR_RED_DARK);
		panel.setBackground(COLOR_RED_LIGHT);
		panel.setBorder(BORDER_ALERT);
	}

	/**
	 * Design alert warning
	 * @param panel
	 */
	public static void makeAlertWarning(JPanel panel, JLabel label) {
		label.setForeground(COLOR_YELLOW_DARK);
		panel.setBackground(COLOR_YELLOW_LIGHT);
		panel.setBorder(BORDER_ALERT);
	}

}
