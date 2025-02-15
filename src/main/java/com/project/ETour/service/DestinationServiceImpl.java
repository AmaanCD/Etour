package com.project.ETour.service;

import com.project.ETour.model.Destination;
import com.project.ETour.model.DestinationRecord;
import com.project.ETour.repository.DestinationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DestinationServiceImpl implements DestinationService{


    private final DestinationRepository destinationRepository;

    Logger logger = LoggerFactory.getLogger(getClass());


    public DestinationServiceImpl(DestinationRepository destinationRepository){
        this.destinationRepository=destinationRepository;
    }

    @Override
    public List<Destination> fetchAllDestination() {
        logger.info("All Destination called");
        return destinationRepository.findAll();
    }

    @Override
    public List<Destination> fetchDestinationsByType(String type) {
        logger.info(" Destination by type called");
        return destinationRepository.findByType(type);
    }

    public void save(DestinationRecord destinationRecord){
        Destination destination = new Destination();
        destination.setName(destinationRecord.name());
        destination.setType(destinationRecord.type());
        destination.setPrice(destinationRecord.price());
        destinationRepository.save(destination);
    }
}
