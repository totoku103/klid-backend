package com.klid.api.monitoring.health.service;

import com.klid.api.monitoring.health.dto.HealthCheckUrlDTO;
import com.klid.api.monitoring.health.persistence.HealthCheckUrlMapper;
import com.klid.common.AppGlobal;
import com.klid.common.util.XLSFileBuilder;
import lombok.RequiredArgsConstructor;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 헬스체크 URL Service
 */
@Service("apiHealthCheckUrlService")
@RequiredArgsConstructor
public class HealthCheckUrlService {

    private final HealthCheckUrlMapper healthCheckUrlMapper;

    /**
     * 헬스체크 URL 목록 조회
     */
    public List<HealthCheckUrlDTO> getHealthCheckUrl(Map<String, Object> params) {
        return healthCheckUrlMapper.selectHealthCheckUrl(params);
    }

    /**
     * 헬스체크 URL 등록
     */
    public int addHealthCheckUrl(Map<String, Object> params) {
        healthCheckUrlMapper.addHealthCheckUrl(params);
        return (Integer) params.get("seqNo");
    }

    /**
     * 헬스체크 URL 수정
     */
    public void editHealthCheckUrl(Map<String, Object> params) {
        healthCheckUrlMapper.editHealthCheckUrl(params);
    }

    /**
     * 집중관리 등록
     */
    public void editWatchOn(Map<String, Object> params) {
        healthCheckUrlMapper.editWatchOn(params);
    }

    /**
     * 집중관리 해제
     */
    public void editWatchOff(Map<String, Object> params) {
        healthCheckUrlMapper.editWatchOff(params);
    }

    /**
     * 헬스체크 URL 상세 조회
     */
    public Map<String, Object> getDetailHealthCheckUrl(Map<String, Object> params) {
        final Map<String, Object> result = new HashMap<>();
        result.put("contents", healthCheckUrlMapper.selectDetailHealthCheckUrl(params));
        return result;
    }

    /**
     * 헬스체크 URL 삭제
     */
    public void delHealthCheckUrl(Map<String, Object> params) {
        healthCheckUrlMapper.delHealthCheckUrl(params);
    }

    /**
     * 헬스체크 장애이력 목록 조회
     */
    public List<HealthCheckUrlDTO> getHealthCheckHist(Map<String, Object> params) {
        return healthCheckUrlMapper.selectHealthCheckHist(params);
    }

    /**
     * 헬스체크 상태 통계 조회
     */
    public List<Map<String, Object>> getHealthCheckStat(Map<String, Object> params) {
        return healthCheckUrlMapper.selectHealthCheckStat(params);
    }

    /**
     * 엑셀 출력
     */
    public Map<String, String> export(Map<String, Object> params) {
        try {
            final String fileNm = "헬스체크 URL";
            final String sheetNm = "URL";
            final String[][] headers = new String[][] {
                    { "시도", "200" }, { "시군구", "200" }, {"홈페이지명", "200"},
                    { "URL", "200" }, { "장애여부", "200" }, {"구분", "200"},
                    { "사용여부", "200" }, { "집중감시", "200" }, {"등록시간", "200"}
            };

            final XLSFileBuilder xls = new XLSFileBuilder();
            xls.newSheet(sheetNm);
            xls.nextRow();
            xls.addHeaders(headers);
            xls.nextRow();

            final List<HealthCheckUrlDTO> list = healthCheckUrlMapper.selectHealthCheckUrl(params);
            if (list != null && !list.isEmpty()) {
                int colIdx = 0;
                int sheetCnt = 0;
                final int maximumRow = 10000;

                for (int i = 0; i < list.size(); i++) {
                    final HealthCheckUrlDTO dto = list.get(i);
                    colIdx = 0;
                    xls.setDataValue(colIdx++, dto.getParentName(), xls.centerValueStyle);
                    xls.setDataValue(colIdx++, dto.getInstNm(), xls.centerValueStyle);
                    xls.setDataValue(colIdx++, dto.getInstCenterNm(), xls.centerValueStyle);
                    xls.setDataValue(colIdx++, dto.getUrl(), xls.centerValueStyle);
                    xls.setDataValue(colIdx++, getValueByType(dto.getLastRes(), 3), xls.centerValueStyle);
                    xls.setDataValue(colIdx++, getValueByType(dto.getMoisYn(), 2), xls.centerValueStyle);
                    xls.setDataValue(colIdx++, getValueByType(dto.getUseYn(), 1), xls.centerValueStyle);
                    xls.setDataValue(colIdx++, getValueByType(dto.getCheckYn(), 1), xls.centerValueStyle);
                    xls.setDataValue(colIdx++, dto.getUpdtime(), xls.centerValueStyle);
                    xls.nextRow();

                    if ((i + 1) % maximumRow == 0) {
                        sheetCnt++;
                        xls.newSheet(sheetNm + "_" + sheetCnt);
                        xls.addHeaders(headers);
                        xls.nextRow();
                    }
                }
            }

            final String createTime = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
            final String fileName = AppGlobal.homePath + "/export/" + createTime + ".xls";
            xls.save(fileName);

            final Map<String, String> resultMap = new HashMap<>();
            resultMap.put("filePath", "/export/" + createTime + ".xls");
            resultMap.put("fileName", fileNm);
            return resultMap;
        } catch (Exception e) {
            throw new RuntimeException("Excel export failed", e);
        }
    }

