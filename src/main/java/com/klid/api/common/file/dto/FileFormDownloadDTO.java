package com.klid.api.common.file.dto;

/**
 * 양식 파일 다운로드 정보 DTO
 *
 * @param filePath 파일 경로
 * @param fileName 파일명
 * @param fileExt 파일 확장자
 */
public record FileFormDownloadDTO(
        String filePath,
        String fileName,
        String fileExt
) {}
