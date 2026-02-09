package com.br.tarefas.handler;

import com.br.tarefas.dto.ErroResponse;
import com.br.tarefas.dto.ValidateError;
import com.br.tarefas.exception.ApiExceptionContrato;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;


@ControllerAdvice


public class GlobalExceptionHandler {

  @ExceptionHandler(RuntimeException.class)

  public ResponseEntity<ErroResponse> handlerApiException(ApiExceptionContrato contrato){
    HttpStatus status = contrato.getHttpStatus();

    ErroResponse erro = new ErroResponse(contrato.getCode(), contrato.getMessage(), status.value());


    return ResponseEntity.status(status).body(erro);
  }


  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErroResponse> handlerValidationException(MethodArgumentNotValidException validException){
    List<ValidateError> erros = validException.getBindingResult().getFieldErrors().stream().map(fieldError -> new ValidateError(fieldError.getField(), fieldError.getDefaultMessage())).toList();
    ErroResponse erroResponse = new ErroResponse("FIELD_VALDIATE_ERROR", "EXISTEM CAMPOS NAO PREENCHIDOS CORRETAMENTE", HttpStatus.BAD_REQUEST.value(), erros);
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erroResponse);
  }



  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErroResponse> handlerGenericException(Exception ex){

    ErroResponse erro = new ErroResponse("INTERNAL_SERVER_ERROR", "Ocorreu um erro inesperado", HttpStatus.INTERNAL_SERVER_ERROR.value());

    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(erro);

  }

}
