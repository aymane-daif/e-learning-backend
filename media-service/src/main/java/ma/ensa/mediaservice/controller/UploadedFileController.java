package ma.ensa.mediaservice.controller;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.ensa.mediaservice.cloudservice.EventCloudService;
import ma.ensa.mediaservice.dto.UploadedFileDto;
import ma.ensa.mediaservice.exception.FileAlreadyExists;
import ma.ensa.mediaservice.service.UploadedFileService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/media")
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

    @PostMapping(value = "/upload-file/{course_id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<UploadedFileDto> uploadCourseSupport(@RequestParam MultipartFile file, @PathVariable Long course_id) throws FileAlreadyExists {
        log.info("Starting .....");
        if(file.getContentType().startsWith("image")) {
            return ResponseEntity.status(HttpStatus.OK).body(this.eventCloudService.uploadFile(file,"/course/image/", course_id));
        }
        return ResponseEntity.status(HttpStatus.OK).body(this.eventCloudService.uploadFile(file,"/course/video/", course_id));
    }

    @GetMapping("/{course_id}")
    public ResponseEntity<List<UploadedFileDto>> getAllFilesOfCourse(@PathVariable Long course_id) {
        Optional<List<UploadedFileDto>> uploadedFileDtos = uploadedFileService.getAllFilesOfCourse(course_id);
        if(uploadedFileDtos.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(uploadedFileDtos.get());
        }
        else
            return ResponseEntity.status(HttpStatus.OK).body(new ArrayList<>());
    }
}
