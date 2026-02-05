package com.br.tarefas.controller;

import com.br.tarefas.entity.Tarefa;
import com.br.tarefas.repository.TarefaRepository;
import com.br.tarefas.service.TarefaService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tarefas")
public class TarefasController {

  @Autowired
  private TarefaService tarefaService;

  @GetMapping("/{id}")
  public ResponseEntity<Tarefa> recuperarTarefas(@PathVariable Long id) {
      return ResponseEntity.ok(tarefaService.recuperarTarefa(id));
  }

  @PostMapping
  public ResponseEntity<Tarefa> addTarefa(@RequestBody Tarefa tarefa){
    return ResponseEntity.status(HttpStatus.CREATED).body(tarefaService.addTarefa(tarefa));
  }

  @GetMapping
  public ResponseEntity<List<Tarefa>> recuperaTarefas() {
    return ResponseEntity.ok(tarefaService.recuperarTarefa());
  }

  @PutMapping("/{id}")
  public ResponseEntity<Tarefa> atualizarTarefa(@PathVariable Long id, @RequestBody Tarefa tarefa){
    return ResponseEntity.ok(tarefaService.atualizarTarefa(id, tarefa));
  }


  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteTarefa(@PathVariable Long id){
    tarefaService.deleteTarefa(id);
    return ResponseEntity.noContent().build();
  }


}
