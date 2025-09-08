package br.com.ecommerce.api.service;

import br.com.ecommerce.api.model.Cliente;
import br.com.ecommerce.api.model.Produto;
import br.com.ecommerce.api.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {
    // Injeção de Dependência
    // Falar que Service depende de alguém
    // final - constante
    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository repo) {
        clienteRepository = repo;
    }

    // Listar Todos os Clientes
    public List<Cliente> listarTodos() {
        return clienteRepository.findAll();
    }

    public Cliente buscarPorId(Integer id) {
        return clienteRepository.findById(id).orElse(null);
    }

    public Cliente cadastrarCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public Cliente atualizarCliente(Integer id, Cliente novoCliente) {
        Cliente clienteExistente = buscarPorId(id);

        if(clienteExistente == null) {
            return null;
        }

        clienteExistente.setNomeCompleto(novoCliente.getNomeCompleto());
        clienteExistente.setDataCadastro(novoCliente.getDataCadastro());
        clienteExistente.setTelefone(novoCliente.getTelefone());
        clienteExistente.setSenha(novoCliente.getSenha());

        return clienteRepository.save(clienteExistente);
    }

    public Cliente deleteCliente(Integer id) {
        Cliente cliente = buscarPorId(id);

        if(cliente == null) {
            return null;
        }

        clienteRepository.delete(cliente);
        return cliente;
    }

}
