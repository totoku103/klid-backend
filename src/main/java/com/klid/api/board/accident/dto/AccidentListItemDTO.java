package com.klid.api.board.accident.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class AccidentListItemDTO {
    private String inciNo;          // 사고번호
    private String netDivName;      // 망구분명
    private String dclInstName;     // 신고기관명
    private String dmgInstName;     // 피해기관명
    private String inciTtl;         // 제목
    private String inciDttNm;       // 탐지명
    private String accdTypName;     // 사고유형명
    private String inciPrcsStatName; // 처리상태명
    private String transInciPrcsStatName; // 이관처리상태명
    private String transSidoPrcsStatName; // 시도이관처리상태명
    private String dclCrgr;         // 담당자
    private Date inciAcpnDt;        // 접수일시
    private Date inciUpdDt;         // 완료일시
    private String inciPrty;        // 우선순위코드
    private String inciPrtyName;    // 우선순위명
    private String inciDclCont;     // 사고내용
    private String inciInvsCont;    // 조사내용
    private String remarks;         // 비고
    private String weekYn;          // 주중주말
    private Integer dclInstCd;      // 신고기관코드
    private Integer dmgInstCd;      // 피해기관코드
    private String accdTypCd;       // 사고유형코드
    private String inciPrcsStat;    // 처리상태코드
    private String transInciPrcsStat; // 이관처리상태코드
    private String transSidoPrcsStat; // 시도이관처리상태코드
    private String inciTtlDtt;      // 제목(탐지명)
    private String tranSigunName;   // 이관기관명
    private String siEndDt;         // 종결일시(시도)
    private String dmgHpNo;         // 심각도
    private String acpnMthdName;    // 접수방법명
    private String dclHpNo;         // 사고유형(5분류)
    private Date inciDt;            // 사고일자
}
