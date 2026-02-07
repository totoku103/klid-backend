package com.klid.api.env.inst.service;

import com.klid.api.env.inst.dto.InstMgmtDTO;
import com.klid.api.env.inst.persistence.InstMgmtMapper;
import com.klid.api.hist.useract.dto.UserActHistDTO;
import com.klid.api.hist.useract.persistence.UserActHistMapper;
import com.klid.common.AppGlobal;
import com.klid.common.util.XLSFileBuilder;
import com.klid.webapp.common.SessionManager;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
public class InstitutionService {

    private final InstMgmtMapper instMgmtMapper;
    private final UserActHistMapper userActHistMapper;

    // GUID for institution management activity history
    private static final String GUID = "9413D8BE-B9C3-48C1-81AA-178523E09C03";
    private static final String REF_TABLE = "TSMINST";

    public List<InstMgmtDTO> getList(final Map<String, Object> params) {
        return instMgmtMapper.selectInstMgmtList(params);
    }

    public InstMgmtDTO getInfo(final String instCd) {
        final Map<String, Object> params = new HashMap<>();
        params.put("instCd", instCd);
        return instMgmtMapper.selectInstMgmtInfo(params);
    }

    public String checkDuplicate(final String instCd) {
        final Map<String, Object> params = new HashMap<>();
        params.put("instCd", instCd);
        final InstMgmtDTO info = instMgmtMapper.selectInstMgmtInfo(params);
        return info != null ? "OVERLAP" : "SUCCESS";
    }

    @Transactional
    public void add(final InstMgmtDTO dto) {
        // Check if already exists
        final Map<String, Object> params = new HashMap<>();
        params.put("instCd", dto.getInstCd());
        final InstMgmtDTO existing = instMgmtMapper.selectInstMgmtInfo(params);
        if (existing != null) {
            throw new RuntimeException("이미 존재하는 기관코드입니다.");
        }

        dto.setLocalCd(AppGlobal.localCd);
        instMgmtMapper.insertInst(dto);
        saveUserActHist("C");
    }

    @Transactional
    public void update(final String instCd, final InstMgmtDTO dto) {
        dto.setInstCd(instCd);
        instMgmtMapper.updateInst(dto);
        saveUserActHist("U");
    }

    @Transactional
    public void delete(final List<String> instCds) {
        for (final String instCd : instCds) {
            instMgmtMapper.deleteInst(instCd);
        }
        saveUserActHist("D");
    }

    public Map<String, String> export(final Map<String, Object> params) throws Exception {
        final String fileNm = "기관관리";
        final String sheetNm = "기관관리";
        final String[][] headers = {
            {"기관코드", "150"}, {"기관명", "200"}, {"차상위기관", "200"},
            {"지역", "150"}, {"유형분류 중", "150"}, {"유형분류 소", "150"}, {"사용여부", "100"}, {"등록일시", "150"}
        };

        final XLSFileBuilder xls = new XLSFileBuilder();
        xls.newSheet(sheetNm);
        xls.addHeaders(headers);
        xls.nextRow();

        final List<InstMgmtDTO> list = instMgmtMapper.selectInstMgmtList(params);
        if (list != null && !list.isEmpty()) {
            int sheetCnt = 0;
            final int maxRow = 10000;
            for (int i = 0; i < list.size(); i++) {
                final InstMgmtDTO dto = list.get(i);
                int colIdx = 0;
                xls.setDataValue(colIdx++, dto.getInstCd(), xls.centerValueStyle);
                xls.setDataValue(colIdx++, dto.getInstNm());
                xls.setDataValue(colIdx++, dto.getPntSInstCdNm());
                xls.setDataValue(colIdx++, dto.getLocalCdNm(), xls.centerValueStyle);
                xls.setDataValue(colIdx++, dto.getTypeMidNm(), xls.centerValueStyle);
                xls.setDataValue(colIdx++, dto.getTypeSmlNm(), xls.centerValueStyle);
                xls.setDataValue(colIdx++, dto.getUseYnNm(), xls.centerValueStyle);
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

    private void saveUserActHist(final String actType) {
        final UserActHistDTO histDTO = new UserActHistDTO();
        histDTO.setGuid(GUID);
        histDTO.setActType(actType);
        histDTO.setRegUserId(SessionManager.getUser().getUserId());
        histDTO.setRefTable(REF_TABLE);
        histDTO.setRegUserName(SessionManager.getUser().getUserName());
        userActHistMapper.addUserActHist(histDTO);
    }
}
