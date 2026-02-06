package com.klid.api.common.file.persistence;

import com.klid.api.common.file.dto.AttachFileDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 파일 다운로드 Mapper
 */
@Repository
public interface FileDownloadMapper {

    /**
     * 파일명 조회
     *
     * @param fileNo 파일 번호
     * @param boardNo 게시판 번호
     * @return 첨부 파일 정보
     */
    AttachFileDTO selectFileName(@Param("fileNo") Long fileNo, @Param("boardNo") Long boardNo);

    /**
     * 사고 파일명 조회
     *
     * @param fileNo 파일 번호
     * @return 첨부 파일 정보
     */
    AttachFileDTO selectAccFileName(@Param("fileNo") Long fileNo);
}
