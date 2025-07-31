package io.github.alfredgood.api_ciber.excepciones;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class ErrorResponder {
    private LocalDateTime timestamp;
    private int status;
    private String error;
    private String menssage;
    private String url;
}
