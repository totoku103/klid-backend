package com.klid.api.env.instip.persistence;

import com.klid.api.env.instip.dto.InstIPMgmtDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface InstIPMgmtMapper {
    List<InstIPMgmtDTO> selectInstIPMgmtList(Map<String, Object> params);
    List<InstIPMgmtDTO> selectInstIPList_instCd(String instCd);
    int insertInstIP(InstIPMgmtDTO dto);
    int updateInstIP(InstIPMgmtDTO dto);
    int deleteInstIP(InstIPMgmtDTO dto);
}
