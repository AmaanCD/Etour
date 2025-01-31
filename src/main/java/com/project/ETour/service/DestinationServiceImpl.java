package com.project.ETour.service;

import com.project.ETour.model.Destination;
import com.project.ETour.repository.DestinationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DestinationServiceImpl implements DestinationService{


    DestinationRepository destinationRepository;

    public DestinationServiceImpl(DestinationRepository destinationRepository){
        this.destinationRepository=destinationRepository;
    }

    @Override
    public List<Destination> fetchAllDestination() {
        return destinationRepository.findAll();
    }

    @Override
    public List<Destination> fetchDestinationsByType(String type) {
        return destinationRepository.findByType(type);
    }
}
