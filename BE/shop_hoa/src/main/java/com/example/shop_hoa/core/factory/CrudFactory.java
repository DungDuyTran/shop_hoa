package com.example.shop_hoa.core.factory;
import org.springframework.beans.factory.annotation.Qualifier;
import com.example.shop_hoa.core.annotation.GenCrud;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.Method;

public class CrudFactory {

    @Autowired
    private ApplicationContext context;

    @Autowired
    @Qualifier("requestMappingHandlerMapping")
    private RequestMappingHandlerMapping handlerMapping;

    @PostConstruct
    public void buildEndpoints() throws Exception {
        String[] beanNames = context.getBeanNamesForAnnotation(GenCrud.class);

        for (String entityBeanName : beanNames) {
            Class<?> entityClass = context.getType(entityBeanName);
            if (entityClass == null) continue;

            String entityName = entityClass.getSimpleName().toLowerCase();
            GenCrud annotation = entityClass.getAnnotation(GenCrud.class);

            // Tìm BaseController đã được đăng ký
            String controllerBeanName = entityName + "basecontroller";
            if (!context.containsBean(controllerBeanName)) continue;

            Object controller = context.getBean(controllerBeanName);
            String basePath = "/api/v1/" + entityName + "s";

            // Map GET /api/v1/entities
            RequestMappingInfo getInfo = RequestMappingInfo.paths(basePath).methods(RequestMethod.GET).build();
            Method getMethod = controller.getClass().getMethod("getAll");
            handlerMapping.registerMapping(getInfo, controller, getMethod);

            // Map GET /api/v1/entities/{id}
            RequestMappingInfo getByIdInfo = RequestMappingInfo.paths(basePath + "/{id}").methods(RequestMethod.GET).build();
            Method getByIdMethod = controller.getClass().getMethod("getById", Object.class);
            handlerMapping.registerMapping(getByIdInfo, controller, getByIdMethod);

            // Map POST /api/v1/entities
            RequestMappingInfo postInfo = RequestMappingInfo.paths(basePath).methods(RequestMethod.POST).build();
            Method postMethod = controller.getClass().getMethod("create", Object.class);
            handlerMapping.registerMapping(postInfo, controller, postMethod);

            // Map DELETE /api/v1/entities/{id}
            RequestMappingInfo deleteInfo = RequestMappingInfo.paths(basePath + "/{id}").methods(RequestMethod.DELETE).build();
            Method deleteMethod = controller.getClass().getMethod("delete", Object.class);
            handlerMapping.registerMapping(deleteInfo, controller, deleteMethod);

            System.out.println("🚀 [Gen-All Engine] Mapped: " + basePath);
        }
    }
}