package com.br.tarefas.dto;


import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;



@Getter
@Setter
public class TarefaDTO {

  private Long id;

  @NotBlank(message = "O titulo eh obrigatorio")
  private String title;

  @NotBlank(message = "A descricao eh obrigatorio")
  private String description;

  @NotBlank(message = "O local eh obrigatorio")
  private String local;

  @NotNull(message = "A data/horario eh obrigatorio")
  private LocalDateTime dateHour;



}
