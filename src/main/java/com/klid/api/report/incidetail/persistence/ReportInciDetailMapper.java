package com.klid.api.report.incidetail.persistence;

import com.klid.api.report.incidetail.dto.ReportInciDetailDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReportInciDetailMapper {

    List<ReportInciDetailDTO> selectInciDetail();
}
