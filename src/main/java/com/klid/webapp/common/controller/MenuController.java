package com.klid.webapp.common.controller;

import com.klid.webapp.common.Criterion;
import com.klid.webapp.common.ReturnData;
import com.klid.webapp.common.menu.dto.SimpleMenuDTO;
import com.klid.webapp.common.menu.service.MenuService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/common/menu")
@Controller
@Slf4j
public class MenuController {
    @Resource(name = "menuService")
    private MenuService service;

    @RequestMapping(value = "getMenuTag", method = RequestMethod.POST)
    public @ResponseBody
    ReturnData getMenuTag() {
        return service.getSiteMenuList(new Criterion());
    }

    @GetMapping("getSimpleMenuList")
    public @ResponseBody
    ReturnData getSimpleMenuList(@RequestParam(value = "targetAuthGrpNo", required = false) Object targetAuthGrpNo) {
        Criterion criterion = new Criterion();
        if (targetAuthGrpNo != null) {
            criterion.addParam("targetAuthGrpNo", targetAuthGrpNo);
        }
        return service.getSimpleMenuList(criterion);
    }

    @PostMapping("getExcludeMenuList")
    public @ResponseBody
    List<SimpleMenuDTO> getExcludeMenuList(@RequestParam("authGrpNo") String authGrpNo) {
        return service.getExcludeMenuList(authGrpNo);
    }

    @PostMapping("saveExcludeMenuList")
    public @ResponseBody
    void saveExcludeMenuList(@RequestParam("authGrpNo") String authGrpNo, @RequestParam(value = "guids", required = false) String[] guids) {
        service.saveExcludeMenuList(authGrpNo, guids);
    }
}
