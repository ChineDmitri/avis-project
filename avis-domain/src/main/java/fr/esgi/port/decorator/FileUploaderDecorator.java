package fr.esgi.port.decorator;

import fr.esgi.model.Jeu;
import fr.esgi.port.FileUploader;

import java.io.InputStream;

public abstract class FileUploaderDecorator implements FileUploader {

    private final FileUploader delegate;

    public FileUploaderDecorator(FileUploader delegate) {
        this.delegate = delegate;
    }

    @Override
    public String upload(Jeu jeu, InputStream io) {
        System.out.println("[LOG] Téléversement démarré pour jeu " + jeu.getNom());
        String fileName = delegate.upload(jeu, io);
        System.out.println("[LOG] Téléversement terminé. FileName: " + fileName);
        return fileName;
    }

}
