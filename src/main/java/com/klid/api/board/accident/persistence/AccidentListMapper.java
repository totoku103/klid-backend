package com.klid.api.board.accident.persistence;

import com.klid.api.board.accident.dto.AccidentListItemDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface AccidentListMapper {
    List<AccidentListItemDTO> selectAccidentList(Map<String, Object> searchParams);
    int deleteAccident(@Param("inciNo") String inciNo);
}
