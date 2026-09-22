package com.example.shop_hoa.core.config;

import com.example.shop_hoa.core.factory.CrudFactory;
import com.example.shop_hoa.core.factory.ModuleFactory;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GenAllStartupConfig {

    // 1. Kích hoạt Nhà máy Cấp 1 (Tạo Bean)
    @Bean
    public static ModuleFactory moduleFactory() {
        return new ModuleFactory(); // Phải là static để chạy cực sớm trong Lifecycle Spring
    }

    // 2. Kích hoạt Nhà máy Cấp 2 (Nối Router)
    @Bean
    public CrudFactory crudFactory() {
        return new CrudFactory();
    }

    // 3. Khởi tạo công cụ Map DTO (Dùng chung cho toàn bộ Base)
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT); // Set Strict để map chính xác trường
        return modelMapper;
    }
}