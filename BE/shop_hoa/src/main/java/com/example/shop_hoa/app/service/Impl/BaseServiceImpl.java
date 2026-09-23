package com.example.shop_hoa.app.service.Impl;

import com.example.shop_hoa.app.service.BaseService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public abstract class BaseServiceImpl<T,ID,REQ,RES> implements BaseService<T,ID,REQ,RES> {
    protected abstract JpaRepository<T, ID> getRepository();
    protected abstract RES mapToResponse(T entity);
    protected abstract T mapToEntity(REQ request);
    protected abstract void updateEntity(REQ request, T entity);

    @Override
    public List<RES> getAll(){
        return getRepository().findAll().stream().map(this::mapToResponse).toList();
    }
    @Override
    public RES getById(ID id){
        T entity = getRepository().findById(id).orElseThrow(() -> new RuntimeException("Không tìm thấy ữ liệu với id này" + id));
        return mapToResponse(entity);
    }
    @Override
    public RES create(REQ request){
        T entity = mapToEntity(request);
        return mapToResponse(getRepository().save(entity));
    }
    @Override
    public RES update(ID id,REQ request){
        T entity = getRepository().findById(id).orElseThrow(() -> new RuntimeException("Không tìm thấy dữ liệu nào với id : " + id));
        updateEntity(request,entity);
        return mapToResponse(getRepository().save(entity));
    }
    @Override
    public void delete(ID id){
        getRepository().deleteById(id);
    }
}
