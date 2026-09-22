package com.example.shop_hoa.core.annotation;

import org.springframework.stereotype.Component;
import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Component
@Documented
public @interface CustomService {
    Class<?> entity(); // Trỏ đến Entity để đè logic Service
}