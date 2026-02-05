package com.klid.api.logs.action.persistence;

import com.klid.api.logs.action.dto.InstitutionChartYAxisDTO;
import com.klid.api.logs.common.dto.InstitutionSummaryGridResDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserActionLogInstitutionMapper {

    List<InstitutionSummaryGridResDTO> selectCTRSSystemGridList(
            @Param("institutionCode") String institutionCode,
            @Param("codeLevel") Integer codeLevel,
            @Param("month") String month);

    List<InstitutionSummaryGridResDTO> selectOtherSystemGridList(
            @Param("systemType") String systemType,
            @Param("institutionCode") String institutionCode,
            @Param("month") String month);

    List<Integer> selectAllSystemChartXAxisList(@Param("month") String month);

    List<InstitutionChartYAxisDTO> selectCTRSChartDataList(
            @Param("institutionCode") String institutionCode,
            @Param("codeLevel") Integer codeLevel,
            @Param("month") String month);

    List<InstitutionChartYAxisDTO> selectOtherChartDataList(
            @Param("systemType") String systemType,
            @Param("institutionCode") String institutionCode,
            @Param("month") String month);
}
