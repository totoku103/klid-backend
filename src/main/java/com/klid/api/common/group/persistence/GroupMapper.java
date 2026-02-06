package com.klid.api.common.group.persistence;

import com.klid.api.common.group.dto.AuthGroupDTO;
import com.klid.api.common.group.dto.GroupDTO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 그룹 관리 MyBatis Mapper
 */
@Repository
public interface GroupMapper {

    void procSpMakeGrpLeaf(Map<String, Object> paramMap);
    void spMakeLeafMove(Map<String, Object> paramMap);

    void procSpMakeGrpTypeLeaf(Map<String, Object> paramMap);
    void spMakeGrpLeafMove(Map<String, Object> paramMap);

    int selectGrpDuplCnt(Map<String, Object> paramMap);

    // 권한그룹
    List<AuthGroupDTO> selectAuthGrpList();
    void insertAuthGrp(Map<String, Object> paramMap);
    void updateAuthGrp(Map<String, Object> paramMap);
    void deleteAuthGrp(Map<String, Object> paramMap);

    // 기본그룹
    List<GroupDTO> selectAuthConfDefaultGrpTreeListAll(Map<String, Object> paramMap);
    List<GroupDTO> selectDefaultGrpTreeListAll(Map<String, Object> paramMap);
    List<GroupDTO> selectDefaultGrpTreeList(Map<String, Object> paramMap);
    List<GroupDTO> selectVmSvrGrpTreeList(Map<String, Object> paramMap);
    List<GroupDTO> selectL4DefaultGrpTreeList(Map<String, Object> paramMap);
    List<GroupDTO> selectApDefaultGrpTreeList(Map<String, Object> paramMap);
    void insertDefaultGroup(Map<String, Object> paramMap);
    Long selectDefaultGroupParent(Map<String, Object> paramMap);
    void updateDefaultGroup(Map<String, Object> paramMap);
    void deleteDefaultGroup(Map<String, Object> paramMap);

    // 조회그룹
    List<GroupDTO> selectSearchGrpTreeList(Map<String, Object> paramMap);
    void insertSearchGroup(Map<String, Object> paramMap);
    void updateSearchGroup(Map<String, Object> paramMap);
    void deleteSearchGroup(Map<String, Object> paramMap);

    // 회선그룹
    List<GroupDTO> selectIfGrpTreeList(Map<String, Object> paramMap);
    void insertIfGroup(Map<String, Object> paramMap);
    void updateIfGroup(Map<String, Object> paramMap);
    void deleteIfGroup(Map<String, Object> paramMap);

    // 서버그룹
    List<GroupDTO> selectServerGrpTreeList(Map<String, Object> paramMap);
    void insertSvrGroup(Map<String, Object> paramMap);
    void updateSvrGroup(Map<String, Object> paramMap);
    void deleteSvrGroup(Map<String, Object> paramMap);

    // 망그룹
    List<GroupDTO> selectMangGrpTreeList(Map<String, Object> paramMap);
    void insertMangGroup(Map<String, Object> paramMap);
    void updateMangGroup(Map<String, Object> paramMap);
    void deleteMangGroup(Map<String, Object> paramMap);

    // 망흐름그룹
    List<GroupDTO> selectMangFlowGrpTreeList(Map<String, Object> paramMap);
    void insertMangFlowGroup(Map<String, Object> paramMap);
    void updateMangFlowGroup(Map<String, Object> paramMap);
    void deleteMangFlowGroup(Map<String, Object> paramMap);

    // 서비스그룹
    List<GroupDTO> selectServiceGrpTreeList(Map<String, Object> paramMap);
    void insertServiceGroup(Map<String, Object> paramMap);
    void updateServiceGroup(Map<String, Object> paramMap);
    void deleteServiceGroup(Map<String, Object> paramMap);

    // 서비스포트그룹
    List<GroupDTO> selectSvcPortGrpList(Map<String, Object> paramMap);
    void insertSvcPortGroup(Map<String, Object> paramMap);
    void updateSvcPortGroup(Map<String, Object> paramMap);
    void deleteSvcPortGroup(Map<String, Object> paramMap);

    // As그룹
    List<GroupDTO> selectAsGrpTreeList(Map<String, Object> paramMap);
    void insertAsGroup(Map<String, Object> paramMap);
    void updateAsGroup(Map<String, Object> paramMap);
    void deleteAsGroup(Map<String, Object> paramMap);

    // App그룹
    List<GroupDTO> selectAppGrpTreeList(Map<String, Object> paramMap);
    void insertAppGroup(Map<String, Object> paramMap);
    void updateAppGroup(Map<String, Object> paramMap);
    void deleteAppGroup(Map<String, Object> paramMap);

    // 기관그룹
    List<GroupDTO> selectInstGrpTreeList(Map<String, Object> paramMap);
}
