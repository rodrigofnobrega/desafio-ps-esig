package com.br.ps.esig.ps_esig.infra;

import com.br.ps.esig.ps_esig.exceptions.EntityNotFoundException;
import com.br.ps.esig.ps_esig.exceptions.UnauthorizedAccessException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

@Slf4j
@ControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler({EntityNotFoundException.class})
    public ResponseEntity<RestErrorMessage> handleNotFoundStatus(RuntimeException exception,
                                                                 HttpServletRequest request) {
        log.error("API ERROR - ", exception);
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new RestErrorMessage(request, HttpStatus.NOT_FOUND, exception.getMessage()));
    }

    @ExceptionHandler(UnauthorizedAccessException.class)
    public ResponseEntity<RestErrorMessage> handleUnauthorized(UnauthorizedAccessException exception,
                                                               HttpServletRequest request) {
        log.error("API ERROR - ", exception);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new RestErrorMessage(request, HttpStatus.UNAUTHORIZED,
                        exception.getLocalizedMessage()));
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<RestErrorMessage> handleGenericException(Exception exception,
                                                                   HttpServletRequest request) {
        log.error("API ERROR - ", exception);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new RestErrorMessage(request, HttpStatus.INTERNAL_SERVER_ERROR,
                        "Erro ao processar requisição"));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<RestErrorMessage> handleMethodArgumentNotValid(MethodArgumentNotValidException exception,
                                                                         HttpServletRequest request) {
        log.error("API ERROR - ", exception);

        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                .body(new RestErrorMessage(request, HttpStatus.UNPROCESSABLE_ENTITY, "Campo(s) invalido(s)"));
    }

    @ExceptionHandler({HttpMessageNotReadableException.class})
    public ResponseEntity<RestErrorMessage> handleHttpMessageNotReadableException(HttpMessageNotReadableException exception,
                                                                                  HttpServletRequest request) {
        log.error("API ERROR - ", exception);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new RestErrorMessage(request, HttpStatus.BAD_REQUEST, "Requisição inválida"));
    }

    @ExceptionHandler({MissingServletRequestPartException.class})
    public ResponseEntity<RestErrorMessage> handleMissingServletRequestPartException(MissingServletRequestPartException exception,
                                                                                     HttpServletRequest request) {
        log.error("API ERROR - ", exception);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new RestErrorMessage(request, HttpStatus.BAD_REQUEST, "Parte da requisição ausente"));
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<RestErrorMessage> handleErrorOnDatabase(DataIntegrityViolationException exception,
                                                                  HttpServletRequest request) {
        log.error("API ERROR - ", exception);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new RestErrorMessage(request, HttpStatus.BAD_REQUEST,
                        exception.getLocalizedMessage()));
    }

    @ExceptionHandler({InternalAuthenticationServiceException.class})
    public ResponseEntity<RestErrorMessage> handleInternalAuthenticationServiceException(Exception exception,
                                                                                         HttpServletRequest request) {
        log.error("API ERROR - ", exception);

        if (exception.getCause() instanceof DisabledException) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(new RestErrorMessage(request, HttpStatus.FORBIDDEN,
                            "Usuário inativo."));
        } else if (exception.getCause() instanceof EntityNotFoundException) {
            return handleNotFoundStatus((RuntimeException) exception, request);
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new RestErrorMessage(request, HttpStatus.INTERNAL_SERVER_ERROR,
                        "Erro ao processar requisição"));
    }

    @ExceptionHandler({AccessDeniedException.class})
    public ResponseEntity<RestErrorMessage> handleInternalAccessDeniedException(Exception exception,
                                                                                HttpServletRequest request) {
        log.error("API ERROR - ", exception);
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(new RestErrorMessage(request, HttpStatus.FORBIDDEN,
                        "Acesso negado para esse recurso."));
    }

    @ExceptionHandler({BadCredentialsException.class})
    public ResponseEntity<RestErrorMessage> handleInternalBadCredentialsException(Exception exception,
                                                                                  HttpServletRequest request) {
        log.error("API ERROR - ", exception);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new RestErrorMessage(request, HttpStatus.BAD_REQUEST,
                        "Credenciais inválidas."));
    }
}