package com.mc.controllrer;

import com.mc.common.api.CommonResult;
import com.mc.dto.MasterListDTO;
import com.mc.po.Master;
import com.mc.service.MasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ManagerController {

    @Autowired
    MasterService masterService;

    @PostMapping("/addMaster")
    public CommonResult addMaster(@RequestBody Master masterParam) {

        Master master = masterService.saveMaster(masterParam);
        return CommonResult.success();
    }

    @GetMapping("/listMaster")
    //public List<Master> listMaster(@PageableDefault(size = 100, sort = {"createTime"}, direction = Sort.Direction.DESC) Pageable pageable) {
    public List<Master> listMaster() {
        Page<Master> masterPage = masterService.listMaster();
        //System.out.println(masterList);
        return masterPage.getContent();
    }

    @GetMapping("/getMasterList")
    public List<MasterListDTO> getMasterList() {
        //System.out.println(masterList);
        Page<MasterListDTO> masterLisPage = masterService.getMasterList();
        return masterLisPage.getContent();
    }
}
