package com.project.ETour.config;

import org.redisson.Redisson;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
public class CacheConfig {

    @Bean
    public RedissonClient createRedisCache(){
        Config config = new Config();
        try {
            config.useClusterServers()
                    // Note: Use service names defined in docker-compose (and internal container ports)
                    .addNodeAddress(

                            "redis://redis-node1:7000"
                   /* "redis://redis-node2:7001",
                    "redis://redis-node3:7002"*/

                    );
            System.out.println(config);
            // Create Redisson client
            RedissonClient redisson = Redisson.create(config);

            // Example: Working with a distributed map
            RMap<String, String> distributedMap = redisson.getMap("myDistributedMap");
            distributedMap.put("key", "value");

            // Retrieve and print the value
            String value = distributedMap.get("key");
            System.out.println("Value from distributed map: " + value);

            return redisson;
        }catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println(e.fillInStackTrace());
        }
        return null;
    }


}
