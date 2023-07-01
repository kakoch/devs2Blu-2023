package repositorios;

import java.util.ArrayList;
import java.util.List;

import ravin.modelos.Cliente;

public class ClienteRepository {

	private List<Cliente> clientes;

	public ClienteRepository() {
		clientes = new ArrayList<Cliente>();
	}

	public void salvar(Cliente entidade) {
		Cliente cliente = buscarPorId(entidade.getId());

		if (cliente == null) {
			clientes.add(entidade);
		} else {
			cliente = entidade;
		}

	}

	public List<Cliente> listarTodos() {
		return clientes;
	}

	public void excluir(Cliente entidade) {
		clientes.remove(entidade);
	}

	public Cliente buscarPorId(int id) {
		Cliente clienteBuscado = null;
		for (Cliente cliente : clientes) {
			if (cliente.getId() == id)
				clienteBuscado = cliente;
		}

		return clienteBuscado;
	}

	public void deletarPeloId(Cliente cliente) {
		clientes.remove(cliente);
	}

	public int contar() {
		return clientes.size();
	}

	public Cliente buscarPorNome(String nome) {
		return null;
	}

}