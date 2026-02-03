package com.br.tarefas;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter


public class Tarefa {
  private Long id;
  private String title;
  private String description;
  private String local;
  private LocalDateTime dateHour;





}
