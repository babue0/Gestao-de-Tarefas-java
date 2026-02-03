package com.br.tarefas;

import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tarefas")
public class TarefasController {

  private List<Tarefa> listaTarefas = new ArrayList<>();

  @GetMapping("/{id}")
  public Tarefa recuperarTarefas(@PathVariable Long id) {
    Optional<Tarefa> tarefa0p = this.listaTarefas.stream().filter(tarefa1 -> tarefa1.getId() == id).findFirst();

    return tarefa0p.get();
  }

  @PostMapping
  public Tarefa addTarefa(@RequestBody Tarefa tarefa){
    this.listaTarefas.add(tarefa);
    return tarefa;
  }

  @GetMapping
  public List<Tarefa> recuperaTarefas() {
    return this.listaTarefas;
  }


}
