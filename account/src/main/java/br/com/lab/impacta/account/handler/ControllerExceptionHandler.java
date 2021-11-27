package br.com.lab.impacta.account.handler;

import br.com.lab.impacta.account.handler.exception.AccountDontExistsException;
import br.com.lab.impacta.account.handler.exception.AccountWithoutBalanceException;
import br.com.lab.impacta.account.handler.exception.ErrorMessageResponse;
import io.opentracing.Span;
import io.opentracing.Tracer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class ControllerExceptionHandler {

    @Autowired
    private Tracer tracer;

    @ExceptionHandler(AccountDontExistsException.class)
    public ResponseEntity<ErrorMessageResponse> accountNotFoundException(AccountDontExistsException accountDontExistsException) {
        Span span = tracer.buildSpan("Não existe conta cadastrada").start();
        ErrorMessageResponse errorMessageResponse = new ErrorMessageResponse(
                HttpStatus.NOT_FOUND.value(),
                new Date(),
                accountDontExistsException.getMessage(),
                accountDontExistsException.getDescription());

        span.setTag("http.status_code", 404);
        span.finish();
        return new ResponseEntity<>(errorMessageResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AccountWithoutBalanceException.class)
    public ResponseEntity<ErrorMessageResponse> withoutBalanceException(AccountWithoutBalanceException ex, WebRequest request) {
        Span span = tracer.buildSpan("Não existe valor na conta").start();
        ErrorMessageResponse message = new ErrorMessageResponse(
                HttpStatus.BAD_REQUEST.value(),
                new Date(),
                ex.getMessage(),
                ex.getDescription());

        span.setTag("http.status_code", 400);
        span.finish();
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorMessageResponse> errorGeneral(RuntimeException ex) {
        Span span = tracer.buildSpan("Erro genérico na aplicação").start();
        ErrorMessageResponse message = new ErrorMessageResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                new Date(),
                ex.getMessage(),
                "Não foi possível processar sua requisição.");

        span.setTag("http.status_code", 500);
        span.finish();
        return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
