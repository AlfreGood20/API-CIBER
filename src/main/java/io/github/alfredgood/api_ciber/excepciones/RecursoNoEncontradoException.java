package io.github.alfredgood.api_ciber.excepciones;

public class RecursoNoEncontradoException extends RuntimeException{
    
    public RecursoNoEncontradoException(String mensaje){
        super(mensaje);
    }
}
