package br.com.urbanswift.api.service;

import br.com.urbanswift.api.model.Endereco;
import br.com.urbanswift.api.model.TipoUsuario;
import br.com.urbanswift.api.repository.EnderecoRepository;
import br.com.urbanswift.api.repository.TipoUsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnderecoService {
    private final EnderecoRepository enderecoRepository;

    public EnderecoService(EnderecoRepository enderecoRepository) {
        this.enderecoRepository = enderecoRepository;
    }

    public List<Endereco> listarTodos() {
        return enderecoRepository.findAll();
    }

    public Endereco buscarPorId(Integer id) {
        return enderecoRepository.findById(id).orElse(null);
    }

    public Endereco cadastrar(Endereco endereco) {
        return enderecoRepository.save(endereco);
    }

    public Endereco atualizar(Integer id, Endereco enderecoNovo) {
        Endereco enderecoExistente = buscarPorId(id);

        if (enderecoExistente == null) {
            return null;
        }

        enderecoExistente.setCep(enderecoNovo.getCep());
        enderecoExistente.setCidade(enderecoNovo.getCidade());
        enderecoExistente.setNumero(enderecoNovo.getNumero());
        enderecoExistente.setLogradouro(enderecoNovo.getLogradouro());

        return enderecoRepository.save(enderecoExistente);
    }

    public Endereco deletar(Integer id) {
        Endereco tExistente = buscarPorId(id);

        if (tExistente == null) {
            return null;
        }

        enderecoRepository.delete(tExistente);
        return tExistente;
    }
}
