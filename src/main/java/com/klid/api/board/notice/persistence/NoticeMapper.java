package com.klid.api.board.notice.persistence;

import com.klid.api.board.notice.dto.NoticeListItemDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface NoticeMapper {

    List<NoticeListItemDTO> getMainNoticeList(
            @Param("listSize") Integer listSize,
            @Param("sAuthMain") String sAuthMain,
            @Param("sInstCd") String sInstCd,
            @Param("sPntInstCd") String sPntInstCd
    );
}
