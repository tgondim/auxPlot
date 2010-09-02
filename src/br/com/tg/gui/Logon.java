package br.com.tg.gui;

import java.awt.Dimension;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

import br.com.tg.entidades.Usuario;
import br.com.tg.exceptions.ErroAcessoRepositorioException;
import br.com.tg.guiUtil.FixedLengthDocument;
import br.com.tg.util.Fachada;

public class Logon extends JOptionPane {

	private static final long serialVersionUID = 1L;
	
	private final static int TENTATIVAS_LOGIN = 3;

	private JFrame owner;
	
	private JComboBox jcbUsuario;
	
	private JPasswordField jpfSenha;
	
	private Usuario usuarioLogado;
	
	private JLabel jlSenhaIncorreta;
	
	public Logon(JFrame newOwner) {
		super();
		this.owner = newOwner;
		setSize(new Dimension(400, 350));
		setVisible(true);
		String senhaDigitada;
		Boolean sair = false;
		Object[] mensagem = {"Usuário", getJcbUsuario(), "Senha", getJpfSenha(), getJlSenhaIncorreta() };
		int tentativas = 0;
		while ((tentativas < TENTATIVAS_LOGIN) && !sair) {
			int returnCode = JOptionPane.showConfirmDialog(owner, mensagem, "Digite a senha", 
					JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
			if (returnCode == JOptionPane.OK_OPTION) {
				senhaDigitada = String.valueOf(getJpfSenha().getPassword());
				Usuario usuario = (Usuario)getJcbUsuario().getSelectedItem();
				if (usuario.getSenha().equals(senhaDigitada)) {
					usuarioLogado = usuario;
					break;
				} else {
					getJlSenhaIncorreta().setText("Senha incorreta! Tente novamente.");
				}
			} else {
				sair = true;
			}		
			tentativas++;
		}
	}
	
	public JComboBox getJcbUsuario() {
		if (jcbUsuario == null) {
			try {
				Vector<Usuario> listaUsuarios = new Vector<Usuario>(Fachada.obterInstancia().listarUsuarios());
				jcbUsuario = new JComboBox();
				DefaultComboBoxModel defaultComboBox = new DefaultComboBoxModel(listaUsuarios);
				jcbUsuario.setModel(defaultComboBox);
				jcbUsuario.setSelectedIndex(0);
				jcbUsuario.grabFocus();
			} catch (ErroAcessoRepositorioException e1) {
				e1.printStackTrace();
			}
		}
		return jcbUsuario;
	}
	
	public JPasswordField getJpfSenha() {
		if (jpfSenha == null) {
			jpfSenha = new JPasswordField();
			jpfSenha.setDocument(new FixedLengthDocument(16));
		}
		return jpfSenha;
	}
	
	public JLabel getJlSenhaIncorreta() {
		if (jlSenhaIncorreta == null) {
			jlSenhaIncorreta = new JLabel();
		}
		return jlSenhaIncorreta;
	}
	
	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

}
