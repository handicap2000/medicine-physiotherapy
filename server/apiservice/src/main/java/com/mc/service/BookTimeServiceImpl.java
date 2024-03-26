package com.mc.service;

import com.mc.dao.BookTimeRespository;

import com.mc.dto.BookTimeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookTimeServiceImpl implements BookTimeService {
    @Autowired
    BookTimeRespository bookTimeRespository;

    @Override
    public List<BookTimeDTO> getBookTimeList() {
        return bookTimeRespository.getBookTime();
    }
}
