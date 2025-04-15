package fr.esgi.decorator;

import fr.esgi.exception.TechnicalException;
import fr.esgi.port.FileUploader;
import fr.esgi.port.decorator.FileUploaderDecorator;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@Component
public class FileUploaderWebDecorator extends FileUploaderDecorator {

    public FileUploaderWebDecorator(FileUploader delegate) {
        super(delegate);
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
