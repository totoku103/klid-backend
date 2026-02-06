package com.klid.api.report.inciprcstat.persistence;

import com.klid.api.report.inciprcstat.dto.ReportInciPrcsStatDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportInciPrcsStatMapper {

    List<ReportInciPrcsStatDTO> selectInciPrcsStat(@Param("sAuthMain") String sAuthMain,
                                                    @Param("instCd") String instCd,
                                                    @Param("dateType") String dateType,
                                                    @Param("startDt") String startDt,
                                                    @Param("endDt") String endDt);
}
