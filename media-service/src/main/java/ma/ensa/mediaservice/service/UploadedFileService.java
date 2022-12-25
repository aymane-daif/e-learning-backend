package ma.ensa.mediaservice.service;

import ma.ensa.mediaservice.dto.UploadedFileDto;
import ma.ensa.mediaservice.entity.UploadedFile;
import ma.ensa.mediaservice.repository.UploadedFileRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UploadedFileService {
    @Autowired
    UploadedFileRepository uploadedFileRepository;
    public static ModelMapper mapper = new ModelMapper();
    public void deleteFile(long file_id) {
        this.uploadedFileRepository.deleteById(file_id);
    }
    public Optional<UploadedFileDto> getFileByID(Long file_id) {
        Optional<UploadedFile> uploadedFile = this.uploadedFileRepository.findById(file_id);
        UploadedFileDto uploadedFileDto = UploadedFileDto.builder().id(uploadedFile.get().getId())
                .path(uploadedFile.get().getPath())
                .sharedPath(uploadedFile.get().getSharedPath())
                .courseId(uploadedFile.get().getCourseId())
                .build();
        return Optional.ofNullable(uploadedFileDto);
    }
    public Optional<List<UploadedFileDto>> getAllFilesOfCourse(Long courseId) {
        List<UploadedFileDto> userDtos = uploadedFileRepository.findAll()
                .stream().map(user -> toUploadedFileDto(user)).collect(Collectors.toList());
        return Optional.of(userDtos);
    }

    private UploadedFileDto toUploadedFileDto(UploadedFile uploadedFile) {
        return mapper.map(uploadedFile, UploadedFileDto.class);
    }
}
