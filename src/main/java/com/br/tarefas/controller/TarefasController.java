package com.br.tarefas.controller;

import com.br.tarefas.dto.TarefaDTO;
import com.br.tarefas.service.TarefaService;
import jakarta.validation.Valid;
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
  public ResponseEntity<TarefaDTO> recuperarTarefas(@PathVariable Long id) {
      return ResponseEntity.ok(tarefaService.recuperarTarefa(id));
  }

  @PostMapping
  public ResponseEntity<TarefaDTO> addTarefa(@Valid @RequestBody TarefaDTO tarefa){
    return ResponseEntity.status(HttpStatus.CREATED).body(tarefaService.addTarefa(tarefa));
  }

  @GetMapping
  public ResponseEntity<List<TarefaDTO>> recuperaTarefas() {
    return ResponseEntity.ok(tarefaService.recuperarTarefa());
  }

  @PutMapping("/{id}")
  public ResponseEntity<TarefaDTO> atualizarTarefa(@PathVariable Long id, @Valid @RequestBody TarefaDTO tarefa){
    return ResponseEntity.ok(tarefaService.atualizarTarefa(id, tarefa));
  }


  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteTarefa(@PathVariable Long id){
    tarefaService.deleteTarefa(id);
    return ResponseEntity.noContent().build();
  }


}
