package oumarly.views.screens;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import oumarly.Kit;

@SuppressWarnings("serial")
public class Welcome extends JPanel {
	
	private JPanel panelNord;
	private JPanel panelCentre;
	private JPanel panelAlerteSuccess;
	private JPanel panelAlerteWarning;
	private JPanel panelAlerteInfo;
	private JPanel panelAlerteDanger;
	private JLabel labelTitre;
	private JLabel labelAlerteSuccess;
	private JLabel labelAlerteWarning;
	private JLabel labelAlerteInfo;
	private JLabel labelAlerteDanger;

	public Welcome() {
		super();
		
		// create
		panelNord = new JPanel();
		panelCentre = new JPanel();
		panelAlerteSuccess = new JPanel();
		panelAlerteWarning = new JPanel();
		panelAlerteInfo = new JPanel();
		panelAlerteDanger = new JPanel();
		labelTitre = new JLabel("Crypto m2tdsi 2020-21");
		labelAlerteSuccess = new JLabel("Crypto à clé secrète");
		labelAlerteWarning = new JLabel("Crypto à clé publique");
		labelAlerteInfo = new JLabel("Gestion de clés secrètes");
		labelAlerteDanger = new JLabel("Gestion de paires de clés");
		
		// position
		panelNord.add(labelTitre);
		panelAlerteSuccess.add(labelAlerteSuccess);
		panelAlerteWarning.add(labelAlerteWarning);
		panelAlerteInfo.add(labelAlerteInfo);
		panelAlerteDanger.add(labelAlerteDanger);
		
		panelCentre.setLayout(new GridLayout(2, 2, 15, 15));
		panelCentre.add(panelAlerteSuccess);
		panelCentre.add(panelAlerteWarning);
		panelCentre.add(panelAlerteInfo);
		panelCentre.add(panelAlerteDanger);
		panelCentre.setBorder(new EmptyBorder(50, 0, 90, 0));
		
		// Design JPanel
		panelNord.setOpaque(false);
		panelCentre.setOpaque(false);
		
		// Design UI
		Kit.makeWelcomeMessage(panelNord, labelTitre);
		Kit.makeAlertSuccess(panelAlerteSuccess, labelAlerteSuccess);
		Kit.makeAlertWarning(panelAlerteWarning, labelAlerteWarning);
		Kit.makeAlertInfo(panelAlerteInfo, labelAlerteInfo);
		Kit.makeAlertDanger(panelAlerteDanger, labelAlerteDanger);
		
		// this
		setOpaque(false);
		setLayout(new BorderLayout());
		add(panelNord, BorderLayout.NORTH);
		add(panelCentre, BorderLayout.CENTER);
		setBorder(new EmptyBorder(150, 90, 150, 90));
	}
}
