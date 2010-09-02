package br.com.tg.guiUtil;

import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JPanel;

import br.com.tg.util.ImagemUtil;

public class BarraCadastro extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private static final int BUSCANDO = 0;
	private static final int NAVEGANDO = 1;
	private static final int EDITANDO = 2;
	
	private ActionListener actionListener;
	
	private ActionEvent actionEvent;
	
	private int status;
	private int iconWidth;
	private int iconHeight;
	
	private JPanel jPConsulta;
	private JPanel jPNavegacao;
	
	private JButton jBBuscar;
	private JButton jBLimpar;
	private JButton jBSalvar;
	private JButton jBAdicionar;
	private JButton jBEditar;
	private JButton jBRemover;
	private JButton jBNavegarAcima;
	private JButton jBNavegarAbaixo;
	private JButton jBFechar;
	private JButton jBConfirmar;
	private JButton jBCancelar;
	private JButton jBSair;
	
	private String buscarIcon;
	private String confirmarIcon;
	private String cancelarIcon;
	private String limparIcon;
	private String salvarIcon;
	private String adicionarIcon;
	private String editarIcon;
	private String removerIcon;
	private String navegarAcimaIcon;
	private String navegarAbaixoIcon;
	private String sairIcon;
	
	private LayoutManager layout;

	public BarraCadastro() {
		super();
		iconWidth = 50;
		iconHeight = 50;
		initComponent();
	}

	public BarraCadastro(int newWidth, int newHeight) {
		super();
		iconWidth = newWidth;
		iconHeight = newHeight;
		initComponent();
	}

	private void initComponent() {
		File file = new File("auxPlot.properties");
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream(file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		buscarIcon = prop.getProperty("buscarIcon");
		confirmarIcon = prop.getProperty("confirmarIcon");
		cancelarIcon = prop.getProperty("cancelarIcon");
		limparIcon = prop.getProperty("limparIcon");
		salvarIcon = prop.getProperty("salvarIcon");
		adicionarIcon = prop.getProperty("adicionarIcon");
		editarIcon = prop.getProperty("editarIcon");
		removerIcon = prop.getProperty("removerIcon");
		navegarAbaixoIcon = prop.getProperty("navegarAbaixoIcon");
		navegarAcimaIcon = prop.getProperty("navegarAcimaIcon");
		sairIcon = prop.getProperty("sairIcon");
		add(getJPConsulta());
		add(getJPNavegacao());
		mudarStatus(NAVEGANDO);
	}

	public void mudarStatus(int newStatus) {
		if (newStatus == BUSCANDO) {
			getJPNavegacao().setVisible(false);
			getJPConsulta().setVisible(true);
			setStatus(BUSCANDO);
		} else  if (newStatus == NAVEGANDO){
			getJPConsulta().setVisible(false);
			getJPNavegacao().setVisible(true);
			setStatus(NAVEGANDO);
		} else if (newStatus == EDITANDO) {
			getJPConsulta().setVisible(true);
			getJPNavegacao().setVisible(false);
			setStatus(EDITANDO);
		}
	}

	public JPanel getJPConsulta() {
		if (jPConsulta == null) {
			jPConsulta = new JPanel();
			jPConsulta.setLayout(getLayout());
			jPConsulta.add(getJBConfirmar());
			jPConsulta.add(getJBCancelar());
			jPConsulta.add(getJBLimpar());
			jPConsulta.add(getJBSalvar());
			jPConsulta.add(getJBAdicionar());
			jPConsulta.add(getJBEditar());
			jPConsulta.add(getJBRemover());
			jPConsulta.add(getJBNavegarAcima());
			jPConsulta.add(getJBNavegarAbaixo());
			jPConsulta.add(getJBSair());
		}
		return jPConsulta;
	}

	public JPanel getJPNavegacao() {
		if (jPNavegacao == null) {
			jPNavegacao = new JPanel();
			jPNavegacao.setLayout(getLayout());
			jPNavegacao.add(getJBBuscar());
			jPNavegacao.add(getJBLimpar());
			jPNavegacao.add(getJBSalvar());
			jPNavegacao.add(getJBAdicionar());
			jPNavegacao.add(getJBEditar());
			jPNavegacao.add(getJBRemover());
			jPNavegacao.add(getJBNavegarAcima());
			jPNavegacao.add(getJBNavegarAbaixo());
			jPNavegacao.add(getJBSair());
		}
		return jPNavegacao;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public JButton getJBBuscar() {
		try {
			jBBuscar = new JButton(ImagemUtil.imagemEscalonada(buscarIcon, iconWidth, iconHeight));
		} catch (Exception e1) {
			jBBuscar = new JButton("Buscar");
		}
		jBBuscar.setToolTipText("Buscar registro");
		jBBuscar.setFocusable(false);
		jBBuscar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (getStatus() == NAVEGANDO) {
					mudarStatus(BUSCANDO);
					actionEvent = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "buscar");
					actionListener.actionPerformed (actionEvent);	
				}
			}
		});
		return jBBuscar;
	}

	public JButton getJBLimpar() {
		try {
			jBLimpar = new JButton(ImagemUtil.imagemEscalonada(limparIcon, iconWidth,
					iconHeight));
		} catch (Exception e1) {
			jBLimpar = new JButton("Limpar");
		}
		jBLimpar.setToolTipText("Limpar campos");
		jBLimpar.setFocusable(false);
		jBLimpar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				actionEvent = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "limpar");
				actionListener.actionPerformed (actionEvent);	
			}
		});
		return jBLimpar;
	}

	public JButton getJBSalvar() {
		try {
			jBSalvar = new JButton(ImagemUtil.imagemEscalonada(salvarIcon, iconWidth,
					iconHeight));
		} catch (Exception e1) {
			jBSalvar = new JButton("Salvar");
		}
		jBSalvar.setToolTipText("Salvar registro");
		jBSalvar.setFocusable(false);
		jBSalvar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (getStatus() == EDITANDO) {
					mudarStatus(NAVEGANDO);
					actionEvent = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "salvar");
					actionListener.actionPerformed (actionEvent);	
				}
			}
		});
		return jBSalvar;
	}

	public JButton getJBAdicionar() {
		try {
			jBAdicionar = new JButton(ImagemUtil.imagemEscalonada(adicionarIcon,
					iconWidth, iconHeight));
		} catch (Exception e1) {
			jBAdicionar = new JButton("Adicionar");
		}
		jBAdicionar.setToolTipText("Adicionar novo registro");
		jBAdicionar.setFocusable(false);
		jBAdicionar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (getStatus() == NAVEGANDO) {
					mudarStatus(EDITANDO);
					actionEvent = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "adicionar");
					actionListener.actionPerformed (actionEvent);	
				}
			}
		});
		return jBAdicionar;
	}

	public JButton getJBEditar() {
		try {
			jBEditar = new JButton(ImagemUtil.imagemEscalonada(editarIcon, iconWidth, iconHeight));
		} catch (Exception e1) {
			jBEditar = new JButton("Editar");
		}
		jBEditar.setToolTipText("Editar registro");
		jBEditar.setFocusable(false);
		jBEditar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (getStatus() == NAVEGANDO) {
					mudarStatus(EDITANDO);
					actionEvent = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "editar");
					actionListener.actionPerformed (actionEvent);	
				}
			}
		});
		return jBEditar;
	}

	public JButton getJBRemover() {
		try {
			jBRemover = new JButton(ImagemUtil.imagemEscalonada(removerIcon, iconWidth,
					iconHeight));
		} catch (Exception e1) {
			jBRemover = new JButton("Remover");
		}
		jBRemover.setToolTipText("Remover registro");
		jBRemover.setFocusable(false);
		jBRemover.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (getStatus() == NAVEGANDO) {
					actionEvent = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "remover");
					actionListener.actionPerformed (actionEvent);	
				}
			}
		});
		return jBRemover;
	}

	public JButton getJBNavegarAcima() {
		try {
			jBNavegarAcima = new JButton(ImagemUtil.imagemEscalonada(
					navegarAcimaIcon, iconWidth, iconHeight));
		} catch (Exception e1) {
			jBNavegarAcima = new JButton("Navegar Acima");
		}
		jBNavegarAcima.setToolTipText("Navegar registro acima");
		jBNavegarAcima.setFocusable(false);
		jBNavegarAcima.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (getStatus() == NAVEGANDO) {
					actionEvent = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "navegarAcima");
					actionListener.actionPerformed (actionEvent);	
				}
			}
		});
		return jBNavegarAcima;
	}

	public JButton getJBNavegarAbaixo() {
		try {
			jBNavegarAbaixo = new JButton(ImagemUtil.imagemEscalonada(
					navegarAbaixoIcon, iconWidth, iconHeight));
		} catch (Exception e1) {
			jBNavegarAbaixo = new JButton("Navegar Abaixo");
		}
		jBNavegarAbaixo.setToolTipText("Navegar registro abaixo");
		jBNavegarAbaixo.setFocusable(false);
		jBNavegarAbaixo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (getStatus() == NAVEGANDO) {
					actionEvent = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "navegarAbaixo");
					actionListener.actionPerformed (actionEvent);	
				}
			}
		});
		return jBNavegarAbaixo;
	}

	public JButton getJBFechar() {
		return jBFechar;
	}

	public JButton getJBConfirmar() {
		try {
			jBConfirmar = new JButton(ImagemUtil.imagemEscalonada(confirmarIcon,
					iconWidth, iconHeight));
		} catch (Exception e1) {
			jBConfirmar = new JButton("Confirmar");
		}
		jBConfirmar.setToolTipText("Confirmar dados");
		jBConfirmar.setFocusable(false);
		jBConfirmar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (getStatus() == BUSCANDO) {
					mudarStatus(NAVEGANDO);
					actionEvent = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "confirmar");
					actionListener.actionPerformed (actionEvent);	
				}
			}
		});
		return jBConfirmar;
	}

	public JButton getJBCancelar() {
		try {
			jBCancelar = new JButton(ImagemUtil.imagemEscalonada(cancelarIcon,
					iconWidth, iconHeight));
		} catch (Exception e1) {
			jBCancelar = new JButton("Cancelar");
		}
		jBCancelar.setToolTipText("Cancelar dados");
		jBCancelar.setFocusable(false);
		jBCancelar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (getStatus() == BUSCANDO || getStatus() == EDITANDO) {
					mudarStatus(NAVEGANDO);
					actionEvent = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "cancelar");
					actionListener.actionPerformed (actionEvent);	
				}
			}
		});
		return jBCancelar;
	}

	public JButton getJBSair() {
		try {
			jBSair = new JButton(ImagemUtil.imagemEscalonada(sairIcon, iconWidth,
					iconHeight));
		} catch (Exception e1) {
			jBSair = new JButton("Sair");
		}
		jBSair.setToolTipText("Sair");
		jBSair.setFocusable(false);
		jBSair.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				actionEvent = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "sair");
				actionListener.actionPerformed (actionEvent);	
			}
		});
		return jBSair;
	}

	public LayoutManager getLayout() {
		if (layout == null) {
			layout = new GridLayout();
		}
		return layout;
	}

	public void addActionListener(ActionListener newActionListener) {
		actionListener = newActionListener;
	}

}
