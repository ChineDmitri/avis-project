package fr.m2iformation.avis.controller.rest;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import java.util.List;

@RestControllerAdvice
public class ApplicationRestControllerAdvice {

    // DRY : Don't Repeat Yourself
    // cette méthode sera greffée dans tous les contrôleurs REST de l'application
    @ExceptionHandler(jakarta.validation.ConstraintViolationException.class)
    @ResponseStatus(code= HttpStatus.UNPROCESSABLE_ENTITY)
    public List<String> traiterConstraintViolationException(ConstraintViolationException e) {
        return e.getConstraintViolations().stream().map(ConstraintViolation::getMessage).toList();
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<String> traiterMaxUploadSizeExceededException() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("La taille du fichier dépasse la limite autorisée");
    }
}
