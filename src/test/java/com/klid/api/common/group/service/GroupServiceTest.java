package com.klid.api.common.group.service;

import com.klid.api.BaseServiceTest;
import com.klid.api.common.group.dto.AuthGroupDTO;
import com.klid.api.common.group.dto.GroupDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 그룹 관리 Service 통합 테스트
 */
@DisplayName("그룹 관리 Service 테스트")
class GroupServiceTest extends BaseServiceTest {

    @Autowired
    private GroupService groupService;

    @Test
    @DisplayName("그룹명 중복 체크")
    void testGetGroupDuplicateCount() {
        // given
        final Map<String, Object> params = new HashMap<>();
        params.put("grpName", "테스트그룹");
        params.put("grpType", "DEFAULT");

        // when
        final int result = groupService.getGroupDuplicateCount(params);

        // then
        assertThat(result).isGreaterThanOrEqualTo(0);
    }

    @Nested
    @DisplayName("권한그룹 Service 테스트")
    class AuthGroupServiceTest {

        @Test
        @DisplayName("권한그룹 트리 목록 전체 조회")
        void testGetAuthConfDefaultGroupTreeListAll() {
            // given
            final Map<String, Object> params = new HashMap<>();

            // when
            final List<GroupDTO> result = groupService.getAuthConfDefaultGroupTreeListAll(params);

            // then
            assertThat(result).isNotNull();
        }

        @Test
        @DisplayName("권한그룹 목록 조회")
        void testGetAuthGroupList() {
            // given
            final Map<String, Object> params = new HashMap<>();

            // when
            final List<AuthGroupDTO> result = groupService.getAuthGroupList(params);

            // then
            assertThat(result).isNotNull();
        }

        @Test
        @DisplayName("권한그룹 등록")
        void testAddAuthGroup() {
            // given
            final Map<String, Object> params = new HashMap<>();
            params.put("authGrpName", "테스트권한그룹");
            params.put("authGrpDesc", "테스트 설명");

            // when & then - 예외가 발생하지 않으면 성공
            groupService.addAuthGroup(params);
        }

        @Test
        @DisplayName("권한그룹 수정")
        void testSaveAuthGroup() {
            // given
            final Map<String, Object> params = new HashMap<>();
            params.put("authGrpNo", "1");
            params.put("authGrpName", "수정된권한그룹");
            params.put("authGrpDesc", "수정된 설명");

            // when & then - 예외가 발생하지 않으면 성공
            groupService.saveAuthGroup(params);
        }

        @Test
        @DisplayName("권한그룹 삭제")
        void testDeleteAuthGroup() {
            // given
            final Map<String, Object> params = new HashMap<>();
            params.put("authGrpNo", "9999");

            // when & then - 예외가 발생하지 않으면 성공
            groupService.deleteAuthGroup(params);
        }
    }

    @Nested
    @DisplayName("기본그룹 Service 테스트")
    class DefaultGroupServiceTest {

        @Test
        @DisplayName("기본그룹 트리 목록 전체 조회")
        void testGetDefaultGroupTreeListAll() {
            // given
            final Map<String, Object> params = new HashMap<>();

            // when
            final List<GroupDTO> result = groupService.getDefaultGroupTreeListAll(params);

            // then
            assertThat(result).isNotNull();
        }

        @Test
        @DisplayName("기본그룹 트리 목록 조회")
        void testGetDefaultGroupTreeList() {
            // given
            final Map<String, Object> params = new HashMap<>();

            // when
            final List<GroupDTO> result = groupService.getDefaultGroupTreeList(params);

            // then
            assertThat(result).isNotNull();
        }

        @Test
        @DisplayName("VM 서버 그룹 트리 목록 조회")
        void testGetVmServerGroupTreeList() {
            // given
            final Map<String, Object> params = new HashMap<>();

            // when
            final List<GroupDTO> result = groupService.getVmServerGroupTreeList(params);

            // then
            assertThat(result).isNotNull();
        }

        @Test
        @DisplayName("L4 기본그룹 트리 목록 조회")
        void testGetL4DefaultGroupTreeList() {
            // given
            final Map<String, Object> params = new HashMap<>();

            // when
            final List<GroupDTO> result = groupService.getL4DefaultGroupTreeList(params);

            // then
            assertThat(result).isNotNull();
        }

        @Test
        @DisplayName("AP 기본그룹 트리 목록 조회")
        void testGetApDefaultGroupTreeList() {
            // given
            final Map<String, Object> params = new HashMap<>();

            // when
            final List<GroupDTO> result = groupService.getApDefaultGroupTreeList(params);

            // then
            assertThat(result).isNotNull();
        }
    }

