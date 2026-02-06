package com.klid.api.env.nationip.persistence;

import com.klid.api.env.nationip.dto.NationIPMgmtDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface NationIPMgmtMapper {
    List<NationIPMgmtDTO> selectNationMgmtList(Map<String, Object> params);
    NationIPMgmtDTO selectNationMgmtInfo(Map<String, Object> params);
    List<NationIPMgmtDTO> selectNationList_domain();
    NationIPMgmtDTO selectNationIP_nationCd(Map<String, Object> params);
    List<NationIPMgmtDTO> selectNationIPList(String nationCd);
    int insertNation(NationIPMgmtDTO dto);
    int deleteNation_all();
    int insertNationIp(NationIPMgmtDTO dto);
    int insertNationIp_list(List<NationIPMgmtDTO> list);
    int deleteNationIP_all();
    NationIPMgmtDTO selectNationDetail(String nationCd);
    int editNation(NationIPMgmtDTO dto);
}
