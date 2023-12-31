package com.cskaoyan.service.wx;

import com.cskaoyan.bean.vo.brandcs.SearchIndexVO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface SearchHistoryService {
    SearchIndexVO searchIndex(HttpServletRequest request);

    void searchClearhistory();

    List<String> searchHelper(String keyword);
}
