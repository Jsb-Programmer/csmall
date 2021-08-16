package com.cskaoyan.bean.vo.brandcs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WxBrandListVO<T> {
    List<T> brandList;
    long totalPages;

    public static <T> WxBrandListVO create(List<T> items, long total) {
        return new WxBrandListVO(items, total);
    }
}
