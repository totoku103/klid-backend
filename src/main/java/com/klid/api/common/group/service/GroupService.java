package com.klid.api.common.group.service;

import com.klid.api.common.group.dto.AuthGroupDTO;
import com.klid.api.common.group.dto.GroupDTO;
import com.klid.api.common.group.persistence.GroupMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 그룹 관리 서비스
 *
 * <p>각종 그룹(권한, 기본, 조회, 회선, 서버, 망, 망흐름, 서비스, 서비스포트, App, As, 기관) 관리 비즈니스 로직</p>
 */
@Service
@RequiredArgsConstructor
public class GroupService {

    private final GroupMapper groupMapper;

    public int getGroupDuplicateCount(final Map<String, Object> params) {
        return groupMapper.selectGrpDuplCnt(params);
    }

    // ====================================== 권한그룹 ======================================

    public List<GroupDTO> getAuthConfDefaultGroupTreeListAll(final Map<String, Object> params) {
        return groupMapper.selectAuthConfDefaultGrpTreeListAll(params);
    }

    public List<AuthGroupDTO> getAuthGroupList(final Map<String, Object> params) {
        return groupMapper.selectAuthGrpList();
    }

    @Transactional
    public void addAuthGroup(final Map<String, Object> params) {
        groupMapper.insertAuthGrp(params);
    }

    @Transactional
    public void saveAuthGroup(final Map<String, Object> params) {
        groupMapper.updateAuthGrp(params);
    }

    @Transactional
    public void deleteAuthGroup(final Map<String, Object> params) {
        groupMapper.deleteAuthGrp(params);
    }

    // ====================================== 기본그룹 ======================================

    public List<GroupDTO> getDefaultGroupTreeListAll(final Map<String, Object> params) {
        return groupMapper.selectDefaultGrpTreeListAll(params);
    }

    public List<GroupDTO> getDefaultGroupTreeList(final Map<String, Object> params) {
        return groupMapper.selectDefaultGrpTreeList(params);
    }

    public List<GroupDTO> getVmServerGroupTreeList(final Map<String, Object> params) {
        return groupMapper.selectVmSvrGrpTreeList(params);
    }

    public List<GroupDTO> getL4DefaultGroupTreeList(final Map<String, Object> params) {
        return groupMapper.selectL4DefaultGrpTreeList(params);
    }

    public List<GroupDTO> getApDefaultGroupTreeList(final Map<String, Object> params) {
        return groupMapper.selectApDefaultGrpTreeList(params);
    }

    @Transactional
    public void addDefaultGroup(final Map<String, Object> params) {
        groupMapper.insertDefaultGroup(params);
        groupMapper.procSpMakeGrpLeaf(params);
    }

    @Transactional
    public void editDefaultGroup(final Map<String, Object> params) {
        groupMapper.updateDefaultGroup(params);
        groupMapper.spMakeLeafMove(params);
    }

    @Transactional
    public void deleteDefaultGroup(final Map<String, Object> params) {
        groupMapper.deleteDefaultGroup(params);
        groupMapper.procSpMakeGrpLeaf(params);
    }

    // ====================================== 조회그룹 ======================================

    public List<GroupDTO> getSearchGroupTreeList(final Map<String, Object> params) {
        return groupMapper.selectSearchGrpTreeList(params);
    }

    @Transactional
    public void addSearchGroup(final Map<String, Object> params) {
        groupMapper.insertSearchGroup(params);
        groupMapper.procSpMakeGrpLeaf(params);
    }

    @Transactional
    public void editSearchGroup(final Map<String, Object> params) {
        groupMapper.updateSearchGroup(params);
        groupMapper.spMakeLeafMove(params);
    }

    @Transactional
    public void deleteSearchGroup(final Map<String, Object> params) {
        groupMapper.deleteSearchGroup(params);
        groupMapper.procSpMakeGrpLeaf(params);
    }

    // ====================================== 회선그룹 ======================================

    public List<GroupDTO> getIfGroupTreeList(final Map<String, Object> params) {
        return groupMapper.selectIfGrpTreeList(params);
    }

    @Transactional
    public void addIfGroup(final Map<String, Object> params) {
        groupMapper.insertIfGroup(params);
        groupMapper.procSpMakeGrpTypeLeaf(params);
    }

    @Transactional
    public void editIfGroup(final Map<String, Object> params) {
        groupMapper.updateIfGroup(params);
        groupMapper.spMakeGrpLeafMove(params);
    }

    @Transactional
    public void deleteIfGroup(final Map<String, Object> params) {
        groupMapper.deleteIfGroup(params);
        groupMapper.procSpMakeGrpTypeLeaf(params);
    }

    // ====================================== 서버그룹 ======================================

    public List<GroupDTO> getServerGroupTreeList(final Map<String, Object> params) {
        return groupMapper.selectServerGrpTreeList(params);
    }

    @Transactional
    public void addServerGroup(final Map<String, Object> params) {
        groupMapper.insertSvrGroup(params);
        groupMapper.procSpMakeGrpTypeLeaf(params);
    }

    @Transactional
    public void editServerGroup(final Map<String, Object> params) {
        groupMapper.updateSvrGroup(params);
        groupMapper.spMakeGrpLeafMove(params);
    }

