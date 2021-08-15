package com.cskaoyan.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;


@Data
@AllArgsConstructor
public class BaseRespData2<T> {

    private List<T> data;
    private Long count;
    private Integer currentPage;

    public static <T> BaseRespData2 create(List<T> data, long count,Integer currentPage) {
        return new BaseRespData2(data, count,currentPage);
    }

}
