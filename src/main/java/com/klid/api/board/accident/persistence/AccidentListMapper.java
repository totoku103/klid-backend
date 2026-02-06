package com.klid.api.board.accident.persistence;

import com.klid.api.board.accident.dto.AccidentListItemDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface AccidentListMapper {
    List<AccidentListItemDTO> selectAccidentList(Map<String, Object> searchParams);
    int deleteAccident(@Param("inciNo") String inciNo);
}
