package br.com.tg.gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Sobre extends JDialog {
	
	private static final long serialVersionUID = 1L;
	public JPanel jpSobre;
	public JButton jbOk;	

	public Sobre(JFrame owner) {
		super(owner, "Sobre AuxPlot");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);		
		initComponents();		
	}
	
	private void initComponents() {
		setModal(true);
		setSize(new Dimension(400, 300));
		add(getJpSobre());
		setVisible(true);
	}

	public JPanel getJpSobre() {
		if (jpSobre == null) {
			jpSobre = new JPanel();
			jpSobre.add(getJbOk());
		}
		return jpSobre;
	}

	public JButton getJbOk() {
		if (jbOk == null) {
			jbOk = new JButton("Ok");
			jbOk.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					setVisible(false);
				}
			});
		}
		return jbOk;
	}
}
