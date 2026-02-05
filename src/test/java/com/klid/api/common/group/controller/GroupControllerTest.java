package com.klid.api.common.group.controller;

import com.klid.api.BaseControllerTest;
import com.klid.config.TestSecurityConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * 그룹 관리 Controller 통합 테스트
 */
@Import(TestSecurityConfig.class)
@DisplayName("그룹 관리 Controller 테스트")
class GroupControllerTest extends BaseControllerTest {

    private static final String BASE_URL = "/api/common/groups";

    @Test
    @DisplayName("그룹명 중복 체크")
    void testCheckGroupDuplicate() throws Exception {
        final Map<String, Object> request = new HashMap<>();
        request.put("grpName", "테스트그룹");
        request.put("grpType", "DEFAULT");

        mockMvc.perform(post(BASE_URL + "/check-duplicate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(request)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"));
    }

    @Nested
    @DisplayName("권한그룹 API 테스트")
    class AuthGroupTest {

        @Test
        @DisplayName("권한그룹 트리 목록 전체 조회")
        void testGetAuthConfDefaultGroupTreeListAll() throws Exception {
            mockMvc.perform(get(BASE_URL + "/auth/tree/all"))
                    .andExpect(status().isOk())
                    .andExpect(MockMvcResultMatchers.content().contentType("application/json"));
        }

        @Test
        @DisplayName("권한그룹 목록 조회")
        void testGetAuthGroupList() throws Exception {
            mockMvc.perform(get(BASE_URL + "/auth"))
                    .andExpect(status().isOk())
                    .andExpect(MockMvcResultMatchers.content().contentType("application/json"));
        }

        @Test
        @DisplayName("권한그룹 등록")
        void testAddAuthGroup() throws Exception {
            final Map<String, Object> request = new HashMap<>();
            request.put("authGrpName", "테스트권한그룹");
            request.put("authGrpDesc", "테스트 설명");

            mockMvc.perform(post(BASE_URL + "/auth")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(toJson(request)))
                    .andExpect(status().isOk());
        }

        @Test
        @DisplayName("권한그룹 수정")
        void testSaveAuthGroup() throws Exception {
            final Map<String, Object> request = new HashMap<>();
            request.put("authGrpNo", "1");
            request.put("authGrpName", "수정된권한그룹");
            request.put("authGrpDesc", "수정된 설명");

            mockMvc.perform(put(BASE_URL + "/auth")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(toJson(request)))
                    .andExpect(status().isOk());
        }

        @Test
        @DisplayName("권한그룹 삭제")
        void testDeleteAuthGroup() throws Exception {
            final Map<String, Object> request = new HashMap<>();
            request.put("authGrpNo", "9999");

            mockMvc.perform(delete(BASE_URL + "/auth")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(toJson(request)))
                    .andExpect(status().isOk());
        }
    }

    @Nested
    @DisplayName("기본그룹 API 테스트")
    class DefaultGroupTest {

        @Test
        @DisplayName("기본그룹 트리 목록 전체 조회")
        void testGetDefaultGroupTreeListAll() throws Exception {
            mockMvc.perform(get(BASE_URL + "/default/tree/all"))
                    .andExpect(status().isOk())
                    .andExpect(MockMvcResultMatchers.content().contentType("application/json"));
        }

        @Test
        @DisplayName("기본그룹 트리 목록 조회")
        void testGetDefaultGroupTreeList() throws Exception {
            mockMvc.perform(get(BASE_URL + "/default/tree"))
                    .andExpect(status().isOk())
                    .andExpect(MockMvcResultMatchers.content().contentType("application/json"));
        }

        @Test
        @DisplayName("VM 서버 그룹 트리 목록 조회")
        void testGetVmServerGroupTreeList() throws Exception {
            mockMvc.perform(get(BASE_URL + "/vm-server/tree"))
                    .andExpect(status().isOk())
                    .andExpect(MockMvcResultMatchers.content().contentType("application/json"));
        }

