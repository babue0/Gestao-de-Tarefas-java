package com.br.tarefas.service;

import com.br.tarefas.entity.Tarefa;
import com.br.tarefas.exception.TarefaNotFound;
import com.br.tarefas.repository.TarefaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TarefaService {

  @Autowired
  private TarefaRepository tarefaRepository;

  public Tarefa recuperarTarefa(Long id){
    Optional<Tarefa> tarefa0p = tarefaRepository.findById(id);

    return tarefa0p.orElseThrow(() -> new TarefaNotFound("Tarefa com o id " + id+ " nao encontrado"));
  }

  public Tarefa addTarefa(Tarefa tarefa){
    return tarefaRepository.save(tarefa);
  }

  public List<Tarefa> recuperarTarefa(){
    return tarefaRepository.findAll();
  }

  public Tarefa atualizarTarefa(Long id, Tarefa tarefa){
    Optional<Tarefa> tarefa0 = tarefaRepository.findById(id);
    if (tarefa0.isPresent()) {
      tarefa.setId(id);
      return tarefaRepository.save(tarefa);
    }
    throw new TarefaNotFound("Tarefa com id " + id+  " nao encontrada");
  }

  public void deleteTarefa(Long id){
    if(!tarefaRepository.existsById(id)){
      throw new TarefaNotFound("Tarefa com id " + id + " nao encontrada");
    }
    tarefaRepository.deleteById(id);

  }
}
