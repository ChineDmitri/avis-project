package fr.esgi.decorator;

import fr.esgi.port.FileUploader;
import fr.esgi.port.decorator.FileUploaderDecorator;

import java.io.InputStream;

public class FilesS3BucketDecorator extends FileUploaderDecorator {

    public FilesS3BucketDecorator(FileUploader delegate) {
        super(delegate);
    }

    @Override
    public String upload(InputStream io) {
        System.out.println("[LOG] Téléversement vers S3 démarré");
        String fileName = super.upload(io);
        System.out.println("[LOG] Téléversement vers S3 terminé. FileName: " + fileName);
        return fileName;
    }
}