        @Test
        @DisplayName("L4 기본그룹 트리 목록 조회")
        void testGetL4DefaultGroupTreeList() throws Exception {
            mockMvc.perform(get(BASE_URL + "/l4-default/tree"))
                    .andExpect(status().isOk())
                    .andExpect(MockMvcResultMatchers.content().contentType("application/json"));
        }

        @Test
        @DisplayName("AP 기본그룹 트리 목록 조회")
        void testGetApDefaultGroupTreeList() throws Exception {
            mockMvc.perform(get(BASE_URL + "/ap-default/tree"))
                    .andExpect(status().isOk())
                    .andExpect(MockMvcResultMatchers.content().contentType("application/json"));
        }

        @Test
        @DisplayName("기본그룹 등록")
        void testAddDefaultGroup() throws Exception {
            final Map<String, Object> request = new HashMap<>();
            request.put("grpName", "테스트기본그룹");
            request.put("grpParent", 0);

            mockMvc.perform(post(BASE_URL + "/default")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(toJson(request)))
                    .andExpect(status().isOk());
        }

        @Test
        @DisplayName("기본그룹 수정")
        void testEditDefaultGroup() throws Exception {
            final Map<String, Object> request = new HashMap<>();
            request.put("grpNo", "1");
            request.put("grpName", "수정된기본그룹");

            mockMvc.perform(put(BASE_URL + "/default")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(toJson(request)))
                    .andExpect(status().isOk());
        }

        @Test
        @DisplayName("기본그룹 삭제")
        void testDeleteDefaultGroup() throws Exception {
            final Map<String, Object> request = new HashMap<>();
            request.put("grpNo", "9999");

            mockMvc.perform(delete(BASE_URL + "/default")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(toJson(request)))
                    .andExpect(status().isOk());
        }
    }

    @Nested
    @DisplayName("조회그룹 API 테스트")
    class SearchGroupTest {

        @Test
        @DisplayName("조회그룹 트리 목록 조회")
        void testGetSearchGroupTreeList() throws Exception {
            mockMvc.perform(get(BASE_URL + "/search/tree"))
                    .andExpect(status().isOk())
                    .andExpect(MockMvcResultMatchers.content().contentType("application/json"));
        }

        @Test
        @DisplayName("조회그룹 등록")
        void testAddSearchGroup() throws Exception {
            final Map<String, Object> request = new HashMap<>();
            request.put("grpName", "테스트조회그룹");
            request.put("grpParent", 0);

            mockMvc.perform(post(BASE_URL + "/search")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(toJson(request)))
                    .andExpect(status().isOk());
        }

        @Test
        @DisplayName("조회그룹 수정")
        void testEditSearchGroup() throws Exception {
            final Map<String, Object> request = new HashMap<>();
            request.put("grpNo", "1");
            request.put("grpName", "수정된조회그룹");

            mockMvc.perform(put(BASE_URL + "/search")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(toJson(request)))
                    .andExpect(status().isOk());
        }

        @Test
        @DisplayName("조회그룹 삭제")
        void testDeleteSearchGroup() throws Exception {
            final Map<String, Object> request = new HashMap<>();
            request.put("grpNo", "9999");

            mockMvc.perform(delete(BASE_URL + "/search")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(toJson(request)))
                    .andExpect(status().isOk());
        }
    }

    @Nested
    @DisplayName("회선그룹 API 테스트")
    class IfGroupTest {

        @Test
        @DisplayName("회선그룹 트리 목록 조회")
        void testGetIfGroupTreeList() throws Exception {
            mockMvc.perform(get(BASE_URL + "/if/tree"))
                    .andExpect(status().isOk())
                    .andExpect(MockMvcResultMatchers.content().contentType("application/json"));
        }

        @Test
        @DisplayName("회선그룹 등록")
        void testAddIfGroup() throws Exception {
            final Map<String, Object> request = new HashMap<>();
            request.put("grpName", "테스트회선그룹");
            request.put("grpParent", 0);

            mockMvc.perform(post(BASE_URL + "/if")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(toJson(request)))
                    .andExpect(status().isOk());
        }

