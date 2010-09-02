package br.com.tg.gui;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;
import java.util.Vector;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.MaskFormatter;
import javax.swing.text.PlainDocument;

import br.com.tg.entidades.Endereco;
import br.com.tg.entidades.Pessoa;
import br.com.tg.entidades.PessoaFisica;
import br.com.tg.entidades.PessoaJuridica;
import br.com.tg.entidades.StatusPessoa;
import br.com.tg.entidades.Telefone;
import br.com.tg.entidades.TipoTelefone;
import br.com.tg.entidades.TipoUsuario;
import br.com.tg.entidades.Usuario;
import br.com.tg.exceptions.ErroAcessoRepositorioException;
import br.com.tg.exceptions.PessoaInexistenteException;
import br.com.tg.exceptions.StatusPessoaInexistenteException;
import br.com.tg.guiUtil.BarraCadastro;
import br.com.tg.guiUtil.CalendarFormatter;
import br.com.tg.guiUtil.FixedLengthDocument;
import br.com.tg.guiUtil.PessoaTableModel;
import br.com.tg.guiUtil.StatusBarMessage;
import br.com.tg.guiUtil.Tela;
import br.com.tg.guiUtil.TelefoneTableModel;
import br.com.tg.guiUtil.jcbTipoTelefoneCellEditor;
import br.com.tg.guiUtil.jcbTipoTelefoneRenderer;
import br.com.tg.util.Conversor;
import br.com.tg.util.Fachada;
import br.com.tg.util.ImagemUtil;
import br.com.tg.util.Validador;

public class CadastroPessoas extends Tela {

	private static final long serialVersionUID = 1L;

	private JTabbedPane jtpPai;
	private JTabbedPane jtpEspecifico;

	private BarraCadastro barraCadastro;

	private JPanel jpGenerico;
	private JPanel jpPessoa;
	private JPanel jpEndereco;
	private JPanel jpTelefones;
	private JPanel jpStatusBar;
	private JPanel jpPessoaFisica;
	private JPanel jpPessoaJuridica;
	private JPanel jpUsuario;

	private JLabel jlId;
	private JLabel jlNome;
	private JLabel jlAtiva;
	private JLabel jlInativa;
	private JLabel jlSituacao;
	private JLabel jlStatusBar;
	private JLabel jlTipoPessoa;
	private JLabel jlNomeFantasia;
	private JLabel jlCnpj;
	private JLabel jlDiaFatura;
	private JLabel jlDataAbertura;
	private JLabel jlCpf;
	private JLabel jlDataNascimento;
	private JLabel jlLogin;
	private JLabel jlSenha;
	private JLabel jlTipoUsuario;
	private JLabel jlLogradouro;
	private JLabel jlNumero;
	private JLabel jlBairro;
	private JLabel jlComplemento;
	private JLabel jlCidade;
	private JLabel jlUf;
	private JLabel jlCep;
	private JLabel jlEmail;
	
	private JButton jbExcluir;
	private JButton jbAdicionar;

	private JTextField jtfId;
	private JTextField jtfNome;
	private JTextField jtfNomeFantasia;
	private JTextField jtfLogin;
	private JTextField jtfLogradouro;
	private JTextField jtfComplemento;
	private JTextField jtfBairro;
	private JTextField jtfCidade;
	private JTextField jtfUf;
	private JTextField jtfEmail;
	private JTextField jtfNumero;

	private JPasswordField jpfSenha;

	private JFormattedTextField jtfCep;
	private JFormattedTextField jtfCnpj;
	private JFormattedTextField jtfDiaFatura;
	private JFormattedTextField jtfDataAbertura;
	private JFormattedTextField jtfCpf;
	private JFormattedTextField jtfDataNascimento;

	private ButtonGroup bgSituacao;
	private ButtonGroup bgTipoPessoa;

	private JRadioButton jrbAtiva;
	private JRadioButton jrbInativa;
	private JRadioButton jrbFisica;
	private JRadioButton jrbJuridica;
	private JRadioButton jrbUsuario;

	private JComboBox jcbTipoUsuario;

	public static final String[] COLUNAS_TELEFONES = { "Tipo", "Área", "Número", "" };
	protected JTable tabelaTelefones;
	protected JScrollPane jspTabelaTelefones;
	protected TelefoneTableModel telefoneTableModel;

	public static final String[] COLUNAS_PESSOAS = { "Id", "Nome", "Tipo", "Logradouro", "Numero", "Bairro", "Cidade", "UF" };
	protected JTable tabelaPessoas;
	protected JScrollPane jspTabelaPessoas;
	protected PessoaTableModel pessoaTableModel;

	private Pessoa pessoaSelecionada;

	private String tabelaExcluirIcon;
	private String tabelaAdicionarIcon;
	
	private boolean editMode;
	private boolean addMode;
	
	public CadastroPessoas() {
		super();
	}

	public CadastroPessoas(String titulo, auxPlot newTelaPrincipal,
			JTabbedPane newPai) throws ErroAcessoRepositorioException {
		super(titulo, newTelaPrincipal);
		this.jtpPai = newPai;
		inicio();
	}

