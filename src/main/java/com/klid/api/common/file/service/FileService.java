package com.klid.api.common.file.service;

import com.klid.api.common.file.dto.FileDeleteResultDTO;
import com.klid.api.common.file.dto.FileFormDownloadDTO;
import com.klid.common.AppGlobal;
import com.klid.webapp.common.Criterion;
import com.klid.webapp.common.SessionManager;
import com.klid.webapp.common.code.dto.BoardMgmtDto;
import com.klid.webapp.common.code.service.CodeService;
import com.klid.webapp.common.file.dto.AttachfileDto;
import com.klid.webapp.common.file.service.FileDeleteService;
import com.klid.webapp.common.file.service.FileDownloadService;
import com.klid.webapp.common.file.service.FileUploadService;
import com.klid.webapp.main.sec.shareBoard.dto.ShareBoardDto;
import com.klid.webapp.main.sec.shareBoard.service.ShareBoardService;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 파일 관리 서비스
 *
 * <p>파일 업로드, 다운로드, 삭제 등의 비즈니스 로직을 처리합니다.</p>
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class FileService {

    private final FileDownloadService fileDownloadService;
    private final FileUploadService fileUploadService;
    private final FileDeleteService fileDeleteService;
    private final ShareBoardService shareBoardService;
    private final CodeService codeService;

    // Static fields for file paths (legacy compatibility)
    public static String emlcsvFilePath = null;
    public static String emlcsvFileName = null;
    public static String emlcsvExtName = null;
    public static String xlsFilePath = null;
    public static String xlsFileName = null;
    public static String xlsExtName = null;

    /**
     * 파일 다운로드
     */
    public void downloadFile(final long fileNo, final HttpServletResponse response) {
        final Criterion criterion = new Criterion();
        criterion.addParam("fileNo", fileNo);
        final List<AttachfileDto> files = fileDownloadService.searchFileName(criterion);

        if (!files.isEmpty()) {
            fileDownloadService.fileRender(response, files.get(0));
        }
    }

    /**
     * 게시판용 파일 업로드
     */
    @Transactional
    public void uploadBoardFile(final MultipartFile file, final String boardNo) {
        final Criterion boardDetailCriterion = new Criterion();
        boardDetailCriterion.addParam("boardNo", boardNo);

        final List<ShareBoardDto> list = shareBoardService.getBoardDetail(boardDetailCriterion);
        final String boardType = list.get(0).getBultnType();

        // 게시판 타입별 GUID 매핑
        final String boardGuid = getBoardGuid(boardType);

        final String tempFileName = generateTempFileName(file);
        final String originalFileName = file.getOriginalFilename();

        // 게시판별 파일 확장자 제약 확인
        final Criterion boardCriterion = new Criterion();
        boardCriterion.addParam("guid", boardGuid);
        final BoardMgmtDto codeDetail = (BoardMgmtDto) codeService.getDetailBoardMgmtList(boardCriterion).getResultData();

        final String contentType = originalFileName.substring(originalFileName.lastIndexOf(".") + 1).toLowerCase();

        // 확장자 검증 및 업로드
        if (codeDetail != null && codeDetail.getFileExt() != null) {
            if (codeDetail.getFileExt().toLowerCase().indexOf(contentType) == -1) {
                log.warn("허용되지 않은 파일 확장자: {}", contentType);
                return;
            }
        }

        fileUploadService.fileUploadLocal(file, tempFileName);

        final String todayPath = getTodayPath();
        final String savePath = "\\" + todayPath;

        final Criterion criterion = new Criterion();
        criterion.addParam("fileName", tempFileName);
        criterion.addParam("originalFileName", originalFileName);
        criterion.addParam("fileType", originalFileName.substring(originalFileName.lastIndexOf(".") + 1));
        criterion.addParam("fileSize", file.getSize());
        criterion.addParam("boardNo", boardNo);
        criterion.addParam("filePath", savePath);

        fileUploadService.insertFileInfo(criterion);
    }

    /**
     * 파일 삭제
     */
    @Transactional
    public FileDeleteResultDTO deleteFile(final Map<String, Object> request) {
        final List<AttachfileDto> files = fileDownloadService.searchFileName(new Criterion(request));

        if (fileDeleteService.deleteFileInfo(new Criterion(request))) {
            fileDeleteService.deleteFile(files);
            return new FileDeleteResultDTO(true, "파일이 삭제되었습니다.");
        } else {
            return new FileDeleteResultDTO(false, "삭제할 파일이 없습니다.");
        }
    }

    /**
     * 그리드 데이터 엑셀 내보내기
     */
    public Map<String, Object> exportGrid(final Map<String, Object> request) {
        return (Map<String, Object>) fileDownloadService.exportGrid(new Criterion(request)).getResultData();
    }

    /**
     * 이미지 내보내기
     */
    public void exportImage(final String fileName, final String imageData, final HttpServletResponse response) {
        String cleanedData = imageData.replace("data:image/jpeg;base64,", "");
        final byte[] byteData = Base64.decodeBase64(cleanedData.getBytes());

        writeToResponse(response, fileName, "jpg", byteData);
    }

    /**
     * Highchart를 export 폴더에 저장
     */
    public void saveHighchart(final Map<String, Object> request) {
        final String fileName = request.get("fname").toString();
        String imageData = request.get("imgData").toString();
        imageData = imageData.replace("data:image/png;base64,", "");
        final byte[] byteData = Base64.decodeBase64(imageData.getBytes());
        final String path = AppGlobal.homePath + "/export/" + fileName;

        try {
            FileUtils.writeByteArrayToFile(new File(path), byteData);
        } catch (final IOException e) {
            log.error("Highchart 저장 실패", e);
            throw new RuntimeException("Highchart 저장 실패", e);
        }
    }

    /**
     * Highchart를 PNG 파일로 내보내기
     */
    public void exportHighchart(final String fileName, final String imageData, final HttpServletResponse response) {
        String cleanedData = imageData.replace("data:image/png;base64,", "");
        final byte[] byteData = Base64.decodeBase64(cleanedData.getBytes());

        writeToResponse(response, fileName, "png", byteData);
    }

    // ====================================== 사고접수용 파일 관리 ======================================

    /**
     * 사고접수용 파일 업로드
     */
    @Transactional
    public void uploadAccidentFile(final MultipartFile file, final String incidentNo) {
        final String tempFileName = generateTempFileName(file);
        final String originalFileName = file.getOriginalFilename();
        final String todayPath = getTodayPath();
        final String savePath = "\\" + todayPath;

        fileUploadService.fileUploadLocal(file, tempFileName);

        final Criterion criterion = new Criterion();
        criterion.addParam("fileName", tempFileName);
        criterion.addParam("originalFileName", originalFileName);
        criterion.addParam("fileType", originalFileName.substring(originalFileName.lastIndexOf(".") + 1));
        criterion.addParam("fileSize", file.getSize());
        criterion.addParam("inciNo", incidentNo);
        criterion.addParam("savePath", savePath);

        fileUploadService.insertAccFileInfo(criterion);
    }

    /**
     * 사고접수용 EML/CSV 파일 업로드
     */
    public void uploadAccidentEmlCsvFile(final MultipartFile file, final String type) {
        final String tempFileName = generateTempEmlFileName(file, type);
        final String originalFileName = file.getOriginalFilename();
        final String todayPath = getTodayPath();
        final String savePath = "\\" + todayPath;

        fileUploadService.fileUploadLocal(file, tempFileName);

        final String fileType = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);

        // Static fields 설정 (legacy compatibility)
        emlcsvFilePath = Paths.get(AppGlobal.uploadPath).toString() + savePath;
        emlcsvFileName = tempFileName;
        emlcsvExtName = fileType;

        log.debug("EML/CSV 파일 업로드 완료 - 파일명: {}, 확장자: {}", tempFileName, fileType);
    }

    /**
     * 사고접수용 양식 파일 다운로드 정보 조회
     */
    public FileFormDownloadDTO getAccidentFormDownloadInfo(final Map<String, Object> request) {
        final String type = request.get("type").toString();

        if ("csv".equals(type)) {
            return new FileFormDownloadDTO("/export/lcsc.csv", "csv_form", ".csv");
        } else if ("eml".equals(type)) {
            return new FileFormDownloadDTO("/export/lcsc.eml", "eml_form", ".eml");
        }

        throw new IllegalArgumentException("지원하지 않는 파일 타입: " + type);
    }

    /**
     * 사고접수용 파일 삭제
     */
    @Transactional
    public FileDeleteResultDTO deleteAccidentFile(final Map<String, Object> request) {
        final List<AttachfileDto> files = fileDownloadService.searchAccFileName(new Criterion(request));

        if (fileDeleteService.deleteAccFileInfo(new Criterion(request))) {
            fileDeleteService.deleteAccFile(files);
            return new FileDeleteResultDTO(true, "파일이 삭제되었습니다.");
        } else {
            return new FileDeleteResultDTO(false, "삭제할 파일이 없습니다.");
        }
    }

    /**
     * 사고접수용 파일 다운로드
     */
    public void downloadAccidentFile(final long fileNo, final HttpServletResponse response) {
        final Criterion criterion = new Criterion();
        criterion.addParam("fileNo", fileNo);
        final List<AttachfileDto> files = fileDownloadService.searchAccFileName(criterion);

        if (!files.isEmpty()) {
            fileDownloadService.fileRender(response, files.get(0));
        }
    }

    // ====================================== 사고유형별 처리방안 파일 관리 ======================================

    /**
     * 사고유형별 처리방안 파일 업로드
     */
    @Transactional
    public void uploadCodeFile(final MultipartFile file, final String code2) {
        final String originalFileName = file.getOriginalFilename();
        final String fileType = "." + originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
        final String fileName = code2 + fileType;

        fileUploadService.fileUploadHelp(file, fileName, code2);

        final Criterion criterion = new Criterion();
        criterion.addParam("fileName", fileName);
        criterion.addParam("code2", code2);

        fileUploadService.addHelpFile(criterion);
    }

    /**
     * 사고유형별 처리방안 파일 다운로드
     */
    public void downloadCodeFile(final String code2, final HttpServletResponse response) {
        fileDownloadService.fileRenderHelp(response, code2);
    }

    // ====================================== 홈페이지 모니터링 파일 관리 ======================================

    /**
     * 홈페이지 모니터링 엑셀 업로드
     */
    public void uploadHomepageUrlFile(final MultipartFile file) {
        final String tempFileName = generateTempEmlFileName(file, "xls");
        final String originalFileName = file.getOriginalFilename();
        final String todayPath = getTodayPath();
        final String savePath = "\\" + todayPath;

        fileUploadService.fileUploadLocal(file, tempFileName);

        final String fileType = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);

        // Static fields 설정 (legacy compatibility)
        xlsFilePath = Paths.get(AppGlobal.uploadPath).toString() + savePath;
        xlsFileName = tempFileName;
        xlsExtName = fileType;

        log.debug("홈페이지 URL 파일 업로드 완료 - 파일명: {}, 확장자: {}", tempFileName, fileType);
    }

    /**
     * 홈페이지용 양식 파일 다운로드 정보 조회
     */
    public FileFormDownloadDTO getHomepageFormDownloadInfo(final Map<String, Object> request) {
        return new FileFormDownloadDTO("/export/home.xls", "xls_form", ".xls");
    }

    // ====================================== Private Helper Methods ======================================

    private String getBoardGuid(final String boardType) {
        return switch (boardType) {
            case "notice" -> "B4529762-C067-4731-9129-B84FF840063A"; // 공지
            case "secu_data" -> "35E56A6F-B4CF-4255-8300-A55BA44F7BA6"; // 자료실
            case "data_sbms" -> "11B3C551-A9E2-4361-AC5C-D45751AD5E64"; // 공유
            case "qna" -> "270241B8-AA1C-4BBA-948F-B9AF8BBD74DD"; // 문의
            default -> "B4529762-C067-4731-9129-B84FF840063A"; // 기본값: 공지
        };
    }

    private String generateTempFileName(final MultipartFile file) {
        final long time = System.currentTimeMillis();
        final SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss_SSS");
        final String uploadTime = format.format(new Date(time));

        return uploadTime + "_" + SessionManager.getUser().getUserId() + "_" + file.getOriginalFilename();
    }

    private String generateTempEmlFileName(final MultipartFile file, final String type) {
        final long time = System.currentTimeMillis();
        final SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss_SSS");
        final String uploadTime = format.format(new Date(time));

        return uploadTime + "_" + type;
    }

    private String getTodayPath() {
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        final Calendar calendar = Calendar.getInstance();
        return sdf.format(calendar.getTime());
    }

    private void writeToResponse(final HttpServletResponse response, final String fileName,
                                  final String fileExt, final byte[] data) {
        ServletOutputStream outputStream = null;
        try {
            final String encodedFileName = URLEncoder.encode(fileName, "UTF-8");
            response.setHeader("Content-Disposition", "attachment; filename=\"" + encodedFileName + "." + fileExt + "\"");
            response.setContentType("application/octet-stream; charset=utf-8");
            outputStream = response.getOutputStream();
            outputStream.write(data);
        } catch (final UnsupportedEncodingException e) {
            log.error("파일명 인코딩 실패", e);
        } catch (final IOException e) {
            log.error("파일 쓰기 실패", e);
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.flush();
                    outputStream.close();
                } catch (final IOException e) {
                    log.error("OutputStream 닫기 실패", e);
                }
            }
        }
    }
}
