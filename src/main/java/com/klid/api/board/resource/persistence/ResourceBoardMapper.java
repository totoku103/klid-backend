package com.klid.api.board.resource.persistence;

import com.klid.api.board.resource.dto.ResourceBoardDTO;
import com.klid.api.board.resource.dto.ResourceBoardListDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Component("apiResourceBoardMapper")
@Mapper
public interface ResourceBoardMapper {

    List<ResourceBoardListDTO> getBoardList(
            @Param("pageNo") Integer pageNo,
            @Param("pageSize") Integer pageSize,
            @Param("searchType") String searchType,
            @Param("searchText") String searchText,
            @Param("sInstCd") String sInstCd);

    ResourceBoardDTO getBoardContents(@Param("boardNo") Long boardNo);

    void incrementReadCount(@Param("boardNo") Long boardNo);

    void addBoard(ResourceBoardDTO boardDTO);

    void editBoard(ResourceBoardDTO boardDTO);

    void delBoard(@Param("boardNo") Long boardNo);

    Map<String, Object> checkAuth(
            @Param("sInstCd") String sInstCd,
            @Param("userId") String userId);

    // MOIS (행안부) Board Methods
    List<ResourceBoardListDTO> getMoisBoardList(
            @Param("pageNo") Integer pageNo,
            @Param("pageSize") Integer pageSize,
            @Param("searchType") String searchType,
            @Param("searchText") String searchText);

    ResourceBoardDTO getMoisBoardContents(@Param("boardNo") Long boardNo);

    void incrementMoisReadCount(@Param("boardNo") Long boardNo);

    void addMoisBoard(ResourceBoardDTO boardDTO);

    void editMoisBoard(ResourceBoardDTO boardDTO);

    void delMoisBoard(@Param("boardNo") Long boardNo);
}
