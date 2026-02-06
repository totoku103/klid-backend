package com.klid.webapp.common.menu.service;

import com.klid.webapp.common.Criterion;
import com.klid.webapp.common.ReturnData;
import com.klid.webapp.common.menu.dto.SimpleMenuDTO;

import java.util.List;

public interface MenuService {

    ReturnData getSiteMenuList(Criterion criterion);

    ReturnData getSimpleMenuList(Criterion criterion);

    List<SimpleMenuDTO> getExcludeMenuList(String authGrpNo);

    ReturnData getDefineMenuList(Criterion criterion);

    void saveExcludeMenuList(String authGrpNo, String[] guids);
}
