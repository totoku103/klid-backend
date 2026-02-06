package com.klid.api.report.incidetail.persistence;

import com.klid.api.report.incidetail.dto.ReportInciDetailDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportInciDetailMapper {

    List<ReportInciDetailDTO> selectInciDetail();
}
