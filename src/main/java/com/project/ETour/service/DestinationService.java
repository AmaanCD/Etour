package com.project.ETour.service;

import com.project.ETour.model.Destination;
import com.project.ETour.model.DestinationRecord;

import java.util.List;

public interface DestinationService {

    List<Destination> fetchAllDestination();

    List<Destination> fetchDestinationsByType(String type);

    void save(DestinationRecord destinationRecord);

}
