package com.mc.dao;

import com.mc.dto.BookTimeDTO;
import com.mc.po.BookTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookTimeRespository extends JpaRepository<BookTime, Long> {
    //    查找所有预约时间
    @Query("select  new com.mc.dto.BookTimeDTO (o.id,o.pickTime,o.checked,o.available) from BookTime o")
    List<BookTimeDTO> getBookTime();
}
