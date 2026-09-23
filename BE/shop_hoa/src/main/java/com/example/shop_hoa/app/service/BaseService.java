package com.example.shop_hoa.app.service;

import java.util.List;

public interface BaseService<T, ID, REQ, RES> {
    List<RES> getAll();
    RES getById(ID id);
    RES create(REQ request);
    RES update(ID id, REQ request);
    void delete(ID id);
}
