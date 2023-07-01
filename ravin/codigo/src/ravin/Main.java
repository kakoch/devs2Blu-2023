package ravin;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static ravin.utilidade.ConstantesTextos.*;

import javax.swing.JOptionPane;

import ravin.controladores.ClienteController;
import ravin.controladores.FuncionarioController;
import ravin.enumeradores.Cargo;
import ravin.enumeradores.Escolaridade;
import ravin.enumeradores.EstadoCivil;
import ravin.enumeradores.StatusComanda;
import ravin.enumeradores.StatusMesa;
import ravin.enumeradores.StatusPreparoPedido;
import ravin.enumeradores.TipoProduto;
import ravin.modelos.Cliente;
import ravin.modelos.Comanda;
import ravin.modelos.Funcionario;
import ravin.modelos.Mesa;
import ravin.modelos.Pedido;
import ravin.modelos.Pessoa;
import ravin.modelos.Produto;
import ravin.utilidade.ConstantesTextos.*;
import ravin.utilidade.UtilitarioData;

/**
 * 
 * @author mmichelluzzi
 * 
 *         Funcionalidades que devem ser contempladas no desenvolvimento do
 *         projeto
 * 
 *         1 - Funcionario Cadastrar Alterar Excluir Consultar Listar Todas
 *         Consultar Garçons Disponíveis 2 - Cliente Cadastrar Alterar Excluir
 *         Consultar Listar Todas 3 - Produto Cadastrar Alterar Excluir
 *         Consultar Listar Todas 4 - Cardapio Cadastrar Alterar Excluir
 *         Consultar Listar Todas 5 - Mesa Cadastrar Alterar Excluir Consultar
 *         Listar Todas Consultar Mesas Disponíveis Reservar Mesa Listar Mesas
 *         Por Status 6 - Pedido Cadastrar Alterar Excluir Consultar Listar
 *         Todas Realizar pedido Consultar Status do pedido Listar Comandas Por
 *         Status Fechar Comanda Cancelar Pedido
 *
 */

public class Main {

	private static FuncionarioController funcionarioController = new FuncionarioController();
	private static ClienteController clienteController = new ClienteController();
	private static AtomicInteger idCounter = new AtomicInteger();

	public static void main(String[] args) {

		boolean executando = true;
		int opcaoSelecionada = 0;

		while (executando) {
			opcaoSelecionada = Integer.parseInt(JOptionPane.showInputDialog(montarMenuPrincipal()));

			switch (opcaoSelecionada) {
			case 1:
				int opcao = Integer.parseInt(JOptionPane.showInputDialog(montarSubMenuFuncionarios()));
				operacaoFuncionario(opcao, funcionarioController);
				break;
			case 2:
				int opcaoC = Integer.parseInt(JOptionPane.showInputDialog(montarSubMenuClientes()));
				operacaoCliente(opcaoC, clienteController);
				break;
			case 3:
				// Chamar menu produto
				break;
			case 4:
				// Chamar menu cardapio
				break;
			case 5:
				// Chamar menu mesa
				break;
			case 6:
				// Chamar menu pedido
				break;
			case 7:
				executando = false;
				break;
			default:
				JOptionPane.showMessageDialog(null, "Escolha uma opção válida");
				break;
			}
		}

	}

