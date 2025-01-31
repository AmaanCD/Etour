package com.project.ETour.controller;

import com.project.ETour.model.Destination;
import com.project.ETour.service.DestinationService;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
      return  new ResponseEntity<>( this.destinationService.fetchAllDestination(), HttpStatusCode.valueOf(200));
    }

    @GetMapping(value = "/destination/{type}")
    public ResponseEntity<List<Destination>> fetchDestinationsByType(@PathVariable String type){
        return new ResponseEntity<>(this.destinationService.fetchDestinationsByType(type),HttpStatusCode.valueOf(200));
    }

}
