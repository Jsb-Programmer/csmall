package com.cskaoyan.service.wx;

import com.cskaoyan.bean.vo.brandcs.SearchIndexVO;

import java.util.List;

public interface SearchHistoryService {
    SearchIndexVO searchIndex();

    void searchClearhistory();

    List<String> searchHelper(String keyword);
}
