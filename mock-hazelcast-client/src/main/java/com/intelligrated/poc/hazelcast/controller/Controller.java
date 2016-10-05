/*********************************************
 *  Intelligrated Hazelcast Member-Client PoC
 * 
 *  2016/10/02
 *********************************************/
package com.intelligrated.poc.hazelcast.controller;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.intelligrated.poc.hazelcast.entity.Product;
import com.intelligrated.poc.hazelcast.service.ProductService;

/**
 * Controller to trigger get/set on simulated 'repository' that has
 * front end Hazelcast caching enabled
 * @author david.moore
 */
@RestController
public class Controller {

    @Autowired
    ProductService productService;
    
    // gain access to Hazelcast
//    @Autowired
//	HazelcastInstance client;
//    HazelcastClient client;

    @RequestMapping(value = "/getProductById/{id}", method = RequestMethod.GET)
    public Product getProductById(@PathVariable("id") Long id) {
      long start = System.nanoTime();
      Product product = productService.getProductById(id);
      
      System.out.println(String.format("getProductById(id) took: %s millis", (TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - start))));
      
      return product;
    }
    
    @RequestMapping(value = "/finaAll", method = RequestMethod.GET)
    public List<Product> findAll() {
    	long start = System.nanoTime();
    	List<Product> returnedList = productService.finaAll();
    	
    	System.out.println(String.format("findAll() took: %s millis", (TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - start))));
    	
    	return returnedList;
    }
    
    @RequestMapping(value = "/save/{id}/", method = RequestMethod.PUT)
    public Product save(@PathVariable("id") Long id, @RequestBody Product product) {
    	long start = System.nanoTime();
    	Product savedProduct = productService.save(id, product);
    	
    	System.out.println(String.format("save(id,product) took: %s millis", (TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - start))));
    	
    	return savedProduct;
    }
    
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id) {
    	long start = System.nanoTime();
    	productService.delete(id);
    	
    	System.out.println(String.format("delete(id) took: %s millis", (TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - start))));
    }
    
//    @RequestMapping(value = "/getclients", method = RequestMethod.GET)
//    public Collection<HazelcastInstance> getClients() {
//    	Collection<HazelcastInstance> clients = HazelcastClient.getAllHazelcastClients();
////    	return client.getMap("shortTermCache");
//    	return clients;
//    }
}
