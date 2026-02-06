package com.klid.webapp.common.file.service;

import com.klid.webapp.common.Criterion;
import com.klid.webapp.common.ReturnData;
import com.klid.webapp.common.file.dto.AttachfileDto;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

public interface FileDownloadService {
	List<AttachfileDto> searchFileName(Criterion criterion);

	//사고접수용 첨부파일 다운
	List<AttachfileDto> searchAccFileName(Criterion criterion);

	HttpServletResponse fileRender(HttpServletResponse response, AttachfileDto fileDto);

	HttpServletResponse fileRender2(HttpServletResponse response, AttachfileDto file);

	HttpServletResponse fileRenderHelp(HttpServletResponse response, String code2);

	ReturnData exportGrid(Criterion criterion);
}
