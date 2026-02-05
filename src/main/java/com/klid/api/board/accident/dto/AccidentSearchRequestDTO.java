package com.klid.api.board.accident.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccidentSearchRequestDTO {
    private String sInstCd;         // 기관코드
    private String sAuthMain;       // 권한
    private String netDiv;          // 망구분
    private String inciPrcsStatCd;  // 처리상태코드
    private String transInciPrcsStatCd; // 이관처리상태코드
    private String transSidoPrcsStatCd; // 시도이관처리상태코드
    private String accdTypCd;       // 사고유형코드
    private String inciPrtyCd;      // 우선순위코드
    private String dclInstName;     // 신고기관명
    private String dmgInstName;     // 피해기관명
    private String inciTtl;         // 제목
    private String inciNo;          // 사고번호
    private String date1;           // 시작일
    private String date2;           // 종료일
    private String inciDclCont;     // 사고내용
    private String inciInvsCont;    // 조사내용
    private String inciBelowCont;   // 시도의견
    private String attIp;           // 공격IP
    private String dmgIp;           // 피해IP
    private String srchAcpnMthd;    // 접수방법(복수선택, 콤마구분)
    private String srchDateType;    // 검색날짜타입
    private String totalTitle;      // 통합검색
    private String weekYn;          // 주중주말
    private String recoInciCd;      // 권고사항코드
}
