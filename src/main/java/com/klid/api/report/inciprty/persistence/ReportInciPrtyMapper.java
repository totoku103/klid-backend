package com.klid.api.report.inciprty.persistence;

import com.klid.api.report.inciprty.dto.ReportInciPrtyDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportInciPrtyMapper {

    List<ReportInciPrtyDTO> selectInciPrtyList(@Param("sAuthMain") String sAuthMain,
                                                @Param("instCd") String instCd,
                                                @Param("dateType") String dateType,
                                                @Param("startDt") String startDt,
                                                @Param("endDt") String endDt);
}
