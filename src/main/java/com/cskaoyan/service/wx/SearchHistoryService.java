package com.cskaoyan.service.wx;

import com.cskaoyan.bean.vo.brandcs.SearchIndexVO;

public interface SearchHistoryService {
    SearchIndexVO searchIndex();

    void searchClearhistory();
}
