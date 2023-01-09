package ma.ensa.course.services;


import ma.ensa.course.dtos.UploadedFileDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@FeignClient("media-service")
public interface MediaService {

    @PostMapping(value = "/upload-file/{course_id}/{lesson_id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public UploadedFileDto uploadCourseSupport(@RequestParam MultipartFile file,
                                               @PathVariable Long course_id,
                                               @PathVariable Long lesson_id);

    }
