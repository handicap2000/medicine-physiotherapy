package com.mc.service;

import com.mc.dto.BookTimeDTO;

import java.util.List;

public interface BookTimeService {
    //    获取所有设置的预约时间
    List<BookTimeDTO> getBookTimeList();
}
