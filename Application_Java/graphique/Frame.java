package graphique;

import java.awt.event.ActionEvent;
import javax.swing.*;

import eventModel.AL;
import upperClass.Syst;

public class Frame extends JFrame implements AL{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JMenuBar menuBar;
	//	private OngletGestion ongletGestion;
	//	private OngletModeNormal ongletModeNormal;
	//	private  OngletScenario ongletSimulation;

	private JMenuItem serveur;
	private JMenuItem log;
	private JMenuItem sauvegarder;
	private static Frame_Logger frameLog;


	public Frame() {
		super();
		this.setTitle("APP RS 2021");
		this.setExtendedState(Frame.MAXIMIZED_BOTH);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		menuBar = new JMenuBar();

		this.serveur=new JMenuItem("serveur");
		this.log=new JMenuItem("logger");
		this.sauvegarder=new JMenuItem("sauvegarder");
		this.frameLog=new Frame_Logger();

		this.initComponent();
	}

	private void initComponent() {
		JMenu fichier = new JMenu("fichier");
		JMenu edition = new JMenu("edition");

		//Definition des diffÃ©rents menus
		menuBar.add(fichier);
		menuBar.add(edition);

		//Contenu du menu Fichier
		JMenuItem ouvrir = new JMenuItem("ouvrir");
		fichier.add(ouvrir);fichier.add(sauvegarder);
		//Contenu du menu prÃ©fÃ©rence


		this.setJMenuBar(menuBar);
		serveur.addActionListener(this);
		log.addActionListener(this);
		sauvegarder.addActionListener(this);
		edition.add(serveur);
		edition.add(log);



		//Les onglets
		JTabbedPane onglet = new JTabbedPane();
		onglet.add("GESTION", new OngletGestion());
		onglet.add("NORMAL", new OngletModeNormal());
		onglet.add("SCENARIO", new OngletScenario());

		this.getContentPane().add(onglet);
		this.setVisible(true);

	}

	public static void actualize() {
		/*this.ongletGestion.initComponent();
		this.ongletModeNormal.initComponent();*/
		//ongletSimulation.repaint();
	}
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==serveur) {
			new Dialog_PrefenceServeur();
		}
		if(e.getSource()==log) {
			frameLog.setVisible(true);
		}
		if(e.getSource()==sauvegarder) {
			try{
				System.out.println("coucou");
				Syst.save();
				
			}
			catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}
	public static Frame_Logger getLogger() {
		return frameLog;
	}
}

