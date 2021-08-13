package com.cskaoyan.bean.vo.market;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseRespDataVO<T> {
    List<T> items;
    long total;

    public static <T> BaseRespDataVO create(List<T> items, long total) {
        return new BaseRespDataVO(items, total);
    }
}
