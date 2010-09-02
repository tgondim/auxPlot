package br.com.tg.guiUtil;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JPanel;

import br.com.tg.gui.auxPlot;

public abstract class Tela extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private auxPlot telaPrincipal;
	
	public Tela() {
		super();
	}

	public Tela(String titulo, auxPlot newTelaPrincipal) {
		super();
		telaPrincipal = newTelaPrincipal;
		setFont(new Font("Dialog", Font.PLAIN, 12));
		setForeground(Color.BLACK);			
		setTelaPrincipal(newTelaPrincipal);
		getTelaPrincipal().getJtPane().add(titulo, this);
		setSize(792, 550);
		setFocusable(true);
		setVisible(true);
		setFocusable(true);
	}

	public auxPlot getTelaPrincipal() {
		return telaPrincipal;
	}

	public void setTelaPrincipal(auxPlot telaPrincipal) {
		this.telaPrincipal = telaPrincipal;
	}
}
