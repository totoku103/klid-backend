package com.klid.api.env.inst.persistence;

import com.klid.api.env.inst.dto.InstMgmtDTO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface InstMgmtMapper {
    List<InstMgmtDTO> selectInstMgmtList(Map<String, Object> params);
    InstMgmtDTO selectInstMgmtInfo(Map<String, Object> params);
    int insertInst(InstMgmtDTO dto);
    int updateInst(InstMgmtDTO dto);
    int deleteInst(String instCd);
}
