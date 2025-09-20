package io.github.alfredgood.api_ciber.excepciones;

public class SolicitudIncorrecta extends RuntimeException{

    public SolicitudIncorrecta(String mensaje){
        super(mensaje);
    }
}
