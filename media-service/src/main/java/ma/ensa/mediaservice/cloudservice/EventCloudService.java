package ma.ensa.mediaservice.cloudservice;


import lombok.extern.slf4j.Slf4j;
import ma.ensa.mediaservice.cloudrepo.EventCloudRepo;
import ma.ensa.mediaservice.dto.UploadedFileDto;
import ma.ensa.mediaservice.entity.UploadedFile;
import ma.ensa.mediaservice.exception.FileAlreadyExists;
import ma.ensa.mediaservice.helper.CloudFileHelper;
import ma.ensa.mediaservice.repository.UploadedFileRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class EventCloudService {
	@Autowired
	EventCloudRepo eventCloudRepo;
	@Autowired
	UploadedFileRepository uploadedFileRepository;

	private static final ModelMapper modelMapper = new ModelMapper();

	public UploadedFileDto uploadFile(MultipartFile multipartFile, String path, Long course_id, Long lesson_id) throws FileAlreadyExists{
		String[] name = multipartFile.getOriginalFilename().split("\\.");
		String fullFilePath = path.concat(name[0] + "_" + course_id + "." + name[1]);
		Optional<File> fileOptional = Optional.ofNullable(CloudFileHelper.getTempFileFromMultiPartFile(multipartFile, course_id));
		if(! isFileExist(fullFilePath) ) {
			fileOptional.ifPresent(file -> {
				eventCloudRepo.upLoadFile(file, fullFilePath);
				file.delete();
			});
			UploadedFile uploadedFile = uploadedFileRepository.save(UploadedFile.builder()
					.courseId(course_id)
					.lessonId(lesson_id)
					.path(fullFilePath)
					.sharedPath(this.doShared(fullFilePath))
					.build());
			return modelMapper.map(uploadedFile, UploadedFileDto.class);
		}
		else throw new FileAlreadyExists();
	}


	public Boolean isFileExist(String path) {
		return eventCloudRepo.isFolderExist(path);
	}
	@Async
	public void deleteFile(String pathFile) {
		eventCloudRepo.deleteFile(pathFile);
	}

	public String doShared(String path) {
		return eventCloudRepo.doShared(path);
	}
}
