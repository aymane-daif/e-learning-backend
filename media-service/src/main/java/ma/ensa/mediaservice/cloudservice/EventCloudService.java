package ma.ensa.mediaservice.cloudservice;


import lombok.extern.slf4j.Slf4j;
import ma.ensa.mediaservice.cloudrepo.EventCloudRepo;
import ma.ensa.mediaservice.dto.UploadedFileDto;
import ma.ensa.mediaservice.entity.UploadedFile;
import ma.ensa.mediaservice.helper.CloudFileHelper;
import ma.ensa.mediaservice.repository.UploadedFileRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
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
	
	public List<String> getFolders() {
		return eventCloudRepo.getEventFolders();
	}
	@Async
	public void createFolder(String path) {
		eventCloudRepo.createFolder(path);
	}

	public UploadedFileDto uploadFile(MultipartFile multipartFile, String path, String generatedKey) {
		String[] name = multipartFile.getOriginalFilename().split("\\.");
		String fullFilePath = path.concat(name[0] + name[1] + "_" + generatedKey + "." + name[1]);
		Optional<File> fileOptional = Optional.ofNullable(CloudFileHelper.getTempFileFromMultiPartFile(multipartFile));
		fileOptional.ifPresent(file -> {
			eventCloudRepo.upLoadFile(file, fullFilePath);
			file.delete();
		});
		UploadedFile uploadedFile = uploadedFileRepository.save(UploadedFile.builder()
				.course_id(Long.valueOf(generatedKey))
				.path(fullFilePath)
				.sharedPath(this.doShared(fullFilePath))
				.build());
		return modelMapper.map(uploadedFile, UploadedFileDto.class);
	}

	public InputStream getFile(String fileName) throws IOException {
		return eventCloudRepo.getFile(fileName);
	}

	public Boolean isFolderExist(String path) {
		return eventCloudRepo.isFolderExist(path);
	}
	@Async
	public void deleteFolder(String pathFolder) {
		eventCloudRepo.deleteFolder(pathFolder);
	}
	@Async
	public void deleteFile(String pathFile) {
		eventCloudRepo.deleteFile(pathFile);
	}

	public String doShared(String path) {
		return eventCloudRepo.doShared(path);
	}

	public String updateFiles(MultipartFile multipartFile, String path,String generatedKey) {
		String[] name = multipartFile.getOriginalFilename().split("\\.");
		String fullFilePath = "/courses/video/".concat(name[0] + "_" + generatedKey + "." + name[1]);
		Optional<File> fileOptional = Optional.ofNullable(CloudFileHelper.getTempFileFromMultiPartFile(multipartFile));
		eventCloudRepo.deleteFile(path);
		fileOptional.ifPresent(file -> {
			eventCloudRepo.upLoadFile(file, fullFilePath);
			file.delete();
		});
		return fullFilePath;
	}
	@Async
	public void renameFile(String oldPath, String newPath) {
		eventCloudRepo.renameFile(oldPath,newPath);
	}
}
