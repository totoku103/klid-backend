package com.klid.api.env.usermgmt.persistence;

import com.klid.api.env.usermgmt.dto.CommUserDTO;
import com.klid.api.env.usermgmt.dto.CommUserRequestUserInfoDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserManagementMapper {
    CommUserDTO selectCommUserBySeq(Integer seq);
    int insertCommUserRequest(CommUserRequestUserInfoDTO dto);
}
