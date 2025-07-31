package io.github.alfredgood.api_ciber.excepciones;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExecepcion {

    @ExceptionHandler(RecursoNoEncontradoException.class)
    public ResponseEntity<?> manejarRecursoNoEncontrado(RecursoNoEncontradoException ex, HttpServletRequest request) {

        ErrorResponder respuesta = ErrorResponder.builder()
                .timestamp(LocalDateTime.now())
                .status(404)
                .error("Recurso no encontrado")
                .menssage(ex.getMessage())
                .url(request.getRequestURI()).build();

        return ResponseEntity.badRequest().body(respuesta);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> manejarValidaciones(MethodArgumentNotValidException ex, HttpServletRequest request) {

        Map<String, String> mensaje = new HashMap<>();
        ex.getFieldErrors().stream().forEach(error -> mensaje.put(error.getField(), error.getDefaultMessage()));

        ErrorResponder respuesta = ErrorResponder.builder()
                .timestamp(LocalDateTime.now())
                .status(400)
                .error("Datos invalidos")
                .menssage(mensaje.toString())
                .url(request.getRequestURI())
                .build();

        return ResponseEntity.badRequest().body(respuesta);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> manejarErrorInesperado(Exception ex, HttpServletRequest request) {

        ErrorResponder repuesta = ErrorResponder.builder()
                .timestamp(LocalDateTime.now())
                .status(500)
                .error("Algo salio mal")
                .menssage(ex.getMessage())
                .url(request.getRequestURI())
                .build();

        return ResponseEntity.internalServerError().body(repuesta);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> cuerpoInvalido(HttpMessageNotReadableException ex, HttpServletRequest request) {
        ErrorResponder respuesta = ErrorResponder.builder()
                .timestamp(LocalDateTime.now())
                .status(400)
                .error("Cuerpo de la petición no válido")
                .menssage("El cuerpo de la solicitud está vacío o mal formado.")
                .url(request.getRequestURI())
                .build();

        return ResponseEntity.badRequest().body(respuesta);
    }

}