    @Nested
    @DisplayName("조회그룹 Service 테스트")
    class SearchGroupServiceTest {

        @Test
        @DisplayName("조회그룹 트리 목록 조회")
        void testGetSearchGroupTreeList() {
            // given
            final Map<String, Object> params = new HashMap<>();

            // when
            final List<GroupDTO> result = groupService.getSearchGroupTreeList(params);

            // then
            assertThat(result).isNotNull();
        }
    }

    @Nested
    @DisplayName("회선그룹 Service 테스트")
    class IfGroupServiceTest {

        @Test
        @DisplayName("회선그룹 트리 목록 조회")
        void testGetIfGroupTreeList() {
            // given
            final Map<String, Object> params = new HashMap<>();

            // when
            final List<GroupDTO> result = groupService.getIfGroupTreeList(params);

            // then
            assertThat(result).isNotNull();
        }
    }

    @Nested
    @DisplayName("서버그룹 Service 테스트")
    class ServerGroupServiceTest {

        @Test
        @DisplayName("서버그룹 트리 목록 조회")
        void testGetServerGroupTreeList() {
            // given
            final Map<String, Object> params = new HashMap<>();

            // when
            final List<GroupDTO> result = groupService.getServerGroupTreeList(params);

            // then
            assertThat(result).isNotNull();
        }
    }

    @Nested
    @DisplayName("망그룹 Service 테스트")
    class NetworkGroupServiceTest {

        @Test
        @DisplayName("망그룹 트리 목록 조회")
        void testGetNetworkGroupTreeList() {
            // given
            final Map<String, Object> params = new HashMap<>();

            // when
            final List<GroupDTO> result = groupService.getNetworkGroupTreeList(params);

            // then
            assertThat(result).isNotNull();
        }
    }

    @Nested
    @DisplayName("망흐름그룹 Service 테스트")
    class NetworkFlowGroupServiceTest {

        @Test
        @DisplayName("망흐름그룹 트리 목록 조회")
        void testGetNetworkFlowGroupTreeList() {
            // given
            final Map<String, Object> params = new HashMap<>();

            // when
            final List<GroupDTO> result = groupService.getNetworkFlowGroupTreeList(params);

            // then
            assertThat(result).isNotNull();
        }
    }

    @Nested
    @DisplayName("서비스그룹 Service 테스트")
    class ServiceGroupServiceTest {

        @Test
        @DisplayName("서비스그룹 트리 목록 조회")
        void testGetServiceGroupTreeList() {
            // given
            final Map<String, Object> params = new HashMap<>();

            // when
            final List<GroupDTO> result = groupService.getServiceGroupTreeList(params);

            // then
            assertThat(result).isNotNull();
        }
    }

    @Nested
    @DisplayName("서비스포트그룹 Service 테스트")
    class ServicePortGroupServiceTest {

        @Test
        @DisplayName("서비스포트그룹 목록 조회")
        void testGetServicePortGroupList() {
            // given
            final Map<String, Object> params = new HashMap<>();

            // when
            final List<GroupDTO> result = groupService.getServicePortGroupList(params);

            // then
            assertThat(result).isNotNull();
        }
    }

    @Nested
    @DisplayName("App그룹 Service 테스트")
    class AppGroupServiceTest {

        @Test
        @DisplayName("App그룹 트리 목록 조회")
        void testGetAppGroupTreeList() {
            // given
            final Map<String, Object> params = new HashMap<>();

            // when
            final List<GroupDTO> result = groupService.getAppGroupTreeList(params);

            // then
            assertThat(result).isNotNull();
        }
    }

    @Nested
    @DisplayName("As그룹 Service 테스트")
    class AsGroupServiceTest {

        @Test
        @DisplayName("As그룹 트리 목록 조회")
        void testGetAsGroupTreeList() {
            // given
            final Map<String, Object> params = new HashMap<>();

            // when
            final List<GroupDTO> result = groupService.getAsGroupTreeList(params);

            // then
            assertThat(result).isNotNull();
        }
    }

    @Nested
    @DisplayName("기관그룹 Service 테스트")
    class InstitutionGroupServiceTest {

        @Test
        @DisplayName("기관그룹 트리 목록 조회")
        void testGetInstitutionGroupTreeList() {
            // given
            final Map<String, Object> params = new HashMap<>();

            // when
            final List<GroupDTO> result = groupService.getInstitutionGroupTreeList(params);

            // then
            assertThat(result).isNotNull();
        }
    }
}
