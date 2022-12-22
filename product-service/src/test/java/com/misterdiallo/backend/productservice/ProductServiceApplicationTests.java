package com.misterdiallo.backend.productservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.misterdiallo.backend.productservice.dto.ProductRequest;
import com.misterdiallo.backend.productservice.dto.ProductResponse;
import com.misterdiallo.backend.productservice.model.Product;
import com.misterdiallo.backend.productservice.repository.ProductRepository;
import com.misterdiallo.backend.productservice.service.ProductService;
import com.mongodb.assertions.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.math.BigDecimal;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
class ProductServiceApplicationTests {

	@Container
	static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:4.4.2");

	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private ProductRepository productRepository;

	private ProductService productService = Mockito.mock(ProductService.class);

	@DynamicPropertySource
	static void setProperties(DynamicPropertyRegistry dynamicPropertyRegistry) {
		dynamicPropertyRegistry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
	}

	@Test
	void shouldCreateProduct() throws Exception {
		ProductRequest productRequest = getProductRequest();
		String productRequestString  = objectMapper.writeValueAsString(productRequest);
		mockMvc.perform(MockMvcRequestBuilders.post("/api/product")
				.contentType(MediaType.APPLICATION_JSON)
				.content(productRequestString))
				.andExpect(status().isCreated());
		Assertions.assertTrue(productRepository.findAll().size() == 1);
	}

	@Test
	void displayAllProduct() throws Exception {
		 mockMvc.perform(MockMvcRequestBuilders.get("/api/product"))
				.andExpect(status().isOk()).andReturn();
		 List<ProductResponse> products = productService.getAllProducts();
		Mockito.when(productService.getAllProducts()).thenReturn(products);
//		return productRepository.findAll().stream().map(this::getProductResponse).toList();
//		Assertions.assertTrue(productRepository.findAll().size() == 1);
	}

	private ProductRequest getProductRequest() {
		return ProductRequest.builder()
				.name("Test Product name")
				.description("Test Product description")
				.price(BigDecimal.valueOf(10000))
				.build();
	}
	private ProductResponse getProductResponse(Product product) {
		return ProductResponse.builder()
				.id(product.getId())
				.name(product.getName())
				.description(product.getDescription())
				.price(product.getPrice())
				.build();
	}

}
