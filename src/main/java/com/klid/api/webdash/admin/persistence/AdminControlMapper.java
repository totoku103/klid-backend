package com.klid.api.webdash.admin.persistence;

import com.klid.api.webdash.admin.dto.*;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component("apiAdminControlMapper")
@Mapper
public interface AdminControlMapper {

    IncidentDTO selectIncidentStatus(Map<String, Object> paramMap);

    List<String> selectInciCnt(Map<String, Object> paramMap);

    List<TbzledgeCntDTO> selectTbzledgeCnt(Map<String, Object> paramMap);

    List<String> selectLocalInciCnt(Map<String, Object> paramMap);

    List<LocalStatusDTO> selectLocalStatus(Map<String, Object> paramMap);

    UrlStatusDTO selectUrlStatus(Map<String, Object> paramMap);

    List<SysErrorDTO> selectSysErrorStatus(Map<String, Object> paramMap);

    List<String> selectInciTypeCnt(Map<String, Object> paramMap);
}
