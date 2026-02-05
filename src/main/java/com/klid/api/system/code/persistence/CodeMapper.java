package com.klid.api.system.code.persistence;

import com.klid.api.system.code.dto.CodeRequest;
import com.klid.api.system.code.dto.CodeResponse;
import com.klid.api.system.code.dto.WeekDayRequest;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 코드 관리 Mapper
 */
@Component("apiCodeMapper")
@Mapper
public interface CodeMapper {

    List<CodeResponse> selectCodeList(Map<String, Object> params);

    void insertCode(CodeRequest request);

    void updateCode(CodeRequest request);

    Integer selectCodeDuplCnt(Map<String, Object> params);

    void insertWeekDay(WeekDayRequest request);

    void deleteWeekDay(String weekdayId);
}
