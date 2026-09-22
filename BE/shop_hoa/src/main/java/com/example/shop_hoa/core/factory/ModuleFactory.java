package com.example.shop_hoa.core.factory;

import com.example.shop_hoa.core.annotation.CustomService;
import com.example.shop_hoa.core.annotation.GenCrud;
import com.example.shop_hoa.core.base.BaseController;
import com.example.shop_hoa.core.base.BaseRepository;
import com.example.shop_hoa.core.base.BaseService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;

import java.util.HashMap;
import java.util.Map;

public class ModuleFactory implements BeanDefinitionRegistryPostProcessor, ApplicationContextAware {

    private ApplicationContext applicationContext;
    private final Map<Class<?>, String> customServicesMap = new HashMap<>();

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        // 1. Quét tìm @CustomService
        Map<String, Object> customBeans = applicationContext.getBeansWithAnnotation(CustomService.class);
        for (Map.Entry<String, Object> entry : customBeans.entrySet()) {
            CustomService ann = entry.getValue().getClass().getAnnotation(CustomService.class);
            customServicesMap.put(ann.entity(), entry.getKey());
        }

        // 2. Quét tìm @GenCrud trong toàn bộ source code
        ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(false);
        scanner.addIncludeFilter(new AnnotationTypeFilter(GenCrud.class));

        for (BeanDefinition bd : scanner.findCandidateComponents("com.example.shop_hoa")) { // Quét từ root package
            try {
                Class<?> entityClass = Class.forName(bd.getBeanClassName());
                GenCrud annotation = entityClass.getAnnotation(GenCrud.class);
                String entityName = entityClass.getSimpleName();

                // 3a. Gen Repository Bean
                String repoBeanName = entityName.toLowerCase() + "BaseRepo";
                BeanDefinition repoDef = BeanDefinitionBuilder.genericBeanDefinition(BaseRepository.class)
                        .addConstructorArgReference("entityManagerFactory")
                        .addConstructorArgValue(entityClass)
                        .getBeanDefinition();
                registry.registerBeanDefinition(repoBeanName, repoDef);

                // 3b. Gen Service Bean (Phân nhánh Inject)
                String serviceBeanName = customServicesMap.get(entityClass);
                if (serviceBeanName == null) { // KHÔNG có CustomService -> Tự sinh BaseService
                    serviceBeanName = entityName.toLowerCase() + "BaseService";
                    BeanDefinition serviceDef = BeanDefinitionBuilder.genericBeanDefinition(BaseService.class)
                            .addConstructorArgReference(repoBeanName)
                            .addConstructorArgReference("modelMapper") // Bơm ModelMapper
                            .addConstructorArgValue(entityClass)       // Bơm Type cho Runtime
                            .addConstructorArgValue(annotation.responseDto())
                            .getBeanDefinition();
                    registry.registerBeanDefinition(serviceBeanName, serviceDef);
                }

                // 3c. Gen Controller Bean
                String controllerBeanName = entityName.toLowerCase() + "BaseController";
                BeanDefinition controllerDef = BeanDefinitionBuilder.genericBeanDefinition(BaseController.class)
                        .addConstructorArgReference(serviceBeanName)
                        .getBeanDefinition();
                registry.registerBeanDefinition(controllerBeanName, controllerDef);

            } catch (ClassNotFoundException e) {
                throw new RuntimeException("Lỗi ModuleFactory lúc quét metadata", e);
            }
        }
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {}
}