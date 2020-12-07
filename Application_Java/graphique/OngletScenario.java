package graphique;

import javax.swing.*;
import action.Scenario;
import eventModel.AL;
import action.Action;
import action.ActionToDo;
import robot.Robot;
import sensor.Sensor;
import upperClass.Syst;
import java.awt.event.*;
import java.util.HashSet;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;
import java.awt.Font;

public class OngletScenario extends JPanel  implements AL{
	private JTable tableFlotte;
	private JTable tableRobot;
	private JTable tableAction;
	private JTable tableScenario;

	private ModeleDynamiqueFlotte modeleFlotte;
	private ModeleDynamiqueRobot modeleRobot;
	private ModeleDynamiqueAction modeleAction;
	private ModeleDynamiqueScenario modeleScenario;

	private int flotteSelect;
	private int robotSelect;
	private CanvasSimulation canvasSimulation;
	private Scenario scenario;
	private JTextField txtTime;

	private JButton raz = new JButton("RAZ");
	private JButton btnCParti = new JButton("c parti");
	private JButton btnFleetToRobot = new JButton("=>");
	private JButton btnAjouter = new JButton("Ajouter");
	private	JButton btnSupprimer = new JButton("Supprimer");
	private JButton btnRobotToAction = new JButton("=>");
	private JRadioButton simuler = new JRadioButton("Simuler");
	private JRadioButton transmettre = new JRadioButton("Transmettre");

	public OngletScenario() {
		setLayout(null);
		this.modeleFlotte=new ModeleDynamiqueFlotte(Syst.getFleets());
		this.modeleRobot=new ModeleDynamiqueRobot();
		this.modeleAction=new ModeleDynamiqueAction();
		this.modeleScenario=new ModeleDynamiqueScenario();
		this.flotteSelect=-1; this.robotSelect=-1;
		this.scenario=new Scenario();
		this.canvasSimulation = CanvasSimulation.getInstance(scenario);

		canvasSimulation.setBounds(10, 500, 1880, 425);
		add(canvasSimulation);
		//JButtonPane transmition =new JButtonPane();
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 410, 460);
		add(scrollPane);

		tableFlotte = new JTable(modeleFlotte);
		scrollPane.setViewportView(tableFlotte);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(475, 10, 410, 460);
		add(scrollPane_1);

		tableRobot = new JTable(modeleRobot);
		scrollPane_1.setViewportView(tableRobot);

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(940, 10, 410, 460);
		add(scrollPane_2);

		tableAction = new JTable(modeleAction);
		scrollPane_2.setViewportView(tableAction);

		//canvaSimulation = new CanvasSimulation(scenario);

		btnCParti.setBounds(1362, 96, 91, 40);
		add(btnCParti);

		raz.setBounds(1362, 156,91,40);
		add(raz);

		
		simuler.setSelected(true);//par défaut, on fait une simulation
		simuler.setBounds(1358, 208, 127, 25);
		add(simuler);


		transmettre.setBounds(1358, 238, 127, 25);
		add(transmettre);
		if (Syst.getClientsocket().isOpen()==false) {
			transmettre.setEnabled(false);
		}

		ButtonGroup group = new ButtonGroup();
		group.add(simuler);group.add(transmettre);


		btnFleetToRobot.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnFleetToRobot.setBounds(423, 208, 50, 25);
		add(btnFleetToRobot);


		btnRobotToAction.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnRobotToAction.setBounds(888, 208, 50, 25);
		add(btnRobotToAction);


		
		btnAjouter.setBounds(1350, 400, 97, 25);
		add(btnAjouter);

		btnSupprimer.setBounds(1350, 440, 97, 25);
		add(btnSupprimer);

		txtTime = new JTextField();
		txtTime.setBounds(1357, 368, 116, 22);
		txtTime.setText("0");
		add(txtTime);
		txtTime.setColumns(10);

		tableScenario=new JTable(modeleScenario);
		JScrollPane scrollPane_3 = new JScrollPane(tableScenario);
		scrollPane_3.setBounds(1504, 10, 386, 460);
		add(scrollPane_3);
		
		raz.addActionListener(this);
		btnFleetToRobot.addActionListener(this);
		btnRobotToAction.addActionListener(this);
		btnRobotToAction.addActionListener(this);
		btnCParti.addActionListener(this);
		btnAjouter.addActionListener(this);
		btnSupprimer.addActionListener(this);
		
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==raz) {
			modeleScenario.removeAll();
			scenario.removeAll();
			System.out.println(scenario.toString());
			canvasSimulation.setTime(5);
			canvasSimulation.repaint();
		}

		if(e.getSource()==btnFleetToRobot) {
			if(tableFlotte.getSelectedRow()>-1) {
				modeleRobot.removeAll();
				HashSet<Robot> robs=Syst.getFleets().get(tableFlotte.getSelectedRow()).getRobots();

				flotteSelect=tableFlotte.getSelectedRow();
				modeleRobot.initTable(robs);
			}
		}
		if(e.getSource()==btnRobotToAction) {
			if(tableRobot.getSelectedRow()>-1) {
				robotSelect=tableRobot.getSelectedRow();
				modeleAction.removeAll();
				HashSet<Robot> robs=Syst.getFleets().get(flotteSelect).getRobots();
				Iterator<Robot> value = robs.iterator();
				HashSet<Action> acts=new HashSet<Action>();
				Robot temp;
				for (int i=0;i<robotSelect+1;i++) {
					temp=value.next();
					acts=temp.getAction();
				}
				modeleAction.initTable(acts);
			}
			if (Syst.getClientsocket().isOpen()) {
				transmettre.setEnabled(true);
			}
		}
		if(e.getSource()==btnCParti) {
			if (simuler.isSelected()) {canvasSimulation.simulate();}
			else if(transmettre.isSelected()) {canvasSimulation.transmettre();}
		}
		if(e.getSource()==btnAjouter) {
			if(tableAction.getSelectedRow()>-1){
				HashSet<Robot> robs=Syst.getFleets().get(flotteSelect).getRobots();
				Iterator<Robot> valueRobots=robs.iterator();
				Robot rob=null;
				for(int i=0;i<robotSelect+1;i++) {
					rob=valueRobots.next();
				}
				HashSet<Action> acts=rob.getAction();
				Iterator<Action> valueAction=acts.iterator();
				Action act=null;
				for(int i=0;i<tableAction.getSelectedRow()+1;i++) {
					act=valueAction.next();
				}
				ActionToDo actiontodo=new ActionToDo(rob.getName(), act.getNom(), Long.parseLong(txtTime.getText()));
				scenario.addAction(actiontodo);
				modeleScenario.addAction(actiontodo);
				canvasSimulation.repaint();
			}
		}
		if(e.getSource()==btnSupprimer) {
			int a=tableScenario.getSelectedRow();
			
			modeleScenario.removeAction(a);
			
			scenario.remove(a);
			canvasSimulation.repaint();
		}

	}
}
