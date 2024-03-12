package com.mc.dao;

import com.mc.dto.MasterListDTO;
import com.mc.po.Master;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface MasterRepository extends JpaRepository<Master, Long> {
    Page<Master> findAll(Pageable pageable);

    @Query("select  new com.mc.dto.MasterListDTO(m.id,m.name,m.avatar,m.mobile,m.type,m.description ) from Master m")
    Page<MasterListDTO> getMasterAll(Pageable pageable);
}
