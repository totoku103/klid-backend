package com.klid.api.common.group.persistence;

import com.klid.api.BaseMapperTest;
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
 * 그룹 관리 Mapper 통합 테스트
 */
@DisplayName("그룹 관리 Mapper 테스트")
class GroupMapperTest extends BaseMapperTest {

    @Autowired
    private GroupMapper groupMapper;

    @Test
    @DisplayName("그룹명 중복 체크")
    void testSelectGrpDuplCnt() {
        // given
        final Map<String, Object> params = new HashMap<>();
        params.put("grpName", "테스트그룹");
        params.put("grpType", "DEFAULT");

        // when
        final int result = groupMapper.selectGrpDuplCnt(params);

        // then
        assertThat(result).isGreaterThanOrEqualTo(0);
    }

    @Nested
    @DisplayName("권한그룹 Mapper 테스트")
    class AuthGroupMapperTest {

        @Test
        @DisplayName("권한그룹 목록 조회")
        void testSelectAuthGrpList() {
            // when
            final List<AuthGroupDTO> result = groupMapper.selectAuthGrpList();

            // then
            assertThat(result).isNotNull();
        }

        @Test
        @DisplayName("권한그룹 등록")
        void testInsertAuthGrp() {
            // given
            final Map<String, Object> params = new HashMap<>();
            params.put("authGrpName", "테스트권한그룹");
            params.put("authGrpDesc", "테스트 설명");

            // when & then - 예외가 발생하지 않으면 성공
            groupMapper.insertAuthGrp(params);
        }

        @Test
        @DisplayName("권한그룹 수정")
        void testUpdateAuthGrp() {
            // given
            final Map<String, Object> params = new HashMap<>();
            params.put("authGrpNo", "1");
            params.put("authGrpName", "수정된권한그룹");
            params.put("authGrpDesc", "수정된 설명");

            // when & then - 예외가 발생하지 않으면 성공
            groupMapper.updateAuthGrp(params);
        }

        @Test
        @DisplayName("권한그룹 삭제")
        void testDeleteAuthGrp() {
            // given
            final Map<String, Object> params = new HashMap<>();
            params.put("authGrpNo", "9999");

            // when & then - 예외가 발생하지 않으면 성공
            groupMapper.deleteAuthGrp(params);
        }
    }

    @Nested
    @DisplayName("기본그룹 Mapper 테스트")
    class DefaultGroupMapperTest {

        @Test
        @DisplayName("권한 설정용 기본그룹 트리 목록 전체 조회")
        void testSelectAuthConfDefaultGrpTreeListAll() {
            // given
            final Map<String, Object> params = new HashMap<>();

            // when
            final List<GroupDTO> result = groupMapper.selectAuthConfDefaultGrpTreeListAll(params);

            // then
            assertThat(result).isNotNull();
        }

        @Test
        @DisplayName("기본그룹 트리 목록 전체 조회")
        void testSelectDefaultGrpTreeListAll() {
            // given
            final Map<String, Object> params = new HashMap<>();

            // when
            final List<GroupDTO> result = groupMapper.selectDefaultGrpTreeListAll(params);

            // then
            assertThat(result).isNotNull();
        }

        @Test
        @DisplayName("기본그룹 트리 목록 조회")
        void testSelectDefaultGrpTreeList() {
            // given
            final Map<String, Object> params = new HashMap<>();

            // when
            final List<GroupDTO> result = groupMapper.selectDefaultGrpTreeList(params);

            // then
            assertThat(result).isNotNull();
        }

        @Test
        @DisplayName("VM 서버 그룹 트리 목록 조회")
        void testSelectVmSvrGrpTreeList() {
            // given
            final Map<String, Object> params = new HashMap<>();

            // when
            final List<GroupDTO> result = groupMapper.selectVmSvrGrpTreeList(params);

            // then
            assertThat(result).isNotNull();
        }

        @Test
        @DisplayName("L4 기본그룹 트리 목록 조회")
        void testSelectL4DefaultGrpTreeList() {
            // given
            final Map<String, Object> params = new HashMap<>();

            // when
            final List<GroupDTO> result = groupMapper.selectL4DefaultGrpTreeList(params);

            // then
            assertThat(result).isNotNull();
        }

        @Test
        @DisplayName("AP 기본그룹 트리 목록 조회")
        void testSelectApDefaultGrpTreeList() {
            // given
            final Map<String, Object> params = new HashMap<>();

            // when
            final List<GroupDTO> result = groupMapper.selectApDefaultGrpTreeList(params);

            // then
            assertThat(result).isNotNull();
        }
    }

