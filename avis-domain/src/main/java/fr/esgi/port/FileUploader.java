package fr.esgi.port;

import java.io.InputStream;

public interface FileUploader {

    String upload(InputStream io);

}
