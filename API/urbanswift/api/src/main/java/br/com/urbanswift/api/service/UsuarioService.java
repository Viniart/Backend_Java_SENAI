package br.com.urbanswift.api.service;

import br.com.urbanswift.api.model.TipoUsuario;
import br.com.urbanswift.api.model.Usuario;
import br.com.urbanswift.api.repository.TipoUsuarioRepository;
import br.com.urbanswift.api.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    private final TipoUsuarioRepository tipoUsuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository, TipoUsuarioRepository tipoUsuarioRepository) {
        this.usuarioRepository = usuarioRepository;
        this.tipoUsuarioRepository = tipoUsuarioRepository;
    }

    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    public Usuario buscarPorId(Integer id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    public Usuario cadastrar(Usuario u) {
        return usuarioRepository.save(u);
    }

    public Usuario atualizar(Integer id, Usuario uNovo) {
        Usuario uExistente = buscarPorId(id);

        if (uExistente == null) {
            return null;
        }

        uExistente.setEmail(uNovo.getEmail());
        uExistente.setSenha(uNovo.getSenha());
        uExistente.setNomeCompleto(uNovo.getNomeCompleto());

        if (uNovo.getTipoUsuario() != null && uNovo.getTipoUsuario().getTipoUsuarioId() != null) {
            Integer novoTipoId = uNovo.getTipoUsuario().getTipoUsuarioId();

            TipoUsuario novoTipo = tipoUsuarioRepository.findById(novoTipoId)
                    .orElseThrow(() -> new EntityNotFoundException("Tipo de Usuário não encontrado com o ID: " + novoTipoId));

            uExistente.setTipoUsuario(novoTipo);
        }

        return usuarioRepository.save(uExistente);
    }

    public Usuario deletar(Integer id) {
        Usuario usuario = buscarPorId(id);

        if (usuario == null) {
            return null;
        }

        usuarioRepository.delete(usuario);
        return usuario;
    }
}