    @Nested
    @DisplayName("조회그룹 Mapper 테스트")
    class SearchGroupMapperTest {

        @Test
        @DisplayName("조회그룹 트리 목록 조회")
        void testSelectSearchGrpTreeList() {
            // given
            final Map<String, Object> params = new HashMap<>();

            // when
            final List<GroupDTO> result = groupMapper.selectSearchGrpTreeList(params);

            // then
            assertThat(result).isNotNull();
        }
    }

    @Nested
    @DisplayName("회선그룹 Mapper 테스트")
    class IfGroupMapperTest {

        @Test
        @DisplayName("회선그룹 트리 목록 조회")
        void testSelectIfGrpTreeList() {
            // given
            final Map<String, Object> params = new HashMap<>();

            // when
            final List<GroupDTO> result = groupMapper.selectIfGrpTreeList(params);

            // then
            assertThat(result).isNotNull();
        }
    }

    @Nested
    @DisplayName("서버그룹 Mapper 테스트")
    class ServerGroupMapperTest {

        @Test
        @DisplayName("서버그룹 트리 목록 조회")
        void testSelectServerGrpTreeList() {
            // given
            final Map<String, Object> params = new HashMap<>();

            // when
            final List<GroupDTO> result = groupMapper.selectServerGrpTreeList(params);

            // then
            assertThat(result).isNotNull();
        }
    }

    @Nested
    @DisplayName("망그룹 Mapper 테스트")
    class NetworkGroupMapperTest {

        @Test
        @DisplayName("망그룹 트리 목록 조회")
        void testSelectMangGrpTreeList() {
            // given
            final Map<String, Object> params = new HashMap<>();

            // when
            final List<GroupDTO> result = groupMapper.selectMangGrpTreeList(params);

            // then
            assertThat(result).isNotNull();
        }
    }

    @Nested
    @DisplayName("망흐름그룹 Mapper 테스트")
    class NetworkFlowGroupMapperTest {

        @Test
        @DisplayName("망흐름그룹 트리 목록 조회")
        void testSelectMangFlowGrpTreeList() {
            // given
            final Map<String, Object> params = new HashMap<>();

            // when
            final List<GroupDTO> result = groupMapper.selectMangFlowGrpTreeList(params);

            // then
            assertThat(result).isNotNull();
        }
    }

    @Nested
    @DisplayName("서비스그룹 Mapper 테스트")
    class ServiceGroupMapperTest {

        @Test
        @DisplayName("서비스그룹 트리 목록 조회")
        void testSelectServiceGrpTreeList() {
            // given
            final Map<String, Object> params = new HashMap<>();

            // when
            final List<GroupDTO> result = groupMapper.selectServiceGrpTreeList(params);

            // then
            assertThat(result).isNotNull();
        }
    }

    @Nested
    @DisplayName("서비스포트그룹 Mapper 테스트")
    class ServicePortGroupMapperTest {

        @Test
        @DisplayName("서비스포트그룹 목록 조회")
        void testSelectSvcPortGrpList() {
            // given
            final Map<String, Object> params = new HashMap<>();

            // when
            final List<GroupDTO> result = groupMapper.selectSvcPortGrpList(params);

            // then
            assertThat(result).isNotNull();
        }
    }

    @Nested
    @DisplayName("App그룹 Mapper 테스트")
    class AppGroupMapperTest {

        @Test
        @DisplayName("App그룹 트리 목록 조회")
        void testSelectAppGrpTreeList() {
            // given
            final Map<String, Object> params = new HashMap<>();

            // when
            final List<GroupDTO> result = groupMapper.selectAppGrpTreeList(params);

            // then
            assertThat(result).isNotNull();
        }
    }

    @Nested
    @DisplayName("As그룹 Mapper 테스트")
    class AsGroupMapperTest {

        @Test
        @DisplayName("As그룹 트리 목록 조회")
        void testSelectAsGrpTreeList() {
            // given
            final Map<String, Object> params = new HashMap<>();

            // when
            final List<GroupDTO> result = groupMapper.selectAsGrpTreeList(params);

            // then
            assertThat(result).isNotNull();
        }
    }

    @Nested
    @DisplayName("기관그룹 Mapper 테스트")
    class InstitutionGroupMapperTest {

        @Test
        @DisplayName("기관그룹 트리 목록 조회")
        void testSelectInstGrpTreeList() {
            // given
            final Map<String, Object> params = new HashMap<>();

            // when
            final List<GroupDTO> result = groupMapper.selectInstGrpTreeList(params);

            // then
            assertThat(result).isNotNull();
        }
    }
}