	// funcionario
	private static void operacaoFuncionario(int opcao, FuncionarioController funcionarioController) {
		Funcionario funcionario = null;
		List<Funcionario> funcionarios;

		int id;
		switch (opcao) {
		case 1: // cadastrar Funcionario
			funcionario = mostrarMenuCadastrarFuncionario();

			try {// inserir
				funcionarioController.salvar(funcionario);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			}

			break;
		case 2: // alterar

			funcionarios = funcionarioController.listarTodos();
			int idFuncionarioAlterar = mostrarMenuLerIdFuncionarioAlterar(funcionarios);
			Funcionario funcionarioAlterar = funcionarioController.consultar(idFuncionarioAlterar);
			mostrarMenuAlterarFuncionario(funcionarioAlterar);
			try {
				funcionarioController.salvar(funcionarioAlterar);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
			break;

		case 3: // excluir
			id = mostrarMenuExcluirFuncionario();
			funcionarioController.excluir(id);
			break;

		case 4: // consultar
			id = mostrarMenuConsultarFuncionario();
			Funcionario funcionarioBuscado = funcionarioController.consultar(id);
			JOptionPane.showMessageDialog(null, funcionarioBuscado);
			break;

		case 5:
			funcionarios = funcionarioController.listarTodos();
			listarFuncionarios(funcionarios);
			break;

		case 6:
			funcionarios = funcionarioController.listarGarconsDisponiveis();
			listarGarconsDisponiveis(funcionarios);
			break;

		default:

		}
	}

	private static int mostrarMenuLerIdFuncionarioAlterar(List<Funcionario> funcionarios) {

		var builder = new StringBuilder();

		builder.append(TextUtils.CABECALHO_TRACO + TextFuncionarios.CABECALHO_TITULO_FUNCIONARIOS + TextUtils.CABECALHO_TRACO);
		builder.append(TextUtils.QUEBRA_LINHA);

		for (Funcionario funcionario : funcionarios) {
			builder.append(funcionario.getId());
			builder.append(" - ");
			builder.append(funcionario.getNome());
			builder.append(TextUtils.QUEBRA_LINHA);
		}

		builder.append(TextFuncionarios.ALTERAR_FUNCIONARIO_ID);

		return Integer.parseInt(JOptionPane.showInputDialog(builder.toString()));
	}

	private static void listarGarconsDisponiveis(List<Funcionario> funcionarios) {
		StringBuilder builder = new StringBuilder();

		builder.append(TextUtils.CABECALHO_TRACO + TextFuncionarios.CABECALHO_TITULO_GARCOM + TextUtils.CABECALHO_TRACO);
		builder.append(TextUtils.QUEBRA_LINHA);

		for (Funcionario funcionario : funcionarios) {
			builder.append(funcionario);
			builder.append(TextUtils.QUEBRA_LINHA);
		}

		JOptionPane.showMessageDialog(null, builder.toString());
	}

	private static void listarFuncionarios(List<Funcionario> funcionarios) {
		StringBuilder builder = new StringBuilder();

		builder.append(TextUtils.CABECALHO_TRACO + TextFuncionarios.CABECALHO_TITULO_FUNCIONARIOS + TextUtils.CABECALHO_TRACO);
		builder.append(TextUtils.QUEBRA_LINHA);

		for (Funcionario funcionario : funcionarios) {
			builder.append(funcionario);
			builder.append(TextUtils.QUEBRA_LINHA);
		}

		JOptionPane.showMessageDialog(null, builder.toString());
	}

	private static int mostrarMenuConsultarFuncionario() {
		return Integer.parseInt(JOptionPane.showInputDialog("Digite o id do funcionário que você deseja consultar"));
	}

	private static int mostrarMenuExcluirFuncionario() {
		return Integer.parseInt(JOptionPane.showInputDialog(TextFuncionarios.ALTERAR_FUNCIONARIO_EXCLUIR));
	}

	private static Funcionario mostrarMenuAlterarFuncionario(Funcionario funcionarioAlterar) {
		funcionarioAlterar.setRg(JOptionPane.showInputDialog(TextFuncionarios.ALTERAR_FUNCIONARIO_RG, 
				funcionarioAlterar.getRg()));
		funcionarioAlterar.setEstadoCivil(EstadoCivil.values()[Integer.parseInt(JOptionPane.showInputDialog(
				TextFuncionarios.ALTERAR_FUNCIONARIO_ESTADO_CIVIL, 
				funcionarioAlterar.getEstadoCivil().ordinal()))]);
		funcionarioAlterar.setCargo(Cargo.values()[Integer.parseInt(JOptionPane.showInputDialog(TextFuncionarios.ALTERAR_FUNCIONARIO_CARGO,
				funcionarioAlterar.getCargo().ordinal()))]);
		funcionarioAlterar.setEscolaridade(Escolaridade.values()[Integer.parseInt(JOptionPane.showInputDialog(
				TextFuncionarios.ALTERAR_FUNCIONARIO_ESCOLARIDADE, 
				funcionarioAlterar.getEscolaridade().ordinal()))]);
		funcionarioAlterar.setPis(Integer.parseInt(JOptionPane.showInputDialog(TextFuncionarios.ALTERAR_FUNCIONARIO_PIS, 
				funcionarioAlterar.getPis())));

		return funcionarioAlterar;
	}

	public static Funcionario mostrarMenuCadastrarFuncionario() {

		Pessoa pessoa = cadastrarPessoa();
		Funcionario funcionario = new Funcionario();
		funcionario.setRg(JOptionPane.showInputDialog(TextFuncionarios.ALTERAR_FUNCIONARIO_RG));
		funcionario.setEstadoCivil(EstadoCivil.values()[Integer.parseInt(JOptionPane.showInputDialog(TextFuncionarios.ALTERAR_FUNCIONARIO_ESTADO_CIVIL))]);
		funcionario.setCargo(Cargo.values()[Integer.parseInt(JOptionPane.showInputDialog(TextFuncionarios.ALTERAR_FUNCIONARIO_CARGO))]);
		funcionario.setEscolaridade(Escolaridade.values()[Integer.parseInt(JOptionPane.showInputDialog(TextFuncionarios.ALTERAR_FUNCIONARIO_ESCOLARIDADE))]);
		funcionario.setPis(Integer.parseInt(JOptionPane.showInputDialog(TextFuncionarios.ALTERAR_FUNCIONARIO_PIS)));
		funcionario.setDataAdmissao(new Date());
		funcionario.setNome(pessoa.getNome());
		funcionario.setId(pessoa.getId());
		funcionario.setCpf(pessoa.getCpf());
		funcionario.setEndereco(pessoa.getEndereco());
		funcionario.setDataNascimento(pessoa.getDataNascimento());
		funcionario.setOberservacao(pessoa.getOberservacao());
		funcionario.setTelefone(pessoa.getTelefone());

		return funcionario;
	}

	public static String montarSubMenuFuncionarios() {
		String subMenuGeral = montarSubMenuGeral(TextFuncionarios.CABECALHO_TITULO_FUNCIONARIOS);

		StringBuilder builder = new StringBuilder();
		builder.append(subMenuGeral);
		builder.append(TextMenu.OPCAO_SEIS + TextFuncionarios.CONSULTAR_FUNCIONARIO_GARCOM_DIPOSNIVEL + TextUtils.QUEBRA_LINHA);
		builder.append(TextMenu.OPCAO_SETE + TextMenu.OPCAO_VOLTAR);

		return builder.toString();
	}

	// Cliente
	private static void operacaoCliente(int opcao, ClienteController clienteController) {
		Cliente cliente = null;
		List<Cliente> clientes;

		int id;
		switch (opcao) {
		case 1: // cadastrar Funcionario
			cliente = mostrarMenuCadastrarCliente();

			try {// inserir
				clienteController.salvar(cliente);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			}

			break;
		case 2: // alterar

			clientes = clienteController.listarTodos();
			int idClienteAlterar = mostrarMenuLerIdClienteAlterar(clientes);
			Cliente clienteAlterar = clienteController.consultar(idClienteAlterar);
			mostrarMenuAlterarCliente(clienteAlterar);
			try {
				clienteController.salvar(clienteAlterar);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
			break;

		case 3: // excluir
			id = mostrarMenuExcluirCliente();
			clienteController.excluir(id);
			break;

		case 4: // consultar
			id = mostrarMenuConsultarCliente();
			Cliente clienteBuscado = clienteController.consultar(id);
			JOptionPane.showMessageDialog(null, clienteBuscado);
			break;

		case 5:
			clientes = clienteController.listarTodos();
			listarCliente(clientes);
			break;
		default:

		}
	}

	private static int mostrarMenuLerIdClienteAlterar(List<Cliente> clientes) {

		var builder = new StringBuilder();

		builder.append(TextUtils.CABECALHO_TRACO + TextCliente.CABECALHO_TITULO_CLIENTES + TextUtils.CABECALHO_TRACO);
		builder.append(TextUtils.QUEBRA_LINHA);

		for (Cliente cliente : clientes) {
			builder.append(cliente.getId());
			builder.append(" - ");
			builder.append(cliente.getNome());
			builder.append(TextUtils.QUEBRA_LINHA);
		}

		//builder.append();

		return Integer.parseInt(JOptionPane.showInputDialog(builder.toString()));
	}

	private static void listarCliente(List<Cliente> clientes) {
		StringBuilder builder = new StringBuilder();

		builder.append(TextUtils.CABECALHO_TRACO + TextCliente.CABECALHO_TITULO_CLIENTES + TextUtils.CABECALHO_TRACO);
		builder.append(TextUtils.QUEBRA_LINHA);

		for (Cliente cliente : clientes) {
			builder.append(cliente);
			builder.append(TextUtils.QUEBRA_LINHA);
		}

		JOptionPane.showMessageDialog(null, builder.toString());
	}

	private static int mostrarMenuConsultarCliente() {
		return Integer.parseInt(JOptionPane.showInputDialog(TextCliente.CONSULTAR_CLIENTE_ID));
	}

	private static int mostrarMenuExcluirCliente() {
		return Integer.parseInt(JOptionPane.showInputDialog(TextCliente.EXCLUIR_CLIENTE_ID));
	}

	private static Cliente mostrarMenuAlterarCliente(Cliente clienteAlterar) {
		clienteAlterar.setCpf(JOptionPane.showInputDialog(TextCliente.ALTERAR_CLIENTE_CPF, clienteAlterar.getCpf()));
		clienteAlterar.setAlergias(
				JOptionPane.showInputDialog(TextCliente.ALTERAR_CLIENTE_ALERGIA, clienteAlterar.getAlergias()));
		boolean vip = Boolean.parseBoolean(JOptionPane.showInputDialog(TextCliente.ALTERAR_CLIENTE_VIP,clienteAlterar.isVip(true)));

		return clienteAlterar;
	}

	public static Cliente mostrarMenuCadastrarCliente() {
		Pessoa pessoa = cadastrarPessoa();
		Cliente cliente = new Cliente();
		cliente.setCpf(JOptionPane.showInputDialog(TextCliente.ALTERAR_CLIENTE_CPF));
		cliente.setNome(pessoa.getNome());
		cliente.setId(pessoa.getId());
		cliente.setCpf(pessoa.getCpf());
		cliente.setEndereco(pessoa.getEndereco());
		cliente.setDataNascimento(pessoa.getDataNascimento());
		cliente.setOberservacao(pessoa.getOberservacao());
		cliente.setTelefone(pessoa.getTelefone());

		return cliente;
	}

	public static Cliente cadastrarCliente() {

		Pessoa pessoa = cadastrarPessoa();

		Cliente cliente = new Cliente();
		cliente.setId(0);
		cliente.setAlergias(JOptionPane.showInputDialog(TextCliente.ALTERAR_CLIENTE_ALERGIA));
		cliente.setVip(Boolean.parseBoolean(JOptionPane.showInputDialog(TextCliente.ALTERAR_CLIENTE_VIP)));
		cliente.setNome(pessoa.getNome());
		cliente.setId(pessoa.getId());
		cliente.setCpf(pessoa.getCpf());
		cliente.setEndereco(pessoa.getEndereco());
		cliente.setDataNascimento(pessoa.getDataNascimento());
		cliente.setOberservacao(pessoa.getOberservacao());
		cliente.setTelefone(pessoa.getTelefone());

		return cliente;
	}

	public static String montarSubMenuClientes() {
		String subMenuGeral = montarSubMenuGeral(TextCliente.CABECALHO_TITULO_CLIENTES);

		StringBuilder builder = new StringBuilder();
		builder.append(subMenuGeral);
		builder.append(TextMenu.OPCAO_SEIS + TextMenu.OPCAO_VOLTAR);

		return builder.toString();
	}

	// Pessoa
	public static Pessoa cadastrarPessoa() {
		int id = idCounter.incrementAndGet();
		String nome = JOptionPane.showInputDialog("Digite o nome da pessoa");
		String endereco = JOptionPane.showInputDialog("Digite o endereço da pessoa");
		String telefone = JOptionPane.showInputDialog("Digite o telefone da pessoa");
		String cpf = JOptionPane.showInputDialog("Digite o CPF da pessoa");
		Date dataNascimento = UtilitarioData.stringParaDate(
				JOptionPane.showInputDialog("Qual a data de nascimento da pessoa? \n Formato: dd/MM/yyyy"));
		String observacao = JOptionPane.showInputDialog("Digite alguma possível observação");
		boolean ativo = Boolean
				.parseBoolean(JOptionPane.showInputDialog("O usuário está ativo? \n 0 - Não \n 1 - Sim"));

		return new Pessoa(id, nome, endereco, telefone, cpf, dataNascimento, observacao, ativo);
	}

	public static Produto cadastrarProduto() {

		int id = idCounter.incrementAndGet();
		String nome = JOptionPane.showInputDialog("Digite o nome do produto:");
		String descricao = JOptionPane.showInputDialog("Digite a descrição do produto:");
		String codigo = JOptionPane.showInputDialog("Digite o código do produto");
		double precoCusto = Double.parseDouble(JOptionPane.showInputDialog("Digite o preço de custo do produto:"));
		double precoVenda = Double.parseDouble(JOptionPane.showInputDialog("Digite o preço de venda do produto:"));
		String tempoPreparo = JOptionPane.showInputDialog("Digite a descrição do tempo de preparo do produto");
		String observacoes = JOptionPane.showInputDialog("Digite as observações do produto:");
		TipoProduto tipoProduto = TipoProduto.values()[Integer.parseInt(
				JOptionPane.showInputDialog("Digite o tipo do produto \n 0 - Lanche \n 1 - Bebida \n 2 - Sobremesa"))];
		boolean ativo = Boolean
				.parseBoolean(JOptionPane.showInputDialog("O produto está ativo? \n 0 - Não \n 1 - Sim"));

		return new Produto(id, nome, descricao, codigo, precoCusto, precoVenda, tempoPreparo, observacoes, tipoProduto,
				ativo);

	}

	public static Pedido cadastrarPedido() {
		Pedido pedido = new Pedido();

		pedido.setId(idCounter.incrementAndGet());
		pedido.setDataHoraSolicitacao(new Timestamp(new Date().getTime()));
		pedido.setObservacao(JOptionPane.showInputDialog("Observações:"));
		pedido.setQuantidade(Integer
				.parseInt(JOptionPane.showInputDialog("Digite a quantidade de items que você quer para esse pedido")));
		pedido.setStatusPreparo(StatusPreparoPedido.SOLICITADO);

		return pedido;
	}

	public static Mesa cadastrarMesa() {
		Mesa mesa = new Mesa();

		mesa.setId(idCounter.incrementAndGet());
		mesa.setCodigo(JOptionPane.showInputDialog("Digite o código da mesa:"));
		mesa.setNome(JOptionPane.showInputDialog("Digite o nome da mesa:"));
		mesa.setNumero(Integer.parseInt(JOptionPane.showInputDialog("Digite o número da mesa:")));
		mesa.setStatusMesa(StatusMesa.DISPONIVEL);

		return mesa;

	}

	public static Comanda cadastrarComanda() {
		Comanda comanda = new Comanda();

		comanda.setId(idCounter.incrementAndGet());
		comanda.setCodigo(JOptionPane.showInputDialog("Digite o código da comanda:"));
		comanda.setObservacoes(JOptionPane.showInputDialog("Digite as observações sobre a comanda:"));
		comanda.setStatus(StatusComanda.EM_ABERTO);

		return comanda;
	}

	public static String montarMenuPrincipal() {

		StringBuilder builder = new StringBuilder();
		builder.append(" ==================== RAVIN ==================== ");
		builder.append(TextUtils.QUEBRA_LINHA);
		builder.append("1 - Funcionarios \n");
		builder.append("2 - Clientes \n");
		builder.append("3 - Produtos \n");
		builder.append("4 - Cardapios \n");
		builder.append("5 - Mesas \n");
		builder.append("6 - Pedidos \n");
		builder.append("7 - Sair");

		return builder.toString();
	}

	public static String montarSubMenuGeral(String modulo) {
		StringBuilder builder = new StringBuilder();
		builder.append(" ==================== Gestão de ");
		builder.append(modulo);
		builder.append(" ==================== ");
		builder.append(TextUtils.QUEBRA_LINHA);
		builder.append("1 -  Cadastrar \n");
		builder.append("2 -  Alterar \n");
		builder.append("3 -  Excluir \n");
		builder.append("4 -  Consultar \n");
		builder.append("5 -  Listar todos \n");

		return builder.toString();
	}

}
