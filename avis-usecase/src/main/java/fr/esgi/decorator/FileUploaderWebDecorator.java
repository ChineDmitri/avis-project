package fr.esgi.decorator;

import fr.esgi.exception.TechnicalException;
import fr.esgi.port.FileUploader;
import fr.esgi.port.decorator.FileUploaderDecorator;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@Component
@Log
public class FileUploaderWebDecorator extends FileUploaderDecorator {

    public FileUploaderWebDecorator(FileUploader delegate) {
        super(delegate);
    }

    /**
     * Upload a MultipartFile and return the file path
     *
     * @param multipartFile the uploaded file from web request
     * @return the file path where the file was saved
     * @throws TechnicalException if file cannot be processed
     */
    public String upload(MultipartFile multipartFile) throws
                                                      TechnicalException {
        try {
            log.info("[LOG] Téléversement standard démarré");
            log.info("[LOG] Nom du fichier: " + multipartFile.getOriginalFilename());
            log.info("[LOG] Taille du fichier: " + multipartFile.getSize() + " bytes");
            log.info("[LOG] Type de contenu: " + multipartFile.getContentType());

            InputStream inputStream = multipartFile.getInputStream();
            String      result      = super.upload(inputStream);

            log.info("[LOG] Téléversement standard terminé. FileName: " + result);
            return result;
        } catch (IOException e) {
            log.info("[ERROR] Erreur lors de la lecture du fichier: " + e.getMessage());
            e.printStackTrace();
            throw new TechnicalException("Erreur lors de la lecture du fichier: " + e.getMessage());
        }
    }

    /**
     * Converts a Spring MultipartFile to a FileInputStream
     *
     * @param multipartFile the uploaded file from web request
     * @return FileInputStream that can be used by domain services
     * @throws IOException if file cannot be processed
     */
    private InputStream toInputStream(MultipartFile multipartFile) throws
                                                                   TechnicalException { // NOSONAR
        try {
            return multipartFile.getInputStream();
        } catch (IOException e) {
            throw new TechnicalException(e.getMessage());
        }
    }

    /**
     * Gets the original filename from the MultipartFile
     */
    private String getOriginalFilename(MultipartFile multipartFile) { // NOSONAR
        return multipartFile.getOriginalFilename();
    }

    /**
     * Gets the content type from the MultipartFile
     */
    private String getContentType(MultipartFile multipartFile) { // NOSONAR
        return multipartFile.getContentType();
    }


}
