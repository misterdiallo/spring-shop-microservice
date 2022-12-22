package com.misterdiallo.backend.inventoryservice;

import com.misterdiallo.backend.inventoryservice.model.Inventory;
import com.misterdiallo.backend.inventoryservice.repository.InventoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

//	@Bean
//	public CommandLineRunner loadData(InventoryRepository inventoryRepository) {
//		return args -> {
//			Inventory inventory = new Inventory();
//			inventory.setSkuCode("iphone_13");
//			inventory.setQuantity(3);
//
//			Inventory inventory1 = new Inventory();
//			inventory1.setSkuCode("iphone_14");
//			inventory1.setQuantity(10);
//
//			Inventory inventory2 = new Inventory();
//			inventory2.setSkuCode("iphone_12");
//			inventory2.setQuantity(1);
//
//			Inventory inventory3 = new Inventory();
//			inventory3.setSkuCode("iphone_13_black");
//			inventory3.setQuantity(0);
//
//			Inventory inventory4 = new Inventory();
//			inventory4.setSkuCode("iphone_13_red");
//			inventory4.setQuantity(0);
//
//			inventoryRepository.save(inventory);
//			inventoryRepository.save(inventory1);
//			inventoryRepository.save(inventory2);
//			inventoryRepository.save(inventory3);
//			inventoryRepository.save(inventory4);
//		};
//	}
}
