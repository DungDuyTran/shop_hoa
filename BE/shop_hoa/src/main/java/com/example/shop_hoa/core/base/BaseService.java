package com.example.shop_hoa.core.base;

import org.modelmapper.ModelMapper;
import java.util.List;
import java.util.stream.Collectors;

public class BaseService<T, ID, REQ, RES> {
    protected final BaseRepository<T, ID> repository;
    protected final ModelMapper modelMapper;

    private final Class<T> entityClass;
    private final Class<RES> responseClass;

    public BaseService(BaseRepository<T, ID> repository, ModelMapper modelMapper,
                       Class<T> entityClass, Class<RES> responseClass) {
        this.repository = repository;
        this.modelMapper = modelMapper;
        this.entityClass = entityClass;
        this.responseClass = responseClass;
    }

    public List<RES> getAll() {
        return repository.findAll().stream()
                .map(entity -> modelMapper.map(entity, responseClass))
                .collect(Collectors.toList());
    }

    public RES getById(ID id) {
        T entity = repository.findById(id);
        if (entity == null) throw new RuntimeException("Resource not found"); // Có thể custom ném Exception tập trung ở đây
        return modelMapper.map(entity, responseClass);
    }

    public RES create(REQ request) {
        T entity = modelMapper.map(request, entityClass);
        T saved = repository.save(entity);
        return modelMapper.map(saved, responseClass);
    }

    public void delete(ID id) {
        repository.deleteById(id);
    }
}