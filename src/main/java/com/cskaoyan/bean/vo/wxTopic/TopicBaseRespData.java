package com.cskaoyan.bean.vo.wxTopic;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;


@Data
@AllArgsConstructor
public class TopicBaseRespData<T> {

    private List<T> data;
    private Long count;
    private Integer currentPage;

    public static <T> TopicBaseRespData create(List<T> data, long count,Integer currentPage) {
        return new TopicBaseRespData(data, count,currentPage);
    }

}
