package com.klid.api.webdash.mois.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WebDashMoisDTO {
    // Threat Now fields
    private String pastThreat;
    private String nowThreat;
    private String instCd;
    private String modDt;

    // HM HC URL fields
    private String instNm;
    private String lastRes;
    private String localNm;
    private String localCd;

    // Region Status fields
    private Long receiptCnt;
    private Long processCnt;
    private Long completeCnt;

    // Region Status Auto fields
    private String regTime;
    private String sumJson;

    // Dash Config List fields
    private String workCp1;
    private String workCp2;
    private String workCp3;
    private String workPs1;
    private String workPs2;
    private String workPs3;
    private String workMng1;
    private String workMng2;
    private String workMng3;
    private String workCon1;
    private String workCon2;
    private String workCon3;
    private Long attCnt;
    private Long nirsCdM;
    private Long nirsCdA;
    private Long nirsCdD;
    private Long nirsDdM;
    private Long nirsDdA;
    private Long nirsDdD;
    private Long nirsHkM;
    private Long nirsHkA;
    private Long nirsHkD;
    private Long nirsEtM;
    private Long nirsEtA;
    private Long nirsEtD;
    private Long klidCdM;
    private Long klidCdA;
    private Long klidCdD;
    private Long klidDdM;
    private Long klidDdA;
    private Long klidDdD;
    private Long klidHkM;
    private Long klidHkA;
    private Long klidHkD;
    private Long klidEtM;
    private Long klidEtA;
    private Long klidEtD;
    private Long gtAv;
    private Long gtMax;
    private Long ctAv;
    private Long ctMax;
    private Long ssAv;
    private Long ssMax;
    private String gtRst;
    private String ctRst;
    private String ssRst;
    private Long errSvr;
    private Long errNet;
    private Long errStr;
    private Long errBak;
    private Long errHom;
    private String noti1;
    private String noti2;
    private String secu1;
    private String secu2;
    private String crtTime;
    private String modTime;
    private String datTime;

    // Dash Chart Sum fields
    private Long nirsAsum;
    private Long nirsMsum;
    private Long klidAsum;
    private Long klidMsum;
}