        @Test
        @DisplayName("회선그룹 수정")
        void testEditIfGroup() throws Exception {
            final Map<String, Object> request = new HashMap<>();
            request.put("grpNo", "1");
            request.put("grpName", "수정된회선그룹");

            mockMvc.perform(put(BASE_URL + "/if")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(toJson(request)))
                    .andExpect(status().isOk());
        }

        @Test
        @DisplayName("회선그룹 삭제")
        void testDeleteIfGroup() throws Exception {
            final Map<String, Object> request = new HashMap<>();
            request.put("grpNo", "9999");

            mockMvc.perform(delete(BASE_URL + "/if")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(toJson(request)))
                    .andExpect(status().isOk());
        }
    }

    @Nested
    @DisplayName("서버그룹 API 테스트")
    class ServerGroupTest {

        @Test
        @DisplayName("서버그룹 트리 목록 조회")
        void testGetServerGroupTreeList() throws Exception {
            mockMvc.perform(get(BASE_URL + "/server/tree"))
                    .andExpect(status().isOk())
                    .andExpect(MockMvcResultMatchers.content().contentType("application/json"));
        }

        @Test
        @DisplayName("서버그룹 등록")
        void testAddServerGroup() throws Exception {
            final Map<String, Object> request = new HashMap<>();
            request.put("grpName", "테스트서버그룹");
            request.put("grpParent", 0);

            mockMvc.perform(post(BASE_URL + "/server")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(toJson(request)))
                    .andExpect(status().isOk());
        }

        @Test
        @DisplayName("서버그룹 수정")
        void testEditServerGroup() throws Exception {
            final Map<String, Object> request = new HashMap<>();
            request.put("grpNo", "1");
            request.put("grpName", "수정된서버그룹");

            mockMvc.perform(put(BASE_URL + "/server")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(toJson(request)))
                    .andExpect(status().isOk());
        }

        @Test
        @DisplayName("서버그룹 삭제")
        void testDeleteServerGroup() throws Exception {
            final Map<String, Object> request = new HashMap<>();
            request.put("grpNo", "9999");

            mockMvc.perform(delete(BASE_URL + "/server")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(toJson(request)))
                    .andExpect(status().isOk());
        }
    }

    @Nested
    @DisplayName("망그룹 API 테스트")
    class NetworkGroupTest {

        @Test
        @DisplayName("망그룹 트리 목록 조회")
        void testGetNetworkGroupTreeList() throws Exception {
            mockMvc.perform(get(BASE_URL + "/network/tree"))
                    .andExpect(status().isOk())
                    .andExpect(MockMvcResultMatchers.content().contentType("application/json"));
        }

        @Test
        @DisplayName("망그룹 등록")
        void testAddNetworkGroup() throws Exception {
            final Map<String, Object> request = new HashMap<>();
            request.put("grpName", "테스트망그룹");
            request.put("grpParent", 0);

            mockMvc.perform(post(BASE_URL + "/network")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(toJson(request)))
                    .andExpect(status().isOk());
        }

        @Test
        @DisplayName("망그룹 수정")
        void testEditNetworkGroup() throws Exception {
            final Map<String, Object> request = new HashMap<>();
            request.put("grpNo", "1");
            request.put("grpName", "수정된망그룹");

            mockMvc.perform(put(BASE_URL + "/network")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(toJson(request)))
                    .andExpect(status().isOk());
        }

        @Test
        @DisplayName("망그룹 삭제")
        void testDeleteNetworkGroup() throws Exception {
            final Map<String, Object> request = new HashMap<>();
            request.put("grpNo", "9999");

            mockMvc.perform(delete(BASE_URL + "/network")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(toJson(request)))
                    .andExpect(status().isOk());
        }
    }

    @Nested
    @DisplayName("망흐름그룹 API 테스트")
    class NetworkFlowGroupTest {

