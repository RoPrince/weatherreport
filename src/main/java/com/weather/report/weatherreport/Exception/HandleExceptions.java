package com.weather.report.weatherreport.Exception;

import org.omg.CORBA.portable.UnknownException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

@RestControllerAdvice
@Component
public class HandleExceptions {

   @ExceptionHandler(value = UnknownException.class)
   @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleBadRequestException(){
       return "Unexpected Error";
   }

   @ExceptionHandler(value = HttpClientErrorException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ResponseEntity<Object> handleNotFoundException(){
       return new ResponseEntity<>(HttpStatus.NOT_FOUND);
   }

}
