package com.example.shop_hoa.app.controller;

import com.example.shop_hoa.app.service.BaseService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public abstract class BaseController<T, ID, REQ, RES> {
    protected abstract BaseService<T, ID, REQ, RES> getService();

    @GetMapping
    public ResponseEntity<List<RES>> getAll() {
        return ResponseEntity.ok(getService().getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RES> getById(@PathVariable ID id) {
        return ResponseEntity.ok(getService().getById(id));
    }

    @PostMapping
    public ResponseEntity<RES> create(@RequestBody @Valid REQ request) {
        return new ResponseEntity<>(getService().create(request), HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    public ResponseEntity<RES> update(@PathVariable ID id, @RequestBody @Valid REQ request) {
        return ResponseEntity.ok(getService().update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable ID id) {
        getService().delete(id);
        return ResponseEntity.noContent().build();
    }
}