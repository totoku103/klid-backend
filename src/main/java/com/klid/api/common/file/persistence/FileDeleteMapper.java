package com.klid.api.common.file.persistence;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 파일 삭제 Mapper
 */
@Mapper
public interface FileDeleteMapper {

    /**
     * 파일 정보 삭제
     *
     * @param fileNo 파일 번호
     * @param boardNo 게시판 번호
     * @return 삭제된 행 수
     */
    int deleteFileInfo(@Param("fileNo") Long fileNo, @Param("boardNo") Long boardNo);

    /**
     * 사고 파일 정보 삭제
     *
     * @param fileNo 파일 번호
     * @return 삭제된 행 수
     */
    int deleteAccFileInfo(@Param("fileNo") Long fileNo);
}
