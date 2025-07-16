package fr.esgi.port.decorator;

import fr.esgi.port.FileUploader;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;

import java.io.InputStream;

@Log
public abstract class FileUploaderDecorator implements FileUploader {

    private final FileUploader delegate;

    protected FileUploaderDecorator(FileUploader delegate) {
        this.delegate = delegate;
    }

    @Override
    public String upload(InputStream io) {
        log.info("[ABSTRACT] Téléversement standard démarré");
        String fileName = delegate.upload(io);
        log.info("[ABSTRACT] Téléversement standard terminé. FileName: " + fileName);
        return fileName;
    }

}
