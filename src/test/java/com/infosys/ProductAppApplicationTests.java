package com.infosys;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.infosys.model.Product;
import com.infosys.repository.ProductRepository;
import com.infosys.service.ProductService;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class ProductAppApplicationTests {

	@MockBean
	private ProductRepository repository;
	@Autowired
	private ProductService service;

	@Test
	public void getProductstest() {

		Mockito.when(repository.findAll())
				.thenReturn(Stream.of(new Product(1, "Iphone", 5000, 1), new Product(2, "Samsung", 5000, 1))
						.collect(Collectors.toList()));
		assertEquals(2, service.getProducts().size());
	}

	@Test
	public void addProductTest() {
		Product product = new Product();
		product.setId(1);
		product.setName("Iphone");
		product.setPrice(5000);
		product.setQuantity(1);

		Mockito.when(repository.save(product)).thenReturn(product);

		assertEquals("Iphone", service.saveProduct(product).getName());
	}

	@Test
	public void addProductsTest() {
		Product product1 = new Product();
		product1.setId(1);
		product1.setName("Iphone");
		product1.setPrice(5000);
		product1.setQuantity(1);

		Product product2 = new Product();
		product2.setId(1);
		product2.setName("Iphone");
		product2.setPrice(5000);
		product2.setQuantity(1);

		List<Product> list = new ArrayList<>();
		list.add(product1);
		list.add(product2);

		Mockito.when(repository.saveAll(list)).thenReturn(list);

		assertEquals(2, service.saveProducts(list).size());
	}

	@Test
	public void getProductByNameTest() {
		String name = "Iphone";
		Product product3 = new Product(1, "Iphone", 5000, 1);
		Mockito.when(repository.findByName(name)).thenReturn(product3);
		assertEquals(product3, service.getProductByName(name));

	}

}
