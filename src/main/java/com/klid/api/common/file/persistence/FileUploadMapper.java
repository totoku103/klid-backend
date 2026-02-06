package com.klid.api.common.file.persistence;

import com.klid.api.common.code.dto.CodeDTO;
import com.klid.api.common.file.dto.AccFileInfoDTO;
import com.klid.api.common.file.dto.AttachFileDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 파일 업로드 Mapper
 */
@Repository
public interface FileUploadMapper {

    /**
     * 파일 정보 삽입
     *
     * @param fileInfo 파일 정보
     * @return 삽입된 행 수
     */
    int insertFileInfo(AttachFileDTO fileInfo);

    /**
     * 사고 파일 정보 삽입
     *
     * @param accFileInfo 사고 파일 정보
     * @return 삽입된 행 수
     */
    int insertAccFileInfo(AccFileInfoDTO accFileInfo);

    /**
     * 도움말 파일 추가
     *
     * @param fileName 파일명
     * @param code2 코드2
     * @return 업데이트된 행 수
     */
    int addHelpFile(@Param("fileName") String fileName, @Param("code2") String code2);

    /**
     * 도움말 파일명 조회
     *
     * @param code2 코드2
     * @return 코드 정보
     */
    CodeDTO helpFileName(@Param("code2") String code2);
}
