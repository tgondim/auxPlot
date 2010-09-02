package br.com.tg.gui;

import br.com.tg.entidades.Pessoa;
import br.com.tg.exceptions.ErroAcessoRepositorioException;
import br.com.tg.exceptions.PessoaInexistenteException;
import br.com.tg.util.Fachada;


public class Principal {
	
	
	public static void main(String[] args) {
		try {
			Pessoa status = Fachada.obterInstancia().procurarPessoa(1);
			System.out.println(status.getNome());
		} catch (PessoaInexistenteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ErroAcessoRepositorioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		EnderecoDAO enderecoDAO = new EnderecoDAO();
//		StatusPessoaDAO statusPessoaDAO = new StatusPessoaDAO();
//		TelefoneDAO telefoneDAO = new TelefoneDAO();
//		TipoPessoaDAO tipoPessoaDAO = new TipoPessoaDAO();
//		TipoTelefoneDAO tipoTelefoneDAO = new TipoTelefoneDAO();
//		TipoUsuarioDAO tipoUsuarioDAO = new TipoUsuarioDAO();
//		RepositorioPessoaDAO repPessoaDAO = new RepositorioPessoaDAO();
//		
		
//		TipoTelefone tipoTelefone = tipoTelefoneDAO.getTipoTelefone(2);

//		Telefone telefone1 = new Telefone();
//		telefone1.setTipoTelefone(tipoTelefoneDAO.getTipoTelefone(2));
//		telefone1.setCodigoArea("81");
//		telefone1.setNumero("33333333");
//		Telefone telefone2 = new Telefone();
//		telefone2.setTipoTelefone(tipoTelefoneDAO.getTipoTelefone(1));
//		telefone2.setCodigoArea("81");
//		telefone2.setNumero("44444444");
//		telefoneDAO.salvar(telefone1);
//		telefoneDAO.salvar(telefone2);
		
//		List<Telefone> listaTelefones = new ArrayList<Telefone>();
//		listaTelefones.add(telefone1);
//		listaTelefones.add(telefone2);
		
//		StatusPessoa statusPessoa = statusPessoaDAO.getStatusPessoa(2);
//		statusPessoa.setId(2);
//		statusPessoa.setDescricao("Inativo");
		
//		TipoPessoa tipoPessoa = tipoPessoaDAO.getTipoPessoa(1);
//		tipoPessoa.setId(1);
//		tipoPessoa.setDescricao("Física");
		
//		TipoPessoa tipoPessoa1 = tipoPessoaDAO.getTipoPessoa(2);
//		tipoPessoa1.setId(2);
//		tipoPessoa1.setDescricao("Usuário");
		
//		TipoUsuario tipoUsuario = tipoUsuarioDAO.getTipoUsuario(2);
//		tipoUsuario.setId(2);
//		tipoUsuario.setDescricao("Comum");

//		Usuario usuario1 = usuarioDAO.getUsuario(1);
//		usuario1.getEndereco().setBairro("Boa Viagem");
//		usuarioDAO.salvar(usuario1);
		
//		Endereco endereco = new Endereco();
//		endereco.setLogradouro("Rua O de Almeida");
//		endereco.setNumero(54);
//		endereco.setBairro("Ali");
//		endereco.setComplemento("apt. 555");
//		endereco.setCidade("Belém");
//		endereco.setUf("PA");
//		endereco.setCep("51021-330");
//		endereco.setEmail("paulo@gmail.com");
//
//		Usuario usuario1 = new Usuario();
//		usuario1.setNome("Paulo Emilio");
//		usuario1.setLogin("Paulo");
//		usuario1.setSenha("123456");
//		usuario1.setEndereco(endereco);
//		usuario1.setTelefones(listaTelefones);
//		usuario1.setStatusPessoa(statusPessoaDAO.getStatusPessoa(2));
//		usuario1.setTipoPessoa(tipoPessoaDAO.getTipoPessoa(2));
//		usuario1.setTipoUsuario(tipoUsuarioDAO.getTipoUsuario(2));
//		usuario1.setDataCadastro(new Date());
//		usuario1.setDataAlteracao(new Date());
//	
//		usuarioDAO.salvar(usuario1);


//		PessoaFisica pessoaFisica = new PessoaFisica();
//		pessoaFisica.setNome("João Carlos Paes Mendonça");
//		pessoaFisica.setEndereco(endereco);
//		pessoaFisica.setCpf("12345678901");
//		usuario2.setLogin("joaoCarlos");
//		usuario2.setSenha("654321");
//		usuario2.setTipoUsuario(tstatusPessoaDAO.getStatusPessoa(2));
//		pessoaFisica.setTelefones(listaTelefones);
//		pessoaFisica.setStatusPessoa(statusPessoaDAO.getStatusPessoa(2));
//		pessoaFisica.setTipoPessoa(tipoPessoaDAO.getTipoPessoa(2));
//		pessoaFisica.setDataCadastro(new Date());
//		pessoaFisica.setUsuarioCadastro(usuarioDAO.getUsuario(1));
//		pessoaFisica.setUsuarioAlteracao(usuarioDAO.getUsuario(1));
//		pessoaFisica.setDataAlteracao(new Date());
//		pessoaFisicaDAO.salvar(pessoaFisica);
		
//		Usuario usuario = repPessoaDAO.getPessoa(1);
//		usuario.setNome("Paulinho da Viola");
//		usuario.getTelefones().get(1).setNumero("55555555");
//		for (Telefone t : usuario.getTelefones()) {
//			telefoneDAO.salvar(t);
//		}
//		repPessoaDAO.atualizar(usuario);
		
//		BarraCadastro barra = new BarraCadastro(15, 15);
//		auxPlot frame = new auxPlot();
//		JTabbedPane tabPane = new JTabbedPane();
//		TelaCadastroPessoas telaPessoas = new TelaCadastroPessoas();
//		try {
//			telaPessoas = new TelaCadastroPessoas("Cadastro de Pessoas", frame, tabPane);
//		} catch (ErroAcessoRepositorioException e) {
//			e.printStackTrace();
//		}
//		tabPane.addTab("Cadastro de Pessoas", telaPessoas);
//		frame.setSize(800, 600);
//		frame.add(tabPane);
//		frame.pack();
//		frame.setVisible(true);
		
//		String email = "tiago@gondim@gmail@com";
//		System.out.println(email + "   "  + (Validador.validaEmail(email) ? "Válido" : "Inválido"));
	}
}