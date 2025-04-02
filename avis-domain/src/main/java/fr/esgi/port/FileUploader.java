package fr.esgi.port;

import fr.esgi.model.Jeu;

import java.io.InputStream;

public interface FileUploader {

    String upload(Jeu jeu, InputStream io);

}
