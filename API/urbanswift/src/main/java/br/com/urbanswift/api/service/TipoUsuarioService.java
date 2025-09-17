package br.com.urbanswift.api.service;

import br.com.urbanswift.api.model.TipoUsuario;
import br.com.urbanswift.api.repository.TipoUsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoUsuarioService {

    private final TipoUsuarioRepository tipoUsuarioRepository;

    public TipoUsuarioService(TipoUsuarioRepository tipoUsuarioRepository) {
        this.tipoUsuarioRepository = tipoUsuarioRepository;
    }

    public List<TipoUsuario> listarTodos() {
        return tipoUsuarioRepository.findAll();
    }

    public TipoUsuario buscarPorId(Integer id) {
        return tipoUsuarioRepository.findById(id).orElse(null);
    }

    public TipoUsuario cadastrar(TipoUsuario t) {
        return tipoUsuarioRepository.save(t);
    }

    public TipoUsuario atualizar(Integer id, TipoUsuario tNovo) {
        TipoUsuario tExistente = buscarPorId(id);

        if (tExistente == null) {
            return null;
        }

        tExistente.setDescricao(tNovo.getDescricao());

        return tipoUsuarioRepository.save(tExistente);
    }

    public TipoUsuario deletar(Integer id) {
        TipoUsuario tExistente = buscarPorId(id);

        if (tExistente == null) {
            return null;
        }

        tipoUsuarioRepository.delete(tExistente);
        return tExistente;
    }

}
