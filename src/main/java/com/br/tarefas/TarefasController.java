package com.br.tarefas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/tarefas")
public class TarefasController {

  @Autowired
  private TarefaRepository tarefaRepository;

  @GetMapping("/{id}")
  public ResponseEntity<Tarefa> recuperarTarefas(@PathVariable Long id) {
    Optional<Tarefa> tarefa0p = tarefaRepository.findById(id);

    return tarefa0p.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

  }

  @PostMapping
  public ResponseEntity<?> addTarefa(@RequestBody Tarefa tarefa){
    tarefa = tarefaRepository.save(tarefa);
    return ResponseEntity.status(HttpStatus.CREATED).body(tarefa);
  }

  @GetMapping
  public ResponseEntity<List<Tarefa>>recuperaTarefas() {
    return ResponseEntity.ok(tarefaRepository.findAll());
  }

  @PutMapping("/{id}")
  public ResponseEntity<Tarefa> atualizarTarefa(@PathVariable Long id, @RequestBody Tarefa tarefa){
    if(tarefaRepository.existsById(id)){
      tarefa.setId(id);
      return ResponseEntity.ok(tarefaRepository.save(tarefa));
    }
    return ResponseEntity.notFound().build();
  }


  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteTarefa(@PathVariable Long id){
    if(tarefaRepository.existsById(id)){
      tarefaRepository.deleteById(id);
      return ResponseEntity.noContent().build();
    }
    return ResponseEntity.notFound().build();
  }


}
