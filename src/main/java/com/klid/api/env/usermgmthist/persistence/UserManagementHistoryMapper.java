package com.klid.api.env.usermgmthist.persistence;

import com.klid.api.env.usermgmthist.dto.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface UserManagementHistoryMapper {
    CommUserRequestDTO selectCommUserRequestBySeq(Integer seq);
    List<HistoryGridResDTO> selectUserManagementHistoryGrid(Map<String, Object> params);
    int insertRequestReviewState(Map<String, Object> params);
    String selectRequestTypeByCommUserRequestSeq(Integer commUserRequestSeq);
    LatestCommUserRequestProcessStateDTO selectLatestRequestProcessState(Integer commUserRequestSeq);
    int copyUserRequestForStateChange(Map<String, Object> params);
    SimpleUserInfoDTO selectCommUserRequestUserInfo(Integer seq);
    SimpleUserInfoDTO selectCommUserUserInfo(Integer seq);
    int insertApproveRejectRecord(Map<String, Object> params);
    SimpleUserInfoDTO selectCommUserByRequestSeq(Integer commUserRequestSeq);
    int updateCommUserFromRequest(Map<String, Object> params);
    int updateOtpSecretKeyIsNullByCommUserSeq(Integer commUserSeq);
    int updateGpkiSerialNoIsNullByCommUserSeq(Integer commUserSeq);
    int insertCommUserFromRequest(Integer commUserRequestSeq);
    int deleteCommUserFromRequest(Integer seq);
    List<StandByRegUserIdDTO> selectStandByRegUserIdList(String userId);
}
