package com.klid.api.board.accident.service;

import com.klid.api.board.accident.dto.AccidentListItemDTO;
import com.klid.api.board.accident.dto.AccidentSearchRequestDTO;
import com.klid.api.board.accident.persistence.AccidentListMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AccidentListService {

    private final AccidentListMapper accidentListMapper;

    /**
     * 사고 신고 목록 조회
     */
    public List<AccidentListItemDTO> getAccidentList(final AccidentSearchRequestDTO searchRequest) {
        final Map<String, Object> searchParams = buildSearchParams(searchRequest);
        return accidentListMapper.selectAccidentList(searchParams);
    }

    /**
     * 사고 신고 삭제
     */
    @Transactional
    public void deleteAccident(final String inciNo) {
        accidentListMapper.deleteAccident(inciNo);
    }

    /**
     * 검색 조건 파라미터 구성
     */
    private Map<String, Object> buildSearchParams(final AccidentSearchRequestDTO searchRequest) {
        final Map<String, Object> params = new HashMap<>();

        // 기본 검색 조건
        params.put("sInstCd", searchRequest.getSInstCd());
        params.put("sAuthMain", searchRequest.getSAuthMain());
        params.put("netDiv", searchRequest.getNetDiv());
        params.put("inciPrcsStatCd", searchRequest.getInciPrcsStatCd());
        params.put("transInciPrcsStatCd", searchRequest.getTransInciPrcsStatCd());
        params.put("transSidoPrcsStatCd", searchRequest.getTransSidoPrcsStatCd());
        params.put("accdTypCd", searchRequest.getAccdTypCd());
        params.put("inciPrtyCd", searchRequest.getInciPrtyCd());
        params.put("dclInstName", searchRequest.getDclInstName());
        params.put("dmgInstName", searchRequest.getDmgInstName());
        params.put("inciTtl", searchRequest.getInciTtl());
        params.put("inciNo", searchRequest.getInciNo());
        params.put("weekYn", searchRequest.getWeekYn());
        params.put("recoInciCd", searchRequest.getRecoInciCd());
        params.put("totalTitle", searchRequest.getTotalTitle());
        params.put("attIp", searchRequest.getAttIp());
        params.put("dmgIp", searchRequest.getDmgIp());

        // 날짜 검색 조건
        params.put("date1", searchRequest.getDate1());
        params.put("srchDateType", searchRequest.getSrchDateType());
        if (searchRequest.getDate1() != null && !searchRequest.getDate1().isEmpty()) {
            params.put("startDt", searchRequest.getDate1() + " 00:00:00");
            params.put("endDt", searchRequest.getDate2() + " 23:59:59");
        }

        // 사고내용 검색 - 띄어쓰기 기준 다중 검색 (IN 조건)
        if (searchRequest.getInciDclCont() != null && !searchRequest.getInciDclCont().isEmpty()) {
            final String[] arrSplit = searchRequest.getInciDclCont().split(" ");
            final List<String> list = new ArrayList<>();
            for (final String item : arrSplit) {
                list.add(item);
            }
            params.put("InciDclCont", list);
        }

        // 조사내용 검색 - 띄어쓰기 기준 다중 검색 (IN 조건)
        if (searchRequest.getInciInvsCont() != null && !searchRequest.getInciInvsCont().isEmpty()) {
            final String[] arrSplit = searchRequest.getInciInvsCont().split(" ");
            final List<String> list = new ArrayList<>();
            for (final String item : arrSplit) {
                list.add(item);
            }
            params.put("InciInvsCont", list);
        }

        // 시도의견 검색 - 띄어쓰기 기준 다중 검색 (IN 조건)
        if (searchRequest.getInciBelowCont() != null && !searchRequest.getInciBelowCont().isEmpty()) {
            final String[] arrSplit = searchRequest.getInciBelowCont().split(" ");
            final List<String> list = new ArrayList<>();
            for (final String item : arrSplit) {
                list.add(item);
            }
            params.put("InciBelowCont", list);
        }

        // 접수방법 복수 선택 - 콤마 기준 IN 조건
        if (searchRequest.getSrchAcpnMthd() != null && !searchRequest.getSrchAcpnMthd().isEmpty()) {
            final String[] arrSplit = searchRequest.getSrchAcpnMthd().split(",");
            final List<String> list = new ArrayList<>();
            for (final String item : arrSplit) {
                list.add(item);
            }
            params.put("srchAcpnMthdList", list);
        }

        return params;
    }
}
