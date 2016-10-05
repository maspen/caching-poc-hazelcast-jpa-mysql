package com.intelligrated.poc.hazelcast.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.intelligrated.poc.hazelcast.entity.Product;

@Component
@Transactional
public interface ProductRepository extends CrudRepository<Product, Long> {

	public Product getProductById(Long id);
	public List<Product> findAll();
	@SuppressWarnings("unchecked")
	public Product save(Product product);
	public void delete(Long id);
	
	// trying to get 'findAll' to work
	// https://github.com/hazelcast/hazelcast-code-samples/blob/master/hazelcast-integration/spring-data-integration/src/main/java/com/hazelcast/spring/jpa/JPAMapStore.java
	
}
