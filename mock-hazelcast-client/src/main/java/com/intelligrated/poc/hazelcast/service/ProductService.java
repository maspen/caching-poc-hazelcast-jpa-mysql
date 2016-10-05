package com.intelligrated.poc.hazelcast.service;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.cache.annotation.CacheKey;
import javax.cache.annotation.CachePut;
import javax.cache.annotation.CacheRemove;
import javax.cache.annotation.CacheResult;
import javax.cache.annotation.CacheValue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.intelligrated.poc.hazelcast.entity.Product;
import com.intelligrated.poc.hazelcast.repository.ProductRepository;

/**
 * Mock Service to apply get/set actions in a simulated repository
 * @author david.moore
 */
@Service
public class ProductService {	
	
	@Autowired
	ProductRepository repository;
	
	@CacheResult(cacheName = "shortTermCache")
	public Product getProductById(@CacheKey Long id) {
		makeSlow();
		
		return repository.getProductById(id);
	}
	
	@CacheResult(cacheName = "shortTermCache")
	public List<Product> finaAll() {
		makeSlow();
		
		return repository.findAll();
	}
	
	@CachePut(cacheName = "shortTermCache")
	public Product save(@CacheKey Long id, @CacheValue Product product) {
		makeSlow();
		
		return repository.save(product);
	}
	
	@CacheRemove(cacheName = "shortTermCache")
	public void delete(@CacheKey Long id) {
		makeSlow();
		
		repository.delete(id);
	}
    
    private void makeSlow() {
    	try {
            // Emulated repository call to simulate load
            TimeUnit.SECONDS.sleep(6);
        } catch (InterruptedException e) {
            System.out.println("Error");
        }
    }
}
