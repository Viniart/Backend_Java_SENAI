package br.com.clinica_medica.service;

import br.com.clinica_medica.model.Medico;
import br.com.clinica_medica.repository.MedicoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicoService {
    private final MedicoRepository medicoRepository;

    public MedicoService(MedicoRepository medicoRepository) {
        this.medicoRepository = medicoRepository;
    }

    public List<Medico> listarTodos(){
        return this.medicoRepository.findAll();
    }

    public Medico buscarPorId(Integer id){
        Optional<Medico> medico = this.medicoRepository.findById(id);

        if(medico.isEmpty()){
            return null;
        }
        return medico.get();
    }

    public Medico cadastrarMedico(Medico medico){
        return this.medicoRepository.save(medico);
    }

    public Medico atualizarMedico(int id, Medico medicoNovo){
        Medico medicoExistente = this.buscarPorId(id);

        if(medicoExistente == null) {
            return null;
        }

        medicoExistente.setNome(medicoNovo.getNome());
        medicoExistente.setEspecialidade(medicoNovo.getEspecialidade());
        medicoExistente.setCrm(medicoNovo.getCrm());

        return medicoRepository.save(medicoExistente);
    }

    public void deletarMedico(Integer id){
        Medico medico = this.buscarPorId(id);

        if(medico != null)
            medicoRepository.delete(medico);
    }
}
