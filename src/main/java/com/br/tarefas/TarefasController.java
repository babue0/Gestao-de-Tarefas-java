package com.br.tarefas;

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

  private List<Tarefa> listaTarefas = new ArrayList<>();

  @GetMapping("/{id}")
  public ResponseEntity<Tarefa> recuperarTarefas(@PathVariable Long id) {
    Optional<Tarefa> tarefa0p = this.listaTarefas.stream().filter(tarefa1 -> tarefa1.getId() == id).findFirst();

    return tarefa0p.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

  }

  @PostMapping
  public ResponseEntity<?> addTarefa(@RequestBody Tarefa tarefa){
    boolean existe = this.listaTarefas.stream().filter(tarefaLista -> tarefaLista.getId() == tarefa.getId()).findFirst().isPresent();
    if(existe){
      return ResponseEntity.badRequest().body(Map.of("menssagem", "tarefa com o id " + tarefa.getId() + " ja existente na lista"));
    }
    this.listaTarefas.add(tarefa);
    return ResponseEntity.status(HttpStatus.CREATED).body(tarefa);
  }

  @GetMapping
  public ResponseEntity<List<Tarefa>>recuperaTarefas() {
    return ResponseEntity.ok(this.listaTarefas);
  }


}
