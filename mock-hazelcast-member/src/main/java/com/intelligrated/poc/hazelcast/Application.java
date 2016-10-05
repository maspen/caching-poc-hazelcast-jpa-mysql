/*********************************************
 *  Intelligrated Hazelcast Member-Client PoC
 * 
 *  2016/10/02
 *********************************************/
package com.intelligrated.poc.hazelcast;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.hazelcast.HazelcastAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cache.annotation.EnableCaching;

/**
 * Main Application for Hazelcast Member
 * @author david.moore
 */
@SpringBootApplication
@EnableAutoConfiguration(exclude = {HazelcastAutoConfiguration.class})
@EnableCaching
public class Application {
    public static void main(String[] args) {
        new SpringApplicationBuilder()
                .profiles("member")
                .sources(Application.class)
                .run(args);
    }
}
