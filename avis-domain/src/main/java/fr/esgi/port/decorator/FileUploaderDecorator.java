package fr.esgi.port.decorator;

import fr.esgi.port.FileUploader;

import java.io.InputStream;

public abstract class FileUploaderDecorator implements FileUploader {

    private final FileUploader delegate;

    protected FileUploaderDecorator(FileUploader delegate) {
        this.delegate = delegate;
    }

    @Override
    public String upload(InputStream io) {
        System.out.println("[LOG] Téléversement standard démarré");
        String fileName = delegate.upload(io);
        System.out.println("[LOG] Téléversement standard terminé. FileName: " + fileName);
        return fileName;
    }

}
