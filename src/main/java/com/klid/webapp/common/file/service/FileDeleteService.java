package com.klid.webapp.common.file.service;

import com.klid.webapp.common.Criterion;
import com.klid.webapp.common.ReturnData;
import com.klid.webapp.common.file.dto.AttachfileDto;

import java.util.List;

public interface FileDeleteService {

	ReturnData deleteFile(List<AttachfileDto> fileDto);

	ReturnData deleteAccFile(List<AttachfileDto> fileDto);

	boolean deleteFileInfo(Criterion criterion);

	boolean deleteAccFileInfo(Criterion criterion);
}
