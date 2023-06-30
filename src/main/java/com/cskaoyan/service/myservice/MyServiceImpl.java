package com.cskaoyan.service.myservice;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyServiceImpl implements MyService {
    @Override
    public Integer find(List<String> id) {
        if(id.size() > 0){
            return id.size();
        }
        return null;
    }
}
