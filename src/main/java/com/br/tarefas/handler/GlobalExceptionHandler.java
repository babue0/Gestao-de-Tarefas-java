package com.br.tarefas.handler;

import com.br.tarefas.dto.ErroResponse;
import com.br.tarefas.exception.ApiExceptionContrato;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

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
    validException.getBindingResult().getFieldErrors().get(0).getDefaultMessage();
  }



  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErroResponse> handlerGenericException(Exception ex){

    ErroResponse erro = new ErroResponse("INTERNAL_SERVER_ERROR", "Ocorreu um erro inesperado", HttpStatus.INTERNAL_SERVER_ERROR.value());

    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(erro);

  }

}
