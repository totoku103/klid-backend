package com.klid.api.common.group.controller;

import com.klid.api.common.group.dto.AuthGroupDTO;
import com.klid.api.common.group.dto.GroupDTO;
import com.klid.api.common.group.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 그룹 관리 REST API Controller
 *
 * <p>각종 그룹(권한, 기본, 조회, 회선, 서버, 망, 망흐름, 서비스, 서비스포트, App, As, 기관) 관리 기능 제공</p>
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/common/groups")
public class GroupController {

    private final GroupService groupService;

    /**
     * 그룹명 중복 체크
     */
    @PostMapping("/check-duplicate")
    public ResponseEntity<Integer> checkGroupDuplicate(@RequestBody final Map<String, Object> request) {
        final int count = groupService.getGroupDuplicateCount(request);
        return ResponseEntity.ok(count);
    }

    // ====================================== 권한그룹 ======================================

    @GetMapping("/auth/tree/all")
    public ResponseEntity<List<GroupDTO>> getAuthConfDefaultGroupTreeListAll(@RequestParam final Map<String, Object> params) {
        final List<GroupDTO> groups = groupService.getAuthConfDefaultGroupTreeListAll(params);
        return ResponseEntity.ok(groups);
    }

    @GetMapping("/auth")
    public ResponseEntity<List<AuthGroupDTO>> getAuthGroupList(@RequestParam final Map<String, Object> params) {
        final List<AuthGroupDTO> groups = groupService.getAuthGroupList(params);
        return ResponseEntity.ok(groups);
    }

