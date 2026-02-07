package com.klid.api.env.nationip.service;

import com.klid.api.env.nationip.dto.NationIPMgmtDTO;
import com.klid.api.env.nationip.persistence.NationIPMgmtMapper;
import com.klid.common.AppGlobal;
import com.klid.common.util.XLSFileBuilder;
import com.klid.webapp.common.SessionManager;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.*;

@Service
@RequiredArgsConstructor
public class NationIPService {

    private final NationIPMgmtMapper nationIPMgmtMapper;

    public List<NationIPMgmtDTO> getNationList(final Map<String, Object> params) {
        return nationIPMgmtMapper.selectNationMgmtList(params);
    }

    public NationIPMgmtDTO getNationInfo(final Map<String, Object> params) {
        return nationIPMgmtMapper.selectNationMgmtInfo(params);
    }

    public List<NationIPMgmtDTO> getDomainList() {
        return nationIPMgmtMapper.selectNationList_domain();
    }

    public NationIPMgmtDTO getByNationCode(final Map<String, Object> params) {
        return nationIPMgmtMapper.selectNationIP_nationCd(params);
    }

    public List<NationIPMgmtDTO> getIPList(final String nationCd) {
        return nationIPMgmtMapper.selectNationIPList(nationCd);
    }

    @Transactional
    public void uploadCSV(final MultipartFile file) {
        final String fileName = file.getOriginalFilename();
        if (fileName == null || fileName.isEmpty()) {
            throw new RuntimeException("파일을 선택해주세요.");
        }

        final String usrId = SessionManager.getUser().getUserId();

        try (final InputStream is = file.getInputStream();
             final BufferedReader br = new BufferedReader(new InputStreamReader(is, "EUC-KR"))) {

            // Get domain list for mapping
            final List<NationIPMgmtDTO> domainList = nationIPMgmtMapper.selectNationList_domain();

            // Delete all existing IPs before bulk insert
            nationIPMgmtMapper.deleteNationIP_all();

            int insertCount = 0;
            String line;
            while ((line = br.readLine()) != null) {
                final String[] columns = line.split(",");
                if (columns.length == 0) break;

                // Trim whitespace and remove quotes
                for (int z = 0; z < columns.length; z++) {
                    String tmp = columns[z];
                    tmp = tmp.replaceAll("(^\\p{Z}+|\\p{Z}+$)", "");
                    tmp = tmp.replaceAll("\"", "");
                    columns[z] = tmp;
                }

                if (columns.length < 6) continue;

                final String sipStr = columns[0];
                final String eipStr = columns[1];
                final String sip = columns[2];
                final String eip = columns[3];
                final String domain = columns[4];
                final String nationNm = columns[5];

                // Skip if any required field is blank
                if (StringUtils.isBlank(sip) || StringUtils.isBlank(eip)
                        || StringUtils.isBlank(sipStr) || StringUtils.isBlank(eipStr)
                        || StringUtils.isBlank(domain) || StringUtils.isBlank(nationNm)) {
                    continue;
                }

                // Find nationCd from domain
                String nationCd = null;
                for (final NationIPMgmtDTO domainDTO : domainList) {
                    if (domain.equals(domainDTO.getDomain())) {
                        nationCd = domainDTO.getNationCd();
                        break;
                    }
                }

                if (nationCd == null) continue;

                final NationIPMgmtDTO dto = new NationIPMgmtDTO();
                dto.setNationCd(nationCd);
                dto.setSip(sip);
                dto.setEip(eip);
                dto.setDomain(domain);
                dto.setNationNm(nationNm);
                nationIPMgmtMapper.insertNationIp(dto);
                insertCount++;
            }

            if (insertCount == 0) {
                throw new RuntimeException("저장할 데이터가 존재하지 않습니다.");
            }

        } catch (IOException e) {
            throw new RuntimeException("파일 처리 중 오류가 발생했습니다.", e);
        }
    }

    @Transactional
    public void delete() {
        // Legacy implementation was a no-op, keeping structure
        nationIPMgmtMapper.deleteNationIP_all();
    }

