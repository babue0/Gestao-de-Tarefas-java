package com.br.tarefas.service;

import com.br.tarefas.dto.TarefaDTO;
import com.br.tarefas.entity.Tarefa;
import com.br.tarefas.exception.TarefaNotFound;
import com.br.tarefas.repository.TarefaRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TarefaService {

  @Autowired
  private TarefaRepository tarefaRepository;

  @Autowired
  private ModelMapper modelMapper;

  public TarefaDTO recuperarTarefa(Long id){
    Optional<Tarefa> tarefa0p = tarefaRepository.findById(id);
    TarefaDTO tarefaDTO = modelMapper.map(tarefa0p.orElseThrow(() -> new TarefaNotFound("Tarefa com o id " + id+ " nao encontrado")), TarefaDTO.class);
    return tarefaDTO;
  }

  public TarefaDTO addTarefa(TarefaDTO tarefa){
    Tarefa tarefaEntity = modelMapper.map(tarefa, Tarefa.class);
    return modelMapper.map(tarefaRepository.save(tarefaEntity), TarefaDTO.class);
  }

  public List<TarefaDTO> recuperarTarefa(){
    return modelMapper.map(tarefaRepository.findAll(), new TypeToken<List<TarefaDTO>>() {}.getType());
  }

  public TarefaDTO atualizarTarefa(Long id, TarefaDTO tarefa){
    Tarefa tarefaEntity = modelMapper.map(tarefa, Tarefa.class);
    Optional<Tarefa> tarefa0 = tarefaRepository.findById(id);
    if (tarefa0.isPresent()) {
      tarefaEntity.setId(id);
      return modelMapper.map(tarefaRepository.save(tarefaEntity), TarefaDTO.class);
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
