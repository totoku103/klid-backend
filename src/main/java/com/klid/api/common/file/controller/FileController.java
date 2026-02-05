package com.klid.api.common.file.controller;

import com.klid.api.common.file.dto.FileDeleteResultDTO;
import com.klid.api.common.file.dto.FileFormDownloadDTO;
import com.klid.api.common.file.service.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 파일 관리 REST API Controller
 *
 * <p>파일 업로드, 다운로드, 삭제 기능을 제공합니다.</p>
 */
@Slf4j
@RestController("apiFileController")
@RequiredArgsConstructor
@RequestMapping("/api/common/files")
public class FileController {

    private final FileService fileService;

    /**
     * 파일 다운로드
     *
     * @param fileNo 파일 번호
     */
    @GetMapping("/download")
    public void downloadFile(@RequestParam("fileNo") final long fileNo, final HttpServletResponse response) {
        fileService.downloadFile(fileNo, response);
    }

    /**
     * 게시판용 파일 업로드
     *
     * @param file 업로드할 파일
     * @param boardNo 게시판 번호
     */
    @PostMapping("/upload")
    public ResponseEntity<Void> uploadFile(
            @RequestParam("fileinput") final MultipartFile file,
            @RequestParam("boardNo") final String boardNo) {
        fileService.uploadBoardFile(file, boardNo);
        return ResponseEntity.ok().build();
    }

    /**
     * 파일 삭제
     *
     * @param request 삭제 요청 (fileNo 포함)
     * @return 삭제 결과
     */
    @DeleteMapping
    public ResponseEntity<FileDeleteResultDTO> deleteFile(@RequestBody final Map<String, Object> request) {
        final FileDeleteResultDTO result = fileService.deleteFile(request);
        return ResponseEntity.ok(result);
    }

    /**
     * 그리드 데이터 엑셀 내보내기
     *
     * @param request 그리드 데이터
     * @return 엑셀 파일 정보
     */
    @PostMapping("/export/grid")
    public ResponseEntity<Map<String, Object>> exportGrid(@RequestBody final Map<String, Object> request) {
        final Map<String, Object> result = fileService.exportGrid(request);
        return ResponseEntity.ok(result);
    }

    /**
     * 이미지 내보내기
     *
     * @param fileName 파일명
     * @param imageData Base64 인코딩된 이미지 데이터
     */
    @PostMapping("/export/image")
    public void exportImage(
            @RequestParam("fname") final String fileName,
            @RequestParam("imgData") final String imageData,
            final HttpServletResponse response) {
        fileService.exportImage(fileName, imageData, response);
    }

    /**
     * Highchart를 export 폴더에 저장
     *
     * @param request 파일명과 이미지 데이터
     */
    @PostMapping("/save/highchart")
    public ResponseEntity<Void> saveHighchart(@RequestBody final Map<String, Object> request) {
        fileService.saveHighchart(request);
        return ResponseEntity.ok().build();
    }

    /**
     * Highchart를 PNG 파일로 내보내기
     *
     * @param fileName 파일명
     * @param imageData Base64 인코딩된 이미지 데이터
     */
    @PostMapping("/export/highchart")
    public void exportHighchart(
            @RequestParam("fname") final String fileName,
            @RequestParam("imgData") final String imageData,
            final HttpServletResponse response) {
        fileService.exportHighchart(fileName, imageData, response);
    }

    // ====================================== 사고접수용 파일 관리 ======================================

    /**
     * 사고접수용 파일 업로드
     *
     * @param file 업로드할 파일
     * @param incidentNo 사고 번호
     */
    @PostMapping("/accident/upload")
    public ResponseEntity<Void> uploadAccidentFile(
            @RequestParam("fileinput") final MultipartFile file,
            @RequestParam("inciNo") final String incidentNo) {
        fileService.uploadAccidentFile(file, incidentNo);
        return ResponseEntity.ok().build();
    }

    /**
     * 사고접수용 EML/CSV 파일 업로드
     *
     * @param file 업로드할 파일
     * @param type 파일 타입 (eml/csv)
     */
    @PostMapping("/accident/upload/eml-csv")
    public ResponseEntity<Void> uploadAccidentEmlCsvFile(
            @RequestParam("emlcsvfileinput") final MultipartFile file,
            @RequestParam("type") final String type) {
        fileService.uploadAccidentEmlCsvFile(file, type);
        return ResponseEntity.ok().build();
    }

    /**
     * 사고접수용 양식 파일 다운로드 정보 조회
     *
     * @param request 타입 정보 (csv/eml)
     * @return 양식 파일 정보
     */
    @PostMapping("/accident/form-info")
    public ResponseEntity<FileFormDownloadDTO> getAccidentFormDownloadInfo(@RequestBody final Map<String, Object> request) {
        final FileFormDownloadDTO formInfo = fileService.getAccidentFormDownloadInfo(request);
        return ResponseEntity.ok(formInfo);
    }

    /**
     * 사고접수용 파일 삭제
     *
     * @param request 삭제 요청 (fileNo 포함)
     * @return 삭제 결과
     */
    @DeleteMapping("/accident")
    public ResponseEntity<FileDeleteResultDTO> deleteAccidentFile(@RequestBody final Map<String, Object> request) {
        final FileDeleteResultDTO result = fileService.deleteAccidentFile(request);
        return ResponseEntity.ok(result);
    }

    /**
     * 사고접수용 파일 다운로드
     *
     * @param fileNo 파일 번호
     */
    @GetMapping("/accident/download")
    public void downloadAccidentFile(@RequestParam("fileNo") final long fileNo, final HttpServletResponse response) {
        fileService.downloadAccidentFile(fileNo, response);
    }

    // ====================================== 사고유형별 처리방안 파일 관리 ======================================

    /**
     * 사고유형별 처리방안 파일 업로드
     *
     * @param file 업로드할 파일
     * @param code2 코드2
     */
    @PostMapping("/code/upload")
    public ResponseEntity<Void> uploadCodeFile(
            @RequestParam("codefileinput") final MultipartFile file,
            @RequestParam("code2") final String code2) {
        fileService.uploadCodeFile(file, code2);
        return ResponseEntity.ok().build();
    }

    /**
     * 사고유형별 처리방안 파일 다운로드
     *
     * @param code2 코드2
     */
    @GetMapping("/code/download")
    public void downloadCodeFile(@RequestParam("code2") final String code2, final HttpServletResponse response) {
        fileService.downloadCodeFile(code2, response);
    }

    // ====================================== 홈페이지 모니터링 파일 관리 ======================================

    /**
     * 홈페이지 모니터링 엑셀 업로드
     *
     * @param file 업로드할 엑셀 파일
     */
    @PostMapping("/homepage/upload")
    public ResponseEntity<Void> uploadHomepageUrlFile(@RequestParam("xlsfileinput") final MultipartFile file) {
        fileService.uploadHomepageUrlFile(file);
        return ResponseEntity.ok().build();
    }

    /**
     * 홈페이지용 양식 파일 다운로드 정보 조회
     *
     * @param request 요청 파라미터
     * @return 양식 파일 정보
     */
    @PostMapping("/homepage/form-info")
    public ResponseEntity<FileFormDownloadDTO> getHomepageFormDownloadInfo(@RequestBody final Map<String, Object> request) {
        final FileFormDownloadDTO formInfo = fileService.getHomepageFormDownloadInfo(request);
        return ResponseEntity.ok(formInfo);
    }
}