	public void inicio() throws ErroAcessoRepositorioException {

		File file = new File("auxPlot.properties");
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream(file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		tabelaExcluirIcon = prop.getProperty("tabelaExcluirIcon");
		tabelaAdicionarIcon = prop.getProperty("tabelaAdicionarIcon");
		setLayout(null);
		add(getBarraCadastro());
		add(getJpGenerico());
		add(getJtpEspecifico());
		add(getJspTabelaPessoas());
		addComponentListener(new ComponentListener() {

			@Override
			public void componentShown(ComponentEvent arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void componentResized(ComponentEvent arg0) {
				doResize();
			}

			@Override
			public void componentMoved(ComponentEvent arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void componentHidden(ComponentEvent arg0) {
				// TODO Auto-generated method stub
			}
		});
		atualizaTabelaPessoas();
		atualizaDadosTela();
		setEditMode(false);
	}

	public void atualizaTabelaPessoas() {
		getPessoaTableModel().clearTable();
		List<Pessoa> listaPessoas;
		try {
			listaPessoas = Fachada.obterInstancia().listarPessoas();
			if (listaPessoas != null) {
				for (Pessoa pessoa : listaPessoas) {
					getPessoaTableModel().addRow(pessoa);
				}
				getTabelaPessoas().repaint();
			}
		} catch (ErroAcessoRepositorioException e) {
			e.printStackTrace();
		} 
	}
	private void doResize() {
		getBarraCadastro().setBounds(00, 00, 500, 35);
		getJpGenerico().setBounds(10, 35, getSize().width - 20, 70);
		getJtpEspecifico().setBounds(00, 110, getSize().width, 170);
		getJspTabelaTelefones().setBounds((getSize().width - 300) / 2, 10, 270, 110);
		getJbAdicionar().setBounds((getSize().width - 300) / 2, 120, 20, 20);
		getJbExcluir().setBounds(((getSize().width - 300) / 2) + 20, 120, 20, 20);
		getJtfNome().setBounds(75, 40, getSize().width - 335, 20);
		getJlSituacao().setBounds(getSize().width - 200, 05, 90, 20);
		getJrbAtiva().setBounds(getSize().width - 200, 35, 80, 20);
		getJrbInativa().setBounds(getSize().width - 115, 35, 80, 20);
		getJpPessoaFisica().setBounds(115, 10, getSize().width - 130, 120);
		getJpPessoaJuridica().setBounds(115, 10, getSize().width - 130, 120);
		getJpUsuario().setBounds(115, 10, getSize().width - 130, 120);
		getJtfNomeFantasia().setBounds(140, 15, getSize().width - 310, 20);
		getJtfLogradouro().setBounds(110, 15, getSize().width - 155, 20);
		getJtfComplemento().setBounds(330, 45, getSize().width - 375, 20);
		getJtfEmail().setBounds(330, 105, getSize().width - 375, 20);
		getJtfCidade().setBounds(470, 75, getSize().width - 516, 20);
		getJspTabelaPessoas().setBounds(00, 280, getSize().width, getSize().height-300);
	}

	// carrega a tela com os dados do objeto pessoaSelecionada
	public void atualizaDadosTela() {
		Pessoa pessoa;
		if (getPessoaSelecionada().getClass() == PessoaJuridica.class) {
			pessoa = (PessoaJuridica) getPessoaSelecionada();
			getJrbJuridica().setSelected(true);
			getJrbJuridica().setEnabled(true);
			getJrbFisica().setEnabled(false);
			getJrbUsuario().setEnabled(false);
		} else if (getPessoaSelecionada().getClass() == Usuario.class) {
			pessoa = (Usuario) getPessoaSelecionada();
			getJrbUsuario().setSelected(true);
			getJrbUsuario().setEnabled(true);
			getJrbJuridica().setEnabled(false);
			getJrbFisica().setEnabled(false);
		} else {
			pessoa = (PessoaFisica) getPessoaSelecionada();
			if (pessoa.getId() != null) {
				getJrbFisica().setSelected(true);
				getJrbFisica().setEnabled(true);
				getJrbJuridica().setEnabled(false);
				getJrbUsuario().setEnabled(false);
			}
		}
		getJtfId().setText(pessoa.getId() != null ? pessoa.getId().toString() : "");
		getJtfNome().setText(pessoa.getNome());
		int status = pessoa.getStatusPessoa() != null ? pessoa.getStatusPessoa().getId() : 0;
		if (status == 1) {
			getJrbAtiva().setSelected(true);
		} else if (status == 2) {
			getJrbInativa().setSelected(true);
		}
		CalendarFormatter dataFormat = new CalendarFormatter();
		if (pessoa.getClass() == PessoaFisica.class) {
			getJtfCpf().setText(((PessoaFisica) pessoa).getCpf());
			Calendar auxDataNascimento = ((PessoaFisica) pessoa).getDataNascimento();
			if (auxDataNascimento != null) {
				getJtfDataNascimento().setText(dataFormat.format(auxDataNascimento));
			} else {
				getJtfDataNascimento().setText("");
			}
		} else if (pessoa.getClass() == PessoaJuridica.class) {
			getJtfNomeFantasia().setText(
					((PessoaJuridica) pessoa).getNomeFantasia());
			getJtfCnpj().setText(((PessoaJuridica) pessoa).getCnpj());
			getJtfDiaFatura().setText(
					String.valueOf(((PessoaJuridica) pessoa).getDiaFatura()));
			Calendar auxDataAbertura = ((PessoaJuridica) pessoa)
					.getDataAbertura();
			if (auxDataAbertura != null) {
				getJtfDataAbertura().setText(dataFormat.format(auxDataAbertura));
			} else {
				getJtfDataAbertura().setText("");
			}
		} else if (pessoa.getClass() == Usuario.class) {
			getJtfLogin().setText(((Usuario) pessoa).getLogin());
			getJtfSenha().setText(((Usuario) pessoa).getSenha());
			getJcbTipoUsuario().setSelectedIndex(((Usuario)pessoa).getTipoUsuario().getId() - 1);
		}
		if (pessoa.getEndereco() != null) {
			getJtfLogradouro().setText(pessoa.getEndereco().getLogradouro());
			if (pessoa.getEndereco().getNumero() != null) {
				getJtfNumero().setText(pessoa.getEndereco().getNumero().toString());
			}
			getJtfComplemento().setText(pessoa.getEndereco().getComplemento());
			getJtfBairro().setText(pessoa.getEndereco().getBairro());
			getJtfCidade().setText(pessoa.getEndereco().getCidade());
			getJtfUf().setText(pessoa.getEndereco().getUf());
			getJtfCep().setText(pessoa.getEndereco().getCep());
			getJtfEmail().setText(pessoa.getEndereco().getEmail());
		}
		getTelefoneTableModel().clearTable();
		List <Telefone> listaTelefones = pessoa.getTelefones(); 
		if (listaTelefones != null) {
			for (Telefone tel : listaTelefones) {
				getTelefoneTableModel().addRow(tel);
			}
			getTabelaTelefones().repaint();
		}
	}

	// atualiza o objeto pessoaSelecionada com os dados da tela
	public void atualizaDadosObjeto() {
		//dados do jpGenerico
		if ((getPessoaSelecionada().getId() != null) && (getJtfId().getText().trim() != null)) {
			getPessoaSelecionada().setId(Integer.parseInt(getJtfId().getText().trim()));
		}
		getPessoaSelecionada().setNome(getJtfNome().getText());
		StatusPessoa status = new StatusPessoa();
		try {
			if (getJrbAtiva().isSelected()) {
				status = Fachada.obterInstancia().procurarStatusPessoa(1);
			} else if (getJrbInativa().isSelected()) {
				status = Fachada.obterInstancia().procurarStatusPessoa(2);
			}
		} catch (StatusPessoaInexistenteException e) {
			e.printStackTrace();
		} catch (ErroAcessoRepositorioException e) {
			e.printStackTrace();
		}
		getPessoaSelecionada().setStatusPessoa(status);
		//dados do jpEspecifico
		//pessoa fisica
		if (getJrbFisica().isSelected()) {
			((PessoaFisica) getPessoaSelecionada()).setCpf(Validador.unmask(getJtfCpf().getText()));
			try {
				((PessoaFisica) getPessoaSelecionada()).setDataNascimento(Conversor.stringToCalendar(getJtfDataNascimento().getText()));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			try {
				getPessoaSelecionada().setTipoPessoa(Fachada.obterInstancia().procurarTipoPessoa(1));
			} catch (Exception e) {
				e.printStackTrace();
			}
			//pessoa juridica
		} else if (getJrbJuridica().isSelected()) {
			((PessoaJuridica) getPessoaSelecionada()).setNomeFantasia(getJtfNomeFantasia().getText());
			((PessoaJuridica) getPessoaSelecionada()).setCnpj(Validador.unmask(getJtfCnpj().getText()));
			((PessoaJuridica) getPessoaSelecionada()).setDiaFatura(Short.parseShort(getJtfDiaFatura().getText()));
			try {
				((PessoaJuridica) getPessoaSelecionada()).setDataAbertura(Conversor.stringToCalendar(getJtfDataAbertura().getText()));
				getPessoaSelecionada().setTipoPessoa(Fachada.obterInstancia().procurarTipoPessoa(2));
			} catch (Exception e) {
				e.printStackTrace();
			}
			//Usuario
		} else if (getJrbUsuario().isSelected()) {
			((Usuario) getPessoaSelecionada()).setLogin(getJtfLogin().getText());
			((Usuario) getPessoaSelecionada()).setSenha(getJtfSenha().getPassword().toString());
			TipoUsuario tipoUsuario = (TipoUsuario)getJcbTipoUsuario().getSelectedItem();
			((Usuario) getPessoaSelecionada()).setTipoUsuario(tipoUsuario);
			try {
				getPessoaSelecionada().setTipoPessoa(Fachada.obterInstancia().procurarTipoPessoa(3));
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}
		//dados do jpEndereco
		Endereco endereco = new Endereco();
		endereco.setLogradouro(getJtfLogradouro().getText());
		if (!getJtfNumero().getText().isEmpty()) {
			endereco.setNumero(Integer.parseInt(getJtfNumero().getText().trim()));
		}
		endereco.setComplemento(getJtfComplemento().getText());
		endereco.setBairro(getJtfBairro().getText());
		endereco.setCidade(getJtfCidade().getText());
		endereco.setUf(getJtfUf().getText());
		endereco.setCep(getJtfCep().getText());
		endereco.setEmail(getJtfEmail().getText());
		endereco.setPessoa(getPessoaSelecionada());
		getPessoaSelecionada().setEndereco(endereco);
		List<Telefone> telefones = getTelefoneTableModel().getList();
		for (Telefone tel : telefones) {
			tel.setPessoa(getPessoaSelecionada());
		}
		getPessoaSelecionada().setTelefones(telefones);
	}

	public void limpaCampos() {
		getJtfId().setText("");
		getJtfNome().setText("");
		getJtfNomeFantasia().setText("");
		getJtfLogin().setText("");
		getJtfSenha().setText("");
		getJtfLogradouro().setText("");
		getJtfComplemento().setText("");
		getJtfBairro().setText("");
		getJtfCidade().setText("");
		getJtfUf().setText("");
		getJtfEmail().setText("");
		getJtfNumero().setText("");
		getJtfCep().setText("");
		getJtfCnpj().setText("");
		getJtfDiaFatura().setText("");
		getJtfDataAbertura().setText("");
		getJtfCpf().setText("");
		getJtfDataNascimento().setText("");
		getTelefoneTableModel().clearTable();
		getTabelaTelefones().repaint();
		getJrbFisica().setEnabled(true);
		getJrbJuridica().setEnabled(true);
		getJrbUsuario().setEnabled(true);
		getJrbFisica().setSelected(true);
		getJrbAtiva().setSelected(true);
	}

	public JPanel getJpGenerico() {
		if (jpGenerico == null) {
			Border etched = BorderFactory.createEtchedBorder();
			jpGenerico = new JPanel();
			jpGenerico.setBorder(etched);
			jpGenerico.setLayout(null);
			jpGenerico.setFocusable(false);
			jpGenerico.add(getJlId());
			getJlId().setBounds(25, 10, 30, 20);
			jpGenerico.add(getJtfId());
			getJtfId().setBounds(75, 10, 50, 20);
			jpGenerico.add(getJlNome());
			getJlNome().setBounds(25, 40, 40, 20);
			jpGenerico.add(getJtfNome());
			jpGenerico.add(getJlSituacao());
			jpGenerico.add(getJrbAtiva());
			jpGenerico.add(getJrbInativa());
			getBgSituacao();
			ActionListener alSituacao = new ActionListener() {
				public void actionPerformed(ActionEvent actionEvent) {
					AbstractButton aButton = (AbstractButton) actionEvent
							.getSource();
					if (aButton.getText().equals("Ativa")) {
						System.out.println("pessoa ativa");
					} else {
						System.out.println("pessoa Inativa");
					}
				}
			};
			getJrbAtiva().addActionListener(alSituacao);
			getJrbInativa().addActionListener(alSituacao);
		}
		return jpGenerico;
	}

	public BarraCadastro getBarraCadastro() {
		if (barraCadastro == null) {
			barraCadastro = new BarraCadastro(15, 15);
			barraCadastro.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					String acao = e.getActionCommand();
					if (acao.equals("sair")) {
						getJtpPai().getSelectedComponent().setVisible(false);
						getJtpPai().removeTabAt(getJtpPai().getSelectedIndex());
					} else if (acao.equals("confirmar")) {
						try {
							setPessoaSelecionada(Fachada.obterInstancia().procurarPessoa(Integer.parseInt(getJtfId().getText().trim())));
						} catch (Exception e1) {
							JOptionPane.showMessageDialog(
											getParent(),
											"Nenhuma pessoa foi encontrada.",
											" Atenção!",
											JOptionPane.INFORMATION_MESSAGE);
						}
						atualizaDadosTela();
						setEditMode(false);
					} else if (acao.equals("limpar")) {
						if (isEditMode()) {
							limpaCampos();
						}
					} else if (acao.equals("salvar")) {
						if (isEditMode()) {
							int linhaSelecionada = getPessoaTableModel().getRow(getPessoaSelecionada());
							atualizaDadosObjeto();
							try {
								Fachada.obterInstancia().atualizarPessoa(getPessoaSelecionada());
							} catch (PessoaInexistenteException e1) {
								e1.printStackTrace();
							} catch (ErroAcessoRepositorioException e2) {
								e2.printStackTrace();
							}
							atualizaTabelaPessoas();
							getTabelaPessoas().getSelectionModel().setSelectionInterval(linhaSelecionada, linhaSelecionada);
							setEditMode(false);
						}
					} else if (acao.equals("buscar")) {
						if (!isEditMode()) {
							setEditMode(true);
							limpaCampos();
						}
					} else if (acao.equals("cancelar")) {
						if (isEditMode()) {
							setEditMode(false);
							limpaCampos();
						}
					} else if (acao.equals("adicionar")) {
						if (!isEditMode()) {
							setEditMode(true);
							limpaCampos();
						}
					} else if (acao.equals("editar")) {
						if (!isEditMode() && (getPessoaSelecionada() != null)) {
							setEditMode(true);
						}
					} else if (acao.equals("remover")) {
						if (getPessoaSelecionada().getId() != null && !isEditMode()) {
							Object[] mensagem = { "Confirma exclusão do registro?" };
							int returnCode = JOptionPane.showConfirmDialog(
									getParent(), mensagem, "Atenção!",
									JOptionPane.OK_CANCEL_OPTION,
									JOptionPane.INFORMATION_MESSAGE);
							if (returnCode == JOptionPane.OK_OPTION) {
								try {
									Fachada.obterInstancia().descadastrarPessoa(getPessoaSelecionada());
									atualizaTabelaPessoas();
								} catch (PessoaInexistenteException e1) {
									e1.printStackTrace();
								} catch (ErroAcessoRepositorioException e1) {
									e1.printStackTrace();
								}
							} 
						}
					} else if (acao.equals("navegarAcima")) {
						if (!isEditMode()) {
							int linhaSelecionada = getPessoaTableModel().getRow(getPessoaSelecionada());
							if (linhaSelecionada > 0 ) {
								getTabelaPessoas().getSelectionModel().setSelectionInterval( linhaSelecionada-1, linhaSelecionada-1 );
								atualizaDadosTela();
							}
						}
					} else if (acao.equals("navegarAbaixo")) {
						if (!isEditMode()) {
							int linhaSelecionada = getPessoaTableModel().getRow(getPessoaSelecionada());
							if (linhaSelecionada < getPessoaTableModel().getRowCount()-1) {
								getTabelaPessoas().getSelectionModel().setSelectionInterval( linhaSelecionada+1, linhaSelecionada+1 );
								atualizaDadosTela();
							}
						}
					}
				}
			});
		}
		return barraCadastro;
	}

	public JTabbedPane getJtpEspecifico() {
		if (jtpEspecifico == null) {
			jtpEspecifico = new JTabbedPane(SwingConstants.TOP);
			jtpEspecifico.setVisible(true);
			jtpEspecifico.addTab("Pessoa", getJpPessoa());
			jtpEspecifico.addTab("Endereco", getJpEndereco());
			jtpEspecifico.addTab("Telefones", getJpTelefones());
		}
		return jtpEspecifico;
	}

	public JPanel getJpPessoa() {
		if (jpPessoa == null) {
			jpPessoa = new JPanel();
			jpPessoa.setLayout(null);
			jpPessoa.setFocusable(false);
			jpPessoa.add(getJlTipoPessoa());
			getJlTipoPessoa().setBounds(5, 10, 90, 20);
			jpPessoa.add(getJrbFisica());
			getJrbFisica().setBounds(15, 40, 80, 20);
			jpPessoa.add(getJrbJuridica());
			getJrbJuridica().setBounds(15, 70, 80, 20);
			jpPessoa.add(getJrbUsuario());
			getJrbUsuario().setBounds(15, 100, 80, 20);
			jpPessoa.add(getJpPessoaFisica());
			jpPessoa.add(getJpPessoaJuridica());
			jpPessoa.add(getJpUsuario());
			getJpPessoaFisica().setVisible(true);
			getJpPessoaJuridica().setVisible(false);
			getJpUsuario().setVisible(false);
			getBgTipoPessoa();
			ChangeListener clTipoPessoa = new ChangeListener() {
				public void stateChanged(ChangeEvent changeEvent) {
					AbstractButton aButton = (AbstractButton) changeEvent
							.getSource();
					if (aButton.getText().equals("Física")
							&& aButton.isSelected()) {
						getJpPessoaFisica().setVisible(true);
						getJpPessoaJuridica().setVisible(false);
						getJpUsuario().setVisible(false);
						setPessoaSelecionada(new PessoaFisica());
					} else if (aButton.getText().equals("Jurídica")
							&& aButton.isSelected()) {
						getJpPessoaFisica().setVisible(false);
						getJpPessoaJuridica().setVisible(true);
						getJpUsuario().setVisible(false);
						setPessoaSelecionada(new PessoaJuridica());
					} else if (aButton.getText().equals("Usuário")
							&& aButton.isSelected()) {
						getJpPessoaFisica().setVisible(false);
						getJpPessoaJuridica().setVisible(false);
						getJpUsuario().setVisible(true);
						setPessoaSelecionada(new Usuario());
					}
				}
			};
			getJrbFisica().addChangeListener(clTipoPessoa);
			getJrbJuridica().addChangeListener(clTipoPessoa);
			getJrbUsuario().addChangeListener(clTipoPessoa);
		}
		return jpPessoa;
	}

	public JPanel getJpEndereco() {
		if (jpEndereco == null) {
			jpEndereco = new JPanel();
			jpEndereco.setLayout(null);
			jpEndereco.setFocusable(false);
			jpEndereco.add(getJlLogradouro());
			getJlLogradouro().setBounds(00, 15, 100, 20);
			jpEndereco.add(getJtfLogradouro());
			jpEndereco.add(getJlNumero());
			getJlNumero().setBounds(00, 45, 100, 20);
			jpEndereco.add(getJtfNumero());
			getJtfNumero().setBounds(110, 45, 40, 20);
			jpEndereco.add(getJlComplemento());
			getJlComplemento().setBounds(220, 45, 100, 20);
			jpEndereco.add(getJtfComplemento());
			jpEndereco.add(getJlBairro());
			getJlBairro().setBounds(00, 75, 100, 20);
			jpEndereco.add(getJtfBairro());
			getJtfBairro().setBounds(110, 75, 280, 20);
			jpEndereco.add(getJlCidade());
			getJlCidade().setBounds(410, 75, 50, 20);
			jpEndereco.add(getJtfCidade());
			jpEndereco.add(getJlUf());
			getJlUf().setBounds(00, 105, 100, 20);
			jpEndereco.add(getJtfUf());
			getJtfUf().setBounds(110, 105, 25, 20);
			jpEndereco.add(getJlCep());
			getJlCep().setBounds(125, 105, 50, 20);
			jpEndereco.add(getJtfCep());
			getJtfCep().setBounds(185, 105, 65, 20);
			jpEndereco.add(getJlEmail());
			getJlEmail().setBounds(267, 105, 50, 20);
			jpEndereco.add(getJtfEmail());
		}
		return jpEndereco;
	}

	public JPanel getJpTelefones() {
		if (jpTelefones == null) {
			jpTelefones = new JPanel();
			jpTelefones.setLayout(null);
			jpTelefones.setFocusable(false);
			jpTelefones.add(getJspTabelaTelefones());
			jpTelefones.add(getJbAdicionar());
			jpTelefones.add(getJbExcluir());

		}
		return jpTelefones;
	}

	public JPanel getJpPessoaFisica() {
		if (jpPessoaFisica == null) {
			Border etched = BorderFactory.createEtchedBorder();
			jpPessoaFisica = new JPanel();
			jpPessoaFisica.setBorder(etched);
			jpPessoaFisica.setLayout(null);
			jpPessoaFisica.setFocusable(false);
			jpPessoaFisica.add(getJlCpf());
			getJlCpf().setBounds(20, 35, 120, 20);
			jpPessoaFisica.add(getJtfCpf());
			getJtfCpf().setBounds(150, 35, 100, 20);
			jpPessoaFisica.add(getJlDataNascimento());
			getJlDataNascimento().setBounds(20, 65, 120, 20);
			jpPessoaFisica.add(getJtfDataNascimento());
			getJtfDataNascimento().setBounds(150, 65, 70, 20);
		}
		return jpPessoaFisica;
	}

	public JPanel getJpPessoaJuridica() {
		if (jpPessoaJuridica == null) {
			Border etched = BorderFactory.createEtchedBorder();
			jpPessoaJuridica = new JPanel();
			jpPessoaJuridica.setBorder(etched);
			jpPessoaJuridica.setLayout(null);
			jpPessoaJuridica.setFocusable(false);
			jpPessoaJuridica.add(getJlNomeFantasia());
			getJlNomeFantasia().setBounds(20, 15, 110, 20);
			jpPessoaJuridica.add(getJtfNomeFantasia());
			jpPessoaJuridica.add(getJlCnpj());
			getJlCnpj().setBounds(20, 45, 110, 20);
			jpPessoaJuridica.add(getJtfCnpj());
			getJtfCnpj().setBounds(140, 45, 120, 20);
			jpPessoaJuridica.add(getJlDiaFatura());
			getJlDiaFatura().setBounds(20, 75, 110, 20);
			jpPessoaJuridica.add(getJtfDiaFatura());
			getJtfDiaFatura().setBounds(140, 75, 20, 20);
			jpPessoaJuridica.add(getJlDataAbertura());
			getJlDataAbertura().setBounds(260, 75, 110, 20);
			jpPessoaJuridica.add(getJtfDataAbertura());
			getJtfDataAbertura().setBounds(380, 75, 70, 20);
		}
		return jpPessoaJuridica;
	}

	public JPanel getJpUsuario() {
		if (jpUsuario == null) {
			Border etched = BorderFactory.createEtchedBorder();
			jpUsuario = new JPanel();
			jpUsuario.setBorder(etched);
			jpUsuario.setLayout(null);
			jpUsuario.setFocusable(false);
			jpUsuario.add(getJlLogin());
			getJlLogin().setBounds(20, 15, 110, 20);
			jpUsuario.add(getJtfLogin());
			getJtfLogin().setBounds(140, 15, 150, 20);
			jpUsuario.add(getJlSenha());
			getJlSenha().setBounds(20, 45, 110, 20);
			jpUsuario.add(getJtfSenha());
			getJtfSenha().setBounds(140, 45, 120, 20);
			jpUsuario.add(getJlTipoUsuario());
			getJlTipoUsuario().setBounds(20, 75, 110, 20);
			jpUsuario.add(getJcbTipoUsuario());
			getJcbTipoUsuario().setBounds(140, 75, 150, 20);
		}
		return jpUsuario;
	}

	public JPanel getJpStatusBar() {
		if (jpStatusBar == null) {
			jpStatusBar = new JPanel();
			jpStatusBar.add(getJlStatusBar());
			jpStatusBar.setFocusable(false);
		}
		return jpStatusBar;
	}

	public JLabel getJlStatusBar() {
		if (jlStatusBar == null) {
			jlStatusBar = new JLabel();
			jlStatusBar.setFocusable(false);
			jlStatusBar.setToolTipText("Barra de Status");
			jlStatusBar.setHorizontalAlignment(JLabel.LEFT);
			jlStatusBar.setBounds(0, getMaximumSize().height - 30,
					getMaximumSize().width, 20);
		}
		return jlStatusBar;
	}

	public JTextField getJtfId() {
		if (jtfId == null) {
			jtfId = new JTextField();
			jtfId.setEditable(false);
		}
		return jtfId;
	}

	public JTextField getJtfNome() {
		if (jtfNome == null) {
			jtfNome = new JTextField();
			jtfNome.setDocument(new FixedLengthDocument(120));
		}
		return jtfNome;
	}

	public JRadioButton getJrbAtiva() {
		if (jrbAtiva == null) {
			jrbAtiva = new JRadioButton("Ativa");
			jrbAtiva.setMnemonic(KeyEvent.VK_A);
			jrbAtiva.setSelected(true);
		}
		return jrbAtiva;
	}

	public JRadioButton getJrbInativa() {
		if (jrbInativa == null) {
			jrbInativa = new JRadioButton("Inativa");
			jrbInativa.setMnemonic(KeyEvent.VK_I);
			jrbInativa.setSelected(false);
		}
		return jrbInativa;
	}

	public ButtonGroup getBgSituacao() {
		if (bgSituacao == null) {
			bgSituacao = new ButtonGroup();
			bgSituacao.add(getJrbAtiva());
			bgSituacao.add(getJrbInativa());
		}
		return bgSituacao;
	}

	public JTextField getJtfNomeFantasia() {
		if (jtfNomeFantasia == null) {
			jtfNomeFantasia = new JTextField();
			jtfNomeFantasia.setDocument(new FixedLengthDocument(100));
		}
		return jtfNomeFantasia;
	}

	public JTextField getJtfLogin() {
		if (jtfLogin == null) {
			jtfLogin = new JTextField();
			jtfLogin.setDocument(new FixedLengthDocument(20));
		}
		return jtfLogin;
	}

	public JPasswordField getJtfSenha() {
		if (jpfSenha == null) {
			jpfSenha = new JPasswordField();
			jpfSenha.setDocument(new FixedLengthDocument(16));
		}
		return jpfSenha;
	}

	public JFormattedTextField getJtfCnpj() {
		if (jtfCnpj == null) {
			jtfCnpj = new JFormattedTextField();
			try {
				jtfCnpj = new JFormattedTextField(new MaskFormatter(
						"##.###.###/####-##"));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			jtfCnpj.addFocusListener(new FocusListener() {

				@Override
				public void focusLost(FocusEvent e) {
					if (!jtfCnpj.getText().equals(".   .   /    -")
							&& !jtfCnpj.getText().equals("  .   .   /    -  ")) {
						if (!Validador.validaCNPJ(jtfCnpj.getText())) {
							StatusBarMessage.say("CNPJ inválido.", 4,
									getJlStatusBar());
							jtfCnpj.grabFocus();
						}
					} else {
						jtfCnpj.setValue(null);
					}
				}

				@Override
				public void focusGained(FocusEvent e) {

				}
			});
		}
		return jtfCnpj;
	}

	public JFormattedTextField getJtfDiaFatura() {
		if (jtfDiaFatura == null) {
			jtfDiaFatura = new JFormattedTextField();
			try {
				jtfDiaFatura = new JFormattedTextField(new MaskFormatter("##"));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			jtfDiaFatura.addFocusListener(new FocusListener() {

				@Override
				public void focusLost(FocusEvent e) {
					if (!jtfDiaFatura.getText().trim().equals("")) {
						int dia = Integer.parseInt(jtfDiaFatura.getText());
						if ((dia < 1) && (dia > 31)) {
							StatusBarMessage.say("Dia da fatura inválido.", 4,
									getJlStatusBar());
							jtfDiaFatura.grabFocus();
						}
					} else {
						jtfDiaFatura.setValue(null);
					}
				}

				@Override
				public void focusGained(FocusEvent e) {

				}
			});
		}
		return jtfDiaFatura;
	}

	public JFormattedTextField getJtfDataAbertura() {
		if (jtfDataAbertura == null) {
			jtfDataAbertura = new JFormattedTextField();
			try {
				jtfDataAbertura = new JFormattedTextField(new MaskFormatter(
						"##-##-####"));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			jtfDataAbertura.addFocusListener(new FocusListener() {

				@Override
				public void focusLost(FocusEvent e) {
					if (!jtfDataAbertura.getText().equals("  -  -    ")) {
						if (!Validador.validaData(jtfDataAbertura.getText())) {
							StatusBarMessage.say("Data inválida.", 4,
									getJlStatusBar());
							jtfDataAbertura.grabFocus();
						}
					} else {
						jtfDataAbertura.setValue(null);
					}
				}

				@Override
				public void focusGained(FocusEvent e) {

				}
			});
		}
		return jtfDataAbertura;
	}

	public JFormattedTextField getJtfDataNascimento() {
		if (jtfDataNascimento == null) {
			jtfDataNascimento = new JFormattedTextField();
			try {
				jtfDataNascimento = new JFormattedTextField(new MaskFormatter(
						"##-##-####"));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			jtfDataNascimento.addFocusListener(new FocusListener() {

				@Override
				public void focusLost(FocusEvent e) {
					if (!jtfDataNascimento.getText().equals("  -  -    ")) {
						if (!Validador.validaData(jtfDataNascimento.getText())) {
							StatusBarMessage.say("Data inválida.", 4,
									getJlStatusBar());
							jtfDataNascimento.grabFocus();
						}
					} else {
						jtfDataNascimento.setValue(null);
					}
				}

				@Override
				public void focusGained(FocusEvent e) {

				}
			});
		}
		return jtfDataNascimento;
	}

	public JFormattedTextField getJtfCpf() {
		if (jtfCpf == null) {
			jtfCpf = new JFormattedTextField();
			try {
				jtfCpf = new JFormattedTextField(new MaskFormatter(
						"###.###.###-##"));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			jtfCpf.addFocusListener(new FocusListener() {

				@Override
				public void focusLost(FocusEvent e) {
					if (!jtfCpf.getText().equals(".   .   -")
							&& !jtfCpf.getText().equals("   .   .   -  ")) {
						if (!Validador.validaCPF(jtfCpf.getText())) {
							StatusBarMessage.say("CPF inválido.", 4,
									getJlStatusBar());
							jtfCpf.grabFocus();
						}
					} else {
						jtfCpf.setValue(null);
					}
				}

				@Override
				public void focusGained(FocusEvent e) {
					// TODO Auto-generated method stub

				}
			});
		}
		return jtfCpf;
	}

	public JComboBox getJcbTipoUsuario() {
		if (jcbTipoUsuario == null) {
			try {
				Vector<TipoUsuario> listaTiposUsuarios = new Vector<TipoUsuario>(Fachada.obterInstancia().listarTiposUsuarios());
				jcbTipoUsuario = new JComboBox();
				DefaultComboBoxModel defaultComboBox = new DefaultComboBoxModel(listaTiposUsuarios);
				jcbTipoUsuario.setModel(defaultComboBox);
				jcbTipoUsuario.setSelectedIndex(-1);
			} catch (ErroAcessoRepositorioException e1) {
				e1.printStackTrace();
			}
		}
		return jcbTipoUsuario;
	}

	public ButtonGroup getBgTipoPessoa() {
		if (bgTipoPessoa == null) {
			bgTipoPessoa = new ButtonGroup();
			bgTipoPessoa.add(getJrbFisica());
			bgTipoPessoa.add(getJrbJuridica());
			bgTipoPessoa.add(getJrbUsuario());
		}
		return bgTipoPessoa;
	}

	public JRadioButton getJrbFisica() {
		if (jrbFisica == null) {
			jrbFisica = new JRadioButton("Física");
			jrbFisica.setMnemonic(KeyEvent.VK_F);
			jrbFisica.setSelected(true);
		}
		return jrbFisica;
	}

	public JRadioButton getJrbJuridica() {
		if (jrbJuridica == null) {
			jrbJuridica = new JRadioButton("Jurídica");
			jrbJuridica.setMnemonic(KeyEvent.VK_J);
			jrbJuridica.setSelected(true);
		}
		return jrbJuridica;
	}

	public JRadioButton getJrbUsuario() {
		if (jrbUsuario == null) {
			jrbUsuario = new JRadioButton("Usuário");
			jrbUsuario.setMnemonic(KeyEvent.VK_U);
			jrbUsuario.setSelected(true);
		}
		return jrbUsuario;
	}

	public JTextField getJtfLogradouro() {
		if (jtfLogradouro == null) {
			jtfLogradouro = new JTextField();
			jtfLogradouro.setDocument(new FixedLengthDocument(100));
		}
		return jtfLogradouro;
	}

	@SuppressWarnings("serial")
	public JTextField getJtfNumero() {
		if (jtfNumero == null) {
			jtfNumero = new JTextField();
			jtfNumero.setDocument(new PlainDocument() {
				@Override
				public void insertString( int offs, String str, AttributeSet a ) throws BadLocationException {  
					for( int i = 0; i < str.length(); i++ )  
						if( Character.isDigit( str.charAt( i ) ) == false ) { 
							return;  
						}
					    super.insertString( offs, str, a );  
					}  
			});
		}
		return jtfNumero;
	}

	public JTextField getJtfComplemento() {
		if (jtfComplemento == null) {
			jtfComplemento = new JTextField();
			jtfComplemento.setDocument(new FixedLengthDocument(40));
		}
		return jtfComplemento;
	}

	public JTextField getJtfBairro() {
		if (jtfBairro == null) {
			jtfBairro = new JTextField();
			jtfBairro.setDocument(new FixedLengthDocument(40));
		}
		return jtfBairro;
	}

	public JTextField getJtfCidade() {
		if (jtfCidade == null) {
			jtfCidade = new JTextField();
			jtfCidade.setDocument(new FixedLengthDocument(40));
		}
		return jtfCidade;
	}

	public JTextField getJtfUf() {
		if (jtfUf == null) {
			jtfUf = new JTextField();
			jtfUf.setDocument(new FixedLengthDocument(2));
		}
		return jtfUf;
	}

	public JTextField getJtfEmail() {
		if (jtfEmail == null) {
			jtfEmail = new JTextField();
			jtfEmail.setDocument(new FixedLengthDocument(100));
			jtfEmail.addFocusListener(new FocusListener() {

				@Override
				public void focusLost(FocusEvent e) {
					if (!jtfEmail.getText().trim().equals("")) {
						if (!Validador.validaEmail(jtfEmail.getText())) {
							StatusBarMessage.say("E-mail inválido.", 4,
									getJlStatusBar());
							jtfEmail.grabFocus();
						}
					} else {
						jtfEmail.setText(null);
					}
				}

				@Override
				public void focusGained(FocusEvent e) {
					// TODO Auto-generated method stub

				}
			});
		}
		return jtfEmail;
	}

	public JFormattedTextField getJtfCep() {
		if (jtfCep == null) {
			jtfCep = new JFormattedTextField();
			try {
				jtfCep = new JFormattedTextField(new MaskFormatter("#####-###"));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return jtfCep;
	}

	public JLabel getJlId() {
		if (jlId == null) {
			jlId = new JLabel("Id", JLabel.RIGHT);
		}
		return jlId;
	}

	public JLabel getJlNome() {
		if (jlNome == null) {
			jlNome = new JLabel("Nome", JLabel.RIGHT);
		}
		return jlNome;
	}

	public JLabel getJlSituacao() {
		if (jlSituacao == null) {
			jlSituacao = new JLabel("Situação", JLabel.LEFT);
		}
		return jlSituacao;
	}

	public JLabel getJlAtiva() {
		if (jlAtiva == null) {
			jlAtiva = new JLabel("Ativa", JLabel.RIGHT);
		}
		return jlAtiva;
	}

	public JLabel getJlInativa() {
		if (jlInativa == null) {
			jlInativa = new JLabel("Inativa", JLabel.RIGHT);
		}
		return jlInativa;
	}

	public JLabel getJlTipoPessoa() {
		if (jlTipoPessoa == null) {
			jlTipoPessoa = new JLabel("Tipo de Pessoa", JLabel.RIGHT);
		}
		return jlTipoPessoa;
	}

	public JLabel getJlNomeFantasia() {
		if (jlNomeFantasia == null) {
			jlNomeFantasia = new JLabel("Nome de Fantasia", JLabel.RIGHT);
		}
		return jlNomeFantasia;
	}

	public JLabel getJlCnpj() {
		if (jlCnpj == null) {
			jlCnpj = new JLabel("CNPJ", JLabel.RIGHT);
		}
		return jlCnpj;
	}

	public JLabel getJlDiaFatura() {
		if (jlDiaFatura == null) {
			jlDiaFatura = new JLabel("Dia da Fatura", JLabel.RIGHT);
		}
		return jlDiaFatura;
	}

	public JLabel getJlDataAbertura() {
		if (jlDataAbertura == null) {
			jlDataAbertura = new JLabel("Data de Abertura", JLabel.RIGHT);
		}
		return jlDataAbertura;
	}

	public JLabel getJlCpf() {
		if (jlCpf == null) {
			jlCpf = new JLabel("CPF", JLabel.RIGHT);
		}
		return jlCpf;
	}

	public JLabel getJlLogin() {
		if (jlLogin == null) {
			jlLogin = new JLabel("Login", JLabel.RIGHT);
		}
		return jlLogin;
	}

	public JLabel getJlSenha() {
		if (jlSenha == null) {
			jlSenha = new JLabel("Senha", JLabel.RIGHT);
		}
		return jlSenha;
	}

	public JLabel getJlTipoUsuario() {
		if (jlTipoUsuario == null) {
			jlTipoUsuario = new JLabel("Tipo de Usuario", JLabel.RIGHT);
		}
		return jlTipoUsuario;
	}

	public JLabel getJlDataNascimento() {
		if (jlDataNascimento == null) {
			jlDataNascimento = new JLabel("Data de Nascimento", JLabel.RIGHT);
		}
		return jlDataNascimento;
	}

	public JLabel getJlLogradouro() {
		if (jlLogradouro == null) {
			jlLogradouro = new JLabel("Logradouro", JLabel.RIGHT);
		}
		return jlLogradouro;
	}

	public JLabel getJlNumero() {
		if (jlNumero == null) {
			jlNumero = new JLabel("Numero", JLabel.RIGHT);
		}
		return jlNumero;
	}

	public JLabel getJlComplemento() {
		if (jlComplemento == null) {
			jlComplemento = new JLabel("Complemento", JLabel.RIGHT);
		}
		return jlComplemento;
	}

	public JLabel getJlBairro() {
		if (jlBairro == null) {
			jlBairro = new JLabel("Bairro", JLabel.RIGHT);
		}
		return jlBairro;
	}

	public JLabel getJlCidade() {
		if (jlCidade == null) {
			jlCidade = new JLabel("Cidade", JLabel.RIGHT);
		}
		return jlCidade;
	}

	public JLabel getJlUf() {
		if (jlUf == null) {
			jlUf = new JLabel("Estado", JLabel.RIGHT);
		}
		return jlUf;
	}

	JLabel getJlEmail() {
		if (jlEmail == null) {
			jlEmail = new JLabel("E-mail", JLabel.RIGHT);
		}
		return jlEmail;
	}

	public JLabel getJlCep() {
		if (jlCep == null) {
			jlCep = new JLabel("CEP", JLabel.RIGHT);
		}
		return jlCep;
	}

	public JButton getJbExcluir() {
		if (jbExcluir == null) {
			try {
				jbExcluir = new JButton(ImagemUtil.imagemEscalonada(
						tabelaExcluirIcon, 15, 15));
				jbExcluir.setToolTipText("Excluir telefone selecionado");
				jbExcluir.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent arg0) {
						int linha;
						if ((linha = getTabelaTelefones().getSelectedRow()) != 0) {
							getTabelaTelefones().setRowSelectionInterval(linha - 1, linha -1);
						}
						int row = getTabelaTelefones().getSelectedRow();
						if (row >= 0) {
							getTelefoneTableModel().removeRow(row);
							getTabelaTelefones().repaint();						
						}
					}
				});
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return jbExcluir;
	}

	public JButton getJbAdicionar() {
		if (jbAdicionar == null) {
			try {
				jbAdicionar = new JButton(ImagemUtil.imagemEscalonada(tabelaAdicionarIcon, 15, 15));
				jbAdicionar.setToolTipText("Adicionar telefone");
				jbAdicionar.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						getTelefoneTableModel().addEmptyRow();
					}
				});
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return jbAdicionar;
	}

	public PessoaTableModel getPessoaTableModel() {
		if (pessoaTableModel == null) {
			pessoaTableModel = new PessoaTableModel(COLUNAS_PESSOAS);
			pessoaTableModel.addTableModelListener(new TableModelListener() {
				
				@Override
				public void tableChanged(TableModelEvent evt) {
					if (evt.getType() == TableModelEvent.UPDATE) {
						int column = evt.getColumn();
						int row = evt.getFirstRow();
						getTabelaPessoas().setColumnSelectionInterval(
								column + 1, column + 1);
						getTabelaPessoas().setRowSelectionInterval(row, row);
					}
				}
			});
		}
		return pessoaTableModel;
	}

	public JTable getTabelaPessoas() {
		if (tabelaPessoas == null) {
			tabelaPessoas = new JTable();
			tabelaPessoas.setModel(getPessoaTableModel());
			tabelaPessoas.setSurrendersFocusOnKeystroke(true);
			tabelaPessoas.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_LAST_COLUMN);
			tabelaPessoas.addKeyListener(new KeyListener() {				

				@Override
				public void keyTyped(KeyEvent e) {
					// TODO Auto-generated method stub

				}

				@Override
				public void keyReleased(KeyEvent e) {
					if (e.getKeyCode() == KeyEvent.VK_UP
							|| e.getKeyCode() == KeyEvent.VK_DOWN
							|| e.getKeyCode() == KeyEvent.VK_KP_UP
							|| e.getKeyCode() == KeyEvent.VK_KP_DOWN) {
						atualizaDadosTela();
					}
				}

				@Override
				public void keyPressed(KeyEvent e) {
				}
			});
			tabelaPessoas.addMouseListener(new MouseListener() {

				@Override
				public void mouseClicked(MouseEvent e) {
				}

				@Override
				public void mouseEntered(MouseEvent e) {
				}

				@Override
				public void mouseExited(MouseEvent e) {
				}

				@Override
				public void mousePressed(MouseEvent e) {
				}

				@Override
				public void mouseReleased(MouseEvent e) {
					if (getTabelaPessoas().isEnabled()) {
						atualizaDadosTela();
					}
				}
			});
			TableColumn colunaId = getTabelaPessoas().getColumnModel().getColumn(PessoaTableModel.ID_INDEX);
			colunaId.setMinWidth(50);
			colunaId.setPreferredWidth(50);
			colunaId.setMaxWidth(50);
			
			TableColumn colunaNome = getTabelaPessoas().getColumnModel().getColumn(PessoaTableModel.NOME_INDEX);
			colunaNome.setMinWidth(200);
			colunaNome.setPreferredWidth(400);
			colunaNome.setMaxWidth(400);
			
			TableColumn colunaTipoPessoa = getTabelaPessoas().getColumnModel().getColumn(PessoaTableModel.TIPO_PESSOA_INDEX);
			colunaTipoPessoa.setMinWidth(60);
			colunaTipoPessoa.setPreferredWidth(60);
			colunaTipoPessoa.setMaxWidth(60);
			
			TableColumn colunaLogradouro = getTabelaPessoas().getColumnModel().getColumn(PessoaTableModel.LOGRADOURO_INDEX);
			colunaLogradouro.setMinWidth(200);
			colunaLogradouro.setPreferredWidth(400);
			colunaLogradouro.setMaxWidth(400);
			
			TableColumn colunaNumero = getTabelaPessoas().getColumnModel().getColumn(PessoaTableModel.NUMERO_INDEX);
			colunaNumero.setMinWidth(45);
			colunaNumero.setPreferredWidth(45);
			colunaNumero.setMaxWidth(45);
			
			TableColumn colunaBairro = getTabelaPessoas().getColumnModel().getColumn(PessoaTableModel.BAIRRO_INDEX);
			colunaBairro.setMinWidth(95);
			colunaBairro.setPreferredWidth(220);
			colunaBairro.setMaxWidth(220);
			
			TableColumn colunaCidade = getTabelaPessoas().getColumnModel().getColumn(PessoaTableModel.CIDADE_INDEX);
			colunaCidade.setMinWidth(95);
			colunaCidade.setPreferredWidth(220);
			colunaCidade.setMaxWidth(220);
			
			TableColumn colunaUf = getTabelaPessoas().getColumnModel().getColumn(PessoaTableModel.UF_INDEX);
			colunaUf.setMinWidth(30);
			colunaUf.setPreferredWidth(30);
			colunaUf.setMaxWidth(30);
		}
		return tabelaPessoas;
	}
	
	private class PessoaCellRenderer extends DefaultTableCellRenderer {
		
		private static final long serialVersionUID = 1L;
		
		protected int selectedColumn;
		
		public PessoaCellRenderer(int newHiddenColumn) {
			this.selectedColumn = newHiddenColumn;
		}
		
		@Override
		public Component getTableCellRendererComponent(JTable table,
				Object value, boolean isSelected, boolean hasFocus, int row, int column) {
			Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
			if (column == selectedColumn && hasFocus) {
				highlightLastRow(row, getPessoaTableModel(), getTabelaPessoas());
			}
			return c;
		}
	}
	
	public JScrollPane getJspTabelaPessoas() {
		if (jspTabelaPessoas == null) {
			jspTabelaPessoas = new JScrollPane(getTabelaPessoas());
		}
		return jspTabelaPessoas;
	}

	public void highlightLastRow(int row, TableModel tableModel, JTable jTable) {
		int lastrow = tableModel.getRowCount();
		if (row == lastrow - 1) {
			jTable.setRowSelectionInterval(lastrow - 1,
					lastrow - 1);
		} else {
			jTable.setRowSelectionInterval(row + 1, row + 1);
		}
		jTable.setColumnSelectionInterval(0, 0);
	}
	
	public TelefoneTableModel getTelefoneTableModel() {
		if (telefoneTableModel == null) {
			telefoneTableModel = new TelefoneTableModel(COLUNAS_TELEFONES);
			telefoneTableModel.addTableModelListener(new TableModelListener() {

				@Override
				public void tableChanged(TableModelEvent evt) {
					if (evt.getType() == TableModelEvent.UPDATE) {
						int column = evt.getColumn();
						int row = evt.getFirstRow();
						getTabelaTelefones().setColumnSelectionInterval(
								column + 1, column + 1);
						getTabelaTelefones().setRowSelectionInterval(row, row);
					}
				}
			});
		}
		return telefoneTableModel;
	}

	public JTable getTabelaTelefones() {
		if (tabelaTelefones == null) {
			tabelaTelefones = new JTable();
			tabelaTelefones.setModel(getTelefoneTableModel());
			tabelaTelefones.setSurrendersFocusOnKeystroke(true);
			tabelaTelefones.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_LAST_COLUMN);
			
			TableColumn colunaTipo = getTabelaTelefones().getColumnModel().getColumn(TelefoneTableModel.TIPO_TELEFONE_INDEX);
			Vector<TipoTelefone> listaTipoTelefone;
			try {
				listaTipoTelefone = new Vector<TipoTelefone>(Fachada.obterInstancia().listarTiposTelefones());
				colunaTipo.setCellEditor(new jcbTipoTelefoneCellEditor(listaTipoTelefone));
				colunaTipo.setCellRenderer(new jcbTipoTelefoneRenderer(listaTipoTelefone));
			} catch (ErroAcessoRepositorioException e) {
				e.printStackTrace();
			}
			colunaTipo.setMinWidth(160);
			colunaTipo.setPreferredWidth(160);
			colunaTipo.setMaxWidth(160);
			
			TableColumn colunaCodigoArea = getTabelaTelefones().getColumnModel().getColumn(TelefoneTableModel.CODIGO_AREA_INDEX);
			colunaCodigoArea.setMinWidth(40);
			colunaCodigoArea.setPreferredWidth(40);
			colunaCodigoArea.setMaxWidth(40);
			
			TableColumn colunaNumero = getTabelaTelefones().getColumnModel().getColumn(TelefoneTableModel.NUMERO_INDEX);
			colunaNumero.setMinWidth(75);
			colunaNumero.setPreferredWidth(75);
			colunaNumero.setMaxWidth(75);
		}
		return tabelaTelefones;
	}

	private class TelefoneCellRenderer extends DefaultTableCellRenderer {

		private static final long serialVersionUID = 1L;

		protected int selectedColumn;

		public TelefoneCellRenderer(int newHiddenColumn) {
			this.selectedColumn = newHiddenColumn;
		}

		@Override
		public Component getTableCellRendererComponent(JTable table,
				Object value, boolean isSelected, boolean hasFocus, int row, int column) {
			Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
			if (column == selectedColumn && hasFocus) {
				highlightLastRow(row, getTelefoneTableModel(), getTabelaTelefones());
			}
			return c;
		}
	}

	public JScrollPane getJspTabelaTelefones() {
		if (jspTabelaTelefones == null) {
			jspTabelaTelefones = new JScrollPane(getTabelaTelefones());
		}
		return jspTabelaTelefones;
	}

	public JTabbedPane getJtpPai() {
		return this.jtpPai;
	}

	public Pessoa getPessoaSelecionada() {
		if (pessoaSelecionada == null) {
			pessoaSelecionada = new PessoaFisica();
		}
		if (getTabelaPessoas().getSelectedRow() != -1 && getTabelaPessoas().getRowCount() > 0 ) {
			pessoaSelecionada = getPessoaTableModel().getList().get(getTabelaPessoas().getSelectedRow());
		}
		return pessoaSelecionada;
	}

	public void setPessoaSelecionada(Pessoa newPessoaSelecionada) {
		pessoaSelecionada = newPessoaSelecionada;
	}

//	public boolean isAddMode() {
//		return addMode;
//	}
//
//	public void setAddMode(boolean addMode) {
//		if (addMode) {
//			setEditMode(true);
//		} else {
//			setEditMode(false);
//		}
//		this.addMode = addMode;
//	}

	public boolean isEditMode() {
		return editMode;
	}

	public void setEditMode(boolean editMode) {
		if (editMode) {
			getJtfNome().setEditable(true);
			getJrbAtiva().setEnabled(true);
			getJrbInativa().setEnabled(true);
			getJrbFisica().setEnabled(true);
			getJrbJuridica().setEnabled(true);
			getJrbUsuario().setEnabled(true);
			getJtfCpf().setEditable(true);
			getJtfDataNascimento().setEditable(true);
			getJtfNomeFantasia().setEditable(true);
			getJtfCnpj().setEditable(true);
			getJtfDiaFatura().setEditable(true);
			getJtfDataAbertura().setEditable(true);
			getJtfLogin().setEditable(true);
			getJtfSenha().setEditable(true);
			getJcbTipoUsuario().setEnabled(true);
			getJtfLogradouro().setEditable(true);
			getJtfNumero().setEditable(true);
			getJtfComplemento().setEditable(true);
			getJtfBairro().setEditable(true);
			getJtfCidade().setEditable(true);
			getJtfUf().setEditable(true);
			getJtfCep().setEditable(true);
			getJtfEmail().setEditable(true);
			getJbAdicionar().setEnabled(true);
			getJbExcluir().setEnabled(true);
			getTabelaTelefones().setEnabled(true);
			getJtfNome().grabFocus();
		} else {
			getJtfNome().setEditable(false);
			getJrbAtiva().setEnabled(false);
			getJrbInativa().setEnabled(false);
			getJrbFisica().setEnabled(false);
			getJrbJuridica().setEnabled(false);
			getJrbUsuario().setEnabled(false);
			getJtfCpf().setEditable(false);
			getJtfDataNascimento().setEditable(false);
			getJtfNomeFantasia().setEditable(false);
			getJtfCnpj().setEditable(false);
			getJtfDiaFatura().setEditable(false);
			getJtfDataAbertura().setEditable(false);
			getJtfLogin().setEditable(false);
			getJtfSenha().setEditable(false);
			getJcbTipoUsuario().setEnabled(false);
			getJtfLogradouro().setEditable(false);
			getJtfNumero().setEditable(false);
			getJtfComplemento().setEditable(false);
			getJtfBairro().setEditable(false);
			getJtfCidade().setEditable(false);
			getJtfUf().setEditable(false);
			getJtfCep().setEditable(false);
			getJtfEmail().setEditable(false);
			getJbAdicionar().setEnabled(false);
			getJbExcluir().setEnabled(false);
			getTabelaTelefones().setEnabled(false);
			this.grabFocus();
		}
		this.editMode = editMode;
	}
	
	
}