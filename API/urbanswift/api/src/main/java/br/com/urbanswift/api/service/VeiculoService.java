package br.com.urbanswift.api.service;

import br.com.urbanswift.api.model.Usuario;
import br.com.urbanswift.api.model.Veiculo;
import br.com.urbanswift.api.repository.UsuarioRepository;
import br.com.urbanswift.api.repository.VeiculoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VeiculoService {
    private final VeiculoRepository veiculoRepository;

    private final UsuarioRepository usuarioRepository;

    public VeiculoService(VeiculoRepository veiculoRepository, UsuarioRepository usuarioRepository) {
        this.veiculoRepository = veiculoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public List<Veiculo> listarTodos() {
        return veiculoRepository.findAll();
    }

    public Veiculo buscarPorId(Integer id) {
        return veiculoRepository.findById(id).orElse(null);
    }

    public Veiculo cadastrar(Veiculo v) {
        return veiculoRepository.save(v);
    }

    public Veiculo atualizar(Integer id, Veiculo vNovo) {
        Veiculo vExistente = buscarPorId(id);

        if (vExistente == null) {
            return null;
        }

        vExistente.setTipo(vNovo.getTipo());
        vExistente.setModelo(vNovo.getModelo());
        vExistente.setPlaca(vNovo.getPlaca());

        if (vExistente.getEntregador() != null && vExistente.getEntregador().getUsuarioId() != null) {
            Integer entregadorId = vExistente.getEntregador().getUsuarioId();

            Usuario novoUsuario = usuarioRepository.findById(entregadorId)
                    .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado com o ID: " + entregadorId));

            vExistente.setEntregador(novoUsuario);
        }

        return veiculoRepository.save(vExistente);
    }

    public Veiculo deletar(Integer id) {
        Veiculo veiculo = buscarPorId(id);

        if (veiculo == null) {
            return null;
        }

        veiculoRepository.delete(veiculo);
        return veiculo;
    }
}
