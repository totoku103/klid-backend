package com.klid.api.env.inst.persistence;

import com.klid.api.env.inst.dto.InstMgmtDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface InstMgmtMapper {
    List<InstMgmtDTO> selectInstMgmtList(Map<String, Object> params);
    InstMgmtDTO selectInstMgmtInfo(Map<String, Object> params);
    int insertInst(InstMgmtDTO dto);
    int updateInst(InstMgmtDTO dto);
    int deleteInst(String instCd);
}
