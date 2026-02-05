package com.klid.api.common.file.controller;

import com.klid.api.BaseControllerTest;
import com.klid.config.TestSecurityConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * 파일 관리 Controller 통합 테스트
 */
@Import(TestSecurityConfig.class)
@DisplayName("파일 관리 Controller 테스트")
class FileControllerTest extends BaseControllerTest {

    private static final String BASE_URL = "/api/common/files";

    @Nested
    @DisplayName("기본 파일 API 테스트")
    class BasicFileTest {

        @Test
        @DisplayName("파일 다운로드 - 존재하지 않는 파일")
        void testDownloadFile_NotFound() throws Exception {
            mockMvc.perform(get(BASE_URL + "/download")
                            .param("fileNo", "99999"))
                    .andExpect(status().isOk()); // 파일이 없어도 200 반환 (빈 응답)
        }

        @Test
        @DisplayName("파일 삭제 - 존재하지 않는 파일")
        void testDeleteFile_NotFound() throws Exception {
            final Map<String, Object> request = new HashMap<>();
            request.put("fileNo", 99999L);

            mockMvc.perform(delete(BASE_URL)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(toJson(request)))
                    .andExpect(status().isOk())
                    .andExpect(MockMvcResultMatchers.content().contentType("application/json"));
        }

        @Test
        @DisplayName("그리드 데이터 엑셀 내보내기")
        void testExportGrid() throws Exception {
            final Map<String, Object> request = new HashMap<>();
            request.put("gridData", "테스트 데이터");
            request.put("fileName", "test_export");

            mockMvc.perform(post(BASE_URL + "/export/grid")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(toJson(request)))
                    .andExpect(status().isOk());
        }
    }

    @Nested
    @DisplayName("사고접수용 파일 API 테스트")
    class AccidentFileTest {

        @Test
        @DisplayName("사고접수용 양식 파일 다운로드 정보 조회 - CSV")
        void testGetAccidentFormDownloadInfo_Csv() throws Exception {
            final Map<String, Object> request = new HashMap<>();
            request.put("type", "csv");

            mockMvc.perform(post(BASE_URL + "/accident/form-info")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(toJson(request)))
                    .andExpect(status().isOk())
                    .andExpect(MockMvcResultMatchers.content().contentType("application/json"));
        }

        @Test
        @DisplayName("사고접수용 양식 파일 다운로드 정보 조회 - EML")
        void testGetAccidentFormDownloadInfo_Eml() throws Exception {
            final Map<String, Object> request = new HashMap<>();
            request.put("type", "eml");

            mockMvc.perform(post(BASE_URL + "/accident/form-info")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(toJson(request)))
                    .andExpect(status().isOk())
                    .andExpect(MockMvcResultMatchers.content().contentType("application/json"));
        }

        @Test
        @DisplayName("사고접수용 파일 삭제 - 존재하지 않는 파일")
        void testDeleteAccidentFile_NotFound() throws Exception {
            final Map<String, Object> request = new HashMap<>();
            request.put("fileNo", 99999L);

            mockMvc.perform(delete(BASE_URL + "/accident")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(toJson(request)))
                    .andExpect(status().isOk())
                    .andExpect(MockMvcResultMatchers.content().contentType("application/json"));
        }

        @Test
        @DisplayName("사고접수용 파일 다운로드 - 존재하지 않는 파일")
        void testDownloadAccidentFile_NotFound() throws Exception {
            mockMvc.perform(get(BASE_URL + "/accident/download")
                            .param("fileNo", "99999"))
                    .andExpect(status().isOk()); // 파일이 없어도 200 반환 (빈 응답)
        }
    }

    @Nested
    @DisplayName("사고유형별 처리방안 파일 API 테스트")
    class CodeFileTest {

        @Test
        @DisplayName("사고유형별 처리방안 파일 다운로드")
        void testDownloadCodeFile() throws Exception {
            mockMvc.perform(get(BASE_URL + "/code/download")
                            .param("code2", "001"))
                    .andExpect(status().isOk());
        }
    }

    @Nested
    @DisplayName("홈페이지 모니터링 파일 API 테스트")
    class HomepageFileTest {

        @Test
        @DisplayName("홈페이지용 양식 파일 다운로드 정보 조회")
        void testGetHomepageFormDownloadInfo() throws Exception {
            final Map<String, Object> request = new HashMap<>();

            mockMvc.perform(post(BASE_URL + "/homepage/form-info")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(toJson(request)))
                    .andExpect(status().isOk())
                    .andExpect(MockMvcResultMatchers.content().contentType("application/json"));
        }
    }
}
