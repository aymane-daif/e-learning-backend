package ma.ensa.mediaservice.service;

import ma.ensa.mediaservice.dto.UploadedFileDto;
import ma.ensa.mediaservice.entity.UploadedFile;
import ma.ensa.mediaservice.repository.UploadedFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UploadedFileService {
    @Autowired
    UploadedFileRepository uploadedFileRepository;

    public void deleteFile(long file_id) {
        this.uploadedFileRepository.deleteById(file_id);
    }
    public Optional<UploadedFileDto> getFileByID(Long file_id) {
        Optional<UploadedFile> uploadedFile = this.uploadedFileRepository.findById(file_id);
        UploadedFileDto uploadedFileDto = UploadedFileDto.builder().id(uploadedFile.get().getId())
                .path(uploadedFile.get().getPath())
                .sharedPath(uploadedFile.get().getSharedPath())
                .course_id(uploadedFile.get().getCourse_id())
                .build();
        return Optional.ofNullable(uploadedFileDto);
    }
}
