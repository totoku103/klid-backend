package com.klid.api.report.securityresult.persistence;

import com.klid.api.report.securityresult.dto.ReportResultExceptlistDTO;
import com.klid.api.report.securityresult.dto.ReportResultListDTO;
import com.klid.api.report.securityresult.dto.ReportResultTotalDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportSecurityResultMapper {

    ReportResultTotalDTO selectResultTotal(@Param("startDt") String startDt,
                                           @Param("endDt") String endDt,
                                           @Param("sInstCd") String sInstCd);

    List<ReportResultListDTO> selectResultList(@Param("startDt") String startDt,
                                                @Param("endDt") String endDt);

    List<ReportResultExceptlistDTO> selectResultExceptlist(@Param("startDt") String startDt,
                                                            @Param("endDt") String endDt);

    List<ReportResultListDTO> selectSecurityTitle(@Param("startDt") String startDt,
                                                   @Param("endDt") String endDt);
}
