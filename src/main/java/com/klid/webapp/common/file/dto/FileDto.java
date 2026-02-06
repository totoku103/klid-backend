package com.klid.webapp.common.file.dto;

import org.springframework.web.multipart.MultipartFile;

public class FileDto {
	private MultipartFile uploadFile;

	public MultipartFile getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(MultipartFile uploadFile) {
		this.uploadFile = uploadFile;
	}
}