        @Test
        @DisplayName("망흐름그룹 트리 목록 조회")
        void testGetNetworkFlowGroupTreeList() throws Exception {
            mockMvc.perform(get(BASE_URL + "/network-flow/tree"))
                    .andExpect(status().isOk())
                    .andExpect(MockMvcResultMatchers.content().contentType("application/json"));
        }

        @Test
        @DisplayName("망흐름그룹 등록")
        void testAddNetworkFlowGroup() throws Exception {
            final Map<String, Object> request = new HashMap<>();
            request.put("grpName", "테스트망흐름그룹");
            request.put("grpParent", 0);

            mockMvc.perform(post(BASE_URL + "/network-flow")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(toJson(request)))
                    .andExpect(status().isOk());
        }

        @Test
        @DisplayName("망흐름그룹 수정")
        void testEditNetworkFlowGroup() throws Exception {
            final Map<String, Object> request = new HashMap<>();
            request.put("grpNo", "1");
            request.put("grpName", "수정된망흐름그룹");

            mockMvc.perform(put(BASE_URL + "/network-flow")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(toJson(request)))
                    .andExpect(status().isOk());
        }

        @Test
        @DisplayName("망흐름그룹 삭제")
        void testDeleteNetworkFlowGroup() throws Exception {
            final Map<String, Object> request = new HashMap<>();
            request.put("grpNo", "9999");

            mockMvc.perform(delete(BASE_URL + "/network-flow")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(toJson(request)))
                    .andExpect(status().isOk());
        }
    }

    @Nested
    @DisplayName("서비스그룹 API 테스트")
    class ServiceGroupTest {

        @Test
        @DisplayName("서비스그룹 트리 목록 조회")
        void testGetServiceGroupTreeList() throws Exception {
            mockMvc.perform(get(BASE_URL + "/service/tree"))
                    .andExpect(status().isOk())
                    .andExpect(MockMvcResultMatchers.content().contentType("application/json"));
        }

        @Test
        @DisplayName("서비스그룹 등록")
        void testAddServiceGroup() throws Exception {
            final Map<String, Object> request = new HashMap<>();
            request.put("grpName", "테스트서비스그룹");
            request.put("grpParent", 0);

            mockMvc.perform(post(BASE_URL + "/service")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(toJson(request)))
                    .andExpect(status().isOk());
        }

        @Test
        @DisplayName("서비스그룹 수정")
        void testEditServiceGroup() throws Exception {
            final Map<String, Object> request = new HashMap<>();
            request.put("grpNo", "1");
            request.put("grpName", "수정된서비스그룹");

            mockMvc.perform(put(BASE_URL + "/service")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(toJson(request)))
                    .andExpect(status().isOk());
        }

        @Test
        @DisplayName("서비스그룹 삭제")
        void testDeleteServiceGroup() throws Exception {
            final Map<String, Object> request = new HashMap<>();
            request.put("grpNo", "9999");

            mockMvc.perform(delete(BASE_URL + "/service")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(toJson(request)))
                    .andExpect(status().isOk());
        }
    }

    @Nested
    @DisplayName("서비스포트그룹 API 테스트")
    class ServicePortGroupTest {

        @Test
        @DisplayName("서비스포트그룹 목록 조회")
        void testGetServicePortGroupList() throws Exception {
            mockMvc.perform(get(BASE_URL + "/service-port"))
                    .andExpect(status().isOk())
                    .andExpect(MockMvcResultMatchers.content().contentType("application/json"));
        }

        @Test
        @DisplayName("서비스포트그룹 등록")
        void testAddServicePortGroup() throws Exception {
            final Map<String, Object> request = new HashMap<>();
            request.put("grpName", "테스트서비스포트그룹");
            request.put("grpParent", 0);

            mockMvc.perform(post(BASE_URL + "/service-port")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(toJson(request)))
                    .andExpect(status().isOk());
        }

