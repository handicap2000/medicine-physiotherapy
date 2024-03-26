package com.mc.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
public class PageUtils {
    @Value("${page.size}")
    private Integer size;

    /**
     * 订单排序，按createTime
     * @return
     */
    public Pageable pageOrderByCreateTime() {
        //  订单按创建时间排序
        Sort sort = Sort.by(Sort.Order.desc("createTime"));
        return PageRequest.of(0, size, sort);
    }

    /**
     * 排序，按Id顺序
     * @return
     */
    public Pageable pageOrderById() {
        //  订单按Id排序
        Sort sort = Sort.by(Sort.Order.asc("id"));
        return PageRequest.of(0, size, sort);
    }
}
