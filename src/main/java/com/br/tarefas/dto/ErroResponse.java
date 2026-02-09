package com.br.tarefas.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter

public class ErroResponse {

  private String code;
  private String message;
  private int status;
  private List<ValidateError> errors;

  public ErroResponse(String code, String message, int status) {
    this.code = code;
    this.message = message;
    this.status = status;
    this.errors = null;
  }





}
