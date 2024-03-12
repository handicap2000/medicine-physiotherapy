package com.mc.service;

import com.mc.dao.MasterRepository;
import com.mc.dto.MasterListDTO;
import com.mc.po.Master;
import com.mc.util.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MasterServiceImpl implements MasterService{

    @Autowired
    PageUtils pageUtils;

    @Autowired
    MasterRepository masterRepository;
    @Override
    public Master saveMaster(Master master) {
        master.setCreateTime(new Date());
        return masterRepository.save(master);
    }

    @Override
    public Page<Master> listMaster() {

        return masterRepository.findAll(pageUtils.pageOrderByCreateTime());
    }

    @Override
    public Page<MasterListDTO> getMasterList() {
        return masterRepository.getMasterAll(pageUtils.pageOrderByCreateTime());
    }

}
