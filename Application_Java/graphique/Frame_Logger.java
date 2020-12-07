package graphique;

import java.util.*;

import javax.swing.*;

public class Frame_Logger extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private ArrayList<String> log;
	private JTextArea txt;

	public Frame_Logger() {
		this.setTitle("Logger");
		this.setSize(500,500);
		this.setVisible(false);
		this.log=new ArrayList<String>();
		
		this.txt = new JTextArea();
		initComponent();
	}

	private void initComponent() {
		txt.setText("");
		txt.setText(log.toString());
		this.add(txt);
	}
	
	public void ajouterLigne(String str) {
		log.add(str);
		this.initComponent();
	}
	public ArrayList getLog() {
		return this.log;
	}

}