    /**
     * 엑셀 Import
     */
    public String importXls(Map<String, Object> params, String xlsFilePath, String xlsFileName) {
        try {
            final String filename = xlsFilePath + "/" + xlsFileName;
            final HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(filename));
            final HSSFSheet sheet = workbook.getSheetAt(0);
            final int rows = sheet.getPhysicalNumberOfRows();

            final List<Integer> dtos = healthCheckUrlMapper.selectRelateInstCd(params);
            int lastSeq = healthCheckUrlMapper.selectLastSeq();
            final List<HealthCheckUrlDTO> list = new ArrayList<>();

            for (int i = 2; i <= rows; i++) {
                final int homeInstCd = (int) (sheet.getRow(i).getCell(0)).getNumericCellValue();
                final String homeName = (sheet.getRow(i).getCell(1)).getStringCellValue();
                final String homeUrl = (sheet.getRow(i).getCell(2)).getStringCellValue();
                final int homeUseYn = (int) (sheet.getRow(i).getCell(3)).getNumericCellValue();
                final int homeChkYn = (int) (sheet.getRow(i).getCell(4)).getNumericCellValue();
                final int homeChkSidoYn = (int) (sheet.getRow(i).getCell(5)).getNumericCellValue();

                final HealthCheckUrlDTO dto = new HealthCheckUrlDTO();
                dto.setInstCd(homeInstCd);

                if (dtos.contains(homeInstCd)) {
                    dto.setResCd(i);
                    dto.setSeqNo(lastSeq);
                    dto.setInstCenterNm(homeName);
                    dto.setUrl(homeUrl);
                    dto.setUseYn(homeUseYn);
                    dto.setLastRes(200);
                    dto.setMoisYn(0);
                    dto.setCheckYn(homeChkYn);
                    dto.setCheckSidoYn(homeChkSidoYn);
                    list.add(dto);
                    lastSeq++;
                }
            }

            if (!list.isEmpty()) {
                final Map<String, Object> map = new HashMap<>();
                map.put("dtos", dtos);
                final List<HealthCheckUrlDTO> urls = healthCheckUrlMapper.selectUrls(map);
                final List<HealthCheckUrlDTO> filteredList = removeDuplicates(list, urls);
                map.put("list", filteredList);

                if (!filteredList.isEmpty()) {
                    healthCheckUrlMapper.addHealthCheckMultiUrl(map);
                }
            }

            return "OK";
        } catch (Exception e) {
            throw new RuntimeException("Excel import failed", e);
        }
    }

    private String getValueByType(int value, int type) {
        switch (type) {
            case 1: // 예 아니오
                return value == 1 ? "예" : "아니오";
            case 2: // 지자체 중앙부처
                return value == 1 ? "중앙부처" : "지자체";
            case 3: // 장애 정상
                return value == 200 ? "정상" : "장애";
            default:
                return "";
        }
    }

    private List<HealthCheckUrlDTO> removeDuplicates(List<HealthCheckUrlDTO> list, List<HealthCheckUrlDTO> urls) {
        final List<Integer> duplList = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            for (HealthCheckUrlDTO url : urls) {
                if (list.get(i).getUrl().equals(url.getUrl()) &&
                    list.get(i).getInstCd() == url.getInstCd()) {
                    if (!duplList.contains(i)) {
                        duplList.add(i);
                    }
                }
            }
        }

        duplList.sort(Collections.reverseOrder());
        for (int index : duplList) {
            list.remove(index);
        }

        return list;
    }
}
