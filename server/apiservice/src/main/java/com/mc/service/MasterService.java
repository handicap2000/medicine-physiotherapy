package com.mc.service;

import com.mc.dto.MasterListDTO;
import com.mc.po.Master;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MasterService {
//    保存师傅数据
    Master saveMaster(Master master);
//    获取所有师傅
    Page<Master> listMaster();

    Page<MasterListDTO> getMasterList();
}
