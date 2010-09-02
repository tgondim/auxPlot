package br.com.tg.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.SpringLayout.Constraints;

import br.com.tg.exceptions.ErroAcessoRepositorioException;

public class auxPlot extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	private JTabbedPane jtPane;
	
	private CadastroPessoas telaPessoas;
	
	private Sobre sobreAuxPlot;
	
	private static Logon logonAtivo;
	
	private String pessoaIcon;
	
	private JMenuBar jmbPrincipal;
	
	private JMenu jmManutencao;	
	private JMenu jmTabelas;	
	private JMenu jmAjuda;
	
	private JMenuItem jmiPessoas;
	private JMenuItem jmiSobre;
	private JMenuItem jmiSair;
	
	private JSeparator jsManutencao;
	
	
	public auxPlot() {	
		initComponents();
	}
	
	private void initComponents() {
//		try {
//			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//		} catch (Exception e1) {
//			e1.printStackTrace();
//		}
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setTitle("auxPlot - Controle de Plotagens");
		setFont(new Font("Dialog", Font.PLAIN, 12));
		setForeground(Color.BLACK);
		setLayout(new GridLayout());
		File file = new File("auxPlot.properties");
		if (!file.exists()) {
			JOptionPane.showMessageDialog(getParent(),
					"O arquivo de configuração AuxPlot.properties não " +
					"foi encontrado. \nO sistema será encerrado. " +
					"\nEntre em contato com o administrador.", 
					" Atenção",
					JOptionPane.ERROR_MESSAGE);
			System.exit(0);			
		} else { 
			Properties prop = new Properties();
			try {
				prop.load(new FileInputStream(file));
				pessoaIcon = prop.getProperty("pessoaIcon");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		File iconFile = new File("resources/auxPlot.png");
		if (iconFile.exists()) {
			try {
				this.setIconImage(ImageIO.read(iconFile));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		setJMenuBar(getJmbPrincipal());
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				sairSistema(true);
			}
		});		
		add(getJtPane(), new Constraints());
//		if (getLogonAtivo().getUsuarioLogado() == null) {
//			sairSistema(false);
//		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == getJmiPessoas()) {
			if (pessoaIcon == null) {
				getJtPane().addTab("Cadastro de Pessoas", getTelaPessoas());
			} else {
				getJtPane().addTab("Cadastro de Pessoas", new ImageIcon(pessoaIcon), getTelaPessoas());
			}
		}

		if (e.getSource() == getJmiSobre()) {
			getSobreAuxPlot().setVisible(true);
		}

		if (e.getSource() == getJmiSair()) {
			sairSistema(true);
		}
	}

	public JTabbedPane getJtPane() {
		if (jtPane == null) {
			jtPane = new JTabbedPane(SwingConstants.TOP);
			jtPane.setVisible(true);	
		}
		return jtPane;
	}
	
	public JMenu getJmAjuda() {
		if (jmAjuda == null) {
			jmAjuda = new JMenu("Ajuda");
			jmAjuda.setMnemonic(KeyEvent.VK_A);
			jmAjuda.add(getJmiSobre());
		}
		return jmAjuda;
	}
	
	public JMenuItem getJmiSobre() {
		if (jmiSobre == null) {
			jmiSobre = new JMenuItem("Sobre");
			jmiSobre.setMnemonic(KeyEvent.VK_S);
			jmiSobre.addActionListener(this);
		}
		return jmiSobre;
	}

	public JMenuItem getJmiSair() {
		if (jmiSair == null) {
			jmiSair = new JMenuItem("Sair");
			jmiSair.setMnemonic(KeyEvent.VK_R);
			jmiSair.addActionListener(this);
		}
		return jmiSair;
	}
	
	public JSeparator getJsManutencao() {
		if (jsManutencao == null) {
			jsManutencao = new JSeparator();
		}
		return jsManutencao;
	}
	
	public JMenuItem getJmiPessoas() {
		if (jmiPessoas == null) {
			jmiPessoas = new JMenuItem("Pessoas");
			jmiPessoas.setMnemonic(KeyEvent.VK_P);
			jmiPessoas.addActionListener(this);
		}
		return jmiPessoas;
	}

	public JMenu getJmTabelas() {
		if (jmTabelas == null) {
			jmTabelas = new JMenu("Tabelas");
			jmTabelas.setMnemonic(KeyEvent.VK_T);
			jmTabelas.add(getJmiPessoas());
		}
		return jmTabelas;
	}
	
	public JMenu getJmManutencao() {
		if (jmManutencao == null) {
			jmManutencao = new JMenu("Manutenção");
			jmManutencao.setMnemonic(KeyEvent.VK_M);
			jmManutencao.add(getJmTabelas());
			jmManutencao.add(getJsManutencao());
			jmManutencao.add(getJmiSair());
		}
		return jmManutencao;
	}
	
	public JMenuBar getJmbPrincipal() {
		if (jmbPrincipal == null) {
			jmbPrincipal = new JMenuBar();
			jmbPrincipal.add(getJmManutencao());
			jmbPrincipal.add(getJmAjuda());
		}
		return jmbPrincipal;
	}
	
	public CadastroPessoas getTelaPessoas() {
		if (telaPessoas == null) {
			try {
				telaPessoas = new CadastroPessoas("Cadastro de Pessoas", this, jtPane);
			} catch (ErroAcessoRepositorioException e) {
				JOptionPane.showMessageDialog(this, "Não foi possível conectar o banco.\n"+e.getExcecaoInterna().getMessage(), " Atenção", JOptionPane.INFORMATION_MESSAGE);
				getJtPane().removeTabAt(getJtPane().getSelectedIndex());
				e.printStackTrace();
			}
			telaPessoas.setVisible(true);
		}
		return telaPessoas;
	}
	
	public Logon getLogonAtivo() {
		if (logonAtivo == null) {
			logonAtivo = new Logon(this);
		}
		return logonAtivo;
	}
	
	public Sobre getSobreAuxPlot() {
		if (sobreAuxPlot == null) {
			sobreAuxPlot = new Sobre(this);
		}
		return sobreAuxPlot;
	}	
	
	/*
	 * Realiza os procedimentos de saída do sistema.
	 */
	public void sairSistema(boolean confirmar) {
		if (!confirmar || JOptionPane
				.showConfirmDialog(
						this,
						"Tem certeza que deseja sair do sistema?",
						" Confirmar",
						JOptionPane.OK_CANCEL_OPTION) == 0) {
			System.exit(0);		
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				auxPlot telaPrincipal = new auxPlot();
				telaPrincipal.pack();
				telaPrincipal.setLocationRelativeTo(null);
				Dimension d = new Dimension(800, 600);				
				telaPrincipal.setMinimumSize(d);
				telaPrincipal.setSize(800, 600);
				telaPrincipal.setLocation(0, 0);
				telaPrincipal.setVisible(true);
			}
		});
	}
}
