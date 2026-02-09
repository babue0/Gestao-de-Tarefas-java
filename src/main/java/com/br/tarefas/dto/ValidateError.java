package com.br.tarefas.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ValidateError {
  private String campo;
  private String mensagem;
  private int status;
  private List<ValidateError> validateError;


  public ValidateError(String campo, String mensagem) {
    this.campo = campo;
    this.mensagem = mensagem;
  }

}
