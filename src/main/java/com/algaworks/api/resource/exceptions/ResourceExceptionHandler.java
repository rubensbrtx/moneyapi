package com.algaworks.api.resource.exceptions;

import com.algaworks.api.service.exceptions.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandartError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request){

        StandartError err = new StandartError(HttpStatus.NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandartError> methodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest request){

        List<Object> lista = erros(e.getBindingResult());

        StandartError err = new StandartError(HttpStatus.BAD_REQUEST.value(), lista, System.currentTimeMillis());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

    private List<Object> erros(BindingResult bindingResult) {

        List<Object> errs = new ArrayList<>();

        for(FieldError fieldError : bindingResult.getFieldErrors()) {
            errs.add(fieldError.getField() + " " + fieldError.getDefaultMessage());
        }

        return errs;
    }

}