    @PostMapping("/auth")
    public ResponseEntity<Void> addAuthGroup(@RequestBody final Map<String, Object> request) {
        groupService.addAuthGroup(request);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/auth")
    public ResponseEntity<Void> saveAuthGroup(@RequestBody final Map<String, Object> request) {
        groupService.saveAuthGroup(request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/auth")
    public ResponseEntity<Void> deleteAuthGroup(@RequestBody final Map<String, Object> request) {
        groupService.deleteAuthGroup(request);
        return ResponseEntity.ok().build();
    }

    // ====================================== 기본그룹 ======================================

    @GetMapping("/default/tree/all")
    public ResponseEntity<List<GroupDTO>> getDefaultGroupTreeListAll(@RequestParam final Map<String, Object> params) {
        final List<GroupDTO> groups = groupService.getDefaultGroupTreeListAll(params);
        return ResponseEntity.ok(groups);
    }

    @GetMapping("/default/tree")
    public ResponseEntity<List<GroupDTO>> getDefaultGroupTreeList(@RequestParam final Map<String, Object> params) {
        final List<GroupDTO> groups = groupService.getDefaultGroupTreeList(params);
        return ResponseEntity.ok(groups);
    }

    @GetMapping("/vm-server/tree")
    public ResponseEntity<List<GroupDTO>> getVmServerGroupTreeList(@RequestParam final Map<String, Object> params) {
        final List<GroupDTO> groups = groupService.getVmServerGroupTreeList(params);
        return ResponseEntity.ok(groups);
    }

    @GetMapping("/l4-default/tree")
    public ResponseEntity<List<GroupDTO>> getL4DefaultGroupTreeList(@RequestParam final Map<String, Object> params) {
        final List<GroupDTO> groups = groupService.getL4DefaultGroupTreeList(params);
        return ResponseEntity.ok(groups);
    }

    @GetMapping("/ap-default/tree")
    public ResponseEntity<List<GroupDTO>> getApDefaultGroupTreeList(@RequestParam final Map<String, Object> params) {
        final List<GroupDTO> groups = groupService.getApDefaultGroupTreeList(params);
        return ResponseEntity.ok(groups);
    }

    @PostMapping("/default")
    public ResponseEntity<Void> addDefaultGroup(@RequestBody final Map<String, Object> request) {
        groupService.addDefaultGroup(request);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/default")
    public ResponseEntity<Void> editDefaultGroup(@RequestBody final Map<String, Object> request) {
        groupService.editDefaultGroup(request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/default")
    public ResponseEntity<Void> deleteDefaultGroup(@RequestBody final Map<String, Object> request) {
        groupService.deleteDefaultGroup(request);
        return ResponseEntity.ok().build();
    }

    // ====================================== 조회그룹 ======================================

    @GetMapping("/search/tree")
    public ResponseEntity<List<GroupDTO>> getSearchGroupTreeList(@RequestParam final Map<String, Object> params) {
        final List<GroupDTO> groups = groupService.getSearchGroupTreeList(params);
        return ResponseEntity.ok(groups);
    }

    @PostMapping("/search")
    public ResponseEntity<Void> addSearchGroup(@RequestBody final Map<String, Object> request) {
        groupService.addSearchGroup(request);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/search")
    public ResponseEntity<Void> editSearchGroup(@RequestBody final Map<String, Object> request) {
        groupService.editSearchGroup(request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/search")
    public ResponseEntity<Void> deleteSearchGroup(@RequestBody final Map<String, Object> request) {
        groupService.deleteSearchGroup(request);
        return ResponseEntity.ok().build();
    }

    // ====================================== 회선그룹 ======================================

    @GetMapping("/if/tree")
    public ResponseEntity<List<GroupDTO>> getIfGroupTreeList(@RequestParam final Map<String, Object> params) {
        final List<GroupDTO> groups = groupService.getIfGroupTreeList(params);
        return ResponseEntity.ok(groups);
    }

    @PostMapping("/if")
    public ResponseEntity<Void> addIfGroup(@RequestBody final Map<String, Object> request) {
        groupService.addIfGroup(request);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/if")
    public ResponseEntity<Void> editIfGroup(@RequestBody final Map<String, Object> request) {
        groupService.editIfGroup(request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/if")
    public ResponseEntity<Void> deleteIfGroup(@RequestBody final Map<String, Object> request) {
        groupService.deleteIfGroup(request);
        return ResponseEntity.ok().build();
    }

    // ====================================== 서버그룹 ======================================

    @GetMapping("/server/tree")
    public ResponseEntity<List<GroupDTO>> getServerGroupTreeList(@RequestParam final Map<String, Object> params) {
        final List<GroupDTO> groups = groupService.getServerGroupTreeList(params);
        return ResponseEntity.ok(groups);
    }

    @PostMapping("/server")
    public ResponseEntity<Void> addServerGroup(@RequestBody final Map<String, Object> request) {
        groupService.addServerGroup(request);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/server")
    public ResponseEntity<Void> editServerGroup(@RequestBody final Map<String, Object> request) {
        groupService.editServerGroup(request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/server")
    public ResponseEntity<Void> deleteServerGroup(@RequestBody final Map<String, Object> request) {
        groupService.deleteServerGroup(request);
        return ResponseEntity.ok().build();
    }

    // ====================================== 망그룹 ======================================

    @GetMapping("/network/tree")
    public ResponseEntity<List<GroupDTO>> getNetworkGroupTreeList(@RequestParam final Map<String, Object> params) {
        final List<GroupDTO> groups = groupService.getNetworkGroupTreeList(params);
        return ResponseEntity.ok(groups);
    }

    @PostMapping("/network")
    public ResponseEntity<Void> addNetworkGroup(@RequestBody final Map<String, Object> request) {
        groupService.addNetworkGroup(request);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/network")
    public ResponseEntity<Void> editNetworkGroup(@RequestBody final Map<String, Object> request) {
        groupService.editNetworkGroup(request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/network")
    public ResponseEntity<Void> deleteNetworkGroup(@RequestBody final Map<String, Object> request) {
        groupService.deleteNetworkGroup(request);
        return ResponseEntity.ok().build();
    }

    // ====================================== 망흐름그룹 ======================================

    @GetMapping("/network-flow/tree")
    public ResponseEntity<List<GroupDTO>> getNetworkFlowGroupTreeList(@RequestParam final Map<String, Object> params) {
        final List<GroupDTO> groups = groupService.getNetworkFlowGroupTreeList(params);
        return ResponseEntity.ok(groups);
    }

    @PostMapping("/network-flow")
    public ResponseEntity<Void> addNetworkFlowGroup(@RequestBody final Map<String, Object> request) {
        groupService.addNetworkFlowGroup(request);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/network-flow")
    public ResponseEntity<Void> editNetworkFlowGroup(@RequestBody final Map<String, Object> request) {
        groupService.editNetworkFlowGroup(request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/network-flow")
    public ResponseEntity<Void> deleteNetworkFlowGroup(@RequestBody final Map<String, Object> request) {
        groupService.deleteNetworkFlowGroup(request);
        return ResponseEntity.ok().build();
    }

    // ====================================== 서비스그룹 ======================================

    @GetMapping("/service/tree")
    public ResponseEntity<List<GroupDTO>> getServiceGroupTreeList(@RequestParam final Map<String, Object> params) {
        final List<GroupDTO> groups = groupService.getServiceGroupTreeList(params);
        return ResponseEntity.ok(groups);
    }

    @PostMapping("/service")
    public ResponseEntity<Void> addServiceGroup(@RequestBody final Map<String, Object> request) {
        groupService.addServiceGroup(request);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/service")
    public ResponseEntity<Void> editServiceGroup(@RequestBody final Map<String, Object> request) {
        groupService.editServiceGroup(request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/service")
    public ResponseEntity<Void> deleteServiceGroup(@RequestBody final Map<String, Object> request) {
        groupService.deleteServiceGroup(request);
        return ResponseEntity.ok().build();
    }

    // ====================================== 서비스포트그룹 ======================================

    @GetMapping("/service-port")
    public ResponseEntity<List<GroupDTO>> getServicePortGroupList(@RequestParam final Map<String, Object> params) {
        final List<GroupDTO> groups = groupService.getServicePortGroupList(params);
        return ResponseEntity.ok(groups);
    }

    @PostMapping("/service-port")
    public ResponseEntity<Void> addServicePortGroup(@RequestBody final Map<String, Object> request) {
        groupService.addServicePortGroup(request);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/service-port")
    public ResponseEntity<Void> editServicePortGroup(@RequestBody final Map<String, Object> request) {
        groupService.editServicePortGroup(request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/service-port")
    public ResponseEntity<Void> deleteServicePortGroup(@RequestBody final Map<String, Object> request) {
        groupService.deleteServicePortGroup(request);
        return ResponseEntity.ok().build();
    }

    // ====================================== App그룹 ======================================

    @GetMapping("/app/tree")
    public ResponseEntity<List<GroupDTO>> getAppGroupTreeList(@RequestParam final Map<String, Object> params) {
        final List<GroupDTO> groups = groupService.getAppGroupTreeList(params);
        return ResponseEntity.ok(groups);
    }

    @PostMapping("/app")
    public ResponseEntity<Void> addAppGroup(@RequestBody final Map<String, Object> request) {
        groupService.addAppGroup(request);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/app")
    public ResponseEntity<Void> editAppGroup(@RequestBody final Map<String, Object> request) {
        groupService.editAppGroup(request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/app")
    public ResponseEntity<Void> deleteAppGroup(@RequestBody final Map<String, Object> request) {
        groupService.deleteAppGroup(request);
        return ResponseEntity.ok().build();
    }

    // ====================================== As그룹 ======================================

    @GetMapping("/as/tree")
    public ResponseEntity<List<GroupDTO>> getAsGroupTreeList(@RequestParam final Map<String, Object> params) {
        final List<GroupDTO> groups = groupService.getAsGroupTreeList(params);
        return ResponseEntity.ok(groups);
    }

    @PostMapping("/as")
    public ResponseEntity<Void> addAsGroup(@RequestBody final Map<String, Object> request) {
        groupService.addAsGroup(request);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/as")
    public ResponseEntity<Void> editAsGroup(@RequestBody final Map<String, Object> request) {
        groupService.editAsGroup(request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/as")
    public ResponseEntity<Void> deleteAsGroup(@RequestBody final Map<String, Object> request) {
        groupService.deleteAsGroup(request);
        return ResponseEntity.ok().build();
    }

    // ====================================== 기관그룹 ======================================

    @GetMapping("/institution/tree")
    public ResponseEntity<List<GroupDTO>> getInstitutionGroupTreeList(@RequestParam final Map<String, Object> params) {
        final List<GroupDTO> groups = groupService.getInstitutionGroupTreeList(params);
        return ResponseEntity.ok(groups);
    }
}
