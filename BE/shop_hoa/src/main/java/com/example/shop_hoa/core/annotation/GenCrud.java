package com.example.shop_hoa.core.annotation;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface GenCrud {
    Class<?> requestDto();  // DTO dùng cho POST, PUT
    Class<?> responseDto(); // DTO dùng để trả về GET
    Class<?> idType() default Long.class; // Mặc định ID là Long, có thể custom sang UUID/String
}