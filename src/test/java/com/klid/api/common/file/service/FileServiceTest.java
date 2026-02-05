package com.klid.api.common.file.service;

import com.klid.api.BaseServiceTest;
import com.klid.api.common.file.dto.FileDeleteResultDTO;
import com.klid.api.common.file.dto.FileFormDownloadDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * 파일 관리 Service 통합 테스트
 */
@DisplayName("파일 관리 Service 테스트")
class FileServiceTest extends BaseServiceTest {

    @Autowired
    @Qualifier("apiFileService")
    private FileService fileService;

    @Nested
    @DisplayName("기본 파일 Service 테스트")
    class BasicFileServiceTest {

        @Test
        @DisplayName("파일 삭제 - 존재하지 않는 파일")
        void testDeleteFile_NotFound() {
            // given
            final Map<String, Object> request = new HashMap<>();
            request.put("fileNo", 99999L);

            // when
            final FileDeleteResultDTO result = fileService.deleteFile(request);

            // then
            assertThat(result).isNotNull();
            assertThat(result.success()).isFalse();
            assertThat(result.message()).isEqualTo("삭제할 파일이 없습니다.");
        }

        @Test
        @DisplayName("그리드 데이터 엑셀 내보내기")
        void testExportGrid() {
            // given
            final Map<String, Object> request = new HashMap<>();
            request.put("gridData", "테스트 데이터");
            request.put("fileName", "test_export");

            // when
            final Map<String, Object> result = fileService.exportGrid(request);

            // then
            assertThat(result).isNotNull();
        }
    }

    @Nested
    @DisplayName("사고접수용 파일 Service 테스트")
    class AccidentFileServiceTest {

        @Test
        @DisplayName("사고접수용 양식 파일 다운로드 정보 조회 - CSV")
        void testGetAccidentFormDownloadInfo_Csv() {
            // given
            final Map<String, Object> request = new HashMap<>();
            request.put("type", "csv");

            // when
            final FileFormDownloadDTO result = fileService.getAccidentFormDownloadInfo(request);

            // then
            assertThat(result).isNotNull();
            assertThat(result.filePath()).isEqualTo("/export/lcsc.csv");
            assertThat(result.fileName()).isEqualTo("csv_form");
            assertThat(result.fileExt()).isEqualTo(".csv");
        }

        @Test
        @DisplayName("사고접수용 양식 파일 다운로드 정보 조회 - EML")
        void testGetAccidentFormDownloadInfo_Eml() {
            // given
            final Map<String, Object> request = new HashMap<>();
            request.put("type", "eml");

            // when
            final FileFormDownloadDTO result = fileService.getAccidentFormDownloadInfo(request);

            // then
            assertThat(result).isNotNull();
            assertThat(result.filePath()).isEqualTo("/export/lcsc.eml");
            assertThat(result.fileName()).isEqualTo("eml_form");
            assertThat(result.fileExt()).isEqualTo(".eml");
        }

        @Test
        @DisplayName("사고접수용 양식 파일 다운로드 정보 조회 - 잘못된 타입")
        void testGetAccidentFormDownloadInfo_InvalidType() {
            // given
            final Map<String, Object> request = new HashMap<>();
            request.put("type", "invalid");

            // when & then
            assertThatThrownBy(() -> fileService.getAccidentFormDownloadInfo(request))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("지원하지 않는 파일 타입");
        }

        @Test
        @DisplayName("사고접수용 파일 삭제 - 존재하지 않는 파일")
        void testDeleteAccidentFile_NotFound() {
            // given
            final Map<String, Object> request = new HashMap<>();
            request.put("fileNo", 99999L);

            // when
            final FileDeleteResultDTO result = fileService.deleteAccidentFile(request);

            // then
            assertThat(result).isNotNull();
            assertThat(result.success()).isFalse();
            assertThat(result.message()).isEqualTo("삭제할 파일이 없습니다.");
        }
    }

    @Nested
    @DisplayName("홈페이지 모니터링 파일 Service 테스트")
    class HomepageFileServiceTest {

        @Test
        @DisplayName("홈페이지용 양식 파일 다운로드 정보 조회")
        void testGetHomepageFormDownloadInfo() {
            // given
            final Map<String, Object> request = new HashMap<>();

            // when
            final FileFormDownloadDTO result = fileService.getHomepageFormDownloadInfo(request);

            // then
            assertThat(result).isNotNull();
            assertThat(result.filePath()).isEqualTo("/export/home.xls");
            assertThat(result.fileName()).isEqualTo("xls_form");
            assertThat(result.fileExt()).isEqualTo(".xls");
        }
    }
}
