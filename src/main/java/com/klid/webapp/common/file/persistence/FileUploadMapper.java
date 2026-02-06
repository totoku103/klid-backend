package com.klid.webapp.common.file.persistence;

import com.klid.webapp.common.code.dto.CodeDto;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface FileUploadMapper {
	void insertFileInfo(Map<String, Object> param);

	//사고접수용 첨부파일 업로드
	void insertAccFileInfo(Map<String, Object> param);

    void addHelpFile(Map<String, Object> param);

    CodeDto helpFileName(Map<String, Object> param);
}