    public Map<String, String> exportNations(final Map<String, Object> params) throws Exception {
        final String fileNm = "국가별IP대역관리";
        final String sheetNm = "국가별IP대역관리";
        final String[][] headers = {
            {"번호", "150"}, {"국가명", "200"}, {"대륙명", "200"}, {"한글명", "200"}, {"갱신일시", "150"}
        };

        final List<NationIPMgmtDTO> list = nationIPMgmtMapper.selectNationMgmtList(params);

        final XLSFileBuilder xls = new XLSFileBuilder();
        xls.newSheet(sheetNm);

        // Total count subtitle
        xls.addSubTitle(0, "총 개수", xls.styleHelper.subTitleStyle);
        xls.addCustomCell(1, list != null ? list.size() : 0, xls.styleHelper.subValueStyle, 2);
        xls.nextRow();
        xls.nextRow();

        xls.addHeaders(headers);
        xls.nextRow();

        if (list != null && !list.isEmpty()) {
            int sheetCnt = 0;
            final int maxRow = 10000;
            for (int i = 0; i < list.size(); i++) {
                final NationIPMgmtDTO dto = list.get(i);
                int colIdx = 0;
                xls.setDataValue(colIdx++, dto.getNationCd(), xls.centerValueStyle);
                xls.setDataValue(colIdx++, dto.getNationNm());
                xls.setDataValue(colIdx++, dto.getContinental());
                xls.setDataValue(colIdx++, dto.getKrNm());
                xls.setDataValue(colIdx++, dto.getRegDt(), xls.centerValueStyle);
                xls.nextRow();

                if ((i + 1) % maxRow == 0) {
                    sheetCnt++;
                    xls.newSheet(sheetNm + "_" + sheetCnt);
                    xls.addHeaders(headers);
                    xls.nextRow();
                }
            }
        }

        final String createTime = DateFormatUtils.format(new Date(), "yyyyMMddHHmmssSSS");
        final String fileName = AppGlobal.homePath + "/export/" + createTime + ".xls";
        xls.save(fileName);

        final Map<String, String> resultMap = new HashMap<>();
        resultMap.put("filePath", "/export/" + createTime + ".xls");
        resultMap.put("fileName", fileNm);
        return resultMap;
    }

    public Map<String, String> exportIPs(final String nationCd, final String nationNm, final String continental) throws Exception {
        final String fileNm = "국가별IP리스트";
        final String sheetNm = "국가별IP리스트";
        final String[][] headers = {
            {"시작IP", "200"}, {"종료IP", "200"}
        };

        final XLSFileBuilder xls = new XLSFileBuilder();
        xls.newSheet(sheetNm);
        xls.nextRow();

        // Nation info subtitle
        xls.addSubTitle(0, "대륙명", xls.styleHelper.subTitleStyle);
        xls.addCustomCell(1, continental != null ? continental : "", xls.styleHelper.subValueStyle, 2);
        xls.addSubTitle(3, "국가명", xls.styleHelper.subTitleStyle);
        xls.addCustomCell(4, nationNm != null ? nationNm : "", xls.styleHelper.subValueStyle, 2);
        xls.nextRow();
        xls.nextRow();

        xls.addHeaders(headers);
        xls.nextRow();

        final List<NationIPMgmtDTO> list = nationIPMgmtMapper.selectNationIPList(nationCd);
        if (list != null && !list.isEmpty()) {
            int sheetCnt = 0;
            final int maxRow = 10000;
            for (int i = 0; i < list.size(); i++) {
                final NationIPMgmtDTO dto = list.get(i);
                int colIdx = 0;
                xls.setDataValue(colIdx++, dto.getSip(), xls.centerValueStyle);
                xls.setDataValue(colIdx++, dto.getEip(), xls.centerValueStyle);
                xls.nextRow();

                if ((i + 1) % maxRow == 0) {
                    sheetCnt++;
                    xls.newSheet(sheetNm + "_" + sheetCnt);
                    xls.addHeaders(headers);
                    xls.nextRow();
                }
            }
        }

        final String createTime = DateFormatUtils.format(new Date(), "yyyyMMddHHmmssSSS");
        final String fileName = AppGlobal.homePath + "/export/" + createTime + ".xls";
        xls.save(fileName);

        final Map<String, String> resultMap = new HashMap<>();
        resultMap.put("filePath", "/export/" + createTime + ".xls");
        resultMap.put("fileName", fileNm);
        return resultMap;
    }

    public NationIPMgmtDTO getNationDetail(final String nationCd) {
        return nationIPMgmtMapper.selectNationDetail(nationCd);
    }

    @Transactional
    public void editNation(final String nationCd, final NationIPMgmtDTO dto) {
        dto.setNationCd(nationCd);
        nationIPMgmtMapper.editNation(dto);
    }
}
