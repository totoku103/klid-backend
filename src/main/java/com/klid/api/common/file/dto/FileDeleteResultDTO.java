package com.klid.api.common.file.dto;

/**
 * 파일 삭제 결과 DTO
 *
 * @param success 성공 여부
 * @param message 결과 메시지
 */
public record FileDeleteResultDTO(
        boolean success,
        String message
) {}
