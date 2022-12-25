package ma.ensa.mediaservice.helper;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.Random;

@Slf4j
public class CloudFileHelper {

    private static final Random RANDOM = new SecureRandom();
    private static final String ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    public static File getTempFileFromMultiPartFile(MultipartFile multipartFile, Long course_id) {
        String fullFileName = multipartFile.getOriginalFilename();
        File file = null;
        try {
            String fileName = fullFileName.substring(0, fullFileName.indexOf('.')) + course_id;
            String extension = fullFileName.substring(fullFileName.indexOf('.') + 1);
            file = File.createTempFile(fileName, extension);
            multipartFile.transferTo(file);
        } catch (IOException e) {
            log.error("Error while trying to get file from multiPartFile", e);
        }
        return file;
    }
}
