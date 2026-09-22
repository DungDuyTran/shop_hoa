package com.example.shop_hoa.core.base;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

public class BaseController<T, ID, REQ, RES> {
    private final BaseService<T, ID, REQ, RES> service;

    public BaseController(BaseService<T, ID, REQ, RES> service) {
        this.service = service;
    }

    @ResponseBody
    public ResponseEntity<List<RES>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @ResponseBody
    public ResponseEntity<RES> getById(@PathVariable("id") ID id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @ResponseBody
    public ResponseEntity<RES> create(@RequestBody REQ request) {
        return new ResponseEntity<>(service.create(request), HttpStatus.CREATED);
    }

    @ResponseBody
    public ResponseEntity<Void> delete(@PathVariable("id") ID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}