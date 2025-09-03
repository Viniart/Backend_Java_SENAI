package br.com.clinica_medica.controller;

import br.com.clinica_medica.model.Medico;
import br.com.clinica_medica.service.MedicoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medicos")
public class MedicoController {
    private final MedicoService medicoService;

    public MedicoController(MedicoService medicoService) {
        this.medicoService = medicoService;
    }

    @GetMapping
    public ResponseEntity<List<Medico>> listarMedicos() {
        List<Medico> medicos = medicoService.listarTodos();
        return ResponseEntity.ok(medicos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Medico> buscarMedicoPorId(@PathVariable Integer id) {
        Medico medico = medicoService.buscarPorId(id);

        if (medico == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(medico);
    }

    @PostMapping
    public ResponseEntity<Medico> inserirMedico(@RequestBody Medico medico){
        Medico novoMedico = medicoService.cadastrarMedico(medico);

        return new ResponseEntity<>(novoMedico, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Medico> atualizarMedico(@PathVariable Integer id, @RequestBody Medico medico){
        Medico medicoAtualizado = medicoService.atualizarMedico(id, medico);

        if(medico == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(medicoAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Medico> removerMedico(@PathVariable Integer id){
        medicoService.deletarMedico(id);
        return ResponseEntity.noContent().build();
    }
}