        @Test
        @DisplayName("서비스포트그룹 수정")
        void testEditServicePortGroup() throws Exception {
            final Map<String, Object> request = new HashMap<>();
            request.put("grpNo", "1");
            request.put("grpName", "수정된서비스포트그룹");

            mockMvc.perform(put(BASE_URL + "/service-port")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(toJson(request)))
                    .andExpect(status().isOk());
        }

        @Test
        @DisplayName("서비스포트그룹 삭제")
        void testDeleteServicePortGroup() throws Exception {
            final Map<String, Object> request = new HashMap<>();
            request.put("grpNo", "9999");

            mockMvc.perform(delete(BASE_URL + "/service-port")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(toJson(request)))
                    .andExpect(status().isOk());
        }
    }

    @Nested
    @DisplayName("App그룹 API 테스트")
    class AppGroupTest {

        @Test
        @DisplayName("App그룹 트리 목록 조회")
        void testGetAppGroupTreeList() throws Exception {
            mockMvc.perform(get(BASE_URL + "/app/tree"))
                    .andExpect(status().isOk())
                    .andExpect(MockMvcResultMatchers.content().contentType("application/json"));
        }

        @Test
        @DisplayName("App그룹 등록")
        void testAddAppGroup() throws Exception {
            final Map<String, Object> request = new HashMap<>();
            request.put("grpName", "테스트App그룹");
            request.put("grpParent", 0);

            mockMvc.perform(post(BASE_URL + "/app")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(toJson(request)))
                    .andExpect(status().isOk());
        }

        @Test
        @DisplayName("App그룹 수정")
        void testEditAppGroup() throws Exception {
            final Map<String, Object> request = new HashMap<>();
            request.put("grpNo", "1");
            request.put("grpName", "수정된App그룹");

            mockMvc.perform(put(BASE_URL + "/app")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(toJson(request)))
                    .andExpect(status().isOk());
        }

        @Test
        @DisplayName("App그룹 삭제")
        void testDeleteAppGroup() throws Exception {
            final Map<String, Object> request = new HashMap<>();
            request.put("grpNo", "9999");

            mockMvc.perform(delete(BASE_URL + "/app")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(toJson(request)))
                    .andExpect(status().isOk());
        }
    }

    @Nested
    @DisplayName("As그룹 API 테스트")
    class AsGroupTest {

        @Test
        @DisplayName("As그룹 트리 목록 조회")
        void testGetAsGroupTreeList() throws Exception {
            mockMvc.perform(get(BASE_URL + "/as/tree"))
                    .andExpect(status().isOk())
                    .andExpect(MockMvcResultMatchers.content().contentType("application/json"));
        }

        @Test
        @DisplayName("As그룹 등록")
        void testAddAsGroup() throws Exception {
            final Map<String, Object> request = new HashMap<>();
            request.put("grpName", "테스트As그룹");
            request.put("grpParent", 0);

            mockMvc.perform(post(BASE_URL + "/as")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(toJson(request)))
                    .andExpect(status().isOk());
        }

        @Test
        @DisplayName("As그룹 수정")
        void testEditAsGroup() throws Exception {
            final Map<String, Object> request = new HashMap<>();
            request.put("grpNo", "1");
            request.put("grpName", "수정된As그룹");

            mockMvc.perform(put(BASE_URL + "/as")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(toJson(request)))
                    .andExpect(status().isOk());
        }

        @Test
        @DisplayName("As그룹 삭제")
        void testDeleteAsGroup() throws Exception {
            final Map<String, Object> request = new HashMap<>();
            request.put("grpNo", "9999");

            mockMvc.perform(delete(BASE_URL + "/as")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(toJson(request)))
                    .andExpect(status().isOk());
        }
    }

    @Nested
    @DisplayName("기관그룹 API 테스트")
    class InstitutionGroupTest {

        @Test
        @DisplayName("기관그룹 트리 목록 조회")
        void testGetInstitutionGroupTreeList() throws Exception {
            mockMvc.perform(get(BASE_URL + "/institution/tree"))
                    .andExpect(status().isOk())
                    .andExpect(MockMvcResultMatchers.content().contentType("application/json"));
        }
    }
}
