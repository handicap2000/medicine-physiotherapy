package com.mc.controllrer;

import com.mc.dto.BookTimeDTO;
import com.mc.service.BookTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookController {

    @Autowired
    BookTimeService bookTimeService;

    @GetMapping("/bookTimeList")
    public List<BookTimeDTO> getBookTimeList() {
        List<BookTimeDTO> bokTimeList = bookTimeService.getBookTimeList();
        System.out.println(bokTimeList);
        return bokTimeList;
    }
}
