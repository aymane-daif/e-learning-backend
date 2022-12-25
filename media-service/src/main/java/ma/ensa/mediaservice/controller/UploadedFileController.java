package ma.ensa.mediaservice.controller;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.ensa.mediaservice.cloudservice.EventCloudService;
import ma.ensa.mediaservice.dto.UploadedFileDto;
import ma.ensa.mediaservice.service.UploadedFileService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/uploaded-file")
@AllArgsConstructor
public class UploadedFileController {
    private UploadedFileService uploadedFileService;
    private EventCloudService eventCloudService;
    @DeleteMapping("/{file_id}")
    public ResponseEntity<Void> deleteFileById(@PathVariable Long file_id) {
        Optional<UploadedFileDto> uploadedFileDto = this.uploadedFileService.getFileByID(file_id);
        if(uploadedFileDto.isPresent()) {
            this.eventCloudService.deleteFile(uploadedFileDto.get().getPath());
            this.uploadedFileService.deleteFile(file_id);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(null);
        }
        else
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    @PostMapping(value = "/{course_id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public UploadedFileDto uploadCourseSupport(@RequestParam MultipartFile file, @PathVariable Long course_id) {
        log.info("Starting .....");
        return this.eventCloudService.uploadFile(file,"/course/video/", String.valueOf(course_id));
    }
}
