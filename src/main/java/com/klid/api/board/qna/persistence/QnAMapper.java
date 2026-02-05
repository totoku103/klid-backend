package com.klid.api.board.qna.persistence;

import com.klid.api.board.qna.dto.QnAListItemDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface QnAMapper {

    List<QnAListItemDTO> getMainQnaList(
            @Param("listSize") Integer listSize,
            @Param("sInstCd") String sInstCd
    );
}
