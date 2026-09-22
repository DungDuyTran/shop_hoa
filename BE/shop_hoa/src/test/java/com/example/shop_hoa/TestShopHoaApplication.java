package com.example.shop_hoa;

import org.springframework.boot.SpringApplication;

public class TestShopHoaApplication {

	public static void main(String[] args) {
		SpringApplication.from(ShopHoaApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
