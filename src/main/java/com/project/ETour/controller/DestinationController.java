package com.project.ETour.controller;

import com.project.ETour.model.Destination;
import com.project.ETour.model.DestinationRecord;
import com.project.ETour.service.DestinationService;

import org.redisson.Redisson;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.redisson.config.ClusterServersConfig;
import org.redisson.config.Config;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class DestinationController {

    private final DestinationService destinationService;

    public DestinationController(DestinationService destinationService){
        this.destinationService = destinationService;
    }

    @GetMapping(value = "/destination")
    public ResponseEntity<List<Destination>> fetchAllDestination(){

        Config config = new Config();
//             config.useClusterServers()
//                .addNodeAddress("redis://redis-master1:6379") // Use service name
//                .addNodeAddress("redis://redis-master2:6379")
//                .addNodeAddress("redis://redis-master3:6379")
//                .addNodeAddress("redis://redis-master4:6379")
//                .addNodeAddress("redis://redis-master5:6379")
//                .addNodeAddress("redis://redis-master6:6379")
//                .setPassword("Amaan@123"); // Redis password
try {
    config.useClusterServers()
            // Note: Use service names defined in docker-compose (and internal container ports)
            .addNodeAddress(
                    "redis://redis-master1:6379",
                    "redis://redis-node1:7000",
                    "redis://redis-node2:7001",
                    "redis://redis-node3:7002"

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

    // Shutdown Redisson client
    redisson.shutdown();
}catch (Exception e){
    System.out.println(e.getMessage());
    System.out.println(e.fillInStackTrace());
}


      return  new ResponseEntity<>( this.destinationService.fetchAllDestination(), HttpStatusCode.valueOf(200));
    }

    @GetMapping(value = "/destination/{type}")
    public ResponseEntity<List<Destination>> fetchDestinationsByType(@PathVariable String type){
        return new ResponseEntity<>(this.destinationService.fetchDestinationsByType(type),HttpStatusCode.valueOf(200));
    }

    @PostMapping(value = "/destination")
    public ResponseEntity<Void> save(@RequestBody DestinationRecord destinationRecord){
        this.destinationService.save(destinationRecord);
        return new ResponseEntity<Void>(HttpStatusCode.valueOf(201));
    }

}
