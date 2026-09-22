package com.example.shop_hoa;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
class ShopHoaApplicationTests {

	@Test
	void contextLoads() {
	}

}
