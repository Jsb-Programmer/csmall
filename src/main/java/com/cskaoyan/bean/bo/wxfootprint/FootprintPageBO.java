package com.cskaoyan.bean.bo.wxfootprint;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @description:
 * @author: Woo
 * @create: 2021-08-16 14:20
 **/

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FootprintPageBO {
    List<FootprintListBO> footprintList;
    Long totalPages;
}
