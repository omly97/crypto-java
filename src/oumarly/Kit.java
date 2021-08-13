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

public class Kit {
	
	public static final Color XDARK = new Color(44, 48, 55);
	public static final Color XWHITE = new Color(188, 186, 187);
	public static final Color XBLUE = new Color(89, 131, 208);
	public static final Color XGRAY = new Color(60, 60, 60);

	
	private static final Color COLOR_WHITE = new Color(255, 255, 255);
	private static final Color COLOR_INDIGO = new Color(71, 65, 124);
	private static final Color COLOR_BLACK_LIGHT = new Color(226, 227, 229);
	private static final Color COLOR_BLUE_LIGHT = new Color(204, 229, 255);
	private static final Color COLOR_BLUE_DARK = new Color(60, 112, 169);
	private static final Color COLOR_GREEN_LIGHT = new Color(212, 237, 218);
	private static final Color COLOR_GREEN_DARK = new Color(102, 151, 114);
	private static final Color COLOR_RED_LIGHT = new Color(248, 215, 218);
	private static final Color COLOR_RED_DARK = new Color(126, 44, 51);

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

		table.getTableHeader().setBackground(COLOR_WHITE);
		table.getTableHeader().setForeground(COLOR_INDIGO);
		table.getTableHeader().setFont(FONT_VERDANA_12);
		table.getTableHeader().setPreferredSize(new Dimension(10, 30));
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

}
