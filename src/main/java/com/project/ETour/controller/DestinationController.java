package com.project.ETour.controller;

import com.project.ETour.model.Destination;
import com.project.ETour.model.DestinationRecord;
import com.project.ETour.service.DestinationService;

import org.redisson.Redisson;
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
        ClusterServersConfig clusterConfig = config.useClusterServers()
                .addNodeAddress("redis://redis-master1:6379") // Use service name
                .addNodeAddress("redis://redis-master2:6379")
                .addNodeAddress("redis://redis-master3:6379")
                .addNodeAddress("redis://redis-master4:6379")
                .addNodeAddress("redis://redis-master5:6379")
                .addNodeAddress("redis://redis-master6:6379")
                .setPassword("Amaan@123"); // Redis password

        // Create Redisson client
        RedissonClient redisson = Redisson.create(config);

        // Use Redisson client
        // Example: Set and get a value
            redisson.getBucket("myKey").set("myValue");
        String value = (String) redisson.getBucket("myKey").get();
        System.out.println("Value from Redis Cluster: " + value);

        // Shutdown Redisson client
        redisson.shutdown();


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
