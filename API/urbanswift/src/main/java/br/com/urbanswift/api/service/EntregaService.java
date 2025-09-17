package br.com.urbanswift.api.service;

import br.com.urbanswift.api.model.Endereco;
import br.com.urbanswift.api.model.Entrega;
import br.com.urbanswift.api.model.Usuario;
import br.com.urbanswift.api.repository.EnderecoRepository;
import br.com.urbanswift.api.repository.EntregaRepository;
import br.com.urbanswift.api.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntregaService {
    private final EntregaRepository entregaRepository;
    private final EnderecoRepository enderecoRepository;
    private final UsuarioRepository usuarioRepository;

    public EntregaService(EntregaRepository entregaRepository, EnderecoRepository enderecoRepository, UsuarioRepository usuarioRepository) {
        this.entregaRepository = entregaRepository;
        this.enderecoRepository = enderecoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public List<Entrega> listarTodos() {
        return entregaRepository.findAll();
    }

    public Entrega buscarPorId(Integer id) {
        return entregaRepository.findById(id).orElse(null);
    }

    public Entrega cadastrar(Entrega e) {
        return entregaRepository.save(e);
    }

    public Entrega atualizar(Integer id, Entrega entregaNova) {
        Entrega entregaExistente = buscarPorId(id);

        if (entregaExistente == null) {
            return null;
        }

        entregaExistente.setStatus(entregaNova.getStatus());
        entregaExistente.setDataPedido(entregaNova.getDataPedido());

        if (entregaNova.getEndereco() != null && entregaNova.getEndereco().getEnderecoId() != null) {
            Integer enderecoId = entregaNova.getEndereco().getEnderecoId();

            Endereco novoEndereco = enderecoRepository.findById(enderecoId)
                    .orElseThrow(() -> new EntityNotFoundException("Endereço não encontrado com o ID: " + enderecoId));

            entregaExistente.setEndereco(novoEndereco);
        }

        if (entregaNova.getCliente() != null && entregaNova.getCliente().getUsuarioId() != null) {
            Integer usuarioId = entregaNova.getCliente().getUsuarioId();

            Usuario novoUsuario = usuarioRepository.findById(usuarioId)
                    .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado com o ID: " + usuarioId));

            entregaExistente.setCliente(novoUsuario);
        }

        return entregaRepository.save(entregaExistente);
    }

    public Entrega deletar(Integer id) {
        Entrega entrega = buscarPorId(id);

        if (entrega == null) {
            return null;
        }

        entregaRepository.delete(entrega);
        return entrega;
    }
}
