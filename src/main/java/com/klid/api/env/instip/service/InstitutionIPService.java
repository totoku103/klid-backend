package com.klid.api.env.instip.service;

import com.klid.api.env.instip.dto.InstIPMgmtDTO;
import com.klid.api.env.instip.persistence.InstIPMgmtMapper;
import com.klid.api.hist.useract.dto.UserActHistDTO;
import com.klid.api.hist.useract.persistence.UserActHistMapper;
import com.klid.common.AppGlobal;
import com.klid.common.util.ConvertUtil;
import com.klid.common.util.XLSFileBuilder;
import com.klid.webapp.common.SessionManager;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
public class InstitutionIPService {

    private final InstIPMgmtMapper instIPMgmtMapper;
    private final UserActHistMapper userActHistMapper;

    private static final String GUID = "2070E22A-A433-11E8-898A-408D5CF61E72";
    private static final String REF_TABLE = "TSMINSTIP";

    public List<InstIPMgmtDTO> getList(final Map<String, Object> params) {
        if (params.containsKey("sInstIp")) {
            final String sInstIp = params.get("sInstIp").toString();
            if (!sInstIp.isEmpty()) {
                final long sInstIpLong = ConvertUtil.ipv4ToLong(sInstIp);
                params.put("sInstIp", sInstIpLong);
            }
        }
        return instIPMgmtMapper.selectInstIPMgmtList(params);
    }

    public List<InstIPMgmtDTO> getByInstitution(final String instCd) {
        return instIPMgmtMapper.selectInstIPList_instCd(instCd);
    }

    @Transactional
    public void add(final InstIPMgmtDTO dto) {
        final long sipLong = ConvertUtil.ipv4ToLong(dto.getSip());
        final long eipLong = ConvertUtil.ipv4ToLong(dto.getEip());
        dto.setSip(String.valueOf(sipLong));
        dto.setEip(String.valueOf(eipLong));
        instIPMgmtMapper.insertInstIP(dto);
        saveUserActHist("C");
    }

    @Transactional
    public void update(final Long seq, final InstIPMgmtDTO dto) {
        dto.setSeq(seq);
        final long sipLong = ConvertUtil.ipv4ToLong(dto.getSip());
        final long eipLong = ConvertUtil.ipv4ToLong(dto.getEip());
        dto.setSip(String.valueOf(sipLong));
        dto.setEip(String.valueOf(eipLong));
        instIPMgmtMapper.updateInstIP(dto);
        saveUserActHist("U");
    }

    @Transactional
    public void delete(final List<InstIPMgmtDTO> items) {
        for (final InstIPMgmtDTO item : items) {
            instIPMgmtMapper.deleteInstIP(item);
        }
        saveUserActHist("D");
    }

    public Map<String, String> export(final Map<String, Object> params) throws Exception {
        final String fileNm = "기관별IP대역관리";
        final String sheetNm = "기관별IP대역관리";
        final String[][] headers = {
            {"번호", "120"}, {"차상위기관", "150"}, {"기관명", "300"},
            {"망구분", "100"}, {"IP", "250"}, {"설명", "250"}
        };

        final XLSFileBuilder xls = new XLSFileBuilder();
        xls.newSheet(sheetNm);
        xls.addHeaders(headers);
        xls.nextRow();

        final List<InstIPMgmtDTO> list = getList(params);
        if (list != null && !list.isEmpty()) {
            final int totalCnt = list.size() + 1;
            int sheetCnt = 0;
            final int maxRow = 10000;
            for (int i = 0; i < list.size(); i++) {
                final InstIPMgmtDTO dto = list.get(i);
                int colIdx = 0;
                xls.setDataValue(colIdx++, String.valueOf(totalCnt - dto.getNo()), xls.centerValueStyle);
                xls.setDataValue(colIdx++, dto.getPntSInstCdNm(), xls.centerValueStyle);
                xls.setDataValue(colIdx++, dto.getInstNm());
                xls.setDataValue(colIdx++, dto.getIpCd(), xls.centerValueStyle);
                xls.setDataValue(colIdx++, dto.getSip() + " ~ " + dto.getEip(), xls.centerValueStyle);
                xls.setDataValue(colIdx++, dto.getIpCont(), xls.centerValueStyle);
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