    @Transactional
    public void deleteServerGroup(final Map<String, Object> params) {
        groupMapper.deleteSvrGroup(params);
        groupMapper.procSpMakeGrpTypeLeaf(params);
    }

    // ====================================== 망그룹 ======================================

    public List<GroupDTO> getNetworkGroupTreeList(final Map<String, Object> params) {
        return groupMapper.selectMangGrpTreeList(params);
    }

    @Transactional
    public void addNetworkGroup(final Map<String, Object> params) {
        groupMapper.insertMangGroup(params);
        groupMapper.procSpMakeGrpTypeLeaf(params);
    }

    @Transactional
    public void editNetworkGroup(final Map<String, Object> params) {
        groupMapper.updateMangGroup(params);
        groupMapper.spMakeGrpLeafMove(params);
    }

    @Transactional
    public void deleteNetworkGroup(final Map<String, Object> params) {
        groupMapper.deleteMangGroup(params);
        groupMapper.procSpMakeGrpTypeLeaf(params);
    }

    // ====================================== 망흐름그룹 ======================================

    public List<GroupDTO> getNetworkFlowGroupTreeList(final Map<String, Object> params) {
        return groupMapper.selectMangFlowGrpTreeList(params);
    }

    @Transactional
    public void addNetworkFlowGroup(final Map<String, Object> params) {
        groupMapper.insertMangFlowGroup(params);
        groupMapper.procSpMakeGrpTypeLeaf(params);
    }

    @Transactional
    public void editNetworkFlowGroup(final Map<String, Object> params) {
        groupMapper.updateMangFlowGroup(params);
        groupMapper.spMakeGrpLeafMove(params);
    }

    @Transactional
    public void deleteNetworkFlowGroup(final Map<String, Object> params) {
        groupMapper.deleteMangFlowGroup(params);
        groupMapper.procSpMakeGrpTypeLeaf(params);
    }

    // ====================================== 서비스그룹 ======================================

    public List<GroupDTO> getServiceGroupTreeList(final Map<String, Object> params) {
        return groupMapper.selectServiceGrpTreeList(params);
    }

    @Transactional
    public void addServiceGroup(final Map<String, Object> params) {
        groupMapper.insertServiceGroup(params);
        groupMapper.procSpMakeGrpTypeLeaf(params);
    }

    @Transactional
    public void editServiceGroup(final Map<String, Object> params) {
        groupMapper.updateServiceGroup(params);
        groupMapper.spMakeGrpLeafMove(params);
    }

    @Transactional
    public void deleteServiceGroup(final Map<String, Object> params) {
        groupMapper.deleteServiceGroup(params);
        groupMapper.procSpMakeGrpTypeLeaf(params);
    }

    // ====================================== 서비스포트그룹 ======================================

    public List<GroupDTO> getServicePortGroupList(final Map<String, Object> params) {
        return groupMapper.selectSvcPortGrpList(params);
    }

    @Transactional
    public void addServicePortGroup(final Map<String, Object> params) {
        groupMapper.insertSvcPortGroup(params);
        groupMapper.procSpMakeGrpTypeLeaf(params);
    }

    @Transactional
    public void editServicePortGroup(final Map<String, Object> params) {
        groupMapper.updateSvcPortGroup(params);
        groupMapper.spMakeGrpLeafMove(params);
    }

    @Transactional
    public void deleteServicePortGroup(final Map<String, Object> params) {
        groupMapper.deleteSvcPortGroup(params);
        groupMapper.procSpMakeGrpTypeLeaf(params);
    }

    // ====================================== App그룹 ======================================

    public List<GroupDTO> getAppGroupTreeList(final Map<String, Object> params) {
        return groupMapper.selectAppGrpTreeList(params);
    }

    @Transactional
    public void addAppGroup(final Map<String, Object> params) {
        groupMapper.insertAppGroup(params);
        groupMapper.procSpMakeGrpTypeLeaf(params);
    }

    @Transactional
    public void editAppGroup(final Map<String, Object> params) {
        groupMapper.updateAppGroup(params);
        groupMapper.spMakeGrpLeafMove(params);
    }

    @Transactional
    public void deleteAppGroup(final Map<String, Object> params) {
        groupMapper.deleteAppGroup(params);
        groupMapper.procSpMakeGrpTypeLeaf(params);
    }

    // ====================================== As그룹 ======================================

    public List<GroupDTO> getAsGroupTreeList(final Map<String, Object> params) {
        return groupMapper.selectAsGrpTreeList(params);
    }

    @Transactional
    public void addAsGroup(final Map<String, Object> params) {
        groupMapper.insertAsGroup(params);
        groupMapper.procSpMakeGrpTypeLeaf(params);
    }

    @Transactional
    public void editAsGroup(final Map<String, Object> params) {
        groupMapper.updateAsGroup(params);
        groupMapper.spMakeGrpLeafMove(params);
    }

    @Transactional
    public void deleteAsGroup(final Map<String, Object> params) {
        groupMapper.deleteAsGroup(params);
        groupMapper.procSpMakeGrpTypeLeaf(params);
    }

    // ====================================== 기관그룹 ======================================

    public List<GroupDTO> getInstitutionGroupTreeList(final Map<String, Object> params) {
        return groupMapper.selectInstGrpTreeList(params);
    }
}
