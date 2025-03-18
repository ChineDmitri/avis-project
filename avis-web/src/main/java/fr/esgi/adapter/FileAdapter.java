package fr.esgi.adapter;

import fr.esgi.exception.TechnicalException;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@Component
public class FileAdapter {

    /**
     * Converts a Spring MultipartFile to a FileInputStream
     * @param multipartFile the uploaded file from web request
     * @return FileInputStream that can be used by domain services
     * @throws IOException if file cannot be processed
     */
    public InputStream toInputStream(MultipartFile multipartFile) throws
                                                                  TechnicalException {
        try {
            return multipartFile.getInputStream();
        } catch (IOException e) {
            throw new TechnicalException(e.getMessage());
        }
    }

    /**
     * Gets the original filename from the MultipartFile
     */
    public String getOriginalFilename(MultipartFile multipartFile) {
        return multipartFile.getOriginalFilename();
    }

    /**
     * Gets the content type from the MultipartFile
     */
    public String getContentType(MultipartFile multipartFile) {
        return multipartFile.getContentType();
    }
}
